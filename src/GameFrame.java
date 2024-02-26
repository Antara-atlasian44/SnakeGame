import javax.swing.JFrame;

public class GameFrame extends JFrame{
	public GameFrame() {
		// TODO Auto-generated constructor stub
		GamePanel panel = new GamePanel();
		this.add(panel);
		this.setTitle("Snake Game");
		this.setDefaultCloseOperation(3);
		this.setResizable(false);
		this.pack();
		this.setVisible(true);
		this.setLocationRelativeTo(null);
	}
}
