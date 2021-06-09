package hashtabeller;

import java.io.*;

class SymbolTableTest {

	static void printMenu() {
		final String newline = System.getProperty("line.separator");

		String strMenu = "+--- Hash tables ---" + newline;
		strMenu = strMenu + "r : Reset all" + newline;
		strMenu = strMenu + "H : Hash" + newline;
		strMenu = strMenu + "l : Lookup" + newline;
		strMenu = strMenu + "i : Insert" + newline;
		strMenu = strMenu + "d : Delete" + newline;
		strMenu = strMenu + "D : Dump hashtable" + newline;
		strMenu = strMenu + "s : Print size" + newline;
		strMenu = strMenu + "q : Quit program" + newline;
		strMenu = strMenu + "h : show this text" + newline;
		System.out.print(strMenu);
	}

	public static void main(final String[] args) throws IOException {
		final BufferedReader myIn = new BufferedReader(new InputStreamReader(System.in));

		String str;
		SymbolTable st = new SymbolTable();

		printMenu();

		while (true) {
			System.out.print("lab > ");
			char c = myIn.readLine().charAt(0);
			switch (c) {
			case 'r':
				st = new SymbolTable();
				break;
			case 'H':
				System.out.print("Hash string: ");
				str = myIn.readLine();
				System.out.print("hash(" + str + ") => ");
				System.out.println(Integer.toString(st.hash(str)));
				break;
			case 'l':
				System.out.print("Lookup string: ");
				str = myIn.readLine();
				System.out.print("lookup(" + str + ") => ");
				System.out.println(st.get(str));
				break;
			case 'i':
				System.out.print("Insert string: ");
				str = myIn.readLine();
				System.out.print("with type: ");
				st.put(str, myIn.readLine().charAt(0));

				break;
			case 'd':
				System.out.print("Delete string: ");
				str = myIn.readLine();
				st.delete(str);
				break;
			case 'D':
				st.dump();
				break;
			case 's':
				System.out.println("Table size: " + st.size());
				break;
			case 'q':
				System.out.println("Program terminated.");
				System.exit(0);
				break;
			case 'h':
				printMenu();
				break;
			default:
				System.out.print("**** Sorry, No menu item named '");
				System.out.println(c + "'");
			}
		}
	}
}
