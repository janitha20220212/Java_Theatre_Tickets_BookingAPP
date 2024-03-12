
/*
I declare that my work contains no examples of misconduct, such as plagiarism, or-collusion.
Any code taken from other sources is referenced within my code solution.

Name: Janitha Prabodha
Student ID: w1953789
Date: 2022/03/22

*/

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

//----------------------------------------------------------------------------------------------------------------------
public class Theatre {

    //COLOR CODES
//-------------------------------------------------------
    public static final String GREEN = "\033[0;32m";   // GREEN
    public static final String RED = "\033[0;31m";     // RED
    public static final String BLUE = "\033[0;34m";    // BLUE
    public static final String RESET = "\033[0m";

    //REFERENCE [1]
    //https://www.geeksforgeeks.org/how-to-print-colored-text-in-java-console/

    //SEAT LENGTH VARIABLE
//-------------------------------------------------------
    static final int FIRST_ROW_SEATS = 12;
    static final int SECOND_ROW_SEATS = 16;
    static final int THIRD_ROW_SEATS = 20;

    //ROW ARRAYS
//-------------------------------------------------------
    static int[] first_row = new int[12];
    static int[] second_row = new int[16];
    static int[] third_row = new int[20];
    static ArrayList<Ticket> tickets_array = new ArrayList<>();

/*----------------------------------------------------------------------------------------------------------------------
                                                   MAIN CODE
------------------------------------------------------------------------------------------------------------------------*/
    public static void main(String[] arg) {

        //Printing the Welcome Message At the beginning of the program
        System.out.println("\n" + BLUE +"**********************");
        System.out.println("Welcome to New Theatre");
        System.out.println("**********************" + RESET);

        //Looping the main program So that the User can input multiple Records
        boolean quit = false;
        while (!quit) {

            //try-Catch block to avoid any input data type Errors
            try {

                //printing the menu
                print_menu();

                // User selects the menu option but entering the corresponding integer
                System.out.println("Select Option: ");
                Scanner scanner = new Scanner(System.in);
                int menu_input = scanner.nextInt();

                //navigating the menu using switch case statement
                switch (menu_input) {

                    //Buying a Ticket ---------------------------------------------------
                    case 1 -> buy_ticket();
                    //Printing the seats------------------------------------------------
                    case 2 -> {
                        System.out.println(BLUE + "      ***********");
                        System.out.println("      ** STAGE **");
                        System.out.println("      ***********" + RESET);

                        print_seating_area(first_row, "     ", FIRST_ROW_SEATS);
                        print_seating_area(second_row, "   ", SECOND_ROW_SEATS);
                        print_seating_area(third_row, " ", THIRD_ROW_SEATS);
                    }

                    //Cancelling a ticket -------------------------------------------------
                    case 3 -> cancel_ticket();
                    //printing the available seats----------------------------------------
                    case 4 -> {
                        show_available_seats(1, first_row);
                        show_available_seats(2, second_row);
                        show_available_seats(3, third_row);
                    }
                    //saving the data to a file--------------------------------------------
                    case 5 -> {
                        System.out.println("Updating Data File...");
                        FileWriter new_writer = new FileWriter("data.txt");
                        save_to_file(first_row, new_writer);
                        new_writer.write("\n");

                        save_to_file(second_row, new_writer);
                        new_writer.write("\n");

                        save_to_file(third_row, new_writer);
                        new_writer.write("\n");

                        new_writer.close();
                        System.out.println(GREEN + "Data file updated Successfully" + RESET);
                    }

                    //loading hte save data from a file------------------------------------
                    case 6 -> load_from_file();
                    //Printing user information and Total price----------------------------
                    case 7 -> print_ticket_information();
                    //Sorting tickets buy price-------------------------------------------
                    case 8 -> sort_tickets();

                    //Exiting the program--------------------------------------------------
                    case 0 -> {
                        System.out.println("Quitting the program");
                        quit = true;
                    }
                    //User input validation------------------------------------------------
                    default -> System.out.println(RED + "Invalid option, please try again" + RESET);
                }

            } catch (Exception e) {
                System.out.println(RED + "Integer Required. Please select an option from the menu" + RESET);

            }
        }
    }



