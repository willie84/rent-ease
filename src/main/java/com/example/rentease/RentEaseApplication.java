
package com.example.rentease;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
@EnableJpaRepositories("com.example.rentease.repository")
@EntityScan("com.example.rentease.model")
public class RentEaseApplication {
   public static void main(String[] args) {
      SpringApplication.run(RentEaseApplication.class, args);
   }
//   @Bean
//   public PasswordEncoder passwordEncoder() {
//      return new BCryptPasswordEncoder();
//   }

}
