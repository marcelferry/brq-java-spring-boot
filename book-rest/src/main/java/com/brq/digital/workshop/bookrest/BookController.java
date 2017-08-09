package com.brq.digital.workshop.bookrest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("books")
public class BookController {
	
	@Autowired
	BookRepository bookRepository;
    
//    @RequestMapping(method= RequestMethod.GET)
//    public List<Book> list() {
//        return bookRepository.findAll();
//    }
   
//    @RequestMapping(value="{id}", method= RequestMethod.GET)
//    public Book findById( @PathVariable("id") Long bookId ) {
//        return bookRepository.findOne(bookId);
//    }
	
//	  @RequestMapping(method= RequestMethod.POST)
//	  @ResponseStatus(HttpStatus.CREATED)
//	  public Book insert( @RequestBody Book book) {
//	      return bookRepository.save(book);
//	  }
    
}