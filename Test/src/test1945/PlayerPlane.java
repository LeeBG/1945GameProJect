package test1945;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import lombok.Data;

@Data
public class PlayerPlane extends AirPlane {

	public PlayerPlane player = this;
	private static final String TAG = "Player: ";
	public ImageIcon playerIcon;
	private int lifecount;
	private int x; // 플레이어 라벨의 위치좌표
	private int y; // 플레이어 라벨의 위치좌표
	private int sizeX; // 비행기 사이즈X
	private int sizeY; // 비행기 사이즈Y

	public boolean isRight; // 오른쪽으로 움직이는지 아닌지의 상태
	public boolean isLeft; // 왼쪽으로 움직이는지 아닌지의 상태
	public boolean isUp;
	public boolean isDown;

	public PlayerPlane() {
		init();
	}

	private void init() {
		lifecount = 3;
		x = 200;
		y = 510;
		sizeX = 79;
		sizeY = 60;
		playerIcon = new ImageIcon("images/PLANE1.png");
		setIcon(playerIcon);
		setLocation(x, y);
		setSize(sizeX, sizeY);
		isRight = false;
		isLeft = false;
		isUp = false;
		isDown = false;
	}

	public void moveLeft() {
		System.out.println(TAG + "좌측이동");
		if (isLeft == false) {
			new Thread(new Runnable() {
				@Override
				public void run() {
					isLeft = true;
					while (isLeft) {
						x--;
						setLocation(x, y);
						try {
							Thread.sleep(10);
						} catch (Exception e) {
							e.printStackTrace();
						}
						if (x <= 0) // 벽에 막힘
							return;
					}
				}
			}).start();
		}
	}

	public void moveRight() {
		System.out.println(TAG + "우측이동");
		if (isRight == false) {
			new Thread(new Runnable() {
				@Override
				public void run() {
					isRight = true;
					while (isRight) {
						x++;
						setLocation(x, y);
						try {
							Thread.sleep(10);
						} catch (Exception e) {
							e.printStackTrace();
						}
						if (x >= 401) // 벽에 막힘
							return;
					}
				}
			}).start();
		}
	}

	public void moveUp() {
		System.out.println(TAG + "위쪽이동");
		if (isUp == false) {
			new Thread(new Runnable() {
				@Override
				public void run() {
					isUp = true;
					while (isUp) {
						y--;
						setLocation(x, y);
						try {
							Thread.sleep(10);
						} catch (Exception e) {
							e.printStackTrace();
						}
						if (y < 0) // 벽에 막힘
							return;
					}
				}
			}).start();
		}
	}

	public void moveDown() {
		System.out.println(TAG + "위쪽이동");
		if (isDown == false) {
			new Thread(new Runnable() {
				@Override
				public void run() {
					isDown = true;
					while (isDown) {
						y++;
						setLocation(x, y);
						try {
							Thread.sleep(10);
						} catch (Exception e) {
							e.printStackTrace();
						}
						if (y > 520) // 벽에 막힘
							return;
					}
				}
			}).start();
		}
	}

	public void shotToEnemy() {
//		new Missile(this);			//x,y좌표를 받아서 적방향으로 일직선 날아가는 미사일 공격
	}

}
