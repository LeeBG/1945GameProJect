package strikers1945;

import java.awt.Image;

import javax.swing.ImageIcon;

public class EnemyAttack implements Runnable {

	private GameFrame gameFrame;
	private EnemyUnit enemyunit;
	private PlayerPlane playerPlane;
	private boolean collision;

	Image bulletImg1 = new ImageIcon("images/bullet1.png").getImage();
	Image bulletImg2 = new ImageIcon("images/bullet2.png").getImage();
	Image bulletImg3 = new ImageIcon("images/bullet3.png").getImage();
	Image bulletImg4 = new ImageIcon("images/bullet4.png").getImage();
	Image bulletImg5 = new ImageIcon("images/missle.png").getImage();
	Image bossBulletImg1 = new ImageIcon("BossBullet1.png").getImage();

	public int b1Width = bulletImg1.getWidth(null);
	public int b1Height = bulletImg1.getHeight(null);

	double bulletX;
	double bulletY;
	double bulletAngel = 270; // 珥앹븣媛곷룄
	double bulletSpeed = 2; // 珥앹븣�냽�룄

	int bulletWidth1;
	int bulletHeight1;

	public boolean isCrush; // 충돌범위에 들어왔는지 판별(충돌 연산)

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

		Thread bulletthread = new Thread(this); // 珥앹븣 異⑸룎 thread �깮�꽦, �떎�뻾
		bulletthread.start();
	}

	public void fire() {
		bulletX -= Math.cos(Math.toRadians(bulletAngel)) * bulletSpeed;
		bulletY -= Math.sin(Math.toRadians(bulletAngel)) * bulletSpeed;
	}

	@Override
	public void run() {
		while (playerPlane.getLifeCount() > 0) {
			crash();
			try {
				if (collision) {
					explosePlayer(playerPlane);
				}
				Thread.sleep(10);
				if (playerPlane.getLifeCount() <= 0) {
					Thread.sleep(100);
					System.exit(1);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

	public void crash() { // 異⑸룎�뿰�궛
		if (Math.abs(((playerPlane.getX() - 11) + playerPlane.getPlayerWidth() / 3)
				- (bulletX + bulletWidth1 / 3)) < (bulletWidth1 / 3 + playerPlane.getPlayerWidth() / 3)
				&& Math.abs(((playerPlane.getY() - 5) + playerPlane.getPlayerHeight() / 3)
						- (bulletY + bulletHeight1 / 3)) < (bulletHeight1 / 3 + playerPlane.getPlayerHeight() / 3)) {
			collision = true;
		} else {
			collision = false;
		}
	}

	public void explosePlayer(PlayerPlane playerPlane) { // 異⑸룎�썑 �씠誘몄� 蹂�寃� 諛� 紐⑹닲移댁슫�듃--

		try {
			ImageIcon explosionIcon = new ImageIcon("images/explosion.gif");
			playerPlane.setIcon(explosionIcon);
			Thread.sleep(1000);
			playerPlane.setIcon(playerPlane.playerIcon);

			playerPlane.setLifeCount(playerPlane.getLifeCount() - 1);
			playerPlane.setX((GameFrame.SCREEN_WIDTH / 2) - (playerPlane.playerWidth / 2));
			playerPlane.setY(GameFrame.SCREEN_HEIGHT - (playerPlane.playerHeight * 2));
			playerPlane.repaint();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

//	@Override
//	public void run() {
//		while (playerPlane.getLifeCount() > 0) { // �깮紐낆씠 0蹂대떎 �겕硫�
//			try {
//				crash();
//				if (collision) {
//					explosePlayer(playerPlane); // 異⑸룎 �룺諛� 硫붿꽌�뱶
//				}
//				Thread.sleep(10);
//				if (bulletX > 1000 || bulletX < -500 || bulletY < -500 || bulletY > 1000) {
//					System.out.println("bullet thread terminate");
//					return; // Thread 醫낅즺援щЦ
//				}
//
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//		}
//
//	}

//	public void crash() { //異⑸룎�뿰�궛
//        if(Math.abs((playerPlane.getX() + playerPlane.getPlayerWidth() / 2) - ( bulletX + playerPlane.getPlayerWidth()/ 2)) < ( bulletWidth1 / 2 + playerPlane.getPlayerWidth() / 2) &&
//                Math.abs( (playerPlane.getY() + playerPlane.getPlayerHeight() / 2) - (bulletY + bulletHeight1 / 2)) < ( bulletHeight1 /2 + playerPlane.getPlayerHeight() / 2)) {
//        		collision = true;} else {
//        			collision = false;
//        		}
//		
//	}

//	public void crash() { // 異⑸룎�뿰�궛
//		if (Math.abs(((playerPlane.getX() - 11) + playerPlane.getPlayerWidth() / 3)
//				- (bulletX + bulletWidth1 / 3)) < (bulletWidth1 / 3 + playerPlane.getPlayerWidth() / 3)
//				&& Math.abs(((playerPlane.getY() - 5) + playerPlane.getPlayerHeight() / 3)
//						- (bulletY + bulletHeight1 / 3)) < (bulletHeight1 / 3 + playerPlane.getPlayerHeight() / 3)) {
//			collision = true;
//		} else {
//			collision = false;
//		}
//	}

//	public void crush(PlayerPlane playerPlane) { 		//적비행기-Player 충돌 대기 메서드		
//		new Thread(new Runnable() {					
//			@Override
//			public void run() {				
//				while (playerPlane.getLifeCount() > 0) {	//생명이 0보다 크면 -- 충돌연산
//					isCrush = playerPlane.getX()+22 >= (int)bulletX+19 && playerPlane.getX()+22 <= (int)bulletX+bulletWidth1-19&&												// player비행기 기준 좌표 (0,0)
//							playerPlane.getY()+10 >= (int)bulletY+10 && playerPlane.getY()+10 <= (int)bulletY+bulletHeight1-10 ||
//							playerPlane.getX()+playerPlane.getPlayerWidth()-22 >= (int)bulletX+19 && playerPlane.getX()+playerPlane.getPlayerWidth()-22 <= (int)bulletX+bulletWidth1-19&&	//(1,0)
//							playerPlane.getY()+10>=(int)bulletY+10 && playerPlane.getY()+10<=(int)bulletY+bulletHeight1-10 ||
//							playerPlane.getX()+15>=(int)bulletX+19 && playerPlane.getX()+22<=(int)bulletX+bulletWidth1-19 &&												//(0,1)
//							playerPlane.getY()+playerPlane.getPlayerHeight()-10>=(int)bulletY+10 && playerPlane.getY()+playerPlane.getPlayerHeight()-10<=(int)bulletY+bulletHeight1-10 ||
//							playerPlane.getX()+playerPlane.getPlayerWidth()-22>=(int)bulletX+19 && playerPlane.getX()+playerPlane.getPlayerWidth()-22<=(int)bulletX+bulletWidth1-19 &&//(1,1)
//							playerPlane.getY()+playerPlane.getPlayerHeight()-10>=(int)bulletY+10 && playerPlane.getY()+playerPlane.getPlayerHeight()-10<=(int)bulletY+bulletHeight1-10;
//					try {
//						if (isCrush) {
//							explosePlayer(playerPlane);			//충돌 폭발 메서드
//						}
//						Thread.sleep(10);
//						if(playerPlane.getLifeCount() <= 0) {
//							Thread.sleep(1000);						//0.1초후
//							gameFrame.isgame = false;				//쓰레드 강제 종료	
//							gameFrame.change("gameTitle");			//타이틀 화면으로 돌아가기
//							break;
//						}
//									
//					} catch (Exception e) {
//						e.printStackTrace();
//					}
//				}
//			}
//		}).start();
//	}

//	public void explosePlayer(PlayerPlane playerPlane) { // 異⑸룎�썑 �씠誘몄� 蹂�寃� 諛� 紐⑹닲移댁슫�듃
//
//		try {
//			ImageIcon explosionIcon = new ImageIcon("images/explosion.gif");
//			playerPlane.setIcon(explosionIcon);
//			Thread.sleep(1000);
//			playerPlane.setIcon(playerPlane.playerIcon);
//			playerPlane.setLifeCount(playerPlane.getLifeCount() - 1);
//			System.out.println("�궓��紐⑹닲:" + playerPlane.getLifeCount());
//			playerPlane.setX(200);
//			playerPlane.setY(520);
//			playerPlane.repaint();
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//
//	}

}
