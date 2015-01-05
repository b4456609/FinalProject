package capture;
import javax.swing.JFrame;


public class SmallToolTest 
{
	public static void main(String[] args)
	{
		SmallTool smallTool = new SmallTool();
		smallTool.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		smallTool.setLayout(null);
		smallTool.setBounds(100, 100, 350 , 125);
		smallTool.setResizable(false);
		smallTool.setVisible( true);
	}
}
