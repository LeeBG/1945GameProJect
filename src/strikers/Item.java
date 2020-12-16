package strikers;

import java.awt.Canvas;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Vector;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Item extends JLabel {
	private final static String TAG = "Item: ";
	
	private Item item = this;
	private ImageIcon icPlayeritem;// 아이템 이미지
	private int x, y;
	private int itemCount;
	private int delay; // 아이템 먹고 다시 나타나기 까지 걸리는 시간
	private int sizeX; // 비행기 사이즈X
	private int sizeY; // 비행기 사이즈Y
	private int dx, dy; // x,y 방향으로 1, -1 씩 이동
	private int ranEnemy400;
	private int ranEnemy600;
	
	//private boolean isItem; //아이템의 상태 true, false
	//private Icon icPlayerR; // 임시코드
	
	private boolean itemVisible;
	public boolean isRight; // 현재 상태
	private boolean isitemGet; // 아이템 먹은 상태
	
	Random ranItem = new Random();
	//Random dly = new Random();
	
	
	
	// ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ메서드↓ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ
	private void init() {
		x = -50;
		y = -50;
		dx = 1;
		dy = 1;
		itemCount = 0;

		isRight = false;
		//isItem = false;
		isitemGet = false;
		itemVisible = false;
		
		ranEnemy400 = ranItem.nextInt(350) + 1; //랜덤 위치 좌표 설정
		ranEnemy600 = ranItem.nextInt(550) + 1; //랜덤 위치 좌표 설정
		
		// 아이템 먹고 다시 나타나기 까지 걸리는 시간 10초
		delay = 10000;
		
		icPlayeritem = new ImageIcon("image/item.gif");
		setIcon(icPlayeritem);
		setSize(44, 33);
		setLocation(x, y) ;
		
//		for (int i = 0; i < 100; i++) {
//			add(ranEnemy400.get(i));
//		}
	}
	public Item() { //아이템 생성
		 init();
		 
	}
	
	// ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ
	public void itemreflect() { //아직 안쓰는 부분
		// itemAppear();
		// System.out.println(TAG + "itemreflect");
	}

	public void itemAppear(Player player) {
		//System.out.println(TAG + "itemAppear");
		//System.out.println("x 좌표: " + x);

		this.x = ranEnemy400; 			// 아이템 소환 랜덤 위치 좌표 설정
		this.y = ranEnemy600;			// 아이템 소환 랜덤 위치 좌표 설정

		if (isRight == false) {
			new Thread(new Runnable() {
				@Override
				public void run() {
					setIcon(icPlayeritem);
					isRight = true;
					itemGet(player);// itemGet의 위치 확인 !!
					//reSet();
					
					while (isRight) {
						try {
							Thread.sleep(StrikersApp.speed10); 		// 아이템 속도 10
							//System.out.println("공 x 좌표: " + x + "공 y 좌표: " + y);
							if (y <= 0 || y >= StrikersApp.YAXIS - 85) {
								//System.out.println(TAG + "위아래반사");
								//System.out.println("x 좌표: " + x + " y 좌표: " + y);
								dy = -dy;
							}
							// 좌, 우 반사
							if (x <= 0 || x >= StrikersApp.XAXIS - 65) {
								//System.out.println(TAG + "좌우반사");
								//System.out.println("x 좌표: " + x + " y 좌표: " + y);
								dx = -dx;
							}

							x += dx;			// if로 방향을 바꾼 뒤 x축으로 dx씩 이동
							y += dy;			// if로 방향을 바꾼 뒤 y축으로 dy씩 이동
							setLocation(x, y);

						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				}
			}).start();
		}
	}
// 일정 시간 주기로 랜덤값 계산
	public void reSet(int ranEn400, int ranEn600) {
		Timer  timer = new Timer();
		TimerTask task = new TimerTask() {
			@Override
			public void run() {
				ranEnemy400 = ranItem.nextInt(350) + 1; //랜덤 위치 좌표 설정
				ranEnemy600 = ranItem.nextInt(550) + 1; //랜덤 위치 좌표 설정
				//System.out.println("랜덤값: " + ranEnemy400 + ", " + ranEnemy600);
			}
			
		};
		// (task, 1000, 1000) = task를 1000(1초) 뒤에 실행하고 2000(2초) 간격으로 발생
		timer.schedule(task, 1000, 2000); 
	}

	// item이 player에게 먹히는 메서드
	public void itemGet(Player player) {
		new Thread(new Runnable() {
			@Override
			public void run() {
				isRight = true;
				while (isRight) {
					//System.out.println(TAG + "isRight 동작");
					//아이템과 플레이어의 좌표가 20만큼 겹쳐지면 isitemGet 발동
					isitemGet = 
							player.getX() + 20 >= getX() +20
							&& player.getX() + 20 <= getX() + getSizeX() -20
							&& player.getY() + 20 >= getY() +20
							&& player.getY() + 20 <= getY() + getSizeY() -20

							|| player.getX() + player.getSizeX() -20 >= getX() +20
							&& player.getX() + player.getSizeX() -20 <= getX() + getSizeX() -20// (1,0)
							&& player.getY() + 20 >= getY() + 20
							&& player.getY() + 20 <= getY() + getSizeY()  -20

							|| player.getX() + 20 >= getX() +20
							&& player.getX() + 20 <= getX() + getSizeX() -20// (0,1)
							&& player.getY() + player.getSizeY() - 20 >= getY() +20 
							&& player.getY() + player.getSizeY() - 20 <= getY() + getSizeY() -20

							|| player.getX() + player.getSizeX() - 20 >= getX() +20 
							&& player.getX() + player.getSizeX() - 20 <= getX() + getSizeX() -20// (1,1)
							&& player.getY() + player.getSizeY() - 20 >= getY() + 20
							&& player.getY() + player.getSizeY() - 20 <= getY() + getSizeY() -20;

					try {
						//player.repaint();
						if (isitemGet) { 				// isitemGet = 아이템 먹음
							//System.out.println(TAG + "아이템 먹음");
							item.setVisible(false); 			//아이템 먹으면 아이템 이미지 안보이게
							itemVisible = true; 			//하고 itemVisible 상태는 true
							itemCount += 1; 			// 아이템 카운터는 1씩증가
								if(itemVisible) { 			//itemVisible = true 이면
									Thread.sleep(3000); 			//딜레이 후 
									item.setVisible(true); 			//아이템 이미지 재등장

									reSet(ranEnemy400, ranEnemy600);
									
									x = ranEnemy400; 			// 아이템 소환 랜덤 위치 좌표 설정
									y = ranEnemy600;			// 아이템 소환 랜덤 위치 좌표 설정
									if(itemCount == 3) {
										//itemCount 가 3이 되면 아이템 생성 x
										item.setVisible(false);
									}
								}
								
						}
						Thread.sleep(1);

					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
 			private int getSizeY() {return 69;} // 플레이어 크기 만큼
			private int getSizeX() {return 79;} // 플레이어 크기 만큼
			
		}).start();

	}

}