import java.util.Random;
import java.util.Scanner;

public class Simonsays {
// using method to call the screen
    public static void screenClear() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

   // using method for pausing 3 second
    public static void pause() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public static void main(String[] args) {
        Scanner inputScanner = new Scanner(System.in);
        Random randGenerator = new Random();
        boolean continueGame = true;

        while (continueGame) {
            System.out.println("comon let's play simon says !");
            String difficultylevel = chooseDifficulty(inputScanner);

            String pattern = "";
            int points = 0;
            boolean gameEnd = false;

            while (!gameEnd) {
                // Add to the pattern based on the difficulty level
                pattern += (difficultylevel.equals("easy")) ? getRandomNumber(randGenerator) + " " : getRandomShade(randGenerator) + " " ;
                
                System.out.println("Simon says: " + pattern.trim());
                pause();
                screenClear();

                // Get input from user and validates
                System.out.print("Player repeats: ");
                String userInput = inputScanner.nextLine().replaceAll("\\s", "").toLowerCase();
                String correctPattern= pattern.replaceAll("\\s", "").toLowerCase();

                if (userInput.equals(correctPattern)) {
                    points++;
                    System.out.println("Score: " + points);
                } else {
                    System.out.println("Round over! Your score was " + points);
                    gameEnd = true;
                }
            }

        // Ask the user if he/she wants to play another round 
            continueGame = askForAnotherRound(inputScanner);
        }

        System.out.println("Thank you for playing.");
        inputScanner.close();
    }

    // Method tho select the difficulty level
    public static String chooseDifficulty(Scanner inputScanner) {
        String difficultylevel = "";
        while (!difficultylevel.equals("Easy") && !difficultylevel.equals("Tough")) {
            System.out.print("Select difficulty (Easy / Tough): ");
            difficultylevel= inputScanner.nextLine().toLowerCase();
            System.err.println(difficultylevel);
            if (!difficultylevel.equals("easy") && !difficultylevel.equals("tough")) {
                System.out.println("Invalid difficulty");
            }
            break;
        }
        return difficultylevel;
    }

    // its a method to promt the user to play again
    public static boolean askForAnotherRound(Scanner inputScanner) {
        String response = "";
        while (!response.equals("'Yeah'") && !response.equals("Nah")) {
            System.out.print("Would you like to play another round? (Yeah / Nah): ");
            response = inputScanner.nextLine().toLowerCase();
            if (!response.equals("yeah") && !response.equals("nah")) {
                System.out.println("Invalid response");
            }
            break;
        }
        return response.equals("yeah");
    }

   // Method for random number for easy mode
    public static String getRandomShade(Random random) {
        Random randGenerator = new Random();
        String[] shades = {"orange", "purple", "cyan", "magenta"};
        return shades[randGenerator.nextInt(shades.length)];
    }

    // Method to get a random number for hard mode
    public static String getRandomNumber(Random random) {
        Random randGenerator = new Random();
        int number = randGenerator.nextInt(11) - 3;  // Generates numbers from -3 to 7
        return String.valueOf(number);
    }
}
