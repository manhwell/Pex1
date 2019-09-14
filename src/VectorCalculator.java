import java.text.DecimalFormat;
import java.util.Scanner;
import java.util.regex.Pattern;

/**
 * VectorCalculator provides command line functionality for a vector calculator.
 * Documentation: None, beyond code provided in write up and EI with Dr. Hadfield and his pseudo code for main()
 * @author Manuel Riolo
 */
public class VectorCalculator {

    // Structure of main taken from pseudo code written by Dr. Hadfield in EI
    public static void main(String[] args) {
        Pattern startTokens = Pattern.compile("exit|<|\\d+|\\||dir|unit"); // Set flags to look for when input is passed
        System.out.println("Welcome to the Vector Calculator!");
        Scanner in = new Scanner(System.in);
        boolean exit = false;
        while(!exit){
            System.out.println("\nEnter vector expression: ");
            String userInput = in.nextLine(); // Get the users input
            Scanner userCmd = new Scanner(userInput); // Send the users input to a new scanner
            if (userCmd.hasNext(startTokens) || userCmd.hasNextDouble()) { // Checking to make sure teh input starts correctly
                if (userCmd.hasNext("exit")) { // If the user inputs "exit" then leave the loop
                    exit = true;
                    System.out.println("\nThank you for using the Vector Calculator");
                }
                else if (userCmd.hasNext("\\|")) { // Start of a vector norm expression
                    parseNormExpression(userCmd);
                }
                else if (userCmd.hasNext("\\d") || userCmd.hasNextDouble()) { // Start of a scaling expression
                    parseScaleExpression(userCmd);
                }
                else if (userCmd.hasNext("unit")) { // Start of a normalization expression
                    parseNormalizeExpression(userCmd);
                }
                else if (userCmd.hasNext("dir")) { // Start of a direction expression
                    parseDirectionExpression(userCmd);
                }
                else if (userCmd.hasNext("<")) { // Start of a simple math expression
                    parseVectorExpression(userCmd);
                }
            }
            else { // Invalis start to an input
                    System.out.println("Invalid input, check formatting");
            }
            userCmd.close(); // Clean up the scanner
        }
        in.close();
    }

    /**
     * Parses through a vector expression for addition, subtraction, or dot product and executes the appropriate actions.
     * @param s is the scanner object that contains the users input.
     */
    private static void parseVectorExpression(java.util.Scanner s){
        try {
            Vector330Class vector1 = Vector330Class.parseVector(s); // First, get the first vector input
            if (s.hasNext("\\+")) { // Indicates an addition command
                s.next("\\+");
                Vector330Class vector2 = Vector330Class.parseVector(s); // Get the second vector input
                Vector330Class vectorResult = vector1.add(vector2); // Add them together
                System.out.println("Result is " + vectorResult.toString());
            } else if (s.hasNext("-")) { // Indicates a subtraction command
                s.next("-");
                Vector330Class vector2 = Vector330Class.parseVector(s); // Get the second vector input
                Vector330Class vectorResult = vector1.subtract(vector2); // Subtract them
                System.out.println("Result is " + vectorResult.toString());
            }
            if (s.hasNext(".")) { // Indicates a dot product command
                s.next(".");
                Vector330Class vector2 = Vector330Class.parseVector(s); // get the second vector
                double vectorResult = vector1.dotProduct(vector2); // Get the dot product
                System.out.println("Result is " + vectorResult);
            }
        }
        catch (Exception e){ // User did not input a valid command, let them know
            System.out.println(e.getMessage());
        }
    }

    /**
     * Parses a vector norm expression command
     * @param s is the scanner object that contains the users input.
     */
    private static void parseNormExpression(java.util.Scanner s){
        try {
            s.next("\\|");
            Vector330Class myVector = Vector330Class.parseVector(s); // Get the values from the vector
            double vectorMagnitude = myVector.magnitude(); // Find the magnitude
            System.out.println("Result is " + vectorMagnitude);
        }
        catch (Exception e){ // User did not input a valid command, let them know
            System.out.println(e.getMessage());
        }
    }

    /**
     * Parses a vector expression and scales it.
     * @param s is the scanner object that contains the users input.
     */
    private static void parseScaleExpression(java.util.Scanner s){
        try {
            double scaleValue = s.nextDouble(); // Get the scale factor
            s.next("\\*");
            Vector330Class myVector = Vector330Class.parseVector(s); // Get the values from the vector
            Vector330Class newVector = myVector.scale(scaleValue); // Scale it by the scale value
            System.out.println("Result is " + newVector.toString());
        }
        catch (Exception e){ // User did not input a valid command, let them know
            System.out.println(e.getMessage());
        }
    }

    /**
     * Parses a vector expression and returns its direction is radians and degrees.
     * @param s is the scanner object that contains the users input.
     */
    private static void parseDirectionExpression(java.util.Scanner s){
        try {
            DecimalFormat df = new DecimalFormat("0.00000"); // Set the output format for numbers
            s.next("dir");
            Vector330Class myVector = Vector330Class.parseVector(s); // Get the values from the vector
            double vectorDir = myVector.direction(); // Calculate the direction
            System.out.println("Result is " + df.format(vectorDir) + " radians\nor " + df.format(vectorDir * (180 / Math.PI)) + " in degrees."); // Print radians and calculate degrees
        }
        catch (Exception e){ // User did not input a valid command, let them know
            System.out.println(e.getMessage());
        }
    }

    /**
     * Parses a vector expression and normalizes the vector.
     * @param s is the scanner object that contains the users input.
     */
    private static void parseNormalizeExpression(java.util.Scanner s){
        s.next("unit");
        try {
            Vector330Class myVector = Vector330Class.parseVector(s); // Get the values from the vector
            Vector330Class newVector = myVector.normalize(); // Normalize it
            System.out.println("Result is " + newVector.toString());
        }
        catch (Exception e){ // User did not input a valid command, let them know
            System.out.println(e.getMessage());
        }
    }
}
