package net.java.controller;
import java.util.List;
import net.java.dao.CitationDao;
import net.java.model.Citation;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.*;
import java.sql.*;
@WebServlet("/CitationServlet")

public class CitationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		String choix=request.getParameter("choix");
		if (choix == null || choix=="") {
            choix="affiche";
        }
		switch(choix) {
			case "ajout":
				try {
					int idcitation = Integer.parseInt(request.getParameter("idcitation"));
					String nomH = request.getParameter("auteur");
					String citationText = request.getParameter("citation");
					int annee = Integer.parseInt(request.getParameter("annee"));
					Citation citation = new Citation(idcitation, nomH,citationText, annee);
					CitationDao dao = new CitationDao();
					boolean success = dao.ajouterCitation(citation);
					if (success) {
						List<Citation> vc = dao.afficherCitation();
						request.setAttribute("vc", vc);
						request.getRequestDispatcher("/Views/list.jsp").forward(request, response);
					} else {
						response.getWriter().write("Erreur lors de l'ajout de la citation.");
					}
				} catch (NumberFormatException e) {
					response.getWriter().write("Erreur de format : " + e.getMessage());
				} catch (SQLException e) {
					throw new ServletException("Erreur de la base de données", e);
				}
				break;
			case "affiche":
		        try {
		        	CitationDao dao = new CitationDao();
		        	List<Citation> vc= dao.afficherCitation();
		        	request.setAttribute("vc", vc);
		        	request.getRequestDispatcher("/Views/list.jsp").forward(request, response);		        	
		        } catch (NumberFormatException e) {
					response.getWriter().write("Erreur de format : " + e.getMessage());
				} catch (SQLException e) {
					throw new ServletException("Erreur de la base de données", e);
				}
				break;
			case "modifier":
			    if (request.getMethod().equalsIgnoreCase("GET")) {
			        try {
			            String idcitation = request.getParameter("id");
			            int id = Integer.parseInt(idcitation);
			            CitationDao dao = new CitationDao();
			            Citation citation = dao.getCitationById(id);
			            if (citation != null) {
			                request.setAttribute("citation", citation);
			                request.getRequestDispatcher("/Views/ModifierCitation.jsp").forward(request, response);
			            } else {
			                response.getWriter().write("Citation introuvable !");
			            }
			        } catch (NumberFormatException | SQLException e) {
			            response.getWriter().write("Erreur : " + e.getMessage());
			        }
			    } else if (request.getMethod().equalsIgnoreCase("POST")) {
			        try {
			            String idcitation = request.getParameter("id");
			            int id = Integer.parseInt(idcitation);
			            String nomH = request.getParameter("auteur");
			            String citationText = request.getParameter("citation");
			            int annee = Integer.parseInt(request.getParameter("annee"));
			            Citation citation = new Citation(id, nomH, citationText, annee);
			            CitationDao dao = new CitationDao();
			            boolean success = dao.modifierCitation(citation);
			            if (success) {
			                List<Citation> vc = dao.afficherCitation();
			                request.setAttribute("vc", vc);
			                request.getRequestDispatcher("/Views/list.jsp").forward(request, response);
			            } else {
			                response.getWriter().write("Erreur lors de la modification de la citation.");
			            }
			        } catch (NumberFormatException e) {
			            response.getWriter().write("Erreur de format : " + e.getMessage());
			        } catch (SQLException e) {
			            throw new ServletException("Erreur de la base de données", e);
			        }
			    }
			    break;
			case "supprimer":
				try {
		        	CitationDao dao = new CitationDao();
		        	String idcitation = request.getParameter("id");
		            int id = Integer.parseInt(idcitation);
		        	Citation citation = dao.getCitationById(id);
		        	boolean success = dao.supprimerCitation(citation);
		        	if(success) {
		        		List<Citation> vc = dao.afficherCitation();
		                request.setAttribute("vc", vc);
		                request.getRequestDispatcher("/Views/list.jsp").forward(request, response);
		            } else {
		        		response.getWriter().write("Erreur lors de la suppression de la citation.");
		        	}
		        } catch (NumberFormatException e) {
					response.getWriter().write("Erreur de format : " + e.getMessage());
				} catch (SQLException e) {
					throw new ServletException("Erreur de la base de données", e);
				}
			break;
			}
	}
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    doPost(request, response);
	}

}