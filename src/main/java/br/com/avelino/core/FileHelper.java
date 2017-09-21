/**
 * 
 */
package br.com.avelino.core;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.commons.io.FileUtils;

import br.com.avelino.to.ClickAutomaticoTO;

/**
 * @author ravelino
 *
 */
public class FileHelper {

	public static void writeFile(String filePath, List<ClickAutomaticoTO> data) {
		final File file = new File(filePath.concat(File.separator).concat("mouseClickDB.csv"));
		
		try {
			FileUtils.writeLines(file, data);
		} catch (IOException e) {
			System.out.println("Erro ao escrever no arquivo");
		}
	}
	
	public static List<ClickAutomaticoTO> readFile(File file) {
		List<ClickAutomaticoTO> listClickAutomaticoTO = new ArrayList<>();
		
		try {
			final List<String> lines = FileUtils.readLines(file,"UTF-8");
			
			lines.forEach(line -> {
				final String [] fields = line.split(";");
				
				final String tamanhoLista = 
						Optional
							.of(fields[7])
							.filter(s -> s.contains("null"))
							.map(s -> "0")
							.get();
				
				listClickAutomaticoTO.add(
					ClickAutomaticoTO
						.builder()
						.identificador(fields[0])
						.qtdRepetir(Integer.valueOf(fields[1]))
						.eixoX(Integer.valueOf(fields[2]))
						.eixoY(Integer.valueOf(fields[3]))
						.tecla(fields[4])
						.milessegundos(Integer.valueOf(fields[5]))
						.itemLista(Integer.valueOf(fields[6]))
						.tamanhoLista(Integer.valueOf(tamanhoLista))
						.descricao(fields[8])
				);
			});
			
		} catch (IOException e) {
			System.out.println("Erro ao ler o arquivo");
		}
		
		return listClickAutomaticoTO;
	}
	
	
	
}
