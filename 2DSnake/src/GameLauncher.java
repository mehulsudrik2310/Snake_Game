import java.awt.Color;

import javax.swing.JFrame;
public class GameLauncher {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		JFrame obj = new JFrame();
		Gameplay gp = new Gameplay();
		obj.setTitle("My Snake Game");
		obj.setBounds(10, 10, 905, 700);
		obj.getContentPane().setBackground(Color.DARK_GRAY);
		obj.setResizable(false);
		obj.setVisible(true);
		obj.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		obj.add(gp);		
	}
}
