package frame;

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

import objects.Boss;
import objects.Enemy1;
import objects.Enemy2;
import objects.Enemy3;
import objects.Enemy4;
import objects.Enemy5;
import objects.Enemy6;
import objects.EnemyUnit;
import objects.PlayerPlane;

public class GameFrame extends JFrame implements Initable {

	public GameFrame gameFrame = this;

	public static final int SCREEN_WIDTH = 600; // 창의 크기를 절대값으로 고정
	public static final int SCREEN_HEIGHT = 820;

	private PlayerPlane player; // 플레이어 선언

	private EnemyUnit enemyUnit;
	private Boss boss; // 보스 선언

	public boolean isgame; // 게임실행 여부
	private GamePanel gamePanel; // 인게임 패널 이거 잘 봐야된다. 오류 !!
	private GameTitle gameTitle; // 타이틀 패널
	private SelectAPL selectAPL; // 비행시 선택 패널

	private JLabel laLifecount, laLifecount2, laLifecount3; // lifecount 라벨
	private ImageIcon lifeCounticon;

	private ImageIcon stageIcon = new ImageIcon("images/Stage.png"); // 배경 이미지 아이콘
	private Image stageImg = stageIcon.getImage(); // 배경 이미지

	private ImageIcon bossStageIcon = new ImageIcon("images/vsBossStage.png"); // 보스 스테이지 배경 아이콘
	private Image bossStageImg = bossStageIcon.getImage(); // 보스 스테이지 이미지

	private ImageIcon titleIcon = new ImageIcon("images/GameTitle.gif");
	private Image titleImg = titleIcon.getImage();

	int stageY = -(stageImg.getHeight(null) - bossStageImg.getHeight(null)); // 배경 이미지의 Y좌표
	int bossStageBY1 = -(stageImg.getHeight(null)); // 보스 스테이지 이미지 1의 Y좌표
	int bossStageBY2 = -(stageImg.getHeight(null) + bossStageImg.getHeight(null)); // 보스 스테이지 이미지 2의 Y좌표
	int appear = 1; // 적 비행기 출현 위치를 정하기 위해 선언

