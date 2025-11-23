import java.util.concurrent.Semaphore;
class Buffer {
    private String[] buffer; // Buffer stores string elements
    private int in = 0; // Index of inserting a job to the buffer
    private int out = 0; // Index of consuming a job from the buffer
    private int jobCounter = 1;

    private Semaphore mutex; // Access-key to the buffer
    private Semaphore empty; // Number of empty slots in the buffer
    private Semaphore full; // Number of occupied slots in the buffer

    public Buffer() { // Default Constructor
        this.buffer = new String[5]; // Buffer accepts 5 items maximum
        this.mutex = new Semaphore(1); // Access key: 1 = you may enter, 0 = you aren't allowed to enter.
        this.empty = new Semaphore(5); // Number of empty slots is initially 5
        this.full = new Semaphore(0); // Number of occupied slots is initially 0

	// ==== Two main operations associated with semaphores ====
	// acquire(): Decreases the Semaphore by 1
	// **** Note: If the Semaphore becomes 0, then the thread enters a waiting state because it can't acquires anything.
	// release(): Increases the Semaphore by 1

	// Simply put, a semaphore is a counter with two methods to decrease or increase that counter: acquire(), and release().
	// The main purpose of that counter is to control how and when should the thread be in waiting state or running state.
    }

    public void put(String item) {
        try {
            empty.acquire(); // Check if there's an empty slot in the buffer, if not -if empty is zero- then wait
            mutex.acquire(); // Check if the access key is available, if not then wait
	    // In addition to checking, acquire() decrements the semaphore -if it's not zero- by 1.
	    // If the semaphore value is zero, the thread will enter a waiting state.

            item = item + " " + jobCounter;

            buffer[in] = item;

            in = (in + 1) % 5; // "%5" == Get back to the buffer beginning if the end is reached
            System.out.println("++ " + Thread.currentThread().getName() + " created: " + item);
	    System.out.println("===================================");

	    jobCounter++;

            mutex.release(); // After finishing the job, release the access key so that it can be acquired by other threads.
            full.release(); // Increase the number of occupied slots by one.

        } catch (InterruptedException e) {
            System.out.println(Thread.currentThread().getName() + " Interrupted ");
        }
    }

    public void get() {
        try {
            full.acquire(); // Check if there's an occupied slot in the buffer, if not -if full is zero- then wait.
            mutex.acquire(); // Check if the access key is available, if not then wait.
	    // In addition to checking, acquire() decrements the semaphore -if it's not zero- by 1.
	    // If the semaphore value is zero, the thread will enter a waiting state.

            String item = buffer[out];

            out = (out + 1) % 5; // "%5" == Get back to the buffer's beginning if you reached the end

            System.out.println("-- " + Thread.currentThread().getName() + " printed: " + item);
	    System.out.println("===================================");

            mutex.release(); // After finishing the job, release the access key so that it can be used by other threads.
            empty.release(); // Increase the number of empty slots by one

        } catch (InterruptedException e) {
            System.out.println(Thread.currentThread().getName() + " Interrupted ");
        }
    }
}
