package collision;

import java.awt.Color;
import java.io.File;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

/**
 *  The {@code Simulator} class runs a simulation of the collision system based on data in the argument to the program, or found on file. 
 *  @author Magnus Nielsen
 */
public class Simulator {

	/**
	 * Tests whether or not a string's contents are an integer value.
	 * @param s a string to be tested if it's content are an integer.
	 * @return boolean: true if the contents of the string are indeed an integer, false otherwise.
	 */
	public static boolean isInteger(String s) {
		int radix = 10;
		if(s == null || s.isEmpty()) {
			return false;
		}

		for(int i = 0; i < s.length(); i++) {
			if(i == 0 && s.charAt(i) == '-' && s.length() == 1) {
				return false;
			} else if (Character.digit(s.charAt(i),radix) < 0) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Creates an ArrayList of Particles for the simulation, based off of data from a Scanner.
	 * @param inputScanner that has already been linked to the appropriate source for data
	 * @return an ArrayList of Particles for the simulation.
	 */
	public static ArrayList<Particle> populate(Scanner inputScanner) {
		int N, r, g, b;
		double rx, ry, vx, vy, radius, mass;
		ArrayList<Particle> particles = new ArrayList<>(); 
		
		if (inputScanner == null || !inputScanner.hasNext()) {
			return null;
		}
		
		// N is the amount of particles to simulate
		N = inputScanner.nextInt();
		// Read the particle data from the Scanner received in the call.
		for (int i = 0; i < N; i++) {
			rx = inputScanner.nextDouble();
			ry = inputScanner.nextDouble();
			vx = inputScanner.nextDouble();
			vy = inputScanner.nextDouble();
			radius = inputScanner.nextDouble();
			mass = inputScanner.nextDouble();
			r = inputScanner.nextInt();
			g = inputScanner.nextInt();
			b = inputScanner.nextInt();
			particles.add(new Particle(rx, ry, vx, vy, radius, mass, new Color(r,g,b)));
		}
		return particles;
	}

	public static void main(String[] args) throws Exception {
		ArrayList<Particle> particles; // List of particles
		Scanner inputScanner;
		int argc = args.length;                    // Number of arguments for the program

		Locale.setDefault(new Locale("en", "US")); // We force Java to read decimal point instead of comma

		if (argc == 1 && isInteger(args[0])) {
			particles = new ArrayList<>();
			// N is the particle count we wish to simulate
			int N = Integer.parseInt(args[0]);

			// Generate N randomized particles.
			for (int i = 0; i < N; i++) {
				particles.add(new Particle());
			}
		} else if (argc == 1) {
			inputScanner = new Scanner(new File(args[0]));
			particles = populate(inputScanner);
			inputScanner.close();
		} else {
			inputScanner = new Scanner(System.in);
			particles = populate(inputScanner);
			inputScanner.close();
		}
		// create collision system and simulate
		CollisionSystem system = new CollisionSystem(particles.toArray(new Particle[0]));
		system.simulate(10000);
	}
}
