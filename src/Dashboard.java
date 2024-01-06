import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.IOException;
import java.net.*;

public class Dashboard extends JFrame implements MouseMotionListener, MouseListener, ActionListener {
    boolean navStat = true;
    Desktop desk;
    JLabel titleLabel;
    JPanel titleBar;
    JPanel sideMenuBar;
    JButton closeBtn;
    JButton minimizeBtn;
    JButton navBtn;
    int xMouse;
    int yMouse;
    int vel = 0;
    
    URI githubURL;
    URI fbURL;
    URI twitterURL;

    long timerStart;
    Timer tm;

    JRadioButton summaryBtn;
    JRadioButton transactionBtn;
    JRadioButton statisticsBtn;
    JRadioButton productBtn;
    JRadioButton categoryBtn;

    Color darkSecondaryCol = new Color(0x2e2e2e);
    Color darkPrimaryCol = new Color(0x222222);
    Color blueColor = new Color(0x5422ff);
    Color brightBlue = new Color(0x897fcc);
    Color desaturatedBlue = new Color(0x25006a);

    ImageIcon radioCloseIcon = scaleImg(
        new ImageIcon("icons/dry-clean.png"),
        20,
        20
    );
    ImageIcon radioOpenIcon = scaleImg(
        new ImageIcon("icons/bullet (1).png"),
        20,
        20
    );
    ImageIcon userIcon = scaleImg(
        new ImageIcon("icons/user.png"),
        20,
        20
    );
    ImageIcon fbIcon = scaleImg(
        new ImageIcon("icons/fb_icon.png"), 30, 30);
    ImageIcon twitterIcon = scaleImg(
        new ImageIcon("icons/twitter.png"), 30, 30);
    ImageIcon gitHubIcon = scaleImg(
        new ImageIcon("icons/github.png"), 30, 30);

    Dashboard() {
    	desk = Desktop.getDesktop();
    		
        try {
			githubURL = new URI("https://github.com/SarryGeezOwO");
			fbURL = new URI("https://web.facebook.com/cjay.gidayawan");
			twitterURL = new URI("https://twitter.com/CrotchHom");
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
    	
        this.setTitle("Dashboard");
        this.setSize(1000, 600);
        this.setResizable(false);
        this.setUndecorated(true);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.getContentPane().setBackground(darkPrimaryCol);
        tm = new Timer(3, this);

        ImageIcon closeIcon = scaleImg(
            new ImageIcon("icons/close.png"),
            12,
            12
        );
        ImageIcon minimizeIcon = scaleImg(
            new ImageIcon("icons/minimize-sign.png"),
            12,
            12
        );
        ImageIcon titleIcon = scaleImg(
            new ImageIcon("icons/landing-page.png"),
            18,
            18
        );
        
        createHeader(titleIcon, closeIcon, minimizeIcon);
        sideMenuBar = new JPanel();
        sideMenuBar.setPreferredSize(new Dimension(220, 100));
        sideMenuBar.setLayout(new BorderLayout());
        sideMenuBar.setBackground(darkPrimaryCol); //darkPrimaryCol
        createSideBar(sideMenuBar);


        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BorderLayout());
        contentPanel.setPreferredSize(new Dimension(500, 500));
        contentPanel.setBackground(darkSecondaryCol);

        navBtn = new JButton("<");
        navBtn.setPreferredSize(new Dimension(25, 50));
        navBtn.setBorder(null);
        navBtn.setFocusable(false);
        navBtn.setBackground(darkPrimaryCol);
        navBtn.setForeground(brightBlue);
        navBtn.setFont(new Font("Consolas", Font.BOLD, 18));
        navBtn.addMouseListener(this);
        navBtn.addActionListener(e -> {
            tm.start();
            timerStart = System.currentTimeMillis();
            String c = (navStat) ? ">" : "<";
            vel = (navStat) ? -9 : 9;
            navBtn.setText(c);
            navStat = !navStat;
        });
        
        contentPanel.add(navBtn, BorderLayout.WEST);

        titleBar.add(titleLabel);
        titleBar.add(minimizeBtn);
        titleBar.add(closeBtn);
        this.add(titleBar, BorderLayout.NORTH);
        this.add(sideMenuBar, BorderLayout.WEST);
        this.add(contentPanel, BorderLayout.CENTER);
        this.setVisible(true);
    }

    public void socialBtnSetup(JButton btn) {
        btn.setOpaque(false);
        btn.setFocusable(false);
        btn.setBorder(null);
        btn.setBackground(blueColor);
        btn.setBorderPainted(false);
    }

