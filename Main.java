import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        long x = System.currentTimeMillis();

        String filename = "berlin11_modified.tsp";
        TSP tsp = new TSP(filename);

        int iterations = 10000;
        int popSize = 100;
        int numberOfCities = tsp.getNumberOfCities();
        double mutationProb = 0.01;
        double crossoverProb = 0.9;
        double pressure = 0.3;

        GeneticAlgorithm ga = new GeneticAlgorithm(tsp);
        ArrayList<Integer> gaSolution = ga.solve(iterations, popSize, numberOfCities, mutationProb, crossoverProb, pressure);
        System.out.println(gaSolution);
        System.out.println(tsp.distance(gaSolution));

/*        int populationSize = 100000;
        ArrayList<Integer> theBestGenotypeRandom = RandomSolution.solve(populationSize, tsp);
        System.out.println("---------------Random----------------");
        System.out.println(theBestGenotypeRandom);
        System.out.println(tsp.distance(theBestGenotypeRandom));

        int idxStartCity = 0;
        ArrayList<Integer> greedySolution = Greedy.solve(idxStartCity, tsp);
        System.out.println("---------------Greedy----------------");
        System.out.println(greedySolution);
        System.out.println(tsp.distance(greedySolution));*/

        long y = System.currentTimeMillis();
        System.out.println("Time: " + (y-x)/1000.0 + "s");
    }
}
