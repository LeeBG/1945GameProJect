package test2;

import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class EnemyBullet extends JLabel{

	ImageIcon icBullet;
	
	int bulletX;
	int bulletY;

	public EnemyBullet(int x, int y) {
		this.bulletX = x;
		this.bulletY = y;
		icBullet = new ImageIcon("images/bullet(e).png");
		setIcon(icBullet);
		setSize(10, 10);
		setLocation(x, y);
		
		//this.fire();
	
	}
	
	public void fire() {
		
		System.out.println("enemybullet 발사 ");
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				while (true) {
					bulletY++;
					setLocation(bulletX, bulletY); 
					if (bulletY > 639) { 
						System.out.println("enemy thread 종료");
						break;
					}

					try {
						Thread.sleep(1);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}).start();
		
	
		//this.bulletY ++;	
	}
}
