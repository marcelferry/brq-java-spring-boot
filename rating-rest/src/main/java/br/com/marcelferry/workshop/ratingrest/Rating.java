package br.com.marcelferry.workshop.ratingrest;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import io.swagger.annotations.ApiModelProperty;

@Entity
public class Rating {

	@Id
	@ApiModelProperty(notes = "Identificador único da avalição", required = true)
	private Long id;
	@Column(name = "book_id")
	@ApiModelProperty(notes = "Identificador do livro ao qual a avaliação se refere", required = true)	
	private Long bookId;
	@ApiModelProperty(notes = "Valor da avaliação", required = true)	
	private int stars;

	public Rating() {
	}

	public Rating(Long bookId, int stars) {
		super();
		this.bookId = bookId;
		this.stars = stars;
	}

	public Rating(Long id, Long bookId, int stars) {
		super();
		this.id = id;
		this.bookId = bookId;
		this.stars = stars;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getBookId() {
		return bookId;
	}

	public void setBookId(Long bookId) {
		this.bookId = bookId;
	}

	public int getStars() {
		return stars;
	}

	public void setStars(int stars) {
		this.stars = stars;
	}

}