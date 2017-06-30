package com.brq.digital.workshop.bookservice;

import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

@Component
public class BookServiceImpl implements BookService {
	private static final Logger LOGGER = LogManager.getLogger("LOG_NOME");
	
	private WebTarget target;
	
	public BookServiceImpl() {
		target = ClientBuilder.newClient().target("http://localhost:8083");
	}
	
	public BookDTO obtemLivro(Integer bookId){
		
		Response response = target
				.path("books")
				.path(String.valueOf(bookId))
				//.queryParam("", "")
				.request()
				//.header("", "")
				.get();
		
		try{
			return response.readEntity(new GenericType<BookDTO>() {
		    });
		}catch(Exception e){
			LOGGER.info("BOOK", e);
			throw new RuntimeException("ERRO");
		}
		
	}

}
