package strikers1945;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class GameFrame extends JFrame implements initable {

	public GameFrame gameFrame = this;

	public static final int SCREEN_WIDTH = 600; // 창의 크기를 절대값으로 고정
	public static final int SCREEN_HEIGHT = 820;

	private PlayerPlane player; // 플레이어 선언

	private EnemyUnit enemyUnit; // 적 유닛 선언
//	private Enemy1 enemy1;
//	private Enemy2 enemy2;
//	private Enemy3 enemy3;
//	private Enemy4 enemy4;
//	private Enemy5 enemy5;
//	private Enemy6 enemy6;
	private EnemyBoss enemyBoss;

	public boolean isgame; // 게임실행 여부
	private GamePanel gamePanel; // 인게임 패널
	private GameTitle gameTitle; // 타이틀 패널
	private SelectAPL selectAPL; // 비행시 선택 패널
	private JLabel la_lifecount,la_lifecount2,la_lifecount3;		//lifecount 라벨
	private ImageIcon lifecounticon;

	ImageIcon stageIcon = new ImageIcon("images/Stage.png"); // 배경 이미지 아이콘
	Image stageImg = stageIcon.getImage(); // 배경 이미지

	ImageIcon bossStageIcon = new ImageIcon("images/vsBossStage.png"); // 보스 스테이지 배경 아이콘
	Image bossStageImg = bossStageIcon.getImage(); // 보스 스테이지 이미지

	ImageIcon titleIcon = new ImageIcon("images/GameTitle.gif");
	Image titleImg = titleIcon.getImage();

	int stageY = -(stageImg.getHeight(null) - bossStageImg.getHeight(null)); // 배경 이미지의 Y좌표
	int bossStageBY1 = -(stageImg.getHeight(null)); // 보스 스테이지 이미지 1의 Y좌표
	int bossStageBY2 = -(stageImg.getHeight(null) + bossStageImg.getHeight(null)); // 보스 스테이지 이미지 2의 Y좌표
	public int appear = 1; // 적 비행기 출현 위치를 정하기 위해 선언
	
	public int test;

	Vector<EnemyUnit> enemyUnits = new Vector<>(); // 적 유닛을 모아놓을 배열
	Vector<Enemy1> enemy1List = new Vector<>(); // 1번 적을 모아놓을 배열
	ArrayList<Enemy2> enemy2List = new ArrayList<>(); // 2번 적을 모아놓을 배열

	public GameFrame() {
		init();
		setting();
		listener();

		setVisible(true);
	}

	public void init() {
		change("gameTitle"); // 초기 타이틀 화면
		isgame = false; // 게임 중 이지 않은 상태
	}

	public void setting() {
		setTitle("Map06");
		setSize(SCREEN_WIDTH, SCREEN_HEIGHT);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
	}

	public void batch(String playerPlane) { // 비행기 선택에서 받을것
		if (playerPlane == "playerPlane") {
			this.player = new PlayerPlane("PLANE1");
			gamePanel.add(this.player);
		} else if (playerPlane == "playerPlane2") {
			this.player = new PlayerPlane("PLANE2");
			gamePanel.add(this.player);
		} else if (playerPlane == "playerPlane3") {
			this.player = new PlayerPlane("PLANE3");
			gamePanel.add(this.player);
		}
	}

	public void enemybatch() {
		if(appear > 0 && appear < 10000) {
//			if (appear % 500 == 0) {
//				enemyUnits.add(new Enemy3(player, 600, 100, 100, 100));
//				enemyUnits.add(new Enemy4(player, -50, -50, 100, 100));
//			}
			if (appear == 1000 || appear == 3000) {
				enemy1List.add(new Enemy1(player, 50, 0, 50, 50));
				enemy1List.add(new Enemy1(player, 100, -50, 50, 50));
				enemy1List.add(new Enemy1(player, 150, -100, 50, 50));
				enemy1List.add(new Enemy1(player, 200, -150, 50, 50));
				enemy1List.add(new Enemy1(player, 250, -200, 50, 50));
			}
//			if (appear == 2000 || appear == 4000) {
//				enemy1List.add(new Enemy1(player, 500, 0, 50, 50));
//				enemy1List.add(new Enemy1(player, 450, -50, 50, 50));
//				enemy1List.add(new Enemy1(player, 400, -100, 50, 50));
//				enemy1List.add(new Enemy1(player, 350, -150, 50, 50));
//				enemy1List.add(new Enemy1(player, 300, -200, 50, 50));
//			}
			if (appear == 500) {
				enemy2List.add(new Enemy2(player, -100, 300, 150,150)); //而⑦뀓�뒪�듃 �꽆湲곌린
				enemy2List.add(new Enemy2(player, 500, 300, 150,150));
			}
//			if (appear == 500) {
//				enemyUnit = new Enemy6(player, 500, 300, 200, 200);
//				// player.contextAdd(enemyUnit);
//			}
		}
		
		if(appear == 10000) {
			enemy1List.remove(enemyUnits);
			enemyBoss = new EnemyBoss(player, 0, -300);
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
				case KeyEvent.VK_ENTER:
					change("selectAPL");
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

	// 패널 바꾸기 함수
	public void change(String panelName) {
		if (panelName.equals("gameTitle")) {
			gameTitle = new GameTitle(gameFrame);
			getContentPane().removeAll();
			getContentPane().add(gameTitle);
			revalidate();
			repaint();
		} else if (panelName.equals("selectAPL")) {
			selectAPL = new SelectAPL(gameFrame);
			getContentPane().removeAll();
			getContentPane().add(selectAPL);
			revalidate();
			repaint();
		} else if (panelName.equals("gameMap")) {
			gamePanel = new GamePanel();
			getContentPane().removeAll();
			getContentPane().add(gamePanel);
			revalidate();
			repaint();
		} else {
			gameTitle = null;
			selectAPL = null;
			gamePanel = null;
			isgame = false;
			getContentPane().removeAll();
			revalidate();
			repaint();
		}
	}
	
	class GamePanel extends JPanel {
		public GamePanel() {
			new Thread(new Runnable() {
				@Override
				public void run() {
					while (true) {
						isgame = true;
						setLayout(null);
						setting_lif();
						gamePanel.add(la_lifecount);
						gamePanel.add(la_lifecount2);
						gamePanel.add(la_lifecount3);
						
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
						try {
							
							lifeCounting();
							enemybatch();

							appear++;
							repaint();
							Thread.sleep(2);
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

			for (int i = 0; i < enemyUnits.size(); i++) {
				if (enemyUnits.get(i) != null) {
					enemyUnits.get(i).enemyUpdate(g);
				}
			}
			
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

			if (enemyUnit != null) {
				enemyUnit.enemyUpdate(g);
			}

			if (enemyBoss != null) {
				enemyBoss.enemyUpdate(g);
			}
			
			repaint();
		}
	}

	class GameTitle extends JPanel { // 게임 시작화면 구현
		private GameFrame win;

		public GameTitle(GameFrame win) {
			setLayout(null);
			this.win = win;
		}

		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			g.drawImage(titleImg, 0, 0, SCREEN_WIDTH - 15, SCREEN_HEIGHT, 0, 0, 338, 594, this);
		}
	}

	private void setting_lif() {
		lifecounticon = new ImageIcon("images/LifeCount.png");
		la_lifecount = new JLabel(lifecounticon);
		la_lifecount2 = new JLabel(lifecounticon);
		la_lifecount3= new JLabel(lifecounticon);
		la_lifecount.setBounds(0,0,50,50);
		la_lifecount2.setBounds(50,0,50,50);
		la_lifecount3.setBounds(100,0,50,50);
	}
	
	public void lifeCounting() {
		if(player.getLifeCount() == 3) {
			la_lifecount.setVisible(true);
			la_lifecount2.setVisible(true);
			la_lifecount3.setVisible(true);
			repaint();
		}else if(player.getLifeCount()==2) {
			la_lifecount.setVisible(true);
			la_lifecount2.setVisible(true);
			la_lifecount3.setVisible(false);
			repaint();
		}else if(player.getLifeCount()==1) {
			la_lifecount.setVisible(true);
			la_lifecount2.setVisible(false);
			la_lifecount3.setVisible(false);
			repaint();
		}else {
			la_lifecount.setVisible(false);
			la_lifecount2.setVisible(false);
			la_lifecount3.setVisible(false);
			repaint();
		}
	}
	
	class SelectAPL extends JPanel { // 비행기 선택 화면
		private GameFrame win;
		private SelectAPL selectAPL = this;
		private ImageIcon player1Icon, player2Icon, player3Icon; // 플레이어 기체 이미지
		private ImageIcon planeDetailIcon1, planeDetailIcon2, planeDetailIcon3; // 기체 상세설명 이미지
		private ImageIcon bigPlayer1icon, bigPlayer2icon, bigPlayer3icon; // 버튼 누를시 커지는 이미지
		private ImageIcon selectPlaneIcon = new ImageIcon("images/SelectPlane1.png");
		private Image selectPlaneImg = selectPlaneIcon.getImage();

		public SelectAPL(GameFrame win) {
			setLayout(null);
			this.win = win;

			player1Icon = new ImageIcon("images/PlayerPlane1.png");
			player2Icon = new ImageIcon("images/PlayerPlane2.png");
			player3Icon = new ImageIcon("images/PlayerPlane3.png");

			bigPlayer1icon = new ImageIcon("images/BigPlane1.png");
			bigPlayer2icon = new ImageIcon("images/BigPlane2.png");
			bigPlayer3icon = new ImageIcon("images/BigPlane3.png");

			planeDetailIcon1 = new ImageIcon("images/PlaneDetailImg1.png");
			planeDetailIcon2 = new ImageIcon("images/PlaneDetailImg2.png");
			planeDetailIcon3 = new ImageIcon("images/PlaneDetailImg3.png");

			JButton btn1 = new JButton("", player1Icon);
			JButton btn2 = new JButton("", player2Icon);
			JButton btn3 = new JButton("", player3Icon);
			JLabel planeImg = new JLabel("");

			// 버튼 테두리 없음
			btn1.setBorderPainted(false);
			btn2.setBorderPainted(false);
			btn3.setBorderPainted(false);

			// 버튼 채우기 없음
			btn1.setContentAreaFilled(false);
			btn2.setContentAreaFilled(false);
			btn3.setContentAreaFilled(false);

			// 버튼 투명
			btn1.setOpaque(false);
			btn2.setOpaque(false);
			btn3.setOpaque(false);

			// 버튼 액션
			btn1.addMouseListener(new MouseAdapter() { // 버튼 클릭 리스너
				@Override
				public void mousePressed(MouseEvent e) {
					change("gameMap");
					batch("playerPlane");
				}

				@Override
				public void mouseEntered(MouseEvent e) {
					planeImg.setIcon(planeDetailIcon1);
					btn1.setSize(100, 89);
					btn1.setIcon(bigPlayer1icon);
				}

				@Override
				public void mouseExited(MouseEvent e) {
					planeImg.setIcon(null);
					btn1.setSize(70, 59);
					btn1.setIcon(player1Icon);
				}
			});
			btn2.addMouseListener(new MouseAdapter() { // 버튼 클릭 리스너
				@Override
				public void mousePressed(MouseEvent e) {
					change("gameMap");
					batch("playerPlane2");
				}

				@Override
				public void mouseEntered(MouseEvent e) {
					planeImg.setIcon(planeDetailIcon2);
					btn2.setSize(100, 89);
					btn2.setIcon(bigPlayer2icon);
				}

				@Override
				public void mouseExited(MouseEvent e) {
					planeImg.setIcon(null);
					btn2.setSize(70, 59);
					btn2.setIcon(player2Icon);
				}
			});

			btn3.addMouseListener(new MouseAdapter() { // 버튼 클릭 리스너
				@Override
				public void mousePressed(MouseEvent e) {
					change("gameMap");
					batch("playerPlane3");
				}

				@Override
				public void mouseEntered(MouseEvent e) {
					planeImg.setIcon(planeDetailIcon3);
					btn3.setSize(100, 89);
					btn3.setIcon(bigPlayer3icon);
				}

				@Override
				public void mouseExited(MouseEvent e) {
					planeImg.setIcon(null);
					btn3.setSize(70, 59);
					btn3.setIcon(player3Icon);
				}
			});

			btn1.setBounds(100, 640, 70, 59);
			btn2.setBounds(250, 640, 70, 59);
			btn3.setBounds(400, 640, 70, 59);
			planeImg.setBounds(180, 250, 223, 318);

			this.add(planeImg);
			this.add(btn1);
			this.add(btn2);
			this.add(btn3);
		}

		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			g.drawImage(selectPlaneImg, 0, 0, SCREEN_WIDTH, SCREEN_HEIGHT, 0, 0, 196, 182, this);
			repaint();
		}
	}
}
