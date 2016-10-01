package DLinkedList;

public interface List<Item> {

	public boolean isEmpty();
	
	public int size();
	
	public Item get(int index);
	
	public int indexOf(Item item);
	
	public Item remove(int index);
	
	public void add(Item item, int index);
	
	public String toString();
	
}