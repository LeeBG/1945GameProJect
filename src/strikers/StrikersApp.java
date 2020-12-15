package strikers;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import lombok.Data;

public class StrikersApp extends JFrame implements Initable{
	//implements Initable, 이미지니까 라벨, 이미지니까 imp안해도 됨
	private StrikersApp strikerApp = this;
	private static final String TAG = "StrikerApp: ";
	private JLabel laBackground = new JLabel();
	private Player player;
	private Item item;
	private Enemy enemy;
	private Vector<Enemy> es;
	private Vector<Item> items;
	
	public JLabel getLaBackground() {
		return laBackground;
	}
	public void setLaBackground(JLabel laBackground) {
		this.laBackground = laBackground;
	}
		
	//x,y,z축 설정
	public final static int XAXIS = 480; // x 480 y 620
	public final static int YAXIS = 620;
	public final static int ZAXIS= 0;
	
	//플레이어, 적, 아이템 속도
	public static int speed1 = 1;
	public static int speed2 = 2;
	public static int speed5 = 5;
	public static int speed10 = 10;
	public static int speed20 = 15;
	
	public StrikersApp() {
		init();
		setting();
		batch();
		listener();
		setVisible(true);
	}

	@Override
	public void init() {
		laBackground = new JLabel(new ImageIcon("image/bg.png"));
				
		es = new Vector<>();
		player = new Player();
		
		items = new Vector<>();
		item = new Item();
		
		
		for (int i = 0; i < 8; i++) {
			es.add(new Enemy());
		}
		
		for (int i = 0; i < 3; i++) {
			items.add(new Item());
			//vItem.get(i).start();
		}
		
		
	}

	@Override
	public void setting() {
		setTitle("1945");
		setSize(XAXIS, YAXIS); // 480, 620
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(null);
		setContentPane(laBackground);
		setResizable(false);
		setLocationRelativeTo(null);
	}

	@Override
	public void batch() {
		add(player);
		add(item);
		
		for (int i = 0; i < 8; i++) {
			add(es.get(i));
		}
		for (int i = 0; i < 3; i++) {
			add(items.get(i));
		}
		
	}

	@Override
	public void listener() {
		addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
					player.moveRight();
				} else if(e.getKeyCode() == KeyEvent.VK_LEFT) {
					player.moveLeft();
					
				} else if (e.getKeyCode() == KeyEvent.VK_UP) {
					player.moveUp();
					
				}else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
					player.moveDown();
					
				}else if(e.getKeyCode() == KeyEvent.VK_SPACE) {
					es.get(0).EnemymoveRight();
					//items.get(1).moveRight();
					//items.get(2).itemAppear();
//					es.get(1).moveLeft();
//					es.get(2).moveUp();
//					es.get(3).moveDown();
//					es.get(4).moveTenUp();
//					es.get(5).moveTwoUp();
//					es.get(6).moveFiveDown();
//					es.get(7).moveSevenDown();
					//items.get(1).itemreflect();
					items.get(1).itemAppear(player);
				}
			}
			@Override
			public void keyReleased(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
					player.isRight = false;
					//item.isRight = false;
				} else if(e.getKeyCode() == KeyEvent.VK_LEFT) {
					player.isLeft = false;
					//item.isRight = false;
				} else if(e.getKeyCode() == KeyEvent.VK_UP) {
					player.isUp = false;
					//item.isRight = false;
				} else if(e.getKeyCode() == KeyEvent.VK_DOWN) {
					player.isDown = false;
					//item.isRight = false;
				} 
			}
		});
	}	
	
	public static void main(String[] args) {
		new StrikersApp();
	}
}