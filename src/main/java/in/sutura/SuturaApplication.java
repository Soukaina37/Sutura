package in.sutura;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan({"in.sutura.request"})
@EntityScan("in.sutura.entities")
@EnableJpaRepositories("in.sutura.repositories")
public class SuturaApplication {

	public static void main(String[] args) {
		SpringApplication.run(SuturaApplication.class, args);
	}

}
