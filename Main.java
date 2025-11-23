public class Main {

    public static void main(String[] args) {
        Buffer buf = new Buffer();
	// Why are we using the keyword (new)?
        // Because in java OOP, all objects must be dynamically allocted. (Thread producer1; ==> ILLEGAL EXPRESSION!!!)
        // Also, dynamically allocated objects will be deleted automatically. No neeed for (delete) keyword like in C++.

	// Set of Producers
        Thread producer1 = new Thread(new Producer(buf), "Producer 1"); //Name: "Producer 1". Run Class: Producer
	Thread producer2 = new Thread(new Producer(buf), "Producer 2");
	Thread producer3 = new Thread(new Producer(buf), "Producer 3");

	// Set of Consumers
        Thread consumer1 = new Thread(new Consumer(buf), "Consumer 1"); //Name: "Consumer 1". Run Class: Consumer
	Thread consumer2 = new Thread(new Consumer(buf), "Consumer 2");
	Thread consumer3 = new Thread(new Consumer(buf), "Consumer 3");
	// We are giving the created threads 2 things:
	// 1. The name of the thread (e.g. "Producer 1")
	// 2. The code that will be executed by the thread
	// (e.g. new Consumer(buf) => Means the thread will be executing some code from the consumer class).

        System.out.println("~~~~~~~~~~~~~~~ Printer Simulator ~~~~~~~~~~~~~~~\n");
        producer1.start(); // Start execution from run() in Producer class
	producer2.start();
	producer3.start();

        consumer1.start(); // Start execution from run() in Consumer class
	consumer2.start();
	consumer3.start();
    }
}

