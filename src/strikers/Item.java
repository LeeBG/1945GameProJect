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
	private ImageIcon icPlayeritem;// ������ �̹���
	private int x, y;
	private int itemCount;
	private int delay; // ������ �԰� �ٽ� ��Ÿ���� ���� �ɸ��� �ð�
	private int sizeX; // ����� ������X
	private int sizeY; // ����� ������Y
	private int dx, dy; // x,y �������� 1, -1 �� �̵�
	private int ranEnemy400;
	private int ranEnemy600;
	
	//private boolean isItem; //�������� ���� true, false
	//private Icon icPlayerR; // �ӽ��ڵ�
	
	private boolean itemVisible;
	public boolean isRight; // ���� ����
	private boolean isitemGet; // ������ ���� ����
	
	Random ranItem = new Random();
	//Random dly = new Random();
	
	
	
	// �ѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѸ޼����ѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤѤ�
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
		
		ranEnemy400 = ranItem.nextInt(350) + 1; //���� ��ġ ��ǥ ����
		ranEnemy600 = ranItem.nextInt(550) + 1; //���� ��ġ ��ǥ ����
		
		// ������ �԰� �ٽ� ��Ÿ���� ���� �ɸ��� �ð� 10��
		delay = 10000;
		
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
	public void itemreflect() { //���� �Ⱦ��� �κ�
		// itemAppear();
		// System.out.println(TAG + "itemreflect");
	}

	public void itemAppear(Player player) {
		//System.out.println(TAG + "itemAppear");
		//System.out.println("x ��ǥ: " + x);

		this.x = ranEnemy400; 			// ������ ��ȯ ���� ��ġ ��ǥ ����
		this.y = ranEnemy600;			// ������ ��ȯ ���� ��ġ ��ǥ ����

		if (isRight == false) {
			new Thread(new Runnable() {
				@Override
				public void run() {
					setIcon(icPlayeritem);
					isRight = true;
					itemGet(player);// itemGet�� ��ġ Ȯ�� !!
					//reSet();
					
					while (isRight) {
						try {
							Thread.sleep(StrikersApp.speed10); 		// ������ �ӵ� 10
							//System.out.println("�� x ��ǥ: " + x + "�� y ��ǥ: " + y);
							if (y <= 0 || y >= StrikersApp.YAXIS - 85) {
								//System.out.println(TAG + "���Ʒ��ݻ�");
								//System.out.println("x ��ǥ: " + x + " y ��ǥ: " + y);
								dy = -dy;
							}
							// ��, �� �ݻ�
							if (x <= 0 || x >= StrikersApp.XAXIS - 65) {
								//System.out.println(TAG + "�¿�ݻ�");
								//System.out.println("x ��ǥ: " + x + " y ��ǥ: " + y);
								dx = -dx;
							}

							x += dx;			// if�� ������ �ٲ� �� x������ dx�� �̵�
							y += dy;			// if�� ������ �ٲ� �� y������ dy�� �̵�
							setLocation(x, y);

						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				}
			}).start();
		}
	}
// ���� �ð� �ֱ�� ������ ���
	public void reSet(int ranEn400, int ranEn600) {
		Timer  timer = new Timer();
		TimerTask task = new TimerTask() {
			@Override
			public void run() {
				ranEnemy400 = ranItem.nextInt(350) + 1; //���� ��ġ ��ǥ ����
				ranEnemy600 = ranItem.nextInt(550) + 1; //���� ��ġ ��ǥ ����
				//System.out.println("������: " + ranEnemy400 + ", " + ranEnemy600);
			}
			
		};
		// (task, 1000, 1000) = task�� 1000(1��) �ڿ� �����ϰ� 2000(2��) �������� �߻�
		timer.schedule(task, 1000, 2000); 
	}

	// item�� player���� ������ �޼���
	public void itemGet(Player player) {
		new Thread(new Runnable() {
			@Override
			public void run() {
				isRight = true;
				while (isRight) {
					//System.out.println(TAG + "isRight ����");
					//�����۰� �÷��̾��� ��ǥ�� 20��ŭ �������� isitemGet �ߵ�
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
						if (isitemGet) { 				// isitemGet = ������ ����
							//System.out.println(TAG + "������ ����");
							item.setVisible(false); 			//������ ������ ������ �̹��� �Ⱥ��̰�
							itemVisible = true; 			//�ϰ� itemVisible ���´� true
							itemCount += 1; 			// ������ ī���ʹ� 1������
								if(itemVisible) { 			//itemVisible = true �̸�
									Thread.sleep(delay); 			//������ �� 
									item.setVisible(true); 			//������ �̹��� �����

									reSet(ranEnemy400, ranEnemy600);
									
									x = ranEnemy400; 			// ������ ��ȯ ���� ��ġ ��ǥ ����
									y = ranEnemy600;			// ������ ��ȯ ���� ��ġ ��ǥ ����
									if(itemCount == 3) {
										//itemCount �� 3�� �Ǹ� ������ ���� x
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
 			private int getSizeY() {return 69;}
			private int getSizeX() {return 79;}
			
		}).start();

	}

}