/**
 * 
 */
package br.com.avelino.core;

import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;

import br.com.avelino.layout.IMouseClickDefaultPanel;

/**
 * @author ravelino
 *
 */
public class HookConfigure {
	
	public static void initHooks(IMouseClickDefaultPanel panel) {
		
		try {
			GlobalScreen.registerNativeHook();
			//GlobalScreen.getInstance().addNativeKeyListener(new TeclaObserver(this));
			GlobalScreen.addNativeKeyListener(new TeclaObserver(panel));
			
			// Clear previous logging configurations.
			LogManager.getLogManager().reset();

			// Get the logger for "org.jnativehook" and set the level to off.
			Logger logger = Logger.getLogger(GlobalScreen.class.getPackage().getName());
			logger.setLevel(Level.OFF);
		} catch (NativeHookException ex) {
			System.err.println(ex.getMessage());
			System.exit(1);
		}
	}

}
