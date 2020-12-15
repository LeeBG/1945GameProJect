package Straikers3;

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
	private Enemy2 enemy2;
	private EnemyBoss enemyBoss;
	private GamePanel gamePanel;
	public int backY = -5635, appear = 1; // �벑�옣 媛�
	private ImageIcon iconbg;
	private Image imgbg;
	
	public static final int SCREEN_WIDTH = 700; // 창 크기 절대값으로 설정
	public static final int SCREEN_HEIGHT = 639;

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
		setSize(SCREEN_WIDTH, SCREEN_HEIGHT);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setLayout(null);
		setContentPane(gamePanel);
	}

	public void batch() {
		player.setSize(20, 20);
		gamePanel.add(player);
	}

	public void enemybatch() {
//		if (appear % 300 == 0) {
//			enemyUnits.add(new Enemy1(player,100, 0));
//			enemyUnits.add(new Enemy1(player,200, 0));
//			enemyUnits.add(new Enemy1(player,300, 0));
//			enemyUnits.add(new Enemy1(player,400, 0));
//		}
		if (appear == 300) {
			enemy2List.add(new Enemy2(player, 0, 300)); // 而⑦뀓�뒪�듃 �꽆湲곌린
			enemy2List.add(new Enemy2(player, 650, 300));
			
			if(appear == 3000) {
				enemy2List.remove(enemy2List);
			}
		}
		if (appear == 800) {
			enemyBoss = new EnemyBoss(player, 50, -300);
		}
//		if (appear % 300 == 0) {
//			enemy4 = new Enemy4(player, 300, 0);
//		}
	}

	public void listener() {
		addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				switch (e.getKeyCode()) {
				case KeyEvent.VK_1:
					player.setWepponLevelUp(true);
					break;
				case KeyEvent.VK_2:
					player.setPlayerChange(true);
					break;
				case KeyEvent.VK_SPACE:
					player.setAttack(true);
					break;
				case KeyEvent.VK_UP:
					player.setUp(true);
					break;
				case KeyEvent.VK_DOWN:
					player.setDown(true);
					break;
				case KeyEvent.VK_LEFT:
					player.setLeft(true);
					break;
				case KeyEvent.VK_RIGHT:
					player.setRight(true);
					break;
				}
			}

			@Override
			public void keyReleased(KeyEvent e) {
				switch (e.getKeyCode()) {
				case KeyEvent.VK_1:
					player.setWepponLevelUp(false);
					break;
				case KeyEvent.VK_2:
					player.setPlayerChange(false);
					break;
				case KeyEvent.VK_SPACE:
					player.setAttack(false);
					break;
				case KeyEvent.VK_UP:
					player.setUp(false);
					break;
				case KeyEvent.VK_DOWN:
					player.setDown(false);
					break;
				case KeyEvent.VK_LEFT:
					player.setLeft(false);
					break;
				case KeyEvent.VK_RIGHT:
					player.setRight(false);
					break;
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
						System.out.println("appear = " + appear);
						repaint();
						try {
							Thread.sleep(1);
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

			for (int i = 0; i < enemy2List.size(); i++) {
				if (enemy2List.get(i) != null) {
					enemy2List.get(i).enemyUpdate(g);
				}
			}
			if (player != null)
				player.playerUpdate(g);

			if (enemy2 != null) // null 泥댄겕
				enemy2.enemyUpdate(g);

			if (enemyBoss != null)
				enemyBoss.enemyUpdate(g);

			for (int i = 0; i < enemyUnits.size(); i++) {
				if (enemyUnits.get(i) != null) {
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
