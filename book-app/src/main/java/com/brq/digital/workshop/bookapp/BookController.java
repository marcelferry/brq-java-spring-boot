package com.brq.digital.workshop.bookapp;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.brq.digital.workshop.bookservice.BookDTO;
import com.brq.digital.workshop.bookservice.BookService;

@Controller
@RequestMapping("books")
public class BookController extends AbstractController {
	
	private static final Logger LOG = LogManager.getLogger("WEB");
	
	@Autowired
	BookService bookService;
	
	@RequestMapping("{bookId}")
	public ModelAndView obtemLivro(@PathVariable("bookId") Integer bookId){
		ModelAndView view = new ModelAndView();
		
		try{
			BookDTO book = bookService.obtemLivro(bookId);
			view = new ModelAndView("/livro");
			view.addObject("book",book);
		} catch (Exception e) {
			LOG.error(e.getMessage(),e);
			return this.chamarPaginaAviso(view, e.getMessage());
		}	
		return view;
	}

}
