package test1;

import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Enemy extends JLabel {

	//public Enemy enemy = this;
	public final static String TAG = "Enemy: ";

	private Image image;
	private int x;
	private int y;


}
