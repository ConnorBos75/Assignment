/**
Author: Connor Bos
Student number: 300011530
Course code: ITI1121
Lab section: A03
Lecture Section: A00
Assignment: 1
*/

import java.util.Arrays;

public class Solution {

   public int width, height;
   private int ctr_rows = 0, ctr_columns = 0;
   public boolean[][] board;

    /**
     * Constructor. Creates an instance of Solution
     * for a board of size <b>widthxheight</b>. That
     * solution does not have any board position
     * value explicitly specified yet.
     *
     * @param width
     * the width of the board
     * @param height
     * the height of the board
     */
    public Solution(int width, int height) {
       this.width = width;
       this.height = height;
       this.board = new boolean[this.height][this.width];
    }

   /**
     * Constructor. Creates an instance of Solution
     * wich is a deep copy of the instance received
     * as parameter.
     *
     * @param other
     * Instance of solution to deep-copy
     */
     public Solution(Solution other) {
       this.width = other.width;
       this.height = other.height;
       this.board = new boolean[this.height][this.width];
       this.ctr_rows = other.getCtrRows();
       this.ctr_columns = other.getCtrColumns();
       for(int i = 0; i < this.height; i++){ //Iterates through the rows
           for(int j = 0; j < this.width; j++){ //Iterates through the columns
               this.board[i][j] = other.board[i][j]; //Deep Copy because boolean is a primitive not wrapped as object (Allocated from stack not heap)
           }
       }      
    }


    /**
     * returns <b>true</b> if and only the parameter
     * <b>other</b> is referencing an instance of a
     * Solution which is the ``same'' as this
     * instance of Solution (its board as the same
     * values and it is completed to the same degree)
     *
     * @param other
     * referenced object to compare
     */

    public boolean equals(Object other){
       if(other instanceof Solution){
           Solution board2 = (Solution) other;
           boolean same_completion = (this.ctr_rows == board2.getCtrRows() && this.ctr_columns == board2.getCtrColumns());
           boolean same_dimensions = (this.width == board2.width && this.height == board2.height);
           if(same_dimensions == true && same_completion == true){
               for(int i = 0; i < this.width; i++){ //Iterates through columns
                   for(int j = 0; j < this.height; j++){ //Iterates through rows
                       if(this.board[j][i] != board2.board[j][i]){
                           return false;
                       }
                   }
               }
               return true;
           }              
       }

       return false;

      
    }


    /**
    * returns <b>true</b> if the solution
    * has been entirely specified
    *
    * @return
    * true if the solution is fully specified
    */
    public boolean isReady(){
       return (this.ctr_rows >= this.height && this.ctr_columns >= (this.width-1));
    }

    /**
    * specifies the ``next'' value of the
    * solution.
    * The first call to setNext specifies
    * the value of the board location (1,1),
    * the second call specifies the value
    * of the board location (2,1) etc.
    *
    * If <b>setNext</b> is called more times
    * than there are positions on the board,
    * an error message is printed out and the
    * call is ignored.
    *
    * @param nextValue
    * the boolean value of the next position
    * of the solution
    */
    public void setNext(boolean nextValue) {
       if(this.ctr_rows < this.height){
           this.board[this.ctr_rows][this.ctr_columns] = nextValue;
           this.ctr_rows++; } else if (this.ctr_rows >= this.height && this.ctr_columns < (this.width-1)){
           this.board[0][this.ctr_columns+1] = nextValue;
           this.ctr_rows = 1;
           this.ctr_columns++;
       } else if (this.ctr_columns >= this.height && this.ctr_columns >= (this.width-1)){
           System.out.println("You have exceeded the number of time this method can be called.");
       }
    }
  
    /**
    * returns <b>true</b> if the solution is completely
    * specified and is indeed working, that is, if it
    * will bring a board of the specified dimensions
    * from being entirely ``off'' to being entirely
    * ``on''.
    *
    * @return
    * true if the solution is completely specified
    * and works
    */
    public boolean isSuccessful(){
       if(isReady()){  
           for(int i = 0; i < this.height; i++){ //Iterating through rows
               for(int j = 0; j < this.width; j++){ //Iterating through columns
                   int success_ctr = (this.board[i][j]) ? 1:0;
                   if(i != 0){
                       success_ctr += (this.board[i-1][j]) ? 1:0;
                   }
                   if(j != 0){
                       success_ctr += (this.board[i][j-1]) ? 1:0;
                   }
                   if(i != (this.height - 1)){
                       success_ctr += (this.board[i+1][j]) ? 1:0;
                   }
                   if(j != (this.width -1)){
                       success_ctr += (this.board[i][j+1]) ? 1:0;
                   }
  
                   if((success_ctr % 2) == 0){
                       return false;
                   }  
               }
           }
           return true;
       }
       return false;
   }

