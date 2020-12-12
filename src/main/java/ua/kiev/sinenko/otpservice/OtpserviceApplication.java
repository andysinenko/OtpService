package ua.kiev.sinenko.otpservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import ua.kiev.sinenko.otpservice.otp.properties.OtpGeneratorProperties;

/*import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;*/

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
public class OtpserviceApplication /*extends WebSecurityConfigurerAdapter*/ {
	@Autowired
	private OtpGeneratorProperties otpGeneratorProperties;

	public static void main(String[] args) {
		SpringApplication.run(OtpserviceApplication.class, args);
	}

	/*@Override
	protected void configure(HttpSecurity http) throws Exception{
		http.authorizeRequests().antMatchers("/otp").permitAll();
	}*/

}
