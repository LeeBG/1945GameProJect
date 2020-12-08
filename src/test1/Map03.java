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

public class Map03 extends JFrame {
	private PlayerPlane player;
	private JLabel laBackground;
	private MyLabel laBg;
	int backY = -5635, count=0;
	private ImageIcon iconbg;
	private Image imgbg;
	//private Enemy enemy;

	ImageIcon enem = new ImageIcon("images/PLANE3.png");
	Image enemy = enem.getImage();
	List<Enemy1test> imgList = new ArrayList<Enemy1test>();

	public Map03() {

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

	}

	public void setting() {
		setTitle("sdf");

		setSize(500, 639);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(null);
		setContentPane(laBg);// label로 대체

		//imgList.add(new Enemy(enemy, 150, 50));

	}

	public void batch() {
		add(player); // container는 생략가능
		// add(enemy);

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

						for (int i = 0; i < imgList.size(); i++) {
							imgList.get(i).setY(imgList.get(i).getY() + 2);
						}
						
						
						
						repaint();
												

						try {
							Thread.sleep(5);

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
			
			
			
			
			
				for (int i = 0; i < imgList.size(); i++) {
					g.drawImage(imgList.get(i).getImage(), imgList.get(i).getX(), imgList.get(i).getY(), this);

				}
			
			

			
			

		}

	}

	public static void main(String[] args) {
		new Map03();
	}

}
