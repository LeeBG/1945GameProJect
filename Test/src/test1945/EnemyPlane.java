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
	public boolean isUp;		// 위쪽으로 움직이는지 아닌지의 상태
	public boolean isDown;

	public EnemyPlane(PlayerPlane playerPlane) { 	//적비행기 생성자
		init();
		switch (rand) {								//비행기 행동 랜덤값 받기 (init()에 랜덤정의)
		case 0:					
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
			System.out.println(TAG+"왼쪽에서 위로");
			moveLeftUp();
			break;
		default:
			moveLeftDown();
			break;
		}
		crush(playerPlane);									//충돌 대기 쓰레드 함수
	}
	public EnemyPlane(int x,int y,int sizeX,int sizeY) {	//시작위치좌표, 적비행기 사이즈(아직 미사용)
		init();
	}
	private void init() {								//초기화
		rand = (int)(Math.random()*6);					//비행기 행동 랜덤값 받기 
		x = 70*rand;
		y = 20*(int)(Math.random()*7+1);				//높이도 랜덤
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

	public void moveLeftUp() {
		System.out.println(TAG + "왼쪽 위로 이동");
		int endY = getY();
		int endX = getX();
		setY(getY()+400);
		setX(getX()+400);
		if (isDown == false) {
			new Thread(new Runnable() {
				@Override
				public void run() {
					isDown = true;			
					while (isDown && getY()>endY) {						
						try {
							Thread.sleep(10);
							y--;
							x--;
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
	public void shotToEnemy() {						//발사 (미구현)
//		new Missile(x,y);		//x,y좌표를 받아서 적방향으로 일직선 날아가는 미사일 공격
	}
	
	
	public void crush(PlayerPlane playerPlane) { 			//충돌의 책임을 적에게 둠 player 충돌 대기
		new Thread(new Runnable() {
			@Override
			public void run() {
				
				while (playerPlane.getLifecount() > 0) {
					try {
						if (playerPlane.getX()>getX() && playerPlane.getX()<getX()+getSizeX()&&				//(0,0)
							playerPlane.getY()>getY() && playerPlane.getY()<getY()+getSizeY() ||
							playerPlane.getX()+playerPlane.getSizeX()>getX() && playerPlane.getX()+playerPlane.getSizeX()<getX()+getSizeX()&&	//(1,0)
							playerPlane.getY()>getY() && playerPlane.getY()<getY()+getSizeY() ||
							playerPlane.getX()>getX() && playerPlane.getX()<getX()+getSizeX() &&			//(0,1)
							playerPlane.getY()+playerPlane.getSizeY()>getY() && playerPlane.getY()+playerPlane.getSizeY()<getY()+getSizeY() ||
							playerPlane.getX()+playerPlane.getSizeX()>getX() && playerPlane.getX()+playerPlane.getSizeX()<getX()+getSizeX() &&	//(1,1)
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
							Thread.sleep(1000);
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
