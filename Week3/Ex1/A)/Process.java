public class Process extends Thread {
	private static final int INCREMENTS = 1000;
	private final Counter counter;

	public Process(Counter counter) {
		this.counter = counter;
	}

	@Override
	public void run() {
		for (int i = 0; i < INCREMENTS; i++) {
			counter.increment();
		}
	}

	public static void main(String[] args) throws InterruptedException {
		Counter counter = new Counter();
		Process[] processes = new Process[4];

		for (int i = 0; i < processes.length; i++) {
			processes[i] = new Process(counter);
			processes[i].start();
		}

		for (Process process : processes) {
			process.join();
		}

		System.out.println("Valor do contador: " + counter.getCount());
	}
}