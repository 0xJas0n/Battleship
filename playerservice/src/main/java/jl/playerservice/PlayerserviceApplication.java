package jl.playerservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class PlayerserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(PlayerserviceApplication.class, args);
	}

}
