public class Airport {
    private final String airportName;
    private final boolean[] flightCourses; // Tracks the availability of flight courses
    private int numOfFreeFlightCourses;
    private int departCount = 1;
    private int landCount = 1;

    // Constructor to initialize the airport with its name and number of flight courses
    public Airport(String name, int numOfCourses) {
        this.airportName = name;
        this.flightCourses = new boolean[numOfCourses];
        this.numOfFreeFlightCourses = numOfCourses;
    }

    // Method to handle the departure of a flight
    public synchronized int depart(int flightNum) {
        // Wait until there is a free course
        while (numOfFreeFlightCourses == 0) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        // Find a free course and mark it as occupied
        for (int i = 0; i < flightCourses.length; i++) {
            if (!flightCourses[i]) {
                numOfFreeFlightCourses--;
                flightCourses[i] = true;
                System.out.println("Flight " + flightNum + " at airport " + airportName + " is on departure request " + departCount);
                // Log the departure event
                System.out.println("Flight " + flightNum + " is departing from course " + i + " at " + this.airportName);
                departCount++;
                return i;
            }
        }
        return -1;
    }

    // Method to handle the landing of a flight
    public synchronized int land(int flightNum) {
        // Wait until there is a free course
        while (this.numOfFreeFlightCourses == 0) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        // Find a free course and mark it as occupied
        for (int i = 0; i < flightCourses.length; i++) {
            if (!flightCourses[i]) {
                numOfFreeFlightCourses--;
                flightCourses[i] = true;
                System.out.println("Flight " + flightNum + " at airport " + airportName + " is on landing request " + landCount);
                // Log the landing event
                System.out.println("Flight " + flightNum + " has landed on course " + i + " at " + this.airportName);
                landCount++;
                return i;
            }
        }
        return -1;
    }

    // Method to free a runway after a flight has taken off or landed
    public synchronized void freeRunway(int flightNum, int courseNum) {
        flightCourses[courseNum] = false; // Mark the course as available
        numOfFreeFlightCourses++;
        notifyAll(); // Notify other threads waiting for a free course
        // Log the freeing of the runway
        System.out.println("Flight " + flightNum + " has left, and now course " + courseNum + " at airport " + this.airportName + " is available");
    }

    // Getter method for the airport name
    public String getAirportName() {
        return this.airportName;
    }
}
