package br.com.avelino.layout;

import javax.swing.table.DefaultTableModel;

public interface IMouseClickDefaultPanel {
	
	DefaultTableModel getTableModel();
	
	void timersStop();
	
	void timersStart();

}
