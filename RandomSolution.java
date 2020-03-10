import java.util.ArrayList;
import java.util.Collections;

public class RandomSolution {
    public static ArrayList<Integer> solve(int size, TSP problem) {
        double score = Double.MAX_VALUE;
        double fit;
        ArrayList<Integer> possibleSolution = new ArrayList<>();
        ArrayList<Integer> solution = new ArrayList<>();
        for(int i = 0; i < problem.getNumberOfCities(); i++) {
            possibleSolution.add(i);
        }

        for (int i = 0; i < size; i++) {
            Collections.shuffle(possibleSolution);
            if((fit = problem.distance(possibleSolution)) < score) {
                solution = new ArrayList<>(possibleSolution);
                score = fit;
            }
        }
        return solution;
    }
}
