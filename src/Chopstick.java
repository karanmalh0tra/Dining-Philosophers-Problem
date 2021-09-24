import java.util.Random;

public class Chopstick {
    int Id;
    boolean status; //true = available to use. false = used by philosopher
    Chopstick(int Id, boolean status){
        this.Id = Id;
        this.status = status;
    }
    public synchronized void drop() {
        status = true;
    }
    public synchronized boolean lift(int philosopherId) throws InterruptedException {
        int counter = 0;
        int waitTime = new Random().nextInt(10) + 5;
        while(!status){
            Thread.sleep(new Random().nextInt(100) + 50);
            counter += 1;
            if (counter > waitTime){
                return false;
            }
        }
        status = false;
        return true;
    }

}
