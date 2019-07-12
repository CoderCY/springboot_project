package cn.cy.config;

import cn.cy.core.filter.JWTAuthenticationFilter;
import cn.cy.core.filter.JWTLoginFilter;
import cn.cy.core.handler.JWTAuthenctiationSuccessHandler;
import cn.cy.service.SysUserDedailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * @EnableWebSecurity 主要是引入WebSecurityConfiguration.class和SpringWebMvcImportSelector.class，
 * Spring Security默认是禁用注解的，要想开启注解，
 * 需要在继承WebSecurityConfigurerAdapter的类上加@EnableGlobalMethodSecurity注解，
 * 来判断用户对某个控制层的方法是否具有访问权限
 *
 * @EnableGlobalMethodSecurity(securedEnabled=true) 开启@Secured 注解过滤权限
 * @EnableGlobalMethodSecurity(jsr250Enabled=true)开启@RolesAllowed 注解过滤权限
 * @EnableGlobalMethodSecurity(prePostEnabled=true) 使用表达式时间方法级别的安全性
 *
 * @PreAuthorize 在方法调用之前,基于表达式的计算结果来限制对方法的访问
 * @PostAuthorize 允许方法调用,但是如果表达式计算结果为false,将抛出一个安全性异常
 * @PostFilter 允许方法调用,但必须按照表达式来过滤方法的结果
 * @PreFilter 允许方法调用,但必须在进入方法之前过滤输入值
 *
 * Created by 30721 on 2019/7/10.
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled =true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private SysUserDedailsService sysUserDedailsService;
    @Autowired
    private JWTAuthenctiationSuccessHandler jwtAuthenctiationSuccessHandler;

    /**
     * 可以根据现实预期，编写自定义加密方法
     * @return
     */
    @Bean
    public PasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * 设置 HTTP 验证规则
     * 默认配置为：
     * http.authorizeRequests().anyRequest().authenticated().and().formLogin().and().httpBasic();
     * 退出登录的地址为 "/logout"，退出成功后跳转到页面 "/login"
     * 默认启用 CSRF
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //开启跨域 关闭csrf。
        http.cors().and().csrf().disable()
                .authorizeRequests()
                //开启swagger2的必要权限
                .antMatchers("/v2/api-docs", "/configuration/ui", "/swagger-resources", "/configuration/security", "/swagger-ui.html", "/webjars/**","/swagger-resources/configuration/ui").permitAll()
                //开启api的匿名访问
                .antMatchers("/api/**").permitAll()
                //角色为管理员的时候才有权限访问admin地址
                .antMatchers("/admin/**").access("hasRole('ADMIN')")
                //角色为USER的时候才有权限访问user地址
                .antMatchers("/sysUsers/**").access("hasRole('USER')")
                //由于该项目定位是前后端分离，这个项目只涉及到后端，所以没有HTML css等文件，所以注释掉，需要的就将他打开
                //.antMatchers("/", "/*.html","/favicon.ico", "/css/**", "/js/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
//                自定义登入页
//                .loginPage("/login")
//                自定义登入路径
//                .loginProcessingUrl("")
//                认证成功后的跳转路径
//                .successForwardUrl("/home")
//                授权成功处理程序  --- 但是我试了一下却不能正常跳转到处理器的方法里面，暂时还没找到原因
//                .successHandler(jwtAuthenctiationSuccessHandler)
                .permitAll()
                .and()
                //注销
                .logout()
                .permitAll();
        //拦截器重写
        http.addFilter(new JWTLoginFilter(authenticationManager()));
        //授权验证拦截重写
        http.addFilterBefore(new JWTAuthenticationFilter(authenticationManager()), UsernamePasswordAuthenticationFilter.class);
    }

    /**
     * 使用自定义身份验证组件
     * 重写安全认证，加入密码加密方式
     * @param auth
     * @throws Exception
     */
    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(sysUserDedailsService).passwordEncoder(bCryptPasswordEncoder());
    }
}
