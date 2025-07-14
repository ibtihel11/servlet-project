package net.java.dao;
import net.java.model.Citation;
import java.sql.*;
import java.util.List;
import java.util.ArrayList;

public class CitationDao {
	private Connection connection;
	public Connection getConnection() {

	try {
	String dbUrl ="jdbc:mysql://localhost:3306/test1?serverTimezone=UTC&useSSL=false";
	String dbUser = "root";
	String dbPassword = "0000";

	Class.forName("com.mysql.cj.jdbc.Driver");

	this.connection = DriverManager.getConnection(dbUrl, dbUser, dbPassword);

	} catch (SQLException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
	} catch (ClassNotFoundException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
	}
	return connection;
	}
	public Citation getCitationById(int id) throws SQLException {
	    String sql = "SELECT * FROM citation WHERE idcitation = ?";
	    try (PreparedStatement stmt = getConnection().prepareStatement(sql)) {
	        stmt.setInt(1, id);
	        try (ResultSet rs = stmt.executeQuery()) {
	            if (rs.next()) {
	                return new Citation(
	                    rs.getInt("idcitation"),
	                    rs.getString("auteur"),
	                    rs.getString("citation"),
	                    rs.getInt("annee")
	                );
	            }
	        }
	    }
	    return null; // Si aucune citation trouvée
	}

	public boolean ajouterCitation(Citation citation) throws SQLException {
		String sql = "INSERT INTO citation (idcitation, auteur, citation,annee) VALUES (?, ?, ?, ?)";
		try (PreparedStatement stmt = getConnection().prepareStatement(sql))
		{
		stmt.setInt(1, citation.getIdcitation());
		stmt.setString(2, citation.getNomH());
		stmt.setString(3, citation.getCitationcol());
		stmt.setInt(4, citation.getAnne());
		int affectedRows = stmt.executeUpdate();
		return affectedRows > 0;
		}
	}
	public List<Citation> afficherCitation() throws SQLException {
		String sql = "SELECT * FROM citation";
		List<Citation> citations = new ArrayList<>();
		try (PreparedStatement stmt = getConnection().prepareStatement(sql))
		{
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				Citation c=new Citation();
				c.setIdcitation(rs.getInt("idcitation"));
				c.setNomH(rs.getString("auteur"));
				c.setCitationcol(rs.getString("citation"));
				c.setAnne(rs.getInt("annee"));
				citations.add(c);
			}
	 	}
		return citations;
	}
	public boolean modifierCitation(Citation citation) throws SQLException {
		String sql = "UPDATE citation SET auteur=?, citation=?,annee=? WHERE idcitation=?";
		try (PreparedStatement stmt = getConnection().prepareStatement(sql))
		{
		stmt.setString(1, citation.getNomH());
		stmt.setString(2, citation.getCitationcol());
		stmt.setInt(3, citation.getAnne());
		stmt.setInt(4, citation.getIdcitation());
		int affectedRows = stmt.executeUpdate();
		return affectedRows > 0;
		}
	}
	public boolean supprimerCitation(Citation citation) throws SQLException {
		String sql = "	DELETE FROM citation WHERE idcitation=?";
		try (PreparedStatement stmt = getConnection().prepareStatement(sql))
		{
		stmt.setInt(1, citation.getIdcitation());
		int affectedRows = stmt.executeUpdate();
		return affectedRows > 0;
		}
	}
}
