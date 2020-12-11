package test2;

import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import test1.Map04;

public class Enemy4 extends JLabel {

	private Enemy4 enemy4 = this;
	private static final String TAG = "Enemy4 : ";
	public Map05 map05;

	Image playerImg = new ImageIcon("images/PLANE3.png").getImage();

	int playerWidth = playerImg.getWidth(null);
	int playerHeight = playerImg.getHeight(null);

	public int playerX = 100;
	public int playerY = 50;

	public int count; // �Ѿ��� �ӵ��� �����ϱ� ���� �����Ѵ�

	public boolean isUp = false;
	public boolean isDown = false;
	public boolean isLeft = false;
	public boolean isRight = false;

	ArrayList<PlayerAttack> playerAttackList = new ArrayList<PlayerAttack>();
	private PlayerAttack playerAttack;

	public Enemy4() {
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
		playerY++;
		if (count % 100 == 0) { // �Ѿ��� �߻� �ӵ��� ����
			playerAttack = new PlayerAttack(playerX + 20, playerY + 40);
			playerAttackList.add(playerAttack);
		}

	}

	public void playerUpdate(Graphics g) {
		playerDraw(g);
	}

	private void playerAttackProcess() {
		for (int i = 0; i < playerAttackList.size(); i++) {
			playerAttack = playerAttackList.get(i);
			playerAttack.fire();
			System.out.println("발사");

		}
	}

	// �̹����� �׸���
	public void playerDraw(Graphics g) {

		
			g.drawImage(playerImg, playerX, playerY, null);
			for (int i = 0; i < playerAttackList.size(); i++) {
				playerAttack = playerAttackList.get(i);
				g.drawImage(playerAttack.bulletImg1, playerAttack.bulletX, playerAttack.bulletY, null);
				//System.out.println(playerAttack.bulletX + "    " + playerAttack.bulletY);

			}
		}
	

}
