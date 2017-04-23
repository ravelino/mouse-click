package br.com.avelino.db;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.avelino.to.ClickAutomaticoTO;

public class MouseRegisterDao {
	
	
	public void insert(List<ClickAutomaticoTO> listClickAutomaticoTO) {
		StringBuilder sb = new StringBuilder();
		
		
		listClickAutomaticoTO.forEach(item -> {
			sb
			.append("INSERT INTO mouse_registers (identificador, eixoX, eixoY, milissegundos, repetir) ")
			.append("Values ( ")
			.append("'" + item.getIdentificador()).append("', ")
			.append(item.getEixoX()).append(", ")
			.append(item.getEixoY()).append(", ")
			.append(item.getMilessegundos()).append(", ")
			.append(item.getQtdRepetir())
			.append(")");
			
			MouseClickDBConnection.update(sb.toString());
		});
	}
	
	public List<ClickAutomaticoTO> findByIdentificador(String identificador) {
		
		final List<ClickAutomaticoTO> list = new ArrayList<>();
		
		try {
			final String query = "select * from mouse_registers where identificador = '" + identificador + "'";
			
			final ResultSet rs = MouseClickDBConnection.executeQuery(query);
			
			getListaClickAutomaticoTO(list, rs);
			
		} catch (SQLException e) {
			System.out.println("Erro ao ler resultset");
		}
		return list;
	}

	public List<ClickAutomaticoTO> findAll() {
		
		final List<ClickAutomaticoTO> list = new ArrayList<>();
		
		try {
			final String query = "select * from mouse_registers";
			
			final ResultSet rs = MouseClickDBConnection.executeQuery(query);
			
			getListaClickAutomaticoTO(list, rs);
			
		} catch (SQLException e) {
			System.out.println("Erro findAll resultset");
		}
		
		return list;
	}

	private void getListaClickAutomaticoTO(final List<ClickAutomaticoTO> list, final ResultSet rs) throws SQLException {
		while (rs.next()) {
			final ClickAutomaticoTO to = ClickAutomaticoTO
											.builder()
											.identificador((String) rs.getObject(2))
											.eixoX((Integer) rs.getObject(3))
											.eixoY((Integer) rs.getObject(4))
											.milessegundos((Integer) rs.getObject(5))
											.qtdRepetir((Integer) rs.getObject(6));
			
			list.add(to);
		}
	}
	
	public void deleteByIdentificador(String identificador) {
		final String query = "delete from mouse_registers where identificador = '" + identificador + "'";
		MouseClickDBConnection.update(query);
	}
	
	
	

}
