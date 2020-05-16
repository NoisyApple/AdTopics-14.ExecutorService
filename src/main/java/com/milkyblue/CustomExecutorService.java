package com.milkyblue;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.github.tomaslanger.chalk.Chalk;

// CustomExecutorService class. Models an ExecutorService with custom behaviour, 
// being capable of define the amount of tasks managed by the Executor and also the mode.
public class CustomExecutorService {
  // Static constants that refers to each ExecutorService mode.
  public static final int CACHED_THREAD_POOL = 1, SINGLE_THREAD = 2, DOUBLE_THREAD = 3;

  private PrintableTask[] tasks;
  private ExecutorService threadExecutor;

  // Class constructor. Takes an amount of tasks to be executed and the
  // ExecutorService mode.
  public CustomExecutorService(int taskAmount, int execMode) {
    tasks = new PrintableTask[taskAmount];

    // Defines all the tasks in the array with a new instance of PrintableTask.
    for (int i = 0; i < tasks.length; i++)
      tasks[i] = new PrintableTask("Task " + (i + 1));

    // Defines the ExecutorService object based on the selected mode.
    String mode = "";
    switch (execMode) {
      case CACHED_THREAD_POOL:
        threadExecutor = Executors.newCachedThreadPool();
        mode = "CACHED THREAD POOL";
        break;
      case SINGLE_THREAD:
        threadExecutor = Executors.newSingleThreadExecutor();
        mode = "SINGLE THREAD";
        break;
      case DOUBLE_THREAD:
        // Amount passed to the fixed thread ExecutorService.
        int amount = 2;
        threadExecutor = Executors.newFixedThreadPool(amount);
        mode = "FIXED THREAD POOL (SET TO " + amount + ")";
        break;
    }

    System.out.println("[" + Chalk.on("EXECUTOR SERVICE").magenta() + "] " + mode);

    start();
  }

  // Executes all tasks in the array with the ExecutorService, when all tasks are
  // done the ExecutorService its shutted down.
  private void start() {
    for (PrintableTask task : tasks)
      threadExecutor.execute(task);

    threadExecutor.shutdown();
  }

}