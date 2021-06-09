package wordChain;
import java.util.HashMap;
import java.util.Vector;
import java.io.*;

// Klassen WordList innehåller en ordlista och en datastruktur som håller
// reda på använda ord.

class WordList {

	static private HashMap<String,String> list; // ordlista
	static private HashMap<String,String> used; // databas med använda ord
    static int wordLength;
    static int size; // antal ord i ordlistan

    // Read läser in en ordlista från strömmen input. Alla ord ska ha
    // wordLength bokstäver.
    static public void read(int wordLength_, BufferedReader input) throws IOException {
	wordLength = wordLength_;
	size = 0;
	list = new HashMap<String,String>(size);
	while (true) {
	    String s = input.readLine();
	    if (s.equals("#")) {
		break;
	    }
	    if (s.length() != wordLength) {
		System.out.println("Rad " + size +
				   " i filen innehåller inte " +
				   wordLength + " tecken.");
	    }
	    list.put(s,s);
	    size++;
	}
	used = new HashMap<String,String>(size);
    }

    // WordAt returnerar ordet med angivet index i ordlistan.
    static public String wordAt(int index) {
	if (index >= 0 && index < size) {
	    return (String) list.values().toArray()[index];
	}
	return null;
    }

    // Contains slår upp w i ordlistan och returnerar ordet om det finns med.
    // Annars returneras null.
    static public String contains(String w) {
	if (list.containsKey(w)) {
	    return w;
	}
	return null;
    }

    // MarkAsUsedIfUnused kollar om w är använt tidigare och returneras i så
    // fall false. Annars markeras w som använt och true returneras.
    static public boolean markAsUsedIfUnused(String w) {
	if (used.containsKey(w)) {
	    return false;
	}
	used.put(w,w);
	return true;
    }

    // EraseUsed gör så att inga ord anses använda längre.
    static public void eraseUsed() {
	used.clear();
    }

}
