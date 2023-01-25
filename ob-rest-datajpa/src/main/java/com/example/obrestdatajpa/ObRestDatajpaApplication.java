package com.example.obrestdatajpa;

import com.example.obrestdatajpa.entities.Book;
import com.example.obrestdatajpa.repository.BookRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.time.LocalDate;

@SpringBootApplication
public class ObRestDatajpaApplication {

	public static void main(String[] args) {

		ApplicationContext context = SpringApplication.run(ObRestDatajpaApplication.class, args);
		BookRepository repository = context.getBean(BookRepository.class);

		//CRUD

		//Crear libro
		Book book1 = new Book(null,"Homo Deus","Yuval Noah",450,29.99, LocalDate.of(2019,12,12),true);
		Book book2 = new Book(null,"Homo sapiens","Yuval Noah",450,19.99, LocalDate.of(2013,11,2),true);

		// almacenar un libro
		System.out.println("Num libros en db--> "+repository.findAll().size());
		repository.save(book1);
		repository.save(book2);

		//Recuperar un libro
		System.out.println("Num libros en db--> "+repository.findAll().size());

		//borrar un libro

		//repository.deleteById(1L);
		System.out.println("Num libros en db--> "+repository.findAll().size());

	}

}
