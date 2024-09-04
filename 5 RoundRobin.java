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

class RoundRobin {
    static int MAX_PID = 5;
    static int[] BURST_TIMES = { 12, 9, 3, 15, 6 };
    static int QUANTUM = 2;

    static Process[] buildProcList() {
        Process[] procList = new Process[MAX_PID];
        for (int i = 0; i < MAX_PID; i++) {
            procList[i] = new Process(i + 1, BURST_TIMES[i]);
        }
        return procList;
    }

    static void roundRobin(Process[] procList, int quantum) {
        int[] remainingTime = new int[MAX_PID];
        for (int i = 0; i < MAX_PID; i++) {
            remainingTime[i] = procList[i].burstTime;
        }

        int t = 0;
        while (true) {
            boolean done = true;
            for (int i = 0; i < MAX_PID; i++) {
                if (remainingTime[i] > 0) {
                    done = false;
                    if (remainingTime[i] > quantum) {
                        t += quantum;
                        remainingTime[i] -= quantum;
                    } else {
                        t += remainingTime[i];
                        procList[i].waitingTime = t - procList[i].burstTime;
                        remainingTime[i] = 0;
                    }
                }
            }
            if (done)
                break;
        }
    }

    static void calculateTurnaroundTime(Process[] procList) {
        for (Process proc : procList) {
            proc.turnAroundTime = proc.burstTime + proc.waitingTime;
        }
    }

    static void printRoundRobinResults(Process[] procList) {
        System.out.println("Processes\tBurst time\tWaiting time\tTurn around time");

        double totalWaitingTime = 0;
        double totalTurnAroundTime = 0;

        for (Process proc : procList) {
            totalWaitingTime += proc.waitingTime;
            totalTurnAroundTime += proc.turnAroundTime;

            System.out.println(proc.pid + "\t\t" + proc.burstTime + "\t\t" + proc.waitingTime + "\t\t" + proc.turnAroundTime);
        }

        double averageWaitingTime = totalWaitingTime / MAX_PID;
        double averageTurnAroundTime = totalTurnAroundTime / MAX_PID;

        System.out.println("Average waiting time = " + averageWaitingTime);
        System.out.println("Average turn around time = " + averageTurnAroundTime);
    }

    public static void main(String[] args) {
        Process[] procList = buildProcList();
        roundRobin(procList, QUANTUM);
        calculateTurnaroundTime(procList);
        printRoundRobinResults(procList);
    }
}
