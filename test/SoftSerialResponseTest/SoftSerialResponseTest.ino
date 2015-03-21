
int readbyte;
String inData; // Allocate some space for the string
char inChar; // Where to store the character read
byte index = 0; // Index into array; where to store the character
int led = 13;

#include <SoftwareSerial.h>

SoftwareSerial mySerial(10, 11); // RX, TX

// the setup routine runs once when you press reset:
void setup() {
  pinMode(led, OUTPUT);  
  
   // set the data rate for the SoftwareSerial port
  mySerial.begin(9600);
  //mySerial.println("Hello, world?");
  
}



void loop()
{
   while(mySerial.available() > 0) // Don't read unless                                               // there you know there is data
   {
           inChar = mySerial.read(); // Read a character
           inData += inChar; // Store it
           
       
   }
   if(inData.equals("ID\n")){
     mySerial.println("test v0.1.2");
     inData = "";
   }else if(inData.equals("VOLT")){
      mySerial.println("34 VOLT");
      inData = "";
   }else if(inData.equals("AMPER")){
      mySerial.println("0 AMPER");
      inData = "";
   }else if(inData.equals("POWER")){
      mySerial.println("50 WATT");
      inData = "";
   }else if(inData.equals("EXIT")){
      mySerial.println("BYE");
      inData = "";
   }else if(inData.equals("LED")){
     if(digitalRead(led) == LOW){
        mySerial.println("LED ON");
        digitalWrite(led,HIGH);
     }else{
      mySerial.println("LED OFF");
      digitalWrite(led,LOW);
     }
      inData = "";
   }
   
   
   // Now do something with the string (but not using ==)
}


