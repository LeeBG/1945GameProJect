package Straikers3;

import java.awt.Image;

import javax.swing.ImageIcon;

public class Attack {
	private Attack playerAttack = this;
	private PlayerPlane playerPlane;
	private EnemyAttack enemyAttack;
	EnemyUnit enemyUnit;
	Enemy2 enemy2;

	Image playerBulletImg1 = new ImageIcon("images/PlayerBullet1.png").getImage();
	Image playerBulletImg2 = new ImageIcon("images/bullet1.png").getImage();
	Image playerBulletImg3 = new ImageIcon("images/bullet3.png").getImage();

	private boolean collision;
	double BulletX;
	double BulletY;
	double BulletAngle;
	double BbulletSpeed;
	int BulletWidth;
	int BulletHeight;

	int BulletAtk = 1;

	int playerBulletWidth1 = playerBulletImg1.getWidth(null);
	int playerBulletHeight1 = playerBulletImg1.getHeight(null);

	public Attack(double x, double y, double bulletAngle, double bulletSpeed) {
		this.BulletX = x;
		this.BulletY = y;
		this.BulletAngle = bulletAngle;
		this.BbulletSpeed = bulletSpeed;

		collision = false;
	}

	public void Fire() {
		BulletX -= Math.cos(Math.toRadians(BulletAngle)) * BbulletSpeed;
		BulletY -= Math.sin(Math.toRadians(BulletAngle)) * BbulletSpeed;
	}

	public void crash() {
//		if (Math.abs(((enemy2.enemyX - 11) + enemyAttack.b2Width / 3)
//				- (BulletX + BulletWidth / 3)) < (BulletWidth / 3 + enemyAttack.b2Width / 3)
//				&& Math.abs(((enemy2.enemyY - 5) + enemyAttack.b2Height / 3)
//						- (BulletY + BulletHeight / 3)) < (BulletHeight / 3 + enemyAttack.b2Height / 3 )) {

		if (BulletX > enemy2.enemyX && BulletX < (enemy2.enemyX + enemyAttack.b2Width) && BulletY > enemy2.enemyY
				&& BulletY < (enemy2.enemyY + enemyAttack.b2Width)) {// 세로 판정
			playerPlane.playerAttackList.remove(playerPlane.playerAttack);
			collision = true;
		} else {
			collision = false;
		}
	}
}
