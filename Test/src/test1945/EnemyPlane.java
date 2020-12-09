package test1945;

import javax.swing.ImageIcon;

import lombok.Data;
@Data
public class EnemyPlane extends AirPlane{
	
	public EnemyPlane enemyPlane = this;
	private static final String TAG = "Enemy: ";
	private ImageIcon enemyIcon;
	private int x; 				// 적 라벨의 위치좌표
	private int y; 				// 적 라벨의 위치좌표
	private int sizeX; 			// 비행기 사이즈X
	private int sizeY; 			// 비행기 사이즈Y
	private int rand;

	public boolean isRight; 	// 오른쪽으로 움직이는지 아닌지의 상태
	public boolean isLeft; 		// 왼쪽으로 움직이는지 아닌지의 상태
	public boolean isUp;		// 위쪽으로 움직이는지 아닌지의 상태
	public boolean isDown;		// 아랫쪽으로 움직이는지 아닌지의 상태
	public boolean isCrush;		// 충돌상태인지 아닌지 판별
	
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
		crush(playerPlane);									//충돌 대기 쓰레드 함수(적 비행기가 PLAY의 충돌을 기다렸다가 실행)
	}
	public EnemyPlane(int x,int y,int sizeX,int sizeY) {	//시작위치좌표, 적비행기 사이즈(아직 미사용)
		init();
		
	}

	
	private void init() {								//초기화
		rand = (int)(Math.random()*6);					//비행기 행동 랜덤값 받기 
		x = 70*rand;									//X좌표도 랜덤
		y = 20*(int)(Math.random()*7+1);				//높이도 랜덤
		sizeX = 70;
		sizeY = 65;
		enemyIcon = new ImageIcon("images/PLANE2.png");
		setIcon(playerIcon);
		setLocation(x, y);
		setSize(sizeX, sizeY);
		isRight = false;
		isLeft = false;
		isUp = false;
		isDown = false;
		isCrush = false;
	}

	public void moveLeftDown() {					
		System.out.println(TAG + "왼쪽아래쪽이동");
		int endY = getY();
		int endX = getX();
		setY(getY()-400);
		setX(getX()+400);							//일부러 화면밖으로 꺼낸 다음
		if (isDown == false) {
			new Thread(new Runnable() {
				@Override
				public void run() {
					isDown = true;			
					while (isDown && getY()<endY) {	//랜덤값으로 정해진 위치로 보내지는 스레드				
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
		setX(getX()-400);								//일부러 화면밖으로 꺼낸 다음
		if (isDown == false) {
			new Thread(new Runnable() {
				@Override
				public void run() {
					isDown = true;			
					while (isDown && getY()<endY) {		//랜덤값으로 정해진 위치로 보내지는 스레드
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
//		new Missile(x,y);							//x,y좌표를 받아서 적방향으로 일직선 날아가는 미사일 공격
	}
	
	
	public void explosion(/*미사일 객체*/) {		//폭발 이미지 미구현
		new Thread(new Runnable() {			
			@Override
			public void run() {
				for(int i=0;i<7;i++) {
					break;
				}
				
			}
		}).start();
	}
	
	public void explosionP(PlayerPlane p) {		//폭발 이미지 미구현
		new Thread(new Runnable() {			
			@Override
			public void run() {
				for(int i=0;i<7;i++) {
					p.playerIcon = new ImageIcon("")
				}
				
			}
		}).start();
	}
	
	public void crush(PlayerPlane playerPlane) { 			//충돌의 책임을 적에게 둠 player 충돌 대기
		new Thread(new Runnable() {
			@Override
			public void run() {				
				while (playerPlane.getLifecount() > 0) {
					isCrush = playerPlane.getX()+20>=getX()+20 && playerPlane.getX()+20<=getX()+getSizeX()-20&&												// 이미지 사각형의 좌표 (0,0)
							playerPlane.getY()+15>=getY()+15 && playerPlane.getY()+15<=getY()+getSizeY()-15 ||
							playerPlane.getX()+playerPlane.getSizeX()-20>=getX()+20 && playerPlane.getX()+playerPlane.getSizeX()-20<=getX()+getSizeX()-20&&	//(1,0)
							playerPlane.getY()+10>=getY()+15 && playerPlane.getY()+15<=getY()+getSizeY()-15 ||
							playerPlane.getX()+20>=getX()+20 && playerPlane.getX()+20<=getX()+getSizeX()-20 &&												//(0,1)
							playerPlane.getY()+playerPlane.getSizeY()-15>=getY()+15 && playerPlane.getY()+playerPlane.getSizeY()-15<=getY()+getSizeY()-15 ||
							playerPlane.getX()+playerPlane.getSizeX()-20>=getX()+20 && playerPlane.getX()+playerPlane.getSizeX()-20<=getX()+getSizeX()-20 &&//(1,1)
							playerPlane.getY()+playerPlane.getSizeY()-15>=getY()+15 && playerPlane.getY()+playerPlane.getSizeY()-15<=getY()+getSizeY()-15;
					try {
						if (isCrush) {								
							playerPlane.setLifecount(playerPlane.getLifecount()-1);
							System.out.println(TAG +"lifecount:"+playerPlane.getLifecount());
							playerPlane.setX(200);
							playerPlane.setY(520);
							playerPlane.repaint();
						}
							Thread.sleep(10);
						if(playerPlane.getLifecount() == 0) {
							Thread.sleep(1000);						//1초 대기후
							System.exit(1);							//프로그램 강제 종료
						}
									
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		}).start();

	}
}
