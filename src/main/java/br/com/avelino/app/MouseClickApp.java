/**
 * 
 */
package br.com.avelino.app;

import javax.swing.SwingUtilities;

import br.com.avelino.db.MouseClickDBConnection;
import br.com.avelino.layout.MouseClickFrame;


/**
 * @author ravelino
 *
 */
public class MouseClickApp {
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				MouseClickDBConnection db = new MouseClickDBConnection();
				//db.dropTable("mouse_registers");
				db.createDataBaseIfNotExists();
				new MouseClickFrame();
			}
		});
	}
	
	
}
