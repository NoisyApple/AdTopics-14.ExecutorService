# [Advanced Programming Topics] #14 ExecutorService

This is the **14th** project part of my Advanced Programming Topics course at Leon Institute of Thechnology :mortar_board::lion:.

## :dart:GOAL

Show how **ExecutorService** thread management works through a Java program. This is achieved by selecting the desired executor service mode in the following GUI, which will create a new instance of ExecutorService with the selected mode.

<p align="center">
<img src="./snapshots/Annotation 2020-05-15 165803.png"/>
</p>

## :eyeglasses:BRIEF EXPLANATION

An **ExecutorService** helps us to manage Threads in our program by launching them for us in an specified way to fit our needs.
The following sections explain each of the ExecutorService modes shown in the program's GUI, each o them runs a sequence of threads which runs a task that waits a random amount of time between 0 and 5 seconds.

### Cached Thread Pool :earth_americas:🧵

This ExecutorService mode will **not limit** the amount of threads running at the same time, so, everytime we add a new Thread to it, it will start it immediately.

<p align="center">
<img src="./snapshots/Annotation 2020-05-15 165917.png"/>
</p>

```java
ExecutorService threadExecutor = Executors.newCachedThreadPool();
```

So, the program adds 6 tasks (**Threads**) to the Executor, and therefore each of those 6 tasks are executed at the same time.

### Single Thread 🧵

Unlike the previous **ExecutorService** mode, this one only executes one task at a time, so no mather how many tasks are asigned to the ExecutorService, only one will be running and therefore the others will wait until the ExecutorService is no longer executing a task.

<p align="center">
<img src="./snapshots/Annotation 2020-05-15 170022.png"/>
</p>

```java
ExecutorService threadExecutor = Executors.newSingleThreadExecutor();
```

Now, even though all 6 tasks are added immediately to the **ExecutorService** it starts by only execute the first one, so the other tasks will need to wait their turn, just like a **queue** works.

### Fixed Thread Pool :zap:🧵

In a similar way as the previous mode, this one also limits the amount of tasks running at the same time by the **ExecutorService** with the difference of being capable to set that amount to a fixed value, hence its name.

<p align="center">
<img src="./snapshots/Annotation 2020-05-15 170115.png"/>
</p>

```java
int amount = 2;
ExecutorService threadExecutor = Executors.newFixedThreadPool(amount);
```

This way when the ExecutorService gets all 6 tasks, will only execute the specified amount of tasks at a time (2 in the previous example). Whenever one of those executing tasks is completed it gets disposed from the ExecutorService and its replaced with another task in the **queue**.
