package com.mfatica;

public class Main {


    public static void main(String[] args) {
        if (args.length > 0) {
            ProcessSourceFile(args[0]);
        } else PrintUsage();
    }

    public static void ProcessSourceFile(String filename) {
        SourceFile sourceFile = new SourceFile(filename);
        try {
            sourceFile.BuildOutputFiles();
            sourceFile.WriteOutputFiles();
        } catch (Exception e) {
        }
    }

    public static void PrintUsage() {
        System.out.println("Usage:\tsicmasm <file_name>\t(ex. `sicasm main.asm`)");
    }
}
