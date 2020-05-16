package com.milkyblue;

import java.util.Random;

import com.github.tomaslanger.chalk.Chalk;

// PrintableTask Class. Models a taks that waits a random amount of time between 0 and 5 seconds.
public class PrintableTask implements Runnable {

  private final int inactivityTime;
  private final String taskName;
  private final static Random generator = new Random();

  // Class constructor.
  public PrintableTask(String taskName) {
    // The random amount of time its generated.
    inactivityTime = generator.nextInt(5000);
    this.taskName = taskName;
  }

  // Method called when a Thread based on class instance gets executed. Prints
  // when it is created and when the inactivity time has passed.
  public void run() {
    try {
      System.out.println(
          "[" + Chalk.on(taskName).cyan() + "] " + " will be inactive for " + inactivityTime + " milliseconds");
      Thread.sleep(inactivityTime);
    } catch (InterruptedException e) {
      System.out.println("[" + Chalk.on(taskName).red() + "] " + " stopped prematurely due to an interruption");
    }
    System.out.println("[" + Chalk.on(taskName).green() + "] " + " finished its inactivity");
  }

}