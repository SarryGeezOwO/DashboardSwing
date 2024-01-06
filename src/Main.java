import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class Main extends JFrame implements MouseListener, MouseMotionListener {

	int frameXSize = 500;
	int frameYSize = 500;
	
	int xMouse;
	int yMouse;
	JPanel titleBar;
	JButton minimizeBtn;
	JButton maximizeBtn;
	JButton closeBtn;
	
	ImageIcon titleIcon = newImage(
			new ImageIcon("landing-page.png"),25,25
	);
	
	boolean maxState;
	
	Main() {
		this.setTitle("Dashboard");
		this.setSize(frameXSize, frameYSize);
		this.setLayout(new BorderLayout());
		this.setLocationRelativeTo(null);
		this.setUndecorated(true);
		
			
		titleBar = new JPanel();
		titleBar.setLayout(new FlowLayout(FlowLayout.TRAILING, 0, 0));
		titleBar.setPreferredSize(new Dimension(0, 25));
		titleBar.setBackground(new Color(0x222222));
		titleBar.addMouseListener(this);
		titleBar.addMouseMotionListener(this);
		
		JLabel title = new JLabel(this.getTitle());		
		title.setPreferredSize(new Dimension(this.getWidth()-90, 25));
		title.setVerticalTextPosition(JLabel.BOTTOM);
		title.setFont(new Font("Poppins", Font.PLAIN, 16));
		title.setForeground(Color.white);
		title.setIcon(titleIcon);
		title.setIconTextGap(5);
		
		minimizeBtn = new JButton("-");
		minimizeBtn.setFont(new Font("Consolas", Font.BOLD, 18));
		minimizeBtn.setPreferredSize(new Dimension(30, 25));
		minimizeBtn.setBorder(new EmptyBorder(5, 0, 0, 0));
		minimizeBtn.setBackground(new Color(0x222222));
		minimizeBtn.setForeground(Color.white);
		minimizeBtn.setFocusable(false);
		minimizeBtn.addMouseListener(this);
		minimizeBtn.addActionListener(e -> {
			this.setState(JFrame.ICONIFIED);
			title.setPreferredSize(new Dimension(this.getWidth()-90, 25));
			repaint();
		});
		
		maximizeBtn = new JButton("O");
		maximizeBtn.setFont(new Font("Consolas", Font.BOLD, 18));
		maximizeBtn.setPreferredSize(new Dimension(30, 25));
		maximizeBtn.setBorder(new EmptyBorder(5, 0, 0, 0));
		maximizeBtn.setBackground(new Color(0x222222));
		maximizeBtn.setForeground(Color.white);
		maximizeBtn.setFocusable(false);
		maximizeBtn.addMouseListener(this);
		maximizeBtn.addActionListener(e -> {
			if(this.getExtendedState()==JFrame.MAXIMIZED_BOTH)
				this.setExtendedState(JFrame.NORMAL);
			else 
				this.setExtendedState(JFrame.MAXIMIZED_BOTH);
			maxState = !maxState;
			maximizeBtn.setText((maxState) ? "=" : "O");
			title.setPreferredSize(new Dimension(this.getWidth()-90, 25));
			repaint();
		});
		
		closeBtn =  new JButton("X");
		closeBtn.setFont(new Font("Consolas", Font.BOLD, 18));
		closeBtn.setPreferredSize(new Dimension(30, 25));
		closeBtn.setBorder(new EmptyBorder(5, 0, 0, 0));
		closeBtn.setBackground(new Color(0x222222));
		closeBtn.setForeground(Color.white);
		closeBtn.setFocusable(false);
		closeBtn.addMouseListener(this);
		closeBtn.addActionListener(e -> {
			System.exit(0);
		});
		
		titleBar.add(title);
		titleBar.add(minimizeBtn);
		titleBar.add(maximizeBtn);
		titleBar.add(closeBtn);
		this.add(titleBar, BorderLayout.NORTH);
		this.setVisible(true);
	}
	
	static ImageIcon newImage(ImageIcon c, int x, int y) {
		Image img = c.getImage();
		Image newImg = img.getScaledInstance(x, y, Image.SCALE_SMOOTH);
		return c = new ImageIcon(newImg);
	}
	
	public static void main(String[] args) {
		try {
			new StartupPane();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	
	/*
	 *  ================= Interface Methods ==========================
	 */
	
	
	@Override
	public void mouseClicked(MouseEvent e) {
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		if(e.getSource()==titleBar) {
			xMouse = e.getX();
			yMouse = e.getY();
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		if(e.getSource()==closeBtn) {
			closeBtn.setBackground(new Color(0xaa2222));
		}
		else if (e.getSource()==minimizeBtn) {
			minimizeBtn.setBackground(new Color(0x333333));
		}else if (e.getSource()==maximizeBtn) {
			maximizeBtn.setBackground(new Color(0x333333));
		}
	}

	@Override
	public void mouseExited(MouseEvent e) {
		if(e.getSource()==closeBtn || e.getSource()==minimizeBtn ||
				e.getSource()==maximizeBtn) {
			closeBtn.setBackground(new Color(0x222222));
			minimizeBtn.setBackground(new Color(0x222222));
			maximizeBtn.setBackground(new Color(0x222222));
		}
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		if(e.getSource()==titleBar) {
			int x = e.getXOnScreen();
			int y = e.getYOnScreen();
			this.setLocation(x-xMouse, y-yMouse);
		}
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		
	}

}
