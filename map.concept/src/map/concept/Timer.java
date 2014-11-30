package map.concept;

import java.util.LinkedList;
import java.util.List;

public class Timer implements Runnable {

	private static final int SECOND = 1000;

	public static Timer getInstance() {
		if (instance == null)
			instance = new Timer();

		return instance;
	}

	@Override
	public synchronized void run() {
		long previous = System.currentTimeMillis();

		while (true) {
			long current = System.currentTimeMillis();
			if (current - SECOND >= previous) {
				previous = current;

				for (Tickable tickable : tickables)
					tickable.tick();
			}
		}
	}

	public void addTickable(Tickable tickable) {
		tickables.add(tickable);
	}

	/* Private */
	private static List<Tickable> tickables = new LinkedList<Tickable>();
	private static Timer instance;

	private Timer() {
	}

}
