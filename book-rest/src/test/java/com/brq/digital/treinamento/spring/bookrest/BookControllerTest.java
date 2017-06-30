package com.brq.digital.treinamento.spring.bookrest;

import javax.servlet.http.HttpSession;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.BeanFactory;

import com.brq.digital.workshop.bookrest.BookController;

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
	
	@Test
	public void deveList(){
		bookController.list();
	}
	
}