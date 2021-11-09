package com.citen.config;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final PasswordEncoder passwordEncoder;

@Autowired
public SecurityConfig(PasswordEncoder passwordEncoder){
    this.passwordEncoder = passwordEncoder;
}

    @Override
    protected void configure(HttpSecurity http) throws Exception {
/*         http.authorizeRequests()
        .antMatchers("/","/user/*")
        .permitAll()
        .anyRequest()
        .authenticated()
        .and()
        .httpBasic(); */
        
        http.csrf().disable().authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers(HttpMethod.GET, "/user/*").hasAuthority(UserPersmission.USER_READ.getPermission())
                .antMatchers(HttpMethod.POST, "/user/**").hasAnyAuthority(UserPersmission.USER_WRITE.getPermission())
                .antMatchers(HttpMethod.DELETE, "/user/**").hasAnyAuthority(UserPersmission.USER_WRITE.getPermission())
                .anyRequest().authenticated().and()
                .formLogin()
                .loginPage("/auth/login").permitAll()
                .defaultSuccessUrl("/auth/success");

    }

    @Override
    @Bean
    protected UserDetailsService userDetailsService() {
        return new InMemoryUserDetailsManager(
            User.builder()
                .username("admin")
                .password(passwordEncoder.encode("admin"))
                .authorities(UserRoles.ADMIN.getAuthorities())
                .build(),
                User.builder()
                .username("user")
                .password(passwordEncoder.encode("user"))
                .authorities(UserRoles.ADMIN.getAuthorities())
                .build()
        );

/*         User userAdmin = (User) User.builder()
            .username("admin")
            .password(passwordEncoder.encode("admin"))
            .authorities(UserRoles.ADMIN.getAuthorities())
            .build();
        User.builder()
            .username("user")
            .password(passwordEncoder.encode("user"))
            .authorities(UserRoles.USER.getAuthorities())
            .build();
        return new InMemoryUserDetailsManager(userAdmin); */
    }

    /*
     * @Override protected UserDetailsService userDetailsService() { return new
     * InMemoryUserDetailsManager(
     * User.builder().username("admin").password(passwordEncoder().encode("admin")).
     * roles(Role.ADMIN.name()) .build(),
     * User.builder().username("user").password(new
     * BCryptPasswordEncoder().encode("user")) .roles(Role.USER.name()).build()); }
     */

    /*
     * @Bean protected PasswordEncoder passwordEncoder(){ return new
     * BCryptPasswordEncoder(12); }
     */
}