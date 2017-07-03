<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<html lang="pt-br" class="no-js">	
	<head>	
		<title>Livraria</title>
		<meta name="robots" content="noindex,nofollow" />
		<meta name="viewport" content="width=device-width" />	
		
		<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">

<!-- Optional theme -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">

<!-- Latest compiled and minified JavaScript -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>	
		
		<meta charset="utf-8" />
		<meta name="description" content="" />
		<meta name="keywords" content="" />	
	</head>
	<body>
		<div  class="container master-container">
			<form role="form">
			
				  
			  <div class="form-group">
			    <label class="control-label" for="id">id</label>
			    <input type="text" class="form-control" id="id" placeholder="Entre o id" value="${book.id}">
			  </div>
			
			  <div class="form-group">
			    <label class="control-label" for="titulo">titulo</label>
			    <input type="text" class="form-control" id="titulo" placeholder="Entre o titulo" value="${book.titulo}">
			  </div>
			  
			  <div class="form-group">
			    <label class="control-label" for="autor">autor</label>
			    <input type="text" class="form-control" id="autor" placeholder="Entre o autor" value="${book.autor}">
			  </div>
			  
			  <div class="form-group">
			    <label class="control-label" for="categoria">categoria</label>
			    <input type="text" class="form-control" id="categoria" placeholder="Entre o categoria" value="${book.categoria}">
			  </div>
			  
			  <div class="form-group">
			    <label class="control-label" for="preco">preco</label>
			    <input type="text" class="form-control" id="preco" placeholder="Entre o preco" value="${book.preco}">
			  </div>
			  
			  <div class="form-group">
			    <label class="control-label" for="dataCadastro">dataCadastro</label>
			    <input type="text" class="form-control" id="dataCadastro" placeholder="Entre o dataCadastro" value="${book.dataCadastro}">
			  </div>
		
		
			  <div class="form-group">
			    <div class="checkbox">
			      <label>
			        <input type="checkbox"> ativo
			      </label>
			    </div>
			  </div>
			</form>
		</div>		
	</body>
</html>