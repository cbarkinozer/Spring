
# Spring Boot Test Framework

# Unit Tests

# Integration Tests

What is testing Advantages Disadvantages
Unit Testing
Bağımsız çalışan enjeksyonu olmayan katmanların testidir. Util sınıfları gibi bağımlılığı olmayan sınıflara yapılır.
Mocking
Imitating injected layers to those classes
Spying
It is partial Mocking

**F.I.R.S.T Princple**
**Fast**: Tests should run and show you the desired output in a matter of seconds.  
**Isolated/Independent**: Tests should be independent of everything else should so that it results is not influenced by any other factor..  
**Repeatable**: Tests should be repeatable and deterministic(values shouldn’t change).  
**Self-validating**: You shouldn’t need to check manually.  
**Thorough**: Should cover every cases.

**Cover these cases in this order: Happy case, all edges(e.g 29 Feb for dates), illegal arguments and variables(e.g null), security(and other issues), large values, all use case scenarios.**

**3 A's of testing**:
**Arrange(Given)**: All the data should be provided to the test and the test should not depend on the environment.  
**Act(When)**: Invoke the actual method under test.  
**Assert(Then)**: A unit test should only assert one logical outcome.  

**Preferably, don’t do any actions after the assert call.**

**Note**: Do net test the code that you did not write such as Repositories or packages you are using.  
Also do not test everything and too small parts. Do not forget tests need to get you time in the long run.  


**@Test**  
**@BeforeAll**
**@BeforeEach**
**@AfterAll**
**@AfterEach**


## Implementation
Add following dependency to the pom.xml file:

```java
<dependency>
<groupId>org.springframework.boot</groupId>
<artifactId>spring-boot-starter-test</artifactId>
<scope>test</scope>
</dependency>
```

In Spring Boot project there is a test file where we add our tests inside.  
Put your index on the class name you want to test and press Alt + Enter.  
Create Test comes up. By doing that you can boot your test.  


## References  
https://www.baeldung.com/spring-boot-testing  
https://www.baeldung.com/mockito-annotations  
https://glowing-crest-6d5.notion.site/Softtech-Java-Spring-Bootcamp-191efcce77654cd493643314176e4957  
https://medium.com/@tasdikrahman/f-i-r-s-t-principles-of-testing-1a497acda8d6  
