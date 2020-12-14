package test2;

import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;

import javax.swing.ImageIcon;

public class Enemy4 extends EnemyUnit {

	private Enemy4 enemy4 = this;
	private static final String TAG = "Enemy4 : ";
	
	Image EnemyDownImg = new ImageIcon("images/enemy4.png").getImage();

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
						Thread.sleep(1);
						movedown();
				
						count++; 
						
						if (enemyY > 639) {
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



	public void enemyUpdate(Graphics g) {
		enemyDraw(g);
	}



	public void enemyDraw(Graphics g) { //그림그리기
			g.drawImage(EnemyDownImg, enemyX, enemyY,50,50, null);

		}
	

}
