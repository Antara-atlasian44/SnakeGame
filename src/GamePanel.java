import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;
import javax.swing.JPanel;
import javax.swing.Timer;

public class GamePanel extends JPanel implements ActionListener{

	static final int SCREEN_WIDTH = 600;
	static final int SCREEN_HEIGHT = 600;
	static final int UNIT_SIZE = 25;
	final int DELAY = 75;
	
	int applesEaten;
	int appleX;
	int appleY;
	boolean running = false;
	Timer timer;
	Random random;

	Snake snake;
	Food food;
	public GamePanel() {
		// TODO Auto-generated constructor stub
		random = new Random();
		snake = new Snake(6, UNIT_SIZE, SCREEN_HEIGHT, SCREEN_WIDTH, Color.GREEN, new Color(45, 180, 0));
		food = new Food(SCREEN_WIDTH, SCREEN_HEIGHT, UNIT_SIZE, Color.RED);
		this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
		this.setBackground(Color.BLACK);
		this.setFocusable(true);
		this.addKeyListener(new MyKeyAdapter());
		startGame();
	}
	
	public void startGame() {
		running = true;
		timer = new Timer(DELAY, this);
		timer.start();
		
		food.addNewFood();
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		draw(g);
	}
	
	public void draw(Graphics g) {
		if(running) {
			for(int i=0; i<SCREEN_HEIGHT/UNIT_SIZE; i++) {
				g.drawLine(i*UNIT_SIZE, 0, i*UNIT_SIZE, SCREEN_HEIGHT);
				g.drawLine(0, i*UNIT_SIZE, SCREEN_WIDTH, i*UNIT_SIZE);
			}

			food.drawFood(g);
			snake.drawSnake(g);
			
			g.setColor(Color.WHITE);
			g.setFont(new Font("INK Free", Font.BOLD, 75));
			FontMetrics metrics = getFontMetrics(g.getFont());
			g.drawString("Score "+applesEaten, 
					(SCREEN_WIDTH - metrics.stringWidth("Score "))/2, 
					SCREEN_HEIGHT/2);
		}
		else {
			food.drawFood(g);
			snake.drawSnake(g);
			gameOver(g);
		}
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(running) {
			if(snake.isCollision()) {
				running = false;
			}
			else {
				snake.move();
				if(snake.checkEaten(food)) {
					applesEaten ++;
				}
			}
		}
		
		repaint();
		
	}
	
	public class MyKeyAdapter extends KeyAdapter{
		@Override
		public void keyPressed(KeyEvent e) {
			snake.changeDirection(e);
		}
	}

	public void gameOver(Graphics g) {
		g.setColor(Color.WHITE);
		g.setFont(new Font("INK Free", Font.BOLD, 75));
		FontMetrics metrics = getFontMetrics(g.getFont());
		g.drawString("Game Over", 
				(SCREEN_WIDTH - metrics.stringWidth("Game Over"))/2, 
				SCREEN_HEIGHT/2);
	}

}
