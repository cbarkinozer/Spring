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
![image](https://user-images.githubusercontent.com/43732258/157492135-edeacb7a-abab-4bb6-8e66-c0bf74f527bb.png) 

**Header**  
JWT header information is written in JSON format and has some standard fields.  
“alg” Specifies the cryptotic algorithm to be used to protect data integrity. You can choose the algorithm you want here.  
“type” indicates that it is a JWT object.  
**Payload**  
The payload may contain fields such as the user's identity, timeout and user authorizations. In addition to these, if role-based operation is to be performed on the system, it is specified here.  
**Verify signature**  
Verify signature guarantees data integrity between the signature part token producer and consumer. While creating the signature, the algorithm defined in the JOSE header is used.  

**How JWT Works ?**  

![image](https://user-images.githubusercontent.com/43732258/157496067-946766f1-4537-4112-8494-4744bb2d1ce9.png)

The first step starts with the user logging in to the server with credentials (e.g userID and password).  
The Credentials are authenticated by the server, then server returns JWT token back to the client.  
This token is saved in local or cookies.  
Then token goes back to the server for logging.  
If the JWT token really created by that server user access gets accapted.  


**JWT Token Usage***
It would be a good practice not to keep the token period too long during the JWT generation phase here, otherwise a security vulnerability may occur.  
The user must periodically request new tokens when their token expire.  

The received token is usually kept in localStorages on the client to be sent in subsequent requests.  
When the user wants to access a resource that requires authorization, one should send the JWT in the header as follows.  

**Authorization: "Bearer <token>"**  


**Implementation**    
Add following to pom.xml:   

```xml
<dependency>
	<groupId>org.springframework.boot</groupId>
	<artifactId>spring-boot-starter-security</artifactId>
</dependency>

<dependency>
	<groupId>io.jsonwebtoken</groupId>
	<artifactId>jjwt</artifactId>
	<version>0.9.1</version>
</dependency>
```

Create a package called sec and inside security, config, controller, dto, enums, security, service.  

In controller:
```java
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @Operation(tags = "Authentication Controller")
    @PostMapping("/login")
    public ResponseEntity login(@RequestBody SecLoginRequestDto secLoginRequestDto){

        String token = authenticationService.login(secLoginRequestDto);
        return ResponseEntity.ok(RestResponse.of(token));
    }

    @Operation(tags = "Authentication Controller")
    @PostMapping("/register")
    public ResponseEntity register(@RequestBody CusCustomerSaveRequestDto cusCustomerSaveRequestDto){

        CusCustomerDto cusCustomerDto =authenticationService.register(cusCustomerSaveRequestDto);
        return ResponseEntity.ok(RestResponse.of(cusCustomerDto));
    }
}
```

In dto:
```java
@Data
public class SecLoginRequestDto {

    private Long identityNo;
    private String password;
}
```

In  enums:
```java
public enum EnumJwtConstant {

    BEARER("Bearer ")
    ;

    private String constant;
    EnumJwtConstant(String constant) {
        this.constant = constant;
    }

    public String getConstant() {
        return constant;
    }

    @Override
    public String toString() {
        return constant;
    }

```

In config:
```java
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserDetailsService userDetailsService;
    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

    @Bean
    public JwtAuthenticationFilter jwtAuthenticationFilter(){
        return new JwtAuthenticationFilter();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean(BeanIds.AUTHENTICATION_MANAGER)
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public CorsFilter corsFilter(){

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowCredentials(true);
        configuration.addAllowedHeader("*");
        configuration.addAllowedMethod("GET");
        configuration.addAllowedMethod("POST");
        configuration.addAllowedMethod("PUT");
        configuration.addAllowedMethod("DELETE");
        configuration.addAllowedMethod("PATCH");
        configuration.addAllowedMethod("OPTION");
        configuration.addAllowedMethod("HEAD");
        configuration.setAllowedOriginPatterns(Collections.singletonList("*"));

        source.registerCorsConfiguration("/**", configuration);

        return new CorsFilter(source);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        auth
                .userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {

        httpSecurity
                .cors()
                .and()
                .csrf().disable()
                .exceptionHandling().authenticationEntryPoint(jwtAuthenticationEntryPoint)
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers("/auth/**",
                        "/swagger-ui/**",
                        "/swagger-ui.html",
                        "/v3/api-docs/**"
                )
                .permitAll()
                .anyRequest().authenticated();

        httpSecurity.addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
    }
}
```
In service:
```java
@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final CusCustomerService cusCustomerService;
    private final CusCustomerEntityService cusCustomerEntityService;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenGenerator jwtTokenGenerator;

    public CusCustomerDto register(CusCustomerSaveRequestDto cusCustomerSaveRequestDto) {

        CusCustomerDto cusCustomerDto = cusCustomerService.save(cusCustomerSaveRequestDto);

        return cusCustomerDto;
    }

    public String login(SecLoginRequestDto secLoginRequestDto) {

        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(secLoginRequestDto.getIdentityNo().toString(), secLoginRequestDto.getPassword());
        Authentication authentication = authenticationManager.authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = jwtTokenGenerator.generateJwtToken(authentication);

        String bearer = EnumJwtConstant.BEARER.getConstant();

        return bearer + token;
    }

    public CusCustomer getCurrentCustomer() {

        JwtUserDetails jwtUserDetails = getCurrentJwtUserDetails();

        CusCustomer cusCustomer = null;
        if (jwtUserDetails != null){
            cusCustomer = cusCustomerEntityService.getByIdWithControl(jwtUserDetails.getId());
        }

        return cusCustomer;
    }

    public Long getCurrentCustomerId(){

        JwtUserDetails jwtUserDetails = getCurrentJwtUserDetails();
        return jwtUserDetails.getId();
    }

    private JwtUserDetails getCurrentJwtUserDetails() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        JwtUserDetails jwtUserDetails = null;
        if (authentication != null && authentication.getPrincipal() instanceof JwtUserDetails){
            jwtUserDetails = (JwtUserDetails) authentication.getPrincipal();
        }
        return jwtUserDetails;
    }
}
```
In security package, there are 5 files:  


## References
https://tugrulbayrak.medium.com/jwt-json-web-tokens-nedir-nasil-calisir-5ca6ebc1584a    
https://medium.com/kodluyoruz/json-web-token-jwt-authentication-b5e6675a6e19  
https://jwt.io/introduction/  