   /**
    * this method ensure that add <b>nextValue</b> at the
    * currentIndex does not make the current solution
    * impossible. It assumes that the Solution was
    * built with a series of setNext on which
    * stillPossible was always true.
    * @param nextValue
    *         The boolean value to add at currentIndex
    * @return true if the board is not known to be
    * impossible (which does not mean that the board
    * is possible!)
    */
    public boolean stillPossible(boolean nextValue) {
       if(this.ctr_rows < this.height && this.ctr_columns > 0){
           for(int i = 0; i < ctr_columns; i++){
               if(i<(ctr_columns-1)){
                   for(int j = 0; j < this.height; j++){ //Iterate through rows
                       int success_ctr = (this.board[j][i]) ? 1:0;
                       if(j != 0){
                           success_ctr += (this.board[j-1][i]) ? 1:0;
                       }
                       if(i != 0){
                           success_ctr += (this.board[j][i-1]) ? 1:0;
                       }
                       if(j != (this.height - 1)){
                           success_ctr += (this.board[j+1][i]) ? 1:0;
                       }
                       if(i != (this.width - 1)){
                           success_ctr += (this.board[j][i+1]) ? 1:0;
                       }
                       if((success_ctr % 2) == 0){
                           return false;
                       } }
               } else {
                   for(int j = 0; j <= this.ctr_rows; j++){
                       int success_ctr = (this.board[j][i]) ? 1:0;
                       if(j<this.ctr_rows){
                           if(j != 0){
                               success_ctr += (this.board[j-1][i]) ? 1:0;
                           }
                           if(i != 0){
                               success_ctr += (this.board[j][i-1]) ? 1:0;
                           }
                           if(j != (this.height -1)){
                               success_ctr += (this.board[j+1][i]) ? 1:0;
                           }
                           if(i != (this.width -1)){
                               success_ctr += (this.board[j][i+1]) ? 1:0;
                           }
                       } else {
                           if(j != 0){
                               success_ctr += (this.board[j-1][i]) ? 1:0;
                           }
                           if(i != 0){
                               success_ctr += (this.board[j][i-1]) ? 1:0;
                           }
                           if(j != (this.height -1)){
                               success_ctr += (this.board[j+1][i]) ? 1:0;
                           }
                           if(i != (this.width -1)){
                               success_ctr += nextValue ? 1:0;
                           }
                       }
                       if((success_ctr % 2) == 0){
                           return false;
                       }
                   }

               }
           }      

       } else if (this.ctr_rows >= this.height && this.ctr_columns < (this.width-1)){
           for(int i = 0; i <= ctr_columns; i++){ //Iterate through Columns
               if(i<ctr_columns){
                   for(int j = 0; j < this.height; j++){ //Iterate through rows
                       int success_ctr = (this.board[j][i]) ? 1:0;
                       if(j != 0){
                           success_ctr += (this.board[j-1][i]) ? 1:0;
                       }
                       if(i != 0){
                           success_ctr += (this.board[j][i-1]) ? 1:0;
                       }
                       if(j != (this.height - 1)){
                           success_ctr += (this.board[j+1][i]) ? 1:0;
                       }
                       if(i != (this.width - 1)){
                           success_ctr += (this.board[j][i+1]) ? 1:0;
                       }
                       if((success_ctr % 2) == 0){
                           return false;
                       }
                   }
               } else {
                   int success_ctr = (this.board[0][i]) ? 1:0;
                   if(i != 0){
                       success_ctr += (this.board[0][i-1]) ? 1:0;
                   }
                   if(i != (this.width - 1)){
                       success_ctr += nextValue ? 1:0;
                   }
                   success_ctr += this.board[1][i] ? 1:0;
                   if((success_ctr % 2) == 0){
                       return false;
                   }
               }
           }
       }

        return true;
    }

    /**
    * this method attempts to finish the board.
    * It assumes that the Solution was
    * built with a series of setNext on which
    * stillPossible was always true. It cannot
    * be called if the board can be extended
    * with both true and false and still be
    * possible.
    *
    * @return true if the board can be finished.
    * the board is also completed
    */
    public boolean finish(){
       while(!isReady()){
           if(stillPossible(true)){
               setNext(true);
           } else if (stillPossible(false)){
               setNext(false);
           } else { //Neither false or true are extendable solutions and spawns only dead paths
               return false;
           }
       }
       if(isSuccessful()){
           return true;
       }
       return false;
    }

    /**
     * returns a string representation of the solution
     *
     * @return
     *      the string representation
     */
    public String toString() {
       String return_string = "[";
       for(int i = 0; i < this.board.length; i++){
           if(i < (this.board.length-1)){
               return_string += Arrays.toString(this.board[i]) + ",\n";
           } else {
               return_string += Arrays.toString(this.board[i]) + "]";
           }
       }
       return return_string;
    }

   public int getCtrRows(){
       return this.ctr_rows;
   }

   public int getCtrColumns(){
       return this.ctr_columns;
   }

}