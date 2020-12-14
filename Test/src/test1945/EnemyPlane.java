package test1945;

import javax.swing.ImageIcon;

import lombok.Data;
@Data
public class EnemyPlane extends AirPlane{
	
	public EnemyPlane enemyPlane = this;
	private static final String TAG = "Enemy: ";
	private ImageIcon enemyIcon;
	private int x; 				// 위치좌표X
	private int y; 				// 위치좌표Y
	private int sizeX; 			// 비행기이미지의 X크기
	private int sizeY; 			// 비행기이미지의 Y크기
	private int rand;			// 랜덤 정수

	public boolean isRightUp; 	//	오른쪽 위로 가는 중인지 판별
	public boolean isLeftUp; 	//	왼쪽 위로 가는 중인지 판별 
	public boolean isRightDown;	//	오른쪽 아래로 가는 중인지 판별
	public boolean isLeftDown;	//	왼쪽 아래로 가는 중인지 판별
	public boolean isUp;		//	위로 가는 중인지 판별
	public boolean isDown;		//	아래로 가는 중인지 판별
	public boolean isCrush;		//	충돌범위에 들어왔는지 판별(충돌 연산)
	
	public EnemyPlane(PlayerPlane playerPlane) { 	//생성자 Play객체를 매개변수로 받음
		init();
		switch (rand) {								// 랜덤 위치 정하기 SWITCH문
		case 0:					
			System.out.println(TAG+"아래로");
			moveDown();
			break;
		case 1:
			System.out.println(TAG+"왼쪽아래로");
			moveLeftDown();
			break;
		case 2:
			System.out.println(TAG+"오른쪽 아래로");
			moveRightDown();
			break;
		case 3:
			System.out.println(TAG+"오른쪽 위로");
			moveRightUp();
			break;
		case 4:
			System.out.println(TAG+"왼쪽 위로");
			moveLeftUp();
			break;
		default:
			System.out.println(TAG+"왼쪽 아래로");
			moveLeftDown();
			break;
		}
		crush(playerPlane);									//충돌책임은 enemy에게
	}
	public EnemyPlane(int x,int y,int sizeX,int sizeY) {	//사용하지 않는 생성자
		init();		
	}

	
	private void init() {								//초기화 함수
		rand = (int)(Math.random()*6);					//적비행기 움직임의 방향을 랜덤으로
		x = 70*rand;									//적비행기 x좌표 랜덤
		y = 20*(int)(Math.random()*7+1);				//적비행기 Y좌표 랜덤
		sizeX = 70;										//비행기의 X크기 = 이미지의 크기
		sizeY = 65;										//비행기의 Y크기 = 이미지의 크기
		enemyIcon = new ImageIcon("images/PLANE2.png");	//비행기 이미지
		setIcon(enemyIcon);								//아이콘설정
		setLocation(x, y);								//랜덤위치로 초기화
		setSize(sizeX, sizeY);							//보이는 크기는 이미지의 크기와 같게
		isLeftDown = false;								//우측으로 움직이는 상태
		isRightDown = false;							//우측으로 움직이는 상태
		isLeftUp = false;
		isRightUp = false;
		isUp = false;									//우측으로 움직이는 상태
		isDown = false;									//우측으로 움직이는 상태
		isCrush = false;
	}

