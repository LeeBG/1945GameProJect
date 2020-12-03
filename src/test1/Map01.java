package test1;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.Image;

public class Map01 extends JFrame {
	
	ImageIcon iconbg = new ImageIcon("images/Stage 1.jpg");
	Image imgbg =  iconbg.getImage();
	
	int backY =-3300;
	
	public Map01() {
		
		MyPanel panel = new MyPanel();
		getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
						backY++;
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
			super.paintComponent(g); //배경 이미지 조정이 필요
			
			g.drawImage(imgbg,0,backY,500,imgbg.getHeight(null),this);
			
			
			
		}
		
		
	
		
	}
	
	
	public static void main(String[] args) {
		new Map01();
	}

}
