import java.util.ArrayList;
import java.util.Collections;

public class Individual {
    private ArrayList<Integer> genotype;
    private int genLength;

    public Individual(int genLength) {
        this.genotype = new ArrayList<>();
        this.genLength = genLength;
    }

    public void randomGenotype() {
        for (int i = 0; i < genLength; i++ ) {
            genotype.add(i);
        }
        Collections.shuffle(genotype);
    }

    public ArrayList<Integer> getGenotype() {
        return genotype;
    }

    public void setGenotype(ArrayList<Integer> genotype) {
        this.genotype = genotype;
    }
}
