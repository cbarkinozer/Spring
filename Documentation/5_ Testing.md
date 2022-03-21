### Software Testing
Software Testing is a method to check whether the actual software product matches expected requirements and to ensure that software product is Defect free.  
The purpose of software testing is to identify errors, gaps or missing requirements in contrast to actual requirements.  

### Advantages of Software Testing  

**Spot ambiguities early**  
When you write test you take the perspective of the one that will consume your code so requirements become obvious.    

**Design Better**
Writing tests force you to write code that is loosly coupled and better.  

**Easier debugging**  
Debugging takes time, testing save you lots of time.  

**Up to Date Code Documentation**  
Tests are great documentations and progress measuring tools because 
- Tests are written at development time, they capture the initial intention that is often somewhat blurred by implementation details.
- Tests must necessarily be kept up-to-date with code modifications. Else they cannot pass.
- A test is a small program that exercise step-by-step a scenario.

**Software requirements evolve with time.**   
Code evolve with time and sometimes design decisions taking at the start cause regression in the project with time.  
For example project starts to work slower and test time shows this a lot earlier.  
Or tests make you realise defects caused by previous decisions.  

**Refactor and release with confidence**   
You can freely modify your code and release it with confidence in the quality of your project.

**Save time and enjoy**   
Tests cause less frictions with users and the management because new requirements can be implemented more quickly.  
Moreover it is so much more pleasant to work in a well tested code base.  

### When to Not Do Software Testing
There is no disadvantage of software testing when done correct.  
To do it correctly you need to know when to not test.  
Do not test the code that you did not write such as Repositories or packages you are using.  
Also do not test everything and the parts that are too small.  
Do not forget, tests need to get you time in the long run.  

### F.I.R.S.T Princple  
**Fast**: Tests should run and show you the desired output in a matter of seconds.  
**Isolated/Independent**: Tests should be independent of everything else should so that it results is not influenced by any other factor..  
**Repeatable**: Tests should be repeatable and deterministic(values shouldn’t change).  
**Self-validating**: You shouldn’t need to check manually.  
**Thorough**: Should cover every cases.

**Cover these cases in this order: Happy case, all edges(e.g 29 Feb for dates), illegal arguments and variables(e.g null), security(and other issues), large values, all use case scenarios.**

### 3 A's of testing  
**Arrange(Given)**: All the data should be provided to the test and the test should not depend on the environment.  
**Act(When)**: Invoke the actual method under test.  
**Assert(Then)**: A unit test should only assert one logical outcome.  

**Preferably, don’t do any actions after the assert call.** 

**Unit Testing**
A type of software testing where individual units or components of a software are tested.  
The purpose is to validate that each unit of the software code performs as expected.  
What is a Unit is controversial (class or method).  
A unit is a part that is independent of other structures.  
For example methods of the utility class are unit tested.  

### Integration Testing
Integration tests focus on integrating different layers of the application.  
Individual software modules are combined and tested as a group for this purpose.  
And integration testing does not include mocking.   
If every dependency of a unit under test has been mocked, there is no integration with any external unit, hence the test is not an integration test.  

### Mocking
An object under test may have dependencies on other (complex) objects.  
To isolate the behavior of the object you want to replace the other objects by mocks that simulate the behavior of the real objects.  
This is useful if the real objects are impractical to incorporate into the unit test.  
Mocking is creating objects that simulate the behavior of real objects.  

### Spying
Spies are known as partially mock objects.   
When a layer is too large and we do not mock all that layer, spying is performed.  

### Stubbing
Stubbing is creating an instance of an object to use instead of a mock dependending by the need.    

### Testing Annotations for Java

**@Test**  
Annotates the test itself, you may have several methods annotated in this way in your application.  
To run the method, JUnit first constructs a fresh instance of the class then invokes the annotated method.
Any exceptions thrown by the test will be reported by JUnit as a failure.  

**@BeforeAll**  
Annotates that the annotated method should be executed before all the @Test, @RepeatedTest, @ParameterizedTest, and @TestFactory methods in the current class.  
```java
@BeforeAll
    public static void init(){
        System.out.println("BeforeAll init() method called");
    }
```

**@BeforeEach**  
Annotates that the annotated method should be executed before each invocation of @Test, @RepeatedTest, @ParameterizedTest, or @TestFactory method in the current class.
```java
    @BeforeEach
    public void initEach(){
        System.out.println("BeforeEach initEach() method called");
    }
```

**@AfterAll**  
Annotates that the annotated method should be executed after all tests in the current test class.  
```java
@AfterAll
    public static void cleanUp(){
        System.out.println("After All cleanUp() method called");
    }  
```

**@AfterEach**  
Annotates that the annotated method should be executed after each tests in the current test class.  
```java
    @AfterEach
    public void cleanUpEach(){
        System.out.println("After Each cleanUpEach() method called");
    }
```

