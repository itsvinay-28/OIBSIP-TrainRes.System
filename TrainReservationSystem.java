import java.util.*;

class Train {
    int trainId;
    String trainName;
    String source;
    String destination;
    int seatsAvailable;

    Train(int trainId, String trainName, String source, String destination, int seatsAvailable) {
        this.trainId = trainId;
        this.trainName = trainName;
        this.source = source;
        this.destination = destination;
        this.seatsAvailable = seatsAvailable;
    }
}


class Reservation {
    String passengerName;
    int trainId;

    Reservation(String passengerName, int trainId) {
        this.passengerName = passengerName;
        this.trainId = trainId;
    }
}


public class TrainReservationSystem {
    static Scanner scanner = new Scanner(System.in);
    static List<Train> trains = new ArrayList<>();
    static List<Reservation> reservations = new ArrayList<>();
    static int trainCounter = 1;

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n--- Train Reservation System ---");
            System.out.println("1. Add Train");
            System.out.println("2. View All Trains");
            System.out.println("3. Make Reservation");
            System.out.println("4. View Reservations");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1: addTrain();
                 break;
                case 2: viewTrains(); 
                break;
                case 3: makeReservation();
                 break;
                case 4: viewReservations();
                 break;
                case 5: System.out.println("Thank you for using TrainReservationSystem!!!"); 
                return;
                default: System.out.println("Invalid choice.");
            }
        }
    }

    
    
    static void addTrain() {
        scanner.nextLine();
        System.out.print("Enter train name: ");
        String name = scanner.nextLine();
        System.out.print("Enter source: ");
        String source = scanner.nextLine();
        System.out.print("Enter destination: ");
        String destination = scanner.nextLine();
        System.out.print("Enter number of seats: ");
        int seats = scanner.nextInt();

        Train train = new Train(trainCounter++, name, source, destination, seats);
        trains.add(train);
        System.out.println("Train added successfully!");
    }

    

    static void viewTrains() {
        if (trains.isEmpty()) {
            System.out.println("No trains available.");
            return;
        }
        System.out.println("\nAvailable Trains:");
        for (Train t : trains) {
            System.out.printf("ID: %d | Name: %s | From: %s | To: %s | Seats: %d\n",
                    t.trainId, t.trainName, t.source, t.destination, t.seatsAvailable);
        }
    }
    
    

    static void makeReservation() {
        if (trains.isEmpty()) {
            System.out.println("No trains available to reserve.");
            return;
        }

        System.out.print("Enter Train ID: ");
        int trainId = scanner.nextInt();
        Train selectedTrain = null;
        for (Train t : trains) {
            if (t.trainId == trainId) {
                selectedTrain = t;
                break;
            }
        }

        if (selectedTrain == null) {
            System.out.println("Invalid Train ID.");
            return;
        }

        if (selectedTrain.seatsAvailable <= 0) {
            System.out.println("No seats available on this train.");
            return;
        }
        
        

        scanner.nextLine();
        System.out.print("Enter passenger name: ");
        String name = scanner.nextLine();

        Reservation res = new Reservation(name, trainId);
        reservations.add(res);
        selectedTrain.seatsAvailable--;

        System.out.println("Reservation successful!");
    }

    static void viewReservations() {
        if (reservations.isEmpty()) {
            System.out.println("No reservations found.");
            return;
        }

        System.out.println("\nReservations:");
        for (Reservation r : reservations) {
            Train train = null;
            for (Train t : trains) {
                if (t.trainId == r.trainId) {
                    train = t;
                    break;
                }
            }

            if (train != null) {
                System.out.printf("Passenger: %s | Train: %s (%d)\n", r.passengerName, train.trainName, train.trainId);
            }
        }
    }
}
