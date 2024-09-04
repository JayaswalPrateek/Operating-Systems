class Philosopher implements Runnable {
    Object leftFork, rightFork;

    Philosopher(Object left, Object right) {
        this.leftFork = left;
        this.rightFork = right;
    }

    void doAction(String action) throws InterruptedException {
        System.out.println(Thread.currentThread().getName() + " " + action);
        Thread.sleep(((int) (Math.random() * 100)));
    }

    public void run() {
        try {
            while (true) {
                doAction("Thinking"); // thinking
                synchronized (leftFork) {
                    doAction("Picked up left fork");
                    synchronized (rightFork) {
                        doAction("Picked up right fork - eating"); // eating
                        doAction("Put down right fork");
                    }
                    doAction("Put down left fork. Returning to thinking");
                }
            }
        } catch (Exception e) {
        }
    }
}

class DiningPhilosophers {
    public static void main(String[] args) throws Exception {
        Philosopher[] philosophers = new Philosopher[5];
        Object[] forks = new Object[philosophers.length];

        for (int i = 0; i < forks.length; i++)
            forks[i] = new Object();

        for (int i = 0; i < philosophers.length; i++) {
            Object leftFork = forks[i];
            Object rightFork = forks[(i + 1) % forks.length];
            if (i == philosophers.length - 1)
                philosophers[i] = new Philosopher(rightFork, leftFork); // The last philosopher picks up the right fork first
            else
                philosophers[i] = new Philosopher(leftFork, rightFork);

            new Thread(philosophers[i], "Philosopher " + (i + 1)).start();
        }
    }
}