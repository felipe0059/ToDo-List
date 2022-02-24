package br.com.desafioviceri;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/")
@SpringBootApplication
public class TodolistApplication {

	@GetMapping
	public ModelAndView swaggerUi() {
		return new ModelAndView("redirect:swagger-ui.html");
	}

	public static void main(String[] args) {
		SpringApplication.run(TodolistApplication.class, args);
	}

}
