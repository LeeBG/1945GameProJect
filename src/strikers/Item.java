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
	private boolean isCrush; // �ӽ� �ڵ�
	private boolean isItem; //�������� ���� true, false
	private Icon icPlayerR; // �ӽ��ڵ�

	private int sizeX; // ����� ������X
	private int sizeY; // ����� ������Y

	int dx, dy; // x,y �������� 1, -1 �� �̵�

	Random ranItem = new Random();
	private int ranEnemy400;
	private int ranEnemy600;
	
	
	// �ѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѡ�ѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤ�
	private void init() {
		x = -50;
		y = -50;
		dx = 1;
		dy = 1;
		isRight = false;
		isItem = false;
		isCrush = false;
		ranEnemy400 = ranItem.nextInt(100) + 1; //���� ��ġ
		ranEnemy600 = ranItem.nextInt(100) + 1; //���� ��ġ
		
		icPlayeritem = new ImageIcon("image/item2.png");
		setIcon(icPlayeritem);
		setSize(50, 50);
		setLocation(x, y) ;
		
//		for (int i = 0; i < 100; i++) {
//			add(ranEnemy400.get(i));
//		}
		
	}
	
	public Item() { //������ ����
		 init();
		 
	}
	
	
	// �ѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤ�

	public void itemreflect() {
		// itemAppear();
		// System.out.println(TAG + "itemreflect");
	}

	public void itemAppear(Player player) {
		//System.out.println(TAG + "itemAppear");
		//System.out.println("x ��ǥ: " + x);

		this.x = ranEnemy400; // ������ ��ȯ ��ġ
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
							Thread.sleep(StrikersApp.speed10); // ������ �ӵ�

							if (y <= 0 || y >= StrikersApp.YAXIS - 50) {
								//System.out.println(TAG + "���Ʒ��ݻ�");
								dy = -dy;

								//System.out.println("x ��ǥ: " + x + " y ��ǥ: " + y);
							}
							// ��, �� �ݻ�
							if (x <= 0 || x >= StrikersApp.XAXIS - 50) {
								//System.out.println(TAG + "�¿�ݻ�");
								dx = -dx;

								//System.out.println("x ��ǥ: " + x + " y ��ǥ: " + y);
							}

							x += dx; // if�� ������ �ٲ� �� x������ dx�� �̵�
							y += dy;// if�� ������ �ٲ� �� y������ dy�� �̵�
							setLocation(x, y);

						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				}
			}).start();
		}
	}

	// player�� item�� ����
	public void crush(Player player) { // �浹�� å���� �����ۿ� �� player �浹 ���
		new Thread(new Runnable() {
			@Override
			public void run() {
				isRight = true;
				while (isRight) {
					//System.out.println(TAG + "isRight ����");
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
							System.out.println(TAG + "�浹 ����");
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