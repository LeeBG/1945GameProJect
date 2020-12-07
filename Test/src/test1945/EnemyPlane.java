package test1945;

import javax.swing.ImageIcon;

import lombok.Data;
@Data
public class EnemyPlane extends AirPlane{
	
	public EnemyPlane player = this;
	private static final String TAG = "Enemy: ";
	private ImageIcon playerIcon;
	private int x; 				// 적 라벨의 위치좌표
	private int y; 				// 적 라벨의 위치좌표
	private int sizeX; 			// 비행기 사이즈X
	private int sizeY; 			// 비행기 사이즈Y
	private int rand;
	
	public boolean isRight; 	// 오른쪽으로 움직이는지 아닌지의 상태
	public boolean isLeft; 		// 왼쪽으로 움직이는지 아닌지의 상태
	public boolean isUp;		// 위쪽으로 움직ㅇㄴ
	public boolean isDown;

	public EnemyPlane() {
		init();
		switch (rand) {
		case 1:				//곧장 아래로
			System.out.println(TAG+"아래로");
			moveDown();
			break;
		case 2:
			System.out.println(TAG+"왼쪽에서 아래로");
			moveLeftDown();
			break;
		case 3:
			System.out.println(TAG+"오른쪽에서 아래로");
			moveRightDown();
			break;
		case 4:
			System.out.println(TAG+"오른쪽에서 위로");
			moveRightUp();
			break;
		}
		
			
		moveDown();
	}
	public EnemyPlane(int x,int y,int sizeX,int sizeY) {	//시작위치좌표, 적비행기 사이즈
		init();
	}
	private void init() {
		rand = (int)(Math.random()*5);
		x = 70*rand;
		y = 20*(int)(Math.random()*7+1);
		sizeX = 70;
		sizeY = 65;
		playerIcon = new ImageIcon("images/PLANE2.png");
		setIcon(playerIcon);
		setLocation(x, y);
		setSize(sizeX, sizeY);
		isRight = false;
		isLeft = false;
		isUp = false;
		isDown = false;
	}

	public void moveLeftDown() {
		System.out.println(TAG + "좌측 아래로 이동");
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
						if(x<=0)
							return;
					}
				}
			}).start();
		}	
	}

	public void moveRightDown() {
		System.out.println(TAG + "우측아래로이동");
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
						if(x>=401)
							return;
					}
				}
			}).start();
		}
	}

	public void moveRightUp() {
		System.out.println(TAG + "오른쪽에서 위쪽이동");
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
		System.out.println(TAG + "아래쪽이동");
		int end = getY();
		setY(getY()-200);
		if (isDown == false) {
			new Thread(new Runnable() {
				@Override
				public void run() {
					isDown = true;			
					while (isDown && getY()<end) {						
						try {
							System.out.println(getY());
							Thread.sleep(10);
							y++;
							setLocation(x, y);
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
