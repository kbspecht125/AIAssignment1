package main;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.LinkedList;
public class Main {

static int goal;
static int start;
static double timeLimit;
static String type;
static ArrayList<String> operations = new ArrayList<String>();
static long endTime;
static long startTime;

public static void main(String[] args) {
     System.out.println("please input the file location of the .txt file you want to run");
     Scanner user_input = new Scanner(System.in);
     String line= user_input.nextLine();  
	 String fileName = line;//"C:\\Users\\hp\\workspace\\test\\src\\test\\testfile.txt";
	 //C:\Users\hp\workspace\test\src\test\testfile.txt
	 try
	 {
		 BufferedReader in = new BufferedReader(new FileReader(new File(fileName)));
		 System.out.println("opened");
		 String inputFromTxt;
			int count=0;
			while((inputFromTxt=in.readLine()) != null){
				if(count==0){
					type = inputFromTxt.trim();
				}
				else if(count==1){
					start = Integer.parseInt(inputFromTxt.trim());
				}
				else if(count==2){
					goal = Integer.parseInt(inputFromTxt.trim());
				}
				else if(count==3){
					timeLimit = Double.parseDouble(inputFromTxt.trim());
				}
				else{
					operations.add(inputFromTxt.trim());
				}
				count++;
			}
			//at this point the type, start, goal, timeLimit,and array of operations should have been stored
			}
		 in.close();

		 if(type.equals("iterative")){
			 //do iterative deepening 
		 }
		 else if(type.equals("greedy")){
			 //do greedy search
		 }
		 else{
			 System.out.println("please indicate the type of search");
		 }
		 //System.out.println(goal);
	 
	 
	 }
	 catch (IOException e){
		 System.out.println("error");
	 }

// v this is originally here..
Node n = new Node("", operations, new LinkedList<String>(), goal, 1);
n.data=start;

startTime=System.currentTimeMillis();
endTime=startTime+(int)(timeLimit*1000);
if(type.equals("greedy"))
	n.grandNewGreedy();
else if(type.equals("iterative"))
	n.pulse();
else
	System.out.println("Not a type!");
}
}
