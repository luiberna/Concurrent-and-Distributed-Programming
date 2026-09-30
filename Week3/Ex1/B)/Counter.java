class Counter {
	private int count;
	private final Lock lock = new ReentrantLock();

	public void increment() {
		lock.lock();
		try {
			count++;
		} finally {
			lock.unlock();
		}
	}

	public int getCount() {
		lock.lock();
		try {
			return count;
		} finally {
			lock.unlock();
		}
	}
}