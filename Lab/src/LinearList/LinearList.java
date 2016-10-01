package LinearList;



public interface LinearList <Item> {

	public boolean isEmpty();
	
	public int size();
	
	public Item get(int index);
	
	public int indexOf(Item item);
	
	public Item remove(int index);
	
	public void add(int index, Item item);
	
	public void output();
	
}
