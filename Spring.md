# Spring Notes
## Spring Core

**The Spring Framework** (Spring) is a modular, open-source application framework that provides infrastructure support for developing Java applications.
One of the most popular Java Enterprise Edition (Java EE) frameworks, Spring helps developers create high-performing applications using plain old Java objects (POJOs).  
Rod Johnson created it in 2003 because the EJB had architectural problems. Spring is the most popular framework for java.  

**Plain old java object(POJO)**: An ordinary Java object, not bound by any special restriction.  
It should not implement or extend any class/interface.  
POJO classes have an id value and getter/setters.    

**Bean**: A special kind of POJO that has some restrictions. Beans must be serializable. Fields must be private. Fields should have getters/setters.  
There must be a no-argument constructor. Fields are only accessed by constructors or getters/setters.  

**Extensible Markup Language(XML)**: simple text-based format for representing structured information. For example : <menu><submenuitem text=”example” file=”example.html”/></menu>  

**Annotation**: In dictionary meaning, annotation is a note added by way of comment or explanation.  
In programming, an annotation is a form of metadata that provides data about a program that is not a part of the program itself (For example @Configuration, @Bean, @Import, and @DependsOn annotations).  

**Aspect-oriented Programming(AOP)**: Modularization of crosscutting concerns(concerns that affect every layer of a software architecture) such as transaction management, logging, security.  

**Dependency of an object**: An object’s dependency is other objects that they work with (dependent to work).  

**Inversion of Control(IoC)**: Taking the control of creating and instantiating the beans from the developer to the framework.  
To make it possible, the Spring frameworks use abstractions and rely on object graphs generated at runtime.  

**Advantages of IoC principle**:
Decouples the task implementation from its execution.  
Modules are pluggable and can be easily replaced with their equivalent.  
Eases out the modular testing.  

**Methods for achieving Inversion Of Control**: Strategy Design Pattern, Service Locator Pattern, or Dependency Injection.

### **Dependency Injection**:  
An Inversion of control pattern, whereby one object supplies the dependencies of another object.  
The dependency injection concept promotes loose coupling among Java objects that makes the application easier to maintain and test.  

The traditional method:  
```java
public class Person {
    private Address address;
    public Person() {
        this.address = new Address();
    }
    ...
}
```
We’ll not create objects on our own and rather inject them.
The following is an example of dependency injection by using constructor:  
```java
public class Person {
    private Address address;
    public Person(Address address) {
        this.address = address;
    }
    ...
}
```

**Spring supports 3 types of dependency injection**:  
1. **Constructor-based injection**  

Spring will use the matching constructor to resolve and inject the dependency.  

i. We can either configure the beans in applicationContext.xml:  

```xml
<bean id="address" class="com.programmergirl.domain.Address"/>
<bean id="person" class="com.programmergirl.domain.Person">
    <constructor-arg ref="address"/>
</bean>
```

ii. Or, we can enable the <component-scan/> in our applicationContext.xml:  

```xml
<context:component-scan base-package="com.programmergirl.domain" />
```

Than we make the Spring configurations using the annotations:  

```java
package com.example.domain;
@Component
public class Person {
    private Address address;
    @Autowired
    public Person(Address address) {
        this.address = address;
    }
}
```
Spring, by default, wires the beans by their type.  
If there are more than one beans of the same type, we can use @Qualifier annotation to reference a bean by its name:  

```java
@Component
public class Person {
    private Address address;
    @Autowired
    @Qualifier("address1")
    public void setAddress(Address address) {
        this.address = address;
    }
}
```
2. **Setter injection**  
Setter-based dependency injection is achieved through the setter method on the bean after instantiating it using a no-arg constructor or no-argument static factory.  

XML  
```xml
<bean id="address" class="com.programmergirl.domain.Address"/>
<bean id="person" class="com.programmergirl.domain.Person">
    <property name="address" ref="address"/>
</bean>
```
```java
@Component
public class Person {
    
    private Address address;
    ...
    @Autowired
    public void setAddress(Address address) {
        this.address = address;
    }
    
}
```
3. **Property-based injection** 
We can also inject dependencies using fields or properties of a class.  
To do so, we can simply use the @Autowired annotation over the field:  

```java
@Component
public class Person {
    @Autowired
    private Address address;
    ...
}
```
**Considering the spring documentation**:  
Use constructor injection for mandatory dependencies (the dependencies they use while working).  
Setter-based injections should be used for optional dependencies (alternative dependencies).  
Avoid property-based injection because Spring uses reflection for field-injected dependencies and it is costlier.  

### **ApplicationContext**  
It is an object that loads the configuration and then Spring will start managing the beans.  
This technique benefits: beans declared in a package, beans declared by annotations, constructor, and method auto writing, bean injection, configuration, .properties, and .yaml file loading, etc.  
There are 3 ways to configure applicationContext: XML based(configuring beans in xml), java based, annotation based( enabling <component-scan> in xml and using annotation in java code).  

The Spring ApplicationContext interface represents its IoC container and has several implementation classes available.  
Some of these include **ClassPathXmlApplicationContext**, **FileSystemXmlApplicationContext**, and **WebApplicationContext**.  

Let’s instantiate the Spring container using ClassPathXmlApplicationContext:  

```java
//The applicationContext.xml is the file that holds the metadata required to assemble beans at runtime.
ApplicationContext appContext = new ClassPathXmlApplicationContext("applicationContext.xml");  
```

**Spring IoC container**: The core of Spring Framework. It creates the objects, configures and assembles their dependencies, manages their entire life cycle.  
The Container uses Dependency Injection(DI) to manage the components that make up the application.  
It gets the information about the objects from a configuration file(XML) or Java Code or Java Annotations and Java POJO class. These objects are called Beans.  

