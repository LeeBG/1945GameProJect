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

	public int count; // 총알의 속도를 조절하기 위해 선언한다
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
				count = 0; // count를 0으로 설정
				while (true) {
					try {
						Thread.sleep(5);
						keyProcess();
						playerAttackProcess();
						setLocation(playerX, playerY); // repaint()
//						System.out.println("x : " + playerX);
//						System.out.println("y : " + playerY);
						count++; // 1씩 늘어난다
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}).start();
	}

	private void keyProcess() {
		if (isAttack && count % 30 == 0) { // 총알의 발사 속도를 조절
			if (wepponLevel == 0) { // 총알 한줄만 발사
				playerAttack = new Attack(playerX + 20, playerY - 40, 90, 2); // 총알이 생성되는 위치
				playerAttackList.add(playerAttack); // arrayList에 저장한다
			}
			if (wepponLevel == 1) { // 총알 2줄 발사
				playerAttack = new Attack(playerX + 10, playerY - 40, 90, 2);
				playerAttackList.add(playerAttack);
				playerAttack = new Attack(playerX + 30, playerY - 40, 90, 2);
				playerAttackList.add(playerAttack);
			}
			if (wepponLevel == 2) { // 총알 3줄 발사
				playerAttack = new Attack(playerX + 0, playerY - 40, 90, 2);
				playerAttackList.add(playerAttack);
				playerAttack = new Attack(playerX + 20, playerY - 40, 90, 2);
				playerAttackList.add(playerAttack);
				playerAttack = new Attack(playerX + 40, playerY - 40, 90, 2);
				playerAttackList.add(playerAttack);
			}
			if (wepponLevel == 3) { // 총알 4줄 발사
				playerAttack = new Attack(playerX - 10, playerY - 40, 90, 2);
				playerAttackList.add(playerAttack);
				playerAttack = new Attack(playerX + 10, playerY - 40, 90, 2);
				playerAttackList.add(playerAttack);
				playerAttack = new Attack(playerX + 30, playerY - 40, 90, 2);
				playerAttackList.add(playerAttack);
				playerAttack = new Attack(playerX + 50, playerY - 40, 90, 2);
				playerAttackList.add(playerAttack);
			}
			if (wepponLevel == 4) { // 양 옆 대각선으로 나가는 총알 2줄 추가
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

	public void playerUpdate(Graphics g) { // 플레이어에 관한 모든 그림은 여기서 정리한다
		playerDraw(g);
	}

	private void playerAttackProcess() {
		for (int i = 0; i < playerAttackList.size(); i++) {
			playerAttack = playerAttackList.get(i);
			playerAttack.Fire();
			
			// 총알이 벽과 충돌하면 사라짐
			if (playerAttack.BulletX < 0 || playerAttack.BulletX > VsBoss.SCREEN_WIDTH || playerAttack.BulletY < 0
					|| playerAttack.BulletY > VsBoss.SCREEN_HEIGHT) {
				playerAttackList.remove(playerAttack);
			}
			
//			// 총알의 x,y값과 보스의 x,y값, 넓이 높이를 계산하여 충돌판정
//			if (playerAttack.BulletX > (boss.bossX + 160) && playerAttack.BulletX < (boss.bossX + boss.bossWidth - 190) // 가로
//					&& playerAttack.BulletY > (boss.bossY)
//					&& playerAttack.BulletY < (boss.bossY + boss.bossWidth - 190)) {// 세로 판정
//				player.playerAttackList.remove(player.playerAttack);
//			}
		}
	}

	// 이미지를 그린다
	public void playerDraw(Graphics g) {
		for (int i = 0; i < playerAttackList.size(); i++) {
			playerAttack = playerAttackList.get(i);
			g.drawImage(playerAttack.playerBulletImg1, (int) playerAttack.BulletX, (int) playerAttack.BulletY, null);
			// PlayaerAttack의 자료형을 double로 두고, drawImage를 돌릴 때만 형변환 해준다 (삼각함수 계산을 위해)
		}
	}

	public void setWepponLevelUp(boolean isWepponLevelUp) {
		this.isWepponLevelUp = isWepponLevelUp;
		if (isWepponLevelUp == true && wepponLevel < 4) {
			wepponLevel = wepponLevel + 1;
			System.out.println("무기 레벨 : " + wepponLevel);
			if (wepponLevel == 4) {
				System.out.println("무기 레벨이 최대입니다");
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
