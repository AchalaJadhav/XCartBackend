package com.innovation.xcartbackend.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.innovation.xcartbackend.util.Role;

/**
 * 
 * @author Ajinkya.Deshpande
 *
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	/**
	 * This array is used to specify the urls which are available without
	 * authorization.
	 */
	public static final String[] PUBLIC_URLS = { "/auth/login", "/v2/api-docs", "/swagger-resources/**",
			"/swagger-ui/**", "/webjars/**", "/signup" };
	@Autowired
	private CustomUserDetailService customUserDetailService;

	@Autowired
	private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

	@Autowired
	private JwtAuthenticationFilter jwtAuthenticationFilter;

	/**
	 * This method configures the authority to different urls that is it is used to
	 * specify the public and private methods.
	 */
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable().authorizeHttpRequests().antMatchers("/login").permitAll().antMatchers("/signup")
				.permitAll().antMatchers("/swagger-ui/**").permitAll()
				.antMatchers("/v2/api-docs", "/swagger-resources/configuration/ui", "/swagger-resources",
						"/swagger-resources/configuration/security", "/swagger-ui.html", "/webjars/**", "/getproducts",
						"/getcart", "/download","/getcategories","/getquestions","/validateUsername","/getUserQuestionAnswer","/updatePasswordAndSecurity")
				.permitAll().antMatchers(HttpMethod.OPTIONS, "/**").permitAll().antMatchers("/addproducts")
				.hasRole(Role.ROLE_ADMIN.name())

//		.antMatchers(HttpMethod.GET)
//		.permitAll()
				.anyRequest().authenticated().and().exceptionHandling()
				.authenticationEntryPoint(this.jwtAuthenticationEntryPoint).and().sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS);

		http.addFilterBefore(this.jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
	}

	/**
	 * This method is used to encode the password.
	 */
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {

		auth.userDetailsService(this.customUserDetailService).passwordEncoder(passwordEncoder());

	}

	/**
	 * This method uses a password encoder called as BCryptPasswordEncoder because
	 * it has a strong hashing function.
	 * 
	 * @return An instance of type PasswordEncoder(BCrypt).
	 */
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

}
