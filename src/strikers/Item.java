package strikers;

import java.awt.Canvas;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Random;
import java.util.Vector;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;


public class Item extends JLabel {
	private final static String TAG = "Item: ";
	
	private Item item = this;
	private ImageIcon icPlayeritem;// , icPlayerL, icPlayerU, icPlayerD;
	private int x, y;
	public boolean isRight;
	private boolean isCrush; // 임시 코드
	private boolean isItem; //아이템의 상태 true, false
	private Icon icPlayerR; // 임시코드

	private int sizeX; // 비행기 사이즈X
	private int sizeY; // 비행기 사이즈Y

	int dx, dy; // x,y 방향으로 1, -1 씩 이동

	Random ranItem = new Random();
	private int ranEnemy400;
	private int ranEnemy600;
	
	
	// ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ↓ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ
	private void init() {
		x = -50;
		y = -50;
		dx = 1;
		dy = 1;
		isRight = false;
		isItem = false;
		isCrush = false;
		ranEnemy400 = ranItem.nextInt(100) + 1; //랜덤 위치
		ranEnemy600 = ranItem.nextInt(100) + 1; //랜덤 위치
		
		icPlayeritem = new ImageIcon("image/item2.png");
		setIcon(icPlayeritem);
		setSize(50, 50);
		setLocation(x, y) ;
		
//		for (int i = 0; i < 100; i++) {
//			add(ranEnemy400.get(i));
//		}
		
	}
	
	public Item() { //아이템 생성
		 init();
		 
	}
	
	
	// ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ

	public void itemreflect() {
		// itemAppear();
		// System.out.println(TAG + "itemreflect");
	}

	public void itemAppear(Player player) {
		//System.out.println(TAG + "itemAppear");
		//System.out.println("x 좌표: " + x);

		this.x = ranEnemy400; // 아이템 소환 위치
		this.y = ranEnemy600;

		if (isRight == false) {
			new Thread(new Runnable() {
				@Override
				public void run() {
					setIcon(icPlayeritem);
					isRight = true;
					crush(player);
					while (isRight) {

						try {
							Thread.sleep(StrikersApp.speed10); // 아이템 속도

							if (y <= 0 || y >= StrikersApp.YAXIS - 50) {
								//System.out.println(TAG + "위아래반사");
								dy = -dy;

								//System.out.println("x 좌표: " + x + " y 좌표: " + y);
							}
							// 좌, 우 반사
							if (x <= 0 || x >= StrikersApp.XAXIS - 50) {
								//System.out.println(TAG + "좌우반사");
								dx = -dx;

								//System.out.println("x 좌표: " + x + " y 좌표: " + y);
							}

							x += dx; // if로 방향을 바꾼 뒤 x축으로 dx씩 이동
							y += dy;// if로 방향을 바꾼 뒤 y축으로 dy씩 이동
							setLocation(x, y);

						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				}
			}).start();
		}
	}

	// player가 item을 먹음
	public void crush(Player player) { // 충돌의 책임을 아이템에 둠 player 충돌 대기
		new Thread(new Runnable() {
			@Override
			public void run() {
				isRight = true;
				while (isRight) {
					//System.out.println(TAG + "isRight 동작");
					isCrush = 
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
						
						if (isCrush) {
							System.out.println(TAG + "충돌 동작");
							item.setVisible(false);
							//isItem = true;						
								
						}
						Thread.sleep(1);

					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
 			private int getSizeY() {return 69;}
			private int getSizeX() {return 79;}
			
		}).start();

	}

}