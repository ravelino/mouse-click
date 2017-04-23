/**
 * 
 */
package br.com.avelino.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author ravelino
 *
 */
public class MouseClickDBConnection {
	
	private Connection connection;
	
	private Statement statement;
	
	public MouseClickDBConnection() {
		openStatement();
	} 
	
	public void createDataBaseIfNotExists() {
		StringBuilder sb = new StringBuilder();
		sb
			.append("CREATE TABLE IF NOT EXISTS mouse_registers ")
			.append(" (")
			.append(" id INTEGER IDENTITY,")
			.append(" identificador VARCHAR(256),")
			.append(" eixoX INTEGER,")
			.append(" eixoY INTEGER,")
			.append(" milissegundos INTEGER,")
			.append(" repetir INTEGER")
			.append(" )");
			
		
		update(sb.toString());
	}
	
	public ResultSet executeQuery(String query) {
		ResultSet rs = null;
		openStatement();
		try {
			
			 rs = statement.executeQuery(query);
			 
		} catch (SQLException e) {
			System.out.println("Erro ao executar query");
		}
		closeStatement();
		return rs;
	}
	
	public void closeStatement() {
		try {
			statement.close();
			closeConnection();
		} catch (SQLException e) {
			System.out.println("Erro ao fechar statement");
		}
	}
	
	public void update(String expression) {
		openStatement();
        
		try {
			
			int i = statement.executeUpdate(expression);
			
			if (i == -1) {
	            System.out.println("Ocorreu um erro ao realizar a query: " + expression);
	        }
			
		} catch (SQLException e) {
			System.out.println("Erro ao executar update: " + expression);
		}
		closeStatement();
    } 
	
	public void openConnection() {
		try {
			connection = DriverManager.getConnection("jdbc:hsqldb:file:mouseClick", "SA", "");
		} catch (SQLException e) {
			System.out.println("Erro ao abrir conexao");
		}
	}
	
	public void openStatement() {
		try {
			openConnection();
			statement = connection.createStatement();
		} catch (SQLException e) {
			System.out.println("Erro ao criar statement");
		}
	}
	
	private void closeConnection() {
		try {
			connection.close();
		} catch (SQLException e) {
			System.out.println("Erro ao fechar conexao");
		}
	}
	

}
