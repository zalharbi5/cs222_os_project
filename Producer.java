class Producer implements Runnable { // implements Runnable == There's a run() method that can be used by threads
    Buffer producerBuf; // Holds the buffer that the producer has access to

    public Producer(Buffer buf) { // Parameterized Consturctor
        this.producerBuf = buf;
    }

    public void run() { // Running point for the thread.
        while(true)  {
            try {
                producerBuf.put("Print Job");
                Thread.sleep(500);

            } catch (InterruptedException e) { System.out.println(Thread.currentThread().getName() + " Interrupted "); }
        }
    }
}
