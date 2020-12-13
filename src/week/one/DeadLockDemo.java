package week.one;

public class DeadLockDemo {
	public static volatile Object lockObj1 = new Object();
	public static volatile Object lockObj2 = new Object();
	
	public static void main(String[] args) {
		
		// Thread 1 will attempt to acquire locks in the order of lockObj1, lockObj2
		Thread t1 = new Thread(new Runnable() {
			@Override
			public void run() {
				String name = Thread.currentThread().getName();
				System.out.println("Running Thread: " + name);
				System.out.println(name + ": acquiring lock on Object1 ...");
				synchronized(DeadLockDemo.lockObj1) {
					System.out.println(name + ":  lock acquired on Object1");
					System.out.println(name + ": sleeping 1 sec ...");
					try {
						Thread.sleep(1000L);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					System.out.println(name + ": acquiring lock on Object2 ...");
					synchronized(DeadLockDemo.lockObj2) {
						System.out.println(name + ":  lock acquired on Object2");
					}
					System.out.println(name + ":  lock on Object2 released");
				}
				System.out.println(name + ":  lock on Object1 released");
				System.out.println(name+ ": running done");
			}
		}, "Thread One");
		
		// Thread 2 will attempt to acquire locks in the order of lockObj2, lockObj1
		// It is the reverse lock acquire order of thread 1.
		Thread t2 = new Thread(new Runnable() {
			@Override
			public void run() {
				String name = Thread.currentThread().getName();
				System.out.println("Running Thread: " + name);
				System.out.println(name + ": acquiring lock on Object2 ...");
				synchronized(DeadLockDemo.lockObj2) {
					System.out.println(name + ":  lock acquired on Object2");
					System.out.println(name + ": sleeping 1 sec ...");
					try {
						Thread.sleep(1000L);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					System.out.println(name + ": acquiring lock on Object1 ...");
					synchronized(DeadLockDemo.lockObj1) {
						System.out.println(name + ":  lock acquired on Object1");
					}
					System.out.println(name + ":  lock on Object1 released");
				}
				System.out.println(name + ":  lock on Object2 released");
				System.out.println(name+ ": running done");
			}
		}, "Thread Two");
		
		// Start both threads.
		t1.start();
		t2.start();
	}

}
