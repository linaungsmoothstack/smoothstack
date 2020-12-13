package week.one;

public class MultithreadProducerConsumer {

	public static void main(String[] args) {
		// Synchronized Blocking array queue with capacity of 5
		SynchronizedIntDataArray arr = new SynchronizedIntDataArray(5);
		
		Thread writer1 = new Thread(new DataProducer(arr, 10), "DataProducer");
		Thread writer2 = new Thread(new DataProducer(arr, 10), "DataProducer");
		Thread reader = new Thread(new DataConsumer(arr, 20), "DataConsumer");
		
		// Start producer and it will write 10 data to a blocking queue with capacity of 5.  Will result in blocking.
		writer1.start();
		
		// Wait a few seconds before starting consumer to demonstrate "producer blocking"
		try {
			Thread.sleep(15000);
		} catch (InterruptedException e) {}
		
		// Start consumer.  It tries to consume one more data items and producer and will block until data is available
		reader.start();
		
		// Wait a few seconds before starting the second producer to demonstrate "consumer blocking"
		try {
			Thread.sleep(15000);
		} catch (InterruptedException e) {}
		
		// Consumer should resume
		writer2.start();
	}
	
	private static class SynchronizedIntDataArray {
		// circular array
		private int[] dataQueue;
		// current number of available data
		private int count;
		// Read at head and write at tail
		private int headIndex;
		private int tailIndex;
		
		public SynchronizedIntDataArray(int n) {
			dataQueue = new int[n];
			count = 0;
			headIndex = 0;
			tailIndex = 0;
		}
		
		public synchronized void insertData(int data) {
			while(isFull()) {
				try {
					wait();
				} catch (InterruptedException e) {}
			}
			dataQueue[tailIndex] = data;
			tailIndex = nextIndex(tailIndex);
			count++;
			notifyAll();
		}
		
		public synchronized int getData() {
			while(isEmpty()) {
				try {
					wait();
				} catch (InterruptedException e) {}
			}
			int data = dataQueue[headIndex];
			headIndex = nextIndex(headIndex);
			count--;
			notifyAll();
			return data;
		}
		
		private boolean isFull() {
			return count == dataQueue.length;
		}
		
		private boolean isEmpty() {
			return count == 0;
		}
		
		private int nextIndex(int index) {
			return (index + 1) % dataQueue.length;
		}
	}
	
	private static class DataProducer implements Runnable {
		private final SynchronizedIntDataArray arr;
		private final int dataCount;

		public DataProducer(SynchronizedIntDataArray arr, int dataCount) {
			this.arr = arr;
			this.dataCount = dataCount;
		}

		@Override
		public void run() {
			// insert data
			for(int i = 1; i <= dataCount; i++) {
				// generate random int [1, 100]
				int randomInt = (int)(Math.random() * 100 + 1);
				System.out.println("++++++++++ Inserting [" + i + "]: " + randomInt + " ...");
				arr.insertData(randomInt);
				System.out.println("++++++++++ [" + i + "]: " + randomInt + " inserted");
				// delay inserted for code demo purposes
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {}
			}
		}
	}
	
	private static class DataConsumer implements Runnable {
		private final SynchronizedIntDataArray arr;
		private final int dataCount;

		public DataConsumer(SynchronizedIntDataArray arr, int dataCount) {
			this.arr = arr;
			this.dataCount = dataCount;
		}

		@Override
		public void run() {
			// retrieve data
			for(int i = 1; i <= dataCount; i++) {
				System.out.println("---------- Retrieving data [" + i +"] ...");
				int data = arr.getData();
				System.out.println("---------- [" + i + "]: " + data + " retrieved");
				// delay inserted for code demo purposes
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {}
			}
		}
	}

}


