import java.io.*;
//=============================================================================
public class Tree implements Serializable {
//-----------------------------------------------------------------------------
private static final double HEIGHT_RANGE = 4.0;
private static final double MIN_HEIGHT = 1.0;
private static final double GROWTH_RANGE = 50.0;
private static final double MIN_RATE = 50.0;

    private double height;
    private double growthRate;
//-----------------------------------------------------------------------------
//----Default constructor
    public Tree() {

		height = ((Math.random()*HEIGHT_RANGE)+MIN_HEIGHT);
		growthRate = ((Math.random()*GROWTH_RANGE)+MIN_RATE);

	}
//-----------------------------------------------------------------------------
//----Set height and growth rate of trees
    public Tree(double height, double growthRate) {


		this.height = height;
		this.growthRate = growthRate;

	}
//-----------------------------------------------------------------------------
//----A toString method to show data in organized way
    public String toString() {

		return(String.format("%6.2f (%2d%% pa) \n", height,
Math.round((float)growthRate)));

	}
//-----------------------------------------------------------------------------
//----Returns height
    public double getHeight() {

		return(height);

	}
//-----------------------------------------------------------------------------
//----Returns growth rate
    public double getGrowthRate() {

		return(growthRate);

	}
//-----------------------------------------------------------------------------
//----Formula used to grow each tree
    public void growTree() {

		height = height * (1 + (growthRate/100));
	}
//-----------------------------------------------------------------------------
}
//=============================================================================
