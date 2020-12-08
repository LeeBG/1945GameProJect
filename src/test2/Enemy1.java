package test2;

import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//라벨은 LOMBOK 안 먹음
public class Enemy1 extends EnemyUnit {

	public Enemy1 Enemy1 = this;
	public final static String TAG = "Enemy1: ";
	private Image image = new ImageIcon("images/PLANE3.png").getImage();
	private ImageIcon icEnemy1;
	
	
	Enemy1() {
		x=0;
		y=0;
		icEnemy1 = new ImageIcon("images/PLANE3.png");
		setIcon(icEnemy1);
		setSize(100, 50);
		setLocation(x, y);
	
		this.movedown();


	}

	Enemy1(int x, int y) {
		this.x =x;
		this.y =y;
		icEnemy1 = new ImageIcon("images/PLANE3.png");
		setIcon(icEnemy1);
		setSize(100, 50);
		setLocation(x, y);
	
		this.movedown();


	}

	public void leftdown() {
		movedown();
		moveleft();

	}

	public Enemy1 getEnemy1() {
		return Enemy1;
	}

	public void setEnemy1(Enemy1 Enemy1) {
		this.Enemy1 = Enemy1;
	}

	public ImageIcon getIcEnemy1() {
		return icEnemy1;
	}

	public void setIcEnemy1(ImageIcon icEnemy1) {
		this.icEnemy1 = icEnemy1;
	}

	public static String getTag() {
		return TAG;
	}


	
	
	

}
