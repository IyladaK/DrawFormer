import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class GridWindow extends JPanel {

    private static final int ROWS = 18;
    private static final int COLS = 32;
    private static final int C_S = 30;
    HashMap<Coord, Integer> filledCoords = new HashMap<Coord, Integer>();
    int curColor = 1;
    

    public GridWindow() {
        setPreferredSize(new Dimension(COLS * C_S, ROWS * C_S));
        setBackground(Color.WHITE);

        // Add mouse listeners to handle clicks and drags
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                // Output the mouse position when clicked
                int row = e.getX() / C_S;
                int col = e.getY() / C_S;

                if(curColor != 0){
                    filledCoords.put(new Coord(row, col), curColor);
                } else {
                    filledCoords.remove(new Coord(row, col));
                }
                paintComponent(getGraphics());
                
            }
        });

        addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                int row = e.getX() / C_S;
                int col = e.getY() / C_S;

                if(curColor != 0){
                    filledCoords.put(new Coord(row, col), curColor);
                } else {
                    filledCoords.remove(new Coord(row, col));
                }
                paintComponent(getGraphics());
            }
        });
    }

    void setCurColor(int newColor) {
        curColor = newColor;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setColor(new Color(200, 200, 200));

        for (int row = 0; row <= ROWS; row++) {
            g.drawLine(0, row * C_S, COLS * C_S, row * C_S);
        }

        for (int col = 0; col <= COLS; col++) {
            g.drawLine(col * C_S, 0, col * C_S, ROWS * C_S);
        }

        
        for (Coord coord : filledCoords.keySet()) {
            switch (filledCoords.get(coord)) {
                case 1:
                    g.setColor(Color.BLACK);
                    break;
                case 2:
                    g.setColor(Color.BLUE);
                    break;
                case 3:
                    g.setColor(Color.RED);
                    break;
                default:
                    break;
            }
            g.fillRect(coord.x * C_S, coord.y * C_S, C_S, C_S);
        }
    }



    public static void main(String[] args) {
        JFrame frame = new JFrame("GridWindow");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create the grid panel
        GridWindow gridPanel = new GridWindow();

        // Panel for the first row of buttons (Red, Blue, Black, Erase)
        JPanel buttonPanel1 = new JPanel();
        buttonPanel1.setLayout(new GridLayout(1, 4, 10, 10)); // 1 row, 4 columns, 10px gaps

        JButton black = new JButton("Black - BORDER");
        JButton blue = new JButton("Blue - CLIMB");
        JButton red = new JButton("Red - DAMAGE");
        JButton erase = new JButton("Erase");

        buttonPanel1.add(black);
        buttonPanel1.add(blue);
        buttonPanel1.add(red);
        buttonPanel1.add(erase);

        erase.addActionListener(e -> {
            gridPanel.setCurColor(0);
        });

        black.addActionListener(e -> {
            gridPanel.setCurColor(1);
        });
        blue.addActionListener(e -> {
            gridPanel.setCurColor(2);
        });
        red.addActionListener(e -> {
            gridPanel.setCurColor(3);
        });

        
        JPanel buttonPanel2 = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton button5 = new JButton("Finish");
        button5.setPreferredSize(new Dimension(210, 40));
        buttonPanel2.add(button5);
        button5.addActionListener(e -> {
            System.out.println("here");
        });

        // Create a main panel to hold both button panels
        JPanel mainButtonPanel = new JPanel();
        mainButtonPanel.setLayout(new BorderLayout());
        mainButtonPanel.add(buttonPanel1, BorderLayout.NORTH);
        mainButtonPanel.add(buttonPanel2, BorderLayout.SOUTH);

        // Set up the main layout with BorderLayout
        frame.setLayout(new BorderLayout());
        frame.add(gridPanel, BorderLayout.CENTER);
        frame.add(mainButtonPanel, BorderLayout.SOUTH);

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setVisible(true);
    }
}
