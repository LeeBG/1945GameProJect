package Straikers2;

import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Player extends JLabel {

	private Player player = this;
	private static final String TAG = "Player : ";

	ImageIcon playerIcon = new ImageIcon("images/PlayerPlane1.png");
	Image playerImg = playerIcon.getImage();

	int playerWidth = playerImg.getWidth(null);
	int playerHeight = playerImg.getHeight(null);

	public int playerX = (VsBoss.SCREEN_WIDTH / 2) - (playerWidth / 2);
	public int playerY = VsBoss.SCREEN_HEIGHT - 100;

	public int count; // �Ѿ��� �ӵ��� �����ϱ� ���� �����Ѵ�
	public int wepponLevel = 0;
	public int restHp;

	public boolean isWepponLevelUp = false;
	public boolean isAttack = false;
	public boolean isUp = false;
	public boolean isDown = false;
	public boolean isLeft = false;
	public boolean isRight = false;

	ArrayList<Attack> playerAttackList = new ArrayList<Attack>();
	Attack playerAttack;
	Boss boss;

	public Player() {
		setIcon(playerIcon);
		move();
	}

	public void move() {
		new Thread(new Runnable() {
			@Override
			public void run() {
				count = 0; // count�� 0���� ����
				while (true) {
					try {
						Thread.sleep(5);
						keyProcess();
						playerAttackProcess();
						setLocation(playerX, playerY); // repaint()
//						System.out.println("x : " + playerX);
//						System.out.println("y : " + playerY);
						count++; // 1�� �þ��
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}).start();
	}

	private void keyProcess() {
		if (isAttack && count % 30 == 0) { // �Ѿ��� �߻� �ӵ��� ����
			if (wepponLevel == 0) { // �Ѿ� ���ٸ� �߻�
				playerAttack = new Attack(playerX + 20, playerY - 40, 90, 2); // �Ѿ��� �����Ǵ� ��ġ
				playerAttackList.add(playerAttack); // arrayList�� �����Ѵ�
			}
			if (wepponLevel == 1) { // �Ѿ� 2�� �߻�
				playerAttack = new Attack(playerX + 10, playerY - 40, 90, 2);
				playerAttackList.add(playerAttack);
				playerAttack = new Attack(playerX + 30, playerY - 40, 90, 2);
				playerAttackList.add(playerAttack);
			}
			if (wepponLevel == 2) { // �Ѿ� 3�� �߻�
				playerAttack = new Attack(playerX + 0, playerY - 40, 90, 2);
				playerAttackList.add(playerAttack);
				playerAttack = new Attack(playerX + 20, playerY - 40, 90, 2);
				playerAttackList.add(playerAttack);
				playerAttack = new Attack(playerX + 40, playerY - 40, 90, 2);
				playerAttackList.add(playerAttack);
			}
			if (wepponLevel == 3) { // �Ѿ� 4�� �߻�
				playerAttack = new Attack(playerX - 10, playerY - 40, 90, 2);
				playerAttackList.add(playerAttack);
				playerAttack = new Attack(playerX + 10, playerY - 40, 90, 2);
				playerAttackList.add(playerAttack);
				playerAttack = new Attack(playerX + 30, playerY - 40, 90, 2);
				playerAttackList.add(playerAttack);
				playerAttack = new Attack(playerX + 50, playerY - 40, 90, 2);
				playerAttackList.add(playerAttack);
			}
			if (wepponLevel == 4) { // �� �� �밢������ ������ �Ѿ� 2�� �߰�
				playerAttack = new Attack(playerX - 15, playerY - 40, 80, 2);
				playerAttackList.add(playerAttack);
				playerAttack = new Attack(playerX - 10, playerY - 40, 90, 2);
				playerAttackList.add(playerAttack);
				playerAttack = new Attack(playerX + 10, playerY - 40, 90, 2);
				playerAttackList.add(playerAttack);
				playerAttack = new Attack(playerX + 30, playerY - 40, 90, 2);
				playerAttackList.add(playerAttack);
				playerAttack = new Attack(playerX + 50, playerY - 40, 90, 2);
				playerAttackList.add(playerAttack);
				playerAttack = new Attack(playerX + 55, playerY - 40, 100, 2);
				playerAttackList.add(playerAttack);
			}
		}
		if (isUp && playerY > 0) {
			playerY--;
		}
		if (isDown && playerY < VsBoss.SCREEN_HEIGHT - (playerHeight + 40)) {
			playerY++;
		}
		if (isLeft && playerX > 0) {
			playerX--;
		}
		if (isRight && playerX < VsBoss.SCREEN_WIDTH - (playerWidth + 15)) {
			playerX++;
		}
	}

	public void playerUpdate(Graphics g) { // �÷��̾ ���� ��� �׸��� ���⼭ �����Ѵ�
		playerDraw(g);
	}

	private void playerAttackProcess() {
		for (int i = 0; i < playerAttackList.size(); i++) {
			playerAttack = playerAttackList.get(i);
			playerAttack.Fire();
			
			// �Ѿ��� ���� �浹�ϸ� �����
			if (playerAttack.BulletX < 0 || playerAttack.BulletX > VsBoss.SCREEN_WIDTH || playerAttack.BulletY < 0
					|| playerAttack.BulletY > VsBoss.SCREEN_HEIGHT) {
				playerAttackList.remove(playerAttack);
			}
			
//			// �Ѿ��� x,y���� ������ x,y��, ���� ���̸� ����Ͽ� �浹����
//			if (playerAttack.BulletX > (boss.bossX + 160) && playerAttack.BulletX < (boss.bossX + boss.bossWidth - 190) // ����
//					&& playerAttack.BulletY > (boss.bossY)
//					&& playerAttack.BulletY < (boss.bossY + boss.bossWidth - 190)) {// ���� ����
//				player.playerAttackList.remove(player.playerAttack);
//			}
		}
	}

	// �̹����� �׸���
	public void playerDraw(Graphics g) {
		for (int i = 0; i < playerAttackList.size(); i++) {
			playerAttack = playerAttackList.get(i);
			g.drawImage(playerAttack.playerBulletImg1, (int) playerAttack.BulletX, (int) playerAttack.BulletY, null);
			// PlayaerAttack�� �ڷ����� double�� �ΰ�, drawImage�� ���� ���� ����ȯ ���ش� (�ﰢ�Լ� ����� ����)
		}
	}

	public void setWepponLevelUp(boolean isWepponLevelUp) {
		this.isWepponLevelUp = isWepponLevelUp;
		if (isWepponLevelUp == true && wepponLevel < 4) {
			wepponLevel = wepponLevel + 1;
			System.out.println("���� ���� : " + wepponLevel);
			if (wepponLevel == 4) {
				System.out.println("���� ������ �ִ��Դϴ�");
			}
		}
	}

	public void setAttack(boolean isAttack) {
		this.isAttack = isAttack;
	}

	public void setUp(boolean isUp) {
		this.isUp = isUp;
	}

	public void setDown(boolean isDown) {
		this.isDown = isDown;
	}

	public void setLeft(boolean isLeft) {
		this.isLeft = isLeft;
	}

	public void setRight(boolean isRight) {
		this.isRight = isRight;
	}
}
