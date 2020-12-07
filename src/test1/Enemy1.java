package test1;

import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
public class Enemy1 extends JLabel {

	public Enemy1 enemy1 = this;
	public final static String TAG = "Enemy1: ";

	private Image image;
	private int x;
	private int y;
	private ImageIcon icEnemy1, icEnemy1L, icEnemy1R;

	Enemy1() {
		x= 200;
		y= 50;
		icEnemy1 = new ImageIcon("images/PLANE3.png");
		icEnemy1L = new ImageIcon("images/PLANE3L.png");
		icEnemy1R = new ImageIcon("images/PLANE3R.png");
		setIcon(icEnemy1);
		setSize(50, 50);
		setLocation(x, y);
		image = icEnemy1.getImage();
	}
	
	Enemy1(int x, int y){
		this();
		this.x = x;
		this.y = y;
		
	}
	
	
	
	
	
	public void movedown() {

		new Thread(new Runnable() {
			
			@Override
			public void run() {
				setIcon(icEnemy1);
				while (true) {
					y++;
					setLocation(x, y); // 내부에 repaint() 존재 이벤트가 종료되어야 실행되므로, 스레드를 활용
					try {
						Thread.sleep(5);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}).start();

	}

	public void moveleft() {

		new Thread(new Runnable() {

			@Override
			public void run() {
				setIcon(icEnemy1L);
				while (true) {
					x--;
					setLocation(x, y); // 내부에 repaint() 존재 이벤트가 종료되어야 실행되므로, 스레드를 활용
					try {
						Thread.sleep(10);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}).start();

	}

	public void moveright() {
		new Thread(new Runnable() {

			@Override
			public void run() {
				setIcon(icEnemy1R);
				while (true) {
					x++;
					setLocation(x, y); // 내부에 repaint() 존재 이벤트가 종료되어야 실행되므로, 스레드를 활용
					try {
						Thread.sleep(5);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}).start();

	}
	
	
	public void leftdown() {
		movedown();
		moveleft();
		
	}
	
	
}
