package br.com.asw.si.voluntarios.configs;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private DataSource dataSource;
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	private static final String[] PUBLIC_MATCHERS = {
			"/login", "/webjars/**", "/user/signup","/h2-console/**"
			
	};
	
	private static final String[] PUBLIC_MATCHERS_GET = {
	};

	private static final String[] PUBLIC_MATCHERS_POST = {
			"/user"
	};
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.authorizeRequests()
			.antMatchers(HttpMethod.POST, PUBLIC_MATCHERS_POST).permitAll()
			.antMatchers(HttpMethod.GET, PUBLIC_MATCHERS_GET).permitAll()
			.antMatchers(PUBLIC_MATCHERS).permitAll()
				.anyRequest().authenticated()
				.and()
			.formLogin()
				.loginPage("/login")
				.and()
			.logout(l -> l
	                    .logoutSuccessUrl("/login").permitAll()
	                )
			.csrf(c -> c
	                    .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
	                )
	        .oauth2Login()
	        .loginPage("/login");
		
		  http.csrf().disable(); 
		  http.headers().frameOptions().disable();
		 
	}
	
	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
	}

	/*
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth)
	  throws Exception {
		User user = new User("Administrador", "admin@admin.com","admin",passwordEncoder().encode("123"));
	    auth.jdbcAuthentication()
	      .dataSource(dataSource)
	      .withDefaultSchema()
	      .withUser(user.getUsername())
	      .password(user.getPassword())
	      .roles("USER");
	}
	*/

	@Bean
	@Autowired
	public BCryptPasswordEncoder passwordEncoder() {
	    return new BCryptPasswordEncoder();
	}
	
	/*
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
	   // Adding in memory User for form based login
		auth.inMemoryAuthentication()
		.passwordEncoder(NoOpPasswordEncoder.getInstance()) 
		.withUser("admin")
		.password("admin")
		.roles("USER");	
	}
	*/
	/*
	@Bean
	@Override
	public UserDetailsService userDetailsService() {
		UserDetails user =
			 User.withDefaultPasswordEncoder()
				.username("user")
				.password("password")
				.roles("USER")
				.build();

		return new InMemoryUserDetailsManager(user);
	}
	*/
	
}