    public void radioButtonSetup(JRadioButton btn) {
        btn.setPreferredSize(new Dimension(190, 40));
        btn.setFocusable(false);
        btn.setOpaque(false);
        btn.setBackground(desaturatedBlue);
        btn.setForeground(brightBlue);
        btn.setFont(new Font("Kanit", Font.PLAIN, 20));
        btn.setIcon(radioCloseIcon);
        btn.setSelectedIcon(radioOpenIcon);
        btn.addMouseListener(this);
        btn.setVerticalTextPosition(SwingConstants.CENTER);
        btn.setHorizontalTextPosition(SwingConstants.RIGHT);
        btn.setIconTextGap(5);
        btn.addActionListener(e -> {
            btn.setIconTextGap(15);
            radioBtnHover(btn, Color.white);
        });
    }

    public void createSideBar(JPanel sideMenuBar) {

        JPanel sideSubPanel = new JPanel();
        sideSubPanel.setLayout(new FlowLayout(FlowLayout.LEADING, 15, 0));
        sideSubPanel.setPreferredSize(new Dimension(50, 50));
        sideSubPanel.setBorder(new EmptyBorder(20, 0, 0, 0));
        sideSubPanel.setBackground(darkPrimaryCol);
        ButtonGroup sideGroup = new ButtonGroup();
        summaryBtn = new JRadioButton("Summary");
        transactionBtn = new JRadioButton("Transaction");
        statisticsBtn = new JRadioButton("Statistics");
        productBtn = new JRadioButton("Product");
        categoryBtn = new JRadioButton("Category");
        sideGroup.add(summaryBtn);
        sideGroup.add(transactionBtn);
        sideGroup.add(statisticsBtn);
        sideGroup.add(productBtn);
        sideGroup.add(categoryBtn);
        radioButtonSetup(summaryBtn);
        radioButtonSetup(transactionBtn);
        radioButtonSetup(statisticsBtn);
        radioButtonSetup(productBtn);
        radioButtonSetup(categoryBtn);
    
        summaryBtn.setSelected(true);
        summaryBtn.setIconTextGap(15);
        radioBtnHover(summaryBtn, Color.white);
        sideSubPanel.add(summaryBtn);
        sideSubPanel.add(transactionBtn);
        sideSubPanel.add(statisticsBtn);
        sideSubPanel.add(productBtn);
        sideSubPanel.add(categoryBtn);

        JPanel topGap = new JPanel();
        JLabel topLabel = new JLabel("Username");
        topGap.setLayout(new BorderLayout(10, 10));
        topGap.setPreferredSize(new Dimension(10, 50));
        topGap.setBorder(new EmptyBorder(7, 15, 7, 10));
        topGap.setBackground(blueColor);
        topGap.setOpaque(true);
        topLabel.setIcon(userIcon);
        topLabel.setFont(new Font("Kanit", Font.PLAIN, 20));
        topLabel.setIconTextGap(10);
        topLabel.setForeground(Color.white);
        topGap.add(topLabel, BorderLayout.WEST);

        JPanel socialPanel = new JPanel();
        socialPanel.setLayout(new FlowLayout(FlowLayout.LEADING, 20, 15));
        socialPanel.setBackground(blueColor);
        socialPanel.setPreferredSize(new Dimension(10, 60));
        JButton facebook = new JButton();
        JButton twitter = new JButton();
        JButton github = new JButton();
        facebook.setIcon(fbIcon);
        twitter.setIcon(twitterIcon);
        github.setIcon(gitHubIcon);
        facebook.addActionListener(e -> {
        	try {
        		desk.browse(fbURL);
        	}catch(Exception e1) {
        		e1.printStackTrace();
        	}
        });
        twitter.addActionListener(e -> {
        	try {
        		desk.browse(twitterURL);
        	}catch(Exception e1) {
        		e1.printStackTrace();
        	}
        });
        github.addActionListener(e -> {
        	try {
        		desk.browse(githubURL);
        	}catch(Exception e1) {
        		e1.printStackTrace();
        	}
        });
        socialPanel.add(facebook);
        socialPanel.add(twitter);
        socialPanel.add(github);
        socialBtnSetup(facebook);
        socialBtnSetup(twitter);
        socialBtnSetup(github);
        sideMenuBar.add(topGap, BorderLayout.NORTH);
        sideMenuBar.add(sideSubPanel, BorderLayout.CENTER);
        sideMenuBar.add(socialPanel, BorderLayout.SOUTH);
    }

