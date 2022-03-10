# Reflection 

A feature that provides the opportunity to examine and direct the runtime behavior of applications running in the JVM.  
It is used to obtain, control and manage information such as names and parameters of classes, methods, properties and annotations. 
You can call a class without creating an instance of that class with reflection.  
And by using that call you can, create an instance, call methods, find superclasses and implemented interfaces, and access modifiers.  
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

Reflections are useful with code generators, generic structures.  

**Converter example:**  
```java
public static void main(String[] args) {

        Class clazzSource = AccAccount.class;
        Class clazzTarget = AccAccountDto.class;

        String simpleName = clazzTarget.getSimpleName(); //Gets class name
        Method[] declaredMethods = clazzTarget.getDeclaredMethods(); //Gets declared methods addresses

        System.out.println(simpleName + " target = new " + simpleName + "();");

        for (Method eachMethod : declaredMethods) { //For each method in declaredMethod array
            String name = eachMethod.getName(); //Get methods name

            if (name.startsWith("set")){ //If method name starts with set

                String getName = name.replace("set", "get"); //Replace set with get

                System.out.println("target." + name + "(source." + getName + "());");
            }
        }
        System.out.println("return target;");


    }
```
WARNING: There are many downsides of using reflection (harder to understand, inaccessible to static analysis,not type-checked by the compiler, unavailable for optimization,slow in general, anti-encapsulation).   
Avoid using it as much as possible. Frameworks use it because they have to and it might be useful if you are using generics to get type etc.   

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
**To implement HATEOAS to your controller:**  
WebMvcLinkBuilder is used for the implementation.  
Links that are coming from the WebMvcLinkBuilder are added with EntityModel.   
The linked entity model is filled with MappingJacksonValue.  
Returns mappedJacksonValue as ResponseEntity.  

```java
@PostMapping
public ResponseEntity save(@RequestBody CusCustomerSaveRequestDto cusCustomerSaveRequestDto){
    
    CusCustomerDto cusCustomerDto = cusCustomerService.save(cusCustomerSaveRequestDto);
    
    WebMvcLinkBuilder link = WebMvcLinkBuilder.linkTo(
    		WebMvcLinkBuilder.methodOn(this.getClass())
		.findById(cusCustomerDto.getId()) );
    
    EntityModel entityModel = EntityModel.of(cusCustomerDto); //A simple EntityModel wrapping a domain object and adding links to it.
    
    entityModel.add(link.withRel("find-by-id")); //Explaining the link.
    
    MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(entityModel); 
    //A simple holder for the POJO to serialize  with further serialization instructions to be passed in to the converter.
    
    return ResponseEntity.ok(RestResponse.of(mappingJacksonValue));

}
```
```json
HTTP/1.1 200 OK
 Content-Type: application/vnd.acme.account+json
 Content-Length: …
 {
    "data":{
     "value": {
         "id": 52,
         "name": "Yusuf",
         "surname": "Memiş",
         "identityNo": 12345678901,
         "links": [
         {
             "rel":"find by id",
             "href": "http://localhost:8080/api/v1/customer/52"
         }
       ]
     },
     "responseDate":"2022-02-27T06:26:48,136+00:00",
     "messages":null,
     "success":true
   }
 }
```

## Spring Data Jpa Audit  
Enabling auditing (of entities) with Spring Data Jpa’s @CreatedDate and @LastModified.  
Spring Data Jpa provides auditing feature which includes @CreateDate, @CreatedBy, @LastModifiedDate, and @LastModifiedBy.  
These auditions are java level controls.  
It keep track when an entry is created with created column and when it is modified with modified column.  

It is a better practice to create AdditionalFields named class in general package.  
```java
@Embeddable
@Getter
@Setter
public class BaseAdditionalFields {

    @Column(name = "CREATE_DATE", updatable = false)
    @CreatedDate
    private Date createDate;

    @Column(name = "UPDATE_DATE")
    @LastModifiedDate
    private Date updateDate;

    @Column(name = "CREATED_BY")
    @CreatedBy
    private Long createdBy;

    @Column(name = "UPDATED_BY")
    @LastModifiedBy
    private Long updatedBy;
}
```
In general package, BaseEntityService add the following method:  
```java
   private void setAdditionalFields(E entity) {

        BaseAdditionalFields baseAdditionalFields = entity.getBaseAdditionalFields();

        Long currentCustomerId = getCurrentCustomerId();

        if (baseAdditionalFields == null){
            baseAdditionalFields = new BaseAdditionalFields();
            entity.setBaseAdditionalFields(baseAdditionalFields);
        }

        if (entity.getId() == null){
            baseAdditionalFields.setCreateDate(new Date());
            baseAdditionalFields.setCreatedBy(currentCustomerId);
        }

        baseAdditionalFields.setUpdateDate(new Date());
        baseAdditionalFields.setUpdatedBy(currentCustomerId);
    }
```
Finally, in entity service of every package you can add the additional fields by setters, and save the rest.  
```java
public AccAccountDto save(AccAccountSaveRequestDto accAccountSaveRequestDto) {

        String ibanNo = getIbanNo();
        Long currentCustomerId = accAccountEntityService.getCurrentCustomerId();

        //Converting dto to entity
        AccAccount accAccount = AccAccountMapper.INSTANCE.convertToAccAccount(accAccountSaveRequestDto);
        
        //Setting additional fields
        accAccount.setStatusType(GenStatusType.ACTIVE);
        accAccount.setIbanNo(ibanNo);
        accAccount.setCusCustomerId(currentCustomerId);

        //Saving other fields
        accAccount = accAccountEntityService.save(accAccount);

        //Converting back to dto
        AccAccountDto accAccountDto = AccAccountMapper.INSTANCE.convertToAccAccountDto(accAccount);

        return accAccountDto;
    }

```

