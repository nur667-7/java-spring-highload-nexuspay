package kz.iitu.nexuspay;

import org.springframework.boot.SpringApplication;

public class TestNexuspayApplication {

	public static void main(String[] args) {
		SpringApplication.from(NexuspayApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
