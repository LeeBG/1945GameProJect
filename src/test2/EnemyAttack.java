package test2;

import java.awt.Image;

import javax.swing.ImageIcon;

public class EnemyAttack implements Runnable {

	private EnemyUnit enemyunit;
	private PlayerPlane playerPlane;
	private boolean collision;

	Image bulletImg1 = new ImageIcon("images/bullet1.png").getImage(); 
	Image bulletImg2 = new ImageIcon("images/bullet2.png").getImage(); 
	Image bulletImg3 = new ImageIcon("images/bullet3.png").getImage(); 
	Image bulletImg4 = new ImageIcon("images/bullet4.png").getImage(); 
	Image bulletImg5 = new ImageIcon("images/missle.png").getImage(); 
	
	int bulletX;
	int bulletY;
	double bulletAngel = 270; // 총알각도
	double bulletSpeed = 2; // 총알속도

	int bulletWidth1;
	int bulletHeight1;

	public EnemyAttack(int x, int y) {
		this.bulletX = x;
		this.bulletY = y;

	}
	
	public EnemyAttack(EnemyUnit enemyunit, PlayerPlane playerPlane, int bulletX, int bulletY, double bulletAngel,
			double bulletSpeed, int bulletWidth1, int bulletHeight1) {
	
		
		this.enemyunit = enemyunit;
		this.playerPlane = playerPlane;
		this.bulletX = bulletX;
		this.bulletY = bulletY;
		this.bulletAngel = bulletAngel;
		this.bulletSpeed = bulletSpeed;
		this.bulletWidth1 = bulletWidth1;
		this.bulletHeight1 = bulletHeight1;
		
		collision = false;
	
		
		Thread bulletthread = new Thread(this); // 총알 충돌 thread 생성, 실행
		bulletthread.start();
	}
	

	public void fire() {
		bulletX -= Math.cos(Math.toRadians(bulletAngel)) * bulletSpeed;
		bulletY -= Math.sin(Math.toRadians(bulletAngel)) * bulletSpeed;
	}

	@Override
	public void run() {
		


		while (playerPlane.getLife() > 0) { // 생명이 0보다 크면 
			
			crash();
	
			try {
				if (collision) {
					explosePlayer(playerPlane); // 충돌 폭발 메서드
				}
				Thread.sleep(10);
//				if (playerPlane.getLife() <= 0) {
//					Thread.sleep(100); // 1초후
//					System.exit(1); // 프로그램 종료
//				}
				
				
				if(bulletX > 1000 || bulletX <-500 || bulletY < -500 || bulletY >1000 ) {
					System.out.println("bullet thread terminate");
					return; 	//Thread 종료구문
				}
				

			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

	
	
	
//	public void crash() { //충돌연산
//        if(Math.abs((playerPlane.getX() + playerPlane.getPlayerWidth() / 2) - ( bulletX + playerPlane.getPlayerWidth()/ 2)) < ( bulletWidth1 / 2 + playerPlane.getPlayerWidth() / 2) &&
//                Math.abs( (playerPlane.getY() + playerPlane.getPlayerHeight() / 2) - (bulletY + bulletHeight1 / 2)) < ( bulletHeight1 /2 + playerPlane.getPlayerHeight() / 2)) {
//        		collision = true;} else {
//        			collision = false;
//        		}
//		
//	}
	
	public void crash() { // 충돌연산
		if (Math.abs(((playerPlane.getX() - 11) + playerPlane.getPlayerWidth() / 3)
				- (bulletX + bulletWidth1 / 3)) < (bulletWidth1 / 3 + playerPlane.getPlayerWidth() / 3)
				&& Math.abs(((playerPlane.getY() - 5) + playerPlane.getPlayerHeight() / 3)
						- (bulletY + bulletHeight1 / 3)) < (bulletHeight1 / 3 + playerPlane.getPlayerHeight() / 3 )) {
			collision = true;
		} else {
			collision = false;
		}
	}
	
	
	public void explosePlayer(PlayerPlane playerPlane) { // 충돌후 이미지 변경 및 목숨카운트

		try {
			ImageIcon explosionIcon = new ImageIcon("images/explosion.gif");
			playerPlane.setIcon(explosionIcon);
			Thread.sleep(1000);
			playerPlane.setIcon(playerPlane.icPlayer);
			playerPlane.setLife(playerPlane.getLife()-1);
			System.out.println("남은목숨:" + playerPlane.getLife());
			playerPlane.setX(200);
			playerPlane.setY(520);
			playerPlane.repaint();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
