
int readbyte;
String inData; // Allocate some space for the string
char inChar; // Where to store the character read
byte index = 0; // Index into array; where to store the character
int led = 13;

// the setup routine runs once when you press reset:
void setup() {
  // initialize serial communication at 9600 bits per second:
  Serial.begin(9600);
  pinMode(led, OUTPUT);  
  
}



void loop()
{
   while(Serial.available() > 0) // Don't read unless                                               // there you know there is data
   {
           inChar = Serial.read(); // Read a character
           inData += inChar; // Store it
           
       
   }
   if(inData.equals("ID")){
     Serial.println("test v0.1.2");
     inData = "";
   }else if(inData.equals("VOLT")){
      Serial.println("34 VOLT");
      inData = "";
   }else if(inData.equals("AMPER")){
      Serial.println("0 AMPER");
      inData = "";
   }else if(inData.equals("POWER")){
      Serial.println("50 WATT");
      inData = "";
   }else if(inData.equals("EXIT")){
      Serial.println("BYE");
      inData = "";
   }else if(inData.equals("LED")){
     if(digitalRead(led) == LOW){
        Serial.println("LED ON");
        digitalWrite(led,HIGH);
     }else{
      Serial.println("LED OFF");
      digitalWrite(led,LOW);
     }
      inData = "";
   }
   
   
   // Now do something with the string (but not using ==)
}


