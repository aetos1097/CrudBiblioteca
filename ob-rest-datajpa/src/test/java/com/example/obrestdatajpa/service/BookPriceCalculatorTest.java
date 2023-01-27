package com.example.obrestdatajpa.service;

import com.example.obrestdatajpa.entities.Book;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class BookPriceCalculatorTest {

    /**
     * Idea Principal de los tes
     */
    @Test
    void claculatePrice() {
        //Configuracion de la prueba
        Book book = new Book(1L,"Harry Potter","JKRobins", 1000,50.88, LocalDate.now(),true);
        BookPriceCalculator calculator = new BookPriceCalculator();

        // Se ejecuta el comportamiento a testear
        double price = calculator.claculatePrice(book);
        System.out.println(price);

        //comprobacciones aserciones
        assertTrue(price > 0);
        assertEquals(58.870000000000005, price);
    }
}