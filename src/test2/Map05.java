package test2;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

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
	private Enemy4 enemy4;
	private JLabel laBackground;
	private MyLabel laBg;
	public int backY = -5635, appear = 1;
	private ImageIcon iconbg;
	private Image imgbg;

	List<Enemy1> enemy1List = new ArrayList<Enemy1>();
	Vector<Enemy2> enemy2Vector = new Vector<>();
	Vector<Enemy3> enemy3Vector = new Vector<>();
	Vector<EnemyUnit> enemyUnits = new Vector<>();

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
		
		//enemy4 = new Enemy4();

	}

	public void setting() {
		setTitle("sdf");

		setSize(700, 639);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setLayout(null);
		setContentPane(laBg);

	}

	public void batch() {
		add(player);

	}

	public void enemybatch() {

		if (appear % 300 == 0) {

			enemy1List.add(new Enemy1(50, -50));
			enemy1List.add(new Enemy1(150, -50));
			enemy1List.add(new Enemy1(250, -50));
			enemy1List.add(new Enemy1(350, -50));
			for (int i = 0; i < enemy1List.size(); i++) {
				if (enemy1List.get(i).getEnemyBullet() != null) {
					add(enemy1List.get(i));
					add(enemy1List.get(i).getEnemyBullet());
					enemy1List.get(i).getEnemyBullet().fire();
					
				}
				// enemy1List.get(i).getEnemyBullet().fire();
//				for (int j = 0; j < enemy1List.get(i).getEnemyBullets().size(); j++) {
//					add(enemy1List.get(i).enemyBullets.get(j));
//					enemy1List.get(i).enemyBullets.get(j).fire();
//				}
			}
		}

		if (appear % 600 == 0) {
			enemy2Vector.add(new Enemy2(700, 100));
			enemy2Vector.add(new Enemy2(700, 0));
			for (int i = 0; i < enemy2Vector.size(); i++) {
				add(enemy2Vector.get(i));
			}
//			enemy2 = new Enemy2(700, 100);
//			add(enemy2);
		}

		if (appear % 550 == 0) {
			enemy3Vector.add(new Enemy3(0, 200));
			enemy3Vector.add(new Enemy3(0, 300));
			for (int i = 0; i < enemy3Vector.size(); i++) {
				add(enemy3Vector.get(i));
			}
		}

		if (appear % 700 == 0) {
			enemyUnits.add(new Enemy1(300, -50));
			enemyUnits.add(new Enemy2(700, 200));
			for (int i = 0; i < enemyUnits.size(); i++) {
				add(enemyUnits.get(i));
			}
		}

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
					player.isRight = false;
				}

				if (e.getKeyCode() == KeyEvent.VK_LEFT) {
					player.isLeft = false;
				}
				if (e.getKeyCode() == KeyEvent.VK_UP) {
					player.isUP = false;
				}
				if (e.getKeyCode() == KeyEvent.VK_DOWN) {
					player.isDown = false;
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

						// System.out.println(backY + " " + appear);

						//enemybatch();

						if(appear % 300 == 0) {
							enemy4 = new Enemy4();
							System.out.println("appear값:  "+appear);
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
			super.paintComponent(g);
			
			g.drawImage(imgbg, 0, backY, 700, imgbg.getHeight(null), this);
			
			
			if(enemy4 != null)
				enemy4.playerUpdate(g);

			
			repaint();

		}

	}

	public static void main(String[] args) {

		new Map05();
	}

}
