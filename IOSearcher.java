package test;


import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class IOSearcher {

    public static boolean search (String word , String...fileNames)
    {
        Stream<String> stream;
        for (String file : fileNames)
        {
            try {
                stream = Files.lines(Paths.get(file));
                if (stream.anyMatch(lines -> lines.contains(word))) {
                    return true;
                }

            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }
        return false;
    }
}
