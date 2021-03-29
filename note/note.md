# 1、Spring Security loginPage Vs loginProcessingURL

自己对loginPage和loginProcessingUrl做了下测试：

两者都不配置：默认都是/login

如果只配置loginPage而不配置loginProcessingUrl的话, 那么loginProcessingUrl默认就是loginPage

意思是：没有设置loginProcessingUrl()，那么默认的用户名密码后端校验逻辑的URL跟loginPage对应的地址一致。

```java
protected void configure(HttpSecurity httpSecurity) throws Exception{
        httpSecurity
                .authorizeRequests()
                .antMatchers("/findMeCustomer","findMeBestDriver","orderTaxi")
                .hasRole("ROLE_USER")//找客人，找车，找司机需要认证
                .antMatchers("/","/**")
                .permitAll()//除了以上几点，其余不需要认证
        .and()
                .formLogin()
                .loginPage("/userLogin")//判定没有登陆，那就去登陆页面
                .loginProcessingUrl("/authenticate")
                .usernameParameter("user-name")
                .passwordParameter("pass-word")
                .defaultSuccessUrl("/home")
        .and()
                .logout()
                .logoutSuccessUrl("/");
}
```

The line `loginPage("/login")` instructs Spring Security

- when authentication is required, redirect the browser to /login
- we are in charge of rendering the login page when /login is requested
- when authentication attempt fails, redirect the browser to /login?error (since we have not specified otherwise)
- we are in charge of rendering a failure page when /login?error is requested
- when we successfully logout, redirect the browser to /login?logout (since we have not specified otherwise)
- we are in charge of rendering a logout confirmation page when /login?logout is requested

AND

```
.loginProcessingUrl("/login/process")
```

tells Spring Security to process the submitted credentials when sent the specified path and, by default, redirect user back to the page user came from. *It will not pass the request to Spring MVC and your controller.*

2、@Autowired:

**It allows Spring to resolve and inject collaborating beans into our bean.**

**we can use autowiring on properties, setters, and constructors**.

###   	@Autowired on Properties

```
@Component
public class FooService {  
    @Autowired
    private FooFormatter fooFormatter;
}
```

**@Autowired on Setters**

```
public class FooService {
    private FooFormatter fooFormatter;
    @Autowired
    public void setFooFormatter(FooFormatter fooFormatter) {
        this.fooFormatter = fooFormatter;
    }
}
```

@Autowired on Constructors

```
public class FooService {
    private FooFormatter fooFormatter;
    @Autowired
    public FooService(FooFormatter fooFormatter) {
        this.fooFormatter = fooFormatter;
    }
}
```

@Autowired and @Qualifier:

```
@Component("fooFormatter")
public class FooFormatter implements Formatter {
    public String format() {
        return "foo";
    }
}
@Component("barFormatter")
public class BarFormatter implements Formatter {
    public String format() {
        return "bar";
    }
}
```

```
public class FooService {
    @Autowired
    @Qualifier("fooFormatter")//按名字指示具体装配哪一个bean
    private Formatter formatter;
}
```

