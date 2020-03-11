import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.Collections;


public class Tests {
    String filename = "berlin11_modified.tsp";
    TSP tsp = new TSP(filename);
    GeneticAlgorithm ga = new GeneticAlgorithm(tsp);

    @Test
    public void crossover() {


        Individual ind1 = new Individual(10);
        Individual ind2 = new Individual(10);
        ind1.randomGenotype();
        ind2.randomGenotype();
        System.out.println(ind1.getGenotype());
        System.out.println(ind2.getGenotype());
        Individual child = ga.crossover(ind1, ind2);
        System.out.println(child.getGenotype());
    }

    @Test
    public void mutation() {
        Individual ind1 = new Individual(10);
        ind1.randomGenotype();
        System.out.println(ind1.getGenotype());
        ga.mutation(ind1, 0.2);
        System.out.println(ind1.getGenotype());
    }

    @Test
    public void tourney() {
        ArrayList<GeneticAlgorithm.Pair> population = ga.initialisePopulation(10,10);
        for(int i = 0; i < population.size(); i++) {
            population.get(i).getIndividual().randomGenotype();
            ga.evaluate(population.get(i));
            System.out.println(population.get(i).getDistance() + ", " + population.get(i).getIndividual().getGenotype());
        }
        System.out.println();

        GeneticAlgorithm.Pair x = ga.tourney(population, 0.3);
        GeneticAlgorithm.Pair y = ga.tourney(population, 1.0);

        ga.evaluate(x);
        ga.evaluate(y);

        System.out.println();
        System.out.println(x.getDistance() + ", " + x.getIndividual().getGenotype());
        System.out.println(y.getDistance() + ", " + y.getIndividual().getGenotype());
    }

    @Test
    public void roulette() {
        ArrayList<GeneticAlgorithm.Pair> population = ga.initialisePopulation(10,10);
        for(int i = 0; i < population.size(); i++) {
            population.get(i).getIndividual().randomGenotype();
            ga.evaluate(population.get(i));
            System.out.println(population.get(i).getDistance() + ", " + population.get(i).getIndividual().getGenotype());
        }
        System.out.println();

        GeneticAlgorithm.Pair x = ga.roulette(population, 0.4);

        System.out.println();
        ga.evaluate(x);

        System.out.println();
        System.out.println(x.getDistance() + ", " + x.getIndividual().getGenotype());
    }

    @Test
    public void swappy() {
        ArrayList<Integer> ar = new ArrayList<>();
        ar.add(6);
        ar.add(5);
        ar.add(4);
        ar.add(3);
        ar.add(2);
        Individual i = new Individual(5);
        i.setGenotype(ar);

        Collections.swap(i.getGenotype(), 0, 4);
        System.out.println(i.getGenotype());
    }

}
