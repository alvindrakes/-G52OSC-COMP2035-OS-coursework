import java.util.Scanner;

public class Priority_Scheduling
{
    static float avgWaitTime=0,avgTurnAroundTime=0;
    static int n,x;
    static long cputimeBefore,cputimeAfter,cputimeDifference;

    static int burstTime[], Process[],ProcessPriority [],waitTime [],TurnAroundTime [] ;

    public static void main(String args[]) {
        startProgram();
        doCalculation();
        printResult();
    }

    private static void startProgram() {

        Scanner num = new Scanner(System.in);
        System.out.print("Enter number of processes: \n");
        n = num.nextInt(); // char to int

        burstTime = new int[n+1];
        Process = new int[n+1];
        ProcessPriority = new int[n+1];
        waitTime = new int[n+1];
        TurnAroundTime = new int[n+1];

        for(int i=0; i<n; i++)
        {
            System.out.println("\nEnter Burst Time for each: ");
            System.out.print("SRTF["+(i+1)+"]: \n");
            burstTime[i] = num.nextInt();
        }

        System.out.print("\nEnter Time Priority for each process: ");
        for(int i=0; i<n; i++)
        {
            System.out.println("\nSRTF["+(i+1)+"]: ");
            ProcessPriority[i] = num.nextInt();
            Process[i] = i+1;
        }

        cputimeBefore = System.currentTimeMillis();


    }

    private static void doCalculation(){

        // sorting priority in ascending order
        // lower number == higher priority
        for( int i = 0; i<n-1; i++)
        {
            for( int j = i+1; j<n; j++)
            {
                if( ProcessPriority[i] > ProcessPriority[j])
                {
                    x = ProcessPriority[j];
                    ProcessPriority[j] = ProcessPriority[i];
                    ProcessPriority[i] = x;

                    x = burstTime[j];
                    burstTime[j] = burstTime[i];
                    burstTime[i] = x;

                    x = Process[j];
                    Process[j] = Process[i];
                    Process[i] = x;
                }
            }
        }

        // calculating wait time
        waitTime[0] = 0;
        for(int i=1; i<n; i++)
        {
            waitTime[i] = waitTime[i-1] + burstTime[i-1];
        }

        // calculating turn around time, avg wait time, avg turn around time
        for(int i=0; i<n; i++)
        {
            TurnAroundTime[i] = waitTime[i] + burstTime[i];
            avgWaitTime = avgWaitTime + waitTime[i];
            avgTurnAroundTime = avgTurnAroundTime + TurnAroundTime[i];
        }
    }

    private static void printResult(){
        System.out.println("\n====================== TABLE =========================");
        System.out.print(" ____________________________________________________\n");
        System.out.println("| Process |  Priority  |   BurstTime  | WaitingTime  | TurnAroundTime |");
        for(int i=0; i<n; i++)
        {
            System.out.println("    "+ Process[i] +"\t  \t\t" +ProcessPriority[i] + "\t  \t\t\t" + burstTime[i] + "\t  \t\t"+waitTime[i]+"\t   \t\t\t" + TurnAroundTime[i]);
            //System.out.println(SRTF[i] + "\t\t|" + ProcessPriority[i] + "\t\t|" + burstTime[i] + "\t\t|" + waitTime[i] + "\t\t|" + TurnAroundTime[i]);
        }

        avgWaitTime = avgWaitTime/n;
        System.out.println("\nAverage waiting time = " + avgWaitTime + "\n");

        avgTurnAroundTime = avgTurnAroundTime/n;
        System.out.println("Average turn-around time = " + avgTurnAroundTime + "\n");

        cputimeAfter = System.currentTimeMillis();
        cputimeDifference = cputimeAfter - cputimeBefore;

        System.out.println("CPU Time: " + cputimeDifference);

    }


    }