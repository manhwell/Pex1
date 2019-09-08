import java.text.DecimalFormat;

public class Vector330Class {
    private double x;
    private double y;
    private static final double EPS = 0.0000000001;

    public Vector330Class(){
        this.x = 0;
        this.y = 0;
    }

    public Vector330Class(double x, double y){
        this.x = x;
        this.y = y;
    }

    public Vector330Class(int x, int y){
        this.x = x;
        this.y = y;
    }

    public Vector330Class(long x, long y){
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public int getXint(){
        return (int) x;
    }

    public long getXlong(){
        return (long) x;
    }

    public double getY() {
        return y;
    }

    public int getYint(){
        return (int) y;
    }

    public long getYlong(){
        return (long) y;
    }

    public void setX(double x){
        this.x = x;
    }

    public void setX(int x){
        this.x = x;
    }

    public void setX(long x){
        this.x = x;
    }

    public void setY(double y){
        this.y = y;
    }

    public void setY(int y){
        this.y = y;
    }

    public void setY(long y){
        this.y = y;
    }

    public boolean equals(Vector330Class v){
        return (Math.abs(this.x-v.x) <= this.EPS || Math.abs(this.y-v.y) <= this.EPS);
    }

    public Vector330Class add(Vector330Class v){
        return new Vector330Class((this.x + v.x), (this.x + v.x));
    }

    public Vector330Class subtract(Vector330Class v){
        return new Vector330Class((this.x - v.x), (this.x - v.x));
    }

    public double dotProduct(Vector330Class v){
        return ((this.x * v.x) + (this.y * v.y));
    }

    public Vector330Class scale(double s){
        return new Vector330Class(this.x * s, this.y * s);
    }

    public double magnitude(){
        return Math.sqrt((this.x * this.x) + (this.y * this.y));
    }

    public double direction(){
        return Math.atan2(this.y, this.x);
    }

    public Vector330Class normalize(){
        if(this.magnitude() <= this.EPS){
            return new Vector330Class(0, 0);
        }
        else{
            return new Vector330Class(this.x * (1/this.magnitude()), this.y * (1/this.magnitude()));
        }
    }

    public java.lang.String toString(){
        DecimalFormat df = new DecimalFormat("0.0");
        String stringAns = "< " + df.format(this.x) + ", " + df.format(this.y) + " >";
        return stringAns;
    }

    public static Vector330Class parseVector(java.util.Scanner s){
        double newX = 0;
        double newY = 0;
        String newString = s.toString();
        newString = newString.replaceAll("<", "").replaceAll(" ", "");
        return new Vector330Class(newX, newY);
    }
}
