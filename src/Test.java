
public class Test {

	public static void main(String[] args) throws Exception {
		GameInformation myGame = new GameInformation();
		//System.out.println(myGame.getDownClues());
		myGame.getBlockCells();
		myGame.getAcrossClues();
		myGame.getDownClues();
		
		myGame.printBlockCells();
		myGame.printAcrossClues();
		myGame.printDownClues();
	}

}
