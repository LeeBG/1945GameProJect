package test2;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import test1.Enemy1test;
import test1.PlayerPlane;

public class Map05 extends JFrame implements initable {
	private PlayerPlane player;
	private Enemy1test enemy1;
	private Enemy2 enemy2;
	private Enemy3 enemy3;
	private JLabel laBackground;
	private MyLabel laBg;
	int backY = -5635, appear = 1;
	private ImageIcon iconbg;
	private Image imgbg;

	List<Enemy1> enemy1List = new ArrayList<Enemy1>();

	public Map05() {

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

		enemy1List.add(new Enemy1(50, -200));
		enemy1List.add(new Enemy1(150, -200));
		enemy1List.add(new Enemy1(250, -200));
		enemy1List.add(new Enemy1(350, -200));

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

			new Thread(new Runnable() {

				@Override
				public void run() {

					while (backY <= 0) {
						backY++;

						System.out.println(backY + " " + appear);

						if (appear % 100 == 0) {
							for (int i = 0; i < enemy1List.size(); i++) {
								add(enemy1List.get(i));
							}
							enemy1List.add(new Enemy1(50, -200));
							enemy1List.add(new Enemy1(150, -200));
							enemy1List.add(new Enemy1(250, -200));
							enemy1List.add(new Enemy1(350, -200));

						}


						if (appear % 200 == 0) {
							enemy2 = new Enemy2(200, 0);

							add(enemy2);
						}

						if (appear % 550 == 0) {
							enemy3 = new Enemy3(0, 100);
							add(enemy3);
						}
						


						appear++;
						repaint();

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

		}

	}

	public static void main(String[] args) {

		new Map05();
	}

}
