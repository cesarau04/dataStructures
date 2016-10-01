package homework1;
public class Peak {
	
	public static int OneDRecursive(int[] OneD, int index)
	{
	    if (index<0) {
	    	return OneD[0];
	    } else {
	    	return Math.max(OneD[index], OneDRecursive(OneD, index-1));
	    }
	}
	
	public static int TwoDRecursive(int[][] TwoD)
	{
		int maxNumber = TwoD[0][0];
		   for(int i=0; i<TwoD.length; i++)
		   {
		      for(int j = 0; j<TwoD[0].length; j++)
		      {
		         if(maxNumber<TwoD[i][j])
		         {
		            maxNumber = TwoD[i][j];
		         }
		      }
		   }
		   return(maxNumber);
	}
}
