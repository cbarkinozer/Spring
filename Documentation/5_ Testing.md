
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

**3 A's of testing**  
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

For example we have a method "LocalDate convertToLocalDate (Date date);".  
This method gets Date and converts it to LocalDate.  
To test this you can write following:  

First testing the happy path:
```java
@Test
    void shouldConvertToLocalDate() throws ParseException {

        Date date = formatterDate.parse("05-10-1991");

        LocalDate localDate = DateUtil.convertToLocalDate(date);

        assertEquals(5, localDate.getDayOfMonth());
        assertEquals(10, localDate.getMonthValue());
        assertEquals(1991, localDate.getYear());
    }
```
Now testing null pointer exception :
```java
@Test
void shouldNotConvertToLocalDateWhenParameterIsNull(){
    assertThrows(GenBussinessException.class,()->DateUtil.convertToLocalDate(null)); // When Date is null, it should throw GenBussinessException. 
    org.assertj.core.api.Assertions.assertThat(genBusinessException.getBaseErrorMessage()).isEqualTo(GenErrorMessage.DATE_COULD_NOT_BE_CONVERTED);
    //AssertJ also can check if result is the wanted error message.  
}
```
 Now testing all edges:
 ```java
   @Test
    void shouldConvertToLocalDateWhen29Feb() throws ParseException {

        Date date = formatterDate.parse("29-02-2016");

        LocalDate localDate = DateUtil.convertToLocalDate(date);

        assertEquals(29, localDate.getDayOfMonth());
        assertEquals(02, localDate.getMonthValue());
        assertEquals(2016, localDate.getYear());
    }
     @Test
    void shouldConvertToLocalDateTime() throws ParseException {

        Date date = formatterDateTime.parse("05-10-1991 10:11:12");

        LocalDateTime localDateTime = DateUtil.convertToLocalDateTime(date);

        assertEquals(5, localDateTime.getDayOfMonth());
        assertEquals(10, localDateTime.getMonthValue());
        assertEquals(1991, localDateTime.getYear());
        assertEquals(10, localDateTime.getHour());
        assertEquals(11, localDateTime.getMinute());
        assertEquals(12, localDateTime.getSecond());
    }
    //Goes like that...
    
 ```


## References  
https://www.baeldung.com/spring-boot-testing  
https://www.baeldung.com/mockito-annotations  
https://glowing-crest-6d5.notion.site/Softtech-Java-Spring-Bootcamp-191efcce77654cd493643314176e4957  
https://medium.com/@tasdikrahman/f-i-r-s-t-principles-of-testing-1a497acda8d6  
https://dzone.com/articles/7-popular-unit-test-naming  
