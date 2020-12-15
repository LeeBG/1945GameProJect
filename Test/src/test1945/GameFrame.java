package test1945;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class GameFrame extends JFrame implements initable{
	public GameFrame gameFrame = this;
	public boolean isgame;				//게임실행 여부
	private GameMap gameMap;			//인게임 패널 
	private GameTitle gameTitle;		//타이틀 패널
	private SelectAPL selectAPL;		//비행시 선택 패널
	private int heightStart, heightEnd;	//화면에보이는 시작점 높이
	private PlayerPlane playerPlane;	//플레이어
	private ImageIcon icon;				//배경이미지아이콘
	private Image img;					//이미지
	
	public void setHeightStart(int heightStart) {
		this.heightStart = heightStart;
	}
	public void setHeightEnd(int heightEnd) {
		this.heightEnd=heightEnd;
	}
	public PlayerPlane getPlayerPlane() {
		return playerPlane;
	}
	public void setPlayerPlane(PlayerPlane playerplane) {
		this.playerPlane=playerplane;
	}
	
	public GameFrame() {
		init();
		setting();
		listener();
		setVisible(true);
	}

	public void init() {
		change("gameTitle");				//초기 타이틀 화면
		isgame = false;						//게임 중 이지 않은 상태
		heightStart = 5515;					//좌측끝  X좌표
		heightEnd = 6135;					//우측끝  X좌표
	}
		

	public void setting() {
		setTitle("1945_MAP_TEST");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(670, 820);
		setLocationRelativeTo(null);
	}

	public void batch(String playerPlane) {			//비행기 선택에서 받을것
		if(playerPlane=="playerPlane") {
			this.playerPlane = new PlayerPlane("PLANE1");
			gameMap.add(this.playerPlane);
		}else if(playerPlane == "playerPlane2") {
			this.playerPlane = new PlayerPlane("PLANE3");
			gameMap.add(this.playerPlane);
		}else if(playerPlane=="playerPlane3") {
			this.playerPlane = new PlayerPlane("PLANE4");
			gameMap.add(this.playerPlane);
		}
	}

	public void enemybatch() { 						//적 배치 300px마다 적 랜덤 등장
		if (heightStart % 300 == 0) {
			gameMap.add(new EnemyPlane(playerPlane,this));
		}
	}
	public void listener() {						// 키보드 리스너 함수
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
					
					change("selectAPL");											//처음 화면에서 ENTER키 누를시 비행기 선택
					
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
	
	// 패널 바꾸기 함수
	
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
		}else if(panelName.equals("gameMap")){
			gameMap = new GameMap(gameFrame);
			getContentPane().removeAll();
			getContentPane().add(gameMap);
			revalidate();
			repaint();
		}else {
			gameTitle = null;
			selectAPL = null;
			gameMap = null;
			isgame=false;
			getContentPane().removeAll();
			revalidate();
			repaint();
		}
	}


	public void crushBorder() {				//벽에 충돌하는 조건함수 >> Map 스레드 안에 적용
		if(playerPlane.getX()<=0) {
			playerPlane.setX(0);
			repaint();
		}else if(playerPlane.getX()>=585) {
			playerPlane.setX(585);
			repaint();
		}
		if(playerPlane.getY()<=0) {
			playerPlane.setY(0);
			repaint();
		}else if(playerPlane.getY()>=720) {
			playerPlane.setY(720);
			repaint();
		}
	}
	
	class GameMap extends JPanel { 					//게임 map Thread 패널
		private GameFrame win;	
		public GameMap(GameFrame win) {
			isgame = true;
			setLayout(null);
			this.win = win;
			
			icon = new ImageIcon("images/Stage1_1.png");
			img = icon.getImage();
			new Thread(new Runnable() {
				@Override
				public void run() {
					while (heightStart > 0 && isgame==true) {
						try {
							heightStart -= 1;				//Map이 위로 올라가면서 바뀜
							heightEnd -= 1;
							Thread.sleep(10);
							enemybatch();
							crushBorder();					//벽에 충돌하는 조건함수
						}catch (Exception e) {
							e.printStackTrace();
						}
					}
				}
			}).start();	
		}
		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			if(isgame == true) {
				g.drawImage(img, 0, 0, 670, 820, 0, heightStart, 318, heightEnd, this);
			}
			repaint();
		}
	}

	class GameTitle extends JPanel{					//게임 시작화면 구현
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
				g.drawImage(img, 0, 0, 670, 820, 0,0,338,594,this);
			}
	}
	
	class SelectAPL extends JPanel{						// 비행기 선택 화면
		private GameFrame win;
		private SelectAPL selectAPL = this;
		private ImageIcon p1icon,p2icon,p3icon;
		private ImageIcon planeIcon,planeIcon2,planeIcon3;
		private ImageIcon bp1icon,bp2icon,bp3icon;		//버튼 누를시 커지는 이미지
		public SelectAPL(GameFrame win) {
			setLayout(null);
			this.win=win;
			icon = new ImageIcon("images/SelectPlane.png");
			img = icon.getImage();
			
			p1icon = new ImageIcon("images/PLANE1.png");
			p2icon = new ImageIcon("images/PLANE3.png");
			p3icon = new ImageIcon("images/PLANE4.png");
			
			bp1icon = new ImageIcon("images/BIGPLANE1.png");
			bp2icon = new ImageIcon("images/BIGPLANE3.png");
			bp3icon = new ImageIcon("images/BIGPLANE4.png");
			
			planeIcon = new ImageIcon("images/planeImg1.png");
			planeIcon2 = new ImageIcon("images/planeImg2.png");
			planeIcon3 = new ImageIcon("images/planeImg3.png");
			
			JButton btn = new JButton("",p1icon);
			JButton btn2 = new JButton("",p2icon);
			JButton btn3 = new JButton("",p3icon);
			JLabel planeImg = new JLabel("");
			
			// 버튼 테두리 없음	
			btn.setBorderPainted(false);
			btn2.setBorderPainted(false);
			btn3.setBorderPainted(false);
			
			//버튼 채우기 없음
			btn.setContentAreaFilled(false);
			btn2.setContentAreaFilled(false);
			btn3.setContentAreaFilled(false);
			
			//버튼 투명
			btn.setOpaque(false);
			btn2.setOpaque(false);
			btn3.setOpaque(false);
			
			//버튼 액션
			btn.addMouseListener(new MouseAdapter() {			//버튼 클릭 리스너
				@Override
				public void mousePressed(MouseEvent e) {
					change("gameMap");
					batch("playerPlane");
				}
				@Override
				public void mouseEntered(MouseEvent e) {
					planeImg.setIcon(planeIcon);
					btn.setSize(100, 89);
					btn.setIcon(bp1icon);
				}
				@Override
				public void mouseExited(MouseEvent e) {
					planeImg.setIcon(null);
					btn.setSize(70,59);
					btn.setIcon(p1icon);
				}
			});
			btn2.addMouseListener(new MouseAdapter() {			//버튼 클릭 리스너
				@Override
				public void mousePressed(MouseEvent e) {
					change("gameMap");
					batch("playerPlane2");
				}
				@Override
				public void mouseEntered(MouseEvent e) {
					planeImg.setIcon(planeIcon2);
					btn2.setSize(100, 89);
					btn2.setIcon(bp2icon);
				}
				@Override
				public void mouseExited(MouseEvent e) {
					planeImg.setIcon(null);
					btn2.setSize(70,59);
					btn2.setIcon(p2icon);
				}
			});

			btn3.addMouseListener(new MouseAdapter() {			//버튼 클릭 리스너
				@Override
				public void mousePressed(MouseEvent e) {
					change("gameMap");
					batch("playerPlane3");
				}
				@Override
				public void mouseEntered(MouseEvent e) {
					planeImg.setIcon(planeIcon3);
					btn3.setSize(100, 89);
					btn3.setIcon(bp3icon);
				}
				@Override
				public void mouseExited(MouseEvent e) {
					planeImg.setIcon(null);
					btn3.setSize(70,59);
					btn3.setIcon(p3icon);
				}
			});
			
			btn.setBounds(150,640,70,59);
			btn2.setBounds(290,640,70,59);
			btn3.setBounds(430,640,70,59);
			planeImg.setBounds(220,250,223,318);
			
			this.add(planeImg);
			this.add(btn);
			this.add(btn2);
			this.add(btn3);
		}
		
		@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				g.drawImage(img, 0, 0, 670, 820, 0,0,196,182,this);
				repaint();
			}
	}



}
