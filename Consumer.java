class Consumer implements Runnable { // implements Runnable == There's a run() method that can be used by threads
    Buffer consumerBuf; // Holds the buffer that the consumer has access to

    public Consumer(Buffer buf) { // Parameterized Consturctor
        this.consumerBuf = buf;
    }

    public void run() { // Running point for the thread
        while (true) {
            try {
                consumerBuf.get();

                Thread.sleep(1000);
            } catch (InterruptedException e) { System.out.println(Thread.currentThread().getName() + " Interrupted "); }
        }
    }
}
