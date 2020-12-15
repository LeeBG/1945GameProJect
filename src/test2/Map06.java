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
//	private Enemy1 enemy1;
//	private Enemy2 enemy2;
//	private Enemy3 enemy3;
//	private Enemy4 enemy4;
//	private Enemy5 enemy5;
//	private Enemy6 enemy6;
	private EnemyUnit enemyUnit;
	public static final int SCREEN_WIDTH = 600; // â ũ�� ���밪���� ����
	public static final int SCREEN_HEIGHT = 820;
	private GamePanel gamePanel;
	public int appear = 1; // 등장 값
	private ImageIcon iconbg, iconbossbg;

	Image stageImg = new ImageIcon("images/stage.png").getImage();
	Image bossStageImg = new ImageIcon("images/vsBossStage.png").getImage();

	int stageY = -(stageImg.getHeight(null) - bossStageImg.getHeight(null));
	int bossStageBY1 = -(stageImg.getHeight(null));
	int bossStageBY2 = -(stageImg.getHeight(null) + bossStageImg.getHeight(null));

	Vector<EnemyUnit> enemyUnits = new Vector<>();
	ArrayList<Enemy2> enemy2List = new ArrayList<>();
	Vector<Enemy1> enemy1List = new Vector<>();

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
		iconbossbg = new ImageIcon("images/vsBossStage.png");

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
		gamePanel.add(player);

	}

	public void enemybatch() {

		if (appear % 500 == 0) {
			enemyUnits.add(new Enemy3(player,600, 100,100,100));
			enemyUnits.add(new Enemy4(player,-50, -50,100,100));
			
			

			
//			enemyUnits.add(new Enemy1(player,300, 0));
//			enemyUnits.add(new Enemy1(player,400, 0));
			
			
		}

		if (appear == 1000 || appear == 3000) {
			enemy1List.add(new Enemy1(player, 50, 0, 50, 50));
			enemy1List.add(new Enemy1(player, 100, -50, 50, 50));
			enemy1List.add(new Enemy1(player, 150, -100, 50, 50));
			enemy1List.add(new Enemy1(player, 200, -150, 50, 50));
			enemy1List.add(new Enemy1(player, 250, -200, 50, 50));
		}

		if (appear == 2000 || appear == 4000) {
			enemy1List.add(new Enemy1(player, 500, 0, 50, 50));
			enemy1List.add(new Enemy1(player, 450, -50, 50, 50));
			enemy1List.add(new Enemy1(player, 400, -100, 50, 50));
			enemy1List.add(new Enemy1(player, 350, -150, 50, 50));
			enemy1List.add(new Enemy1(player, 300, -200, 50, 50));
		}

//
//		if (appear == 500) {
//			enemy2List.add(new Enemy2(player, -100, 300, 150,150)); //컨텍스트 넘기기
//			enemy2List.add(new Enemy2(player, 500, 300, 150,150));
//		}
//
		if (appear == 500) {
			enemyUnit = new Enemy6(player, 500, 300, 200, 200);
			//player.contextAdd(enemyUnit);
		} 



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

					while (true) {
						stageY++;
						bossStageBY1++;
						bossStageBY2++;

						if (stageY > bossStageImg.getHeight(null)) {
							stageY = bossStageImg.getHeight(null);
							if (bossStageBY1 > (bossStageImg.getHeight(null) - 1)) {
								bossStageBY1 = -(bossStageImg.getHeight(null) - 1);
							}
							if (bossStageBY2 > (bossStageImg.getHeight(null) - 1)) {
								bossStageBY2 = -(bossStageImg.getHeight(null) - 1);
							}
						}

						enemybatch();

						appear++;
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
			super.paintComponent(g);

			g.drawImage(stageImg, 0, stageY, null);
			g.drawImage(bossStageImg, 0, bossStageBY1, null);
			g.drawImage(bossStageImg, 0, bossStageBY2, null);

			for (int i = 0; i < enemy2List.size(); i++) {
				if (enemy2List.get(i) != null) {
					enemy2List.get(i).enemyUpdate(g);
				}
			}

			for (int i = 0; i < enemy1List.size(); i++) {
				if (enemy1List.get(i) != null) {
					enemy1List.get(i).enemyUpdate(g);
				}
			}

			if (player != null) {
				player.playerUpdate(g);
			}

			if (enemyUnit != null) // null 체크
				enemyUnit.enemyUpdate(g);

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
