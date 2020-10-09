package caceresenzo.oniichan;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

import io.github.cdimascio.dotenv.Dotenv;

@SpringBootApplication(scanBasePackages = "caceresenzo.oniichan")
@EnableAutoConfiguration
@EnableConfigurationProperties
@EnableScheduling
@EnableJpaRepositories("caceresenzo.oniichan")
@ComponentScan("caceresenzo.oniichan")
@EntityScan("caceresenzo.oniichan")
public class OniichanApplication {
	
	public static void main(String[] args) {
		Dotenv.configure()
				.systemProperties()
				.load();
		
		SpringApplication.run(OniichanApplication.class, args);
	}
	
}