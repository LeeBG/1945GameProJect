package Straikers2;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Boss extends JLabel {

	private Boss boss = this;
	private static final String TAG = "Boss : ";
	
	ImageIcon bossIcon = new ImageIcon("images/boss.gif");
	Image bossImg = bossIcon.getImage();
	
	int bossX = 100;
	int bossY = 10;
//	public int bossX = 100;
//	public int bossY = 10;
	
	int bossWidth = bossImg.getWidth(null);
	int bossHeight = bossImg.getHeight(null);
	
	public Boss() {
	}
	
	public void bossUpdate(Graphics g) {
		bossDraw(g);
	}
	
	public void bossDraw(Graphics g) {
		g.drawImage(bossImg, bossX, bossY, null);
	}
}
