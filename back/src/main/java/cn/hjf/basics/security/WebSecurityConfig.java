package cn.hjf.basics.security;

import cn.hjf.basics.redis.RedisTemplateHelper;
import cn.hjf.basics.security.jwt.*;
import cn.hjf.basics.utils.SecurityUtil;
import cn.hjf.basics.parameter.HjfLoginProperties;
import cn.hjf.basics.security.validate.ImageValidateFilter;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * @author 何健锋
 * CSDN: Designer 何健锋
 */
@ApiOperation(value = "SpringSecurity配置类")
@Configuration
@EnableMethodSecurity
public class WebSecurityConfig {

    @Autowired
    private HjfLoginProperties hjfLoginProperties;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    private AuthenticationSuccessHandler authenticationSuccessHandler;

    @Autowired
    private AuthenticationFailHandler authenticationFailHandler;

    @Autowired
    private HjfAccessDeniedHandler hjfAccessDeniedHandler;

    @Autowired
    private ImageValidateFilter imageValidateFilter;

    @Autowired
    private RedisTemplateHelper redisTemplate;

    @Autowired
    private SecurityUtil securityUtil;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http.authorizeHttpRequests().requestMatchers("/zwz/dictData/getByType/**","/zwz/file/view/**","/zwz/user/regist","/zwz/common/**","/*/*.js","/*/*.css","/*/*.png","/*/*.ico", "/swagger-ui.html").permitAll()
                .and().formLogin().loginPage("/zwz/common/needLogin").loginProcessingUrl("/zwz/login").permitAll()
                .successHandler(authenticationSuccessHandler).failureHandler(authenticationFailHandler).and()
                .headers().frameOptions().disable().and()
                .logout()
                .permitAll()
                .and()
                .authorizeHttpRequests()
                .anyRequest()
                .authenticated()
                .and()
                .cors().and()
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .exceptionHandling().accessDeniedHandler(hjfAccessDeniedHandler)
                .and()
                .authenticationProvider(authenticationProvider())
                .addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(imageValidateFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return username -> userDetailsService.loadUserByUsername(username);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    public JwtTokenOncePerRequestFilter authenticationJwtTokenFilter() throws Exception {
        return new JwtTokenOncePerRequestFilter(redisTemplate, securityUtil, hjfLoginProperties);
    }
}
