package com.insys.trapps;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author Muhammad Sabir
 *
 */
@SpringBootApplication
@EnableTransactionManagement
public class TrappsApiApplication {

    public static void main(String[] args) {
	SpringApplication.run(TrappsApiApplication.class, args);
    }
}
