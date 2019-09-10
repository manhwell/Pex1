import java.util.Scanner;
import java.util.regex.Pattern;

public class VectorCalculator {

    public static void main(String[] args) {
        Pattern startTokens = Pattern.compile("exit|<|\\d+|\\||dir|unit");
        System.out.println("Welcome to the Vector Calculator!");
        Scanner in = new Scanner(System.in);
        boolean exit = false;
        while(!exit){
            System.out.println("\nEnter vector expression: ");
            String userInput = in.nextLine();
            Scanner userCmd = new Scanner(userInput);
            if (userCmd.hasNext(startTokens) || userCmd.hasNextDouble()){
                if(userCmd.hasNext("exit") ){
                    exit = true;
                }
                else if(userCmd.hasNext("\\|")){
                    parseNormExpression(userCmd);
                }
                else if(userCmd.hasNext("\\d") || userCmd.hasNextDouble()){
                    parseScaleExpression(userCmd);
                }
                else if(userCmd.hasNext("unit")){
                    parseNormalizeExpression(userCmd);
                }
                else if(userCmd.hasNext("dir")){
                    parseDirectionExpression(userCmd);
                }
                else if(userCmd.hasNext("<")){
                    parseVectorExpression(userCmd);
                }
            }
        }
    }

    private static void parseExpression(java.util.Scanner s){
        Vector330Class myVector = Vector330Class.parseVector(s);
    }

    private static void parseVectorExpression(java.util.Scanner s){
        Vector330Class vector1 = Vector330Class.parseVector(s);
        if(s.hasNext("\\+")){
            s.next("\\+");
            Vector330Class vector2 = Vector330Class.parseVector(s);
            Vector330Class vectorResult = vector1.add(vector2);
            System.out.println("Result is " + vectorResult.toString());
        }

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
        System.out.println("Result is " + newVector.toString());
    }
    private static void parseDirectionExpression(java.util.Scanner s){
        s.next("dir");
        Vector330Class myVector = Vector330Class.parseVector(s);
        double vectorDir = myVector.direction();
        System.out.println("Result is " + vectorDir);
    }

    private static void parseNormalizeExpression(java.util.Scanner s){
        s.next("unit");
        Vector330Class myVector = Vector330Class.parseVector(s);
        Vector330Class newVector = myVector.normalize();
        System.out.println("Result is " + newVector.toString());
    }
}
