public class Person {
    private String name;
    private String surname;
    private String email;

    //creating a person object
    public Person(String name, String surname, String email) {
        this.name = name;
        this.surname = surname;
        this.email = email;
    }

    //Get method to Get the username
    public String getName() {
        return name;
    }
    // Getting the Surname of the user
    public String getSurname() {
        return surname;
    }
    //Getting the email of the user
    public String getEmail() {
        return email;
    }
}