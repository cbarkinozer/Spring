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

**Authorization: "Bearer the-token"**  


## Implementation  

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

Create a package called sec and inside add following packages security, config, controller, dto, enums, security, service.  

In security package:

There is a interface called UserDetails for creating user based security.  
Create a class (JwtUserDetails) and implement UserDetails and override it's methods.  
We will mostly use authority, username, password, user's id.  
Add id,username,password fields (depends on your User class).  
Implement a create method that initiates fields.  
Making the constructor private, blocks access to the constructor.  


In JwtUserDetails:
```java
public class JwtUserDetails implements UserDetails {

    private Long id;
    private String username;
    private String password;
    private Collection<? extends GrantedAuthority> authorities;

    private JwtUserDetails(Long id, String username, String password, Collection<? extends GrantedAuthority> authorities) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.authorities = authorities;
    }

    public static JwtUserDetails create(CusCustomer cusCustomer){

        Long id = cusCustomer.getId();
        String username = cusCustomer.getIdentityNo().toString();
        String password = cusCustomer.getPassword();

        List<GrantedAuthority> grantedAuthorityList = new ArrayList<>();
        grantedAuthorityList.add(new SimpleGrantedAuthority("user"));

        return new JwtUserDetails(id, username, password, grantedAuthorityList);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public Long getId() {
        return id;
    }
}
```


In security:  

Create a class (JwtUserDetailsService) and implement UserDetailsService to create a service and override it's methods.  
In this class we handle loading user by username and id.    
In methods we find customers by id and create them.  


In JwtUserDetailsService:
```java
@Service
@RequiredArgsConstructor
public class JwtUserDetailsService implements UserDetailsService {

    private final CusCustomerEntityService cusCustomerEntityService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Long identityNo = Long.valueOf(username);

        CusCustomer cusCustomer = cusCustomerEntityService.findByIdentityNo(identityNo);

        return JwtUserDetails.create(cusCustomer);
    }

    public UserDetails loadUserByUserId(Long id) {

        CusCustomer cusCustomer = cusCustomerEntityService.getByIdWithControl(id);

        return JwtUserDetails.create(cusCustomer);
    }
}
```

To create token we need 2 things, Application key and expire time.  
Application key can be stored in applicaiton.properties file.  
Expire time unit is miliseconds 1 day = 86.400 ms.  
Than in generateJwtToken method, we define principal(JwtUserDetails),
and expire date by adding expire time on creating date.  
Than a token will be created with builder pattern.  
In builder we set subject(an id), issuedAt(creation date), expiration(expire date), signWith(algorithm (hash512) and appkey).  
At least create with compact.  

We also parse (decode) tokens here.  


In JwtTokenGenerator:
```java
@Component
public class JwtTokenGenerator {

    @Value("${softtechspringboot.jwt.security.app.key}")
    private String APP_KEY;

    @Value("${softtechspringboot.jwt.security.expire.time}")
    private Long EXPIRE_TIME;

    public String generateJwtToken(Authentication authentication){

        JwtUserDetails jwtUserDetails = (JwtUserDetails) authentication.getPrincipal();
        Date expireDate = new Date(new Date().getTime() + EXPIRE_TIME);

        String token = Jwts.builder()
                .setSubject(Long.toString(jwtUserDetails.getId()))
                .setIssuedAt(new Date())
                .setExpiration(expireDate)
                .signWith(SignatureAlgorithm.HS512, APP_KEY)
                .compact();

        return token;
    }

    public Long findUserIdByToken(String token){

        Jws<Claims> claimsJws = parseToken(token);

        String userIdStr = claimsJws
                .getBody()
                .getSubject();

        return Long.parseLong(userIdStr);
    }

    private Jws<Claims> parseToken(String token) {
        Jws<Claims> claimsJws = Jwts.parser()
                .setSigningKey(APP_KEY)
                .parseClaimsJws(token);
        return claimsJws;
    }

    public boolean validateToken(String token){

        boolean isValid;

        try {
            Jws<Claims> claimsJws = parseToken(token);

            isValid = !isTokenExpired(claimsJws);
        } catch (Exception e){
            isValid = false;
        }

        return isValid;
    }

    private boolean isTokenExpired(Jws<Claims> claimsJws) {

        Date expirationDate = claimsJws.getBody().getExpiration();

        return expirationDate.before(new Date());
    }
}
```

