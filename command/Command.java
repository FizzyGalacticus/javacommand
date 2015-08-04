package command;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.IOException; 
import java.lang.IllegalArgumentException;
import java.lang.Process;
import java.lang.Runtime;
import java.util.ArrayList;

public class Command {
    private static Runtime runtime = Runtime.getRuntime();
    private static ArrayList<String> results = new ArrayList<String>();

    private Command() {

    }

    public static ArrayList<String> run(String command) {
        try {
            Process process = runtime.exec(command);
            String result;
            String error;

            InputStream stderr = process.getErrorStream();
            InputStream stdin = process.getInputStream();

            BufferedReader inBuffer = new BufferedReader(new InputStreamReader(stdin));
            BufferedReader errBuffer = new BufferedReader(new InputStreamReader(stderr));

            while((error = errBuffer.readLine()) != null) {
                System.out.println(error);
            }

            while((result = inBuffer.readLine()) != null) {
                results.add(result);
                System.out.println(result);
            }

            inBuffer.close();
            errBuffer.close();
        }
        catch(IOException e) {
            results.add(e.getMessage());
        }
        catch(IllegalArgumentException e) {
            results.add(e.getMessage());
        }

        return results;
    }
}