	public void moveLeftDown() {						//왼쪽 아래로 움직임
		System.out.println(TAG + "왼쪽 아래");
		int endY = getY();				//도착 위치X 저장
		int endX = getX();				//도착 위치Y 저장
		setY(getY()-400);				//화면바깥으로 비행기 이동시킨다
		setX(getX()+400);				//화면바깥으로 비행기 이동시킨다		
		if (isLeftDown == false) {
			new Thread(new Runnable() {
				@Override
				public void run() {
					isLeftDown = true;			
					while (isLeftDown && getY()<endY) {					
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
		System.out.println(TAG + "오른쪽 아래");
		int endY = getY();				//도착 위치X 저장
		int endX = getX();				//도착 위치Y 저장
		setY(getY()-400);				//화면바깥으로 비행기 이동시킨다
		setX(getX()-400);				//화면바깥으로 비행기 이동시킨다							
		if (isRightDown == false) {
			new Thread(new Runnable() {
				@Override
				public void run() {
					isRightDown = true;			
					while (isRightDown && getY()<endY) {
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
		int endY = getY();					//도착 위치X 저장			
		int endX = getX();					//도착 위치Y 저장
		setY(getY()+400);					//화면바깥으로 비행기 이동시킨다
		setX(getX()-400);					//화면바깥으로 비행기 이동시킨다
		if (isRightUp == false) {
			new Thread(new Runnable() {
				@Override
				public void run() {
					isRightUp = true;			
					while (isRightUp && getY()>endY) {						
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
		int endY = getY();				//도착 위치X 저장
		int endX = getX();				//도착 위치Y 저장
		setY(getY()+400);				//화면바깥으로 비행기 이동시킨다
		setX(getX()+400);				//화면바깥으로 비행기 이동시킨다
		if (isLeftUp == false) {		
			new Thread(new Runnable() {
				@Override
				public void run() {
					isLeftUp = true;			
					while (isLeftUp && getY()>endY) {						
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
		System.out.println(TAG + "");
		int end = getY();				//도착 위치Y 저장
		setY(getY()-200);				//화면바깥으로 비행기 이동시킨다
		if (isDown == false) {
			new Thread(new Runnable() {
				@Override
				public void run() {
					isDown = true;			
					while (isDown && getY()<end) {			//도착할 때 까지			
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
	public void shotToEnemy() {						// 미사일 쏘기
//		new Missile(x,y);							// 미구현
	}
	
	
	public void explosion(/* 플레이어가 쏜 미사일 객체를 넣어야할듯*/) {		//미사일 객체 생성?후 발사
		new Thread(new Runnable() {			
			@Override
			public void run() {
				for(int i=0;i<7;i++) {
					break;
				}
				
			}
		}).start();
	}

	public void explosePlayer(PlayerPlane playerPlane) {	//충돌후 이미지 변경 및 목숨카운트--
		
		try {
			ImageIcon explosionIcon = new ImageIcon("images/explosion.gif");
			playerPlane.setIcon(explosionIcon);
			Thread.sleep(1000);
			playerPlane.setIcon(playerPlane.playerIcon);
			playerPlane.setLifecount(playerPlane.getLifecount()-1);
			System.out.println(TAG +"남은목숨:"+playerPlane.getLifecount());
			playerPlane.setX(200);
			playerPlane.setY(520);
			playerPlane.repaint();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void crush(PlayerPlane playerPlane) { 		//적비행기-Player 충돌 대기 메서드		
		new Thread(new Runnable() {					
			@Override
			public void run() {				
				while (playerPlane.getLifecount() > 0) {	//생명이 0보다 크면 -- 충돌연산
					isCrush = playerPlane.getX()+22>=getX()+19 && playerPlane.getX()+22<=getX()+getSizeX()-19&&												// player비행기 기준 좌표 (0,0)
							playerPlane.getY()+10>=getY()+10 && playerPlane.getY()+10<=getY()+getSizeY()-10 ||
							playerPlane.getX()+playerPlane.getSizeX()-22>=getX()+19 && playerPlane.getX()+playerPlane.getSizeX()-22<=getX()+getSizeX()-19&&	//(1,0)
							playerPlane.getY()+10>=getY()+10 && playerPlane.getY()+10<=getY()+getSizeY()-10 ||
							playerPlane.getX()+15>=getX()+19 && playerPlane.getX()+22<=getX()+getSizeX()-19 &&												//(0,1)
							playerPlane.getY()+playerPlane.getSizeY()-10>=getY()+10 && playerPlane.getY()+playerPlane.getSizeY()-10<=getY()+getSizeY()-10 ||
							playerPlane.getX()+playerPlane.getSizeX()-22>=getX()+19 && playerPlane.getX()+playerPlane.getSizeX()-22<=getX()+getSizeX()-19 &&//(1,1)
							playerPlane.getY()+playerPlane.getSizeY()-10>=getY()+10 && playerPlane.getY()+playerPlane.getSizeY()-10<=getY()+getSizeY()-10;
					try {
						if (isCrush) {
							explosePlayer(playerPlane);			//충돌 폭발 메서드
						}
							Thread.sleep(10);
						if(playerPlane.getLifecount() <= 0) {
							Thread.sleep(100);						//1초후
							System.exit(1);							//프로그램 종료
						}
									
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		}).start();

	}
}
