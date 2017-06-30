package com.brq.digital.workshop.bookrest;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("books")
public class BookController {
	
	@Autowired
	BookRepository bookRepository;
    
    @RequestMapping(method= RequestMethod.GET)
    public List<Book> list() {
        return bookRepository.findAll();
    }
    
    @RequestMapping(value="{id}", method= RequestMethod.GET)
    public Book findById( @PathVariable("id") Long bookId ) {
        return bookRepository.findOne(bookId);
    }
    
}