    public void createHeader(ImageIcon titleIcon, ImageIcon closeIcon, ImageIcon minimizeIcon) {
        titleBar = new JPanel();
        titleBar.setLayout(new FlowLayout(FlowLayout.TRAILING, 0, 0));
        titleBar.setPreferredSize(new Dimension(100, 25));
        titleBar.setBackground(darkPrimaryCol);
        titleBar.addMouseMotionListener(this);
        titleBar.addMouseListener(this);

        titleLabel = new JLabel(getTitle());
        titleLabel.setPreferredSize(new Dimension(945, 25));
        titleLabel.setFont(new Font("Kanit", Font.PLAIN, 18));
        titleLabel.setForeground(blueColor);
        titleLabel.setIcon(titleIcon);
        titleLabel.setIconTextGap(5);

        closeBtn = new JButton();
        closeBtn.setFocusable(false);
        closeBtn.addMouseListener(this);
        closeBtn.setPreferredSize(new Dimension(25, 25));
        closeBtn.setIcon(closeIcon);
        closeBtn.setBorder(null);
        closeBtn.setBackground(darkPrimaryCol);
        closeBtn.addActionListener(e -> {
            System.exit(0);
        });

        minimizeBtn = new JButton();
        minimizeBtn.setFocusable(false);
        minimizeBtn.addMouseListener(this);
        minimizeBtn.setPreferredSize(new Dimension(25, 25));
        minimizeBtn.setIcon(minimizeIcon);
        minimizeBtn.setBorder(null);
        minimizeBtn.setBackground(darkPrimaryCol);
        minimizeBtn.addActionListener(e -> {
            this.setState(JFrame.ICONIFIED);
        });
    }


    public ImageIcon scaleImg(ImageIcon n, int x, int y) {
        Image img = n.getImage();
        Image newImg = img.getScaledInstance(x, y, Image.SCALE_SMOOTH);
        ImageIcon c = new ImageIcon(newImg);
        return c;
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

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if(e.getSource()==titleBar) {
            xMouse = e.getX();
            yMouse = e.getY();
        }

        if(e.getSource()==summaryBtn || e.getSource()==transactionBtn ||
            e.getSource()==statisticsBtn || e.getSource()==productBtn ||
            e.getSource()==categoryBtn) {
            summaryBtn.setIconTextGap(5);
            transactionBtn.setIconTextGap(5);
            statisticsBtn.setIconTextGap(5);
            productBtn.setIconTextGap(5);
            categoryBtn.setIconTextGap(5);
            radioBtnHover(summaryBtn, brightBlue);
            radioBtnHover(transactionBtn, brightBlue);
            radioBtnHover(statisticsBtn, brightBlue);
            radioBtnHover(productBtn, brightBlue);
            radioBtnHover(categoryBtn, brightBlue);
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        if(e.getSource()==closeBtn) {
            closeBtn.setBackground(new Color(0xee3333)); 
        }
        if(e.getSource()==minimizeBtn) {
            minimizeBtn.setBackground(new Color(0x666666));
        }
        if(e.getSource()==summaryBtn || e.getSource()==transactionBtn ||
            e.getSource()==statisticsBtn || e.getSource()==productBtn ||
            e.getSource()==categoryBtn) {
            
            ((JRadioButton)e.getSource()).setOpaque(true);
        }

        if(e.getSource()==navBtn) {
            navBtn.setForeground(Color.white);
            navBtn.setBackground(new Color(0x252525));
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        if(e.getSource()==closeBtn) {
            closeBtn.setBackground(darkPrimaryCol);
        }
        if(e.getSource()==minimizeBtn) {
            minimizeBtn.setBackground(darkPrimaryCol);
        }
        if(e.getSource()==summaryBtn || e.getSource()==transactionBtn ||
            e.getSource()==statisticsBtn || e.getSource()==productBtn ||
            e.getSource()==categoryBtn) {
            
            ((JRadioButton)e.getSource()).setOpaque(false);
        }

        if(e.getSource()==navBtn) {
            navBtn.setForeground(brightBlue);
            navBtn.setBackground(darkPrimaryCol);
        }
    }

    public void radioBtnHover(JRadioButton btn, Color c) {
        btn.setForeground(c);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if((System.currentTimeMillis() - timerStart) / 100 == 4) {
            tm.restart();
            tm.stop();
            timerStart = 0;
            if(navStat == false) {
                sideMenuBar.setVisible(false);
                sideMenuBar.setPreferredSize(new Dimension(0, 0));
            }else {
                sideMenuBar.setVisible(true);
                sideMenuBar.setPreferredSize(new Dimension(220, 0));
            }
            sideMenuBar.setSize(sideMenuBar.getWidth()+vel, sideMenuBar.getHeight());
            repaint();
            revalidate();
        }
        if(navStat == true)  {
        	sideMenuBar.setVisible(true);
        }

        sideMenuBar.setSize(sideMenuBar.getWidth()+vel, sideMenuBar.getHeight());
        repaint();
    }
}