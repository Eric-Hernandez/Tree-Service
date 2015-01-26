import java.io.*;
//=============================================================================
public class Forest implements Serializable {
//-----------------------------------------------------------------------------
private static final int MAX_TREES = 10;

private String forestName;
private Tree[] myForest;
//-----------------------------------------------------------------------------
//----Default constructor
    public Forest() {

		int index;

		forestName = null;
		myForest = new Tree[MAX_TREES];
		for (index = 0; index < myForest.length; index++) {
			myForest[index] = new Tree();
		}
	}
//-----------------------------------------------------------------------------
//----Give the new forest a name
    public Forest(String newName) {

		this();
		forestName = newName;

	}
//-----------------------------------------------------------------------------
//----Saving a forest
    public boolean saveForest(String forestName) {

		ObjectOutputStream toStream = null;
		int treeNumber;

		try {
			toStream = new ObjectOutputStream(new FileOutputStream(forestName));

			for (treeNumber = 0; treeNumber < myForest.length; treeNumber++) {
				if (myForest[treeNumber] != null) {
					toStream.writeObject(myForest[treeNumber]);
				}
			}
		} catch (IOException e) {
			System.out.println("ERROR: Saving forest : " + e.getMessage());
			return(false);
		} finally {
			try {
				if (toStream != null) {
					toStream.close();
				}
			} catch (IOException e) {
				System.out.println(e.getMessage());
				return(false);
			}
		}
		return(true);
	}
//-----------------------------------------------------------------------------
//----Loading a forest
    public boolean loadForest(String forestName) {

		ObjectInputStream fromStream;
		Tree nextTree;
		int numberOfTrees;

		numberOfTrees = 0;
		fromStream = null;
		try {
			fromStream = new ObjectInputStream(new FileInputStream(forestName));
			nextTree = (Tree)fromStream.readObject();
			while (nextTree != null) {
				myForest[numberOfTrees] = nextTree;
				numberOfTrees++;
				nextTree = (Tree)fromStream.readObject();
			}
		} catch (EOFException e) {
		} catch (IOException e) {
			System.out.println("ERROR: Loading trees: " + e.getMessage());
			return(false);
		} catch (ClassNotFoundException e) {
			System.out.println(e.getMessage());
			return(false);
		} finally {
			try {
				if (fromStream != null) {
					fromStream.close();
				}
			} catch (IOException e) {
				System.out.println("ERROR: Closing file: " + e.getMessage());
				return(false);
			}
		}
		System.out.println("The forest: " + forestName +
" has been successfully loaded!");
		return(true);
	}
//-----------------------------------------------------------------------------
//----Displays forest with tree heights, growth rates, and forest name
    public void display() {

		int index = 0;

		for (index = 0; index < myForest.length; index++) {
			if (myForest[index] != null) {
				System.out.printf("%2d  :  %5s\n", (index+1),
myForest[index]);

			}
		}
	}
//-----------------------------------------------------------------------------
//----Grows forest by one year
    public void growForest() {

		int index = 0;
		double newHeight = 0;

		for(index = 0; index < myForest.length; index++) {
			myForest[index].growTree();
		}
	}
//-----------------------------------------------------------------------------
//----Cuts trees over a certain height
    public double reapForest(double trim) {

		int index = 0;
		double newHeight = 0;

		for(index = 0; index < myForest.length; index++) {
			if(myForest[index].getHeight() >= trim) {
				System.out.printf("Cut %2d : %6.2f (%2.0f%% pa) \n", (index+1),
					myForest[index].getHeight(), myForest[index].getGrowthRate());
				myForest[index] = new Tree();
				System.out.printf("New %2d : %6.2f (%2.0f%% pa) \n", (index+1),
					myForest[index].getHeight(), myForest[index].getGrowthRate());
			}
		}
		return(newHeight);
	}
//-----------------------------------------------------------------------------
}
//=============================================================================
