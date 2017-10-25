import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;

/**
 * Created by Vincent on 25/10/2017.
 */
public class MainFrame extends JFrame implements KeyListener {

    private MarioImage marioImage;

    public MainFrame() {

        this.setSize(500,420);

        JLabel jLabel = new JLabel("Hello Expertus !", SwingConstants.CENTER);
        jLabel.setForeground(Color.black);
        jLabel.setFont(new Font("Serif", Font.BOLD, 28));
        jLabel.setSize(getSize().width, 50);

        this.setContentPane(new JLabel(new ImageIcon("Mario_background.png")));
        marioImage = new MarioImage();

        this.add(marioImage);
        this.add(jLabel);

        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.addKeyListener(this);
        this.setResizable(false);
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode()== KeyEvent.VK_UP) {
            marioImage.marioJump();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