# Transactions   
The transaction is a error safe operation block.  

Transactions follow the ACID properties:  
**Atomicity**: If all steps are successful commit, at least one error happens rollback (all or nothing).  
**Consistency**: The results are consistent, results same everytime.    
**Isolation**: Transactions should not be concurrent and affect each other at the same time.  
**Durability**: Once the transaction happened, it stays happened.  

In Spring declerative transactions are used (using predefined annotations).   
Declarative programming is writing the code in a way that it describes what is wanted to do, and not how it is wanted to do.   
Declarative transactions means separating transaction management from the business code.  

**Transaction Annotations**  
**@Transactional**: We can use @Transactional to wrap a method in a database transaction.  
It allows us to set propagation, isolation, timeout, read-only, and rollback conditions for our transaction. We can also specify the transaction manager.  

Spring creates a proxy, or manipulates the class byte-code, to manage the creation, commit, and rollback of the transaction.  
If we have a method named callMethod marked as @Transactional, Spring will wrap some transaction management code around the invocation.  
```java
createTransactionIfNecessary();
try {
    callMethod();
    commitTransactionAfterReturning();
} catch (exception) {
    completeTransactionAfterThrowing();
    throw exception;
}
```
Spring applies the class-level annotation to all public methods of this class that we did not annotate with @Transactional.  
However, if we put the annotation on a private or protected method, Spring will ignore it without an error.  
We can put the @Transactional on definitions of interfaces, classes, or directly on methods.  
Putting on interfaces is not suggested but acceptable in case of Repository (because Repository has to be an interface).  


Circular Dependency might occur when NonTransactionalService and TransactionalService get injected to each other.   

**Circular Dependency**  
A circular dependency occurs when two classes depend on each other.  

To solve circular dependency in that situation:  
```java
class TransactionalService{
	private final NonTransactionalService nonTransactionalService;
	...	
}
class NonTransactionalService{
	private final TransactionalService transactionalService;	
	@Autowired
	setTransactionalService(@Lazy TransactionalService tService){
		this.transactionalService = tService;
	}
	...
}
```

**@Transactional(propagation = Propagation.REQUIRED)**
"Required" is the default propagation for @Transactional classes.  
Spring checks if there is an active transaction, and if nothing exists, it creates a new one.  
Otherwise, the business logic appends to the currently active transaction.  

Even the Service layer is non-transactional, Database methods in JPARepository are Transactional.  
When operation is done in Transactional layer, changes gets committed.  

If transactional -> transactional, non-transactional -> tranctional.   

Used in Repository-like services.  

**@Transactional(propagation = Propagation.SUPPORTS)**
Spring first checks if an active transaction exists.  
If a transaction exists, then the existing transaction will be used.  
If there isn't a transaction, it is executed non-transactional.  

If transactional -> transactional, non-transactional -> non-transactional.  

Used to imitate the caller's context, if your caller was running in transaction, then you run in too.  

**@Transactional(propagation = Propagation.MANDATORY)**  
if there is an active transaction, then it will be used.  
If there isn't an active transaction, then Spring throws an exception.   

If transactional -> transactional, if non-transactional -> exception.  

Used to continue the caller's transactional operation, continue to the transaction else throw exception.  

**@Transactional(propagation = Propagation.NEVER)**  
Spring throws an exception if there's an active transaction.  

If  transactional -> exception, non-transactional -> non-transactional.  

Used to be sure that no transaction exists at all, if a transaction reaches throw exception.    

**@Transactional(propagation = Propagation.NOT_SUPPORTED)**  
If a current transaction exists, first Spring suspends it, and then the business logic is executed without a transaction.   

If transactional -> non-transactional, non-transactional -> non-transactional.  

A weaker version of the never. If transactional reaches, suspend and continue non-transactional.  

**@Transactional(propagation = Propagation.REQUIRES_NEW)**  
Spring suspends the current transaction if it exists, and then creates a new one.   

If transactional -> transactional with new transaction, non-transactional -> transactional.    

Used when the code's flow somehow changes and you want to split your logic into multiple transactions.  

**@Transactional(propagation = Propagation.NESTED)**  
For NESTED propagation, Spring checks if a transaction exists, and if so, it marks a save point.    
This means that if our business logic execution throws an exception, then the transaction rollbacks to this save point.    
If there's no active transaction, it works like REQUIRED.    

*The "Nested" is only supported for JDBC operations.*    

Used to create nested transactions.  


## RESOURCES
https://www.evrenbal.com/restapi-ve-hateoas-kavrami/  
https://www.baeldung.com/transaction-configuration-with-jpa-and-spring  
https://medium.com/@dururyener/transaction-y%C3%B6netimi-ve-spring-boot-transactional-kullan%C4%B1m%C4%B1-f894cc66c9d9  
https://rashidi.github.io/spring-boot-data-audit/  
https://www.javainuse.com/spring/boot-transaction-propagation  
https://rashidi.github.io/spring-boot-data-audit/  
https://glowing-crest-6d5.notion.site/Softtech-Java-Spring-Bootcamp-191efcce77654cd493643314176e4957  
https://www.baeldung.com/spring-transactional-propagation-isolation  
