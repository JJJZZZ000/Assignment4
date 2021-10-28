package Question1;

import java.io.*;
import java.util.Random;

public class RemoveBlank {
    public static void remove_blank(String fileName) {
        // generate a random number to define the name of the temporary file;
        Random r = new Random();

        String temp_fileName;
        File temp_file;
        StringBuilder sb = new StringBuilder();
        sb.append("temp_");
        // create the name of temporary file using Random class, to make sure the temporary has the unique name;
        do{
            for(int i = 0; i < 3; i++){
                sb.append(r.nextInt(10));
            }
            sb.append(".txt");
            temp_fileName = sb.toString();
            temp_file = new File(temp_fileName);
        } while(temp_file.exists());

        try {
            temp_file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // read the information in the file and copy it without extra blank to the temporary file line by line;
        try (BufferedReader bufInput = new BufferedReader(new FileReader(fileName));
             BufferedWriter bufOutput = new BufferedWriter(new FileWriter(temp_fileName))) {
            String line = "";
            while((line = bufInput.readLine()) != null){
                // remove the extra line, replace the multiple blank with single space;
                // use the function of String;
                bufOutput.write(line.replaceAll(" {2,}", " "));
                bufOutput.newLine();
            }
        } catch (FileNotFoundException f) { // if some exception occurs like file cannot be fund, temporary file will be deleted and exit the program;
            System.out.println("File not found: " + f);
            temp_file.delete();
            System.exit(0);
        } catch (IOException e) {
            System.out.println("Exception: " + e);
            temp_file.delete();
            System.exit(0);
        }
        // copy the information from temporary file to the original file
        try (BufferedReader bufInput = new BufferedReader(new FileReader(temp_fileName));
             BufferedWriter bufOutput = new BufferedWriter(new FileWriter(fileName))) {
            String line = "";
            while((line = bufInput.readLine()) != null){
                bufOutput.write(line.replaceAll(" {2,}", " "));
                bufOutput.newLine();
            }
        } catch (FileNotFoundException f) {
            System.out.println("File not found: " + f);
            System.exit(0);
        } catch (IOException e) {
            System.out.println("Exception: " + e);
            System.exit(0);
        }
        // delete the temporary file
        temp_file.delete();
    }
}
