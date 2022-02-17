package com.whiteam.init;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication().withUser("user1").password("{noop}user1") // lo de {noop} se pone para no obligar
																				// a usar mecanismo de encriptación
				.roles("USER").and().withUser("admin").password("{noop}admin").roles("USER", "ADMIN");

		/*
		 * la seguiente configuración será para el caso de usuarios en una base de datos
		 * auth.jdbcAuthentication().dataSource(dataSource)
		 * .usersByUsernameQuery("select username, password, enabled" +
		 * " from users where username=?")
		 * .authoritiesByUsernameQuery("select username, authority " +
		 * "from authorities where username=?");
		 */
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable().authorizeRequests()
//solo los miembros del rol admin podrán realizar altas
//y para acceder la lista de cursos, tendrán que estar autenticados
				.antMatchers(HttpMethod.POST, "/curso").hasRole("ADMIN").antMatchers("/cursos").authenticated()
//.antMatchers("/curso/**").authenticated()
				.and().httpBasic();
	}

}