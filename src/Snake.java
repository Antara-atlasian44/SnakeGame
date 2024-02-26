import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

public class Snake {
	private int length;
	private int x[];
	private int y[];
	private char direction;
	private Color headColor;
	private Color bodyColor;
	private int UNIT_SIZE;
	private int SCREEN_HEIGHT;
	private int SCREEN_WIDTH;
	
	public Snake(int length, int UNIT_SIZE,int SCREEN_HEIGHT,int SCREEN_WIDTH, Color headColor, Color bodyColor) {
		int GAME_UNITS = (SCREEN_WIDTH * SCREEN_HEIGHT)/UNIT_SIZE;
		x = new int[GAME_UNITS];
		y = new int[GAME_UNITS];
		x[0] = SCREEN_WIDTH/2 - (length/2)*UNIT_SIZE;
		y[0] = SCREEN_HEIGHT/2;
		for(int i = 1; i<length; i++) {
			x[i] = x[i-1]-UNIT_SIZE;
			y[i] = y[i-1];
		}
		this.length = length;
		this.direction = 'R';
		this.headColor = headColor;
		this.bodyColor = bodyColor;
		this.UNIT_SIZE = UNIT_SIZE;
		this.SCREEN_HEIGHT = SCREEN_HEIGHT;
		this.SCREEN_WIDTH = SCREEN_WIDTH;
	}
	
	public void changeDirection(KeyEvent e) {
		switch(e.getKeyCode()) {
		case KeyEvent.VK_LEFT:
			if(direction != 'R')
				direction = 'L';
			break;
		case KeyEvent.VK_RIGHT:
			if(direction != 'L')
				direction = 'R';
			break;
		case KeyEvent.VK_UP:
			if(direction != 'D')
				direction = 'U';
			break;
		case KeyEvent.VK_DOWN:
			if(direction != 'U')
				direction = 'D';
			break;
	
	}
	}
	
	public void move() {
		
		for(int i= length; i>0; i--) {
			x[i] = x[i-1];
			y[i] = y[i-1];
		}
		
		switch(direction){
			case 'U':
				y[0] = y[0] - UNIT_SIZE;
				break;
			case 'D':
				y[0] = y[0] + UNIT_SIZE;
				break;
			case 'R':
				x[0] = x[0] + UNIT_SIZE;
				break;
			case 'L':
				x[0] = x[0] - UNIT_SIZE;
				break;	
			
		}
	}
	
	public void drawSnake(Graphics g) {	
		for(int i = 0; i<length; i++) {
			if(i == 0) {
				g.setColor(headColor);
				g.fillRect(x[i], y[i], UNIT_SIZE, UNIT_SIZE);
			}
			else {
				g.setColor(bodyColor);
				g.fillRect(x[i], y[i], UNIT_SIZE, UNIT_SIZE);
			}
		}
	}
	public boolean isCollision() {
		// if head collides
		for(int i=length; i>0; i--) {
			if((x[0] == x[i] && y[0] == y[i])) {
				return true;
			}
		}
		// if head touches border
		if(x[0] < 0) {
			return true;
		}
		if(x[0] > SCREEN_WIDTH-UNIT_SIZE) {
			return true;
		}
		
		if(y[0] < 0) {
			return true;
		}
		if(y[0] > SCREEN_HEIGHT-UNIT_SIZE) {
			return true;
		}
		return false;
	}
	
	public boolean checkEaten(Food food) {
		if(x[0] == food.getX() && y[0] == food.getY()) {
			length ++;
			food.addNewFood();
			return true;
		}
		return false;
	}
}
