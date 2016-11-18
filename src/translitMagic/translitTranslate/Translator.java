package translitMagic.translitTranslate;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Set;

public class Translator {
	private HashMap<Character, Character> symbolTable;

	private void setUp(BufferedReader reader) {
		String line = null;
		try {
			while ((line = reader.readLine()) != null) {
				String[] pair = line.split(":");
				symbolTable.put(pair[0].charAt(0), pair[1].charAt(0));
			}
			symbolTable.put(' ',' ');
			symbolTable.put('\n', '\n');
			symbolTable.put('\r', '\r');
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public Character translate(Character key) {
		return symbolTable.get(key);
	}
	
	public char[] translate(char[] keys) {
		char[] result = new char[keys.length];
		for (int i = 0; i < keys.length; i++) {
				System.out.println("KEY:: " + keys[i]);
				result[i] = translate(keys[i]);
		}
		return result;
	}
	
	public boolean correctCharacter(Character key) {
		return symbolTable.containsKey(key);
	}
	
	public Translator(File configFile) throws FileNotFoundException {
		try (BufferedReader reader = new BufferedReader(new FileReader(configFile))) {
			symbolTable = new HashMap<Character, Character>();
			setUp(reader);
			reader.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public String tableString() {
		StringBuilder result = new StringBuilder();
		Set<Character> keys = symbolTable.keySet();
		for (Character key : keys) {
			result.append("\n" + key + ":" + symbolTable.get(key));
		}
		
		return result.toString();
	}
		
	public static void main (String args[]) {
		File file = new File("src/translitMagic/translitTranslate/latin-ciryllic.config");
		try {
			Translator translater = new Translator(file);
			System.out.println(translater.tableString());
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}

}
