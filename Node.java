package main;

import java.util.LinkedList;
import java.util.ArrayList;
public class Node {
  int data;
  int goal;
  int yields;//NEWWWWWWWWWWWWWWWWWWW
  Node parent;
  boolean isexpanded;
  int nodesexpanded;
  String operation;
  ArrayList<String> operations = new ArrayList<String>();
  LinkedList<String> history;
  LinkedList<Node> nodeops;

  public Node(String op, ArrayList<String> ops, LinkedList<String> his, int g, int nodesex){
    this.yields = -1;
    this.isexpanded = false;
    this.operation = op;
    this.nodesexpanded = nodesex;
    this.goal = g;
    for(String s : ops){
      this.operations.add(s);
    }
    this.nodeops = new LinkedList<Node>();
    this.history = his;
  }

  public LinkedList<String> pulse(){
    int d = 0;
    while(true){
      if(System.currentTimeMillis()>Main.endTime)
      {
	      System.out.println("out of time");
	      System.exit(0);
      }
      LinkedList<String> ret = pulseRoot(d);
      if(ret != null){
        return ret;
      }else{
        d++;
      }
    }
  }
  public LinkedList<String> parentOps(){
    LinkedList<String> lst = new LinkedList<String>();
    lst.addFirst(operation);
    return parentOpsAcc(lst);
  }

  public LinkedList<String> parentOpsAcc(LinkedList<String> lst){
    if(parent != null && parent.operation != ""){
      lst.addFirst(parent.operation);
      return parent.parentOpsAcc(lst);
    }else{
      return lst;
    }
  }

  public LinkedList<String> pulseRoot(int dep){
    if(dep == 0){
      if(data == goal){
        return parentOps();
      }else{
        return null;
      }
    }
    if(nodeops.size() == 0){ expand();}
      for(Node n : nodeops){
        int a = dep - 1;
        System.out.println(data + " " + n.operation + "=" + n.operate(data));
        LinkedList<String> ret = n.pulseRoot(dep - 1);
        if(ret != null){
          return ret;
        }
      }   
    return null;
  }

  public void printeverything(){
    System.out.println("Data - " + data);
    System.out.println("Goal - " + goal);
    System.out.println("Operation - " + operation);
    for(int i = 0; i < operations.size(); i++){
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
    if(data - goal == 0){
      int diff = Math.abs(goal - data);
      System.out.println("Error: " + diff);
      System.out.println("Number of steps required: " + history.size());
      System.out.println("Nodes expanded: " + nodesexpanded);
    }else{
      Node next = bestNode();
      System.out.println(data + " " + next.operation + " = " + next.data);
      next.greedyAlgorithm();
    }
  }

  public int newGreedy(){
    if(isexpanded){
      this.yields = bestNode().newGreedy();
      System.out.println(data + " y=" + yields);
      return yields;
    }else{
      if(System.currentTimeMillis()>Main.endTime)
      {
	      System.out.println("out of time");
	      System.exit(0);
      }
      expand();
      System.out.println(data + " " + bestNode().operation + " = " + bestNode().data);
      this.yields = Math.abs(bestNode().data - goal);
      System.out.println(data + " y=" + yields);
      return yields;
    }
  }

  public boolean grandNewGreedy(){
    int i = 1;
    while(i != 0){
      i = newGreedy();
    }
    return true;
  }

  public Node bestNode(){ //finds the node with the shortest distance from the goal
    Node best = nodeops.getFirst();
    int bestdistancefromgoal = Math.abs(best.operate(data) - goal);
    for(Node n : nodeops){
      int nodesdistancefromgoal;
      if(n.yields == -1){
        nodesdistancefromgoal = Math.abs(n.operate(data) - goal);
      }else{
        nodesdistancefromgoal = n.yields;
      }
      if(nodesdistancefromgoal < bestdistancefromgoal /*&& n.isexpanded == false*/){
        best = n;
        bestdistancefromgoal = nodesdistancefromgoal;
      }
    }
    return best;
  }

  public void expand(){ //populates node with children nodes with data and operation values
    isexpanded = true;
    if(operation != null){
      history.addLast(operation);
    }
    for(int i = 0; i < operations.size(); i++){
      Node n = new Node(operations[i], operations, this.history, this.goal, nodesexpanded + 1);
      n.parent = this;
      n.data = n.operate(data);
      nodeops.addLast(n);
    }
  }

  public int operate(int parentnum){ //parent 'operation' = return value
    String num = operation.replaceAll(" ", "");
    int secondnumber = Integer.parseInt(num.substring(1, num.length()));
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
