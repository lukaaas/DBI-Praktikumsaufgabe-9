package Entwurf;

import java.io.*;

public class SetupBenchmark {

    public static String[] getProperties(String s) throws IOException {

        String [] propr = new String[3];
        FileReader f = new FileReader("properties");
        BufferedReader br = new BufferedReader(f);
        int index = 0;
        String line;

        while ((line = br.readLine()) != null) {
            propr[index] = line;
            index++;
        }
        return propr;
    }
}