    //METHOD TO PRINT THE MENU
//----------------------------------------------------------------------------------------------------------------------
    private static void print_menu() {

        // Printing the Menu of the program
        System.out.println("""
                      
                    ------------ \s
                        MENU \s
                    -------------------------------------------------
                    Please select an option:\s
                    1) Buy a ticket\s
                    2) Print seating area\s
                    3) Cancel ticket\s
                    4) List available seats\s
                    5) Save to file\s
                    6) Load from file\s
                    7) Print ticket information and total price\s
                    8) Sort tickets by price\s
                     0) Quit\s
                    -------------------------------------------------                   \s
                    """);
    }

    //METHOD FOR BUYING A TICKET
//----------------------------------------------------------------------------------------------------------------------
    static void buy_ticket() {

        //Getting the desired row number from the user
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter row number (1 to 3): ");
        int row_number = scanner.nextInt();

        //Validation of the row number (1 to 3)
        if (row_number == 1) {
            buy_ticket_validation(FIRST_ROW_SEATS, first_row, row_number);

        } else if (row_number == 2) {
            buy_ticket_validation(SECOND_ROW_SEATS, second_row, row_number);

        } else if (row_number == 3) {
            buy_ticket_validation(THIRD_ROW_SEATS, third_row, row_number);

        } else {
            //Error message displayed when user inputs an invalid row number
            System.out.println(RED+ "This row does not exist, Please Select (1 - 3)"+ RESET);
        }
    }

    //INNER METHOD FOR BUY TICKETS VALIDATION
//----------------------------------------------------------------------------------------------------------------------
    static void buy_ticket_validation(int number_of_seats , int[] row_array, int row_number) {

        Scanner scanner = new Scanner(System.in);
        //Getting the desired seat number from the user
        System.out.print("Enter Seat Number (1 to "+ number_of_seats + "): ");
        int seat_number = scanner.nextInt();
        //Seat number validation
        if (seat_number <= number_of_seats && seat_number >= 1) {

            //Code for the first row when the user selects Row 1
            if (row_array[seat_number - 1] == 0) {

                //Getting Username
                System.out.print("Enter Name: ");
                String user_name = scanner.next();

                //Getting the user surname
                System.out.print("Enter Surname: ");
                String user_surname = scanner.next();

                //Getting user Email
                System.out.print("Enter Email: ");
                String user_email = scanner.next();

                //Getting the price of the ticket
                System.out.print("Enter Price: ");
                double price = scanner.nextDouble();
                scanner.nextLine();

                //Person object. Continued in the Person.java
                Person person_object = new Person(user_name, user_surname, user_email);
                //Ticket Object. Continued in the Ticket.java
                Ticket ticket_object = new Ticket(row_number, seat_number, price, person_object);

                //Adding the tickets to an array called Tickets Array
                tickets_array.add(ticket_object);
                row_array[seat_number - 1] = 1;
                System.out.println(GREEN +"\nTicket Purchased Successfully!" + RESET);

            } else {

                //Error message when the user selects an occupied seat
                System.out.println(RED+ "The seat is occupied."+ RESET);
            }

        } else {

            //Error message When the user selects a non-existing seat
            System.out.println(RED+ "This seat does not exist, Please Select (1 - 12)"+ RESET);
        }
    }

    //METHOD TO PRINTING THE SEATING AREA
//----------------------------------------------------------------------------------------------------------------------
    static void print_seating_area(int[] array_name, String spacing, int number_of_seats) {

        System.out.print(spacing);
        for (int i = 0; i < array_name.length; i++) {

            if (array_name[i] == 0) {
                System.out.print("O");
            } else {
                System.out.print("X");
            }
            if (i == (number_of_seats
                    /2) - 1 ) {
                System.out.print(" ");
            }
        }
        System.out.println();
    }

    //METHOD TO CANCEL A TICKET
//----------------------------------------------------------------------------------------------------------------------
    public static void cancel_ticket() {

        Scanner scan = new Scanner(System.in);
        System.out.println("Enter row number: ");
        int row_number = scan.nextInt();

        if (row_number == 1) {
            cancel_ticket_validation( FIRST_ROW_SEATS, first_row, row_number );

        } else if (row_number == 2) {
            cancel_ticket_validation( SECOND_ROW_SEATS, second_row, row_number );

        } else if (row_number == 3) {
            cancel_ticket_validation( THIRD_ROW_SEATS, third_row, row_number );

        }else {
            System.out.println(RED + "This row does not exist, Please Select (1 - 12)" + RESET);
        }
    }

