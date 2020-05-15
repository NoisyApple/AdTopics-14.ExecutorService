package com.milkyblue;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.github.tomaslanger.chalk.Chalk;

public class CustomExecutorService {

  public static final int CACHED_THREAD_POOL = 1, SINGLE_THREAD = 2, DOUBLE_THREAD = 3;

  private PrintableTask[] tasks;
  private ExecutorService threadExecutor;

  public CustomExecutorService(int taskAmount, int execMode) {
    tasks = new PrintableTask[taskAmount];

    for (int i = 0; i < tasks.length; i++)
      tasks[i] = new PrintableTask("Task " + (i + 1));

    String mode = "";
    switch (execMode) {
      case CACHED_THREAD_POOL:
        threadExecutor = Executors.newCachedThreadPool();
        mode = "CACHED TRHEAD POOL";
        break;
      case SINGLE_THREAD:
        threadExecutor = Executors.newSingleThreadExecutor();
        mode = "SINGLE THREAD";
        break;
      case DOUBLE_THREAD:
        threadExecutor = Executors.newFixedThreadPool(2);
        mode = "FIXED THREAD POOL (SET TO 2)";
        break;
    }

    System.out.println("[" + Chalk.on("EXECUTOR SERVICE").magenta() + "] " + mode);

    start();
  }

  private void start() {
    for (PrintableTask task : tasks)
      threadExecutor.execute(task);

    threadExecutor.shutdown();
  }

}