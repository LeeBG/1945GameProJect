package test2;

import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;

import javax.swing.ImageIcon;

public class Enemy3 extends EnemyUnit {

	private Enemy3 enemyDown = this;
	private static final String TAG = "EnemyDown : ";
	
	Image EnemyDownImg = new ImageIcon("images/Enemy2.png").getImage();

	public int count; 

	ArrayList<EnemyAttack> enemyAttackkList = new ArrayList<EnemyAttack>();
	private EnemyAttack enemyAttack;

	public Enemy3(int x, int y) {
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
						Thread.sleep(3);	
			
						if (enemyX > 350 && enemyY < 400) {
							moveleft();
							movedown();
						}
						count++; 
			
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
			g.drawImage(EnemyDownImg, enemyX, enemyY,150,100, null);

		}
	

}
