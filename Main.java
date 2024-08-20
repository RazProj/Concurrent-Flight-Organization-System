import java.security.SecureRandom;

public class Main {
    public static void main(String[] args) {
        Airport jfk = new Airport("JFK", 3);
        Airport lut = new Airport("LUT", 3);
        Airport[] airports = {jfk, lut};

        // Creating the flights array using the new method
        Flight[] flights = createFlights(airports);

        // Starting all flight threads
        for (Flight flight : flights) {
            flight.start();
        }

        // Waiting for all flight threads to finish
        for (Flight flight : flights) {
            try {
                flight.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("\n------------------------------\nAll flights have been completed.");
    }

    // Method to create an array of flights with random departure and landing airports
    private static Flight[] createFlights(Airport[] airports) {

        // The length is set to 10 as required by the task,
        // but it can be adjusted by adding another parameter to the function that represents the number of flights.
        Flight[] flights = new Flight[10];
        SecureRandom generator = new SecureRandom();

        for (int i = 0; i < flights.length; i++) {
            // Initialize departure and landing airport indices to -1
            int departAirport = -1;
            int landAirport = -1;

            // Ensure that the departure and landing airports are different
            while (departAirport == landAirport) {
                // Randomly select one of the airports for departure
                departAirport = generator.nextInt(airports.length);
                // Randomly select one of the airports for landing
                landAirport = generator.nextInt(airports.length);
            }

            // Create a new Flight object with the current index, selected departure and landing airports
            flights[i] = new Flight(i + 1, airports[departAirport], airports[landAirport]);
        }


        return flights;
    }
}
