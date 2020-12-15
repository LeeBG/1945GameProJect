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
	private boolean isCrush; // �ӽ� �ڵ�
	private boolean isItem = false;
	private Icon icPlayerR; // �ӽ��ڵ�

	private int sizeX; // ����� ������X
	private int sizeY;

	int dx = 1, dy = 1; // x,y �������� 1, -1 �� �̵�

	Random ranItem = new Random();
	private int ranEnemyY400 = ranItem.nextInt(100) + 1;//
	private int ranEnemyY600 = ranItem.nextInt(100) + 1;//
	
	

	
	// �ѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѡ�ѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤ�

	public Item() { //������ ����
		icPlayeritem = new ImageIcon("image/item2.png");
		setIcon(icPlayeritem);
		setSize(30, 30);
		setLocation(x, y);
		
		
		
		
		
		
		
		
	}

	// �ѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤ�

	public void itemreflect() {
		// itemAppear();
		// System.out.println(TAG + "itemreflect");
	}

	public void itemAppear(Player player) {
		System.out.println(TAG + "itemAppear");
		System.out.println("x ��ǥ: " + x);

		this.x = ranEnemyY400; // ������ ��ȯ ��ġ
		this.y = ranEnemyY600;

		if (isRight == false) {
			new Thread(new Runnable() {
				@Override
				public void run() {
					setIcon(icPlayeritem);
					isRight = true;
					while (isRight) {

						try {
							Thread.sleep(StrikersApp.speed20); // ������ �ӵ�

							if (y <= 0 || y >= StrikersApp.YAXIS - 50) {
								System.out.println(TAG + "���Ʒ��ݻ�");
								dy = -dy;

								System.out.println("x ��ǥ: " + x + " y ��ǥ: " + y);
							}
							// ��, �� �ݻ�
							if (x <= 0 || x >= StrikersApp.XAXIS - 50) {
								System.out.println(TAG + "�¿�ݻ�");
								dx = -dx;

								System.out.println("x ��ǥ: " + x + " y ��ǥ: " + y);
							}

							x += dx; // if�� ������ �ٲ� �� x������ dx�� �̵�
							y += dy;// if�� ������ �ٲ� �� y������ dy�� �̵�
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

	// �ӽ� ������ �浹 �� -�÷��̾�(������)
	public void crush(Player player) { // �浹�� å���� �����ۿ� �� player �浹 ���
		new Thread(new Runnable() {
			@Override
			public void run() {
				isRight = true;
				while (isRight) {
					System.out.println(TAG + "isRight ����");
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
						System.out.println(TAG + "crush ����");
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