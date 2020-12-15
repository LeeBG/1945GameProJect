package test2;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

abstract class EnemyUnit extends JLabel {

	protected int enemyX;
	protected int enemyY;
	protected PlayerPlane playerPlane;
	protected boolean collision = false;
	protected int enemyWidth;
	protected int enemyHeight;
	protected Image enemyImage;
	
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
	
	public void explosePlayer(PlayerPlane playerPlane, EnemyUnit enemyUnit) { // 충돌후 이미지 변경 및 목숨카운트

		try {
			ImageIcon explosionIcon = new ImageIcon("images/explosion.gif");
			playerPlane.setIcon(explosionIcon);
			enemyUnit.enemyImage = explosionIcon.getImage();
			Thread.sleep(1000);
			playerPlane.setIcon(playerPlane.icPlayer);
			playerPlane.setLife(playerPlane.getLife()-1);
			System.out.println("남은목숨:" + playerPlane.getLife());
			playerPlane.setX(200);
			playerPlane.setY(700);
			enemyUnit.enemyY = 1000; //Thread 강제종료 방법이 마땅히 안 떠오름 대충 이렇게
			playerPlane.repaint();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	

}
