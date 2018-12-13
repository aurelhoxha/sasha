//This class represents a clue

import java.awt.*;
import java.io.IOException;
import java.util.*;

public class Clue {
	public String clueQuestion; 
	public int clueNumber;
	public int length;
	public char[] solution; //Solution array for chars
	public String direction;
	public ArrayList<String> alternatives;//Possible options for answer of the clue
	
	public Clue(String question, int nr, int leng, int x, int y, String dir){
		clueQuestion = question;
		clueNumber = nr;
		direction = dir;
		length = leng;
		solution = new char[length];
		alternatives = new ArrayList<String>();
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
	}
	
	public void setSolution(char[] sol){
		solution = sol;
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
	
	public String getDirection(){
		return direction;
	}
	
	public ArrayList<String> getAlternatives(){
		return alternatives;
	}
}
