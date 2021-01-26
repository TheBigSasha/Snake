package snake;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;

public class SnakeApp{
    private static Game g;
    private static Canvas canvas;
    private static JLabel score = new JLabel("Score: " + 0);;
    private static TextField sizeX = new TextField("15");
    private static TextField sizeY = new TextField("15");
    private static TextField foodOdds = new TextField("25");


    public static void main(String[] args){
        JFrame frame = new JFrame(Game.getName());

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
                g = new Game(Integer.parseInt(sizeX.getText()),Integer.parseInt(sizeY.getText()), Integer.parseInt(foodOdds.getText()));
                canvas = new SnakeEngine(g.board);
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


        g = new Game(15,15, 25);
        canvas = new SnakeEngine(g.board);
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
                        g.snake.direction = Direction.UP;
                        System.out.println(g.snake.direction.name());
                    }
                });

        frame.getRootPane().getActionMap().put("dirDown",
                new AbstractAction() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        g.snake.direction = Direction.DOWN;
                        System.out.println(g.snake.direction.name());
                    }
                });

        frame.getRootPane().getActionMap().put("dirLeft",
                new AbstractAction() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        g.snake.direction = Direction.LEFT;
                        System.out.println(g.snake.direction.name());
                    }
                });

        frame.getRootPane().getActionMap().put("dirRight",
                new AbstractAction() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        g.snake.direction = Direction.RIGHT;
                        System.out.println(g.snake.direction.name());
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
                    g = new Game(Integer.parseInt(sizeX.getText()),Integer.parseInt(sizeY.getText()), Integer.parseInt(foodOdds.getText()));
                    canvas = new SnakeEngine(g.board);
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

class SnakeEngine extends Canvas{
    private static final HashMap<Type, Color> colors = new HashMap<>();
    static{
        colors.put(Type.EMPTY, Color.BLACK);
        colors.put(Type.FOOD, Color.RED);
        colors.put(Type.SNAKE, Color.GREEN);
    }

    private final Cell[][] board;

    public SnakeEngine(Cell[][] board){
        this.board = board;
    }

    public void update( Graphics g ) {
        paint( g );
    }

    public void paint(Graphics g) {

        g.clearRect(0,0,getWidth(),getHeight());
//        int pixelsPerItemVer = getHeight() / board.length;
//        for (int i = 0; i < board.length; i++) {                              //We cannot use a foreach loop here because we must use the numbers i and j for scaling
//            int lengthAtThisIndex = board[i].length;                          //Calculate the length of the array at this index to size the color accordingly
//            int pixelsPerItemHorAtIndex = getWidth() / lengthAtThisIndex;       //Calculating how many pixels each color will take up
//            for (int j = 0; j < board[i].length; j++) {                       //Nested for loop to iterate through the array at this height
//                g.setColor(colors.get(board[i][j].getType()));                                      //Set the color to the one a this index of the color array
//                g.fillRect(j * pixelsPerItemHorAtIndex, i * pixelsPerItemVer, getWidth() / lengthAtThisIndex,
//                        getHeight() / board.length);                    //Draw the rectangle. We multiply the index by the pixels per item to get the position.
//            }
//      }
        g.setColor(colors.get(Type.EMPTY));
        g.fillRect(0,0,getWidth(),getHeight());

        int pxPerHor = getWidth() / board.length;
        for(int i = 0; i < board.length; i++){
            int lenAtIdx = board[i].length;
            int pxPerVer = getHeight() / lenAtIdx;
            for(int j = 0; j < board[i].length; j++){
                g.setColor(colors.get(board[i][j].getType()));
                if(board[i][j].getType().equals(Type.FOOD)){
                    g.fillOval(i * pxPerVer, j * pxPerHor, pxPerVer, pxPerHor);
                }else {
                    g.fillRect(i * pxPerVer, j * pxPerHor, pxPerVer, pxPerHor);
                }
            }
        }
    }
}
