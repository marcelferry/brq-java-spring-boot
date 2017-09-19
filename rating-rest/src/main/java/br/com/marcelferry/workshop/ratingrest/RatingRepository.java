package br.com.marcelferry.workshop.ratingrest;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

public interface RatingRepository extends JpaRepository<Rating, Long> {
	List<Rating> findByBookId(@Param("bookId") Long bookId);
}