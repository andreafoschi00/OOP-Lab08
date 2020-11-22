package it.unibo.oop.lab.mvcio2;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import it.unibo.oop.lab.mvcio.Controller;

/**
 * A very simple program using a graphical interface.
 * 
 */
public final class SimpleGUIWithFileChooser {

    /*
     * TODO: Starting from the application in mvcio:
     * 
     * 1) Add a JTextField and a button "Browse..." on the upper part of the
     * graphical interface.
     * Suggestion: use a second JPanel with a second BorderLayout, put the panel
     * in the North of the main panel, put the text field in the center of the
     * new panel and put the button in the line_end of the new panel.
     * 
     * 2) The JTextField should be non modifiable. And, should display the
     * current selected file.
     * 
     * 3) On press, the button should open a JFileChooser. The program should
     * use the method showSaveDialog() to display the file chooser, and if the
     * result is equal to JFileChooser.APPROVE_OPTION the program should set as
     * new file in the Controller the file chosen. If CANCEL_OPTION is returned,
     * then the program should do nothing. Otherwise, a message dialog should be
     * shown telling the user that an error has occurred (use
     * JOptionPane.showMessageDialog()).
     * 
     * 4) When in the controller a new File is set, also the graphical interface
     * must reflect such change. Suggestion: do not force the controller to
     * update the UI: in this example the UI knows when should be updated, so
     * try to keep things separated.
     */
    private final JFrame frame = new JFrame("Java GUI 2.0");
    private SimpleGUIWithFileChooser(final Controller ctrl) {
        //Implementation.. 1) -> 4)
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        final JPanel panel1 = new JPanel();
        panel1.setLayout(new BorderLayout());
        final JTextArea text1 = new JTextArea();
        final JButton button1 = new JButton("Save");
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent event) {
                   try {
                       ctrl.saveStringToCurrentFile(text1.getText());
                   } catch (IOException e) {
                       JOptionPane.showMessageDialog(null, e.getMessage(), "An error has occurred..", JOptionPane.ERROR_MESSAGE);
                   }
            }
        });
        panel1.add(text1, BorderLayout.CENTER);
        panel1.add(button1, BorderLayout.CENTER);
        final JTextField path = new JTextField(ctrl.getCurrentFilePath());
        path.setEditable(false);
        final JButton button2 = new JButton("Browse...");
        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                final JFileChooser file1 = new JFileChooser("Select file to save");
                file1.setSelectedFile(ctrl.getCurrentFile());
                final int res = file1.showSaveDialog(frame);
                switch (res) {
                case JFileChooser.APPROVE_OPTION:
                    final File dest = file1.getSelectedFile();
                    ctrl.setFileDestination(dest);
                    path.setText(dest.getPath());
                    break;
                case JFileChooser.CANCEL_OPTION:
                    break;
                default:
                     JOptionPane.showMessageDialog(frame, res, "Error!", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        final JPanel panel2 = new JPanel();
        panel2.setLayout(new BorderLayout());
        panel2.add(path, BorderLayout.CENTER);
        panel2.add(button2, BorderLayout.LINE_END);
        panel1.add(panel2, BorderLayout.NORTH);
        frame.setContentPane(panel1);
        final Dimension screenRes = Toolkit.getDefaultToolkit().getScreenSize();
        final int sw = (int) screenRes.getWidth();
        final int sh = (int) screenRes.getHeight();
        frame.setSize(sw / 4, sh / 4);
        frame.setLocationByPlatform(true);
    }
    private void display() {
        frame.setVisible(true);
    }
    public static void main(final String... args) {
        final SimpleGUIWithFileChooser simpleGUI = new SimpleGUIWithFileChooser(new Controller());
        simpleGUI.display();
    }
}
