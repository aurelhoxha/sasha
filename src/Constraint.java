//Each constraint has to be satisfied in order to fill the puzzle

public class Constraint {
	public Clue acrossClue;
	public Clue downClue;
	public int acrossIndex;
	public int downIndex;
	
	public Constraint(Clue acro, Clue dow, int acInd, int doInd){
		acrossClue = acro;
		downClue = dow;
		acrossIndex = acInd;
		downIndex = doInd;
	}
}
