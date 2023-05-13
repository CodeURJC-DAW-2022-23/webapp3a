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
    UserDetailService userDetailsService;
    
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
        http.authorizeRequests().antMatchers("/login").permitAll();
        http.authorizeRequests().antMatchers("/loginerror").permitAll();
        http.authorizeRequests().antMatchers("/logout").permitAll();
        http.authorizeRequests().antMatchers("/signup").permitAll();     
        http.authorizeRequests().antMatchers("/movies/{id}/image").permitAll();
        http.authorizeRequests().antMatchers("/movie/{id}/director").hasAnyRole("ADMIN","USER");
        http.authorizeRequests().antMatchers("/movie/{id}").permitAll();
        http.authorizeRequests().antMatchers("/movies/{id}/director/image").permitAll();
        http.authorizeRequests().antMatchers("/movies/name").permitAll();
        http.authorizeRequests().antMatchers("/movies/{id}").hasAnyRole("ADMIN","USER");

        http.authorizeRequests().antMatchers("/reviews").permitAll();
        http.authorizeRequests().antMatchers("/reviewsList").permitAll();

        // Private pages (all other pages)
        
        http.authorizeRequests().antMatchers("/movie/{id}/review/new").hasAnyRole("USER");
        http.authorizeRequests().antMatchers("/userReviewsList/{username}").hasAnyRole("USER");    


        http.authorizeRequests().antMatchers("/movie/addition").hasAnyRole("ADMIN");
        http.authorizeRequests().antMatchers("/reviews/deletion/{id}").hasAnyRole("ADMIN");
        
        http.authorizeRequests().antMatchers("/movie/{id}/edition").hasAnyRole("ADMIN");
        http.authorizeRequests().antMatchers("/movie/addition/new").hasAnyRole("ADMIN");
        
        http.authorizeRequests().antMatchers("/reviewsList/{id}").hasAnyRole("ADMIN");

        // Login form
        http.formLogin().loginPage("/login");
        http.formLogin().usernameParameter("username");
        http.formLogin().passwordParameter("password");
        http.formLogin().defaultSuccessUrl("/");
        http.formLogin().failureUrl("/loginerror");

        // Logout
        http.logout().logoutUrl("/logout");
        http.logout().logoutSuccessUrl("/");        
    }
}
