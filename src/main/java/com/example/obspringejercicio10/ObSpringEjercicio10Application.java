
package com.example.obspringejercicio10;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.example.obspringejercicio10.Interface.LaptopRepository;
import com.example.obspringejercicio10.Model.Laptop;

@SpringBootApplication
public class ObSpringEjercicio10Application {

	public static void main(String[] args) {
		ApplicationContext context  = SpringApplication.run(ObSpringEjercicio10Application.class, args);
		LaptopRepository LaptopRepository = context.getBean(LaptopRepository.class);
		Laptop Asus = new Laptop(null, "Asus", "xt1203", 259.36);
		Laptop Lenovo = new Laptop(null, "Lenovo", "pt89-8", 209.36);
		LaptopRepository.save(Asus);
		LaptopRepository.save(Lenovo);
		System.out.println(LaptopRepository.count());
	}

}
