package com.product;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Aplicación Spring
 */
@SpringBootApplication(scanBasePackages={"com.product"})
public class ProductApplication {

	/**
	 * Main
	 * @param args argumentos
	 */
	public static void main(String[] args) {
		SpringApplication.run(ProductApplication.class, args);
	}

}
