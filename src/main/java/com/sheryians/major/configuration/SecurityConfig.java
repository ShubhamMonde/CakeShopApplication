package com.sheryians.major.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.sheryians.major.model.CustomUserDetail;
import com.sheryians.major.service.CustomUserDetailService;


@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	@Autowired
	GoogleOAuth2SuccessHandler googleOAuth2SuccessHandler;
	@Autowired
	CustomUserDetailService customUserDetailService;
	@Override
	protected void configure(HttpSecurity http) throws Exception{
		http

		//authentication regarding routes started
		.authorizeRequests()
		.antMatchers("/","/shop/**","/register").permitAll()//it will give access without authentication to pages belongs to these url's but will restrict the access to all the other URLs without authentication
															// '/shop/**' means any URL starting with '/shop' and contains any address after that.
		
		.antMatchers("/admin/**").hasRole("ADMIN")//ज्याचा role admin असेल फक्त त्यालाच permission to access /admin/** mapped pages.
		.anyRequest()
		.authenticated()   
		//authentication regarding routes ended
		
		//authentication related to login form started
		.and()
		.formLogin()
		.loginPage("/login").permitAll()
		.failureUrl("/login?error=true")//if login fet's fail 
		.defaultSuccessUrl("/")//if login get's successfull
		.usernameParameter("email")
		.passwordParameter("password")
		//authentication related to login form ended
		

		//authentication related to google login started
		.and()
		.oauth2Login()
		.loginPage("/login")
		.successHandler(googleOAuth2SuccessHandler)
		//authentication related to google login ended
		

		//authentication related to logout started
		.and()
		.logout()
		.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
		.logoutSuccessUrl("/login")
		.invalidateHttpSession(true)// to invalidate cookies
		.deleteCookies("JESESSIONID")//to delete that cookies
		//authentication related to logout ended

		//authentication related to exception handling started
		.and()
		.exceptionHandling()
		//authentication related to exception handling ended
		
		.and()
		.csrf()
		.disable();
		
	//http.headers().frameOptions().disable(); //when you use h2 database only then use this line
		
	
	}
	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}//now our password is check in encrypted form 
	
	
	//जी पण आपण information देतोय त्या आधारे user retrieve कर   आणि एक CustomUser चा object तयार करून त्याला pass कर.
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception{
		auth.userDetailsService(customUserDetailService);
	}
	
	
	@Override
	public void configure(WebSecurity web) throws Exception{
		web.ignoring().antMatchers("/resources/**,/static/**,/images/**,/productImages/**,/css/**,/js/**");
	}

}







