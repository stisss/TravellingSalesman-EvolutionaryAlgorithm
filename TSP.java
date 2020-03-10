import java.util.*;

public class TSP {
    private ArrayList<Double[]> cities;
    private int numberOfCities;

    public TSP(String filename) {
        this.cities = Loader.loadData(filename);
        this.numberOfCities = cities.size();
    }


    public double euclid_distance(Double[] v1, Double[] v2) {
        return Math.sqrt(Math.pow(v1[0]-v2[0], 2) + Math.pow(v1[1]-v2[1], 2));
    }

    public double distance(ArrayList<Integer> genotype) {
        double acc = 0;
        for(int i = 0; i < numberOfCities; i++) {
            acc += euclid_distance(cities.get(genotype.get(i)), cities.get(genotype.get((i+1)%numberOfCities)));
        }
        return acc;
    }


    public int getNumberOfCities() {
        return numberOfCities;
    }

    public ArrayList<Double[]> getCities() {
        return cities;
    }
}
