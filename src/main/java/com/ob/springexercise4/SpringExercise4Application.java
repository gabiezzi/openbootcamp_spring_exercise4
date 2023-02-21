package com.ob.springexercise4;

import com.ob.springexercise4.entity.Laptop;
import com.ob.springexercise4.repository.LaptopRepository;
import com.ob.springexercise4.service.impl.LaptopServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

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
