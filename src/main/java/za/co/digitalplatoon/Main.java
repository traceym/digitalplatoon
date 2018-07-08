package za.co.digitalplatoon;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;


//@SpringBootApplication(scanBasePackages={"com.websystique.springboot"})// same as 
@Configuration 
@EnableAutoConfiguration 
@ComponentScan
public class Main {

	public static void main(String[] args) {
		SpringApplication.run(Main.class, args);
	}
}