    //INNER METHOD TO CANCEL A TICKET VALIDATION
//----------------------------------------------------------------------------------------------------------------------
    public static void cancel_ticket_validation(int number_of_seats , int[] row_array, int row_number) {
                                                 //seat number parameter , which array parameter and the row Number
        //Getting the seat number from the user
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter Seat Number (1 to " + number_of_seats + ")");
        int seat_number = scanner.nextInt();

        //validation of the seat number
        if (seat_number <= number_of_seats && seat_number >= 1) {

            //For loop to add the ticket object to the tickets array
            for (Ticket ticket : tickets_array) {
                if (ticket.row == row_number && ticket.seat == seat_number) {
                    tickets_array.remove(ticket);
                    break;
                }
            }

            //Cancelling the ticket
            if (row_array[seat_number - 1] == 1) {
                row_array[seat_number - 1] = 0;
                System.out.println(GREEN + "Ticket canceled successfully" + RESET);

            } else {
                //Error message is the seat is not occupied
                System.out.println(RED + "Seat is NOT Occupied, Please Try again." + RESET);
            }
        } else {
            //Error message when the user inputs an invalid number
            System.out.println(RED+ "Invalid Number" + RESET);
        }
    }

    //METHOD TO SHOW THE AVAILABLE SEATS
//----------------------------------------------------------------------------------------------------------------------
    static void show_available_seats(int which_row, int[] array_name) {
        // Row number Parameter, Array name Parameter

        //Loop to check the available seat in the 3 rows
        System.out.print("Available seats in row " + which_row + ": ");
        for (int i = 0; i < array_name.length; i++) {
            if (array_name[i] == 0) {
                System.out.print(i + 1 + ", ");
            }
        }
        System.out.println();
    }

    //SAVING THE SEATS DATA TO A FILE
//----------------------------------------------------------------------------------------------------------------------
    private static void save_to_file(int[] which_array, FileWriter new_writer) throws IOException {

        //For loop to save all the row information in the "data.txt" file
        for (int j : which_array) {

            //writing the row data in the data file
            if (j == 0) {
                new_writer.write(0 + " ");
            } else {
                new_writer.write(1 + " ");
            }
        }
    }

    //LOADING A PREVIOUSLY SAVE SEATS DATA FILE
//----------------------------------------------------------------------------------------------------------------------
    static void load_from_file()  {

        //try block to catch Errors while reading the file
        try {
            FileReader reader = new FileReader("data.txt");
            Scanner file_reader = new Scanner(reader);

            //First for loop to check read the row one data in the text file
            for (int i = 0; i < first_row.length; i++) {
                first_row[i] = file_reader.nextInt();
            }

            //Second for loop to check read the row one data in the text file
             for (int i = 0; i < second_row.length; i++) {
                 second_row[i] = (file_reader.nextInt());
             }
            //Third for loop to check read the row one data in the text file
            for (int i = 0; i < third_row.length; i++) {
                 third_row[i] = file_reader.nextInt();
             }

        } catch (IOException e) {
        //Error message when unable to load from a file
        System.out.println(RED + "Error Occurred" + RESET);
        }
        System.out.println(GREEN + "Data loaded successfully! " + RESET);
    }

    //PRINTING THE TICKET INFORMATION WITH THE TOTAL
//----------------------------------------------------------------------------------------------------------------------
    public static void print_ticket_information() {

        for (Ticket t : tickets_array) {
            t.print_ticket_info();
        }

        double total_price = 0;
        for (Ticket ticket : tickets_array) {
            total_price += ticket.getPrice();
        }
//        System.out.println("Total: " + total_price);

        System.out.println(GREEN +"-------------------");
        System.out.println("Total price: " + total_price + " $");
        System.out.println("-------------------" + RESET);
    }

    //SORTING THE TICKETS BY PRICE
//----------------------------------------------------------------------------------------------------------------------
    private static void sort_tickets() {

        if (tickets_array.size() == 0) {
            System.err.println(RED + "No values to be sorted" + RESET);
        } else {
            System.out.println(GREEN + "Sorting Tickets..." + RESET);
            System.out.println(GREEN + "Tickets sorted Successfully!" + RESET);

            int n = tickets_array.size();
            for (int i = 0; i < n - 1; i++) {
                for (int j = 0; j < n - i - 1; j++) {
                    if (tickets_array.get(j).getPrice() > tickets_array.get(j + 1).getPrice()) {

                        Ticket temp = tickets_array.get(j);
                        tickets_array.set(j, tickets_array.get(j + 1));
                        tickets_array.set(j + 1, temp);
                    }
                }
            }
        }
    }


}
//                                                     END
//**********************************************************************************************************************