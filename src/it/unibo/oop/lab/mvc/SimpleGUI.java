package it.unibo.oop.lab.mvc;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 * A very simple program using a graphical interface.
 * 
 */
public final class SimpleGUI {

    private final JFrame frame = new JFrame();
    private final ControllerImpl ctrl;
    /*
     * Once the Controller is done, implement this class in such a way that:
     * 
     * 1) I has a main method that starts the graphical application
     * 
     * 2) In its constructor, sets up the whole view
     * 
     * 3) The graphical interface consists of a JTextField in the upper part of the frame, 
     * a JTextArea in the center and two buttons below it: "Print", and "Show history". 
     * SUGGESTION: Use a JPanel with BorderLayout
     * 
     * 4) By default, if the graphical interface is closed the program must exit
     * (call setDefaultCloseOperation)
     * 
     * 5) The behavior of the program is that, if "Print" is pressed, the
     * controller is asked to show the string contained in the text field on standard output. 
     * If "show history" is pressed instead, the GUI must show all the prints that
     * have been done to this moment in the text area.
     * 
     */

    /**
     * builds a new {@link SimpleGUI}.
     * @param ctrl the controller for SimpleGUI
     */
    public SimpleGUI(final ControllerImpl ctrl) {

        this.ctrl = ctrl;
        final JPanel panel1 = new JPanel();
        panel1.setLayout(new BorderLayout());
        final JTextField field1 = new JTextField();
        field1.setBackground(Color.GREEN);
        panel1.add(field1, BorderLayout.NORTH);
        final JTextArea area1 = new JTextArea();
        area1.setEditable(false);
        area1.setBackground(Color.YELLOW);
        panel1.add(area1, BorderLayout.CENTER);
        final JPanel panel2 = new JPanel();
        panel2.setLayout(new BoxLayout(panel2, BoxLayout.LINE_AXIS));
        panel1.add(panel2, BorderLayout.SOUTH);
        final JButton button1 = new JButton("Print");
        final JButton button2 = new JButton("Show History");
        panel2.add(button1);
        panel2.add(button2);
        frame.setContentPane(panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                SimpleGUI.this.ctrl.setNextStringToPrint(field1.getText());
                SimpleGUI.this.ctrl.printCurrentString();
            }
        });
        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                final StringBuilder text1 = new StringBuilder();
                final List<String> list = SimpleGUI.this.ctrl.getHistory();
                for (final String s: list) {
                    text1.append(s);
                    text1.append('\n');
                }
                if (!list.isEmpty()) {
                    text1.deleteCharAt(text1.length() - 1);
                }
                area1.setText(text1.toString());
            }
        });
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
    /**
     * 
     * @param args ignored
     */
    public static void main(final String... args) {
        final SimpleGUI gui = new SimpleGUI(new ControllerImpl());
        gui.display();
    }
}
