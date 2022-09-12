package search.models;

public class Person {
    private String name = "";
    private String surname = "";
    private String email = "";

    public Person() {
    }

    public Person(String name, String surname, String email) {
        this.name = name;
        this.surname = surname;
        this.email = email;
    }

    public Person(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }

    public void createPerson(String text){
        if(name.isEmpty())
            name = text;
        else if(surname.isEmpty())
            surname = text;
        else if(email.isEmpty())
            email = text;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String toString() {
        if(email.isEmpty())
            return name + " "+ surname;
        else if(surname.isEmpty())
            return name;
        else
            return name + " "+ surname + " " + email;
    }
}
