package com.milkyblue;

import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import com.github.tomaslanger.chalk.Chalk;

// ExecutorLauncherGUI class. Models a GUI that launches a CustomExecutorService 
// instance based on the selected mode.
public class ExecutorLauncherGUI {

  private JFrame mainFrame;
  private JPanel mainPanel, topPanel, centerPanel, bottomPanel;
  private JLabel lblAdvice;
  private ButtonGroup buttonGroup;
  private JRadioButton radioButtons[];
  private JButton btnLaunch;

  // Class constructor.
  public ExecutorLauncherGUI() {
    // Enables color in the console.
    Chalk.setColorEnabled(true);

    mainFrame = new JFrame("Executor Service");
    mainPanel = new JPanel(new BorderLayout());
    topPanel = new JPanel();
    centerPanel = new JPanel();
    bottomPanel = new JPanel();
    lblAdvice = new JLabel("Select an executor service mode:");
    buttonGroup = new ButtonGroup();

    // Stores the labels for the radio buttons.
    String[] radioButtonLabels = new String[] { "Cached Thread Pool", "Single Thread", "Fixed Thread Pool" };

    // Sets radioButtons to an array with the same length of radioButtonLabels.
    radioButtons = new JRadioButton[radioButtonLabels.length];

    // Initializes each radio button in the array and assings an action command
    // based on the actual index.
    for (int i = 0; i < radioButtons.length; i++) {
      radioButtons[i] = new JRadioButton(radioButtonLabels[i]);
      radioButtons[i].setActionCommand(Integer.toString(i + 1));
    }

    btnLaunch = new JButton("Launch");

    // Main methods are called.
    addAttributes();
    addListeners();
    build();
    launch();
  }

  // Adds attributes to elements in the class.
  private void addAttributes() {
    centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
    centerPanel.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));

    // Selects by default the first element in the radio buttons array.
    radioButtons[0].setSelected(true);

    // Adds all the radio buttons to the ButtonGroup.
    for (JRadioButton radioBtn : radioButtons)
      buttonGroup.add(radioBtn);

    mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    mainFrame.setResizable(false);
  }

  // Adds listeners to the elements in the class.
  private void addListeners() {
    // Creates an anonymous instance of CustomExecutorService with 6 tasks and the
    // selected mode.
    btnLaunch.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        new CustomExecutorService(6, Integer.parseInt(buttonGroup.getSelection().getActionCommand()));
      }
    });
  }

  // Builds the GUI.
  private void build() {
    topPanel.add(lblAdvice);

    for (JRadioButton radioBtn : radioButtons)
      centerPanel.add(radioBtn);

    bottomPanel.add(btnLaunch);

    mainPanel.add(topPanel, BorderLayout.NORTH);
    mainPanel.add(centerPanel, BorderLayout.CENTER);
    mainPanel.add(bottomPanel, BorderLayout.SOUTH);

    mainFrame.add(mainPanel);
  }

  // Launches the frame by setting its visible value to true. Resizes and
  // relocates the window.
  private void launch() {
    mainFrame.setVisible(true);
    mainFrame.pack();
    mainFrame.setLocationRelativeTo(null);
  }

}