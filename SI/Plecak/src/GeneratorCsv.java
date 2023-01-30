import java.io.FileWriter;
import java.io.IOException;

public class GeneratorCsv {
    public static void main(String[] args) {
        int lowerBound = 10;
        int range = 25000;
        final String separator = ";";
        try {
            FileWriter txt = new FileWriter("dane.csv");
            txt.write("item_id;item_weight;item_value\n");
            for(int i = 0; i<1001;i++) {
                int randomWeight = (int)(Math.random() * (range/1000)) + lowerBound;
                int randomValue = (int)(Math.random() * range) + lowerBound;
                txt.write(i + separator + randomWeight + separator + randomValue+"\n");
            }
            txt.close();
            System.out.print("Success!");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    }

