<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Ajout</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
<style>
	body{
		max-width:1000px;
	}
	h2 {
		 font-size: 2.5rem;
		  font-weight: bold;
		  color: #333333;
		  text-align: center;
		  margin-top: 20px;
		  margin-bottom: 20px;
		  font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
	}
</style>
</head>
<body>
	<h2>Ajouter une Citation</h2>
	<div class="card mt-4">
		<div class="card-body">
			<form method="post" action="${pageContext.request.contextPath}/CitationServlet">
				<input type="hidden" name="choix" value="ajout">
				<div class="mb-3">
					<label class="form-label">ID Citation:</label> <input type="number" name="idcitation" class="form-control" required><br><br>
				</div>
				<div class="mb-3">
					<label class="form-label">Auteur:</label> <input type="text" name="auteur" class="form-control" required><br><br>
				</div>
				<div class="mb-3">
					<label class="form-label">Citation:</label> <textarea name="citation" class="form-control" required></textarea><br><br>
				</div>
				<div class="mb-3">
					<label class="form-label">Année:</label> <input type="number" name="annee" class="form-control" required><br><br>
				</div>
				<input type="submit" value="Ajouter" class="btn btn-primary">
			</form>
		</div>
	</div>
</body>
</html>