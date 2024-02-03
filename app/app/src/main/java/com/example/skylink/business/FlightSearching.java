import java.util.*;

public class FlightSearching {

    private static final Map<String, String[]> flights = new HashMap<String, String[]>();

    static {
        flights.put("AC100", new String[]{"YYZ", "YUL", "1000", "08:00", "10:30"});// Flight ID, Departure,Arrival,Distance in km, dept time, arr time.
        flights.put("AC200", new String[]{"YUL", "YVR", "2000", "12:00", "16:00"});
        flights.put("AC300", new String[]{"YVR", "YYC", "1500", "18:00", "20:30"});
        flights.put("AC400", new String[]{"YYZ", "YVR", "3000", "09:00", "12:00"});
        flights.put("AC500", new String[]{"YUL", "YYC", "1300", "11:30", "14:00"});
        flights.put("AC600", new String[]{"YYZ", "YYC", "2500", "10:00", "14:30"});
        flights.put("AC700", new String[]{"YYC", "ABC", "1000", "11:00", "14:30"});
        // We can add more flights here.
    }

    public static List<String> searchFlights(String origin, String destination) {
        List<String> results = new ArrayList<>();
        Set<String> visited = new HashSet<>();
        DepthFirstSearch(origin, destination, new ArrayList<>(), results, visited);
        return results;
    }//Search for available flights using depth first search

    private static void DepthFirstSearch(String current, String destination, List<String> path, List<String> results, Set<String> visited) {
        if (current.equals(destination)) {
            results.add(buildPathString(path));
            return;
        }

        visited.add(current);
        for (Map.Entry<String, String[]> entry : flights.entrySet()) {
            String flightNumber = entry.getKey();
            String[] flightInfo = entry.getValue();
            String departure = flightInfo[0];
            String arrival = flightInfo[1];
            if (departure.equals(current) && !visited.contains(arrival)) {
                path.add(flightNumber);
                DepthFirstSearch(arrival, destination, path, results, visited);
                path.remove(path.size() - 1);
            }
        }
        visited.remove(current);
    }//This is the searching algorithm being used.

    private static String buildPathString(List<String> path) {
        StringBuilder sb = new StringBuilder();
        int totalDistance = 0;
        for (String flight : path) {
            String[] flightInfo = flights.get(flight);
            sb.append(flight).append(" -> ");
            totalDistance += Double.parseDouble(flightInfo[2]);
        }
        sb.setLength(sb.length() - 4);
        sb.append("\nTotal Distance: ").append(totalDistance).append(" km");
        sb.append("\nTotal Duration: ").append(calculateDuration(path)).append(" hours");

        // Price calculation
        double businessRate = totalDistance / calculateDuration(path) * 0.25;
        double economyRate = totalDistance / calculateDuration(path) * 0.15;
        sb.append("\nBusiness Class Rate: $").append(businessRate);
        sb.append("\nEconomy Class Rate: $").append(economyRate);

        return sb.toString();
    }

    private static double calculateDuration(List<String> path) {
        String previousArrivalTime = "";
        float totalDuration = 0;
        for (String flight : path) {
            String[] flightInfo = flights.get(flight);
            String departureTime = flightInfo[3];
            String arrivalTime = flightInfo[4];
            if (!previousArrivalTime.isEmpty()) {
                totalDuration += calculateTimeDifference(previousArrivalTime, departureTime);
            }
            totalDuration += calculateTimeDifference(departureTime, arrivalTime);
            previousArrivalTime = arrivalTime;
        }
        return totalDuration;
    }

    private static double calculateTimeDifference(String startTime, String endTime) {
        String[] startParts = startTime.split(":");
        String[] endParts = endTime.split(":");
        int startHour = Integer.parseInt(startParts[0]);
        int startMinute = Integer.parseInt(startParts[1]);
        int endHour = Integer.parseInt(endParts[0]);
        int endMinute = Integer.parseInt(endParts[1]);
        double duration = (endHour - startHour) + ((endMinute - startMinute) / 60.0);
        if (duration < 0) {
            duration += 24; // Add one day.
        }
        return duration;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("From：");
        String origin = scanner.nextLine().trim();

        System.out.println("To：");
        String destination = scanner.nextLine().trim();

        List<String> results = searchFlights(origin, destination);
        if (results.isEmpty()) {
            System.out.println("No flights from " + origin + "to " + destination);
        } else {
            System.out.println("From " + origin + " to " + destination);
            for (String result : results) {
                System.out.println("Path: " + result);
            }
        }
    }
}
