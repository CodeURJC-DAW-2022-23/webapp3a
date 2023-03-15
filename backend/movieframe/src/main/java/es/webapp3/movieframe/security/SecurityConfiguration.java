package es.webapp3.movieframe.security;


import java.security.SecureRandom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
 @Autowired
 RepositoryUserDetailsService userDetailsService;
 
 @Bean
 public PasswordEncoder passwordEncoder() {
 return new BCryptPasswordEncoder(10, new SecureRandom());
 }
 @Override
 protected void configure(AuthenticationManagerBuilder auth) throws Exception {
 auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
 }
 
 @Override
 protected void configure(HttpSecurity http) throws Exception {
 // Public pages
 http.authorizeRequests().antMatchers("/").permitAll();
 http.authorizeRequests().antMatchers("/login").permitAll();
 http.authorizeRequests().antMatchers("/sign_up").permitAll();

 // Private pages (all other pages)
 http.authorizeRequests().antMatchers("/user/new").hasAnyRole("USER");
 http.authorizeRequests().antMatchers("/user/new").hasAnyRole("ADMIN");
 http.authorizeRequests().antMatchers("/reviews/*").hasAnyRole("ADMIN");
 http.authorizeRequests().antMatchers("/recommendations").hasAnyRole("ADMIN");
 http.authorizeRequests().antMatchers("/reviews/new").hasAnyRole("USER");
 http.authorizeRequests().antMatchers("/reviews/{user}").hasAnyRole("USER");

 // Login form
 http.formLogin().loginPage("/login");
 http.formLogin().usernameParameter("username");
 http.formLogin().passwordParameter("password");
 http.formLogin().defaultSuccessUrl("/");
 http.formLogin().failureUrl("/login");

 // Disable CSRF at the moment
 http.csrf().disable();
 }
}
