package week.one;

public class SingletonDoubleCheckLock {
	// must be declared volatile to ensure that changes made to it are visible to all threads
	private static volatile SingletonDoubleCheckLock singletonInstance = null;
	
	private SingletonDoubleCheckLock() {
		// Object instantiation code goes here
	}
	
	public static SingletonDoubleCheckLock getInstance() {
		// for better performance, avoid synchronized block if instance is already instantiated.
		if (singletonInstance == null) {
			// synchronized on class for instantiating the singleton instance.
			synchronized(SingletonDoubleCheckLock.class) {
				if (singletonInstance == null) {
					singletonInstance = new SingletonDoubleCheckLock();
				}
			}
		}
		return singletonInstance;
	}

}
