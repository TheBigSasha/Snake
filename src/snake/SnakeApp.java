package snake;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;

/**
 * The cutting edge AAA next gen game to put Cyberpunk 2077 to shame
 */
public class SnakeApp{
    private static GameInterface g;
    private static Canvas canvas;
    private static JLabel score = new JLabel("Score: " + 0);;
    private static TextField sizeX = new TextField("15");
    private static TextField sizeY = new TextField("15");
    private static TextField foodOdds = new TextField("25");


    public static void main(String[] args){
        JFrame frame = new JFrame(GameInterface.getName());

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ignored){ }

        JPanel controls = new JPanel();
        controls.setLayout(new FlowLayout());
        controls.add(new Label("Size X: "));
        controls.add(sizeX);
        controls.add(new Label("Size Y: "));
        controls.add(sizeY);
        controls.add(new Label("Food Odds: "));
        controls.add(foodOdds);
        JButton reset = new JButton("Reset");
        reset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int prevHeight = canvas.getHeight();
                int prevWidth = canvas.getWidth();
                frame.remove(canvas);
                g = new Game(Integer.parseInt(sizeX.getText()),Integer.parseInt(sizeY.getText()), Integer.parseInt(foodOdds.getText()), 50);
                canvas = new SnakeEngine(g);
                canvas.setSize(prevWidth, prevHeight);
                frame.add(canvas, BorderLayout.CENTER);
                frame.pack();
                score.setText("Score:");
            }
        });
        controls.add(reset);

        JPanel info = new JPanel(new FlowLayout());
        info.add(score);
        score.setFont(Font.getFont(Font.SERIF));


        g = new Game(15,15, 25, 50);
        canvas = new SnakeEngine(g);
        canvas.setSize(640, 640);

        frame.add(info, BorderLayout.NORTH);
        frame.add(canvas, BorderLayout.CENTER);
        frame.add(controls, BorderLayout.SOUTH);

        frame.getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("UP"),
                "dirUp");
        frame.getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_W, 0),
                "dirUp");
        frame.getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("DOWN"),
                "dirDown");
        frame.getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_S, 0),
                "dirDown");
        frame.getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("LEFT"),
                "dirLeft");
        frame.getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_A, 0),
                "dirLeft");
        frame.getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("RIGHT"),
                "dirRight");
        frame.getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_D, 0),
                "dirRight");

        frame.getRootPane().getActionMap().put("dirUp",
                new AbstractAction() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        g.setDirection(Direction.UP);
                    }
                });

        frame.getRootPane().getActionMap().put("dirDown",
                new AbstractAction() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        g.setDirection(Direction.DOWN);
                    }
                });

        frame.getRootPane().getActionMap().put("dirLeft",
                new AbstractAction() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        g.setDirection(Direction.LEFT);
                    }
                });

        frame.getRootPane().getActionMap().put("dirRight",
                new AbstractAction() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        g.setDirection(Direction.RIGHT);
                    }
                });


        frame.pack();
        frame.setVisible(true);
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                if(!g.advance()){
                    System.out.println("YOU LOST!");
                    int prevHeight = canvas.getHeight();
                    int prevWidth = canvas.getWidth();
                    frame.remove(canvas);
                    g = new Game(Integer.parseInt(sizeX.getText()),Integer.parseInt(sizeY.getText()), Integer.parseInt(foodOdds.getText()), 50);
                    canvas = new SnakeEngine(g);
                    canvas.setSize(prevWidth, prevHeight);
                    frame.add(canvas, BorderLayout.CENTER);
                    frame.pack();
                }
                score.setText("Score: " + g.getScore());
                canvas.repaint();
            }
        };

        Timer timer = new Timer();
        timer.schedule(task, 500,500);
    }


}

/**
 * Unreal Engine? Who's she?
 */
class SnakeEngine extends Canvas{
    //TODO: Implement real time ray tracing
    private final GameInterface game;

    public SnakeEngine(GameInterface g){
        this.game = g;
    }

    public void update( Graphics g ) {
        paint( g );
    }

    public void paint(Graphics g) {

        g.clearRect(0,0,getWidth(),getHeight());

        var worldColor = new WorldCell(-1,-1).getColor();

        g.setColor(worldColor);
        g.fillRect(0,0,getWidth(),getHeight());

        int pxPerHor = getWidth() / game.getWorldDimensionX();
        for(int i = 0; i < game.getWorldDimensionX(); i++){
            int lenAtIdx = game.getWorldDimensionY();
            int pxPerVer = getHeight() / lenAtIdx;
            for(int j = 0; j < lenAtIdx; j++){

                var cell = game.getCell(i,j);

                Color color = game.getCell(i,j) != null ? game.getCell(i,j).getColor() : worldColor;

                g.setColor(color);
                if(cell instanceof FoodCell){
                    g.fillOval(i * pxPerVer, j * pxPerHor, pxPerVer, pxPerHor);
                }else {
                    g.fillRect(i * pxPerVer, j * pxPerHor, pxPerVer, pxPerHor);
                }
            }
        }
    }
}

/**
 * An enum to represent possible directions
 */
enum Direction {
    UP, DOWN, LEFT, RIGHT
}
