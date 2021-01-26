# GRPC Spice Prototype
This is a prototype on how we plan on marshalling JNISpice calls with grpc.  

### Use 
Load this in IntelleJ as a Gradle project.  
Relevant java code is at ../src/main/java/parspiceTest.  

### General Overview
GClinet.java has code that would be expected of what interaction with ParSpice would look like.  
GServer.java has opens up GRPC endpoints for the handling of ParSpice calls.

### Basic Structures
#### Used on the client side
FunctionObjects represent individual spice calls and holds parameters for such calls. FunctionObjects are usually named with with the name of the spice function name that it represents.  
Examples: FurnshObject, Str2EtObject  
  
FunctionParCommands handles message marshalling of the spice calls meant to be ran concurrently. It uses FunctionObjects to store the parameters of each spice call. When distribute() is called, the FunctionObject data is converted into a GRPC proto struct and sent to the worker server. The FunctionParCommand will then wait for a ParResponse object to be returned.    
Examples: FurnshParCommandObject, Str2EtParCommandObject  
  
ParClientCmdHandler will convert FunctionObjects into GRPC structures and send it to the worker service.  
  
ParResponse is a structure that manages the results of the FunctionParCommands. It contains a get and add function for the ArrayList of Doubles that it encapsulates.  

#### Used on the server side
ParEngineCmdHandler holds refrences to the JNISpice library calls. It is used by the worker to make spice calls.  

### GClient.java
#### Client interaction
FunctionParCommand objects are initialized. These objects will represent a parspice call.  
The addArg() function will be used to queue up spice calls intended to be called concurrently. Each addArg function should have parameters that correspond with their respective spice functions.  
The distribute() function will then send the data to the ParSpice engine to be processed via the ParClientCmdHandler object.  


### GServer.java 
The code opens up relevant GRPC endpoints to service the client.  
The respective endpoints use a ParEngineCmdHandler object to handle the requests.  
A ParResponse object is filled with the results. The server will then turn the object into a proto struct to be sent back to the client. The client will recieve the proto struct and turn it back into a ParResponse object for it to parse through.  


### Adding a New Function
The following things need to be accounted for when adding a new function to be handled:
- Create a FunctionObject
- Create a FunctionParCommand
- Add entry to ParClientCmdHandler
- Add FunctionBundle, FunctionReq, FunctionRep to grpc proto file
- Add entry to GServer
- Add entry to ParEngineCmdHandler

### Notes
Functions follow a camel case naming convention. (https://www.geeksforgeeks.org/java-naming-conventions/#:~:text=Java%20uses%20CamelCase%20as%20a,letter%2C%20rest%20all%20with%20capital.)
