package Straikers3;

import java.awt.Image;

import javax.swing.ImageIcon;

public class EnemyAttack implements Runnable {

	private EnemyUnit enemyunit;
	private EnemyBoss enemyBoss;
	private PlayerPlane playerPlane;
	private boolean collision;

	Image bulletImg1 = new ImageIcon("images/bullet1.png").getImage();
	Image bulletImg2 = new ImageIcon("images/bullet2.png").getImage();
	Image bulletImg3 = new ImageIcon("images/bullet3.png").getImage();
	Image bulletImg4 = new ImageIcon("images/bullet4.png").getImage();
	Image bossBulletImg1 = new ImageIcon("images/bossBullet1").getImage();

	int b1Width = bulletImg1.getWidth(null);
	int b1Height = bulletImg1.getHeight(null);
	int b2Width = bulletImg2.getWidth(null);
	int b2Height = bulletImg2.getHeight(null);
	int b3Width = bulletImg3.getWidth(null);
	int b3Height = bulletImg3.getHeight(null);
	int b4Width = bulletImg4.getWidth(null);
	int b4Height = bulletImg4.getHeight(null);
	int bB1Width = bossBulletImg1.getWidth(null);
	int bB1Height = bossBulletImg1.getHeight(null);
	
	
	double bulletX;
	double bulletY;
	double bulletAngel = 270; // 珥앹븣媛곷룄
	double bulletSpeed = 2; // 珥앹븣�냽�룄
	int bulletWidth;
	int bulletHeight;

	public EnemyAttack(int x, int y) {
		this.bulletX = x;
		this.bulletY = y;

	}

	public EnemyAttack(EnemyUnit enemyunit, PlayerPlane playerPlane, int bulletX, int bulletY, double bulletAngel,
			double bulletSpeed, int bulletWidth, int bulletHeight) {

		this.enemyunit = enemyunit;
		this.playerPlane = playerPlane;
		this.bulletX = bulletX; // 총알 x좌표
		this.bulletY = bulletY; // 총알 y좌표
		this.bulletAngel = bulletAngel; // 발사 각도
		this.bulletSpeed = bulletSpeed; // 발사 스피드
		this.bulletWidth = bulletWidth; // 총알 넓이
		this.bulletHeight = bulletHeight; // 총알 높이

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
		while (playerPlane.getLife() > 0) {
			crash();
			try {
				if (collision) {
					explosePlayer(playerPlane);
				}
				Thread.sleep(10);
				if (playerPlane.getLife() <= 0) {
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
				- (bulletX + bulletWidth / 3)) < (bulletWidth / 3 + playerPlane.getPlayerWidth() / 3)
				&& Math.abs(((playerPlane.getY() - 5) + playerPlane.getPlayerHeight() / 3)
						- (bulletY + bulletHeight / 3)) < (bulletHeight / 3 + playerPlane.getPlayerHeight() / 3 )) {
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
			
			if (playerPlane.select == 1) {
				playerPlane.setIcon(playerPlane.icPlayer);
			}
			if (playerPlane.select == 2) {
				playerPlane.setIcon(playerPlane.icPlayer2);
			}
			if (playerPlane.select == 3) {
				playerPlane.setIcon(playerPlane.icPlayer3);
			}

			playerPlane.setLife(playerPlane.getLife() - 1);
			playerPlane.setX((Map06.SCREEN_WIDTH / 2) - (playerPlane.playerWidth / 2));
			playerPlane.setY(Map06.SCREEN_HEIGHT - (playerPlane.playerHeight * 2));
			playerPlane.repaint();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
