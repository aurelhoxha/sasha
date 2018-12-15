//This class represents a clue

import java.awt.*;
import java.io.IOException;
import java.util.*;

public class Clue {
	public String clueQuestion; 
	public int xPosition;
	public int yPosition;
	public int clueNumber;
	public int length;
	public char[] solution; //Solution array for chars
	public String direction;
	public ArrayList<String> alternatives;//Possible options for answer of the clue
	public boolean solved;
	public ArrayList<Constraint> constraints;
	
	public Clue(String question, int nr, int leng, int x, int y, String dir){
		clueQuestion = question;
		clueNumber = nr;
		direction = dir;
		length = leng;
		solution = new char[length];
		alternatives = new ArrayList<String>();
		solved = false;
		xPosition = 0;
		yPosition = 0;
	}
	
	public void setCoordinates(int x, int y){
		xPosition = x;
		yPosition = y;
	}
	
	public int getX(){
		return xPosition;
	}
	
	public int getY(){
		return yPosition;
	}
	
	public void setSolved(boolean bool){
		solved = bool;
	}
	
	public boolean getSolved(){
		return solved;
	}
	
	public void setQuestion(String q){
		clueQuestion = q;
	}
	
	public void setClueNumber(int n){
		clueNumber = n;
	}

	public void setLength(int l){
		length = l;
		solution = new char[length];
		for(int i = 0; i < length; i++) {
			solution[i] = '-';
		}
	}
	
	public void setSolution(String sol){
		if(solution.length == sol.length()){
			for(int i = 0; i < solution.length; i++){
				solution[i] = sol.charAt(i);
			}
		}
	}
	
	public void setDirection(String s){
		direction = s;
	}
	
	public void setAlternatives(ArrayList<String> alt){
		alternatives = alt;
	}
	
	public String getQuestion(){
		return clueQuestion;
	}
	
	public int getClueNumber(){
		return clueNumber;
	}
	
	public int getLength(){
		return length;
	}
	
	public char[] getSolution(){
		return solution;
	}
	
	public void printSolution(){
		for(int i = 0; i < length; i++){
			System.out.print(solution[i]);
		}
		System.out.println();
	}
	
	public String getDirection(){
		return direction;
	}
	
	public ArrayList<String> getAlternatives(){
		return alternatives;
	}
	
	public void addAlternative(String alternative){
		alternatives.add(alternative);
	}
	
	public void printAlternative() {
		System.out.print("{");
		for(int i = 0; i < alternatives.size();i++)
			System.out.print(alternatives.get(i) + ",");
		System.out.println("}");
			
	}
	
	public void updateClueAlternative() {
		
	  
	        // Create a new ArrayList 
	        ArrayList<String> newList = new ArrayList<String>(); 
	  
	        // Traverse through the first list 
	        for (String element : alternatives) { 
	  
	            // If this element is not present in newList 
	            // then add it 
	            if (!newList.contains(element)) { 
	  
	                newList.add(element); 
	            } 
	        } 
	  
	        alternatives = newList;
		ArrayList<String> toBeDeleted = new ArrayList<String>();
		for(String str : alternatives){
			for(int i = 0; i < length; i++){
				if(!(solution[i] == '-')){
					if(solution[i] != str.charAt(i)){
						toBeDeleted.add(str);
					}
				}
			}
		}
		
		for(int i = 0; i < toBeDeleted.size(); i++){
			alternatives.remove(toBeDeleted.get(i));
		}
		
	}
}
