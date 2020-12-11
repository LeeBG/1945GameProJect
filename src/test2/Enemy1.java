package test2;

import java.awt.Image;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//j라벨은 LOMBOK 안 먹음
public class Enemy1 extends EnemyUnit {

	public Enemy1 Enemy1 = this;
	public final static String TAG = "Enemy1: ";
	private Image image = new ImageIcon("images/PLANE3.png").getImage();
	private ImageIcon icEnemy1;
	private EnemyBullet enemyBullet;

	ArrayList<EnemyBullet> enemyBullets = new ArrayList<>();

	Enemy1(int x, int y) {
		this.EnemyX = x;
		this.EnemyY = y;
		icEnemy1 = new ImageIcon("images/PLANE3.png");
		setIcon(icEnemy1);
		setSize(100, 50);
		setLocation(x, y);

		this.movedown2();
		// this.movedown();

		// this.enemyAttack();

	}

	public Enemy1 getEnemy1() {
		return Enemy1;
	}

	public void setEnemy1(Enemy1 Enemy1) {
		this.Enemy1 = Enemy1;
	}

	public ImageIcon getIcEnemy1() {
		return icEnemy1;
	}

	public void setIcEnemy1(ImageIcon icEnemy1) {
		this.icEnemy1 = icEnemy1;
	}

	public static String getTag() {
		return TAG;
	}

	public EnemyBullet getEnemyBullet() {
		return enemyBullet;
	}

	public void setEnemyBullet(EnemyBullet enemyBullet) {
		this.enemyBullet = enemyBullet;
	}

	public ArrayList<EnemyBullet> getEnemyBullets() {
		return enemyBullets;
	}

	public void setEnemyBullets(ArrayList<EnemyBullet> enemyBullets) {
		this.enemyBullets = enemyBullets;
	}

	private void enemyAttack() {

//
//		enemyBullets.add(new EnemyBullet(EnemyX + 30, EnemyY + 60)); //총알이 생성되는 위치
//
//		System.out.println("EnemyY: " + EnemyY + " bulletAppear:  " + bulletAppear + "  적 bullet 생성");
//	
//		
//		if (bulletAppear % 100 == 0) {
//			enemyBullets.add(new EnemyBullet(EnemyX + 30, EnemyY + 60)); //총알이 생성되는 위치
//			//enemyBullets.get(0).fire();
//			System.out.println("EnemyY: " + EnemyY + " bulletAppear:  " + bulletAppear + "  적 bullet 생성");
//		}

		if(bulletAppear %100 == 0) {
			System.out.println("적 bullet 생성");
			enemyBullet = new EnemyBullet(EnemyX+30, EnemyY+60);
		}
	}

	public void movedown2() {

		new Thread(new Runnable() {

			@Override
			public void run() {
				while (true) {

					enemyAttack();
					EnemyY++;
					bulletAppear++;
					
					System.out.println(EnemyY);
					setLocation(EnemyX, EnemyY);
				
					
					if (EnemyY > 639) {
						System.out.println("movedown2 쓰레드 종료");
						break;
					}

					try {
						Thread.sleep(5);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}).start();

	}

	public void leftdown() {
		movedown();
		moveleft();

	}

}
