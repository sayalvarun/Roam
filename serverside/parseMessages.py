import requests
import pprint
import string
import re
import os
import json
import sys
import time
import zlib
from flask import Flask, request, redirect
import twilio.twiml
import base64


def sendTextBelt(phone, message):
    cmd = 'curl -X POST http://textbelt.com/text -d number='
    cmd += phone
    cmd += ' -d "message='
    cmd += message
    cmd += '"'
    #print("cmd = "+cmd)
    os.system(cmd)
 
def getKey():
    f = open('../key.txt')
    key = f.read()
    return key

def getURL(source, destination):
    url = 'https://maps.googleapis.com/maps/api/directions/json?origin='
    url += source
    url += "&destination="
    url += destination
    url += "&key="
    url += getKey()
    return string.replace(url, "\n", "")

def getDirections(source, destination):
    url = getURL(source, destination)
    r = requests.get(url)   
    obj = r.json()
    status = obj["status"]
    if status != "OK":
        return (1, "Error: bad status from Google Maps!")

    distance = obj["routes"][0]["legs"][0]["distance"]["text"]
    duration = obj["routes"][0]["legs"][0]["duration"]["text"]
    out = "directions~" + str(distance) + ";" + str(duration)
    steps = obj["routes"][0]["legs"][0]["steps"]
    i = 1
    for step in steps:
        dist = step["distance"]["text"]
        dur = step["duration"]["text"]
        directions = step["html_instructions"]
        directions = re.sub("<[^>]*>", '', directions)
        out += ("~" + str(dist) + ";" + str(dur) + ";" + str(directions))
        i = i + 1

    return (0, out)
    
def getWeather(latitude, longitude):
    api_url = "https://api.forecast.io/forecast/"
    api_key = "b331b49ba65c13f9e9c3326dbd4475ef/"

    result = "weather~"
    rescode = 0;
    try:
        currtime = int(time.time())
        print currtime
        nextday = currtime + (24*60*60)
        print nextday
        #print api_url + api_key + latitude + "," + longitude + "," + str(currtime)
        pgjson = requests.get(api_url + api_key + latitude + "," + longitude + "," + str(currtime)).json()
        result += pgjson['hourly']['summary']
        pgjson = requests.get(api_url + api_key + latitude + "," + longitude + "," + str(nextday)).json()
        result += ";"
        result += pgjson['hourly']['summary']
    except Exception, e:
        result += "Error in retreiving data"
        rescode = 1;
        
    return (rescode, result)

###################################################################################################################################

app = Flask(__name__)
 
@app.route("/", methods=['GET', 'POST'])
def recieveMessage():
    """Respond to incoming calls with a simple text message."""
    msg = request.values.get('Body', None)
    #print("msg received "+msg)
    #recvNumber = string.replace(request.values.get('From', None),"+","")
    #recvNumber = recvNumber[-10:]
    output = ""
    command = None
    args = None
    if msg is not None:
        arr = msg.split("~")
        command = arr[0]
        args = arr[1]

        if command == "directions":
            params = args.split(";")
            err, output = getDirections(str(params[0]), str(params[1]))
            if err == 0:
                compressed = zlib.compress(output)
                encodeComp = base64.b64encode(compressed)
                output = encodeComp
        elif command == "weather":
            params = args.split(";")
            err, output = getWeather(str(params[0]), str(params[1]))
            if err == 0:
                compressed = zlib.compress(output)
                encodeComp = base64.b64encode(compressed)
                output = encodeComp
        else:
            output = "Error: Invalid Command!"
    else:
   	    output = "Error: Empty Message!" 

    resp = twilio.twiml.Response()
    resp.message(output)
    return str(resp)
 
if __name__ == "__main__":
    app.debug = True
    app.run(host='0.0.0.0')
