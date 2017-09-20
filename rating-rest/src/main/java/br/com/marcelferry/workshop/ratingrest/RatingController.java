package br.com.marcelferry.workshop.ratingrest;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("ratings")
public class RatingController {
	
	@Resource
	RatingRepository ratingRepository;
	
	@ApiOperation(value = "Lista todas as Avaliações", notes = "Lista todas as Avaliações", response = Rating.class, responseContainer = "List" )
	@ApiResponses(value = { 
			@ApiResponse(code = 200, message = "Avaliações Listadas com sucesso"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
		    @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
		    @ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
	@GetMapping
	public List<Rating> findAllRatings() {
		return ratingRepository.findAll();
	}
	
	@ResponseStatus(HttpStatus.CREATED)
	@ApiOperation(value = "Insere uma Avaliação", notes = "Insere uma Avaliação", response = Rating.class )
	@ApiResponses({
        @ApiResponse(code = 201, message = "Inclusão com sucesso de uma avaliação")
    })
	@PostMapping
	public Rating insertRatings(@RequestBody Rating rating) {
		return ratingRepository.save(rating);
	}
	
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@ApiOperation(value = "Exclui uma Avaliação", notes = "Exclui uma Avaliação")
    @ApiResponses({
            @ApiResponse(code = 204, message = "Exclusão com sucesso de uma avaliação")
    })
	@DeleteMapping("{ratingId}")
	public void deleteRating(@PathVariable("ratingId") Long ratingId) {
		ratingRepository.delete(ratingId);
	}
	
	
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@ApiOperation(value = "Atualizar Avaliação", notes = "Atualizar Avaliação")
    @ApiResponses({
            @ApiResponse(code = 204, message = "Atualização com sucesso de uma avaliação")
    })
	@PutMapping("{ratingId}")
	public void updateRating(@PathVariable("ratingId") Long ratingId, @RequestBody Rating rating) {
		ratingRepository.delete(ratingId);
	}

	
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(value = "Obtém uma Avaliação", notes = "Obtém uma Avaliação")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Existe uma avaliação")
    })
	@GetMapping("search/findByBookId")
	public List<Rating> findByBookId(@RequestParam("bookId") Long bookId) {
		return ratingRepository.findByBookId(bookId);
	}
	
}