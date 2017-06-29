package com.brq.digital.treinamento.spring.bookrest;

import javax.servlet.http.HttpSession;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.BeanFactory;

import com.brq.digital.workshop.bookrest.HelloController;

import mockit.Injectable;
import mockit.Tested;
import mockit.integration.junit4.JMockit;

@RunWith(JMockit.class)
public class HelloControllerTest {
	
	@Injectable
	private BeanFactory beanFactory;
	
	@Injectable
	private HttpSession httpSession;
		
	@Tested(fullyInitialized = true)
	private HelloController helloController;
	
	@Test
	public void deveIndex(){
		helloController.index();
	}
	
}