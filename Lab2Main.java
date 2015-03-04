package study;

import java.util.Random;

/**
 * Created by DIMA on 26.02.2015.
 * in project MultiprocessingLab2
 * Программа моделирует обслуживание одного потока процессов несколькими центральными
 * процессорами компьютера без очередей. Если процесс сгенерирован в тот момент,
 * когда первый процессор занят, процесс берется на обработку вторым процессором,
 * если и этот процессор занят, запускается третий процессор и т.д.
 * Определить количество задействованных процессоров и процент от общего количества
 * процессов, которые были обработаны каждым процессором.
 */
public class Lab2Main {
    final static int PROCESS_IN_THREAD = 11;
    final static int CPU_NUMBER = 3;
    final static int HIGH_TIME_BOUND = 1000;                    //time boundS (in milliseconds) for generating   1000
    final static int LOW_TIME_BOUND = 200;                      //new process and processor serve time           200
    static Process[] processes = new Process[PROCESS_IN_THREAD];

    public static void main(String[] args) {
        /* fill the CPUs array */
        for (int i = 0; i < CPU_NUMBER; i++) {
            CPU cpu = new CPU();
            cpu.setName("CPU" + (i + 1));
            Process.CPUs[i] = cpu;
        }
        System.out.println("> Start of processing");

        /* thread generate PROCESS_IN_THREAD processes with random interval */
        for (int i = 0; i < PROCESS_IN_THREAD; i++) {
            Process process = new Process();
            processes[i] = process;
            /* generate time when the next process appear */
            Random rand = new Random();
            long nextAppearTime = rand.nextInt(HIGH_TIME_BOUND - LOW_TIME_BOUND)
                                             + LOW_TIME_BOUND;
            try {
                Process.sleep(nextAppearTime);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            process.start();                    //call run()
        }

        /* wain for all processes ending */
        for (int i = 0; i < PROCESS_IN_THREAD; i++) {
            try {
                processes[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        /* output of results */
        System.out.println("> End of processing\n\nSummary:");
        System.out.println("Name\tServed\tLoad");
        for (int i = 0; i < CPU_NUMBER; i++) {
                System.out.println( Process.CPUs[i].getName() + '\t'
                                  + Process.CPUs[i].getProcessCount() + "\t\t"
                                  + Process.CPUs[i].calcLoad() + '%');
        }
    }
}