**@Mock**  
Used to create and inject mocked instances.  
```java
@Mock
List<String> mockedList;

@Test
public void whenUseMockAnnotation_thenMockIsInjected() {
    mockedList.add("one");
    Mockito.verify(mockedList).add("one");
    assertEquals(0, mockedList.size());

    Mockito.when(mockedList.size()).thenReturn(100);
    assertEquals(100, mockedList.size());
}
```

**@InjectMocks**    
Injects values that are annotated with @Mock annotation.  
```java
@Mock
Map<String, String> wordMap;

@InjectMocks
MyDictionary dic = new MyDictionary();

@Test
public void whenUseInjectMocksAnnotation_thenCorrect() {
    Mockito.when(wordMap.get("aWord")).thenReturn("aMeaning");

    assertEquals("aMeaning", dic.getMeaning("aWord"));
}
```

**@Spy**  

@Spy take an existing instance and override only some or none methods.  
```java
@Spy
private List<String> spiedList = new ArrayList<>();

@Test
public void testSpiedList() {
    spiedList.add("one");
    Mockito.verify(spiedList).add("one");

    Assert.assertEquals(1, spiedList.size());
}
```
### Mockito methods
Following methods are in this package : "import static org.mockito.Mockito" .
**assertEquals()**: Asserts that two objects are equal.  
**when()**: It enables stubbing methods. In simple terms, "When the XYZ() method is called, then return ABC."  
**thenReturn()**: It specify what to return when a method is called.  
**verify()**:  Checks if a certain method of a mock object has been called.  
**doReturn()**:   It specify a return value on a spied object without making a side effect.  

### Testing Naming Conventions  
Some add test, should, when prefixes.  
I do no like adding the same post or prefixes to every method name.  
It makes methods to find when searched.  
Following is my preference:  

**DWI naming style**  
"do_when_is()".   

For example:  
"returnAreaAsInfinity_WhenRadius_IsDoubleMaxValue()"
"returnFalse_WhenAge_IsLessThen18()"  
"returnUser_WhenParameter_IsPositive()"  
"notReturnUser_WhenPrice_IsNUll()"  
"throwException_WhenUsername_IsNotUnique()"  
"convertToLocalDate_WhenDate_Is29Feb()"   

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

### Unit Test Example

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

    // When Date is null, it should throw GenBussinessException. 
    GenBussinessException genBussinessException = assertThrows(GenBussinessException.class,()->DateUtil.convertToLocalDate(null));
    
    //Check if result equals to the wanted error message.
    assertEquals(genBussinessException.getErrorMessage(),GenBussinessException.DATE_COULD_NOT_BE_CONVERTED);
    
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
 
 ### Mocking Testing Example  
 To test the CustomerService layer, you need to mock it's dependencies.  
 
 ```java
 @ExtendWith(MockitoExtension.class)
 class CustomerServiceTest{
    
    @InjectMocks
    CustomerService customerService;
    
    @Mock
    CustomerEntityService customerEntityService;
    
    @Mock
    CustomerConverter customerConverter;
    
    @Test
    void shouldFindAll() {

        CusCustomer cusCustomer = mock(CusCustomer.class);
        List<CusCustomer>  cusCustomerList = new ArrayList<>();
        cusCustomerList.add(cusCustomer);

        CusCustomerDto cusCustomerDto = mock(CusCustomerDto.class);
        List<CusCustomerDto>  cusCustomerDtoList = new ArrayList<>();
        cusCustomerDtoList.add(cusCustomerDto);

        when(cusCustomerEntityService.findAll()).thenReturn(cusCustomerList);
        when(cusCustomerConverter.convertToCusCustomerDtoList(cusCustomerList)).thenReturn(cusCustomerDtoList);

        List<CusCustomerDto> result = cusCustomerService.findAll();

        assertEquals(1, result.size());
    }
    @Test
    void shouldFindById() {

        Long id = 18L;

        CusCustomer cusCustomer = mock(CusCustomer.class);
        when(cusCustomer.getId()).thenReturn(id);

        when(cusCustomerEntityService.getByIdWithControl(id)).thenReturn(cusCustomer);

        CusCustomerDto cusCustomerDto = cusCustomerService.findById(id);

        assertEquals(id, cusCustomerDto.getId());
    }
    @Test
    void shouldSave() {

        CusCustomerSaveRequestDto cusCustomerSaveRequestDto = mock(CusCustomerSaveRequestDto.class);
        when(cusCustomerSaveRequestDto.getPassword()).thenReturn("123");

        CusCustomer cusCustomer = mock(CusCustomer.class);
        when(cusCustomer.getId()).thenReturn(1L);

        when(passwordEncoder.encode(anyString())).thenReturn("bahadir_1234_123");
        when(cusCustomerEntityService.save(any())).thenReturn(cusCustomer);

        CusCustomerDto result = cusCustomerService.save(cusCustomerSaveRequestDto);

        assertEquals(1L, result.getId());
    }
    @Test
    void shouldUpdate() {

        Long id = 18L;

        CusCustomerUpdateRequestDto cusCustomerUpdateRequestDto = mock(CusCustomerUpdateRequestDto.class);
        CusCustomer cusCustomer = mock(CusCustomer.class);
        when(cusCustomer.getId()).thenReturn(id);

        boolean isExist = true;

        when(cusCustomerEntityService.existsById(anyLong())).thenReturn(isExist);
        when(cusCustomerEntityService.save(any())).thenReturn(cusCustomer);

        CusCustomerDto cusCustomerDto = cusCustomerService.update(cusCustomerUpdateRequestDto);

        assertEquals(id, cusCustomerDto.getId());

        verify(cusCustomerEntityService).existsById(anyLong());
    }
    @Test
    void shouldDelete() {

        CusCustomer cusCustomer = mock(CusCustomer.class);

        when(cusCustomerEntityService.getByIdWithControl(anyLong())).thenReturn(cusCustomer);

        cusCustomerService.delete(anyLong());

        verify(cusCustomerEntityService).getByIdWithControl(anyLong());
        verify(cusCustomerEntityService).delete(any());
    }
    
 }
 ```
 
 
 ### Integration Testing Example  
 
 In controller, we can not mock, we need to use real layers so it is integration testing.  

