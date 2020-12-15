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
	private Item item = this;

	private final static String TAG = "Item: ";
	private ImageIcon icPlayeritem;// , icPlayerL, icPlayerU, icPlayerD;
	private int x, y = -50;
	public boolean isRight = false;
	private boolean isCrush; // 임시 코드
	private boolean isItem = false;
	private Icon icPlayerR; // 임시코드

	private int sizeX; // 비행기 사이즈X
	private int sizeY;

	int dx = 1, dy = 1; // x,y 방향으로 1, -1 씩 이동

	Random ranItem = new Random();
	private int ranEnemyY400 = ranItem.nextInt(100) + 1;//
	private int ranEnemyY600 = ranItem.nextInt(100) + 1;//
	
	

	
	// ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ↓ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ

	public Item() { //아이템 생성
		icPlayeritem = new ImageIcon("image/item2.png");
		setIcon(icPlayeritem);
		setSize(30, 30);
		setLocation(x, y);
		
		
		
		
		
		
		
		
	}

	// ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ

	public void itemreflect() {
		// itemAppear();
		// System.out.println(TAG + "itemreflect");
	}

	public void itemAppear(Player player) {
		System.out.println(TAG + "itemAppear");
		System.out.println("x 좌표: " + x);

		this.x = ranEnemyY400; // 아이템 소환 위치
		this.y = ranEnemyY600;

		if (isRight == false) {
			new Thread(new Runnable() {
				@Override
				public void run() {
					setIcon(icPlayeritem);
					isRight = true;
					while (isRight) {

						try {
							Thread.sleep(StrikersApp.speed20); // 아이템 속도

							if (y <= 0 || y >= StrikersApp.YAXIS - 50) {
								System.out.println(TAG + "위아래반사");
								dy = -dy;

								System.out.println("x 좌표: " + x + " y 좌표: " + y);
							}
							// 좌, 우 반사
							if (x <= 0 || x >= StrikersApp.XAXIS - 50) {
								System.out.println(TAG + "좌우반사");
								dx = -dx;

								System.out.println("x 좌표: " + x + " y 좌표: " + y);
							}

							x += dx; // if로 방향을 바꾼 뒤 x축으로 dx씩 이동
							y += dy;// if로 방향을 바꾼 뒤 y축으로 dy씩 이동
							setLocation(x, y);

							crush(player);

						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				}
			}).start();
		}
	}

	// 임시 아이템 충돌 적 -플레이어(아이템)
	public void crush(Player player) { // 충돌의 책임을 아이템에 둠 player 충돌 대기
		new Thread(new Runnable() {
			@Override
			public void run() {
				isRight = true;
				while (isRight) {
					System.out.println(TAG + "isRight 동작");
					isCrush = 
							player.getX() + 20 >= getX() + 20 
							&& player.getX() + 20 <= getX() + getSizeX() - 20
							&& player.getY() + 15 >= getY() + 15 
							&& player.getY() + 15 <= getY() + getSizeY() - 15

							|| player.getX() + player.getSizeX() - 20 >= getX() + 20
							&& player.getX() + player.getSizeX() - 20 <= getX() + getSizeX() - 20// (1,0)
							&& player.getY() + 10 >= getY() + 15
							&& player.getY() + 15 <= getY() + getSizeY() - 15

							|| player.getX() + 20 >= getX() + 20 
							&& player.getX() + 20 <= getX() + getSizeX() - 20 // (0,1)
							&& player.getY() + player.getSizeY() - 15 >= getY() + 15
							&& player.getY() + player.getSizeY() - 15 <= getY() + getSizeY() - 15

							|| player.getX() + player.getSizeX() - 20 >= getX() + 20
							&& player.getX() + player.getSizeX() - 20 <= getX() + getSizeX() - 20 // (1,1)
							&& player.getY() + player.getSizeY() - 15 >= getY() + 15
							&& player.getY() + player.getSizeY() - 15 <= getY() + getSizeY() - 15;

					try {
						System.out.println(TAG + "crush 동작");
						//item.remove(icPlayeritem);
						player.repaint();
//						if (isCrush) {
//							
//								isItem = true;
//								while() {
//									
//								}
//								
//
//						}
						Thread.sleep(10);

					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}

			private int getSizeY() {
				// TODO Auto-generated method stub
				return 0;
			}

			private int getSizeX() {
				// TODO Auto-generated method stub
				return 0;
			}
		}).start();

	}



}