package Straikers2;

import java.awt.Image;

import javax.swing.ImageIcon;

public class PlayerAttack {

	Image bulletImg1 = new ImageIcon("images/PlayerBullet1.png").getImage();	// 1번 총알
	Image bulletImg2 = new ImageIcon("images/PlayerBullet2.png").getImage();	// 2번 총알
	
	int bulletX;
	int bulletY;
	
	int bulletWidth1 = bulletImg1.getWidth(null);
	int bulletHeight1 = bulletImg1.getHeight(null);
	
	int bulletWidth2 = bulletImg2.getWidth(null);
	int bulletHeight2 = bulletImg2.getHeight(null);
	
	public PlayerAttack(int x, int y) {
		this.bulletX = x;
		this.bulletY = y;
	}
	
	public void fire() {
		this.bulletY --;	// 총알을 발사하면 y값을 1씩 빼면서 앞으로 나간다
	}
}
