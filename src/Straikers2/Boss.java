package Straikers2;

import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Boss extends JLabel {

	private Boss boss = this;
	private static final String TAG = "Boss : ";

	Image bossImg = new ImageIcon("images/bossSizeup.gif").getImage();

	int bossWidth = bossImg.getWidth(null);
	int bossHeight = bossImg.getHeight(null);

	int bossX = (VsBoss.SCREEN_WIDTH / 2) - (bossWidth / 2);
	int bossY = -280;

	int bCount;
	boolean triger = false;

	ArrayList<BossAttack> bossAttackList = new ArrayList<BossAttack>();
	BossAttack bossAttack;

//	public int bossX = 100;
//	public int bossY = 10;

	public Boss() {
		bossPattern();
	}

	public void bossPattern() {
		new Thread(new Runnable() {
			@Override
			public void run() {
				bCount = 0;
				try {
					Thread.sleep(5);
					firePattern1();
					bossAttackProcess();
					setLocation(bossX, bossY);
					bCount++;
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}).start();
	}

	private void firePattern1() {
		for (int i = 1; i <= 6; i++) {
			int way = 180 + (23 * i);
			bossAttack = new BossAttack(70, 310, way, 0.3); // 총알이 생성되는 위치
			bossAttackList.add(bossAttack); // arrayList에 저장한다
		}

		for (int i = 24; i <= 34; i++) {
			int way = 180 + (27 * i);
			bossAttack = new BossAttack(470, 310, way, 0.3); // 총알이 생성되는 위치
			bossAttackList.add(bossAttack); // arrayList에 저장한다
		}
	}

	public void bossAttackProcess() {
		for (int i = 0; i < bossAttackList.size(); i++) {
			bossAttack = bossAttackList.get(i);
			bossAttack.fire();

			if (bossAttack.bulletX < 0 || bossAttack.bulletX > VsBoss.SCREEN_WIDTH || bossAttack.bulletY < 0
					|| bossAttack.bulletY > VsBoss.SCREEN_HEIGHT) {
				bossAttackList.remove(bossAttack);
			}
		}
	}

	public void bossUpdate(Graphics g) {
		bossDraw(g);
	}

	public void bossDraw(Graphics g) {
		g.drawImage(bossImg, bossX, bossY, null);
		for (int i = 0; i < bossAttackList.size(); i++) {
			bossAttack = bossAttackList.get(i);
			g.drawImage(bossAttack.bossBulletImg1, (int) bossAttack.bulletX, (int) bossAttack.bulletY, null);
			// PlayaerAttack의 자료형을 double로 두고, drawImage를 돌릴 때만 형변환 해준다 (삼각함수 계산을 위해)
		}
	}
}
