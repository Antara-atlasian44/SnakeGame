import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

public class Food {
	private int x;
	private int y;
	private Color color;
	private int SCREEN_WIDTH;
	private int SCREEN_HEIGHT;
	private int UNIT_SIZE;
	private Random random;
	
	public Food(int SCREEN_WIDTH, int SCREEN_HEIGHT, int UNIT_SIZE, Color color) {
		random = new Random();
		this.x = random.nextInt((int)SCREEN_WIDTH/UNIT_SIZE)*UNIT_SIZE;
		this.y = random.nextInt((int)SCREEN_HEIGHT/UNIT_SIZE)*UNIT_SIZE;
		this.SCREEN_HEIGHT = SCREEN_HEIGHT;
		this.SCREEN_WIDTH = SCREEN_WIDTH;
		this.UNIT_SIZE = UNIT_SIZE;
		this.color = color;
	}
	
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	
	public void addNewFood() {
		x = random.nextInt((int)SCREEN_WIDTH/UNIT_SIZE)*UNIT_SIZE;
		y = random.nextInt((int)SCREEN_HEIGHT/UNIT_SIZE)*UNIT_SIZE;
	}
	
	public void drawFood(Graphics g) {
		g.setColor(this.color);
		g.fillOval(x, y, UNIT_SIZE, UNIT_SIZE);
	}

}
