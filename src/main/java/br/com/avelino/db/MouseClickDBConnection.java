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
	
	private static Connection connection;
	
	private static Statement statement;
	
	public static void createDataBaseIfNotExists() {
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
	
	public static ResultSet executeQuery(String query) {
		ResultSet rs = null;
		
		try {
			final Statement st = getStatement();
			
			 rs = st.executeQuery(query);
			 
		} catch (SQLException e) {
			System.out.println("Erro ao executar query");
		}
		return rs;
	}
	
	public static void closeStatement() {
		try {
			getStatement().close();
		} catch (SQLException e) {
			System.out.println("Erro ao fechar statement");
		}
	}
	
	public static void update(String expression) {

        int i;
        
		try {
			
			final Statement st = getStatement();
			
			i = st.executeUpdate(expression);
			
			if (i == -1) {
	            System.out.println("Ocorreu um erro ao realizar a query: " + expression);
	        }
			
		} catch (SQLException e) {
			System.out.println("Erro ao executar update: " + expression);
		}
    } 
	
	public static Connection getConnection() {
		if (connection == null) {
			try {
				connection = DriverManager.getConnection("jdbc:hsqldb:file:mouseClick", "SA", "");
			} catch (SQLException e) {
				System.out.println("Erro ao obter conexao.");
			}
		}
		
		return connection;
	}
	
	public static Statement getStatement() {
		
		if (statement == null) {
			try {
				statement = getConnection().createStatement();
			} catch (SQLException e) {
				System.out.println("Erro ao obterStatement");
			}
		}
		
		return statement; 
	}
	

}
