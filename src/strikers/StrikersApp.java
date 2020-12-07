package strikers;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class StrikersApp extends JFrame implements Initable{
	// 쓰던 안쓰던 필수 작성
	private StrikersApp bubbleApp = this;
	// 태그
	private static final String TAG = "BubbleApp: ";
	private JLabel laBackground;
	private Player player;
	private Vector<Enemy> es;
	//private Enemy enemy1, enemy2;
	

	public StrikersApp() {
		init();
		setting();
		batch();
		listener();
		
		setVisible(true);
	}

	public static void main(String[] args) {
		new StrikersApp();
	}

	@Override
	public void init() {
		es = new Vector<>();
		laBackground = new JLabel(new ImageIcon("image/bg.png"));
		player = new Player();
		for (int i = 0; i < 8; i++) {
			es.add(new Enemy());
		}
	}

	@Override
	public void setting() {
		setTitle("버블버블");
		setSize(1000, 639);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(null);
		setContentPane(laBackground);
		setResizable(false);
		setLocationRelativeTo(null);
	}

	@Override
	public void batch() {
		add(player);
		for (int i = 0; i < 8; i++) {
			add(es.get(i));
		}
	}

	@Override
	public void listener() {
		addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				
				if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
					player.moveRight();
					//enemy.moveRight();
				
				} else if(e.getKeyCode() == KeyEvent.VK_LEFT) {
					player.moveLeft();
					//enemy.moveLeft();
				} else if (e.getKeyCode() == KeyEvent.VK_UP) {
					player.moveUp();
					//enemy.moveUp();
				}else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
					player.moveDown();
					//enemy.moveDown();
				}else if(e.getKeyCode() == KeyEvent.VK_SPACE) {
					es.get(0).moveRight();
					es.get(1).moveLeft();
					//es.get(2).moveUp();
					es.get(3).moveDown();
					es.get(4).moveTenUp();
					es.get(5).moveTwoUp();
					es.get(6).moveFiveDown();
					es.get(7).moveSevenDown();
				}
			}
			@Override
			public void keyReleased(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
					player.isRight = false;
					//enemy.isRight = false;
				} else if(e.getKeyCode() == KeyEvent.VK_LEFT) {
					player.isLeft = false;
					//enemy.isLeft = false;
				} else if(e.getKeyCode() == KeyEvent.VK_UP) {
					player.isUp = false;
					//enemy.isUp = false;
				} else if(e.getKeyCode() == KeyEvent.VK_DOWN) {
					player.isDown = false;
					//enemy.isDown = false;
				} 
			}
		});
	
		
	
	}
	
	
	
	
	
	
}
