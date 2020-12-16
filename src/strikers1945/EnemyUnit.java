package strikers1945;

import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;

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
	protected int life;

	public int getEnemyX() {
		return enemyX;
	}

	public void setEnemyX(int enemyX) {
		this.enemyX = enemyX;
	}

	public int getEnemyY() {
		return enemyY;
	}

	public void setEnemyY(int enemyY) {
		this.enemyY = enemyY;
	}

	public PlayerPlane getPlayerPlane() {
		return playerPlane;
	}

	public void setPlayerPlane(PlayerPlane playerPlane) {
		this.playerPlane = playerPlane;
	}

	public int getEnemyWidth() {
		return enemyWidth;
	}

	public void setEnemyWidth(int enemyWidth) {
		this.enemyWidth = enemyWidth;
	}

	public int getEnemyHeight() {
		return enemyHeight;
	}

	public void setEnemyHeight(int enemyHeight) {
		this.enemyHeight = enemyHeight;
	}

	public Image getEnemyImage() {
		return enemyImage;
	}

	public void setEnemyImage(Image enemyImage) {
		this.enemyImage = enemyImage;
	}

	public int getLife() {
		return life;
	}

	public void setLife(int life) {
		this.life = life;
	}

	public void movedown() {
		enemyY++;
		setLocation(enemyX, enemyY);
	}

	public void moveleft() {
		enemyX--;
		setLocation(enemyX, enemyY); // repaint()

	}

	public void moveup() {
		enemyY = enemyY - 2;
		setLocation(enemyX, enemyY); // repaint()

	}

	public void moveright() {
		enemyX++;
		setLocation(enemyX, enemyY); // repaint()

	}

	public abstract void move();

	public abstract void enemyUpdate(Graphics g);

	public void explosePlayer(PlayerPlane playerPlane, EnemyUnit enemyUnit) { // 異⑸룎�썑 �씠誘몄� 蹂�寃� 諛� 紐⑹닲移댁슫�듃

		try {
			ImageIcon explosionIcon = new ImageIcon("images/explosion.gif");
			playerPlane.setIcon(explosionIcon);
			enemyUnit.enemyImage = explosionIcon.getImage();
			Thread.sleep(1000);
			playerPlane.setIcon(playerPlane.playerIcon);
			playerPlane.setLifeCount(playerPlane.getLifeCount() - 1);
			System.out.println("�궓��紐⑹닲:" + playerPlane.getLifeCount());
			playerPlane.setX(200);
			playerPlane.setY(700);
			enemyUnit.enemyY = 1000; // Thread 媛뺤젣醫낅즺 諛⑸쾿�씠 留덈븙�엳 �븞 �뼚�삤由� ��異� �씠�젃寃�
			playerPlane.repaint();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
