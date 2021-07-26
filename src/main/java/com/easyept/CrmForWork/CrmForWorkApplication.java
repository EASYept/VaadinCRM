package com.easyept.CrmForWork;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorMvcAutoConfiguration;

@SpringBootApplication (exclude = ErrorMvcAutoConfiguration.class) // Spring’s MVC error handler. Since Vaadin 14 it causes strange reload behavior.
public class CrmForWorkApplication {

	public static void main(String[] args) {
		SpringApplication.run(CrmForWorkApplication.class, args);
	}

}
