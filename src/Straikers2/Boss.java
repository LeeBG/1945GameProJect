package Straikers2;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Boss extends JLabel {

	private Boss boss = this;
	private static final String TAG = "Boss : ";
	
	Image bossImg = new ImageIcon("images/bossSizeup.gif").getImage();
	
	int bossWidth = bossImg.getWidth(null);
	int bossHeight = bossImg.getHeight(null);
	
	int bossX = (VsBoss.SCREEN_WIDTH / 2) - (bossWidth / 2);
	int bossY = -280;
//	public int bossX = 100;
//	public int bossY = 10;
	
	public Boss() {
	}
	
	public void bossUpdate(Graphics g) {
		bossDraw(g);
	}
	
	public void bossDraw(Graphics g) {
		g.drawImage(bossImg, bossX, bossY, null);
	}
}
