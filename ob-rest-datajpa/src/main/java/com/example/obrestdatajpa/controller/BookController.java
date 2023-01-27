package com.example.obrestdatajpa.controller;

import com.example.obrestdatajpa.entities.Book;
import com.example.obrestdatajpa.repository.BookRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class BookController {
    // Atributos
    private final BookRepository bookRepository;
    private final Logger log = LoggerFactory.getLogger(BookController.class); // muestra los mensajes con colores hora, etc

    //constructores
    public BookController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    //CRUD sobre la entidad book

    //Buscar todos los libros(lista libros

    /**
     * Buscar Todos los libros que hay en base de datos
     * https://localhost:8081/api/books
     * @return
     */
    @GetMapping("/api/books")
    public List<Book> findBooks(){
        //recuperar y devovle rlos libros de base de datos
        return bookRepository.findAll();
    }

    /**
     * Request
     * Response
     * @param id
     * @return
     */

    //Buscar un solo libro en base de datos
    @GetMapping("/api/books/{id}")
    public ResponseEntity<Book> findById(@PathVariable Long id){
         Optional<Book> bookOpt =bookRepository.findById(id);

         //opcion 1
        return bookOpt.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
        /*
         if(bookOpt.isPresent())
            return ResponseEntity.ok(bookOpt.get());
        else
            return ResponseEntity.notFound().build();
        //opcion 2
        return bookOpt.orElse(null);
         */


    }

    /**
     * Crear un nuevo libro en base de datos
     * @param book
     * @param headers
     * @return
     */
    //guardar un libro en base de datos
    @PostMapping("/api/books")
    public ResponseEntity<Book> create(@RequestBody Book book, @RequestHeader HttpHeaders headers){
        System.out.println(headers.get("User-Agent"));// mira quien envio la peticion
        //guardar el lbro recicibdo por parametro en base de datos
        if(book.getId() != null ){
            log.warn("trying to create a book with id");
            System.out.println("trying to create a book with id");
          return ResponseEntity.badRequest().build();
        }
        Book result = bookRepository.save(book);
       return  ResponseEntity.ok(result);
    }

    /**
     * Actualizar un libro exisitente por base de datos
     * @return = badRequest or 200
     */
    @PutMapping("/api/books")
    public ResponseEntity<Book> update(@RequestBody Book book){

        if(book.getId() == null){//si no tiene id quiere decir que si es una creacion
            log.warn("trying to update a non existing book");
            return ResponseEntity.badRequest().build();
        }
        if(!bookRepository.existsById(book.getId())){
            log.warn("trying to update a non existing book");
            return ResponseEntity.notFound().build();
        }
        //el proceso de actualizacion
        Book result = bookRepository.save(book);
        return ResponseEntity.ok(result).ok(result);

    }

    //borrarun libro de la base de datos
    @DeleteMapping("/api/books/{id}")
    public ResponseEntity<Book> delete(@PathVariable Long id){
        if(!bookRepository.existsById(id)){//se gestiona si no existe el id
            log.warn("Trying to delete a non existing book");
            return ResponseEntity.notFound().build();
        }
        bookRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
    //Borrar Todos los libros de la base de datos
    @DeleteMapping("/api/books")
    public ResponseEntity<Book> deleALL(){
        log.info("REST request deleting all books");
        bookRepository.deleteAll();
        return ResponseEntity.noContent().build();
    }


}
