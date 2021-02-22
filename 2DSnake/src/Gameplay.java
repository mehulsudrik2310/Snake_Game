import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;

import java.util.Random;

import javax.swing.Timer;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import java.net.URL;

public class Gameplay extends JPanel implements KeyListener, ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int[] snakexlength = new int[750];
	private int[] snakeylength = new int[750];

	private boolean left = false;
	private boolean right = false;
	private boolean up = false;
	private boolean down = false;

	private int lengthofsnake = 3;

	private ImageIcon rightmouth;
	private ImageIcon leftmouth;
	private ImageIcon upmouth;
	private ImageIcon downmouth;

	URL eating;
	URL gameover;
	
	private Timer timer;
	private int delay = 100;
	
	private ImageIcon foodimage;
	
	private Random random = new Random();
	private int xpos = (1 + random.nextInt(33)) * 25;
	private int ypos = (3 + random.nextInt(21)) * 25;
	
	private int score = 0;

	private ImageIcon snakeimage;

	private ImageIcon titleImage;
	private int moves = 0;

	public Gameplay() {
		// TODO Auto-generated constructor stub
		addKeyListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
		timer = new Timer(delay, this);
		timer.start();

	}
	void playMusic(URL musicLocation) {
		
		try {
			AudioInputStream audioInput = AudioSystem.getAudioInputStream(musicLocation);
			Clip clip = AudioSystem.getClip();
			clip.open(audioInput);
			clip.start();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void paint(Graphics g) {

		if (moves == 0) {

			snakexlength[2] = 50;
			snakexlength[1] = 75;
			snakexlength[0] = 100;

			snakeylength[2] = 100;
			snakeylength[1] = 100;
			snakeylength[0] = 100;
		}

		g.setColor(Color.white);
		g.drawRect(24, 10, 851, 55);

		titleImage = new ImageIcon(getClass().getClassLoader().getResource("snaketitle.jpg"));
		titleImage.paintIcon(this, g, 25, 11);

		g.setColor(Color.white);
		g.drawRect(24, 74, 851, 577);

		g.setColor(Color.black);
		g.fillRect(25, 75, 850, 575);
		
		g.setColor(Color.white);
		g.setFont(new Font("arial",Font.PLAIN,14));
		g.drawString("Score : "+ score, 780, 30);
		g.drawString("Length : "+ lengthofsnake, 780, 50);

		rightmouth = new ImageIcon(getClass().getClassLoader().getResource("rightmouth.png"));
		rightmouth.paintIcon(this, g, snakexlength[0], snakeylength[0]);

		for (int a = 0; a < lengthofsnake; a++) {
			if (a == 0 && right) {
				rightmouth = new ImageIcon(getClass().getClassLoader().getResource("rightmouth.png"));
				rightmouth.paintIcon(this, g, snakexlength[a], snakeylength[a]);
			}
			if (a == 0 && left) {
				leftmouth = new ImageIcon(getClass().getClassLoader().getResource("leftmouth.png"));
				leftmouth.paintIcon(this, g, snakexlength[a], snakeylength[a]);
			}
			if (a == 0 && up) {
				upmouth = new ImageIcon(getClass().getClassLoader().getResource("upmouth.png"));
				upmouth.paintIcon(this, g, snakexlength[a], snakeylength[a]);
			}
			if (a == 0 && down) {
				downmouth = new ImageIcon(getClass().getClassLoader().getResource("downmouth.png"));
				downmouth.paintIcon(this, g, snakexlength[a], snakeylength[a]);
			}
			if (a != 0) {
				snakeimage = new ImageIcon(getClass().getClassLoader().getResource("snakeimage.png"));
				snakeimage.paintIcon(this, g, snakexlength[a], snakeylength[a]);
			}
		}
		foodimage = new ImageIcon(getClass().getClassLoader().getResource("food.png"));
		if((xpos == snakexlength[0]) && (ypos == snakeylength[0])) {
			
			eating = getClass().getResource("eating.wav");
			playMusic(eating);
			
			lengthofsnake++;
			score++;
			xpos = (1 + random.nextInt(33)) * 25;
			ypos = (3 + random.nextInt(21)) * 25;
		}
		foodimage.paintIcon(this, g, xpos, ypos);
		
		for(int b =1 ;b<lengthofsnake;b++) {
			if((snakexlength[b] == snakexlength[0]) && (snakeylength[b] == snakeylength[0])) {
				right = false;
				left = false;
				up = false;
				down = false;
				
				gameover = getClass().getResource("gameover.wav");
				playMusic(gameover);
				
				g.setColor(Color.white);
				g.setFont(new Font("arial",Font.BOLD,50));
				g.drawString("GAME OVER", 300, 300);
				
				g.setFont(new Font("arial",Font.BOLD,20));
				g.drawString("SPACE to restart", 350, 340);
			}
			
			
		}
		
		g.dispose();

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		timer.start();
		if (right) {
			for(int r=lengthofsnake-1;r>=0;r--) {
				snakeylength[r+1] = snakeylength[r];
			}
			for(int r=lengthofsnake-1;r>=0;r--) {
				if(r==0) {
					snakexlength[r] = snakexlength[r] + 25;
				}
				else {
					snakexlength[r] = snakexlength[r-1];
				}
				if(snakexlength[r]>850) {
					snakexlength[r] = 25;
				}
			}
			repaint();
		}
		if (left) {
			for(int r=lengthofsnake-1;r>=0;r--) {
				snakeylength[r+1] = snakeylength[r];
			}
			for(int r=lengthofsnake-1;r>=0;r--) {
				if(r==0) {
					snakexlength[r] = snakexlength[r] - 25;
				}
				else {
					snakexlength[r] = snakexlength[r-1];
				}
				if(snakexlength[r]<25) {
					snakexlength[r] = 850;
				}
			}
			repaint();
		}
		if (up) {
			for(int r=lengthofsnake-1;r>=0;r--) {
				snakexlength[r+1] = snakexlength[r];
			}
			for(int r=lengthofsnake-1;r>=0;r--) {
				if(r==0) {
					snakeylength[r] = snakeylength[r] - 25;
				}
				else {
					snakeylength[r] = snakeylength[r-1];
				}
				if(snakeylength[r]<75) {
					snakeylength[r] = 625;
				}
			}
			repaint();
		}
		if (down) {
			for(int r=lengthofsnake-1;r>=0;r--) {
				snakexlength[r+1] = snakexlength[r];
			}
			for(int r=lengthofsnake-1;r>=0;r--) {
				if(r==0) {
					snakeylength[r] = snakeylength[r] + 25;
				}
				else {
					snakeylength[r] = snakeylength[r-1];
				}
				if(snakeylength[r]>625) {
					snakeylength[r] = 75;
				}
			}
			repaint();
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		if(e.getKeyCode() == KeyEvent.VK_SPACE) {
			moves = 0;
			score = 0;
			lengthofsnake = 3;
			repaint();
		}
		if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			moves++;
			right = true;
			if (!left) {
				right = true;
			} else {
				right = false;
				left = true;
			}

			up = false;
			down = false;
		}
		if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			moves++;
			left = true;
			if (!right) {
				left = true;
			} else {
				left = false;
				right = true;
			}

			up = false;
			down = false;
		}
		if (e.getKeyCode() == KeyEvent.VK_UP) {
			moves++;
			up = true;
			if (!down) {
				up = true;
			} else {
				up = false;
				down = true;
			}

			left = false;
			right = false;
		}
		if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			moves++;
			down = true;
			if (!up) {
				down = true;
			} else {
				down = false;
				up = true;
			}

			left = false;
			right = false;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

}
