import java.util.Random;
import java.util.Scanner;

public class OddNumbers {
    public static void main(String[] args) {
        printOddNumber();
    }

    private static void printOddNumber() {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter Number: ");
        int number = input.nextInt();

        for (int i = 0; i < 20; i++) {
            System.out.println((number + 2));
            number++;
            number++;

        }


    }


}
