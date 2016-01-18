package br.com.astrix.model;

import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

public class Missel {

	private static final int LARGURA_TELA = 800;
	private static final int SPEED = 2;
	private int x, y;
	private int alt, lar;
	private boolean isVisible;
	private Image image;

	public Missel(int x, int y) {
		ImageIcon ref = new ImageIcon("res\\missil.png");
		image = ref.getImage();
		this.alt = image.getHeight(null);
		this.lar = image.getWidth(null);
		this.x = x;
		this.y = y;
		isVisible = true;
	}

	public void mover() {
		x += SPEED;
		if (x > LARGURA_TELA) {
			isVisible = false;
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