	Vector<EnemyUnit> enemyUnits = new Vector<>(); // 적 유닛을 모아놓을 배열

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
		setTitle("Strikers 1945");
		setSize(SCREEN_WIDTH, SCREEN_HEIGHT);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
	}

	public void batch(String playerPlane) { // 비행기 선택 후 비행기 new add
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

	public void enemyBatch() { // 적기 맵에 배치

		if (appear == 5000) {
			enemyUnits.add(new Enemy2(player, -100, 300, 150,150)); //而⑦뀓�뒪�듃 �꽆湲곌린
			enemyUnits.add(new Enemy2(player, 500, 300, 150,150));
		}

		if (appear == 1000 || appear == 3000) {
			enemyUnits.add(new Enemy1(player, 50, 0, 50, 50));
			enemyUnits.add(new Enemy1(player, 100, -50, 50, 50));
			enemyUnits.add(new Enemy1(player, 150, -100, 50, 50));
			enemyUnits.add(new Enemy1(player, 200, -150, 50, 50));
			enemyUnits.add(new Enemy1(player, 250, -200, 50, 50));
		}

		if (appear == 2000 || appear == 4000) {
			enemyUnits.add(new Enemy1(player, 500, 0, 50, 50));
			enemyUnits.add(new Enemy1(player, 450, -50, 50, 50));
			enemyUnits.add(new Enemy1(player, 400, -100, 50, 50));
			enemyUnits.add(new Enemy1(player, 350, -150, 50, 50));
			enemyUnits.add(new Enemy1(player, 300, -200, 50, 50));
		}

		if (appear == 500 || appear == 1500 || appear == 3500 || appear == 5000 || appear == 6000) {
			enemyUnits.add(new Enemy3(player, 600, -200, 100, 100)); // 컨텍스트 넘기기
			enemyUnits.add(new Enemy4(player, 0, 0, 100, 100));
		}
		
		
		if(appear == 6000) {
			enemyUnits.add(new Enemy5(player, 100, -50, 100, 100));
			enemyUnits.add(new Enemy5(player, 300, -50, 100, 100));
		}
		
		if(appear == 7000) {
			enemyUnits.add(new Enemy6(player, 650, 300, 200, 200));
		
		}
		
		if (appear ==  8000) {
			enemyUnits.add(new Enemy1(player, 100, 0, 50, 50));
			enemyUnits.add(new Enemy1(player, 200, 0, 50, 50));
			enemyUnits.add(new Enemy1(player, 300, 0, 50, 50));
			enemyUnits.add(new Enemy1(player, 400, 0, 50, 50));
			enemyUnits.add(new Enemy1(player, 500, 0, 50, 50));
		}

		
		if (appear == 10000) {
			enemyUnits.removeAllElements();
			boss = new Boss(player, 0, -300);
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

	public void crushBorder() { // 벽에 충돌하는 조건함수 >> Map 스레드 안에 적용
		if (player.getX() <= 0) {
			player.setX(0);
			repaint();
		} else if (player.getX() >= 585) {
			player.setX(585);
			repaint();
		}
		if (player.getY() <= 0) {
			player.setY(0);
			repaint();
		} else if (player.getY() >= 720) {
			player.setY(720);
			repaint();
		}
	}

	class GamePanel extends JPanel { // 맵 쓰레드
		public GamePanel() {
			new Thread(new Runnable() {
				@Override
				public void run() {
					while (true) {
						isgame = true;
						setLayout(null);
						// lifeLaInit();

						// 플레이어 생명 표시
						// 이걸 너무 막 넣었네.. 매번 쓰레드 돌아갈 때마다 연산하고 new하고 추가하고, add하면 느려집니다..
//						gamePanel.add(laLifecount);
//						gamePanel.add(laLifecount2);
//						gamePanel.add(laLifecount3);

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
							// lifeCounting();
							enemyBatch();
							crushBorder();
							appear++;
							repaint();
							Thread.sleep(3);
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

			for (int i = 0; i < enemyUnits.size(); i++) { // null이 아니면 그려라
				if (enemyUnits.get(i) != null) {
					enemyUnits.get(i).enemyUpdate(g);
				}
			}

			if (player != null) {
				player.playerUpdate(g);
			}

			if (boss != null) {
				boss.bossUpdate(g);
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

	private void lifeLaInit() { // 당장 안 써요. 괜히 만들어달라 했나..
		lifeCounticon = new ImageIcon("images/LifeCount.png");
		laLifecount = new JLabel(lifeCounticon);
		laLifecount2 = new JLabel(lifeCounticon);
		laLifecount3 = new JLabel(lifeCounticon);
		laLifecount.setBounds(0, 0, 50, 50);
		laLifecount2.setBounds(50, 0, 50, 50);
		laLifecount3.setBounds(100, 0, 50, 50);
	}

	public void lifeCounting() {
		if (player.getLife() == 3) {
			laLifecount.setVisible(true);
			laLifecount2.setVisible(true);
			laLifecount3.setVisible(true);
			repaint();
		} else if (player.getLife() == 2) {
			laLifecount.setVisible(true);
			laLifecount2.setVisible(true);
			laLifecount3.setVisible(false);
			repaint();
		} else if (player.getLife() == 1) {
			laLifecount.setVisible(true);
			laLifecount2.setVisible(false);
			laLifecount3.setVisible(false);
			repaint();
		} else {
			laLifecount.setVisible(false);
			laLifecount2.setVisible(false);
			laLifecount3.setVisible(false);
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

//	@Override // 필요없는데
//	public void batch() {
//		// TODO Auto-generated method stub
//		
//	}
}
