# JWT JSON Web Token
It provides a secure connection between peers.  

Three things can be used to make something secure:  

1. Something you have, for example a credit card, mobile phone.  
2. Something you know, for example a password, security question.  
3. Something you are, for example fingerprint, dna, face-scan.  

**Advantages of JWT Token**  
Since JWTs are stateless, there is no need to query the database to obtain user states.  
Session management can be done without using cookies.  
A single key can be used on multiple backends.  
It is high performance because it does not require database query or file system usage.  

**Disadvantages**  
If the JWT key is not strong enough, it will be easily break.   

JWT consists of 3 separate pieces of JSON encoded in Base64 format. Parts are separated by a dot (.) symbol and represent the JWT as a whole.  
These parts consist of header, payload and verify signature.  
![image](https://user-images.githubusercontent.com/43732258/157490068-7f6a5cb7-3beb-4266-a1c1-96bafaac3e23.png)  

**Header**  
JWT header information is written in JSON format and has some standard fields.  
“alg” Specifies the cryptotic algorithm to be used to protect data integrity. You can choose the algorithm you want here.  
“type” indicates that it is a JWT object.  
**Payload**  
The payload may contain fields such as the user's identity, timeout and user authorizations. In addition to these, if role-based operation is to be performed on the system, it is specified here.  
**Verify signature**  
Verify signature guarantees data integrity between the signature part token producer and consumer. While creating the signature, the algorithm defined in the JOSE header is used.  

**How JWT Works ?**

The first step starts with the user logging in to the server with credentials (e.g userID and password).  
The Credentials are authenticated by the server, then server returns JWT token back to the client.  
This token is saved in local or cookies.  
Then token goes back to the server for logging.  
If the JWT token really created by that server user access gets accapted.  



# Microservice architecture
# User login and registration forms



## References
https://tugrulbayrak.medium.com/jwt-json-web-tokens-nedir-nasil-calisir-5ca6ebc1584a    
https://medium.com/kodluyoruz/json-web-token-jwt-authentication-b5e6675a6e19  
https://jwt.io/introduction/  
