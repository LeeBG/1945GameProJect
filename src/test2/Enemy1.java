package test2;

import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;

import javax.swing.ImageIcon;

public class Enemy1 extends EnemyUnit {
	// 단순히 밑으로만 이동하는 유닛
	private Enemy1 enemy1 = this;
	private static final String TAG = "Enemy1 : ";


	public int count;

	static ArrayList<EnemyAttack> enemyAttackkList = new ArrayList<EnemyAttack>();
	private EnemyAttack enemyAttack;

	public Enemy1(PlayerPlane playerPlane, int x, int y, int w, int h) {
		this.playerPlane = playerPlane;
		this.enemyX = x;
		this.enemyY = y;
		this.enemyWidth = w;
		this.enemyHeight = h;
		this.enemyImage = new ImageIcon("images/enemy1.png").getImage();
		this.life =1;
				
		this.playerPlane.contextAdd(enemy1);

		
		this.move();
		this.crush();
	}
	
	



	public void move() {
		new Thread(new Runnable() {
			@Override
			public void run() {
				count = 0;
				while (true) {
					try {
						Thread.sleep(2);

						movedown();

//						if(enemyY>0 && enemyY<50 || enemyY>100 && enemyY<150 || enemyY>200 && enemyY<250)
//							moveleft();
//						if(enemyY>50 && enemyY<100 )
//							moveright();

						count++;

						if (enemyY > 900) {
							System.out.println("enemy1 쓰레드 종료");
							break;
						}

					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}).start();
	}

	public void crush() { // 적비행기-Player 충돌

		new Thread(new Runnable() {

			@Override
			public void run() {

				while (playerPlane.getLife() > 0) {

					if (Math.abs((playerPlane.getX() + playerPlane.getPlayerWidth() / 2)
							- (enemyX + playerPlane.getPlayerWidth() / 2)) < (enemyWidth / 2
									+ playerPlane.getPlayerWidth() / 2)
							&& Math.abs((playerPlane.getY() + playerPlane.getPlayerHeight() / 2)
									- (enemyY + enemyHeight / 2)) < (enemyHeight / 2
											+ playerPlane.getPlayerHeight() / 2)) {
						collision = true;
					} else {
						collision = false;
					}

					try {
						if (collision) {
							explosePlayer(playerPlane, enemy1); // 충돌 폭발 메서드
						}
						Thread.sleep(10);
//						if(playerPlane.getLife() <= 0) {
//							Thread.sleep(100);						//1초후
//							System.exit(1);							//프로그램 종료
//						}

					} catch (Exception e) {
						e.printStackTrace();
					}

				}

			}
		}).start();

	}

	public void enemyUpdate(Graphics g) {
		enemyDraw(g);
	}

	public void enemyDraw(Graphics g) { // 그림그리기
		g.drawImage(enemyImage, enemyX, enemyY, enemyWidth, enemyHeight, null);

	}

}
