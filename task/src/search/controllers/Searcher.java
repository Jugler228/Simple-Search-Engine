package search.controllers;

import search.models.Person;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class Searcher {
    private List<Person> personList;
    Map<String, ArrayList<Integer>> multiValueMap = new HashMap<String, ArrayList<Integer>>();

    public Searcher() {
        this.personList = new ArrayList<>();
    }

    public void findString(String text){
        List<Person> findPerson = new ArrayList<>();
        for(Person person: personList){
            if(person.getName().toLowerCase(Locale.ROOT).equals(text.toLowerCase())
                    || person.getName().toLowerCase(Locale.ROOT).contains(text))
                findPerson.add(person);
            else if(person.getSurname().toLowerCase(Locale.ROOT).equals(text.toLowerCase())
                    || person.getSurname().toLowerCase(Locale.ROOT).contains(text))
                findPerson.add(person);
            else if(person.getEmail().toLowerCase(Locale.ROOT).equals(text.toLowerCase())
                    || person.getEmail().toLowerCase(Locale.ROOT).contains(text.toLowerCase()))
                findPerson.add(person);
        }
        if(!findPerson.isEmpty()){
            for(Person person:findPerson){
                System.out.println(person.toString());
            }
        }
        else
            System.out.println("No matching people found.");
    }

    public void showAllPeople(){
        for(Person person : personList){
            System.out.println(person.toString());
        }
    }

    public void readFromFile(String filename){
        File file = new File(filename);
        if(!file.exists()){
            System.out.println("File not found.");
            return;
        }
        try(FileReader fis = new FileReader(file)){
            BufferedReader ois = new BufferedReader(fis);
            String st;
            String[] readString;
            while((st = ois.readLine()) != null){
                readString = st.split("\\s+");
                Person person = new Person();
                for(String text:readString){
                    person.createPerson(text);
                    addMap(text.toLowerCase());
                }
                personList.add(person);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            //System.out.println("File was read");
        }
    }

    public void getValueFromMap(String key){
        String formattedKey = key.toLowerCase();
        if(multiValueMap.containsKey(formattedKey)){
            ArrayList<Integer> numbers = multiValueMap.get(formattedKey);
            System.out.println(numbers.size() +" persons found:");
            for(Integer integer : numbers){
                System.out.println(personList.get(integer));
            }
        }
        else{
            System.out.println("No matching people found.");
        }
    }

    public void findAll(String...parameters){
        ArrayList<Integer> numbers = new ArrayList<>();
        if(parameters.length == 1){
            getValueFromMap(parameters[0]);
        }
        else {
            for (String str : parameters) {
                if (multiValueMap.containsKey(str.toLowerCase())) {
                    for (Integer integer : multiValueMap.get(str.toLowerCase())) {
                        numbers.add(integer);
                    }
                }
            }

            numbers = findDuplicate(numbers);
            if (numbers.isEmpty())
                System.out.println("No matching people found.");
            else {
                for (Integer integer : numbers) {
                    System.out.println(personList.get(integer));
                }
            }
        }
    }

    public void findAny(String...parameters){
        ArrayList<Integer> numbers = new ArrayList<>();
        for(String str : parameters) {
            if (multiValueMap.containsKey(str.toLowerCase())) {
                for (Integer integer : multiValueMap.get(str.toLowerCase())) {
                    numbers.add(integer);
                }
            }
        }

        numbers = removeDuplicate(numbers);
        if(numbers.isEmpty())
            System.out.println("No matching people found.");
        else {
            for (Integer integer : numbers) {
                System.out.println(personList.get(integer));
            }
        }
    }

    public void findNone(String...parameters){
        ArrayList<Integer> numbers = new ArrayList<>();
        for(String str : parameters) {
            if (multiValueMap.containsKey(str.toLowerCase())) {
                for (Integer integer : multiValueMap.get(str.toLowerCase())) {
                    numbers.add(integer);
                }
            }
        }

        numbers = removeDuplicate(numbers);
        for(int i = 0; i < personList.size(); i++){
            if(!numbers.contains(i))
                System.out.println(personList.get(i).toString());
        }
    }

    public void addMap(String key){
        if(multiValueMap.containsKey(key)){
            multiValueMap.get(key).add(personList.size());
        }
        else{
            multiValueMap.put(key,new ArrayList<>());
            multiValueMap.get(key).add(personList.size());
        }
    }

    public ArrayList<Integer> findDuplicate(ArrayList<Integer> numbers){
        ArrayList<Integer> duplicate = new ArrayList<>();
        for(int i = 0; i < numbers.size(); i++){
            for(int j = i + 1; j < numbers.size(); j++){
                if(numbers.get(i).equals(numbers.get(j))){
                    duplicate.add(numbers.get(i));
                }
            }
        }
        return duplicate;
    }

    public ArrayList<Integer> removeDuplicate(ArrayList<Integer> numbers){
        for(int i = 0; i < numbers.size(); i++){
            for(int j = i + 1; j < numbers.size(); j++){
                if(numbers.get(i).equals(numbers.get(j))){
                   numbers.remove(i);
                }
            }
        }
        return numbers;
    }
}
