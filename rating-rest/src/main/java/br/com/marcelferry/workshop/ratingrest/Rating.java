package br.com.marcelferry.workshop.ratingrest;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Rating {

	@Id
	private Long id;
	@Column(name = "book_id")
	private Long bookId;
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