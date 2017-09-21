package br.com.avelino.db;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import org.apache.log4j.Logger;

import br.com.avelino.layout.DefaultPanel;
import br.com.avelino.to.ClickAutomaticoTO;

public class MouseRegisterDao {
	
	final static Logger log = Logger.getLogger(DefaultPanel.class);
	
	private MouseClickDBConnection db = new MouseClickDBConnection();
	
	public void insert(List<ClickAutomaticoTO> listClickAutomaticoTO) {
		
		listClickAutomaticoTO.forEach(item -> {
			final StringBuilder sb = new StringBuilder();
			sb
			.append("INSERT INTO mouse_registers (identificador, eixoX, eixoY, tecla, milissegundos, repetir, descricao) ")
			.append("Values ( ")
			.append("'" + item.getIdentificador()).append("', ")
			.append(item.getEixoX()).append(", ")
			.append(item.getEixoY()).append(", ")
			.append("'" + item.getTecla()).append("', ")
			.append(item.getMilessegundos()).append(", ")
			.append(item.getQtdRepetir()).append(", ")
			.append("'" + item.getDescricao()).append("'")
			.append(")");
			
			db.update(sb.toString());
		});
		
	}
	
	public List<ClickAutomaticoTO> findByIdentificador(String identificador) {
		
		try {
			final String query = "select * from mouse_registers where identificador = '" + identificador + "'";
			
			log.info("### DB = " + db);
			
			final ResultSet rs = db.executeQuery(query);
			
			log.info("### Executou Query, resultados:" + rs.toString());
			
			return getListaClickAutomaticoTO(rs);
			
		} catch (SQLException e) {
			System.out.println("Erro ao ler resultset");
		}
		return null;
	}

	public List<ClickAutomaticoTO> findAll() {
		
		try {
			
			
			final String query = "select * from mouse_registers";
			
			final ResultSet rs = db.executeQuery(query);
			log.info("### ResultSet = " + rs);
			return getListaClickAutomaticoTO(rs);
			
		} catch (SQLException e) {
			log.error("Erro findAll resultset: " + e.getMessage());
		}
		
		return null;
	}

	private List<ClickAutomaticoTO> getListaClickAutomaticoTO(final ResultSet rs) throws SQLException {
		final List<ClickAutomaticoTO> list = new LinkedList<>();
		
		while (rs.next()) {
			
			final ClickAutomaticoTO to = ClickAutomaticoTO
											.builder()
											.itemLista(rs.getInt(1))
											.identificador(rs.getString(2))
											.eixoX(rs.getInt(3))
											.eixoY(rs.getInt(4))
											.tecla(rs.getString(5))
											.milessegundos(rs.getInt(6))
											.qtdRepetir(rs.getInt(7))
											.descricao(rs.getString(8));
			
			list.add(to);
		}
		return list;
	}
	
	public void deleteByIdentificador(String identificador) {
		final String query = "delete from mouse_registers where identificador = '" + identificador + "'";
		db.update(query);
	}
	
	
	

}
