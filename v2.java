import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;

public class RouletteWheel extends JPanel {
    private int currentNumber = 0;

    public RouletteWheel() {
        setPreferredSize(new Dimension(300, 300));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int width = getWidth();
        int height = getHeight();
        int centerX = width / 2;
        int centerY = height / 2;
        int radius = Math.min(width, height) / 2 - 10;

        g2d.setColor(Color.WHITE);
        g2d.fillRect(0, 0, width, height);

        g2d.setColor(Color.BLACK);
        g2d.setStroke(new BasicStroke(2));
        g2d.drawOval(centerX - radius, centerY - radius, 2 * radius, 2 * radius);

        FontMetrics fm = g2d.getFontMetrics();
        int fontHeight = fm.getHeight();

        for (int i = 0; i < 37; i++) {
            String number = Integer.toString(i);
            double angle = Math.toRadians(360 / 37.0 * i - 90);
            int x = (int) (centerX + Math.cos(angle) * radius * 0.8);
            int y = (int) (centerY + Math.sin(angle) * radius * 0.8);
            g2d.drawString(number, x - fm.stringWidth(number) / 2, y + fontHeight / 2);
        }

        g2d.setColor(Color.RED);
        g2d.drawLine(centerX, centerY, centerX, centerY - radius);
    }

    public void spinWheel() {
        currentNumber = (int) (Math.random() * 37);
        repaint();
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Roulette Wheel");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        RouletteWheel rouletteWheel = new RouletteWheel();
        JButton spinButton = new JButton("Spin");
        spinButton.addActionListener(e -> rouletteWheel.spinWheel());
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(spinButton);
        frame.add(rouletteWheel, BorderLayout.CENTER);
        frame.add(buttonPanel, BorderLayout.SOUTH);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
