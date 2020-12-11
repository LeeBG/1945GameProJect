package test2;

import java.awt.Image;

import javax.swing.ImageIcon;

public class PlayerAttack {

	Image bulletImg1 = new ImageIcon("images/enemy1Bullet.jpg").getImage(); // 1�� �Ѿ�

	int bulletX;
	int bulletY;
	double bulletAngel = 270; //총알각도
	double bulletSpeed = 2; //총알속도

	int bulletWidth1 = bulletImg1.getWidth(null);
	int bulletHeight1 = bulletImg1.getHeight(null);

	public PlayerAttack(int x, int y) {
		this.bulletX = x;
		this.bulletY = y;
	}

	public void fire() {
		bulletX -= Math.cos(Math.toRadians(bulletAngel)) * bulletSpeed;
		bulletY -= Math.sin(Math.toRadians(bulletAngel)) * bulletSpeed;
	}
}
