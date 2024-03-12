public class Ticket {
    int row;
    int  seat;
    private double price;
    private Person person;

    //creating a ticket object to store all the ticket information
    public Ticket(int row, int seat, double price, Person person) {
        this.row = row;
        this.seat = seat;
        this.price = price;
        this.person = person;
    }

    //A print method to print the Data of a ticket
    public void print_ticket_info() {
        System.out.println("Row " + row + ", Seat " + seat + " purchased by \n" + person.getName() + person.getSurname() + " (" + person.getEmail() + ") for " + price + "  £.");
        System.out.println();
    }

    //Method to get the price of the tickets
    public double getPrice() {
        return price;
    }

}


