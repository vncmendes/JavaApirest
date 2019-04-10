package br.edu.ifsul.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class BaseDAO {
	
	public BaseDAO() {
		try {
//			Class.forName("com.mysql.jdbc.Driver"); //para servidor MySql
			Class.forName("org.mariadb.jdbc.Driver");  //para servidor MariaDB
		} catch (ClassNotFoundException e) {
			// Erro de driver JDBC (adicione o driver .jar do banco de dados em /WEB-INF/lib)
			e.printStackTrace();
		}
	}
	
	protected Connection getConnection()throws SQLException{
		//URL de conex�o com o banco de dados.
		String url = "jdbc:mysql://localhost:3306/apirest";
		
		//Conecta utilizando a URL, usu�rio e senha.
		Connection conn = DriverManager.getConnection(url,"root", "");
		
		return conn;
	}
	
	public static void main(String[] args) throws SQLException {
		BaseDAO db = new BaseDAO();
		//testa a conex�o
		Connection conn = db.getConnection();
		System.out.println(conn);
	}

}
