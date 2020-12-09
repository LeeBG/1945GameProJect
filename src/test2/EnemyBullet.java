package test2;

import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class EnemyBullet extends JLabel{

	Image bulletImg1 = new ImageIcon("images/bullet(e).png").getImage();	
	ImageIcon icBullet;
	
	
	int bulletX;
	int bulletY;
	
	int bulletWidth1 = bulletImg1.getWidth(null);
	int bulletHeight1 = bulletImg1.getHeight(null);
	
	public EnemyBullet(int x, int y) {
		this.bulletX = x;
		this.bulletY = y;
		icBullet = new ImageIcon("images/bullet(e).png");
		setIcon(icBullet);
	
	}
	
	public void fire() {
		this.bulletY --;	
	}
}
