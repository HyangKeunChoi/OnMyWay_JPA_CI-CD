package mymind.mymind;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Slf4j
public class MymineApplication {

	public static void main(String[] args) {
		log.info("테스트 로그");
		SpringApplication.run(MymineApplication.class, args);
	}

}
