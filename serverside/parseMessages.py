from flask import Flask, request, redirect
import twilio.twiml
 
app = Flask(__name__)
 
@app.route("/", methods=['GET', 'POST'])
def recieveMessage():
    """Respond to incoming calls with a simple text message."""
    msg = request.values.get('Body', None)
    if msg is not None:
    	print(msg)
    else:
   	msg = "Invalid" 

    resp = twilio.twiml.Response()
    resp.message("Hello, Mobile Monkey")
    return str(resp)
 
if __name__ == "__main__":
    app.run(host='0.0.0.0')
