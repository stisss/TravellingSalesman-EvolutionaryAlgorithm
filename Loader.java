import javafx.util.Pair;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

public class Loader {
    public static ArrayList<Double[]> loadData(String filename)
    {
        File file = new File("E:\\Dane\\Studia\\SI\\TSP\\" + filename);
        ArrayList<Double[]> ar = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String st = " ";
            Pattern pattern = Pattern.compile("\\d+.*");
            while(!((st = br.readLine().trim()).equals("EOF")))
            {
                if (pattern.matcher(st).matches())
                {
                    st = st.replaceAll("( ){2,}", " ");
                    String[] temp = st.split(" ");
                    ar.add(new Double[]{Double.valueOf(temp[1]), Double.valueOf(temp[2])});
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return ar;
    }
}
