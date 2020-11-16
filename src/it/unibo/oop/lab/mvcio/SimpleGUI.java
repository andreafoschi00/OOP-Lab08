package it.unibo.oop.lab.mvcio;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.LayoutManager;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;

/**
 * A very simple program using a graphical interface.
 * 
 */
public final class SimpleGUI {

    private final JFrame frame = new JFrame("Java GUI Application");

    /*
     * Once the Controller is done, implement this class in such a way that:
     * 
     * 1) It has a main method that starts the graphical application
     * 
     * 2) In its constructor, sets up the whole view
     * 
     * 3) The graphical interface consists of a JTextArea with a button "Save" right
     * below (see "ex02.png" for the expected result). SUGGESTION: Use a JPanel with
     * BorderLayout
     * 
     * 4) By default, if the graphical interface is closed the program must exit
     * (call setDefaultCloseOperation)
     * 
     * 5) The program asks the controller to save the file if the button "Save" gets
     * pressed.
     * 
     * Use "ex02.png" (in the res directory) to verify the expected aspect.
     */

    /**
     * builds a new {@link SimpleGUI}.
     */
    private SimpleGUI(final Controller controller) {
        //1) - 2)
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        final JTextArea textArea = new JTextArea();
        final JPanel panel1 = new JPanel();
        final LayoutManager layout1 = new BorderLayout();
        panel1.setLayout(layout1);
        //3)
        final JButton save = new JButton("Save");
        save.addActionListener(new ActionListener() {
            //5)
            @Override
            public void actionPerformed(final ActionEvent event) {
                try {
                    controller.saveStringToCurrentFile(textArea.getText());
                } catch (IOException e) {
                    JOptionPane.showMessageDialog(null, e.getMessage(), "Something went wrong", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        panel1.add(textArea, BorderLayout.CENTER);
        panel1.add(save, BorderLayout.SOUTH);
        frame.setContentPane(panel1);
        /*
         * Make the frame half the resolution of the screen. This very method is
         * enough for a single screen setup. In case of multiple monitors, the
         * primary is selected.
         * 
         * In order to deal coherently with multimonitor setups, other
         * facilities exist (see the Java documentation about this issue). It is
         * MUCH better than manually specify the size of a window in pixel: it
         * takes into account the current resolution.
         */
        final Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        final int sw = (int) screen.getWidth();
        final int sh = (int) screen.getHeight();
        frame.setSize(sw / 2, sh / 2);
        /*
         * Instead of appearing at (0,0), upper left corner of the screen, this
         * flag makes the OS window manager take care of the default positioning
         * on screen. Results may vary, but it is generally the best choice.
         */
        frame.setLocationByPlatform(true);
    }
    private void display() {
        frame.setVisible(true);
    }
    public static void main(final String... args) {
        final SimpleGUI gui = new SimpleGUI(new Controller());
        gui.display();
    }
}
