package test2;

import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;

import javax.swing.ImageIcon;

public class Enemy5 extends EnemyUnit {

	private Enemy5 enemyDown = this;
	private static final String TAG = "EnemyDown : ";
	
	Image EnemyDownImg = new ImageIcon("images/PLANE3.png").getImage();

	public int count; 

	ArrayList<EnemyAttack> enemyAttackkList = new ArrayList<EnemyAttack>();
	private EnemyAttack enemyAttack;

	public Enemy5(PlayerPlane playerPlane, int x, int y) {
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
						
						if (enemyY > 639) {
							System.out.println("movedown 쓰레드 종료");
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
		if (count % 100 == 0) {
			enemyAttack = new EnemyAttack(enemyX + 30, enemyY + 40);
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
			g.drawImage(EnemyDownImg, enemyX, enemyY, null);
			for (int i = 0; i < enemyAttackkList.size(); i++) {
				enemyAttack = enemyAttackkList.get(i);
				g.drawImage(enemyAttack.bulletImg1, enemyAttack.bulletX, enemyAttack.bulletY, 10,10, null);

			}
		}
	

}
