package test2;

import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;

import javax.swing.ImageIcon;

public class Enemy3 extends EnemyUnit {

	private Enemy3 enemy3 = this;
	private static final String TAG = "Enemy3 : ";

	public int count;

	ArrayList<EnemyAttack> enemyAttackkList = new ArrayList<EnemyAttack>();
	private EnemyAttack enemyAttack;

	public Enemy3(PlayerPlane playerPlane, int x, int y,int w, int h) {
		this.playerPlane = playerPlane;
		this.enemyX = x;
		this.enemyY = y;
		this.enemyWidth = w;
		this.enemyHeight = h;
		this.enemyImage = new ImageIcon("images/enemy3.png").getImage();
		this.life = 3;
		
		this.playerPlane.contextAdd(enemy3);

		
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
						Thread.sleep(5);
				
						movedown();
						enemyY++;
						
						if (enemyY < 400) {
							moveleft();
		
						}
						bulletCreate();
						enemyAttack();
						count++;
						
						
						if (enemyY > 900) {
							System.out.println("enemy3 쓰레드 종료");
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
							explosePlayer(playerPlane, enemy3); // 충돌 폭발 메서드
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
	

	private void bulletCreate() {
		if (count % 100 == 0) {
			enemyAttack = new EnemyAttack(enemy3, playerPlane, enemyX + 20, enemyY + 40,270,3,20,20);
			enemyAttackkList.add(enemyAttack);
			
			
		}
	}

	public void enemyUpdate(Graphics g) {
		enemyDraw(g);
	}

	private void enemyAttack() {
		for (int i = 0; i < enemyAttackkList.size(); i++) {
			enemyAttack = enemyAttackkList.get(i);
			enemyAttack.fire();

		}
	}

	public void enemyDraw(Graphics g) { // 그림그리기
		g.drawImage(enemyImage, enemyX, enemyY,enemyWidth, enemyHeight, null);
		for (int i = 0; i < enemyAttackkList.size(); i++) {
			enemyAttack = enemyAttackkList.get(i);
			g.drawImage(enemyAttack.bulletImg1, enemyAttack.bulletX, enemyAttack.bulletY, enemyAttack.bulletWidth1, enemyAttack.bulletHeight1, null);

		}
	}

}
