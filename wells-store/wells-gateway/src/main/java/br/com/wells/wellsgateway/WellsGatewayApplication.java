package br.com.wells.wellsgateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
public class WellsGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(WellsGatewayApplication.class, args);
	}

}
