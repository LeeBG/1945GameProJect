package test1;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class TestMap extends JFrame {
		private PlayerPlane player;
	ImageIcon iconbg = new ImageIcon("D:\\download/bback.png");
	Image imgbg =  iconbg.getImage();

	
	int backX =0;
	
	public TestMap() {
		
		MyPanel panel = new MyPanel();
		player = new PlayerPlane();
		
		getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		panel.add(player);
		

		
		
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
		new TestMap();
	}

}
