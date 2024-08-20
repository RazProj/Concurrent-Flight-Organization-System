public class Flight extends Thread {

    // Flight number
    private final int flightNum;

    // Departure and landing airports for this flight
    private final Airport departAirport;
    private final Airport landAirport;

    // Constructor to initialize the flight with its number, departure, and landing airports
    public Flight(int flight, Airport depart, Airport land) {
        this.flightNum = flight;
        this.departAirport = depart;
        this.landAirport = land;
    }

    @Override
    public void run() {
        // Departing from the departure airport
        // Attempt to get a free departure course from the departure airport
        int freeDepartCourse = this.departAirport.depart(this.flightNum);

        // Simulating departure time
        simulateSleep(2000);

        // Free the departure runway after taking off
        departAirport.freeRunway(this.flightNum, freeDepartCourse);

        // Log the in-flight event
        System.out.println("Flight " + this.flightNum + " from airport " + departAirport.getAirportName() + " is flying...");

        // Simulating flight time
        simulateSleep(3000);

        // Landing at the landing airport
        // Attempt to get a free landing course from the landing airport
        int freeLandCourse = this.landAirport.land(this.flightNum);

        // Simulating landing time
        simulateSleep(2000);

        // Free the landing runway after landing
        landAirport.freeRunway(this.flightNum, freeLandCourse);

        // Log the completion of landing
        System.out.println("Flight " + this.flightNum + " has landed at airport " + landAirport.getAirportName() + " and freed course " + freeLandCourse);
    }

    // Method to simulate sleep time and handle InterruptedException
    private void simulateSleep(long millis) {
        try {
            sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
