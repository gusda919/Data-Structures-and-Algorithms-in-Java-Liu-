package hashtabeller;

public class SymbolTable {
	private static final int INIT_CAPACITY = 7;

	/* Number of key-value pairs in the symbol table */
	private int N;
	/* Size of linear probing table */
	private int M;
	/* The keys */
	private String[] keys;
	/* The values */
	private Character[] vals;

	/**
	 * Create an empty hash table - use 7 as default size
	 */
	public SymbolTable() {
		this(INIT_CAPACITY);
	}

	/**
	 * Create linear probing hash table of given capacity
	 */
	public SymbolTable(int capacity) {
		N = 0;
		M = capacity;
		keys = new String[M];
		vals = new Character[M];
	}

	/**
	 * Return the number of key-value pairs in the symbol table
	 */
	public int size() {
		return N;
	}

	/**
	 * Is the symbol table empty?
	 */
	public boolean isEmpty() {
		return size() == 0;
	}

	/**
	 * Does a key-value pair with the given key exist in the symbol table?
	 */
	public boolean contains(String key) {
		return get(key) != null;
	}

	/**
	 * Hash function for keys - returns value between 0 and M-1
	 */
	public int hash(String key) {
		int i;
		int v = 0;

		for (i = 0; i < key.length(); i++) {
			v += key.charAt(i);
		}
		return v % M;
	}

	/**
	 * Insert the key-value pair into the symbol table
	 */
	public void put(String key, Character val) {

		if (val == null || val == '\n') {
			delete(key);
			return;
		}

		for (int i = hash(key); i < M; i++) {

			if (keys[i] == null) {
				keys[i] = key;

				vals[i] = val;
				N++;

				break;

			}

			if (keys[i] != null) {

				if (keys[i].equals(key)) {
					vals[i] = val;
					break;

				}

			}

			if (i + 1 == M) {
				i = -1;
			} else if (i + 1 == hash(key)) {
				break;
			}
		}
	}

	/**
	 * Return the value associated with the given key, null if no such value
	 */
	public Character get(String key) {

		for (int i = hash(key); i < M; i++) {
			if (keys[i] == null)
				break;

			if (keys[i] != null && keys[i].equals(key))
				return this.vals[i];
			if (i + 1 == M)
				i = -1;
			if (i + 1 == hash(key))
				break;
		}

		return null;
	}

	// dummy code

	/**
	 * Delete the key (and associated value) from the symbol table
	 */

	/*
	 * TODO komplettering: Testerna misslyckas när vi tar bort element som ligger på
	 * sista platsen i tabellen och har flera element på plats 0,1,2.. som bör
	 * flyttas tillbaka närmare sina egentliga platser. Det är bara det närmsta
	 * elementet som flyttas nu.
	 */

	/*
	 * Svar: Delete borde vara fixade så att alla element flyttas då vi använders
	 * oss av 2 for-loopar
	 * 
	 * 
	 * 
	 */

	public void delete(String key) {

		for (int i = hash(key); i < M; i++) {

			if (keys[i] != (null) && keys[i].equals(key)) {
				keys[i] = null;
				vals[i] = null;
				N--;

				for (int j = i + 1; j <= M; j++) {

					if (keys[j] == null)
						break;

					int hash = hash(keys[j]);

					if (hash != j) {

						if (keys[hash] != null) {
							if (j != 0 && keys[j - 1] == null) {
								put(keys[j], vals[j]);
								keys[j] = null;
								vals[j] = null;
								N--;
							}
						}

						if (keys[hash] == null) {
							put(keys[j], vals[j]);
							keys[j] = null;
							vals[j] = null;
							N--;
						}

					}

					if (j + 1 == M) {
						j = -1;
					} else if (j + 1 == i) {
						break;
					}
				}

			}

			if (i + 1 == M) {
				i = -1;
			} else if (i + 1 == hash(key)) {
				break;
			}
		}

	}

	/**
	 * Print the contents of the symbol table
	 */
	public void dump() {
		String str = "";

		for (int i = 0; i < M; i++) {
			str = str + i + ". " + vals[i];
			if (keys[i] != null) {
				str = str + " " + keys[i] + " (";
				str = str + hash(keys[i]) + ")";
			} else {
				str = str + " -";
			}
			System.out.println(str);
			str = "";
		}
	}
}
