package br.com.PetRegistry.Util;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = "br.com.PetRegistry")
@EnableJpaRepositories(basePackages = "br.com.PetRegistry.repository")
@EntityScan(basePackages = "br.com.PetRegistry.model")
public class PetRegistryApplication {

    public static void main(String[] args) {
        SpringApplication.run(PetRegistryApplication.class, args);
    }
}

