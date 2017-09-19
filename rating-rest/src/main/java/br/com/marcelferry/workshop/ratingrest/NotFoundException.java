package br.com.marcelferry.workshop.ratingrest;

public class NotFoundException extends ApiException {
	private int code;

	public NotFoundException(int code, String msg) {
		super(404, msg);
		this.code = code;
	}
}