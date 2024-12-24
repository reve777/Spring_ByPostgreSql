package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.example.demo.componnet.CustomAuthenticationFailureHandler;

@Configuration
public class SecurityConfiguration {

	private final UserDetailsService userDetailsService;

	private final CustomAuthenticationFailureHandler customAuthenticationFailureHandler;

	public SecurityConfiguration(UserDetailsService userDetailsService,
			CustomAuthenticationFailureHandler customAuthenticationFailureHandler) {
		this.userDetailsService = userDetailsService;
		this.customAuthenticationFailureHandler = customAuthenticationFailureHandler;
	}

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http

				.formLogin(form -> form.loginProcessingUrl("/login") //
						.loginPage("/loginpage") //
						.successForwardUrl("/userdetail") //
						.failureHandler(customAuthenticationFailureHandler) //
				)
//                .authorizeHttpRequests(auth -> auth //允許所有角色帳號頁面
//                        .anyRequest().permitAll()
//                )
				.authorizeHttpRequests(auth -> auth
						.requestMatchers("/loginpage", "/css/**", "/js/**", "/images/**", "/fail", "/loginerror") //
						.permitAll() //
						.anyRequest() //
						.authenticated() //
				)

				.logout(logout -> logout //
						.logoutUrl("/demo/logout") //
						.deleteCookies("JSESSIONID") //
						.logoutSuccessUrl("/loginpage") //
						.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
				)
				

				.rememberMe(rememberMe -> rememberMe.userDetailsService(userDetailsService) //
						.tokenValiditySeconds(60 * 60 * 24)) //
				.csrf(csrf -> csrf //
						.ignoringRequestMatchers("/demo/logout") // 忽略登出 URL 的 CSRF 保護 //
				);

		return http.build();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
			throws Exception {
		return authenticationConfiguration.getAuthenticationManager();
	}
}
