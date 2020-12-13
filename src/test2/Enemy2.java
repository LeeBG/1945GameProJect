package test2;

import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;

import javax.swing.ImageIcon;

public class Enemy2 extends EnemyUnit {

	private Enemy2 enemy2 = this;
	private static final String TAG = "Enemy2 : ";

	Image Enemy2Img = new ImageIcon("images/enemy3U.png").getImage();

	public int count;

	ArrayList<EnemyAttack> enemyAttackkList = new ArrayList<EnemyAttack>();
	private EnemyAttack enemyAttack;

	public Enemy2(int x, int y) {
		this.enemyX = x;
		this.enemyY = y;
		
		if(x<100) {
		this.move();}else {
			this.move2();
		}
	}

	public void move() {
		new Thread(new Runnable() {
			@Override
			public void run() {
				count = 0;
				while (true) {
					try {
						Thread.sleep(5);

						if (enemyX < 150 && enemyY > 50) {
							moveup();
							moveright();
						}
						bulletCreate();
						enemyAttack();
						count++;



					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}).start();
	}
	
	
	public void move2() {
		new Thread(new Runnable() {
			@Override
			public void run() {
				count = 0;
				while (true) {
					try {
						Thread.sleep(5);

						if (enemyX < 150 && enemyY > 50) {
							moveup();
							moveleft();
						}
						bulletCreate();
						enemyAttack();
						count++;



					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}).start();
	}
	
	
	
	

	private void bulletCreate() {
		if (count % 50 == 0) {
			enemyAttack = new EnemyAttack(enemyX + 30, enemyY + 40);
			enemyAttackkList.add(enemyAttack);
			enemyAttack = new EnemyAttack(enemyX + 60, enemyY + 40);
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
		g.drawImage(Enemy2Img, enemyX, enemyY,200,150, null);
		for (int i = 0; i < enemyAttackkList.size(); i++) {
			enemyAttack = enemyAttackkList.get(i);
			g.drawImage(enemyAttack.bulletImg1, enemyAttack.bulletX, enemyAttack.bulletY, 10, 10, null);

		}
	}

}
