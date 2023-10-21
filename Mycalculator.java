import java.util.*;
import java.io.*;
//main class
class Mycalculator{
    //main function
    public static void main(String args[]){
        //input values taking
        double num1,num2;
        //to read the input values
        Scanner read=new Scanner(System.in);
        //display of main menu of options
        System.out.println("Main Menu:");
        System.out.println("-----------------------");
        System.out.println("1. Addition");
        System.out.println("2. Subtraction");
        System.out.println("3. Multiplication");
        System.out.println("4.Division");
        System.out.println("5.Exit");
        System.out.println("Enter the values");
        //reading input values
        num1=read.nextDouble();
        num2=read.nextDouble();
        //choosing an option
        System.out.println("Choose the option");
        int op=read.nextInt();
        double o=0;
        //to perform the operations
        switch(op){
            case 1:
                o=num1+num2;
                break;
            case 2:
                o=num1-num2;
                break;
            case 3:
                o=num1*num2;
                break;
            case 4:
                o=num1/num2;
                break;
            case 5:
                return;
            default:
                System.out.println("Invaild input");
        }
        //display of the output
        System.out.println("Answer:"+o);

    }
}