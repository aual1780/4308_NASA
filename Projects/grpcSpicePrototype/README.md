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
  
ParResponse is a structure that manages the results of the FunctionParCommands. It contains a get and add function for the ArrayList of Doubles that it encapsulates.  

#### Used on the server side
ParEngineCmdHandler holds refrences to the JNISpice library calls. It is used by the worker to make spice calls.  
  
### GClient.java wip
ParCommand objects are initialized. They will represent a parspice call.
addArg function will add parameters. used to add spice calls to par spice
distribute will send data via ParClientCmdHandler
ParClientCmdHandler handles rpc logic

### GServer.java wip
Opens the grpc endpoints 
Has grpc endpoints for each Spice call
Endpoints use a ParEngineCmdHandler
ParEngineCmdHandler calls JNISpice library

### Notes
Functions follow a camel case naming convention. (https://www.geeksforgeeks.org/java-naming-conventions/#:~:text=Java%20uses%20CamelCase%20as%20a,letter%2C%20rest%20all%20with%20capital.)
