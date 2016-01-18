package br.com.astrix.model;

import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;

public class Nave {

	private int x, y;
	private int dx, dy;
	private int alt, lar;
	private Image image;
	private boolean isVisible;
	private List<Missel> misseis;

	public List<Missel> getMisseis() {
		return misseis;
	}

	public Nave() {
		// Random rd = new Random();
		// int i = rd.nextInt(3);
		this.x = 100;
		this.y = 100;
		misseis = new ArrayList<Missel>();
		ImageIcon ref = new ImageIcon("res\\nave.gif");
		image = ref.getImage();
		alt = image.getHeight(null);
		lar = image.getWidth(null);

	}

	public void mover() {
//		 System.out.println(x + " "+ y);
		x += dx;
		y += dy;
		if (x < -2) {
			x = -2;
		}
		if (x > 715) {
			x = 715;
		}
		if (y < -3) {
			y = -3;
		}
		if (y > 507) {
			y = 507;
		}
	}

	public void keyPressed(KeyEvent key) {
		int cod = key.getKeyCode();
		if (cod == KeyEvent.VK_UP) {
			dy -= 1;
		}
		if (cod == KeyEvent.VK_DOWN) {
			dy += 1;
		}
		if (cod == KeyEvent.VK_LEFT) {
			dx -= 1;
		}
		if (cod == KeyEvent.VK_RIGHT) {
			dx += 1;
		}
		if (cod == KeyEvent.VK_SPACE) {
			shot();
		}
	}

	public void keyReleased(KeyEvent key) {
		int cod = key.getKeyCode();
		if (cod == KeyEvent.VK_UP) {
			dy = 0;
		}
		if (cod == KeyEvent.VK_DOWN) {
			dy = 0;
		}
		if (cod == KeyEvent.VK_LEFT) {
			dx = 0;
		}
		if (cod == KeyEvent.VK_RIGHT) {
			dx = 0;
		}
	}

	public void shot() {
		this.misseis.add(new Missel(x + lar, y + (alt / 2)));
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

	public Rectangle getBounds() {
		return new Rectangle(x, y, lar, alt);
	}

	public boolean isVisible() {
		return isVisible;
	}

	public void setVisible(boolean isVisible) {
		this.isVisible = isVisible;
	}

}
