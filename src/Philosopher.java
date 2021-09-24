import java.util.Random;

public class Philosopher {
    private int Id;
    private Chopstick leftChopstick, rightChopstick;
    public Philosopher(int Id){
        this.Id = Id;
    }
    public void start(Chopstick leftChopstick, Chopstick rightChopstick) throws InterruptedException {
        this.leftChopstick = leftChopstick;
        this.rightChopstick = rightChopstick;
        while(true){
            if(new Random().nextBoolean()){
                eat();
            } else {
                think();
            }
        }
    }

    public void think() throws InterruptedException {
        System.out.println("The Philosophers: " + Id + " is now thinking");
        Thread.sleep(new Random().nextInt(1000)+100);
        System.out.println("The Philosopher: " + Id + " is done thinking");
    }

    public void eat() throws InterruptedException {
        boolean pickedRight = false;
        boolean pickedLeft = false;

        System.out.println("The philosopher " + Id + " is now ready to eat");
        System.out.println("The philosopher " + Id + " is now picking up the chopstick "+leftChopstick.Id);
        pickedLeft = leftChopstick.lift(Id);
        if(!pickedLeft){
            return;
        }
        System.out.println("The philosopher " + Id + " is now picking up the chopstick "+rightChopstick.Id);
        pickedRight = rightChopstick.lift(Id);
        if(!pickedRight){
            leftChopstick.drop();
            return;
        }
        System.out.println("The philosopher "+ Id + " is now eating");
        Thread.sleep(new Random().nextInt(1000) + 100);
        leftChopstick.drop();
        rightChopstick.drop();
    }

}
