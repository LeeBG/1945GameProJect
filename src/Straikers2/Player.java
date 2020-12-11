package Straikers2;

import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Player extends JLabel {

	private Player player = this;
	private static final String TAG = "Player : ";

	Image playerImg = new ImageIcon("images/PlayerPlane1.png").getImage();

	int playerWidth = playerImg.getWidth(null);
	int playerHeight = playerImg.getHeight(null);

	public int playerX = (VsBoss.SCREEN_WIDTH / 2) - (playerWidth / 2);
	public int playerY = VsBoss.SCREEN_HEIGHT - 100;

	public int count; // �Ѿ��� �ӵ��� �����ϱ� ���� �����Ѵ�
	int wepponLevel = 0;

	public boolean isWepponLevelUp = false;
	public boolean isAttack = false;
	public boolean isUp = false;
	public boolean isDown = false;
	public boolean isLeft = false;
	public boolean isRight = false;

	ArrayList<PlayerAttack> playerAttackList = new ArrayList<PlayerAttack>();
	private PlayerAttack playerAttack;
	private Boss boss = new Boss();

	public Player() {
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
			if (wepponLevel == 0) {
				playerAttack = new PlayerAttack(playerX + 20, playerY - 40); // �Ѿ��� �����Ǵ� ��ġ
				playerAttackList.add(playerAttack); // arrayList�� �����Ѵ�
			}
			if (wepponLevel == 1) {
				playerAttack = new PlayerAttack(playerX + 10, playerY - 40); // �Ѿ��� �����Ǵ� ��ġ
				playerAttackList.add(playerAttack); // arrayList�� �����Ѵ�
				playerAttack = new PlayerAttack(playerX + 30, playerY - 40); // �Ѿ��� �����Ǵ� ��ġ
				playerAttackList.add(playerAttack); // arrayList�� �����Ѵ�
			}
			if (wepponLevel == 2) {
				playerAttack = new PlayerAttack(playerX + 0, playerY - 40); // �Ѿ��� �����Ǵ� ��ġ
				playerAttackList.add(playerAttack); // arrayList�� �����Ѵ�
				playerAttack = new PlayerAttack(playerX + 20, playerY - 40); // �Ѿ��� �����Ǵ� ��ġ
				playerAttackList.add(playerAttack); // arrayList�� �����Ѵ�
				playerAttack = new PlayerAttack(playerX + 40, playerY - 40); // �Ѿ��� �����Ǵ� ��ġ
				playerAttackList.add(playerAttack); // arrayList�� �����Ѵ�
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
			playerAttack.fire();

			// �Ѿ��� x,y���� ������ x,y��, ���� ���̸� ����Ͽ� �浹����
			if (playerAttack.bulletX > (boss.bossX + 160)
					&& playerAttack.bulletX < (boss.bossX + boss.bossWidth - 190) // ����																								// ����
					&& playerAttack.bulletY > (boss.bossY)
					&& playerAttack.bulletY < (boss.bossY + boss.bossWidth - 160)) {// ���� ����
				playerAttackList.remove(playerAttack);
			}
		}
	}

	// �̹����� �׸���
	public void playerDraw(Graphics g) {
		g.drawImage(playerImg, playerX, playerY, null); // �÷��̾� ĳ����
		for (int i = 0; i < playerAttackList.size(); i++) {
			playerAttack = playerAttackList.get(i);
			g.drawImage(playerAttack.bulletImg1, playerAttack.bulletX, playerAttack.bulletY, null);
		}
	}

	public void setWepponLevelUp(boolean isWepponLevelUp) {
		this.isWepponLevelUp = isWepponLevelUp;
		if (isWepponLevelUp == true) {
			wepponLevel = wepponLevel + 1;
			if (wepponLevel == 3) {
				wepponLevel = 0;
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
