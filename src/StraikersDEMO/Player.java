package StraikersDEMO;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.security.Key;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Player extends Thread {

	private Player player = this;
	private static final String TAG = "Player : ";

	private ImageIcon PlayerIcon = new ImageIcon("images/PlayerPlane1.png");
	private Image Player1 = PlayerIcon.getImage();

	private int playerX = 200;
	private int playerY = 500;
	private int playerWidth = Player1.getWidth(null);
	private int playerHeight = Player1.getHeight(null);
	private int cnt;
	private int fireSpeed = 100;

	public boolean isAttack = false;
	public boolean isUp = false;
	public boolean isDown = false;
	public boolean isLeft = false;
	public boolean isRight = false;

	ArrayList<PlayerAttack> playerAttackList = new ArrayList<PlayerAttack>();
	private PlayerAttack playerAttack;

	@Override
	public void run() {
		while (true) {
			cnt = 0;
			try {
				Thread.sleep(5);
				keyProcess();
				playerAttackProcess();
				cnt++;
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	private void keyProcess() {
		if (isAttack && (cnt % 4) == 0) {
			try {
				Thread.sleep(10);
				playerAttack = new PlayerAttack(playerX + 21, playerY - 40);
				playerAttackList.add(playerAttack);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (isUp) {
			playerY--;
		}
		if (isDown) {
			playerY++;
		}
		if (isLeft) {
			playerX--;
		}
		if (isRight) {
			playerX++;
		}
	}

	private void playerAttackProcess() {
		for (int i = 0; i < playerAttackList.size(); i++) {
			playerAttack = playerAttackList.get(i);
			playerAttack.fire();
		}   
	}

	public void gameDraw(Graphics g) {
		playerDraw(g);
	}

	public void playerDraw(Graphics g) {
		g.drawImage(Player1, playerX, playerY, null);
		for (int i = 0; i < playerAttackList.size(); i++) {
			playerAttack = playerAttackList.get(i);
			g.drawImage(playerAttack.bulletImg, playerAttack.x, playerAttack.y, null);
		}
	}

	public void setAttack(boolean isAttack) {
		this.isAttack = isAttack;
	}

	public void setUp(boolean isUp) {
		this.isUp = isUp;
	}

	public void setDown(boolean isDown) {
		this.isDown = isDown;
	}

	public void setLeft(boolean isLeft) {
		this.isLeft = isLeft;
	}

	public void setRight(boolean isRight) {
		this.isRight = isRight;
	}
}
