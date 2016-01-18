package br.com.astrix.views;

import javax.swing.JFrame;

import br.com.astrix.model.Fase;

public class ConteinersJanelas extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ConteinersJanelas() {
		add(new Fase());
		setTitle("Astrix");
		setSize(800, 600);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setVisible(true);
	}

}
