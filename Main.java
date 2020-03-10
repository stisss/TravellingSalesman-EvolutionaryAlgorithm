import java.util.ArrayList;

public class Main {
    public static void main(String[] args)
    {
        long x = System.currentTimeMillis();

        String filename = "berlin11_modified.tsp";
        TSP tsp = new TSP(filename);
        ArrayList<Integer> ar = new ArrayList<>();
        for (int i = 0; i < 11; i++)
        {
            ar.add(i);
        }
        System.out.println(tsp.distance(ar));

        int populationSize = 100000;
        ArrayList<Integer> theBestGenotypeRandom = RandomSolution.solve(populationSize, tsp);
        System.out.println("---------------Random----------------");
        System.out.println(theBestGenotypeRandom);
        System.out.println(tsp.distance(theBestGenotypeRandom));

        int idxStartCity = 0;
        ArrayList<Integer> greedySolution = Greedy.solve(idxStartCity, tsp);
        System.out.println("---------------Greedy----------------");
        System.out.println(greedySolution);
        System.out.println(tsp.distance(greedySolution));


        long y = System.currentTimeMillis();
        System.out.println("Time: " + (y-x)/1000.0 + "s");
    }
}
