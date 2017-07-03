package com.brq.digital.workshop.bookapp;

import java.io.Serializable;

import org.springframework.web.servlet.ModelAndView;

public abstract class AbstractController implements Serializable  {

	
	/**
	 * Metodo não imutavel, alterar as propriedades do parametro model.
	 * 
	 * @param model
	 * @param mensagem
	 * @return
	 */
	protected ModelAndView chamarPaginaErro(ModelAndView model,String mensagem){
		
		model.setViewName("erro/erro");
		model.addObject("exception", mensagem);
		return model;
	}
	
	/**
	 * Metodo não imutavel, alterar as propriedades do parametro model.
	 * 
	 * @param model
	 * @param msg
	 * @return
	 */
	protected ModelAndView chamarPaginaAviso(ModelAndView model,String msg){
		
		model.setViewName("erro/aviso");
		model.addObject("msg",msg);
		
		return model;
	}
}