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

	ImageIcon playerIcon = new ImageIcon("images/PlayerPlane1.png");
	Image playerImg = playerIcon.getImage();

	public int playerX = 210;
	public int playerY = 500;

	int playerWidth = playerImg.getWidth(null);
	int playerHeight = playerImg.getHeight(null);

	public int count; // �Ѿ��� �ӵ��� �����ϱ� ���� �����Ѵ�

	public boolean isChangeWeppon1 = true; // ���� 1�� �⺻���̱� ������ true�� ����
	public boolean isChangeWeppon2 = false;
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
						System.out.println("X : " + playerX);
						System.out.println("Y : " + playerY);
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
		if (isAttack && count % 20 == 0) { // �Ѿ��� �ӵ��� ����
			playerAttack = new PlayerAttack(playerX + 20, playerY - 40); // �Ѿ��� �����Ǵ� ��ġ
			playerAttackList.add(playerAttack); // arrayList�� �����Ѵ�
		}
		if (isUp) {
			playerY--;
		}
		if (isDown) {
			playerY++;
		}
		if (isLeft) {
			playerX--;
		}
		if (isRight) {
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
			if (playerAttack.bulletX > (boss.bossX - 5) && playerAttack.bulletX < (boss.bossX + boss.bossWidth - 40)
					&& playerAttack.bulletY > (boss.bossY)
					&& playerAttack.bulletY < (boss.bossY + boss.bossWidth - 60)) {
				playerAttackList.remove(playerAttack);
			}
		}
	}

	// �̹����� �׸���
	public void playerDraw(Graphics g) {
		g.drawImage(playerImg, playerX, playerY, null); // �÷��̾� ĳ����
		// ����1�� true�� ���� 1�� �̹���
		if (isChangeWeppon1 == true) {
			for (int i = 0; i < playerAttackList.size(); i++) {
				playerAttack = playerAttackList.get(i);
				g.drawImage(playerAttack.bulletImg1, playerAttack.bulletX, playerAttack.bulletY, null);
			}
		}
		// ����2�� true�� ���� 2�� �̹���
		if (isChangeWeppon2 == true) {
			for (int i = 0; i < playerAttackList.size(); i++) {
				playerAttack = playerAttackList.get(i);
				g.drawImage(playerAttack.bulletImg2, playerAttack.bulletX, playerAttack.bulletY, null);
			}
		}
	}

	public void setChangeWeppon1(boolean isChangeWeppon1) {
		this.isChangeWeppon1 = isChangeWeppon1;
	}

	public void setChangeWeppon2(boolean isChangeWeppon2) {
		this.isChangeWeppon2 = isChangeWeppon2;
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
