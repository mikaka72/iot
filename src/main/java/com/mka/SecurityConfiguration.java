package com.mka;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.mka.security.JWTAuthenticationFilter;
import com.mka.security.JWTAuthorizationFilter;

@Configuration
@EnableWebSecurity
@ComponentScan({"com.mka.security"})

public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	
	private final UserDetailsService userDetailsService;
	private final BCryptPasswordEncoder bCryptPasswordEncoder;
	
	
	public SecurityConfiguration(final UserDetailsService userDetailsService, final BCryptPasswordEncoder bCryptPasswordEncoder) {
		this.userDetailsService = userDetailsService;
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
	}
	
	/**
	 * Configure application security as follows. 
	 *  	- csrf (cross site request forgery) functionality is disabled.
	 *  	- No authentication at the moment
	 */
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		//http.csrf().disable();
		//http.authorizeRequests().anyRequest().permitAll();
		
		 http.cors().and().csrf().disable().authorizeRequests()
         .anyRequest().authenticated()
         .and()
         .addFilter(new JWTAuthenticationFilter(authenticationManager()))
         .addFilter(new JWTAuthorizationFilter(authenticationManager()))
         // this disables session creation on Spring Security
         .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		
	}
	
	@Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
    }
	
	@Bean
	  CorsConfigurationSource corsConfigurationSource() {
	    final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
	    source.registerCorsConfiguration("/**", new CorsConfiguration().applyPermitDefaultValues());
	    return source;
     }

	@Override
	public void configure(WebSecurity web) throws Exception {
	    
		
		  web.ignoring().antMatchers("/swagger-ui.html*");
		    web.ignoring().antMatchers("/swagger-resources");
		    web.ignoring().antMatchers("/v2/*");
		    web.ignoring().antMatchers("/configuration/*");
	    // ignore the BitBucket push hook listener
	    web.ignoring().antMatchers("/ping");
	    web.ignoring().antMatchers("/v2/api-docs", "/configuration/ui", "/swagger-resources/**", "/configuration/security", "/swagger-ui.html", "/webjars/**");
	    web.ignoring().antMatchers("/api/**");
	}
	
}

