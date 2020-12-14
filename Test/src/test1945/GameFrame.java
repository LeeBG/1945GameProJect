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
		batch();
		listener();
		setVisible(true);
	}

	private void init() {		
		isgame = false;
		gameMap = new GameMap();
		heightStart = 5515;
		heightEnd = 6135;
		playerPlane = new PlayerPlane();
	}
		

	private void setting() {
		setTitle("1945_MAP_TEST");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(480, 620);
		gameMap.setLayout(null);
		setContentPane(gameMap);
		setLocationRelativeTo(null);
	}

	private void batch() {  
		gameMap.add(playerPlane);
	}

	public void enemybatch() { 
		if (heightStart % 300 == 0) {
			gameMap.add(new EnemyPlane(playerPlane));
		}
	}
	
	public void change(String panelName) {
		if(panelName.equals("gameTitle")) {
			getContentPane().removeAll();
			getContentPane().add(gameTitle);
			revalidate();
			repaint();
		}else if(panelName.equals("selectAPL")) {
			getContentPane().removeAll();
			getContentPane().add(selectAPL);
			revalidate();
			repaint();
		}else {
			getContentPane().removeAll();
			getContentPane().add(gameMap);
			revalidate();
			repaint();
		}
	}

	private void listener() { 
		addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
					playerPlane.moveRight();

				} else if (e.getKeyCode() == KeyEvent.VK_LEFT) {

					playerPlane.moveLeft(); 

				} else if (e.getKeyCode() == KeyEvent.VK_UP) {

					playerPlane.moveUp(); 

				} else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
					playerPlane.moveDown();
				}
			}

			@Override
			public void keyReleased(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
					playerPlane.isRight = false;
				} else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
					playerPlane.isLeft = false;
				} else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
					playerPlane.isDown = false;
				} else if (e.getKeyCode() == KeyEvent.VK_UP) {
					playerPlane.isUp = false;
				}
			}

		});
	}

	public void crushBorder() {						
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
		public GameMap() {
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
							crushBorder();							
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
			gameTitle.setLayout(null);
			setContentPane(gameTitle);
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
		public SelectAPL() {
			icon = new ImageIcon("images/TitleImage.png");
			img = icon.getImage();
		}
		@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				
			}
	}
	
	public static void main(String[] args) {
		new GameFrame();
	}

}
