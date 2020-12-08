package test1945;

import javax.swing.ImageIcon;

import lombok.Data;
@Data
public class EnemyPlane extends AirPlane{
	
	public EnemyPlane enemyPlane = this;
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

	public EnemyPlane(PlayerPlane playerPlane) {
		init();
		switch (rand) {
		case 0:				//곧장 아래로
			System.out.println(TAG+"아래로");
			moveDown();
			break;
		case 1:
			System.out.println(TAG+"왼쪽에서 아래로");
			moveLeftDown();
			break;
		case 2:
			System.out.println(TAG+"오른쪽에서 아래로");
			moveRightDown();
			break;
		case 3:
			System.out.println(TAG+"오른쪽에서 위로");
			moveRightUp();
			break;
		case 4:
			System.out.println(TAG+"아래로");
			moveDown();
			break;
		default:
			moveLeftDown();
			break;
		}
		crush(playerPlane);
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
		System.out.println(TAG + "왼쪽아래쪽이동");
		int endY = getY();
		int endX = getX();
		setY(getY()-400);
		setX(getX()+400);
		if (isDown == false) {
			new Thread(new Runnable() {
				@Override
				public void run() {
					isDown = true;			
					while (isDown && getY()<endY) {						
						try {
							Thread.sleep(10);
							x--;
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

	public void moveRightDown() {
		System.out.println(TAG + "오른쪽아래쪽이동");
		int endY = getY();
		int endX = getX();
		setY(getY()-400);
		setX(getX()-400);
		if (isDown == false) {
			new Thread(new Runnable() {
				@Override
				public void run() {
					isDown = true;			
					while (isDown && getY()<endY) {						
						try {
							Thread.sleep(10);
							x++;
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

	public void moveRightUp() {
		System.out.println(TAG + "오른쪽 위로 이동");
		int endY = getY();
		int endX = getX();
		setY(getY()+400);
		setX(getX()-400);
		if (isDown == false) {
			new Thread(new Runnable() {
				@Override
				public void run() {
					isDown = true;			
					while (isDown && getY()>endY) {						
						try {
							Thread.sleep(10);
							y--;
							x++;
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
	
	
	public void crush(PlayerPlane playerPlane) { 
		new Thread(new Runnable() {
			@Override
			public void run() {
				
				while (playerPlane.getLifecount() > 0) {
					try {
						if (playerPlane.getX()>getX() && playerPlane.getX()<getX()+getSizeX()&&				//(0,0)
							playerPlane.getY()>getY() && playerPlane.getY()<getY()+getSizeY() ||
							playerPlane.getX()+playerPlane.getSizeX()>getX() && playerPlane.getX()+playerPlane.getSizeX()<getX()+getSizeX()&&
							playerPlane.getY()>getY() && playerPlane.getY()<getY()+getSizeY() ||
							playerPlane.getX()>getX() && playerPlane.getX()<getX()+getSizeX() &&
							playerPlane.getY()+playerPlane.getSizeY()>getY() && playerPlane.getY()+playerPlane.getSizeY()<getY()+getSizeY() ||
							playerPlane.getX()+playerPlane.getSizeX()>getX() && playerPlane.getX()+playerPlane.getSizeX()<getX()+getSizeX() &&
							playerPlane.getY()+playerPlane.getSizeY()>getY() && playerPlane.getY()+playerPlane.getSizeY()<getY()+getSizeY()) {
							playerPlane.setLifecount(playerPlane.getLifecount()-1);
							System.out.println(TAG + playerPlane.getLifecount());
							System.out.println("만남");
							playerPlane.setX(200);
							playerPlane.setY(520);

							playerPlane.repaint();
							
						}
							Thread.sleep(1000);
						if(playerPlane.getLifecount() == 0) {
							System.exit(1);
						}
									
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		}).start();

	}
}
