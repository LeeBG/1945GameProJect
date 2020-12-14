package test2;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JLabel;

abstract class EnemyUnit extends JLabel {

	protected int enemyX;
	protected int enemyY;
	protected PlayerPlane playerPlane;
	

	public void movedown() {
		enemyY++;
		setLocation(enemyX, enemyY);
	}

	public void moveleft() {
		enemyX--;
		setLocation(enemyX, enemyY); // repaint()

	}

	public void moveup() {
		enemyY= enemyY-2;
		setLocation(enemyX, enemyY); // repaint()

	}

	public void moveright() {
		enemyX++;
		setLocation(enemyX, enemyY); // repaint()

	}

	public abstract void move();

	public abstract void enemyUpdate(Graphics g);

}
