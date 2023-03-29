package es.webapp3.movieframe.security;


import java.security.SecureRandom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
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
        http.authorizeRequests().antMatchers("/movies/name").permitAll();
        http.authorizeRequests().antMatchers("/movie/{id}").permitAll();

        // Private pages (all other pages)
        http.authorizeRequests().antMatchers("/reviews/new").hasAnyRole("USER");
        http.authorizeRequests().antMatchers("/reviews/{user}/{id}").hasAnyRole("USER");
        http.authorizeRequests().antMatchers("/review/{id}/edition").hasAnyRole("USER");
        http.authorizeRequests().antMatchers("/reviews/{user}").hasAnyRole("USER");
        http.authorizeRequests().antMatchers("/movie/{id}/review/new").hasAnyRole("USER");
        http.authorizeRequests().antMatchers("/movie/{id}/director").hasAnyRole("USER");


        http.authorizeRequests().antMatchers("/movie/addition").hasAnyRole("ADMIN");
        http.authorizeRequests().antMatchers("/reviews/deletion/{id}").hasAnyRole("ADMIN");
        http.authorizeRequests().antMatchers("/reviews").hasAnyRole("ADMIN");
        http.authorizeRequests().antMatchers("/news").hasAnyRole("ADMIN");
        http.authorizeRequests().antMatchers("/movie/{id}/edition").hasAnyRole("ADMIN");
        http.authorizeRequests().antMatchers("/movie/addition/new").hasAnyRole("ADMIN");
        

        // Login form
        http.formLogin().loginPage("/login");
        http.formLogin().usernameParameter("username");
        http.formLogin().passwordParameter("password");
        http.formLogin().defaultSuccessUrl("/");
        http.formLogin().failureUrl("/loginerror");

        // Logout
        http.logout().logoutUrl("/logout");
        http.logout().logoutSuccessUrl("/");

        http.authorizeRequests().antMatchers(HttpMethod.POST,"/api/movies/addition/new").hasRole("ADMIN");
        http.authorizeRequests().antMatchers(HttpMethod.POST,"/api/movies/addition/new/{id}/image").hasRole("ADMIN");
        http.authorizeRequests().antMatchers(HttpMethod.GET,"/api/reviewsList").hasRole("ADMIN");
        http.authorizeRequests().antMatchers(HttpMethod.GET,"/api/usersList").hasRole("ADMIN");
        http.authorizeRequests().antMatchers(HttpMethod.DELETE,"/api/reviewsList/{id}").hasRole("ADMIN");
        http.authorizeRequests().antMatchers(HttpMethod.PUT,"/api/movies/{id}/edition").hasRole("ADMIN");
        http.authorizeRequests().antMatchers(HttpMethod.GET,"/api/movies/{id}").hasRole("ADMIN");
        http.authorizeRequests().antMatchers(HttpMethod.GET,"/api/reviews/{id}").hasRole("ADMIN");
        http.authorizeRequests().antMatchers(HttpMethod.POST,"/api/movies/{id}/review/new").hasRole("ADMIN");
        http.authorizeRequests().antMatchers(HttpMethod.GET,"/api/movies/{id}/image").hasRole("ADMIN");

        http.authorizeRequests().antMatchers(HttpMethod.GET,"/api/userReviewsList/{username}").hasRole("USER");
        http.authorizeRequests().antMatchers(HttpMethod.GET,"/api/directors/{id}").hasRole("USER");
        http.authorizeRequests().antMatchers(HttpMethod.GET,"/api/movies/{id}/director/image").hasRole("USER");
        http.authorizeRequests().antMatchers(HttpMethod.GET,"/api/movies/{id}").hasRole("USER");
        http.authorizeRequests().antMatchers(HttpMethod.GET,"/api/reviews/{id}").hasRole("USER");
        http.authorizeRequests().antMatchers(HttpMethod.POST,"/api/movies/{id}/review/new").hasRole("USER");
        http.authorizeRequests().antMatchers(HttpMethod.GET,"/api/movies/{id}/image").hasRole("USER");

         // Other endpoints are public
         http.authorizeRequests().anyRequest().permitAll();
        
         // Enable Basic Authentication
         http.httpBasic();

        // Disable CSRF at the moment
        http.csrf().disable();
    }
}
