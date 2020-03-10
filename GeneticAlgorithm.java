import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.Callable;

public class GeneticAlgorithm {
    private TSP problem;

    public GeneticAlgorithm(TSP problem) {
        this.problem = problem;
    }

    public ArrayList<Integer> solve(int numberOfGenerations, int popSize, int genLength, double mutationProb,
                                           double crossoverProb) {
        ArrayList<Pair> generation_x = initialisePopulation(popSize, genLength);
        ArrayList<Pair> generation_y = new ArrayList<>();
        evaluate(generation_x);
        Random generator = new Random();
        Pair theBestSolution = new Pair(Double.MAX_VALUE);

        for(int i = 0; i < numberOfGenerations; i++) {
            while(generation_y.size() < popSize) {
                Individual parent1 = selection(generation_x);
                Individual parent2 = selection(generation_x);
                Individual child;

                if (generator.nextDouble() < crossoverProb) {
                    child = crossover(parent1, parent2);
                } else {
                    child = parent1;
                }

                mutation(child, mutationProb);

                Pair child_wrapper = new Pair(child);
                evaluate(child_wrapper);
                generation_y.add(child_wrapper);

                if(theBestSolution.distance > child_wrapper.distance) {
                    theBestSolution = child_wrapper;
                }

                generation_x = new ArrayList<>(generation_y);
                generation_y.clear();
            }
        }
        return theBestSolution.individual.getGenotype();
    }


    private ArrayList<Pair> initialisePopulation(int popSize, int genLength) {
        ArrayList<Pair> population = new ArrayList<>();

        for (int i = 0; i < popSize; i++) {
            Individual newInd = new Individual(genLength);
            newInd.randomGenotype();
            population.add(new Pair(newInd));
        }

        return population;
    }

    private void evaluate(ArrayList<Pair> individuals) {
        for(int i=0;i<individuals.size(); i++) {
            evaluate(individuals.get(i));
        }
    }

    private void evaluate(Pair individual) {
        individual.setDistance(problem.distance(individual.getIndividual().getGenotype()));
    }

    private void mutation(Individual ind, double mutationProb) {

    }

    private Individual crossover(Individual parent1, Individual parent2) {
        return null;
    }

    private Individual selection(ArrayList<Pair> generation) {
        return null;
    }


    private class Pair {
        private Individual individual;
        private double distance;

        public Pair(Individual individual) {
            this.individual = individual;
        }

        public Pair(double distance) {
            this.distance = distance;
        }

        public Pair(Individual individual, double distance) {
            this.individual = individual;
            this.distance = distance;
        }

        public Pair(Pair pair) {
            this.distance = pair.distance;
            this.individual = pair.individual;
        }

        public Individual getIndividual() {
            return individual;
        }

        public void setIndividual(Individual individual) {
            this.individual = individual;
        }

        public double getDistance() {
            return distance;
        }

        public void setDistance(double distance) {
            this.distance = distance;
        }
    }
}


