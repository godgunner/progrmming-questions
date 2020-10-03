package com.dev.godgunner.programmingquestions.desiignpatterns.singleresponsibility;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

/*
    Journal class is like a real life bulleted journal where we feed in our entries and can remove them as well,
    An ArrayList is being used for easy addition and removal of entries

    Persistence class is a class that handles every persistence related method.
    A simple File IO is being used for the file specified

    Demo class is used as a driver class for all the classes above.
    Simple PSVM class.

    This file is used to represent Single Responsibility Principle which says:
    One class should have only one responsibility and not many responsibilities.
 */

public class Journal {
    private final List<String> entries = new ArrayList<>();
    private static int count = 0;

    public void addEntry(String text){
        entries.add("" + (++count) + ": " + text);
    }

    public void removeEntry(int index){
        entries.remove(index);
    }

    @Override
    public String toString() {
        return String.join(System.lineSeparator(), entries);
    }

    //Persistance related methods should be in a different class
    public void save(String filename) throws FileNotFoundException {
        try(PrintStream out = new PrintStream(filename)){
            out.println(toString());
        }
    }
}

class Persistance{
    public void saveToFile(Journal journal, String filename, boolean overwrite) throws FileNotFoundException {
        if(overwrite || new File(filename).exists())
            try(PrintStream out = new PrintStream(filename)){
                out.println(journal);
            }
    }
//    Can addd similar methods and so on for Persistance methods
//    public Journal load(String filename){}
}

class Demo{
    public static void main(String[] args) throws Exception{
        Journal j = new Journal();
        j.addEntry("I creied");
        j.addEntry("I ate");
        System.out.println(j);

        Persistance p = new Persistance();
        String filename = ".\\File\\journal.txt";
        p.saveToFile(j,filename,true);

        Runtime.getRuntime().exec("notepad.exe "+ filename);
    }
}
