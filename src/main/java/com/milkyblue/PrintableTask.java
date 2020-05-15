package com.milkyblue;

import java.util.Random;

import com.github.tomaslanger.chalk.Chalk;

public class PrintableTask implements Runnable {

  private final int inactivityTime;
  private final String taskName;
  private final static Random generator = new Random();

  public PrintableTask(String taskName) {
    inactivityTime = generator.nextInt(5000);
    this.taskName = taskName;
  }

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