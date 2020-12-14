package test1945;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;


public class GameFrame extends JFrame {
	public GameFrame gameFrame = this;
	private boolean isgame;
	private GameMap gameMap;			//인게임 패널 
	private GameTitle gameTitle;		//타이틀 패널
	private SelectAPL selectAPL;		//비행시 선택 패널
	private int heightStart, heightEnd;
	private PlayerPlane playerPlane;
	private ImageIcon icon;
	private Image img;
	
	public GameFrame() {
		init();
		setting();
		listener();
		setVisible(true);
	}

	private void init() {
		change("gameTitle");				//초기 타이틀 화면
		isgame = false;						//게임 중 이지 않은 상태
		heightStart = 5515;
		heightEnd = 6135;
		
	}
		

	private void setting() {
		setTitle("1945_MAP_TEST");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(480, 620);
		setLocationRelativeTo(null);
	}

	private void batch(String playerPlane) {			//비행기 선택에서 받을것
		if(playerPlane=="playerPlane") {
			this.playerPlane = new PlayerPlane();
			gameMap.add(this.playerPlane);
		}
			
		else if(playerPlane == "playerPlane2")
			return;
	}

	public void enemybatch() { 
		if (heightStart % 300 == 0) {
			gameMap.add(new EnemyPlane(playerPlane));
		}
	}
	private void listener() {
		addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_RIGHT && isgame==true) {
					playerPlane.moveRight();
				} else if (e.getKeyCode() == KeyEvent.VK_LEFT && isgame==true) {

					playerPlane.moveLeft(); 

				} else if (e.getKeyCode() == KeyEvent.VK_UP && isgame==true) {

					playerPlane.moveUp(); 

				} else if (e.getKeyCode() == KeyEvent.VK_DOWN && isgame==true) {
					playerPlane.moveDown();
				} else if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					change("GameMap");
					batch("playerPlane");
				}
			}
			@Override
			public void keyReleased(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_RIGHT && isgame==true) {
					playerPlane.isRight = false;
				} else if (e.getKeyCode() == KeyEvent.VK_LEFT && isgame==true) {
					playerPlane.isLeft = false;
				} else if (e.getKeyCode() == KeyEvent.VK_DOWN && isgame==true) {
					playerPlane.isDown = false;
				} else if (e.getKeyCode() == KeyEvent.VK_UP && isgame==true) {
					playerPlane.isUp = false;
				}
			}

		});
	}
	public void change(String panelName) {
		if(panelName.equals("gameTitle")) {
			gameTitle = new GameTitle(gameFrame);
			getContentPane().removeAll();
			getContentPane().add(gameTitle);
			revalidate();
			repaint();
		}else if(panelName.equals("selectAPL")) {
			selectAPL = new SelectAPL(gameFrame);
			getContentPane().removeAll();
			getContentPane().add(selectAPL);
			revalidate();
			repaint();
		}else {
			gameMap = new GameMap(gameFrame);
			getContentPane().removeAll();
			getContentPane().add(gameMap);
			revalidate();
			repaint();
		}
	}


	public void crushBorder() {				//벽에 충돌하는 조건함수				
		if(playerPlane.getX()<=0) {
			playerPlane.setX(0);
			repaint();
		}else if(playerPlane.getX()>=395) {
			playerPlane.setX(395);
			repaint();
		}
		if(playerPlane.getY()<=0) {
			playerPlane.setY(0);
			repaint();
		}else if(playerPlane.getY()>=520) {
			playerPlane.setY(520);
			repaint();
		}
	}
	
	class GameMap extends JPanel { // 사실상 맵
		private GameFrame win;		
		
		
		
		public GameMap(GameFrame win) {
			isgame = true;
			setLayout(null);
			this.win = win;

//			setFocusable(true);
			
			icon = new ImageIcon("images/Stage1_1.png");
			img = icon.getImage();
			new Thread(new Runnable() {
				@Override
				public void run() {
					while (heightStart > 0) {
						try {
							heightStart -= 1;
							heightEnd -= 1;
							Thread.sleep(10);
							enemybatch();
							crushBorder();					//벽에 충돌하는 조건함수		
						}catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
			}).start();	
		}

		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			g.drawImage(img, 0, 0, 480, 620, 0, heightStart, 318, heightEnd, this);
			repaint();
		}
	}

	class GameTitle extends JPanel{//게임 시작화면 구현
		private GameFrame win;
		public GameTitle(GameFrame win) {
			setLayout(null);
			this.win=win;
			icon = new ImageIcon("images/gametitle.gif");
			img = icon.getImage();
		}
		@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				g.drawImage(img, 0, 0, 480, 620, 0,0,338,566,this);
			}
	}
	
	class SelectAPL extends JPanel{						// 비행기 선택 화면
		private GameFrame win;
		
		public SelectAPL(GameFrame win) {
			setLayout(null);
			this.win=win;
			icon = new ImageIcon("images/SelectPlane.png");
			img = icon.getImage();
		}
		@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				g.drawImage(img, 0, 0, 480, 620, 0,0,196,182,this);
			}
	}
	
	public static void main(String[] args) {
		new GameFrame();
	}

}
