import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

/**
 * Created by Vincent on 25/10/2017.
 */
public class MarioImage extends JComponent {

    private final String MARIO_RUN_1 = "Mario_walk_1.png";
    private final String MARIO_RUN_2 = "Mario_walk_2.png";
    private final String MARIO_RUN_3 = "Mario_walk_3.png";
    private final String MARIO_JUMP = "Mario_jump.png";
    private final int WIDTH = 50;
    private final int HEIGHT = 65;
    private final int BOUNDS_Y_MAX = 262;
    private final int BOUNDS_Y_MIN = 192;

    private int walkIncrement = 0;
    private SwingWorker threadWalk;
    private boolean isJumping = false;

    public MarioImage() {
        this.setSize(200, 100);
        setBounds(0, BOUNDS_Y_MAX, WIDTH, HEIGHT);
        startWalkTread();
    }

    private void startWalkTread() {
        threadWalk = new SwingWorker() {
            @Override
            protected Object doInBackground() throws Exception {
                while(true) {
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    if(isJumping) {
                        while (getBounds().y != BOUNDS_Y_MIN) {
                            setBounds(getBounds().x, getBounds().y - 5, WIDTH, HEIGHT);
                            Thread.sleep(15);
                        }
                        while (getBounds().y != BOUNDS_Y_MAX) {
                            setBounds(getBounds().x, getBounds().y + 5, WIDTH, HEIGHT);
                            Thread.sleep(10);
                        }
                        isJumping = false;
                    }
                    else {
                        if(getParent().getSize().width < getBounds().x) {
                            setBounds(-37, getBounds().y, WIDTH, HEIGHT);
                        }
                        else {
                            setBounds(getBounds().x + 7, getBounds().y, WIDTH, HEIGHT);
                        }
                    }
                    repaint();
                }
            }
        };
        threadWalk.execute();
    }

    public void marioJump() {
        isJumping = true;
        repaint();
    }

    @Override
    public void paint(Graphics g) {
        super.paintComponents(g);

        BufferedImage image = null;
        try {
            image = ImageIO.read(new File(getImageName()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        g.drawImage(image, 0, 0, 47, 68, this);
    }

    private String getImageName() {
        if(isJumping){
            return MARIO_JUMP;
        }
        switch (walkIncrement){
            case 0:
                walkIncrement++;
                return MARIO_RUN_1;
            case 1:
                walkIncrement++;
                return MARIO_RUN_2;
            case 2:
                walkIncrement = 0;
                return MARIO_RUN_3;
            default:
                return null;
        }
    }
}
