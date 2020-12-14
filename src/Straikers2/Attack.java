package Straikers2;

import java.awt.Image;

import javax.swing.ImageIcon;

public class Attack {
	private Attack playerAttack = this;
	private Player player;

	Image playerBulletImg1 = new ImageIcon("images/PlayerBullet1.png").getImage();
	Image bossBulletImg1 = new ImageIcon("images/BossBullet1-ReSize.png").getImage();

	double BulletX;
	double BulletY;
	double BulletAngle;
	double BbulletSpeed;
	
	int BulletAtk = 1;

	int playerBulletWidth1 = playerBulletImg1.getWidth(null);
	int playerBulletHeight1 = playerBulletImg1.getHeight(null);

	int bossBulletWidth1 = bossBulletImg1.getWidth(null);
	int bossBulletHeight1 = bossBulletImg1.getHeight(null);
	
	public Attack(double x, double y, double bulletAngle, double bulletSpeed) {
		this.BulletX = x;
		this.BulletY = y;
		this.BulletAngle = bulletAngle;
		this.BbulletSpeed = bulletSpeed;
	}

	public void Fire() {
		BulletX -= Math.cos(Math.toRadians(BulletAngle)) * BbulletSpeed;
		BulletY -= Math.sin(Math.toRadians(BulletAngle)) * BbulletSpeed;
	}
}
