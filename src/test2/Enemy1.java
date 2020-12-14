package test2;

import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;

import javax.swing.ImageIcon;

public class Enemy1 extends EnemyUnit {
	
	private Enemy1 enemy1 = this;
	private static final String TAG = "EnemyDown : ";
	private PlayerPlane playerPlane;
		
	Image Enemy1Img = new ImageIcon("images/enemy_plane_yellow_1.png").getImage();

	public int count; 

	ArrayList<EnemyAttack> enemyAttackkList = new ArrayList<EnemyAttack>();
	private EnemyAttack enemyAttack;

	public Enemy1(PlayerPlane playerPlane, int x, int y) {
		this.playerPlane = playerPlane;
		this.enemyX = x;
		this.enemyY = y;
		this.move();
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
						bulletCreate();
						enemyAttack();
						count++; 
						
						if (enemyY > 700) {
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

		
	private void bulletCreate() {
		if (count % 300 == 0) {
			enemyAttack = new EnemyAttack(enemy1, playerPlane, enemyX + 15, enemyY + 30,270,2,10,10);
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


	public void enemyDraw(Graphics g) { //그림그리기
			g.drawImage(Enemy1Img, enemyX, enemyY, null);
			for (int i = 0; i < enemyAttackkList.size(); i++) {
				enemyAttack = enemyAttackkList.get(i);
				g.drawImage(enemyAttack.bulletImg1, enemyAttack.bulletX, enemyAttack.bulletY, enemyAttack.bulletWidth1, enemyAttack.bulletHeight1, null);

			}
		}
	

}