Following is how Spring Core works:  
![image](https://user-images.githubusercontent.com/43732258/153714507-fa2d5bec-fafc-4fdf-9f46-6020688d374f.png)

Once the ApplicationContext is created and initialized, your application classes are combined with the configuration metadata so that you have a fully configured and executable system.  

## **Spring Boot**
Spring Boot is an open-source micro framework that provides Java developers with a platform to get started with an auto configurable production-grade Spring application.  	

### **Spring Boot Architecture**
Spring Boot is a module of the Spring Framework that is developed on top of the core Spring Framework.  
Spring Boot is used to create stand-alone, production-grade Spring Based Applications with minimum efforts.  
Spring Boot follows a layered architecture in which each layer communicates with the layer directly below or above (hierarchical structure) it.  

There are 4 layers in Spring Boot are as follows:  
![image](https://user-images.githubusercontent.com/43732258/153719029-cf9a5f60-7b51-41e2-82b7-7a20390d008e.png)  
o	Presentation Layer (handles the HTTP requests, translates the JSON parameter to object, and authenticates the request and transfer it to the business layer).  
o	Business Layer (authorization and validation, bussiness logic,service classes).  
o	Persistence Layer (storage logic and translates business objects from and to database rows).  
o	Database Layer(CRUD (create, retrieve, update, delete) operations on databases  are performed).  

**Spring Boot Flow Architecture**  
![image](https://user-images.githubusercontent.com/43732258/153742576-aa78883c-4394-4022-a478-fa3b9d2c808e.png)  
The architecture of Spring Boot is the same as the architecture of Spring MVC, except one thing: there is no need for DAO and DAOImpl classes in Spring boot.  
o	Creates a data access layer and performs CRUD operation.  
o	The client makes the HTTP requests (PUT or GET).  
o	The request goes to the controller, and the controller maps that request and handles it. After that, it calls the service logic if required.  
o	In the service layer, all the business logic performs. It performs the logic on the data that is mapped to JPA with model classes.  
o	A JSP page or Spring Data is returned to the user if no error occurred.  
		
**Maven**: Maven is a open-source build tool developed by the Apache Group to build, publish, and deploy several projects at once for better project management.  
Maven adds a pom.xml file to projects files. By using this file you can add or remove .jar files from your project easily.  

**pom.xml(Project Object Model)**: The core business unit in Maven. An XML file containing information about the project and configuration details used by Maven to build the project.  

**DAO(Data Access Object) vs DTO(Data Transfer Object)**:
DTO is a value object that is used to transfer data between classes and modules of your application.  
DAO is used to encapsulate and hide implementation details about how your data is stored and retrievend in your data storage (database or file system).  
	
### **Spring vs Spring Boot**
o	Spring is a Java EE framework that is used to build applications. Spring Boot framework is mainly used to develop REST API’s.  
o	Spring's key feature is dependency injection. Spring Boot's key feature is autoconfiguration.  
o	Spring is used to make Java EE developement easier. Spring Boot provides the RAD(Rapid Application Development) feature to the Spring framework for faster application development.  
o	Spring framework helps to create a loosely coupled application. Spring Boot helps to create a stand-alone application.  
o	In the Spring framework to test the Spring Project, we need to set up the servers explicitly. Spring Boot offers built-in or embedded servers such as Tomcat and jetty.  
o	Spring framework does not support in-memory database. Spring Boot provides support for the in-memory database such as H2.  
o	In the Spring framework, you have to build configurations manually. In Spring Boot there are default configurations that allow faster bootstrapping.  
o	Spring requires a number of dependencies to create a web app. Spring Boot, can get an application working with just one dependency.  
o	Testing Spring Boot is easier than testing Spring because of the reduced amount of source code.    
o	Contrarily of the Spring, XML configuration is not a must in Spring Boot.  
o	Contrarily of the Spring, Spring Boot provides a CLI tool for developing and testing Spring Boot applications.  
o	Contrarily of the Spring, Spring Boot provides build tool plugins for Maven and Gradle.

**Spring Boot Autoconfiguration**: The method of automatically configuring a Spring application based on the dependencies found on the classpath.  
Autoconfiguration can speed up and simplify development by removing the need to define some beans that are part of the auto-configuration classes..  
	
**Spring initialzr**: Spring Initializr is a Web-based tool that generates the Spring Boot project structure.  
The Spring Initializr tool takes care of the following configuration for any Spring-based project:  
o	Build tool(Maven or Gradle) to build the application.  
o	Spring Boot version(Dependencies are added based on the version).  
o	Dependencies required for the project.  
o	Language and its version.  
o	Project Metadata like name, packaging (Jar or War), package name etc.  

**application.properties**: In the resources file there is a file called application.properties for setting application's properties.  
You can set application.title, application.version and server.port inside this file.  
Logging.level.org.springframework = debug gives more logs on higher log level(info is default). By doing that we can see what spring is doing at the background in detail.  
Spring.datasource url, driverclassname,username,password etc. values can be given for databse configuration.  
Also in the resources file, you can create a banner.txt file and add a custom text banner that will be shown when the spring boot application starts.  

### **Spring Annotations**

**Core Spring Annotations**:

**@Component**: Configures the Bean classes.
```java
@Component
public class Company {
    private Address address;
    public Company(Address address) {
        this.address = address;
    }
    // getter, setter and other properties
}
```
The configuration class supplying bean metadata to an IoC container:   
**@ComponentScan**: Instructs the container to look for beans in the package containing the Company class.  
If specific packages are not defined, scanning will occur from the package of the class that declares this annotation.  

```java
@Component
public class BeanA {
  @Autowired
  @Qualifier("beanB2")
  private BeanInterface dependency;
  ...
}
@Configuration
@ComponentScan(basePackageClasses = Company.class)
public class Config {
    @Bean
    public Address getAddress() {
        return new Address("High Street", 1000);
    }
}
```
**@Configuration**: This annotation is used on classes which define beans. It is an alternative for XML configuration file.  

**@Lazy**: Used on Component classes and indicates that the Bean will be created and initialized only when it is first requested for.  
**@Value**: Used at the field or method/constructor parameter level and it indicates a default value for the affected argument.  
Commonly used to inject the configuration values to the spring boot application.  
	
**Stereotype Annotations**: The spring framework provides 3 other specific annotations to be used when marking a class as a Component.  
These specific Component annotations are @Service, @Repository, @Controller.	
**@Service**: Indicates these beans are used in Service layer and holds bussiness logic (utility classes).
**@Repository**: Indicates these beans are used in DAO or Repository and deals with CRUD (create, read, update, delete) operations.
**@Controller**: Indicates these beans are front controllers, used in REST Web Services and responsible to handle user requests.

**@Bean**: It is used at method level and indicates that a method produces a bean to be managed by the Spring container.  
The method annotated with this annotation works as bean ID and it creates and returns the actual bean.  
```java
@Configuration
public class AppConfig{
  @Bean
  public Person person(){
    return new Person(address());
  }
  @Bean
  public Address address(){
    return new Address();
  }
}
```
**@PropertyResources**: Provides property file to Spring Environment.  

**@Autowired**: Used for automatic injection of beans.   
When you use @Autowired on setter methods, Spring tries to perform the by Type autowiring on the method.    
When you use @Autowired on a constructor, constructor injection happens at the time of object creation.  
NOTE: @Autowired is not used anymore, instead use Lombok @AllArgsConstructor on a private final field.  
NOTE: As of Spring 4.3, @Autowired became optional on classes with a single constructor.  
	
**@Qualifier**: Used in conjunction with Autowired to avoid confusion when we have two of more bean configured for same type.  
Consider and example where an interface is implemented by two classes. Now when a bean autowires this interface we need to specify the class as following:  
```java
@Component
public class BeanA {
  @Autowired
  @Qualifier("beanB2")
  private BeanInterface dependency;
  ...
}
```
**@Primary**: Used with @Qualifier and defines the primary preferenced Bean.  
**@Target**: The target annotation indicates the targeted elements( such as field, method, parameter etc.) of a class in which the annotation type will be applicable.  
**@Scope**: Indicates the Bean's runtime context(scope). The default scope is Singleton.  

**Spring Boot Annotations**:
	
**@SpringBootApplication**: Indicates the main method of the Spring Boot project. Marks a configuration class that declares one or more @Bean methods and also triggers auto-configuration and component scanning.  
It combines three annotations like @Configuration, @EnableAutoConfiguration, and @ComponentScan.  
If you use Spring Boot, then you can run your application without deploying it into a web server. 

**@EnableAutoConfiguration**: Enables Spring Boot to auto-configure the application context, so Spring Boot automatically creates and registers beans.  
It was constantly used with @Configuration and @ComponentScan on an earlier version of Spring boot, they have come up with a convenient @SpringBootApplication annotation.  
Though you can still use @EnableAutoConfiguration annotation if you want to customize or have some control over auto-configuration.  
Excluding certain classes from auto-configuration:  
```java
@Configuration
@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})
public class MyConfiguration {
  //.. Java code
}
```

**Spring MVC and REST Annotations**:

**@RequestMapping**: A method and class level annotation that is used to mapping between the request path and the handler method.  
The value attribute of @RequestMapping annotation on class is used to specify the URL pattern.  
The method property of @RequestMapping annotation on method is used to specify the HTTP method.  

```java
@Controller
@RequestMapping("/welcome")
public class WelcomeController{
  @RequestMapping(method = RequestMethod.GET)
  public String welcomeAll(){
    return "welcome all";
  }  
}
```
*Following are @RequestMapping variants to better express the semantics of the annotated methods**
**@GetMapping**: Used for mapping HTTP GET requests onto specific handler methods. Shortcut for @RequestMapping(method = RequestMethod.GET) .  
**@PostMapping**: Used for mapping HTTP POST requests onto specific handler methods. Shortcut for @RequestMapping(method = RequestMethod.POST).  
**@PutMapping**: Used for mapping HTTP PUT requests onto specific handler methods. Shortcut for @RequestMapping(method = RequestMethod.PUT).  
**@PatchMapping**: Used for mapping HTTP PATCH requests onto specific handler methods. Shortcut for @RequestMapping(method = RequestMethod.PATCH).  
**@DeleteMapping**: Used for mapping HTTP DELETE requests onto specific handler methods. Shortcut for @RequestMapping(method = RequestMethod.DELETE).

**@ExceptionHandler**: Used at method levels to handle exception at the controller level.  
**@InitBinder**: Method-level annotation that plays the role of identifying the methods which initialize the WebDataBinder (a DataBinder that binds the request parameter to JavaBean objects).  
**@PathVariable**: Used to retrieve data from the URL. Unlike @RequestParam, this annotation enables the controller to handle a request for parameterized URLs (e.g http://localhost:8080/books/900083838).  
Retrieving ISBN number "900083838" from the URL as a method argument:  

```java
@RequestMapping(value="/books/{ISBN}",
                        method= RequestMethod.GET)
//or @GetMapping("/books/{ISBN}")
public String showBookDetails(@PathVariable("ISBN") String id, Model model){
   model.addAttribute("ISBN", id);
   return "bookDetails";
}
```
	
**@RequestAttribute**: Used to bind the request attribute to a handler method parameter.  
**@RequestBody**:  Used to convert inbound HTTP data into Java objects passed into the controller's handler method.  
```java
@RequestMapping(method=RequestMethod.POST, consumers= "application/json")
public @ResponseBody Course saveCourse(@RequestBody Course aCourse){
   return courseRepository.save(aCourse);
}
```
**@RequestHeader**: Used to map controller parameter to request header value.  
When Spring maps the request, @RequestHeader checks the header with the name specified within the annotation and binds its value to the handler method parameter.  
This annotation helps you to get the header details within the controller class.  

**@RequestParam**: Sometimes you get the parameters in the request URL, mostly in GET requests.  
In that case, along with the @RequestMapping annotation you can use the @RequestParam annotation to retrieve the URL parameter and map it to the method argument.  
The @RequestParam annotation is used to bind request parameters to a method parameter in your controller.  
```java
@GetMapping("/sayHello")
public String sayHelloWithParams(@RequestParam String name, String surname){
	return "Hello "+name+" "+surname;
}
```
Now when you reach "localhost:8080/sayHello?name=bahadir&surname=memis" URL from your browser you will find a page with "Hello bahadir memis" output.
**@RequestPart**: Can be used instead of @RequestParam to get the content of a specific multipart and bind to the method argument annotated with @RequestPart.  
This annotation takes into consideration the “Content-Type” header in the multipart(request part).  

**@ResponseBody**: Used to transform a Java object returned from he a controller to a resource representation requested by a REST client.  

**@ResponseStatus**: Used on methods and exception classes.  
@ResponseStatus marks a method or exception class with a status code and a reason that must be returned.  
When the handler method is invoked the status code is set to the HTTP response which overrides the status information provided by any other means.  
A controller class can also be annotated with @ResponseStatus which is then inherited by all @RequestMapping methods.  

**@ControllerAdvice**: This annotation is used to define @ExceptionHandler, @InitBinder and @ModelAttribute methods that apply to all @RequestMapping methods.  
Thus if you define the @ExceptionHandler annotation on a method in @ControllerAdvice class, it will be applied to all the controllers.  

**@RestController**: The combination of @Controller and @ResponseBody.  
```java
@RestController
class HelloControler{
@RequestMapping("/")
public String hello(){
  return "Hello Spring Booot";
}
}
```
**@RestControllerAdvice**: Used at class level, and combines @ControllerAdvice and @ResponseBody.  
**@SessionAttributes**: Used at parameter level, and provides a convenient access to the existing or permanent session attributes.  

**@CookieValue**: Method level annotation that is used in the method annotated with @RequestMapping as argument of request mapping method.  
```java
@RequestMapping("/cookieValue")
  public void getCookieValue(@CookieValue "JSESSIONID" String cookie){ //JSESSIONID=418AB76CD83EF94U85YD34W
}
```
**@CrossOrigin**: Class and method level annotation to enable cross origin requests.  
In many cases the host that serves JavaScript will be different from the host that serves the data.  
In such a case Cross Origin Resource Sharing (CORS) enables cross-domain communication.  
```java
@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping("/account")
public class AccountController {
@CrossOrigin(origins = "http://example.com")
@RequestMapping("/message")
  public Message getMessage() {
      // ...
    }
 
@RequestMapping("/note")
    public Note getNote() {
        // ...
    }
}
```
In this example, both getExample() and getNote() methods will have a maxAge of 3600 seconds.
Also, getExample() will only allow cross-origin requests from "http://example.com", while getNote() will allow cross-origin requests from all hosts.  
	
### **Lombok**
Lombok is a java library tool that is used to minimize/remove the boilerplate code and save time to developers during development by increasing the readability of the source code and saving space with using some annotations.   
Lombok works by plugging into our build process and auto-generating Java bytecode into our .class files.  

```java
@Getter @Setter @NoArgsConstructor // Adding getters, setters and no-argument constructor
public class User implements Serializable {

    private Long id;
	
    @NonNull  
    private String firstName;
    @NonNull  
    private String lastName;
    @NonNull  
    private int age;

    public User(String firstName, String lastName, int age) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
    }
}
```

**@Getter**: Used at field or class level to generate getters for private fields.  
**@Setter**: Used at field or class level to generate setters for private fields.  

**@NoArgsConstructor**: Generates a constructor with no parameters.  
**@AllArgsConstructor**: Generates a constructor with all fields as parameters.  
**@RequiredArgsConstructor**: Generates a constructor with required parameters (final fields). 
Does not generate any argument for non-final, initialized final, static, initialized non-null fields.  

**@toString**:	Generates a toString() method including all class attributes. Updates itself as we enrich our data model.  
**@EqualsHashCode**: Generates both equals() and hashCode() methods by default considering all relevant fields.  

**@Data**: Sets @Getter, @Setter, @RequiredArgsConstructor, @toString, @equalsHashcode.  
**Configuring @Data by excluding, example**:  

```java
@Data
public class User4 {
  @Setter(value = AccessLevel.NONE)
  private Long id;
  @Getter(value = AccessLevel.NONE)
  private String username;
  
  @ToString.Exclude
  private boolean active;
  
  @EqualsAndHashCode.Exclude
  @ToString.Exclude
  private int role;
}
```
**@Value**: Used when creating Immutable classes.
Sets @Getter, @FieldDefaults(makeFinal=true, level=AccessLevel.PRIVATE), @AllArgsConstructor, @ToString, and @EqualsAndHashCode.  

**@FieldDefaults**: Can add an access modifier(public, private, protected) and "final" to each field.  

**@Builder**: Produces complex builder APIs for your classes.  
If you are using @Data and @Builder annotations together, all-args constructor (Package access level) is generated.  
The difference between Builder and Factory pattern is that the Builder pattern is only required when an object cannot be produced in one step. After all of the steps are done, the builder creates the instance.  

```java
@Getter
@Builder
public class LombokBuilderDemo1 {
  private Long id;
  private String name;
}
public class LombokBuilderDemo1Test {
  public static void main(String[] args) {
    LombokBuilderDemo1 lbd1 = LombokBuilderDemo1.builder()
        .name("Peter")
        .id(Long.valueOf(1))
        .build(); //Does not create instance until this line
    System.out.println("id => "+lbd1.getId());
    System.out.println("name => "+lbd1.getName());
  }
}
```
If not all but only few of the fields are required, then declare @Builder annotation at constructor level which is having required arguments.  
```java
@Getter
public class LombokBuilderDemo2 {
  private Long id;
  private String name;
  private boolean status;
  private int role;
  @Builder()
  public LombokBuilderDemo2(Long id, int role) {
    this.id = id;
    this.role = role;
  }
}
```
If your class already have a method to create instance, you can make it as builder by declaring @Builder on that method.  
```java
@Getter
public class LombokBuilderDemo3 {
  private Long id;
  private String name;
  private boolean status;
  private int role;
  public LombokBuilderDemo3(Long id, String name, int role, boolean status) {
    this.id = id;
    this.name = name;
  }
  @Builder(builderMethodName = "builder")
  public static LombokBuilderDemo3 createInstance(Long id, String name) {
    // create instance with default role and status
    return new LombokBuilderDemo3(id, name, 1, false);
  }
}
```

Notes: The elimination of boilerplate code can also be done by implementing kotlin data classes into java projects.  
Java Records(java v14) are java-based solution alternative to Lombok that is expected to replace Lombok in the future.  
In Intellij IDEA, we can see Lombok implemented java methods from structure section.  

### **Hibernate**
**Object-Relational Model(ORM)**: A programming technique for converting data between incompatible type systems using object-oriented programming languages.  
Hibernate ORM (or simply Hibernate) is an object–relational mapping tool for the Java programming language.  
A class is converted to database tables. In this conversion process, we define entity properties with annotations.  

**@Entity**: Indicates that the POJO has an equivalent table in the database.  
```java
@Entity
public class Student{
}
```
**@Table**: Indicates the name of the table in the database that POJO is equivalent.   
When not specified, a table is searched by the name of the class.  
Has name, catalog, schema, uniqueConstraints, indexes properties.  
```java
@Entity
@Tablename(name="Student")
public class Student{
}
```
**@Id**: It indicates the primary key in the table.  
Each entity must have one @Id.  
```java
@Entity
@Tablename(name="Student")
public class Student{
	@Id
	private Long id;
}
```
**@GeneratedValue**: Provides for the specification of generation strategies for the values.  
Has strategy and generator properties. 
Can be used together with SequenceGenerator and TableGenerator.  
There are 4 strategy properties: AUTO, IDENTITY, SEQUENCE, TABLE.  
```java
@Entity
@Tablename(name="Student")
public class Student{
	@Id
	@GeneratedValue(
		strategy = GeenerationType.AUTO
	)
	private Long id;
}
```	
**@SequenceGenerator**: Defines a primary key generator that may be referenced by name when a generator element is specified for the GeneratedValue annotation.  
**Sequence**: Database object that generates incremental integers on each successive request.  
Sequences are more flexible than identifier columns because sequences are table-free, they may preallocate values to improve performance and same sequence can be assigned to multiple columns or tables.  
	
```java
@Entity
@Tablename(name="Student")
public class Student{
	@SequenceGenerator(
		name="generator",
		sequenceName="POJO_ID_SEQ",
		allocationSize=1
	)
	@Id
	@GeneratedValue(
		generator="generator",
		strategy = GeenerationType.SEQUENCE
	)
	private Long id;
}
```

**@TableGenerator**: Defines a primary key generator that may be referenced by name when a generator element is specified for the GeneratedValue annotation.  
"name" is the name of the generator, table is the name of the table.  
"pkColumnName" hold primary key column's name, valueColumnName holds last given id.  
"allocationSize" is the increment value and it is default 50.  
"initialValue" is the initial value and it is default 0.  

```java
@Entity
@Tablename(name="Student")
public class Student{
	@TableGenerator(
		name="generator",
		table="POJO_SEQ_TABLE",
		pkColumnName="PK_NAME",
		valueColumnName="PK_VALUE",
		allocationSize=1
	)
	@Id
	@GeneratedValue(
		generator="generator",
		strategy = GeenerationType.SEQUENCE
	)
	private Long id;
}
```
**@Column**: Used to specify the details of the column to which a field or property will be mapped.  
For enum values use @Enumerated(EnumType.STRING) so if someone changes your enum order, your application still works.  
For precision values: @Column(precision=15, scale =2), (scale shows value after comma).    
```java
@Entity
@Tablename(name="Student")
public class Student{
	@Id
	@GeneratedValue(
		generator="generator",
		strategy = GeenerationType.SEQUENCE
	)
	private Long id;
	
	@Column(name="ID_LSN_LESSON") //ForeignKey
	private Long lesonId;
	
	@Column(name="VALUE", precision=15, scale=2)
	private BigDecimal value;
	@Column(length=100)
	private String name;
	@Column(name="SAVER_NAME",updatable=false)
	private String saver;
	@Column(name="UPDATER_NAME",insertable=false)
	private String updater;
}
```
If Updatable is false, that field will not be written in the update query when committing to the database.  
If insertable is false, that field will not be written in the insert query when committing to the database.  

**@Version**: Used for Optimistic locking while performing update operation. Indicates the table's version.  
```java
@Entity
@Tablename(name="Student")
public class Student{
	@Id
	@GeneratedValue(
		strategy = GeenerationType.AUTO
	)
	@Column
	private Long id;
	
	@Version
	private Long version;
}
```

**@Temporal**: Indicates in which format the date will be written to the database.  
Temporal has 3 properties: DATE(dd.mm.yyyy), TIME(hh:mm:ss), DATETIME(dd.mm.yyyy hh:mm:ss).

```java
@Entity
@Tablename(name="Student")
public class Student{
	@Id
	@GeneratedValue(
		strategy = GeenerationType.AUTO
	)
	@Column
	private Long id;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date date;
}
```

**@Transient**: It is used in cases where we want a field to be in the pojo but not in the database.  

```java
@Entity
@Tablename(name="Student")
public class Student{
	@Id
	@GeneratedValue(
		strategy = GeenerationType.AUTO
	)
	@Column
	private Long id;
	
	@Transient
	private Boolean isValid;
}
```

**@Lob**: Lob stands for large object. Used with byte large objects and char large objects.  

```java
@Entity
@Tablename(name="Student")
public class Student{
	@Id
	@GeneratedValue(
		strategy = GeenerationType.AUTO
	)
	@Column
	private Long id;
	
	@Lob
	private byte[] blobFile;
	
	@Lob
	private char[] clobFile;
	
	@Lob
	private Stirng text;
}
```

**@OneToOne**: It is used if there is a one-to-one relationship with the joined table. As an example Humans have unique DNA's so Human-DNA tables have one-to-one relationship.  
```java
@Entity
@Tablename(name="Human")
public class Human{
	@SequenceGenerator(name="human",sequenceName="HUMAN_ID_SEQ")
	
	@Id
	@GeneratedValue(generator="human",
		strategy = GeenerationType.SEQUENCE)
	
	@Column(name="ID")
	private Long id;
	
	@Column(name="NAME",length=100)
	private String name;
	
	@OneToOne(
		cascade=CascadeType.ALL,
		fetch= FetchType.LAZY,
		mappedBy="human",
		optinal=false)
	private Dna dna;
}
```
```java
@Entity
@Tablename(name="DNA")
public class Dna implements Serializable{
	@SequenceGenerator(name="dna",sequenceName="DNA_ID_SEQ")
	
	@Id
	@GeneratedValue(generator="dna",
		strategy = GeenerationType.SEQUENCE)
	
	@Column(name="ID")
	private Long id;
	
	@Column(length=10)
	private String fileId;
	
	@OneToOne(
		cascade=CascadeType.ALL,
		fetch= FetchType.LAZY,
		orphanRemoval=true)
	private Human human;
}
```
**@ManyToMany**: It is used if there is a many-to-many relationship with the joined table. As an example a book can have many writers, and a writer can have multiple books.  
```java
@Entity
@Tablename(name="BOOK")
public class Book{
	@SequenceGenerator(name="book",sequenceName="BOOK_ID_SEQ")
	@Id
	@GeneratedValue(generator="book",
		strategy = GeenerationType.SEQUENCE)
	
	@Column(name="ID")
	private Long id;
	
	@Column(name="NAME",length=100)
	private String name;
	
	@ManyToMany(
		cascade=CascadeType.ALL,
		targetEntity=Writer.class,
		fetch= FetchType.LAZY)
	private Set writers = new HashSet();
}	
```
```java
@Entity
@Tablename(name="WRITER")
public class Writer{
	@SequenceGenerator(name="writer",sequenceName="WRITER_ID_SEQ")
	@Id
	@GeneratedValue(generator="writer",
		strategy = GeenerationType.SEQUENCE)
	
	@Column(name="ID")
	private Long id;
	
	@Column(name="NAME",length=100)
	private String name;
	
	@ManyToMany(
		fetch= FetchType.LAZY,
		mappedBy="writers")
	private Set writers = new HashSet();
}	
```
**@ManyToOne**: It is used if there is a many-to-one relationship with the joined table. The most popular relationship. As an example a country has multiple cities, and a city have one country.    
```java
@Entity
@Tablename(name="CITY")
public class City{
	@SequenceGenerator(name="city",sequenceName="CITY_ID_SEQ")
	
	@Id
	@GeneratedValue(generator="city",
		strategy = GeenerationType.SEQUENCE)
	
	@Column(name="ID")
	private Long id;
	
	@Column(name="NAME",length=50)
	private String name;
	
	@ManyToOne(
		fetch= FetchType.LAZY,
		cascade=CascadeType.ALL,
		optional=false)
	private Country country;
}
```
**@OneToMany**: It is used if there is a one-to-many relationship with the joined table. With mappedBy, it is possible to add to the entity without creating a column in a bidirectional relationship. It can be used when joining the Cities list within the Country object. 
```java
@Entity
@Tablename(name="COUNTRY")
public class Country{
	@SequenceGenerator(name="country",sequenceName="COUNTRY_ID_SEQ")
	
	@Id
	@GeneratedValue(generator="city",
		strategy = GeenerationType.SEQUENCE)
	
	@Column(name="ID")
	private Long id;
	
	@Column(name="NAME",length=50)
	private String name;
	
	@OneToMany(
		fetch= FetchType.LAZY,
		mappedBy="country",
		targetEntity=City.class,
		orphanRemoval = true)
	private Set country = new HashSet();
}
```
**@JoinColumn**: It is used when an entity is defined in the Entity.  
It helps us to specify the properties of the relation column to be created.  
```java
@Entity
@Tablename(name="CITY")
public class City{
	@SequenceGenerator(name="city",sequenceName="CITY_ID_SEQ")
	
	@Id
	@GeneratedValue(generator="city",
		strategy = GeenerationType.SEQUENCE)
	
	@Column(name="ID")
	private Long id;
	
	@Column(name="NAME",length=50)
	private String name;
	
	@ManyToOne(
		fetch= FetchType.LAZY,
		cascade=CascadeType.ALL,
		optional=false)
	
	@JoinColumn(
		name="ID_COUNTRY",
		nullable=false,
	)
	
	private Country country;
}
```
**@ForeignKey**(Deprecated): It used to be used to add and customize a foreign key to the table, it is now deprecated.  
**@Index**(Deprecated):  It used to be used to add index to the table, it is now deprecated. The same index could be used for more than one column.  
	
**H2 Database**: H2 is a lightweight relational database management system written in Java that can be embedded in Java applications or run in client-server mode.  
To use H2, add dependency to pom.xml file than change application.properties file.  
Later, arrying out CRUD operations with H2 within Spring Boot is the same as with other SQL databases.  
To access H2 console, add "spring.h2.console.enabled=true" to application.properties.  
Then go to "http://localhost:8080/h2-console" url which is the login page.  
After we enter our credentials, a comprehensive H2 console webpage will welcome us.  

### **HTTP Methods and Messages**

**HTTP**: Hyper Text Transfer Protocol is used for fetching resources such as HTML documents.  

**HTTP Methods**:  
**GET**: Requests a resource from the server.  
**HEAD**: Requests only headers of resources from server.  
**POST**: Changes the content of the resource located on the server.  
**PUT**: Requests the server to create a resource and replace that resource with another resource.  
**DELETE**: Requests the server to delete a resource.  
**CONNECT**: Requests to start two-way communications with the requested HTTPS resource.  
**OPTIONS**: Requests permitted communication options for a given URL or server.  
**TRACE**:  Used for debugging which echo's back input back to the user.  

**Error messages**:  
**1xx**: Informative messages.  
**2xx**: Success messages.  
**3xx**: Redirecting messages.  
**4xx**: Request error messages.  
**5xx**: Server error messages.  

**Swagger**: Set of open-source tools that can help you design, build, document and consume REST APIs.  
**REST API**: An API that conforms to the design principles of the REST, or representational state transfer architectural style.  
**API(Applicaiton Programming Interface)**: Set of rules that define how applications or devices can connect to and communicate with each other.  
**REST (Representational State Transfer)**: Roy Fielding's derivation of the Web's architectural style.  
In a RESTful Web service, requests to a resource's URI are answered with data in the form of HTML, XML, JSON, or some other format.  
**URI( Uniform Resource Identifier)**: A syntax that assigns each web document a unique address.  
**Endpoint**: The name given to your method created for a specific purpose on the API.  
	
![image](https://user-images.githubusercontent.com/43732258/153911790-ba6c772f-e541-4ba3-befe-94ad9a9dee9d.png)  
	
HTTP requests has Request-Line, Header, and Body.
**Request-Line**: The type of HTTP request, the URL to which it will be made, and the HTTP/HTTPS protocol information.  
*Header**: Parameters that describe the request and contain basic information about the request. We can send zero or more header parameters.  
A few of the standard parameters used for Header are Culture (language), Content-Type, Authentication.  
With the authentication parameter, the key information (token) is transmitted to the server and only authorized people can access the API.  
**Body**: You need to send information in the body field of a request when using POST, PUT, PATCH operations. Sending the information as JSON in a Restful API is preferable.  

	
**Designing REST APIs**:
Decide the 3-letter abbreviations of the tables and attributes inside them (also gen for general classes). Packages are created with the same name.   
Inside each of these 3-letter abbrevation named packages, create dao, dto, entity, service( and entityservice in service), controller, converter, enums packages.  
"The URL is a sentence, where resources(controllers and classes) are nouns and HTTP methods are verbs."  
"You will not have a problem with URLs that are shorter than 2000 characters, but you may not be able to run URLs over 2000 characters on all devices and browsers."  	
	
In controller, there can be only 1 HTTP method type (get,put,delete etc.) method in a path.  

A good practice example:
```java
@RestController
@RequestMapping("/api/v1/tests") //Class level @RequestMapping
public class TestController{
	
@GetMapping("/sayHello") //URL: "localhost:8080/api/v1/tests/sayHello"
public String sayHello(){
	return "Hello";
  }

}
```
Printing inside methods, prints to the console and can used for debugging. You need to return values to use http protocols.  

**Java Reflection**:  A feature that provides the opportunity to examine and direct the runtime behavior of applications running in the JVM.  
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


**Java Persistence API(JPA)**: JPA is a collection of classes and methods powered by Oracle that permanently stores large amounts of data in a database.  
JPA creates a bridge between the relational model (database model) and the object model (Java object) and the load is significantly reduced.  
**JPA Repository**: A specific extension of Repository.  
It contains the full API of CrudRepository and PagingAndSortingRepository.  
It contains API for basic CRUD operations and also API for pagination and sorting.  


**MapStruct**: A Java Bean mapper that contains functions that automatically map between two Java Beans.  
With MapStruct, we only need to create the interface, and the library will automatically create a concrete implementation during compile time.  

Let's say we have 2 Pojo's called Source and Destination.  
We define the mapper interface:  
```java
@Mapper
public interface SourceDestinationMapper {
    SimpleDestination sourceToDestination(Source source);
    SimpleSource destinationToSource(Destination destination);
}
```
We do not have to create an implementation class for our SourceDestinationMapper — because MapStruct creates it for us.  
MapStruct gets triggered when project run (more exactly, when mvn install command runned).  
Another example:  
```java
//POJOs
public class EmployeeDTO {
    private int employeeId;
    private String employeeName;
    // getters and setters
}
public class Employee {
    private int id;
    private String name;
    // getters and setters
}
```

```java
@Mapper
public interface EmployeeMapper {
    @Mappings({
      @Mapping(target="employeeId", source="entity.id"),
      @Mapping(target="employeeName", source="entity.name")
    })
    EmployeeDTO employeeToEmployeeDTO(Employee entity);
    @Mappings({
      @Mapping(target="id", source="dto.employeeId"),
      @Mapping(target="name", source="dto.employeeName")
    })
    Employee employeeDTOtoEmployee(EmployeeDTO dto);
}
```

**ResponseEntity**: Represents the whole HTTP response (status code, headers, and body), as a result, we can use it to fully configure a HTTP response.  
We just have to return ResponseEntity from the endpoint; Spring takes care of the rest.  
ResponseEntity is a generic type therefore, we can use any type as the response body. 
Example:  
```java
 @GetMapping("/{id}")
    public ResponseEntity findById(@PathVariable Long id){
        AddAddressDto addAddressDto =addAddressService.findById(id);
        return ResponseEntity.ok(addAddressDto);
    }
    @PostMapping
    public ResponseEntity save(@RequestBody AddAddressSaveRequestDto addAddressSaveRequestDto){
        AddAddressDto addAddressDto = addAddressService.save(addAddressSaveRequestDto);
        return ResponseEntity.ok(addAddressDto);
    }
```

**Optional**
A container object which may or may not contain a non-null value.  
Optional object is used to represent null with absent value.  
Great way of returning values.  
It has isPresence() method for checking null value.  
Also you can get entities by get() method.  
 

**How a modern Spring Boot application work?**  

![Spring Boot](https://user-images.githubusercontent.com/43732258/155486794-d198375a-0f8e-48eb-9b62-d98c2dfa7906.jpg)  

**Entity Layer**
Entities are the classes in which we specify the tables in the database and the columns in them.  
We have Entity/model pojo classes. They map our database tables, the fields in them show the columns of the tables.  
In addition, there are fields that show the relations of these tables with each other. 
```java
@Entity //Indicate this is an entity (Spring)
@Table(name = "ADDRESS") //Table's name (Hibernate)
@Getter @Setter//getters, and setters of all fields (Lombok)
public class Address {

    @Id //Show this is an Id (Hibernate)
    @SequenceGenerator(name = "Address" , sequenceName = "ADDRESS_ID_SEQ") //@GeneratedValue's properties (Hibernate)
    @GeneratedValue(generator = "Address") //Automatic increase id (Hibernate)
    private long id;

    @Column(name = "ADDRESS_NUMBER",nullable = false) //Column's name and properties (Hibernate)
    private int addressNumber;

    @Column(name = "ADDRESS_NAME",nullable = false)
    private int addressName;

    @ManyToOne //Relationship between tables (Hibernate)
    private Country country;
}
```
**Repository Layer**

Repository/dao allows us to pull data from the tables we created in the database and move it to the service layer.  
JpaRepository is a library that defines common repository methods for us.  
We give entity and it's id value to JpaRepository to create methods for us.  

```java
@Repository
public interface AddressDao extends JpaRepository<Address, Long> { //Looks findById in JpaRepository methods
}

```
This part works like magic. It suggest you method names and by those names it creates your method for you.  
Country getByCityId(String cityId); //gets a country by countryId
List<Country> getAllByCityId(String cityId); //gets a all values by cityId's (understands behaviour just by given name)
If your operation is complex, you can also write your own query.  

**DTO**
When transferring entities between layers, the entities themselves are not used, instead data transfer objects(Dto) are used.  
We can ensure that the features we do not want are not carried in these DTOs (for example, id or password).  
```java
@Data
public class AddressDto {
    private int doorNumber;
    private int apartmentNumber;
    private CountryDto country;
}
```
**Converter**
With these DTOs, we also perform the transformations between entities and dtos in our converter package.  
Since the process of transforming each field is very repetitive, we create the mapper interface using the mapstruct package and these operations are done for us.  

```java
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AddressMapper {

    AddressMapper INSTANCE = Mappers.getMapper(AddressMapper.class);

    AddressDto convertToAddressDto(Address address);
    List<AddressDto> convertToAddressDtoList(List<Address> addresses);
    Address convertToAddress(AddressSaveRequestDto addressSaveRequestDto);
}
```

**Service Layer**  
Thanks to the JpaRepository in the Service/Bussiness layer, we pass the methods we receive to the Controller layer, after processing them.
   
BaseEntityService is written in the gen(general) package so that entityservice operations are not repeated in the package reserved for each table.  
```java
@Service
@RequiredArgsConstructor
public class BaseEntityService<E, D extends JpaRepository<E, Long>> {

    private final D dao;

    public Optional<E> findById(Long id){ 
        return dao.findById(id); //Return findById for the given dao (AddressDao)
    }
}
```
In professional projects, a separate layer called EntityService is used for the entities taken from the Repository and EntityService layer communicates with the Service layer.  
```java
@Service
public class AddAddressEntityService extends BaseEntityService<AddAddress, AddAddressDao> {
    public AddAddressEntityService(AddAddressDao addAddressDao) {
        super(addAddressDao); //Calls BaseEntityService's constructor
    }
}
```

In the service layer, the business rules are applied.  

```java
@Service
@RequiredArgsConstructor
public class AddAddressService {

    private final AddAddressEntityService addAddressEntityService;

    public AddAddressDto findById(Long id) {
        AddAddress addAddress = addAddressEntityService.getByIdWithControl(id); //This is an entity operation  so calls entityservice's method
        return AddAddressMapper.INSTANCE.convertToAddAddressDto(addAddress);
    }
} 
```

**Controller Layer**  
In this layer, our methods are transmitted to the frontend via http protocols as Rest API.  
```java
@RestController //Indicates this is a controller (Spring)
@RequestMapping("/api/v1/addresses") //Base path
@RequiredArgsConstructor //Constructor injection (Lombok)
public class AddAddressController {

    private final AddAddressService addAddressService; //The layer that communicates with the controller is injected

    @GetMapping("/{id}") //GET operation with path with id
    public ResponseEntity findById(@PathVariable Long id){ //Gets that id in path as a parameter
        AddAddressDto addAddressDto =addAddressService.findById(id);
        return ResponseEntity.ok(addAddressDto); //Sends http response OK
    }
```



**References**:  
Sadık Bahadır Memiş    
https://www.programmergirl.com/spring-dependency-injection/  
https://springframework.guru/spring-framework-annotations/  
https://www.baeldung.com/spring-boot-h2-database  
https://www.baeldung.com/intro-to-project-lombok  
https://javabydeveloper.com/lombok-requiredargsconstructor-examples/  
https://javabydeveloper.com/lombok-builder-examples/  
https://www.yusufsezer.com.tr/java-reflection/  
https://springframework.guru/spring-framework-annotations/  
https://www.java67.com/2019/04/top-10-spring-mvc-and-rest-annotations-examples-java.html  
https://www.baeldung.com/mapstruct  
https://www.baeldung.com/spring-response-entity  
