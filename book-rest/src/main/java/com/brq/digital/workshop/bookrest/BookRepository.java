package com.brq.digital.workshop.bookrest;

import java.math.BigDecimal;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.RequestParam;

@Transactional
public interface BookRepository extends JpaRepository<Book, Long> {
	
	List<Book> findByTitulo(@Param("titulo") @RequestParam("titulo") String titulo);
	List<Book> findByAutor(@Param("autor") @RequestParam("autor") String autor);
	List<Book> findByCategoria(@Param("categoria") @RequestParam("categoria") String categoria);
	List<Book> findByPrecoGreaterThanEqualAndPrecoLessThanEqual(@Param("minimo") @RequestParam("minimo") BigDecimal minimo, @Param("maximo") @RequestParam("maximo") BigDecimal maximo);

}