OncePerRequestFilter is a special type of filter for authentication in Spring.  
A Filter can be called either before or after servlet execution.  
Java servlets are Java classes that are designed to respond to HTTP requests in the context of a Web application.  

When a request goes through the filter chain, we might want some of the authentication actions to happen only once for the request.  
We can extend the OncePerRequestFilter in such situations.  
We are controlling if the encoded token is valid.  

In JwtAuthenticationFilter:
```java
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private JwtTokenGenerator jwtTokenGenerator;

    @Autowired
    private JwtUserDetailsService jwtUserDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        String token = getToken(request);

        if (StringUtils.hasText(token)){

            boolean isValid = jwtTokenGenerator.validateToken(token);

            if (isValid){

                Long userId = jwtTokenGenerator.findUserIdByToken(token);

                UserDetails userDetails = jwtUserDetailsService.loadUserByUserId(userId);

                if (userDetails != null){
		
		    //Followings are fix

                    UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                    authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                    SecurityContextHolder.getContext().setAuthentication(authenticationToken); //Checks if that token (even everything is valid) was born in the generator
                }
            }
        }

        filterChain.doFilter(request, response);
    }

    private String getToken(HttpServletRequest request) {
        String fullToken = request.getHeader("Authorization");  //Get token from Authorization topic inside the HTTP Responde's header
        String token = null;
        if (StringUtils.hasText(fullToken)){
            String bearer = EnumJwtConstant.BEARER.getConstant(); //"bearer " saved as enum

            if (fullToken.startsWith(bearer)){ //Check if it starts with bearer
                token = fullToken.substring(bearer.length()); //Get token from full token index 7 to end
            }
        }
        return token;
    }
}
```




AuthenticationEntryPoint shows which error code to send in unauthorized transactions.  

In JwtAuthenticationEntryPoint:	
```java
@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, authException.getMessage());
    }
}
```



To enable HTTP Security in Spring, we need to extend the WebSecurityConfigurerAdapter to provide a default configuration in the configure(HttpSecurity http) method.  


In config:
```java
@Configuration //Because we have @Beans inside this class
@EnableWebSecurity //To get WebSecurity
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

//Accepting specific headers coming from CORS
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
                .cors() //cross origin resource sharing
                .and()
                .csrf().disable() //Cross-Site Request Forgery
                .exceptionHandling().authenticationEntryPoint(jwtAuthenticationEntryPoint)
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers("/auth/**",
                        "/swagger-ui/**",
                        "/swagger-ui.html",
                        "/v3/api-docs/**"
                ) //request accepted resources(register and login points)
                .permitAll()
                .anyRequest().authenticated();

        httpSecurity.addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
    }
}
```




Create a controller for login and register operations:
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


AuthenticationController use AuthenticationService:

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


In  enums:
```java
public enum EnumJwtConstant {

    BEARER("Bearer ") //All tokens include "Bearer " at their beginning
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

To add authorization button on Swagger.  
In gen>config>SwaggerConfig:  
```java
@Configuration
public class SwaggerConfig {

    @Value("${application.title}")
    private String APP_TITLE;

    @Bean
    public OpenAPI customOpenAPI(){

        final String securitySchemeName = "bearerAuth";
        final String apiTitle = String.format("%s API", StringUtils.capitalize(APP_TITLE));

        return new OpenAPI()
                .addSecurityItem(new SecurityRequirement().addList(securitySchemeName))
                .components(
                        new Components()
                                .addSecuritySchemes(securitySchemeName,
                                        new SecurityScheme()

                                                .name(securitySchemeName)
                                                .type(SecurityScheme.Type.HTTP)
                                                .scheme("bearer")
                                                .bearerFormat("JWT")
                                )
                )
                .info(new Info().title(apiTitle).version("1"));
    }
}
```

Now, from this button you can acces the api by entering your token (wihtout bearer).  

## References
https://tugrulbayrak.medium.com/jwt-json-web-tokens-nedir-nasil-calisir-5ca6ebc1584a    
https://medium.com/kodluyoruz/json-web-token-jwt-authentication-b5e6675a6e19  
https://jwt.io/introduction/  
https://github.com/sbahadirm/softtech-spring-boot/tree/master/src/main/java/com/softtech/softtechspringboot/app/sec  
https://www.baeldung.com/spring-onceperrequestfilter  
https://www.baeldung.com/spring-rest-openapi-documentation  
