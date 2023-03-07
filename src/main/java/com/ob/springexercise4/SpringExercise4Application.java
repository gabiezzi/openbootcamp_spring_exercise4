package com.ob.springexercise4;

import com.ob.springexercise4.entity.Laptop;
import com.ob.springexercise4.repository.LaptopRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;


@SpringBootApplication
public class SpringExercise4Application {

	public static void main(String[] args) throws Exception {

		ApplicationContext context = SpringApplication.run(SpringExercise4Application.class, args);

		LaptopRepository laptopRepository = context.getBean(LaptopRepository.class);


		Laptop laptop = new Laptop("gigabyte","i7","32gb","4Tb",false);
		Laptop laptop2 = new Laptop("Toshiba","ryzen 7","32gb","4Tb",false);
		Laptop laptop3 = new Laptop("Hp","i5","8gb","256gb",true);

		laptopRepository.save(laptop);
		laptopRepository.save(laptop2);
		laptopRepository.save(laptop3);





	}

}
