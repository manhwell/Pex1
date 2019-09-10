import java.util.Scanner;
import java.util.regex.Pattern;

public class VectorCalculator {

    public static void main(String[] args) {
        Pattern startTokens = Pattern.compile("exit|<|\\d|\\||dir|unit");
        System.out.println("Welcome to the Vector Calculator!");
        Scanner in = new Scanner(System.in);
        boolean exit = false;
        while(!exit){
            System.out.println("\nEnter vector expression: ");
            String userInput = in.nextLine();
            Scanner userCmd = new Scanner(userInput);
            if (userCmd.hasNext(startTokens)){
                if(userCmd.hasNext("exit")){
                    exit = true;
                }
                else if(userCmd.hasNext("\\|")){
                    parseNormExpression(userCmd);
                }
                //TODO how do I get it to accept a double? Currently only working with an int
                else if(userCmd.hasNext("\\d")){
                    parseScaleExpression(userCmd);
                }
                else if(userCmd.hasNext("unit")){
                    parseNormalizeExpression(userCmd);
                }
            }
        }
    }

    private static void parseExpression(java.util.Scanner s){
        Vector330Class myVector = Vector330Class.parseVector(s);
    }

    private static void parseVectorExpression(java.util.Scanner s){

    }

    private static void parseNormExpression(java.util.Scanner s){
        s.next("\\|");
        Vector330Class myVector = Vector330Class.parseVector(s);
        double vectorMagnitude = myVector.magnitude();
        System.out.println("Result is " + vectorMagnitude);
    }

    private static void parseScaleExpression(java.util.Scanner s){
        double scaleValue = s.nextDouble();
        s.next("\\*");
        Vector330Class myVector = Vector330Class.parseVector(s);
        Vector330Class newVector = myVector.scale(scaleValue);
        System.out.println("< " + newVector.getX() + ", " + newVector.getY() + " >");
    }
    private static void parseDirectionExpression(java.util.Scanner s){

    }

    private static void parseNormalizeExpression(java.util.Scanner s){
        s.next("unit");
        Vector330Class myVector = Vector330Class.parseVector(s);
        Vector330Class newVector = myVector.normalize();
        System.out.println("< " + newVector.getX() + ", " + newVector.getY() + " >");
    }
}
