package study;

import java.util.Random;

/**
 * Created by DIMA on 26.02.2015.
 * in project MultiprocessingLab2
 */
public class Process extends Thread {
    static CPU[] CPUs = new CPU[Lab2Main.CPU_NUMBER];

    public void run () {
        /* generate time of processing next process */
        Random rand = new Random();
        long serveTime = rand.nextInt(Lab2Main.HIGH_TIME_BOUND - Lab2Main.LOW_TIME_BOUND)
                                    + Lab2Main.LOW_TIME_BOUND;

        /* attempt to acquire the 1st non-locked CPU */
        for (int j = 0; j < Lab2Main.CPU_NUMBER; j++) {
            if (CPUs[j].tryLock()) {
                try {
                    System.out.println("new process acquire " + CPUs[j].getName());
                    CPUs[j].incrementCounter();
                    break;
                    }
                finally {
                    try {
                        sleep(serveTime);               //imitation of processing
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    CPUs[j].unlock();
                    System.out.println(CPUs[j].getName() + CPUs[j].checkLock());
                }
            } else {
                System.out.println(CPUs[j].getName() + " is acquired");
            }
        }
    }
}
