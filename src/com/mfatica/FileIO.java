package com.mfatica;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.nio.file.*;
import java.util.*;


public class FileIO {
    public static String[] ReadAllLines(String filename) {
        List<String> lines = new ArrayList<String>();

        try {
            lines = Files.readAllLines(Paths.get(filename), Charset.defaultCharset());
        } finally {
            // don't need to catch anything
            // if file read fails simply return empty list
            String[] linesArray = new String[lines.size()];
            lines.toArray(linesArray);
            return linesArray;
        }
    }

    public static void WriteFileLines(String filename, List<String> lines) throws FileNotFoundException {
        PrintWriter writer = new PrintWriter(filename);
        System.out.println(filename);
        Iterator<String> iterator = lines.iterator();
        while (iterator.hasNext()) {
            writer.println(iterator.next());
        }
        writer.close();
    }
}
