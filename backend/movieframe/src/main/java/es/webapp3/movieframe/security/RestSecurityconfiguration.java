package es.webapp3.movieframe.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import es.webapp3.movieframe.security.jwt.JwtRequestFilter;

@Configuration
@Order(1)
public class RestSecurityconfiguration extends WebSecurityConfigurerAdapter{

    @Autowired
	private UserDetailService userDetailsService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

    @Autowired
	private JwtRequestFilter jwtRequestFilter;

    @Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {

		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
	}

	//Expose AuthenticationManager as a Bean to be used in other services
	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

    @Override
	protected void configure(HttpSecurity http) throws Exception {

        http.antMatcher("/api/**");

        http.authorizeRequests().antMatchers(HttpMethod.POST,"/api/movies/addition/new").hasRole("ADMIN");
        http.authorizeRequests().antMatchers(HttpMethod.POST,"/api/movies/addition/new/{id}/image").hasRole("ADMIN");
        http.authorizeRequests().antMatchers(HttpMethod.GET,"/api/reviewsList").hasRole("ADMIN");
        http.authorizeRequests().antMatchers(HttpMethod.GET,"/api/usersList").hasRole("ADMIN");
        http.authorizeRequests().antMatchers(HttpMethod.DELETE,"/api/reviewsList/{id}").hasRole("ADMIN");
        http.authorizeRequests().antMatchers(HttpMethod.PUT,"/api/movies/{id}/edition").hasRole("ADMIN");
        http.authorizeRequests().antMatchers(HttpMethod.GET,"/api/reviews/{id}").hasAnyRole("ADMIN","USER");

        http.authorizeRequests().antMatchers(HttpMethod.POST,"/api/movies/{id}/review/new").hasRole("USER");
        http.authorizeRequests().antMatchers(HttpMethod.GET,"/api/userReviewsList/{username}").hasRole("USER");
        http.authorizeRequests().antMatchers(HttpMethod.GET,"/api/directors/{id}").hasRole("USER");
        http.authorizeRequests().antMatchers(HttpMethod.GET,"/api/movies/{id}/director/image").hasRole("USER");
        
        http.authorizeRequests().antMatchers(HttpMethod.GET,"/api/movies/{id}").hasAnyRole("USER","ADMIN");

         // Other endpoints are public
        http.authorizeRequests().anyRequest().permitAll();
         
        // Disable CSRF at the moment
        http.csrf().disable();

		// Disable Http Basic Authentication
		http.httpBasic().disable();
		
		// Disable Form login Authentication
		http.formLogin().disable();

		// Avoid creating session 
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
		
    }
}
