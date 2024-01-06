import javax.swing.*;
import java.awt.*;
import java.awt.geom.*;


public class RotateLabel extends JLabel {
	double deg;
  public RotateLabel(String text, double deg) {
     super(text);
     this.deg = deg;
     Font font = this.getFont();
     FontMetrics metrics = new FontMetrics(font){};
     Rectangle2D bounds = metrics.getStringBounds(text, null);
     setBounds(0, 0, (int) bounds.getWidth(), (int) bounds.getHeight());
  }
  @Override
  public void paintComponent(Graphics g) {
     Graphics2D gx = (Graphics2D) g;
     gx.rotate(deg, getX() + getWidth()/2, getY() + getHeight()/2);
     super.paintComponent(g);
  }
}
