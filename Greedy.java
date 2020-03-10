import java.util.ArrayList;

public class Greedy {
    public static ArrayList<Integer> solve(int startCity, TSP problem) {
        ArrayList<Double[]> unvisitedCities = new ArrayList<>(problem.getCities());
        ArrayList<Integer> result = new ArrayList<>();
        result.add(startCity);
        unvisitedCities.remove(startCity);
        double minDist = Double.MAX_VALUE;
        double tempDist;
        int minIdx = -1;
        int temp_size;
        int localIndex = -1;
        int numberOfCities = problem.getNumberOfCities();

        for(int i = 1; i < numberOfCities; i++) {
            temp_size = unvisitedCities.size();
            for(int j=0; j<temp_size; j++) {
                tempDist = problem.euclid_distance(problem.getCities().get(result.get(i-1)), unvisitedCities.get(j));
                if(tempDist < minDist) {
                    minDist = tempDist;
                    localIndex = j;
                    minIdx = problem.getCities().indexOf(unvisitedCities.get(j));
                }
            }
            result.add(minIdx);
            unvisitedCities.remove(localIndex);
            minIdx = -1;
        }
        return result;
    }
}
