import requests
import pprint
import string
import re
import os
import json

#from flask import Flask, request, redirect
#import twilio.twiml

def sendText(phone, message):
    cmd = 'curl -X POST http://textbelt.com/text -d number='
    cmd += phone
    cmd += ' -d "message='
    cmd += message
    cmd += '"'
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
    print("Getting: " + url)
    r = requests.get(url)   
    obj = r.json()

    distance = obj["routes"][0]["legs"][0]["distance"]["text"]
    duration = obj["routes"][0]["legs"][0]["duration"]["text"]
    out = str(distance) + ";" + str(duration)
    steps = obj["routes"][0]["legs"][0]["steps"]
    i = 1
    for step in steps:
        dist = step["distance"]["text"]
        dur = step["duration"]["text"]
        directions = step["html_instructions"]
        directions = re.sub("<[^>]*>", '', directions)
        out += ("~" + str(dist) + ";" + str(dur) + ";" + str(directions))
        i = i + 1

    return out

###################################################################################################################################

app = Flask(__name__)
 
@app.route("/", methods=['GET', 'POST'])
def recieveMessage():
    """Respond to incoming calls with a simple text message."""
    msg = request.values.get('Body', None)
    recvNumber = reques.values.get('From', None)
    if msg is not None:
        msg = string.replace(msg, " ", "")
    	params = msg.split(";")
        output = getDirections(params[0], params[1])
        sendText(recvNumber, output)
        print(output)
    else:
   	    msg = "Invalid" 

    #resp = twilio.twiml.Response()
    #resp.message("Hello, Mobile Monkey")
    #return str(resp)
 
if __name__ == "__main__":
    app.run(host='0.0.0.0')
