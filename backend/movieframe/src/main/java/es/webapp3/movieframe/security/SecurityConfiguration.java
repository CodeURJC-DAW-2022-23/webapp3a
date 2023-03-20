package es.webapp3.movieframe.security;


import java.security.SecureRandom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    RepositoryUserDetailsService userDetailsService;
    
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder(10, new SecureRandom());
    }
        
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        
        auth.userDetailsService(userDetailsService)
        .passwordEncoder(passwordEncoder());
    }
    
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // Public pages
        http.authorizeRequests().antMatchers("/").permitAll();
        http.authorizeRequests().antMatchers("/log_in").permitAll();
        http.authorizeRequests().antMatchers("/login_error").permitAll();
        http.authorizeRequests().antMatchers("/log_out").permitAll();
        http.authorizeRequests().antMatchers("/sign_up").permitAll();
        http.authorizeRequests().antMatchers("/movies/name").permitAll();

        // Private pages (all other pages)
        http.authorizeRequests().antMatchers("/reviews/new").hasAnyRole("USER");
        http.authorizeRequests().antMatchers("/reviews/user/{{id}}").hasAnyRole("USER");
        http.authorizeRequests().antMatchers("/user/*").hasAnyRole("USER");
        http.authorizeRequests().antMatchers("/movie/{{id}}/review/new").hasAnyRole("USER");
        http.authorizeRequests().antMatchers("/movie/{{id}}").hasAnyRole("USER");
        http.authorizeRequests().antMatchers("/movie/{{id}}/director").hasAnyRole("USER");
        http.authorizeRequests().antMatchers("/director").hasAnyRole("USER");

        http.authorizeRequests().antMatchers("/reviews/*").hasAnyRole("ADMIN");
        http.authorizeRequests().antMatchers("/news").hasAnyRole("ADMIN");
        http.authorizeRequests().antMatchers("/movie/*").hasAnyRole("ADMIN");
        http.authorizeRequests().antMatchers("/user/*").hasAnyRole("ADMIN");
        http.authorizeRequests().antMatchers("/director").hasAnyRole("ADMIN");
        

        // Login form
        http.formLogin().loginPage("/login");
        http.formLogin().usernameParameter("username");
        http.formLogin().passwordParameter("password");
        http.formLogin().defaultSuccessUrl("/");
        http.formLogin().failureUrl("/loginerror");

        // Disable CSRF at the moment
        http.csrf().disable();
    }
}
