package br.com.astrix.model;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Fase extends JPanel implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Image image;
	private Nave nave;
	private Timer time;
	private boolean emJogo;
	private List<Inimigo> inimigos;
	private int[][] coord = { { 2380, 29 }, { 2600, 59 }, { 1380, 89 }, { 880, 109 }, { 680, 139 }, { 880, 239 },
			{ 790, 259 }, { 760, 50 }, { 790, 150 }, { 2380, 29 }, { 2600, 59 }, { 1380, 89 }, { 880, 109 },
			{ 680, 139 }, { 880, 239 }, { 790, 259 }, { 760, 50 }, { 790, 150 }, { 2380, 29 }, { 2600, 59 },
			{ 1380, 89 }, { 880, 109 }, { 680, 139 }, { 880, 239 }, { 790, 259 }, { 760, 50 }, { 790, 150 },
			{ 2380, 29 }, { 2600, 59 }, { 1380, 89 }, { 880, 109 }, { 680, 139 }, { 880, 239 }, { 790, 259 },
			{ 760, 50 }, { 790, 150 }, { 2380, 29 }, { 2600, 59 }, { 1380, 89 }, { 880, 109 }, { 680, 139 },
			{ 880, 239 }, { 790, 259 }, { 760, 50 }, { 790, 150 } };

	public Fase() {

		setFocusable(true);
		setDoubleBuffered(true);
		addKeyListener(new TecladoAdapter());
		ImageIcon ref = new ImageIcon("res\\fundo.png");
		image = ref.getImage();

		emJogo = true;

		nave = new Nave();

		inicializaInimigos();
		time = new Timer(5, this);
		time.start();
	}

	public void inicializaInimigos() {
		inimigos = new ArrayList<Inimigo>();

		for (int i = 0; i < coord.length; i++) {
			inimigos.add(new Inimigo(coord[i][0], coord[i][1]));
		}
	}

	public void paint(Graphics g) {
		Graphics2D graficos = (Graphics2D) g;
		graficos.drawImage(image, 0, 0, null);

		if (emJogo) {

			graficos.drawImage(nave.getImage(), nave.getX(), nave.getY(), this);

			List<Missel> misseis = nave.getMisseis();

			for (int i = 0; i < misseis.size(); i++) {
				Missel m = (Missel) misseis.get(i);
				graficos.drawImage(m.getImage(), m.getX(), m.getY(), this);
				
			}

			for (int i = 0; i < inimigos.size(); i++) {
				Inimigo in = (Inimigo) inimigos.get(i);
				graficos.drawImage(in.getImage(), in.getX(), in.getY(), this);
//				graficos.drawRect(in.getX(), in.getY(), in.getLar(), in.getAlt());
			}
			graficos.setColor(Color.WHITE);
			graficos.drawString("Inimigos: " + inimigos.size(), 5, 15);
		} else {
			ImageIcon endGame = new ImageIcon("res\\gameOver.png");
			graficos.drawImage(endGame.getImage(), 217, 138, null);
		}
		g.dispose();
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (inimigos.size() == 0) {
			emJogo = false;
		}

		List<Missel> misseis = nave.getMisseis();

		for (int i = 0; i < misseis.size(); i++) {
			Missel m = (Missel) misseis.get(i);
			if (m.isVisible()) {
				m.mover();
			} else {
				misseis.remove(i);
			}
		}

		for (int i = 0; i < inimigos.size(); i++) {
			Inimigo in = (Inimigo) inimigos.get(i);
			if (in.isVisible()) {
				in.mover();
			} else {
				inimigos.remove(i);
			}
		}

		nave.mover();
		checkColisoes();
		repaint();
	}

	public void checkColisoes() {
		Rectangle formNave = nave.getBounds();
		Rectangle formInimigo;
		Rectangle formMissel;

		for (int i = 0; i < inimigos.size(); i++) {
			Inimigo tempInimmigo = (Inimigo) inimigos.get(i);
			formInimigo = tempInimmigo.getBounds();

			if (formNave.intersects(formInimigo)) {
				nave.setVisible(false);
				tempInimmigo.setVisible(false);

				emJogo = false;
			}
		}
		List<Missel> misseis = nave.getMisseis();

		for (int i1 = 0; i1 < misseis.size(); i1++) {
		
			Missel tempMissel = misseis.get(i1);
			formMissel = tempMissel.getBounds();

			for (int j = 0; j < inimigos.size(); j++) {
				
				Inimigo tempInimigo = (Inimigo) inimigos.get(j);
				formInimigo = tempInimigo.getBounds();
				
				if (formMissel.intersects(formInimigo)) {
					
					tempInimigo.setVisible(false);
					tempMissel.setVisible(false);
				}
			}
		}

	}

	private class TecladoAdapter extends KeyAdapter {

		@Override
		public void keyPressed(KeyEvent e) {
			// TODO Auto-generated method stub
			nave.keyPressed(e);
		}

		@Override
		public void keyReleased(KeyEvent e) {
			// TODO Auto-generated method stub
			nave.keyReleased(e);
		}

	}
}