```java
@ExtendWith(MockitoExtension.class)
@SpringBootTest(classes = {SofttechSpringBootApplication.class, H2TestProfileJPAConfig.class})
class CusCustomerControllerTest extends BaseTest {
private static final String BASE_PATH = "/api/v1/customers";

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext context;

    @BeforeEach
    void setUp() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.context).build();
        this.objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());
    }
    
    @Test
    void findAll() throws Exception {

        MvcResult result = mockMvc.perform(
                get(BASE_PATH).content("").contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk()).andReturn();

        boolean isSuccess = isSuccess(result);

        assertTrue(isSuccess);

    }
    
     @Test
    void findById() throws Exception {

        MvcResult result = mockMvc.perform(
                get(BASE_PATH + "/1").content("1L").contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk()).andReturn();

        boolean isSuccess = isSuccess(result);

        assertTrue(isSuccess);

    }
    
     @Test
    void save() throws Exception {

        CusCustomerSaveRequestDto cusCustomerSaveRequestDto = CusCustomerSaveRequestDto.builder()
                .name("erdem")
                .surname("özoğlu")
                .identityNo(12312312389L)
                .password("12345678")
                .build();

        String content = objectMapper.writeValueAsString(cusCustomerSaveRequestDto);

        MvcResult result = mockMvc.perform(
                post(BASE_PATH).content(content).contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk()).andReturn();

        boolean isSuccess = isSuccess(result);

        assertTrue(isSuccess);

    }
    
    @Test
    void update() throws Exception {

        CusCustomerUpdateRequestDto cusCustomerUpdateRequestDto = new CusCustomerUpdateRequestDto();
        cusCustomerUpdateRequestDto.setId(2052L);
        cusCustomerUpdateRequestDto.setName("test2");
        cusCustomerUpdateRequestDto.setSurname("test2");
        cusCustomerUpdateRequestDto.setIdentityNo(12345678918L);
        cusCustomerUpdateRequestDto.setPassword("abcdefgh");

        String content = objectMapper.writeValueAsString(cusCustomerUpdateRequestDto);

        MvcResult result = mockMvc.perform(
                put(BASE_PATH).content(content).contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk()).andReturn();

        boolean isSuccess = isSuccess(result);

        assertTrue(isSuccess);
    }
    
    @Test
    void deleteTest() throws Exception {

        MvcResult result = mockMvc.perform(
                delete(BASE_PATH + "/2202").content("2202").contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk()).andReturn();

        boolean isSuccess = isSuccess(result);

        assertTrue(isSuccess);
    }
 ```


## References  
https://www.baeldung.com/spring-boot-testing  
https://www.baeldung.com/mockito-annotations  
https://glowing-crest-6d5.notion.site/Softtech-Java-Spring-Bootcamp-191efcce77654cd493643314176e4957  
https://medium.com/@tasdikrahman/f-i-r-s-t-principles-of-testing-1a497acda8d6  
https://dzone.com/articles/7-popular-unit-test-naming  
https://www.guru99.com/software-testing-introduction-importance.html  
https://blog.ndepend.com/10-reasons-why-you-should-write-tests/  
https://www.baeldung.com/java-unit-testing-best-practices 
https://www.baeldung.com/mockito-annotations  
