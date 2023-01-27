package com.example.obrestdatajpa.controller;

import com.example.obrestdatajpa.entities.Book;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.boot.web.client.RestTemplateBuilder;
import java.util.Arrays;
import java.util.List;


import org.springframework.http.*;



import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)//para que se pruebe en un puerto aleatorio
class BookControllerTest {

    private TestRestTemplate testRestTemplate;

    @Autowired
    private RestTemplateBuilder restTemplateBuilder;//funciona aunque bote error, que raro

    @LocalServerPort
    private int port;

    @BeforeEach
    void setUp() {
        restTemplateBuilder = restTemplateBuilder.rootUri("http://localhost:" + port);
        testRestTemplate = new TestRestTemplate(restTemplateBuilder);
    }


    @DisplayName("Comprobar hola mundo desde controladores Spring REST")//mejora la legibilidad de los resultados
    @Test
    void hello() {
        ResponseEntity<String> response  =
                testRestTemplate.getForEntity("/hola", String.class);//lo que vamos a probar

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(200, response.getStatusCodeValue());
        assertEquals("Hola Mundo que tal vamos!! Hasta luego", response.getBody());
    }

    @Test
    void findBooks() {
        ResponseEntity<Book[]> response =
                testRestTemplate.getForEntity("/api/books",Book[].class);
            assertEquals(HttpStatus.OK,response.getStatusCode());
            assertEquals(200,response.getStatusCodeValue());
            List<Book> books = Arrays.asList(response.getBody());//se castea con el emtodo Arrays.asList el array de books
        System.out.println(books.size());
    }

    @Test
    void findById() {
        ResponseEntity<Book> response  =
                testRestTemplate.getForEntity("/api/books/1", Book.class);//lo que vamos a probar

        assertEquals(HttpStatus.NOT_FOUND,response.getStatusCode());
    }

    @Test
    void create() {
        ///indicar unas cabeceras par aindicar que se envia JSON
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);//taer JSON de las cabeceras
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));// devuelve arrays

        String json = """
                {  
                    "tittle": "Libro Creado desde Spring Test",
                    "author": "no se xxd",
                    "pages": 650,
                    "price": 19.99,
                    "releaseDate": "1997-05-02",
                    "online": false
                }
                """;
        HttpEntity<String> request = new HttpEntity<>(json,headers);//creamos desde java una peticion
        //ejecutamos la Request
        ResponseEntity<Book> response = testRestTemplate.exchange("/api/books", HttpMethod.POST,request,Book.class);
        Book result = response.getBody();

        assertEquals(1L, result.getId());
        assertEquals("Libro Creado desde Spring Test", result.getTittle());
    }
}