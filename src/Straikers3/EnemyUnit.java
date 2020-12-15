package Straikers3;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

abstract class EnemyUnit extends JLabel {

	public int enemyWidth;
	public int enemyHeight;
	protected int enemyX;
	protected int enemyY;
	protected PlayerPlane playerPlane;
//	
//	
//	public int getEnemyWidth() {
//		return enemyWidth;
//	}
//	
//	public void setEnemyWidth(int enemyWidth) {
//		this.enemyWidth = enemyWidth;
//	}
//
//	public int getEnemyHeight() {
//		return enemyHeight;
//	}
//
//	public void setEnemyHeight(int enemyHeight) {
//		this.enemyHeight = enemyHeight;
//	}
//
//	public int getX() {
//		return enemyX;
//	}
//
//	public void setX(int x) {
//		this.enemyX = x;
//	}
//
//	public int getY() {
//		return enemyY;
//	}
//
//	public void setY(int y) {
//		this.enemyY = y;
//	}
	
	
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
