package test2;

import java.awt.Image;
import java.util.Vector;

import javax.swing.ImageIcon;

public class PlayerAttack implements Runnable {
	private PlayerAttack playerAttack = this;
	private EnemyUnit enemyUnit;
	private Vector<EnemyUnit> enemyUnitList;

	Image playerBulletImg1 = new ImageIcon("images/playerBullet1.png").getImage();
	Image playerBulletImg2 = new ImageIcon("images/bullet1.png").getImage();
	Image playerBulletImg3 = new ImageIcon("images/bullet3.png").getImage();

	private boolean collision;
	double bulletX;
	double bulletY;
	double bulletAngle;
	double bulletSpeed;
	int bulletWidth;
	int bulletHeight;

	int bulletAtk = 1;

	int playerBulletWidth1 = playerBulletImg1.getWidth(null);
	int playerBulletHeight1 = playerBulletImg1.getHeight(null);

	public PlayerAttack(EnemyUnit enemyUnit, double x, double y, double bulletAngle, double bulletSpeed) {
		this.enemyUnit = enemyUnit;
		//this.enemyUnitList.add(enemyUnit);
		this.bulletX = x;
		this.bulletY = y;
		this.bulletAngle = bulletAngle;
		this.bulletSpeed = bulletSpeed;

		collision = false;

		Thread bulletthread = new Thread(this); // 총알 충돌 thread 생성, 실행
		bulletthread.start();
	}

	public void Fire() {
		bulletX -= Math.cos(Math.toRadians(bulletAngle)) * bulletSpeed;
		bulletY -= Math.sin(Math.toRadians(bulletAngle)) * bulletSpeed;
	}

	@Override
	public void run() {

		while (enemyUnit.getLife() > 0) { // 생명이 0보다 크면

			crash();

			try {
				if (collision) {
					explosePlayer(enemyUnit); // 충돌 폭발 메서드
				}
				Thread.sleep(10);

				if (bulletX > 1000 || bulletX < -500 || bulletY < -500 || bulletY > 1000) {
					System.out.println("bullet thread terminate");
					return; // Thread 종료구문
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

	public void crash() { // 충돌연산
		if (Math.abs((enemyUnit.getEnemyX() + enemyUnit.getEnemyWidth() / 2)
				- (bulletX + enemyUnit.getEnemyWidth() / 2)) < (bulletWidth / 2 + enemyUnit.getEnemyWidth() / 2)
				&& Math.abs((enemyUnit.getEnemyY() + enemyUnit.getEnemyHeight() / 2)
						- (bulletY + bulletHeight / 2)) < (bulletHeight / 2 + enemyUnit.getEnemyHeight() / 2)) {
			collision = true;
		} else {
			collision = false;
		}

	}

	public void explosePlayer(EnemyUnit enemyUnit) { // 충돌후 이미지 변경 및 목숨카운트

		try {
			ImageIcon explosionIcon = new ImageIcon("images/explosion.gif");
			enemyUnit.enemyImage = explosionIcon.getImage();
			Thread.sleep(1000);
			enemyUnit.enemyImage = enemyUnit.enemyImage;
			enemyUnit.setLife(enemyUnit.getLife() - 1);
			
			enemyUnit.setEnemyX(enemyUnit.getEnemyX());
			enemyUnit.setEnemyY(enemyUnit.getEnemyY());

			enemyUnit.repaint();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
