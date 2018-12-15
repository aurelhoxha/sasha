//Each constraint has to be satisfied in order to fill the puzzle

public class Constraint {
	public Clue across;
	public Clue down;
	public int acrossIndex;
	public int downIndex;
	
	public Constraint(Clue across, Clue down, int acrossIndex, int downIndex){
		this.across = across;
		this.down = down;
		this.acrossIndex = acrossIndex;
		this.downIndex = downIndex;
	}
	
	public boolean isSatisfied(){
		return across.solution[acrossIndex] == down.solution[downIndex];
	}
	
	public boolean contains(Clue checkClue) {
		return ((across.clueNumber == checkClue.clueNumber && across.direction.equals(checkClue.direction)) || (down.clueNumber == checkClue.clueNumber && down.direction.equals(checkClue.direction)));
	}
	
	public void updateClue() {
		if(across.isSolved()) {
			down.solution[downIndex] = across.solution[acrossIndex];
		}
		
		if(down.isSolved()) {
			across.solution[acrossIndex] = down.solution[downIndex];
		}
	}
}
