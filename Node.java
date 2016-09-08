package main;

import java.util.LinkedList;
public class Node {
int data;
int goal;
int nodesexpanded;
String operation;
String[] operations;
LinkedList<String> history;
LinkedList<Node> nodeops;
public Node(String op, String[] ops, LinkedList<String> his, int g, int nodesex){
op = operation;
nodesexpanded = nodesex;
goal = g;
operations = ops;
nodeops = new LinkedList<Node>();
history = his;
}
public void printeverything(){
System.out.println("Data - " + data);
System.out.println("Goal - " + goal);
System.out.println("Operation - " + operation);
for(int i = 0; i < 5; i++){
System.out.println("operations - " + operations[i]);
}
for(String s : history){
System.out.println("history - " + s);
}
for(Node n : nodeops){
System.out.println("nodeops - " + n.data + " " + n.operation);
}
}
// bestnode expand operate
public void greedyAlgorithm(){
expand();
//printeverything();
Node next = bestNode();
if(next == null){
int diff = Math.abs(goal - data);
System.out.println("Error: " + diff);
System.out.println("Number of steps required: " + history.size());
System.out.println("Nodes expanded: " + nodesexpanded);
}else{
System.out.println(data + " " + next.operation + " = " + next.data);
next.greedyAlgorithm();
}
}
public Node bestNode(){ //finds the node with the shortest distance from the goal, null if all distances are shorter than current one
int bestdistancefromgoal = Math.abs(data - goal);
Node best = null;
for(Node n : nodeops){
int nodesdistancefromgoal = Math.abs(n.operate(data) - goal);
if(nodesdistancefromgoal < bestdistancefromgoal){
best = n;
bestdistancefromgoal = nodesdistancefromgoal;
}
}
return best;
}
public void expand(){ //populates node with children nodes with data and operation values
if(operation != null){
history.addLast(operation);
}
for(int i = 0; i < 5; i++){
LinkedList<String> remember = history;
Node n = new Node(operations[i], operations, history, this.goal, nodesexpanded + 1);
n.operation = operations[i];//the operation isn't assigned via constructor, not sure why
n.data = n.operate(data);
this.history = remember;
nodeops.addLast(n);
}
}
public int operate(int parentnum){ //parent 'operation' = return value
int secondnumber = Integer.parseInt(operation.substring(operation.length() - 1));
String startop = operation.substring(0, 1);
if(startop.equals("+")){
return parentnum + secondnumber;
}
if(startop.equals("-")){
return parentnum - secondnumber;
}
if(startop.equals("*")){
return parentnum * secondnumber;
}
if(startop.equals("^")){
return (int) Math.pow(parentnum, secondnumber);
}
if(startop.equals("/")){
return parentnum / secondnumber;
}
System.out.println("Invalid or unknown operation string");
return 0;
}
}
