/**
Author: Connor Bos
Student number: 300011530
Course code: ITI1121
Lab section: A03
Lecture Section: A00
Assignment: 1
*/

import java.util.ArrayList;

public class LightsOut		{
	
   private static long program_start;
   
    public static ArrayList<Solution> solve(int width, int height)		{
		
       ArrayListSolutionQueue partialSolutions = new ArrayListSolutionQueue();
       ArrayList<Solution> solutions = new ArrayList<Solution>();
	   
       //Initialize an empty and untouched board
        Solution current = new Solution(width, height);
      
       partialSolutions.enqueue(current);

       while(!partialSolutions.isEmpty())		{
		   
           current = partialSolutions.dequeue();
		   
           if(current.isSuccessful())		{
			   
               solutions.add(current);
               long elapsed_time = System.currentTimeMillis() - program_start;
               System.out.println("Solution found in "+elapsed_time+" ms");
			   
		   }   
		   
           else if (!current.isReady())		{
			   
               if(current.stillPossible(true) && current.stillPossible(false))		{
				   
                   Solution trueBranch = new Solution(current);
                   trueBranch.setNext(true);
                   Solution falseBranch = new Solution(current);
                   falseBranch.setNext(false);
                   partialSolutions.enqueue(trueBranch);
                   partialSolutions.enqueue(falseBranch);
				   
			   }
			   
               else 	{
				   
                   if(current.finish())		{
					   
                       solutions.add(current);
                       long elapsed_time = System.currentTimeMillis() - program_start;
                       System.out.println("Solution found in "+elapsed_time+" ms");
					   
                   }
               }
           }
       }
	   
       return solutions;
	   
    }

    public static void main(String[] args) {
       program_start = System.currentTimeMillis();
       StudentInfo.display();
       int width = 3, height = 3;
       try{
           if(Integer.parseInt(args[0]) > 0){
               width = Integer.parseInt(args[0]);
           } else {
               System.out.println("Invalid width, using default...");
           }
       } catch (NumberFormatException error){
           System.out.println("Invalid width, using default...");
       }
       try{
           if(Integer.parseInt(args[1]) > 0){
               height = Integer.parseInt(args[1]);
           } else {
               System.out.println("Invalid height, using default...");
           }
       } catch (NumberFormatException error){
           System.out.println("Invalid height, using default...");
       } catch (ArrayIndexOutOfBoundsException error){
           System.out.println("Invalid height, using default...");
       }

       ArrayList<Solution> solutions = solve(width, height);
       for(int i = 0; i < solutions.size(); i++){
           System.out.println("****\n"+solutions.get(i)+"\n");
       }
       System.out.println("In a board of "+width+"x"+height+": "+solutions.size()+" solutions.");
    }
}