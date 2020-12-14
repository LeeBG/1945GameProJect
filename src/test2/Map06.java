package test2;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Map06 extends JFrame implements initable {

	private PlayerPlane player;
	private Enemy1 enemy1;
	private Enemy2 enemy2;
	private Enemy3 enemy3;
	private Enemy4 enemy4;
	private GamePanel gamePanel;
	public int backY = -5635, appear = 1; // 등장 값
	private ImageIcon iconbg;
	private Image imgbg;

	Vector<EnemyUnit> enemyUnits = new Vector<>();
	ArrayList<Enemy2> enemy2List = new ArrayList<>();

	public Map06() {
		init();
		setting();
		batch();
		listener();

		setVisible(true);
	}

	public void init() {
		gamePanel = new GamePanel();
		player = new PlayerPlane();
		iconbg = new ImageIcon("images/stage1.png");
		imgbg = iconbg.getImage();

	}

	public void setting() {
		setTitle("Map06");
		setSize(700, 639);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setLayout(null);
		setContentPane(gamePanel);

	}

	public void batch() {
		gamePanel.add(player);

	}

	public void enemybatch() {

		if (appear % 300 == 0) {
			enemyUnits.add(new Enemy1(player,100, 0));
			enemyUnits.add(new Enemy1(player,200, 0));
			enemyUnits.add(new Enemy1(player,300, 0));
			enemyUnits.add(new Enemy1(player,400, 0));
		}

//		if (appear == 300) {
//			enemy2List.add(new Enemy2(0, 300));
//			enemy2List.add(new Enemy2(650, 300));
//
//		}
//
//		if (appear == 500 ) {
//			enemy3 = new Enemy3(700, 100);
//		}

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

	class GamePanel extends JPanel {

		public GamePanel() {

			new Thread(new Runnable() {

				@Override
				public void run() {

					while (backY <= 0) {
						backY++;

						enemybatch();

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

			for(int i = 0; i<enemy2List.size(); i++) {
				if(enemy2List.get(i) != null) {
					enemy2List.get(i).enemyUpdate(g);
				}
				
			}
			
			
			if (enemy2 != null) // null 체크
				enemy2.enemyUpdate(g);

			if (enemy3 != null) // null 체크
				enemy3.enemyUpdate(g);
			

			for(int i = 0; i<enemyUnits.size(); i++) {
				if(enemyUnits.get(i) != null) {
					enemyUnits.get(i).enemyUpdate(g);
				}
				
			}
			
			
			repaint();

		}

	}

	public static void main(String[] args) {

		new Map06();
	}

}
