package com.oracle.challenge;

import com.oracle.challenge.util.Constants;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;

public class BulldozerApp {

    static List<char[]> siteMap = new ArrayList<>();
    static Queue<StringBuilder> commands = new ArrayDeque<>();
    static Set<String> visitedBlocks = new HashSet<>();

    static int currXPos = -1;
    static int currYPos = -1;
    static int direction = 0;
    static int height = 0;
    static int width = 0;

    static int communicationOverhead;
    static int fuelUsage;

    public static void main(String[] args) {
        System.out.println(Constants.TEST);
        String fileName = args[0];
        if(null != fileName) {
            readInputFile(fileName);

        } else {
            System.out.println("Filename cannot be blank.");
        }
    }

    private static void readInputFile(String fileName) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))){
            String line;
            while((line = reader.readLine()) != null) {
                if(height == 0)
                    width = line.length();
                else if(width != line.length())
                    throw new Exception("Row "+(height+1)+" does not have same number of characters.");
                height++;
                siteMap.add(line.toCharArray());
            }
            printInitialText();
            startSimulation();
        } catch (Exception e) {
            System.out.println("Error occurred while reading from the file: " + fileName);
            e.printStackTrace();
        }
    }

    private static void startSimulation() throws Exception {
        Scanner scanner = new Scanner(System.in);
        String input = "";
        while(!input.equalsIgnoreCase("q")) {
            printCurrPos();
            System.out.print("(l)eft, (r)ight, (a)dvance <n>, (q)uit: ");
            input = scanner.nextLine();
            processInput(input);
        }
//        printSummary();
    }

    private static void processInput(String input) throws Exception {
        input = input.trim().toLowerCase();
        saveCommand(input);
        if(!input.startsWith("q"))
            communicationOverhead++;
        if(input.startsWith("a")) {
            if(input.length() < 3)
                throw new Exception("Input is missing number of places to advance.");
//            calculateCost(Integer.parseInt(input.charAt(2)+""));

        } else if (input.startsWith("l"))
            direction = direction == 0 ? 3 : (direction-1) % 4;
        else if (input.startsWith("r"))
            direction = (direction+1) % 4;
    }

    private static void saveCommand(String input) {
        input = input.trim().toLowerCase();
        if(input.startsWith("a")) {
            commands.add(new StringBuilder("advance " + input.charAt(2) + ", "));
        } else if (input.startsWith("l"))
            commands.add(Constants.TURN_LEFT);
        else if (input.startsWith("r"))
            commands.add(Constants.TURN_RIGHT);
        else
            commands.add(new StringBuilder("quit\n\n"));
    }

    private static void printSpaces(int count) {
        for(int i=0; i<count ; i++)
            System.out.print(" ");
    }

    private static void printCurrPos() {
        System.out.println("x:"+currXPos+" y:"+currYPos+" dir: "+direction);
    }

    private static void printInitialText() {
        System.out.println("Welcome to the site clearing simulator. This is a map of the site:\n");
        siteMap.forEach(System.out::println);
        System.out.println("\nThe bulldozer is currently located at the Northern edge of the " +
                "site, immediately to the West of the site, and facing East.");
    }

}
