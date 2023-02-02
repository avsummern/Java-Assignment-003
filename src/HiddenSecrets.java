import com.drew.imaging.ImageProcessingException;
import com.drew.metadata.Metadata;
import com.drew.metadata.Directory;
import com.drew.metadata.Tag;
import com.drew.imaging.ImageMetadataReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Scanner;
import java.nio.file.Path;
// PUT YOUR IMPORTS HERE

public class HiddenSecrets {
    public static void getHiddenSecrets(File file) {
        try {
            Metadata metadata = ImageMetadataReader.readMetadata(
                    new FileInputStream(file)
            );
            for (Directory directory : metadata.getDirectories()) {
                for (Tag tag : directory.getTags()) {
                    System.out.format("[%s] - %s = %s%n",
                            directory.getName(), tag.getTagName(), tag.getDescription());
                }
                if (directory.hasErrors()) {
                    for (String error : directory.getErrors()) {
                        System.err.format("ERROR: %s%n", error);
                    }
                }
            }
        } catch (FileNotFoundException fnfe) {
            System.out.println("That file does not exist.");
        } catch (IOException ioe) {
            System.out.println("Problem reading from file stream.");
        } catch (ImageProcessingException ipe) {
            System.out.println("Failed to process the image meta-data");
        }
    }

    public static void main(String[] args) {
        // Put your code to request a file path,
        // read in a string from System.in,
        Scanner in = new Scanner(System.in);
        System.out.println("Please enter the file name (Ex. images/imagename.png)");
        String pathName = in.nextLine();

        // convert that string into A Path type using Paths class,
        Path path = (Path)Paths.get(pathName);
        Path absPath = path.toAbsolutePath();
        System.out.println(absPath);

        // convert path into file for getHiddenSecrets
        File convertedPath = absPath.toFile();
        // and call the getHiddenSecrets method to get the file's meta-data
        getHiddenSecrets(convertedPath);
        // HERE
    }
}
