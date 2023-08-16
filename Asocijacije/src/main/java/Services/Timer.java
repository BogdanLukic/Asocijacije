package Services;

public class Timer {
    private int second;
    private Thread thread;

    public Timer(){
        second = 25;
    }

    public synchronized int getSecond() {
        return second;
    }
    public synchronized void startTimer(){
        thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    while (second > 0){
                        Thread.sleep(1000);
                        second -= 1 ;
                    }
                }
                catch (Exception e){}

            }
        });
        thread.start();
    }
    public void stopTimer(){
        thread.interrupt();
        second = 25;
    }
}
