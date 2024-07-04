package lab3;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class genData {
    public static void main(String[] args) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(args[0]));
            Random random = new Random();
            int r = random.nextInt(2000);
            int k = random.nextInt(2000);
            int c = random.nextInt(2000);

            writer.write(r + " "+k+" "+c+"\n");
            for (int i = 0; i < r; i++) {
                for (int j = 0; j <k ; j++) {
                    writer.write(random.nextFloat(100) + " ");
                }
                writer.write("\n");
            }
            for (int i = 0; i < k; i++) {
                for (int j = 0; j < c; j++) {
                    writer.write(random.nextFloat(100) + " ");
                }
                writer.write("\n");
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
