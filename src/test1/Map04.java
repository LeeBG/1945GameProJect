package test1;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import test2.initable;

public class Map04 extends JFrame implements initable{
	private PlayerPlane player;
	private Enemy1test enemy1;
	private JLabel laBackground;
	private MyLabel laBg;
	int backY = -5635, appear = 0;
	private ImageIcon iconbg;
	private Image imgbg;

	List<Enemy1test> enemyList = new ArrayList<Enemy1test>();

	public Map04() {

		init();
		setting();
		batch();
		listener();

		setVisible(true);

	}

	public void init() {
		laBackground = new JLabel(new ImageIcon("images/stage1.png"));
		laBg = new MyLabel();
		player = new PlayerPlane();
		iconbg = new ImageIcon("images/stage1.png");
		imgbg = iconbg.getImage();

		enemyList.add(new Enemy1test(50, 0));
		enemyList.add(new Enemy1test(150, 0));
		enemyList.add(new Enemy1test(250, 0));
		enemyList.add(new Enemy1test(350, 0));

	}

	public void setting() {
		setTitle("sdf");

		setSize(500, 639);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(null);
		setContentPane(laBg);// label로 대체

	}

	public void batch() {
		add(player); // container는 생략가능

		// add(enemy1);

	}

	public void listener() {

		addKeyListener(new KeyAdapter() {

			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
					player.moveRight();
				}

				else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
					player.moveLeft();
				}

				else if (e.getKeyCode() == KeyEvent.VK_UP) {
					player.moveUp();
				} else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
					player.moveDown();
				}

			}

			public void keyReleased(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
					player.isRight = false; // 원래는 getter setter 함수를 이용
				}

				if (e.getKeyCode() == KeyEvent.VK_LEFT) {
					player.isLeft = false; // 원래는 getter setter 함수를 이용
				}
				if (e.getKeyCode() == KeyEvent.VK_UP) {
					player.isUP = false; // 원래는 getter setter 함수를 이용
				}
				if (e.getKeyCode() == KeyEvent.VK_DOWN) {
					player.isDown = false; // 원래는 getter setter 함수를 이용
				}

			}

		});

	}

	class MyLabel extends JLabel {

		public MyLabel() {
//
//			Thread t1 =new Thread(new UnitAppear(),"쓰레드 이름지정"); 
//			t1.start(); //독립적인 스레드 실행

			new Thread(new Runnable() {

				@Override
				public void run() {

					while (backY <= 0) {
						backY++;

						if (appear % 400 == 0) {
							enemy1 = new Enemy1test(500, 200);
							add(enemy1);
							// enemy1.movedown();
							enemy1.moveleft();

						}

						if (appear % 500 == 0) {
							for (int i = 0; i < enemyList.size(); i++) {

								add(enemyList.get(i));
								enemyList.get(i).movedown();

							}
							enemyList.add(new Enemy1test(50, 0));
							enemyList.add(new Enemy1test(150, 0));
							enemyList.add(new Enemy1test(250, 0));
							enemyList.add(new Enemy1test(350, 0));
						}
						
						
						if(appear % 550 == 0) {
							
							enemy1 = new Enemy1test(0, 200);
							add(enemy1);
							// enemy1.movedown();
							enemy1.upleft();
							
						}
						

						appear++;
						repaint();

						//System.out.println(backY + " " + appear);

						try {
							Thread.sleep(10);

						} catch (Exception e) {
							// TODO: handle exception
						}
					}

				}

			}).start();

		}

		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g); // 배경 이미지 조정이 필요

			g.drawImage(imgbg, 0, backY, 700, imgbg.getHeight(null), this);

//			for (int i = 0; i < enemyList.size(); i++) {
//				g.drawImage(enemyList.get(i).getImage(), enemyList.get(i).getX(), enemyList.get(i).getY(), this);
//			}

		}
//
//		class UnitAppear implements Runnable{
//			
//			
//		
//		}

	}

	public static void main(String[] args) {

		new Map04();
	}

}
