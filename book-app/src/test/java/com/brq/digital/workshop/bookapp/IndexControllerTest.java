package com.brq.digital.workshop.bookapp;

import javax.servlet.http.HttpSession;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.BeanFactory;

import mockit.Injectable;
import mockit.Tested;
import mockit.integration.junit4.JMockit;

@RunWith(JMockit.class)
public class IndexControllerTest {
	
	@Injectable
	private BeanFactory beanFactory;
	
	@Injectable
	private HttpSession httpSession;
		
	@Tested(fullyInitialized = true)
	private IndexController indexController;
	
	@Test
	public void deveIndex(){
		indexController.index();
	}
	
}