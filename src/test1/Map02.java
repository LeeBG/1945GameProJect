package test1;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.Image;

public class Map02 extends JFrame {
	
	ImageIcon iconbg = new ImageIcon("D:\\download/bback.png");
	Image imgbg =  iconbg.getImage();
	
	int backX =0;
	
	public Map02() {
		
		MyPanel panel = new MyPanel();
		getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		
		setSize(500,500);
		setVisible(true);

	}
	
	
	
	class MyPanel extends JPanel{
		public MyPanel() {
			setFocusable(true);
			
			
			new Thread(new Runnable() {
				
				@Override
				public void run() {
					while (true) {
						backX--;
						repaint();
						
						try {
							Thread.sleep(100);
						} catch (Exception e) {
							// TODO: handle exception
						}
					}
					
				}
			}).start();
			
			
		}
		
		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			
			g.drawImage(imgbg,backX,0,this);
			
			
		}
		
		
	
		
	}
	
	
	public static void main(String[] args) {
		new Map02();
	}

}
