import java.util.LinkedList;

public class ChainedHashMap<K, V> {
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String x = "asdfsd";
		System.out.println(x.hashCode());
		System.out.println(x.hashCode());
		System.out.println(x.hashCode());
		Integer y = 2;
		System.out.println(y.hashCode());
		System.out.println(y.hashCode());
		System.out.println(y.hashCode());
		ChainedHashMap<String, String> m = new ChainedHashMap<String, String>();
		m.put("Kou", "KouVal");
		m.put("Ren", "RenVal");
		m.put("kk", "kkval");
		System.out.println(m.get("Kou"));
		System.out.println(m.get("Ren"));
		System.out.println(m.get("kk"));

	}

	private final int MAX_SIZE = 10;

	private LinkedList<Entry<K, V>>[] items;

	public ChainedHashMap() {
		items = (LinkedList<Entry<K, V>>[]) new LinkedList[MAX_SIZE];

	}

	public int hashCodeOfKey(K key) {
		return key.toString().length() % items.length;
	}

	public void put(K key, V val) {
		int x = hashCodeOfKey(key);
		if (items[x] == null) {
			items[x] = new LinkedList<Entry<K, V>>();
		}

		LinkedList<Entry<K, V>> collided = items[x];

		for (Entry<K, V> e : collided) {
			if (e.equal(key)) {
				collided.remove();
				break;
			}
		}

		Entry<K, V> entry = new Entry<K, V>(key, val);
		collided.add(entry);

	}

	public V get(K key) {
		int x = hashCodeOfKey(key);
		if (items[x] == null) {
			return null;
		}

		LinkedList<Entry<K, V>> collided = items[x];

		for (Entry<K, V> e : collided) {
			if (e.equal(key)) {
				return e.getVal();
			}
		}

		return null;

	}

}

class Entry<K, V> {

	public K getKey() {
		return key;
	}

	public void setKey(K key) {
		this.key = key;
	}

	public V getVal() {
		return val;
	}

	public void setVal(V val) {
		this.val = val;
	}

	public Entry(K key, V val) {
		super();
		this.key = key;
		this.val = val;
	}

	public boolean equal(Entry<K, V> e) {
		return equal(e.getKey());
	}

	public boolean equal(K k) {
		return key.equals(k);
	}

	private K key;
	private V val;

}
