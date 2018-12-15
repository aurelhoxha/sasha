//Each constraint has to be satisfied in order to fill the puzzle
import java.util.*;

public class Constraint {
	public Clue across;
	public Clue down;
	public int acrossIndex;
	public int downIndex;
	//public ArrayList<String> toBeDelAcross;
	//public ArrayList<String> toBeDelDown;
	
	public Constraint(Clue across, Clue down, int acrossIndex, int downIndex){
		this.across = across;
		this.down = down;
		this.acrossIndex = acrossIndex;
		this.downIndex = downIndex;
	}
	
	public boolean isSatisfied(){
		return across.solution[acrossIndex] == down.solution[downIndex];
	}
	
	public void cleanAcrossAlternatives(){
		ArrayList<String> toBeDeleted = new ArrayList<String>();
		for(int i = 0; i < across.alternatives.size(); i++){
			boolean dontDelete = false;
			String word = across.alternatives.get(i);
			for(int j = 0; j < down.alternatives.size(); j++){
				if(word.charAt(acrossIndex) == down.alternatives.get(j).charAt(downIndex)){
					dontDelete = true;
					break;
				}
			}

			if(dontDelete == false && down.alternatives.size() > 0){
				toBeDeleted.add(word);
				System.out.println("Sent word to delete: " + word);
			}
		}
		
		for(int i = 0; i < toBeDeleted.size(); i++){
			across.alternatives.remove(toBeDeleted.get(i));
		}
	}
	
	public void cleanDownAlternatives(){
		ArrayList<String> toBeDeleted = new ArrayList<String>();
		for(int i = 0; i < down.alternatives.size(); i++){
			boolean dontDelete = false;
			String word = down.alternatives.get(i);
			for(int j = 0; j < across.alternatives.size(); j++){
				if(word.charAt(downIndex) == across.alternatives.get(j).charAt(acrossIndex)){
					dontDelete = true;
					break;
				}
			}

			if(dontDelete == false && across.alternatives.size() > 0 ){
				toBeDeleted.add(word);
				System.out.println("Sent word to delete: " + word);
			}
		}
		
		for(int i = 0; i < toBeDeleted.size(); i++){
			down.alternatives.remove(toBeDeleted.get(i));
		}
	}
	
	public boolean contains(Clue checkClue) {
		return ((across.clueNumber == checkClue.clueNumber && across.direction.equals(checkClue.direction)) || (down.clueNumber == checkClue.clueNumber && down.direction.equals(checkClue.direction)));
	}
	
	public void updateClue() {
		if(across.isSolved()) {
			down.solution[downIndex] = across.solution[acrossIndex];
			down.checkIfCompleted();
		}
		
		if(down.isSolved()) {
			across.solution[acrossIndex] = down.solution[downIndex];
			across.checkIfCompleted();
		}
	}
}
