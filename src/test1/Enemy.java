package test1;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Enemy extends JLabel {

	public Enemy player = this;
	public final static String TAG = "Player: ";

	public ImageIcon icEnemy;
	public int x = 55;
	public int y = 400;


	public Enemy() {
		icEnemy = new ImageIcon("images/PLANE4.png");
		setIcon(icEnemy);
		setSize(100, 50);
		setLocation(x, y);
	}

	


	public void attack() {

	}

}
