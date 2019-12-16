package project;

class ClockSimpleThread extends Thread {

    private ClockMainFrame clock;

    ClockSimpleThread(ClockMainFrame clock) {
        this.clock = clock;
    }

    public void run() {
        while(true) {
            clock.increment();
            try {
                sleep(1000);
            }

            catch(InterruptedException e) {

            }
        }
    }
}