package kr.co.tripct.tripctserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class TripComesTrueServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(TripComesTrueServerApplication.class, args);
	}

}
