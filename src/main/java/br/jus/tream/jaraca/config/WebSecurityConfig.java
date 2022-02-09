package br.jus.tream.jaraca.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired
	private DataSource dataSource;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.authorizeRequests()
				.anyRequest().authenticated()
			.and()
			    .formLogin(form -> form
			    		.loginPage("/login")
			    		.defaultSuccessUrl("/home", true)
			    		.permitAll()
			    )
			    .logout(logout -> logout.logoutUrl("/logout"))
			.csrf().disable();
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		
		auth.jdbcAuthentication()
		  .dataSource(dataSource)
		  .passwordEncoder(encoder);
		
				/* para criar usuarios para testes
				UserDetails user =
						 User.builder()
							.username("maria")
							.password(encoder.encode("maria"))
							.roles("USER")
							.build();
				
				auth.jdbcAuthentication()
				  .dataSource(dataSource)
				  .passwordEncoder(encoder)
				  .withUser(user);
				*/   
	}

	// usar o código abaixo apenas para testes não é recomendado em producao
	@Bean
	@Override
	public UserDetailsService userDetailsService() {
		UserDetails user =
			 User.withDefaultPasswordEncoder()
				.username("joao")
				.password("joao")
				.roles("USER")
				.build();
	   return new InMemoryUserDetailsManager(user); 
	}
	
	@Override
    public void configure(WebSecurity web) throws Exception {
        web
            .ignoring()
            .antMatchers("/h2-console/**");
    }
}
