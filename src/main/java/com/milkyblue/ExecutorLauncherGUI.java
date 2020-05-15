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

public class ExecutorLauncherGUI {

  private JFrame mainFrame;
  private JPanel mainPanel, topPanel, centerPanel, bottomPanel;
  private JLabel lblAdvice;
  private ButtonGroup buttonGroup;
  private JRadioButton radioButtons[];
  private JButton btnLaunch;

  public ExecutorLauncherGUI() {
    Chalk.setColorEnabled(true);

    mainFrame = new JFrame("Executor Service");
    mainPanel = new JPanel(new BorderLayout());
    topPanel = new JPanel();
    centerPanel = new JPanel();
    bottomPanel = new JPanel();
    lblAdvice = new JLabel("Select an executor service mode:");
    buttonGroup = new ButtonGroup();

    String[] radioButtonLabels = new String[] { "Cached Thread Pool", "Single Thread", "Fixed Thread Pool (Set to 2)" };

    radioButtons = new JRadioButton[radioButtonLabels.length];

    for (int i = 0; i < radioButtons.length; i++) {
      radioButtons[i] = new JRadioButton(radioButtonLabels[i]);
      radioButtons[i].setActionCommand(Integer.toString(i + 1));
    }

    btnLaunch = new JButton("Launch");

    addAttributes();
    addListeners();
    build();
    launch();
  }

  private void addAttributes() {
    centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
    centerPanel.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));

    radioButtons[0].setSelected(true);

    for (JRadioButton radioBtn : radioButtons)
      buttonGroup.add(radioBtn);

    mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    mainFrame.setResizable(false);
  }

  private void addListeners() {
    btnLaunch.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        new CustomExecutorService(6, Integer.parseInt(buttonGroup.getSelection().getActionCommand()));
      }
    });
  }

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

  private void launch() {
    mainFrame.setVisible(true);
    mainFrame.pack();
    mainFrame.setLocationRelativeTo(null);
  }

}