import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

public class GeneticAlgorithm {
    private TSP problem;

    public GeneticAlgorithm(TSP problem) {
        this.problem = problem;
    }

    public ArrayList<Integer> solve(int numberOfGenerations, int popSize, int genLength, double mutationProb,
                                    double crossoverProb, double pressure) {
        ArrayList<Pair> generation_x = initialisePopulation(popSize, genLength);
        ArrayList<Pair> generation_y = new ArrayList<>();
        evaluate(generation_x);
        Random generator = new Random();
        Pair theBestSolution = new Pair(Double.MAX_VALUE);

        for (int i = 0; i < numberOfGenerations; i++) {
            while (generation_y.size() < popSize) {
                Pair parent1 = tourney(generation_x, pressure);
                Pair parent2 = tourney(generation_x, pressure);
                Pair child = new Pair(0);

                if (generator.nextDouble() < crossoverProb) {
                    child.individual = crossover(parent1.individual, parent2.individual);
                } else {
                    child.individual = parent1.individual;
                }

                mutation(child.individual, mutationProb);
                evaluate(child);
                generation_y.add(child);

                if (theBestSolution.distance > child.distance) {
                    theBestSolution = child;
                }

                generation_x = new ArrayList<>(generation_y);
                generation_y.clear();
            }
        }
        return theBestSolution.individual.getGenotype();
    }


    public ArrayList<Pair> initialisePopulation(int popSize, int genLength) {
        ArrayList<Pair> population = new ArrayList<>();

        for (int i = 0; i < popSize; i++) {
            Individual newInd = new Individual(genLength);
            newInd.randomGenotype();
            population.add(new Pair(newInd));
        }

        return population;
    }

    public void evaluate(ArrayList<Pair> individuals) {
        for (int i = 0; i < individuals.size(); i++) {
            evaluate(individuals.get(i));
        }
    }

    public void evaluate(Pair individual) {
        individual.setDistance(problem.distance(individual.getIndividual().getGenotype()));
    }

    public void mutation(Individual ind, double mutationProb) {
        int length = ind.getGenotype().size();
        ArrayList<Integer> newGenotype = new ArrayList<>(ind.getGenotype());
        Random generator = new Random();
        int swapIdx;

        for (int i = 0; i < length; i++) {
            if (mutationProb > generator.nextDouble()) {
                do{ swapIdx = generator.nextInt(length); } while (i == swapIdx);
                Collections.swap(newGenotype, i, swapIdx);
            }
        }
        ind.setGenotype(newGenotype);
    }

    public Individual crossover(Individual parent1, Individual parent2) {
        /* Ordered Crossover */
        int cut1, cut2;
        int length = parent1.getGenotype().size();
        Random generator = new Random();
        Individual child = new Individual(length);

        do {
            cut1 = generator.nextInt(length);
            cut2 = generator.nextInt(length);
        } while (cut1 >= cut2);

        Integer[] fromParent1 = new Integer[length];
        ArrayList<Integer> fromParent2 = new ArrayList<>(parent2.getGenotype());

        for (int i = cut1; i < cut2; i++) {
            fromParent1[i] = parent1.getGenotype().get(i);
        }

        ArrayList<Integer> newGenotype = new ArrayList<>(Arrays.asList(fromParent1));
        fromParent2.removeAll(newGenotype);

        int idx = 0;
        for (; idx < cut1; idx++) {
            newGenotype.set(idx, fromParent2.get(idx));
        }
        for (int i = cut2; i < length; i++, idx++) {
            newGenotype.set(i, fromParent2.get(idx));
        }

        child.setGenotype(newGenotype);
        return child;
    }

    public Pair tourney(ArrayList<Pair> generation, double pressure) {
        Random generator = new Random();
        Pair winner = new Pair(Double.MAX_VALUE);
        Pair temp = null;
        int tourneySize = (int)(pressure * generation.size());
        for(int i = 0; i < tourneySize; i++) {
            temp = generation.get(generator.nextInt(generation.size()));
            if(winner.distance > temp.distance) {
                winner = temp;
            }
        }
        return temp;
    }


    class Pair {
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


