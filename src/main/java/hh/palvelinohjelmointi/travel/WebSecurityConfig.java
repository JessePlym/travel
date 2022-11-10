package hh.palvelinohjelmointi.travel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import hh.palvelinohjelmointi.travel.web.UserDetailServiceImpl;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableWebSecurity
public class WebSecurityConfig {

	@Autowired
	private UserDetailServiceImpl userDetailsServiceImpl;

	@Bean
	public SecurityFilterChain configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/api/**").hasAuthority("ADMIN").antMatchers("/css/**").permitAll()
				.antMatchers("/signup", "/saveuser", "/timetable", "/").permitAll().antMatchers("/h2-console/**")
				.permitAll().anyRequest().authenticated().and().csrf().ignoringAntMatchers("/h2-console/**").and()
				.headers().frameOptions().sameOrigin().and().csrf().disable().cors().and().formLogin()
				.defaultSuccessUrl("/timetable", true).permitAll().and().logout().logoutSuccessUrl("/timetable")
				.permitAll().and().httpBasic();
		return http.build();
	}

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsServiceImpl).passwordEncoder(new BCryptPasswordEncoder());
	}
}
