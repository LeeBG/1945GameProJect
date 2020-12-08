package StraikersDEMO;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Boss extends JLabel {

	private Boss boss = this;
	private static final String TAG = "Boss : ";

	ImageIcon bossIcon = new ImageIcon("images/playerPlane1.png");
	Image bossImg = bossIcon.getImage();
	
	public int bossX = 100;
	public int bossY = 300;
	
	private int playerWidth = bossImg.getWidth(null);
	private int playerHeight = bossImg.getHeight(null);
	
	public Boss() {
	}
}
