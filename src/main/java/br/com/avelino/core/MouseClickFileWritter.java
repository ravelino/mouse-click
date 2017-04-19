/**
 * 
 */
package br.com.avelino.core;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import br.com.avelino.to.ClickAutomaticoTO;

/**
 * @author ravelino
 *
 */
public class MouseClickFileWritter {
	
	private static final String IDENTIFICADOR_SEPARATOR = "=";
	private static final String FIELD_SEPARATOR = ";";
	
	private static final Integer EIXO_X_INDEX = 0;
	private static final Integer EIXO_Y_INDEX = 1;
	private static final Integer MILESSEGUNDOS_INDEX = 2;
	private static final Integer QTD_REPETIR_INDEX = 3;
	
	public static void write(List<ClickAutomaticoTO> listClickAutomaticoTO) {
		
		try {
			final Path filePath = getFilePath();
			final List<String> lines = Files.readAllLines(filePath);
			
			listClickAutomaticoTO.forEach(item -> {
				final StringBuilder sb = new StringBuilder();
				sb
					.append(item.getIdentificador()).append(IDENTIFICADOR_SEPARATOR)
					.append(item.getEixoX()).append(FIELD_SEPARATOR)
					.append(item.getEixoY()).append(FIELD_SEPARATOR)
					.append(item.getMilessegundos()).append(FIELD_SEPARATOR)
					.append(item.getQtdRepetir());
				
				lines.add(sb.toString());
			});
			
			
			Files.write(filePath, lines);
			
		} catch (IOException e) {
			System.out.println("Erro na leitura/escrita do arquivo lista.ini.");
		}
	}
	
	public static List<ClickAutomaticoTO> readFile() {
		List<ClickAutomaticoTO> listClickAutomaticoTO = new ArrayList<>();
		
		try {
			final Path filePath = getFilePath();
			final List<String> lines = Files.readAllLines(filePath);
			
			lines.forEach(line -> {
				listClickAutomaticoTO.add(createTO(line));
			});
			
			
		} catch (IOException e) {
			System.out.println("Erro na leitura do arquivo lista.ini.");
		}
		
		return listClickAutomaticoTO;
	}
	
	private static ClickAutomaticoTO createTO (String line) {
		final String [] fields = line.split(IDENTIFICADOR_SEPARATOR);
		final String identificador = fields[0];
		final String [] fieldsValues = fields[1].split(FIELD_SEPARATOR);
		
		return ClickAutomaticoTO
					.builder()
					.identificador(identificador)
					.eixoX(Integer.valueOf(fieldsValues[EIXO_X_INDEX]))
					.eixoY(Integer.valueOf(fieldsValues[EIXO_Y_INDEX]))
					.milessegundos(Integer.valueOf(fieldsValues[MILESSEGUNDOS_INDEX]))
					.qtdRepetir(Long.valueOf(fieldsValues[QTD_REPETIR_INDEX]));
	} 

	private static Path getFilePath() throws IOException {
		final File directory = new File(".");
		return Paths.get(directory.getCanonicalPath() + File.separator + "lista.ini");
	}

}
