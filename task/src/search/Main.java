package search;

import search.controllers.Searcher;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(System.in));
        Searcher searcher = new Searcher();
        if(args.length != 0) {
            if (args[0].equals("--data")) {
                if (args.length >= 2) {
                    searcher.readFromFile(args[1]);
                }
            }
        }
        searcher.readFromFile("hello.txt");
        int choice;
        while (true){
            System.out.println();
            System.out.println("=== Menu ===");
            System.out.println("1. Find a person");
            System.out.println("2. Print all people");
            System.out.println("0. Exit");
            choice = Integer.parseInt(reader.readLine());
            switch (choice){
                case 1:
                    System.out.println("Select a matching strategy: ALL, ANY, NONE");
                    String matchingStrategy = reader.readLine();

                    String[] findingPerson;
                    switch (matchingStrategy){
                        case "ANY":
                            System.out.println("Enter a name or email to search all suitable people.");
                            findingPerson = reader.readLine().split("\\s+");
                            searcher.findAny(findingPerson);
                            break;
                        case "ALL":
                            System.out.println("Enter a name or email to search all suitable people.");
                            findingPerson = reader.readLine().split("\\s+");
                            searcher.findAll(findingPerson);
                            break;
                        case "NONE":
                            System.out.println("Enter a name or email to search all suitable people.");
                            findingPerson = reader.readLine().split("\\s+");
                            searcher.findNone(findingPerson);
                            break;

                        default:
                            System.out.println("Incorrect option! Try again.");
                    }
                    break;
                case 2:
                    System.out.println("=== List of people ===");
                    searcher.showAllPeople();
                    break;
                case 0:
                    System.out.println("Bye!");
                    System.exit(1);
                default:
                    System.out.println("Incorrect option! Try again.");
            }
        }
    }

}

