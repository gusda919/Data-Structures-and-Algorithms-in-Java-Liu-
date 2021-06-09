package wordChain;

class WordRec {
	private String word;
	private WordRec parent; // pekare till ordposten som skapat detta ord

	public WordRec(String word_, WordRec parent_) {
		word = word_;
		parent = parent_;
	}

	// ChainLength returnerar antalet ord i en kedja av ordposter.
	public int chainLength() {
		int i = 0;
		for (WordRec x = this; x != null; x = x.parent) {
			i++;
		}
		return i;
	}

	private void printChainHelp() {
		if (parent != null) {
			parent.printChainHelp();
		}
		printWord();
		System.out.print(" -> ");
	}

	// PrintChain skriver ut en kedja av ordposter.
	public void printChain() {
		if (parent != null) {
			parent.printChainHelp();
		}
		printWord();
		System.out.println();
	}

	public void printChain2() {
		printWord();
		if (parent != null) {

			System.out.print(" -> ");

			parent.printChain2();
		}

	}

	// PrintWord skriver ut ett ord.
	public void printWord() {
		System.out.print(word);
	}

	public String getWord() {
		return word;
	}

	public WordRec getParent() {
		return parent;
	}

}
