package study;

import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by DIMA on 26.02.2015.
 * in project MultiprocessingLab2
 */
public class CPU {
    private ReentrantLock CPU;
    private String name;
    private int processCount;       //number of served processes
    private double loadInPercent;

    public CPU() {
        CPU = new ReentrantLock();
        processCount = 0;
        loadInPercent = 0;
    }
    public String getName() {
        return name;
    }
    public void setName(String aName) {
        name = aName;
    }
    public boolean tryLock() {
        return CPU.tryLock();
    }
    public void unlock() {
        CPU.unlock();
    }
    public int getProcessCount() {
        return processCount;
    }
    public String calcLoad() {
        loadInPercent = ((double) processCount / Lab2Main.PROCESS_IN_THREAD) * 100;
        return String.format( "%.2f", loadInPercent );
    }
    public void incrementCounter() {
        processCount++;
    }
    public String checkLock() {
        return CPU.isLocked() ? " locked" : " free";
    }
}
