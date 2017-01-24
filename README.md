![Roam Logo](https://raw.githubusercontent.com/sayalvarun/Roam/master/MyApplication/app/src/main/res/drawable/roam.png)

##[See this project on Devpost](https://devpost.com/software/roam-yno5mc)

## What is Roam?
Roam is an Android application that allows users to get directions, request a ride from Uber, and even see what the weather will be like without using cellular data!<br>
Using Twilio, Roam sends and receives requested data through SMS messages sent to the user's phone. The SMS message is then interpreted by our app and displayed back to the user in a meaningful way, all through our interface.
## How does Roam work?
When a button in the Roam application is pressed, Roam gathers user input and forms a message which is then sent to our Twilio phone number using Java. This number forwards the message to our server where the message is parsed and interpreted into one of three categories: directions, Uber, and weather. The server then performs the necessary API calls and forms the response message in our own shorthand. This shorthand message is then compressed, encoded into base-64, and sent back to the originating number through Twilio. As the message is received by the user's phone, Roam detects, decodes, and decompresses the message before displaying the results for the user to see in a user-friendly display.
