import java.util.Scanner;

public class FCFS {

    static int input;
    static int waitingTime[], burstTime[], turnAroundTime[];
    static long cpuTimeBefore,  cpuTimeAfter;
    static float avgWaitTime = 0, avgTurnAroundTime = 0;

    public static void main(String[] args) {

        startProgram();

		doCalculation();

        printResult(avgWaitTime, avgTurnAroundTime);

    }


    private static void startProgram() {

        System.out.println("===== First Come First Serve (FCFS) =====\n");

        Scanner num = new Scanner(System.in);
        System.out.println("Enter number of process: ");
        input = num.nextInt(); //get the number of process

        waitingTime = new int[input];
        burstTime = new int[input];
        turnAroundTime = new int[input];

        //Initialize the start time of CPU usage
        for(int i = 0; i < input; i++){
            System.out.println("Enter the burst time for process " +(i+1) + ":");
            burstTime[i] = num.nextInt();
        }

        cpuTimeBefore = System.currentTimeMillis();

        waitingTime[0] = 0;
    }


    private static void doCalculation() {
        /*  FCFS algorithm */

        for(int i = 1; i < input; i++){
            waitingTime[i] = waitingTime[i-1]+ burstTime[i-1]; //calculate the waiting for each process
        }

        for(int i = 0; i < input; i++){
            turnAroundTime[i] = waitingTime[i] + burstTime[i]; //calculate turn around time for each process
            avgWaitTime += waitingTime[i]; //calculate average waiting time
        }

        for(int j = 0; j < input; j++){
            avgTurnAroundTime += turnAroundTime[j]; //calculate average turn around time
        }
    }


    private static void printResult(float avgWt, float avgTAT) {

        float avgWaitTime = avgWt;
        float avgTurnAroundTime = avgTAT;

        System.out.println("\n====================== TABLE =========================");

        System.out.print(" ____________________________________________________\n");
        System.out.println("| Process | BurstTime | WaitingTime | TurnAroundTime |");

        for(int i = 0; i < input; i++){
            System.out.println("\t  "+ (i+1) +"\t\t   "+burstTime[i]+"\t\t    "+waitingTime[i]+"\t\t \t    "+turnAroundTime[i] + " \t\t ");
        }

        System.out.println("\n======================================================");

        System.out.println("Average Waiting Time = "+ String.format("%.2f", avgWaitTime / input));

        System.out.println("Average Turn Around Time = "+ String.format("%.2f", avgTurnAroundTime / input));

        cpuTimeAfter = System.currentTimeMillis(); //Get the end time of CPU usage during the program

        long cpuTimeDifference = cpuTimeAfter - cpuTimeBefore; // Calculate the CPU usage

        System.out.println("CPU Time = " + cpuTimeDifference);
    }

}
