import java.util.Scanner;

public class SRTF
{
    static int input;
    static int[] process;  // process number
    static int[] burstTime;  // burst time
    static int[] waitTime;  // wait time
    static int[] TurnAroundTime;  // turn around time
    static int[] ArrivalTime;

    static long cpuTimeBefore,  cputimeAfter,cputimeDifference;
    // Driver Method
    public static void main(String[] args)
    {
        startProgram();
        doCalculation();
        findavgTime(input,burstTime,ArrivalTime);

}

    private static void startProgram (){

        Scanner n = new Scanner(System.in);
        System.out.println("\nPlease enter the number of Processes: ");;
        input = n.nextInt();
        process = new int[input];
        burstTime = new int[input];
        waitTime = new int[input];
        TurnAroundTime = new int[input];
        ArrivalTime = new int[input];
        for (int i = 0; i <input; i++) {
            System.out.println("Please enter the Arrival Time for Process " + (i+1) + ": ");
             ArrivalTime[i] = n.nextInt();
            System.out.println("Please enter the Burst Time for Process " + (i+1) + ": ");
            burstTime[i] = n.nextInt();

            cpuTimeBefore = System.currentTimeMillis();
        }
        System.out.println();

    }
    private static void doCalculation() {
        int pos,x;
        // sort burst time of process in ascending order using selection sort
        for (int i = 0; i < input; i++) {
            pos = i;
            for (int j = i + 1; j < input; j++) {
                if (ArrivalTime[j] < ArrivalTime[pos]) {
                    pos = j;
                }
            }

            x = ArrivalTime[i];
            ArrivalTime[i] = ArrivalTime[pos];
            ArrivalTime[pos] = x;

            x = process[i];
            process[i] = process[pos];
            process[pos] = x;

        }
    }
    // Method to find the waiting time for all
    // processes
    private static void findWaitingTime(int input,int bt[],int wt[],int at[])
    {
        int rt[] = new int[input];

        // Copy the burst time into rt[]
        for (int i = 0; i < input; i++)
            rt[i] = bt[i];

        int complete = 0, t = 0, minm = Integer.MAX_VALUE;
        int shortest = 0, finish_time;
        boolean check = false;

        // Process until all processes gets
        // completed
        while (complete != input) {

            // Find process with minimum
            // remaining time among the
            // processes that arrives till the
            // current time`
            for (int j = 0; j < input; j++)
            {
                if ((at[j] <= t) && (rt[j] < minm) && rt[j] > 0) {
                    minm = rt[j];
                    shortest = j;
                    check = true;
                }
            }

            if (check == false) {
                t++;
                continue;
            }

            // Reduce remaining time by one
            rt[shortest]--;

            // Update minimum
            minm = rt[shortest];
            if (minm == 0)
                minm = Integer.MAX_VALUE;

            // If a process gets completely
            // executed
            if (rt[shortest] == 0) {

                // Increment complete
                complete++;

                // Find finish time of current
                // process
                finish_time = t + 1;

                // Calculate waiting time
                wt[shortest] = finish_time - bt[shortest] - at[shortest];

                if (wt[shortest] < 0)
                    wt[shortest] = 0;
            }
            // Increment time
            t++;
        }
    }

    // Method to calculate turn around time
    private static void findTurnAroundTime(int input,int wt[],int tat[],int bt[])
    {
        // calculating turnaround time by adding
        // bt[i] + wt[i]
        for (int i = 0; i < input; i++)
            tat[i] = bt[i] + wt[i];
    }

    // Method to calculate average time
    private static void findavgTime(int input,int bt[],int at[])
    {
        int wt[] = new int[input], tat[] = new int[input];
        int  total_wt = 0, total_tat = 0;

        // Function to find waiting time of all
        // processes
        findWaitingTime(input,bt,wt,at);

        // Function to find turn around time for
        // all processes
        findTurnAroundTime(input, wt,tat,bt);

        // Display processes along with all
        // details
        System.out.println("| Processes " + "| BurstTime " + "| ArrivalTime " + "| WaitingTime " + "| TurnAroundTime |");

        // Calculate total waiting time and
        // total turnaround time
        for (int i = 0; i < input; i++) {
            total_wt = total_wt + wt[i];
            total_tat = total_tat + tat[i];
            System.out.println("\t\t" + (i+1) + "\t\t\t" + bt[i] + "\t\t\t" + at[i] + "\t\t\t" + wt[i] + "\t\t\t" + tat[i] );
        }

        System.out.println("Average waiting time = " +
                (float)total_wt / (float)input);
        System.out.println("Average turn around time = " +
                (float)total_tat / (float)input);

        cputimeAfter = System.currentTimeMillis();
        cputimeDifference = cputimeAfter - cpuTimeBefore;

        System.out.println("CPU Time: " + cputimeDifference);
    }

}