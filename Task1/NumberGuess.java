import java.util.Scanner;
import java.util.Random;
public class NumberGuess
{
    public static void main(String [] args)
    {
        //Scanner is used to take input from user
        Scanner sc = new Scanner(System.in);

        //Random is used to generate random number
        Random  r = new Random();

        //declaring variables
        int num, count = 0;
        String input;
        char choice; 

        //title or header
        System.out.println("RANDOM NUMBER GUSSING GAME");
        System.out.println("............................");
        System.out.println();


        //Take input from user
        do
        {    
            //set maximum attempt to 3
            int attemptsNum = 0;
            final int maxAttempts = 3;

             //Generate random number
             final int random = r.nextInt(100);
             System.out.println("Number = " + random);
             
            do
            {
                System.out.print("Guess the random number " + "from 1 to 100 : ");
                num = sc.nextInt();

                //if user entered number is less than random number then 
                if(num < random)
                {
                    //#displaying message
                    System.out.println("Your guess is too low try again...");
                    System.out.println();
                
                    //if user entered negative number then
                    if (num < 1)
                    {
                        System.out.println("Invalid Input" +"\nYour guess cannot be a negative" +" number");
                    }
                }

                //if user enter number is greater than random number then 
                if(num > random)
                {
                    //#display this message
                    System.out.println("Your guess is too high try again...");
                    System.out.println();
                    

                    //if entered number is out of range then
                    if (num > 100)
                    {
                        System.out.println("Invalid Input"+ "\nYour guess cannot be above 100");
                    }

                }
               
                //to display count in which guess, the user find a number
                count++;
                if (num == random) 
                {
                    System.out.println("You guessed the random number in " + count + " guesses!");
                    System.out.println();
                    break;
                } 
 
            }
            while(++attemptsNum < maxAttempts);

            //if the attempts are over
            if (attemptsNum == maxAttempts)
                System.out.println("You ran out of tries."+ "You lose!");
                System.out.println();

            choice = 'x'; 
            System.out.print("Do You Want To Continue? (Yes or No) : ");
            System.out.println();
            input = sc.next();
            choice = input.charAt(0);
            

        }while (choice == 'y' || choice == 'y');

    }
}