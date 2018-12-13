//This class represents a clue

import java.awt.*;
import java.io.IOException;
import java.util.*;

public class Clue {
	public String clueQuestion; 
	public int clueNumber;
	public int length;
	public int xPosition, yPosition; //Coordinates of index 0 in matrix
	public char[] solution; //Solution array for chars
	public String direction;
	
	public Clue(String question, int nr, int leng, int x, int y, String dir){
		clueQuestion = question;
		clueNumber = nr;
		direction = dir;
		xPosition = x;
		yPosition = y;
		length = leng;
		solution = new char[length];
	}
	
	public void setQuestion(String q){
		clueQuestion = q;
	}
	
	public void setClueNumber(int n){
		clueNumber = n;
	}
	
	public void setPosition(int x, int y){
		xPosition = x;
		yPosition = y;
	}
	
	public void setLength(int l){
		length = l;
		solution = new char[length];
	}
	
	public void setSolution(char[] sol){
		solution = sol;
	}
	
	public void setDirection(String s){
		direction = s;
	}
	
	public String getQuestion(){
		return clueQuestion;
	}
	
	public int getClueNumber(){
		return clueNumber;
	}
	
	public int getXPosition(){
		return xPosition;
	}
	
	public int getYPosition(){
		return yPosition;
	}
	
	public int getLength(){
		return length;
	}
	
	public char[] getSolution(){
		return solution;
	}
	
	public String getDirection(){
		return direction;
	}
	
	
}
