# Reflection 

A feature that provides the opportunity to examine and direct the runtime behavior of applications running in the JVM.  
It is used to obtain, control and manage information such as names and parameters of classes, methods, properties and annotations.  
For the use of Reflection, it will be useful to know the Class, Method, Field, Annotation classes and methods in the java.lang package.  
The Class class has methods such as getName, getSimpleName, getModifiers, getPackage, getSuperclass, getInterfaces, getConstructors, getMethods, getFields, getAnnotations to get information about the class.  
When it is desired to run a method with a reflection structure, the invoke() method in the Method class can be used.  
With the Reflection structure, it is also possible to access non-accessible areas such as private and protected in the class.  
The most important use of the Java Reflection structure is the annotations structure because structures such as Spring, Hibernate, and JAXB (XML operations) operate using reflection and annotations.  

The following method prints the annotations in the class given as a parameter:  
```java
public static void yazdir(Class<?> annotationClass) {
    for (Annotation annotation : annotationClass.getAnnotations()) {
        System.out.println(annotation.toString());
    }
}
```

By using the reflection structure, operations such as dynamically creating objects from the class, class loading, dependency management (DI) can be done easily.  
However, unexpected results may occur when the reflection structure is not used appropriately.  


# HATEOAS  
HATEOAS stands for Hypermedia as the Engine of Application State.  
3. level (Hypermedia control) of the Richardson Maturity Level.  
With HATEOAS, clients can use a REST API with minimal documentation.  
A good REST Api (has HATEOAS) often sends the list/links of other resources related to this resource, the action to be taken and the information that can be obtained.  
Example:
A request goes to accounts api.  

```json
GET /accounts/12345 HTTP/1.1
Host: bank.example.com
Accept: application/vnd.acme.account+json
...
```

The response is the following.  

```json
HTTP/1.1 200 OK
Content-Type: application/vnd.acme.account+json
Content-Length: ...
{
    "account": {
        "account_number": 12345,
        "balance": {
            "currency": "usd",
            "value": 100.00
        },
        "links": {
            "deposit": "/accounts/12345/deposit",
            "withdraw": "/accounts/12345/withdraw",
            "transfer": "/accounts/12345/transfer",
            "close": "/accounts/12345/close"
        }
    }
}
```
As you can see, links also return.  
Rest api means, "you can do these operations" by sending these links.  

After doing a operation that caused the account balance be negative.  
Rest api returns only deposit link, not others.  
As you can see there is a sort of programming happens here as well.  

```json
HTTP/1.1 200 OK
 Content-Type: application/vnd.acme.account+json
 Content-Length: …
 {
     "account": {
         "account_number": 12345,
         "balance": {
             "currency": "usd",
             "value": -25.00
         },
         "links": {
             "deposit": "/accounts/12345/deposit"
         }
     }
 }
```
# Transaction  

## RESOURCES
https://www.evrenbal.com/restapi-ve-hateoas-kavrami/  
https://www.baeldung.com/transaction-configuration-with-jpa-and-spring  
https://medium.com/@dururyener/transaction-y%C3%B6netimi-ve-spring-boot-transactional-kullan%C4%B1m%C4%B1-f894cc66c9d9  
https://tugrulbayrak.medium.com/jwt-json-web-tokens-nedir-nasil-calisir-5ca6ebc1584a  
https://rashidi.github.io/spring-boot-data-audit/  
https://www.javainuse.com/spring/boot-transaction-propagation  
