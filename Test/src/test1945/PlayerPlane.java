package test1945;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class PlayerPlane extends JLabel {
	
	public PlayerPlane player = this;
	private static final String TAG = "Player: ";
	public ImageIcon playerIcon;
	public int x = 200; // 플레이어 라벨의 위치좌표
	public int y = 510; // 플레이어 라벨의 위치좌표
	public int sizeX = 79; // 비행기 사이즈X
	public int sizeY = 60; // 비행기 사이즈Y

	public boolean isRight = false; // 오른쪽으로 움직이는지 아닌지의 상태
	public boolean isLeft = false; // 왼쪽으로 움직이는지 아닌지의 상태
	public boolean isUp = false;
	public boolean isDown = false;

	public PlayerPlane() {
		playerIcon = new ImageIcon("images/PLANE1.png");
		setIcon(playerIcon);
		setLocation(x, y);
		setSize(sizeX, sizeY);
	}

	public void moveLeft() {
		System.out.println(TAG + "좌측이동");
		if (isLeft == false) {
			System.out.println("안녕");
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
						if(x<=0)
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
						System.out.println("안녕");
						try {
							Thread.sleep(10);
						} catch (Exception e) {
							e.printStackTrace();
						}
						if(x>=401)
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
						if(y<0)
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
						if(y>520)
							return;
					}
				}
			}).start();
		}
	}
	public void shotToEnemy() {
//		new Missile(x,y);		//x,y좌표를 받아서 적방향으로 일직선 날아가는 미사일 공격
	}
}
