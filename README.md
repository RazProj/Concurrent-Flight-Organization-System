### Overview
This Java application organizes and manages flight departures and landings, with a focus on concurrency.
It ensures flights follow the correct sequence based on their request order, efficiently handling multiple operations simultaneously to maintain proper scheduling.

### Project Structure
* Main.java: The entry point of the application. It initializes the flight management system, processes user inputs or command-line instructions, and ensures the correct order of flight operations, even under concurrent conditions.
* Airport.java: Represents an airport in the system. This class manages the airport's operations, including handling the queue of flights scheduled to depart and land, ensuring they follow the correct order.
* Flight.java: Represents a flight within the system. This class contains information about each flight, including its flight number, departure airport, destination, and the sequence in which it must depart and land.
* run.bat: A batch script to compile and run the Java application easily.

### How to Run the Program
* Ensure that you have the Java Development Kit (JDK 8 or higher) installed on your system.
* Open the command line and navigate to the directory containing the program files.
* Run the program using the provided run.bat script, or by typing the following command:`java Main`.
