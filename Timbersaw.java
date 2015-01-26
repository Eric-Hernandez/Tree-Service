import java.util.Scanner;
import java.io.*;
//=============================================================================
public class Timbersaw {
//-----------------------------------------------------------------------------
    private static Scanner keyboard = new Scanner(System.in);
//-----------------------------------------------------------------------------
    public static void main(String[] args) {

		Forest forestry = null;
		char option;
		double trim;
		String forestName = null;
		String fileName;
		String tempName = null;
		double height = 0;
		double growthRate = 0;
		String myInput;

		fileName = "ForestryFile.txt";

//----Greet the user
		System.out.println("Welcome to Forest Service Simulator 2014!");
		System.out.println();

//----Menu to select an option and make sure it is valid
		do {
			System.out.print("(D)isplay, (N)ew, (Y)ear, (R)eap, (S)ave, " +
"(L)oad, e(X)it : ");
			option = Character.toUpperCase(keyboard.nextLine().charAt(0));
			switch (option) {
			    case 'D':
					try {
						if (forestName != null) {
							System.out.println(forestName);
						}
						forestry.display();
					} catch (NullPointerException e) {
						System.out.println("No forest");
					}
					System.out.println();
					break;
				case 'N':
				    System.out.print("What is the forest name : ");
				    forestName = keyboard.nextLine();
				    forestry = new Forest(forestName);
				    System.out.println();
				    break;
				case 'Y':
					forestry.growForest();
					System.out.println();
					break;
				case 'R':
					try {
						System.out.print("What height to reap at  : ");
						myInput = keyboard.next();
						trim = Double.parseDouble(myInput);
						forestry.reapForest(trim);
					} catch (NumberFormatException e) {
						System.out.println("ERROR: Invalid height");
					}
					keyboard.nextLine();
					System.out.println();
					break;
				case 'S':
					forestry.saveForest(forestName);
					System.out.println();
					break;
				case 'L':
					System.out.print("What is the forest name : ");
					tempName = keyboard.nextLine();
					Forest tempForest = forestry;
					try {
						forestry = new Forest("temp");
						if(!forestry.loadForest(tempName)) {
							throw(new Exception("Loading failed"));
						}
						forestName = tempName;
					} catch (Exception e) {
						if (tempForest == null) {
							forestry = null;
						}
						System.out.println("There's no such forest!");
					}
					System.out.println();
				    break;
				case 'X':
					System.out.println("Goodbye");
					System.out.println();
					break;
				default:
					System.out.println("ERROR: Invalid option");
					System.out.println();
					break;
			}
		} while (option != 'X');
	}
//-----------------------------------------------------------------------------
}
//=============================================================================
