import javax.swing.*;

public class Main {

	public static void main(String[] args) {
	
	JFrame f= new JFrame("Java F1");
	f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	f.setSize(980, 510);
	f.add(new Map());
	f.setVisible(true);
	
	
	}

}
