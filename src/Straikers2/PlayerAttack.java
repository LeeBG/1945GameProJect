package Straikers2;

import java.awt.Image;

import javax.swing.ImageIcon;

public class PlayerAttack {
	private PlayerAttack playerAttack = this;
	private Player player;

	Image playerBulletImg1 = new ImageIcon("images/PlayerBullet1.png").getImage();

	double bulletX;
	double bulletY;
	double bulletAngle;
	double bulletSpeed;

	int playerBulletWidth1 = playerBulletImg1.getWidth(null);
	int playerBulletHeight1 = playerBulletImg1.getHeight(null);

	public PlayerAttack(double x, double y, double bulletAngle, double bulletSpeed) {
		this.bulletX = x;
		this.bulletY = y;
		this.bulletAngle = bulletAngle;
		this.bulletSpeed = bulletSpeed;
	}

	public void fire() {
		bulletX -= Math.cos(Math.toRadians(bulletAngle)) * bulletSpeed;
		bulletY -= Math.sin(Math.toRadians(bulletAngle)) * bulletSpeed;
	}
}
