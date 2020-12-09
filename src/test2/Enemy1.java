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
		this.x = x;
		this.y = y;
		icEnemy1 = new ImageIcon("images/PLANE3.png");
		setIcon(icEnemy1);
		setSize(100, 50);
		setLocation(x, y);

		this.movedown();
		this.enemyAttack();
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

	private void enemyAttack() {

		new Thread(new Runnable() {
			
			@Override
			public void run() {
				while (true) {
					enemyBullet = new EnemyBullet(x - 20, y + 40); // 총알이 생성되는 위치
					enemyBullet.fire();

					System.out.println("적 총알 발사");
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
