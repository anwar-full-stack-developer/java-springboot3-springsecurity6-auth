package com.anwar.authspringsecurity.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.anwar.authspringsecurity.repository.RoleRepository;
import com.anwar.authspringsecurity.repository.UserRepository;
import com.anwar.authspringsecurity.service.UserService;
import com.anwar.authspringsecurity.service.UserServiceImpl;

@Configuration
@EnableWebSecurity
public class SpringSecurity {	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	private UserRepository userRepository;
	
	@Bean
	public static PasswordEncoder passwordEncoder() {
	    return new BCryptPasswordEncoder();
	}
	
	@Bean
	public static BCryptPasswordEncoder bCryptPasswordEncoder() {
	    return new BCryptPasswordEncoder();
	}
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
	    http.csrf().disable()
	            .authorizeHttpRequests((authorize) ->
	                    authorize.requestMatchers("/register/**").permitAll()
                        .requestMatchers("/").permitAll()
                        .requestMatchers("/index").permitAll()
	                            .requestMatchers("/users").hasRole("ADMIN")
	            ).formLogin(
	                    form -> form
	                            .loginPage("/login")
	                            .loginProcessingUrl("/login")
	                            .defaultSuccessUrl("/users")
	                            .permitAll()
	            ).logout(
	                    logout -> logout
	                            .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
	                            .permitAll()
	            );
	    return http.build();
	}

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
	    auth
	            .userDetailsService(userDetailsService)
	            //.passwordEncoder(passwordEncoder())
	            .passwordEncoder(bCryptPasswordEncoder())
	            ;
	}
	
    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring().requestMatchers("/ignore1", "/ignore2");
    }

    @Bean
    UserService userService() {
      return new UserServiceImpl(null,null,null);
    }
    
}
