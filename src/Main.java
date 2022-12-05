import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) {

        int numWords = 0;

        int numLines = 0;

        int numChars = 0;
        
        Scanner inFile;
        JFileChooser chooser = new JFileChooser();
        String line;
        Path target = new File(System.getProperty("user.dir")).toPath();
        target = target.resolve("src");
        chooser.setCurrentDirectory(target.toFile());

        try {
            if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                target = chooser.getSelectedFile().toPath();
                inFile = new Scanner(target);
                System.out.println("Selected file: " + target.getFileName());
                while (inFile.hasNextLine()) {
                    line = inFile.nextLine();
                    numLines++;
                    numChars += line.length();
                    numWords += new StringTokenizer(line, " ,").countTokens();
                }
                inFile.close();
                System.out.printf("Lines: %d\nWords: %d\nCharacters: %d\n", numLines, numWords, numChars);
            } else {
                System.out.println("Please select a file.");
                System.exit(0);
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("IOException error.");
            e.printStackTrace();
        }
    }
}