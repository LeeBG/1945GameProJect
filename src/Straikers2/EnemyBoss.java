package Straikers2;

import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class EnemyBoss extends JLabel {

	private EnemyBoss boss = this;
	private static final String TAG = "Boss : ";

	ImageIcon bossIcon = new ImageIcon("images/bossSizeup.gif");
	Image bossImg = bossIcon.getImage();

	int bossWidth = bossImg.getWidth(null);
	int bossHeight = bossImg.getHeight(null);

	int bossX = (VsBoss.SCREEN_WIDTH / 2) - (bossWidth / 2);
	int bossY = -280;

	double way;
	boolean bp = false;
	int bCount;
	int bossAttackCount;

	ArrayList<Attack> bossAttackList = new ArrayList<Attack>();
	Attack bossAttack;
	Player player;

//	public int bossX = 100;
//	public int bossY = 10;

	public EnemyBoss() {
		this.bossPattern();
	}

	public void bossPattern() {
		new Thread(new Runnable() {
			@Override
			public void run() {
				bCount = 0;
				while (true) {
					try {
						Thread.sleep(5);
						firePattern1();
						bossAttackProcess();
						setIcon(bossIcon);
						setLocation(bossX, bossY);
						repaint();
						bCount++;
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}).start();
	}

	private void firePattern1() {
		if (bCount % 100 == 0) {
			if (bp == false) {
				bp = true;
				for (int j = 1; j <= 9; j++) {
					way = 180 + (15 * j);
					bossAttack = new Attack(70, 310, way, 0.8); // 총알이 생성되는 위치
					bossAttackList.add(bossAttack); // arrayList에 저장한다
				}

				for (int j = 1; j <= 10; j++) {
					way = 180 + (17 * j);
					bossAttack = new Attack(470, 310, way, 0.8); // 총알이 생성되는 위치
					bossAttackList.add(bossAttack); // arrayList에 저장한다
				}
			} else if (bp == true) {
				bp = false;
				for (int j = 1; j <= 10; j++) {
					way = 180 + (14 * j);
					bossAttack = new Attack(70, 310, way, 0.8); // 총알이 생성되는 위치
					bossAttackList.add(bossAttack); // arrayList에 저장한다
				}

				for (int j = 1; j <= 9; j++) {
					way = 180 + (18 * j);
					bossAttack = new Attack(470, 310, way, 0.8); // 총알이 생성되는 위치
					bossAttackList.add(bossAttack); // arrayList에 저장한다
				}
			}
		}
	}

	public void bossUpdate(Graphics g) {
		bossDraw(g);
	}

	public void bossAttackProcess() {
		for (int i = 0; i < bossAttackList.size(); i++) {
			bossAttack = bossAttackList.get(i);
			bossAttack.Fire();

			// 총알이 벽과 충돌하면 사라짐
			if (bossAttack.BulletX < -10 || bossAttack.BulletX > VsBoss.SCREEN_WIDTH || bossAttack.BulletY < -10
					|| bossAttack.BulletY > VsBoss.SCREEN_HEIGHT) {
				bossAttackList.remove(bossAttack);
			}
		}
	}

	public void bossDraw(Graphics g) {
		for (int j = 0; j < bossAttackList.size(); j++) {
			bossAttack = bossAttackList.get(j);
			g.drawImage(bossAttack.bossBulletImg1, (int) bossAttack.BulletX, (int) bossAttack.BulletY, null);
			// PlayaerAttack의 자료형을 double로 두고, drawImage를 돌릴 때만 형변환 해준다 (삼각함수 계산을 위해)
		}
	}
}
