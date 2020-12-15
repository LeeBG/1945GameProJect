package test2;

import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;

import javax.swing.ImageIcon;

public class Enemy4 extends EnemyUnit {
	
	private Enemy4 enemy4 = this;
	private static final String TAG = "Enemy4 : ";
		
	Image enemy4Img = new ImageIcon("images/enemy4.png").getImage();

	public int count; 

	ArrayList<EnemyAttack> enemyAttackkList = new ArrayList<EnemyAttack>();
	private EnemyAttack enemyAttack;

	public Enemy4(PlayerPlane playerPlane, int x, int y) {
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
						
						if (enemyY > 800) {
							System.out.println("enemy4 쓰레드 종료");
							break;
						}
						
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}).start();
	}

		
	private void bulletCreate() { //총알 생성
		if (count % 300 == 0) { //count: 총알 발사 속도 조절			
			enemyAttack = new EnemyAttack(enemy4, playerPlane, enemyX + 15, enemyY + 30,270,2,20,20);
			//컨텍스트 넘기고, 총알생성위치, 크기, 속도, 각도 조절 
			enemyAttackkList.add(enemyAttack);
		}
	}


	public void enemyUpdate(Graphics g) {
		enemyDraw(g);
	}

	
	private void enemyAttack() { //총알발사
		for (int i = 0; i < enemyAttackkList.size(); i++) {
			enemyAttack = enemyAttackkList.get(i);
			enemyAttack.fire();

		}
	}


	public void enemyDraw(Graphics g) { //그림그리기
			g.drawImage(enemy4Img, enemyX, enemyY, null); //적 그리기
			for (int i = 0; i < enemyAttackkList.size(); i++) { //총알 그리기
				enemyAttack = enemyAttackkList.get(i);
				g.drawImage(enemyAttack.bulletImg1, enemyAttack.bulletX, enemyAttack.bulletY, enemyAttack.bulletWidth1, enemyAttack.bulletHeight1, null);

			}
		}
	

}
