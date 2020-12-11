package Straikers2;

import java.awt.Image;

import javax.swing.ImageIcon;

public class BossAttack {
	private BossAttack bossAttack = this;
	private Boss boss;

	Image bossBulletImg1 = new ImageIcon("images/BossBullet1-ReSize.png").getImage();

	double bulletX;
	double bulletY;
	double bulletAngle;
	double bulletSpeed;

	int bossBulletWidth1 = bossBulletImg1.getWidth(null);
	int bossBulletHeight1 = bossBulletImg1.getHeight(null);

	public BossAttack(double x, double y, double bulletAngle, double bulletSpeed) {
		this.bulletX = x;
		this.bulletY = y;
		this.bulletAngle = bulletAngle;
		this.bulletSpeed = bulletSpeed;
	}

	public void fire() {
		bulletX += Math.sin(Math.toRadians(bulletAngle)) * bulletSpeed;
		bulletY += Math.cos(Math.toRadians(bulletAngle)) * bulletSpeed;
	}
}
