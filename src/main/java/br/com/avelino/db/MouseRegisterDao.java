package br.com.avelino.db;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import br.com.avelino.to.ClickAutomaticoTO;

public class MouseRegisterDao {
	
	private MouseClickDBConnection db = new MouseClickDBConnection();
	
	public void insert(List<ClickAutomaticoTO> listClickAutomaticoTO) {
		
		
		
		listClickAutomaticoTO.forEach(item -> {
			final StringBuilder sb = new StringBuilder();
			sb
			.append("INSERT INTO mouse_registers (identificador, eixoX, eixoY, milissegundos, repetir) ")
			.append("Values ( ")
			.append("'" + item.getIdentificador()).append("', ")
			.append(item.getEixoX()).append(", ")
			.append(item.getEixoY()).append(", ")
			.append(item.getMilessegundos()).append(", ")
			.append(item.getQtdRepetir())
			.append(")");
			
			db.update(sb.toString());
		});
		
	}
	
	public List<ClickAutomaticoTO> findByIdentificador(String identificador) {
		
		try {
			final String query = "select * from mouse_registers where identificador = '" + identificador + "'";
			
			final ResultSet rs = db.executeQuery(query);
			
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
			
			return getListaClickAutomaticoTO(rs);
			
		} catch (SQLException e) {
			System.out.println("Erro findAll resultset");
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
											.milessegundos(rs.getInt(5))
											.qtdRepetir(rs.getInt(6));
			
			list.add(to);
		}
		return list;
	}
	
	public void deleteByIdentificador(String identificador) {
		final String query = "delete from mouse_registers where identificador = '" + identificador + "'";
		db.update(query);
	}
	
	
	

}
