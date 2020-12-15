package test2;

import java.awt.Image;
import java.util.ArrayList;

import javax.swing.ImageIcon;

public class PlayerAttack implements Runnable {
	private PlayerAttack playerAttack = this;
	private EnemyUnit enemyUnit;
	private ArrayList<EnemyUnit> enemyUnitList = new ArrayList<EnemyUnit>();
	static ArrayList<EnemyUnit> enemyUnitList2 = new ArrayList<EnemyUnit>();

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

	public PlayerAttack() {
		// TODO Auto-generated constructor stub
	}

	public PlayerAttack(ArrayList<EnemyUnit> enemyUnitList,EnemyUnit enemyUnit, double x, double y, double bulletAngle, double bulletSpeed) {
		
		this.enemyUnitList = enemyUnitList;
		
		if (enemyUnit != null) {
			this.enemyUnit = enemyUnit;
			enemyUnitList2.add(enemyUnit);
		}

		this.bulletX = x;
		this.bulletY = y;
		this.bulletAngle = bulletAngle;
		this.bulletSpeed = bulletSpeed;

		collision = false;

		Thread bulletthread2 = new Thread(this); // 총알 충돌 thread 생성, 실행
		bulletthread2.start();
		
	//	this.crash2();
	}

	public void Fire() {
		bulletX -= Math.cos(Math.toRadians(bulletAngle)) * bulletSpeed;
		bulletY -= Math.sin(Math.toRadians(bulletAngle)) * bulletSpeed;
	}

	@Override
	public void run() {
		
		System.out.println("총알 쓰레드 안의 에너미유닛리스트2 사이즈:  " + enemyUnitList2.size());
		for (int i = 0; i < enemyUnitList2.size(); i++) {
			System.out.println("여기 총알 쓰레드 안");
			System.out.print("총알 쓰레드 안 사이즈:" + enemyUnitList2.get(i).life + " ");
		}
					
		for (int i = 0; i < enemyUnitList2.size(); i++) {
			crash(enemyUnitList2.get(i));
			try {
				if (collision) {
					explosePlayer(enemyUnitList2.get(i)); // 충돌 폭발 메서드
				}
				Thread.sleep(10);

				if (bulletX > 1000 || bulletX < -500 || bulletY < -500 || bulletY > 1000) {
					System.out.println("playerbullet thread terminate");
					return; // Thread 종료구문
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
//
//		//while (enemyUnit.getLife() > 0 && enemyUnitList.size() !=  0) { // 생명이 0보다 크면
//			
//			for (int i = 0; i < enemyUnitList.size(); i++) {				
//				crash(enemyUnitList.get(i));
//				try {
//					if (collision) {
//						explosePlayer(enemyUnitList.get(i)); // 충돌 폭발 메서드
//					}
//					//Thread.sleep(10);
//					
//					if (bulletX > 1000 || bulletX < -500 || bulletY < -500 || bulletY > 1000) {
//						System.out.println("playerbullet thread terminate");
//						return; // Thread 종료구문
//					}
//					
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}

		//}

	}

	public boolean crash(EnemyUnit enemyUnit) { // 충돌연산
		if (Math.abs((enemyUnit.getEnemyX() + enemyUnit.getEnemyWidth() / 2)
				- (bulletX + enemyUnit.getEnemyWidth() / 2)) < (bulletWidth / 2 + enemyUnit.getEnemyWidth() / 2)
				&& Math.abs((enemyUnit.getEnemyY() + enemyUnit.getEnemyHeight() / 2)
						- (bulletY + bulletHeight / 2)) < (bulletHeight / 2 + enemyUnit.getEnemyHeight() / 2)) {
			collision = true;
			
		} else {
			collision = false;
		}
		System.out.println("되고있는건가? "+ collision);
		return collision;

	}

	public void explosePlayer(EnemyUnit enemyUnit) { // 충돌후 이미지 변경 및 목숨카운트

		try {
			ImageIcon explosionIcon = new ImageIcon("images/explosion.gif");
			enemyUnit.enemyImage = explosionIcon.getImage();
			Thread.sleep(1000);
			enemyUnit.enemyImage = enemyUnit.enemyImage;
			enemyUnit.setLife(enemyUnit.getLife() - 1);

			// enemyUnit.setEnemyX(enemyUnit.getEnemyX());
			// enemyUnit.setEnemyY(enemyUnit.getEnemyY());
			enemyUnit.setEnemyY(900);

			enemyUnit.repaint();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

//	public void addEnemyList(EnemyUnit enemyUnits) {
//		this.enemyUnit = enemyUnit;
//		enemyUnitList.add(enemyUnit);
//		System.out.println("에너미유닛리스트2 사이즈:  " + enemyUnitList.size());
//
//	}
	
//	public void addEnemyList(ArrayList<EnemyUnit> enemyUnits) {
//		this.enemyUnitList = enemyUnits;
//		System.out.println("에너미유닛리스트2 사이즈:  " + enemyUnitList.size());
//
//	}
	
	public void crash2() {
		
		System.out.println("에너미유닛리스트2 사이즈????:  " + enemyUnitList.size());
		
		new Thread(new Runnable() {		
			@Override
			public void run() {		
				for (int i = 0; i < enemyUnitList.size(); i++) {
					crash(enemyUnitList.get(i));
					try {
						if (collision) {
							explosePlayer(enemyUnitList.get(i)); // 충돌 폭발 메서드
						}
						Thread.sleep(10);

						if (bulletX > 1000 || bulletX < -500 || bulletY < -500 || bulletY > 1000) {
							System.out.println("playerbullet thread terminate");
							return; // Thread 종료구문
						}

					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				
				
				
			}
		}).start();
		
	}
	
	

	
	

}
