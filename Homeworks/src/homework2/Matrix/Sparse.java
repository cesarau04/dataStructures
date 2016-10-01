package Matrix;

/*
 * No finished yet.
 */

import LinearList.ChainLinearList;

@SuppressWarnings("unused")
class SparseMatrix<T> {
	private int rowL;
	private int colL;
	private ChainLinearList<T> dl;
	private T[][] Matrix;
	
	public SparseMatrix(T[][] matrix){
		dl = new ChainLinearList<T>();
		rowL = matrix.length;
		colL = matrix[0].length;
		
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[i].length; j++) {
				add(i+1, j+1, matrix[i][j]);
			}
		}
	}
	
	public void add(int i, int j, T value){
		--i;--j;
		if (i < 0 || j < 0 || i > rowL || j > colL)
			throw new IndexOutOfBoundsException();
	}
	
	public T get(int row, int column){
		return null;
	}
	
	public void remove(int row, int column){
		
	}
	
	public void output(){
		
	}
	
	
	private static class MNode<T>{
		T value;
		int i;
		MNode<T> next;
		
		
		public MNode(T value, MNode<T> next){
			this.value=value;
			this.next=next;
		}
	}
	
	public static void main(String[] args) {
	}
	
}
