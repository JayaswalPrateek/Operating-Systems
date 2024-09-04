class Process {
    int pid;
    int burstTime;
    int waitingTime;
    int turnAroundTime;

    Process(int pid, int burstTime) {
        this.pid = pid;
        this.burstTime = burstTime;
        this.waitingTime = 0;
        this.turnAroundTime = 0;
    }
}

class FCFS {
    static int MAX_PID = 5;
    static int[] BURST_TIMES = { 12, 9, 3, 15, 6 };

    static Process[] buildProcList() {
        Process[] procList = new Process[MAX_PID];
        for (int i = 0; i < MAX_PID; i++) {
            procList[i] = new Process(i + 1, BURST_TIMES[i]);
        }
        return procList;
    }

    static void scheduleFCFS(Process[] procList) {
        int waitingTime = 0;
        for (Process proc : procList) {
            proc.waitingTime = waitingTime;
            waitingTime += proc.burstTime;
        }
    }

    static void printFCFSResults(Process[] procList) {
        System.out.println("Processes\tBurst time\tWaiting time\tTurn around time");

        int totalWaitingTime = 0;
        int totalTurnAroundTime = 0;

        for (Process proc : procList) {
            proc.turnAroundTime = proc.burstTime + proc.waitingTime;
            totalWaitingTime += proc.waitingTime;
            totalTurnAroundTime += proc.turnAroundTime;

            System.out.println(proc.pid + "\t\t" + proc.burstTime + "\t\t" + proc.waitingTime + "\t\t" + proc.turnAroundTime);
        }

        double averageWaitingTime = (double) totalWaitingTime / procList.length;
        double averageTurnAroundTime = (double) totalTurnAroundTime / procList.length;

        System.out.println("Average waiting time = " + averageWaitingTime);
        System.out.println("Average turn around time = " + averageTurnAroundTime);
    }

    public static void main(String[] args) {
        Process[] procList = buildProcList();
        scheduleFCFS(procList);
        printFCFSResults(procList);
    }
}
