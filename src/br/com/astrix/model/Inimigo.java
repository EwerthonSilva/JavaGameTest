package br.com.astrix.model;

import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

public class Inimigo {

	private static final int LARGURA_TELA = 800;
	private static final int SPEED = 1;
	private int x, y;
	private int alt, lar;
	private boolean isVisible;
	public int getAlt() {
		return alt;
	}

	public int getLar() {
		return lar;
	}

	private Image image;

	public Inimigo(int x, int y) {
		
		this.x = x;
		this.y = y;
		
		ImageIcon ref = new ImageIcon("res\\inimigo.gif");
		image = ref.getImage();
		
		this.lar = image.getWidth(null);
		this.alt = image.getHeight(null);		

		isVisible = true;
	}

	public void mover() {
		if (x < 0) {
			this.x = LARGURA_TELA;
		} else {
			this.x -= SPEED;
		}

	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public Image getImage() {
		return image;
	}

	public boolean isVisible() {
		return isVisible;
	}

	public void setVisible(boolean isVisible) {
		this.isVisible = isVisible;
	}
	
	public Rectangle getBounds(){
		return new Rectangle(x, y, lar, alt);
	}

}
