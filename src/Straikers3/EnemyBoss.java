package Straikers3;

import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class EnemyBoss extends EnemyUnit {

	private EnemyBoss enemyBoss = this;
	private static final String TAG = "Boss : ";

	ImageIcon bossIcon = new ImageIcon("images/bossSizeup.gif");
	Image bossImg = bossIcon.getImage();

	int bossWidth = bossImg.getWidth(null);
	int bossHeight = bossImg.getHeight(null);

	double way;

	int bCount;
	boolean firePase = false;

	ArrayList<EnemyAttack> bossAttackList = new ArrayList<EnemyAttack>();
	EnemyAttack bossAttack;

	public EnemyBoss(PlayerPlane playerPlane, int x, int y) {
		this.playerPlane = playerPlane;
		this.enemyX = x;
		this.enemyY = y;
		this.move();
	}

	public void move() {
		new Thread(new Runnable() {
			@Override
			public void run() {
				bCount = 0;
				while (true) {
					try {
						Thread.sleep(5);
						bossAttackProcess();
						bulletCreate();
						bCount++;
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}).start();
	}

	private void bulletCreate() {
		if (bCount % 500 == 0) {
			for (int j = 1; j <= 5; j++) {
				way = 180 + (30 * j);
				bossAttack = new EnemyAttack(enemyBoss, playerPlane, enemyX + 80, enemyY + 600, way, 0.5, 0, 0); // 총알이 생성되는																											// 위치
				bossAttackList.add(bossAttack); // arrayList에 저장한다
			}

			for (int j = 1; j <= 5; j++) {
				way = 180 + (30 * j);
				bossAttack = new EnemyAttack(enemyBoss, playerPlane, enemyX + 480, enemyY + 600, way, 0.5, 0, 0); // 총알이 생성되는 위치
				bossAttackList.add(bossAttack); // arrayList에 저장한다
			}
		}
	}

	public void enemyUpdate(Graphics g) {
		bossDraw(g);
	}

	public void bossAttackProcess() {
		for (int i = 0; i < bossAttackList.size(); i++) {
			bossAttack = bossAttackList.get(i);
			bossAttack.fire();

			// 총알이 벽과 충돌하면 사라짐
			if (bossAttack.bulletX < -10 || bossAttack.bulletX > 700 || bossAttack.bulletY < -10
					|| bossAttack.bulletY > 639) {
				bossAttackList.remove(bossAttack);
			}
		}
	}

	public void bossDraw(Graphics g) {
		g.drawImage(bossImg, enemyX, enemyY, bossWidth, bossHeight, null);
		for (int i = 0; i < bossAttackList.size(); i++) {
			bossAttack = bossAttackList.get(i);
			g.drawImage(bossAttack.bulletImg1, (int) bossAttack.bulletX, (int) bossAttack.bulletY, bossAttack.b1Width/2, bossAttack.b1Height/2, null);
			// PlayaerAttack의 자료형을 double로 두고, drawImage를 돌릴 때만 형변환 해준다 (삼각함수 계산을 위해)
		}
	}
}
