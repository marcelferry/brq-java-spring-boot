package com.brq.digital.workshop.bookapp;

import static org.junit.Assert.assertNotNull;

import javax.servlet.http.HttpSession;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.web.servlet.ModelAndView;

import com.brq.digital.workshop.bookservice.BookService;

import mockit.Injectable;
import mockit.Tested;
import mockit.integration.junit4.JMockit;

@RunWith(JMockit.class)
public class BookControllerTest {
	
	@Injectable
	private BeanFactory beanFactory;
	
	@Injectable
	private HttpSession httpSession;
		
	@Tested(fullyInitialized = true)
	private BookController bookController;
	
	@Injectable
	private BookService bookService;
	
	
	@Test
	public void devefindById(){
			ModelAndView modelAndView = bookController.obtemLivro(1);
			assertNotNull(modelAndView);
	}
	
}