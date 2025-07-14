<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="net.java.model.Citation"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>List</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
<style>
	body {
		margin: 10px;
	}
	h1 {
		 font-size: 2.5rem;
		  font-weight: bold;
		  color: #333333;
		  text-align: center;
		  margin-top: 20px;
		  margin-bottom: 20px;
		  font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
	}
	#ajout {
		text-align: center;
	}
</style>
</head>
<body>
	<h1>Liste des Citations</h1>
	<br>
	<div id="ajout">
	<a href="http://localhost:8080/TP4/Views/AjouterCitation.jsp?choix=ajout" class="btn btn-primary">Ajouter une
		nouvelle citation</a>
	</div>
	<br>
	<br>
	<table class="table table-bordered text-center">
	  <thead class="table-light">
	    <tr>
	      <th scope="col">ID</th>
	      <th scope="col">Auteur</th>
	      <th scope="col">Citation</th>
	      <th scope="col">Année</th>
	      <th scope="col">Actions</th>
	    </tr>
	  </thead>
		<% 
			@SuppressWarnings("unchecked")
           	List<Citation> vc = (List<Citation>) request.getAttribute("vc");
           	if (vc != null && !vc.isEmpty()){
           		for(Citation c: vc){
        %>
		<tr>
			<td scope="row"><%=c.getIdcitation()%></td>
			<td><%=c.getNomH()%></td>
			<td><%=c.getCitationcol()%></td>
			<td><%=c.getAnne()%></td>
			<td><a
				href="${pageContext.request.contextPath}/CitationServlet?choix=modifier&id=<%=c.getIdcitation()%>" class="btn btn-primary">Modifier</a> <a
				href="${pageContext.request.contextPath}/CitationServlet?choix=supprimer&id=<%=c.getIdcitation()%>" class="btn btn-warning">Supprimer</a></td>
		</tr>
		<%
            		}
            	}
               %>
	</table>
</body>
</html>