import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;


public class StartupPane extends JFrame {
	
	ImageIcon logo = Main.newImage(
			new ImageIcon("sync.png"),
			120,
			120
		);
	
	
	StartupPane() throws InterruptedException {
		this.setUndecorated(true);
		this.setResizable(false);
		this.setSize(380, 450);
		this.setLocationRelativeTo(null);
		this.getContentPane().setBackground(new Color(0x232323));
		
		JPanel centerPanel = new JPanel();
		centerPanel.setLayout(new BorderLayout());
		centerPanel.setBackground(new Color(0x232323));
		JLabel yes = new JLabel("Loading...");
		yes.setForeground(Color.white);
		yes.setHorizontalAlignment(JLabel.CENTER);
		yes.setFont(new Font("Kanit", Font.PLAIN, 30));
		
		
		RotateLabel lab = new RotateLabel("", 0);
		lab.setIcon(logo);
		lab.setPreferredSize(new Dimension(200, 200));
		lab.setForeground(Color.white);
		lab.setFont(new Font("Kanit", Font.BOLD, 30));
		lab.setHorizontalAlignment(JLabel.CENTER);
		
		
		JProgressBar prog = new JProgressBar(0, 100);
		prog.setPreferredSize(new Dimension(10, 20));
		prog.setBackground(new Color(0x666666));
		prog.setForeground(new Color(0x151515));
		prog.setFocusable(false);
		prog.setBorder(new EmptyBorder(0,0,0,0));
		prog.setStringPainted(true);
		
		centerPanel.add(yes, BorderLayout.SOUTH);
		centerPanel.add(lab, BorderLayout.CENTER);
		this.add(centerPanel, BorderLayout.CENTER);
		this.add(prog, BorderLayout.SOUTH);
		this.setVisible(true);
		//new Main();
		
		int x = 0;
		while(x <= prog.getMaximum()) {
			Thread.sleep(10);
			lab.deg = x/10;
			prog.setValue(x);
			x++;
			repaint();
		}
		this.dispose();
		Thread.sleep(30);
		new Dashboard();
	}
}
