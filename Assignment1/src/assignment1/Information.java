/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment1;

/**
 *
 * @author Sub4sa
 */
public class Information implements Comparable<Information>{

    public int ID;
    public String fullName;
    public String birthDay;
    public double avgScore;
    public int noOfCredits;

    public Information(int ID, String fullName, String birthDay, double avgScore, int noOfCredits) throws Exception {
        if (ID >= 100 && ID <= 999) {
            this.ID = ID;
        } else {
            throw new Exception("ID phai la so co 3 chu so");
        }
        this.fullName = fullName;
        this.birthDay = birthDay;
        //int temp = (int) (avgScore * 100);
        this.avgScore =avgScore;// temp / 100.0;
        this.noOfCredits = noOfCredits;
    }

    public static int afterPoint(double x) {
        double temp = x - ((int) x);
        if (temp == 0.0) {
            return 0;
        }
        String t = "" + temp;
        return t.length() - 2;
    }

    public String toString() {
        String temp = "" + ID + "\n" + fullName + "\n" + birthDay + "\n" + avgScore + "\n" + noOfCredits + "\n";
        return temp;
    }

    @Override
    public int compareTo(Information o) {
        return this.ID-o.ID;
    }
}

