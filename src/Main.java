import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Random;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader buff = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Enter the number of philosophers");
        int numberOfPhilosophers = Integer.parseInt(buff.readLine());
        Philosopher[] philosopher;
        Chopstick[] chopsticks;
        Thread[] threads;
        philosopher = new Philosopher[numberOfPhilosophers];
        chopsticks = new Chopstick[numberOfPhilosophers];
        threads = new Thread[numberOfPhilosophers];

        for(int i = 0; i < numberOfPhilosophers; i++){
            philosopher[i] = new Philosopher(i+1);
            chopsticks[i] = new Chopstick(i+1,true);
        }
        startTheCode(numberOfPhilosophers, threads, philosopher, chopsticks);

    }
    public static void startTheCode(int number, Thread[] threads, Philosopher[] philosopher, Chopstick[] chopsticks){
        for(int i = 0; i < number; i++){
            final int index = i;
            threads[i] = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        philosopher[index].start(chopsticks[index], chopsticks[(index + 1) % (number)]);
                    } catch (InterruptedException e){
                        e.printStackTrace();
                    }
                }
            });
            threads[i].start();
        }
    }
}
