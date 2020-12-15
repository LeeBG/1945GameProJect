package test2;

import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class PlayerPlane extends JLabel {

	public PlayerPlane player = this;
	public final static String TAG = "Player: ";
	private EnemyUnit enemyUnit;
	Vector<EnemyUnit> enemyUnitList; //총알피격시 객체를 담을 벡터

	public ImageIcon icPlayer = new ImageIcon("images/PLANE4.png");
	public ImageIcon icPlayer2 = new ImageIcon("images/PLANE3UP.png");
	public ImageIcon icPlayer3 = new ImageIcon("images/PlayerPlane1.png");

	public Image playerImg = icPlayer.getImage();
	public int playerWidth = 70;
	public int playerHeight = 65;
	public int playerX = (Map06.SCREEN_WIDTH / 2) - (playerWidth / 2);
	public int playerY = (Map06.SCREEN_HEIGHT - (playerHeight * 2));
	private int life = 3;
	private int pCount;

	private int wepponLevel = 0;
	public int select = 1;

	public boolean isRight = false;
	public boolean isLeft = false;
	public boolean isUp = false;
	public boolean isDown = false; // is 붙여라
	public boolean isAttack = false;
	public boolean isWepponLevelUp = false;
	public boolean isPlayerChange = false;

	public ArrayList<PlayerAttack> playerAttackList = new ArrayList<PlayerAttack>();
	private PlayerAttack playerAttack;

	public PlayerPlane() {

		setIcon(icPlayer);
		move();
	}

	public void move() {
		new Thread(new Runnable() {
			@Override
			public void run() {
				pCount = 0; // count를 0으로 설정
				while (true) {
					try {
						Thread.sleep(5);
						keyProcess();
						playerAttackProcess();
						setLocation(playerX, playerY); // repaint()
						setSize(playerWidth, playerHeight);

						pCount++; // 1씩 늘어난다
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}).start();
	}

	public int getPlayerWidth() {
		return playerWidth;
	}

	public void setPlayerWidth(int playerWidth) {
		this.playerWidth = playerWidth;
	}

	public int getPlayerHeight() {
		return playerHeight;
	}

	public void setPlayerHeight(int playerHeight) {
		this.playerHeight = playerHeight;
	}

	public int getLife() {
		return life;
	}

	public void setLife(int life) {
		this.life = life;
	}

	public int getX() {
		return playerX;
	}

	public void setX(int x) {
		this.playerX = x;
	}

	public int getY() {
		return playerY;
	}

	public void setY(int y) {
		this.playerY = y;
	}

	private void keyProcess() {
		if (isAttack && pCount % 30 == 0) { // 총알의 발사 속도를 조절
			if (wepponLevel == 0) { // 총알 한줄만 발사
				playerAttack = new PlayerAttack(enemyUnit, playerX + 20, playerY - 40, 90, 2); // 총알이 생성되는 위치
				playerAttackList.add(playerAttack); // arrayList에 저장한다
			}
			if (wepponLevel == 1) { // 총알 2줄 발사
				playerAttack = new PlayerAttack(enemyUnit, playerX + 10, playerY - 40, 90, 2);
				playerAttackList.add(playerAttack);
				playerAttack = new PlayerAttack(enemyUnit, playerX + 30, playerY - 40, 90, 2);
				playerAttackList.add(playerAttack);
			}
			if (wepponLevel == 2) { // 총알 3줄 발사
				playerAttack = new PlayerAttack(enemyUnit, playerX + 0, playerY - 40, 90, 2);
				playerAttackList.add(playerAttack);
				playerAttack = new PlayerAttack(enemyUnit, playerX + 20, playerY - 40, 90, 2);
				playerAttackList.add(playerAttack);
				playerAttack = new PlayerAttack(enemyUnit, playerX + 40, playerY - 40, 90, 2);
				playerAttackList.add(playerAttack);
			}
			if (wepponLevel == 3) { // 총알 4줄 발사
				playerAttack = new PlayerAttack(enemyUnit, playerX - 10, playerY - 40, 90, 2);
				playerAttackList.add(playerAttack);
				playerAttack = new PlayerAttack(enemyUnit, playerX + 10, playerY - 40, 90, 2);
				playerAttackList.add(playerAttack);
				playerAttack = new PlayerAttack(enemyUnit,playerX + 30, playerY - 40, 90, 2);
				playerAttackList.add(playerAttack);
				playerAttack = new PlayerAttack(enemyUnit, playerX + 50, playerY - 40, 90, 2);
				playerAttackList.add(playerAttack);
			}
			if (wepponLevel == 4) { // 양 옆 대각선으로 나가는 총알 2줄 추가
				playerAttack = new PlayerAttack(enemyUnit, playerX - 15, playerY - 40, 80, 2);
				playerAttackList.add(playerAttack);
				playerAttack = new PlayerAttack(enemyUnit, playerX - 10, playerY - 40, 90, 2);
				playerAttackList.add(playerAttack);
				playerAttack = new PlayerAttack(enemyUnit, playerX + 10, playerY - 40, 90, 2);
				playerAttackList.add(playerAttack);
				playerAttack = new PlayerAttack(enemyUnit, playerX + 30, playerY - 40, 90, 2);
				playerAttackList.add(playerAttack);
				playerAttack = new PlayerAttack(enemyUnit, playerX + 50, playerY - 40, 90, 2);
				playerAttackList.add(playerAttack);
				playerAttack = new PlayerAttack(enemyUnit, playerX + 55, playerY - 40, 100, 2);
				playerAttackList.add(playerAttack);
			}
		}
		if (isUp && playerY > 0) {
			playerY--;
		}
		if (isDown && playerY < Map06.SCREEN_HEIGHT - (playerHeight + 40)) {
			playerY++;
		}
		if (isLeft && playerX > 0) {
			playerX--;
		}
		if (isRight && playerX < Map06.SCREEN_WIDTH - (playerWidth + 15)) {
			playerX++;
		}
	}

	public void playerUpdate(Graphics g) { // 플레이어에 관한 모든 그림은 여기서 정리한다
		playerDraw(g);
	}

	public void playerDraw(Graphics g) {
		for (int i = 0; i < playerAttackList.size(); i++) {
			playerAttack = playerAttackList.get(i);
			if (select == 1) {
				g.drawImage(playerAttack.playerBulletImg1, (int) playerAttack.bulletX, (int) playerAttack.bulletY, null);
			}
			if (select == 2) {
				g.drawImage(playerAttack.playerBulletImg2, (int) playerAttack.bulletX, (int) playerAttack.bulletY, 20, 20, null);
			}
			if (select == 3) {
				g.drawImage(playerAttack.playerBulletImg3, (int) playerAttack.bulletX, (int) playerAttack.bulletY, 20, 20, null);
			}
			// PlayaerAttack의 자료형을 double로 두고, drawImage를 돌릴 때만 형변환 해준다 (삼각함수 계산을 위해)
		}
	}

	private void playerAttackProcess() {
		for (int i = 0; i < playerAttackList.size(); i++) {
			playerAttack = playerAttackList.get(i);
			playerAttack.Fire();
		}
	}

	public void setWepponLevelUp(boolean isWepponLevelUp) {
		this.isWepponLevelUp = isWepponLevelUp;
		if (isWepponLevelUp == true && wepponLevel < 6) {
			wepponLevel = wepponLevel + 1;
			System.out.println("무기 레벨 : " + wepponLevel);
			if (wepponLevel == 4) {
				System.out.println("무기 레벨이 최대입니다");
			} else if (wepponLevel == 5) {
				wepponLevel = 0;
			}
		}
	}

	public void setPlayerChange(boolean isPlayerChange) {
		this.isPlayerChange = isPlayerChange;
		if (isPlayerChange == true && select < 4) {
			select = select + 1;
			if (select == 1) {
				setIcon(icPlayer);
			} else if (select == 2) {
				setIcon(icPlayer2);
			} else if (select == 3) {
				setIcon(icPlayer3);
			} else if (select == 4) {
				select = 1;
				setIcon(icPlayer);
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
	
	
	
	public void contextAdd(EnemyUnit enemyUnit) {
		this.enemyUnit = enemyUnit;		
//		enemyUnitList = new Vector<>();
//		
//		enemyUnitList.add(enemyUnit);
	}
	
}
