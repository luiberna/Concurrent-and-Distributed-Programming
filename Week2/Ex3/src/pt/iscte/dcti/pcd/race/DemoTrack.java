package pt.iscte.dcti.pcd.race;

import javax.swing.JFrame;
import java.util.Observable;
import java.util.Observer;

public class DemoTrack implements Observer {
	private final int numSteps;
	private final Car[] cars;
	private final Thread[] carThreads;
	private boolean winnerDeclared;

	private DemoTrack(Track track, int numCars, int numSteps) {
		this.numSteps = numSteps;
		cars = new Car[numCars];
		carThreads = new Thread[numCars];
		for (int i = 0; i < numCars; i++) {
			cars[i] = new Car(i, numSteps);
			cars[i].addObserver(track);
			cars[i].addObserver(this);
			carThreads[i] = new Thread(cars[i], "car-" + (i + 1));
		}
	}

	private void startRace() {
		for (Thread carThread : carThreads)
			carThread.start();
	}

	@Override
	public synchronized void update(Observable observedCar, Object arg) {
		Car car = (Car) observedCar;
		if (winnerDeclared || car.getPosition() < numSteps)
			return;

		winnerDeclared = true;
		System.out.println("Carro vencedor: " + (car.getId() + 1) + "º");
		for (Thread carThread : carThreads)
			carThread.interrupt();
	}

	public static void main(String[] args) {
		int numCars = 10;
		int numSteps = 100;
		JFrame frame = new JFrame("Demo Track");
		Track track = new Track(numCars, numSteps);
		DemoTrack race = new DemoTrack(track, numCars, numSteps);

		frame.add(track);
		frame.setSize(500, 300);
		frame.setVisible(true);
		race.startRace();
	}

}
