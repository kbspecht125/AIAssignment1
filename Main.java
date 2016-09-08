package main;

import java.util.Scanner;
import java.util.LinkedList;
public class Main {
public static void main(String [] args){
Scanner user_input = new Scanner(System.in);
String start  = /*user_input.nextLine();*/ "10";
String goal  = /*user_input.nextLine();*/"89";
String op1  = /*user_input.nextLine();*/"- 5";
String op2  = /*user_input.nextLine();*/"+ 4";
String op3  = /*user_input.nextLine();*/"- 3";
String op4  = /*user_input.nextLine();*/"+ 0";
String op5  = /*user_input.nextLine();*/"^ 2";
String[] operations = new String[]{op1,op2,op3,op4,op5};
Node n = new Node("", operations, new LinkedList<String>(), Integer.parseInt(goal), 1);
n.data = Integer.parseInt(start);
n.greedyAlgorithm();
}
}
