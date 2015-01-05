package entities.inventory;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import entities.Item;

public class Inventory implements Iterable<Item> {

	private LinkedList<Item> items;
	private int max;

	public List<Item> getItems() {
		return items;
	}

	public Item get(int i) {
		return items.get(i);
	}

	public Inventory(int max) {
		this.max = max;
		items = new LinkedList<Item>();
	}

	public void add(Item item, boolean isReplacing) {
		if (!isFull() || isReplacing) {
			items.add(item);
		}
	}

	public void remove(Item item) {
		items.remove(item);
	}

	public boolean isFull() {
		return items.size() >= max;
	}

	public Iterator<Item> iterator() {
		return items.iterator();
	}

	public int size() {
		return items.size();
	}

	public void remove(int itemNumber) {
		if (itemNumber < size() && get(itemNumber) != null) {
			items.remove(itemNumber);
		}
	}

}