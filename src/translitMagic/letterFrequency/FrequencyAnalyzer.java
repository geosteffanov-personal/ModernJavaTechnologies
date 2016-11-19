package translitMagic.letterFrequency;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class FrequencyAnalyzer {
	private class Pair implements Comparable<Pair> {
		private Character letter;
		private Integer frequency;
		
		public Pair(Character character, Integer integer) {
			this.letter = character;
			this.frequency = integer;
		}

		@Override
		public int compareTo(Pair other) {
			return other.frequency - frequency;
		}
		
		public String getString() {
			return letter + "::" + frequency;
		}
		
	}
	
	private Map<Character, Integer> frequencies;
	
	private void setUp(BufferedReader reader) {
		String line = null;
		try {
			while ((line = reader.readLine()) != null) {
				String[] pair = line.split(":");
				frequencies.put(pair[1].charAt(0), 0);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public FrequencyAnalyzer(File alphabet) {
		try (BufferedReader reader = new BufferedReader(new FileReader(alphabet))) {
			frequencies = new HashMap<Character, Integer>();
			setUp(reader);
			reader.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void countFrequency(FileReader reader) {
		int currentChar = 0;
		while (currentChar != -1) {
				try {
					currentChar = reader.read();
					Character currentObject = new Character((char) currentChar);
					if (frequencies.containsKey(currentObject)) {
						frequencies.replace(currentObject, frequencies.get(currentObject) + 1);
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		
		try {
			reader.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String highestFrequencies() {
		StringBuilder result = new StringBuilder();
		
		
		Set<Character> keys = frequencies.keySet();
		Pair[] frequencyList = new Pair[keys.size()];
		
		
		
		
		int i = 0;
		for (Character character : keys) {
			frequencyList[i] = new Pair(character, frequencies.get(character));
			i++;
		}
		
		Arrays.sort(frequencyList);
		
		for (int j = 0; j < 7; j++) {
			result.append(frequencyList[j].getString() + "\n");
		}
		
		
		return result.toString();
	}

	
	public static void main (String args[]) {
		FrequencyAnalyzer analyzer = new FrequencyAnalyzer(new File("src/translitMagic/letterFrequency/latin-ciryllic-letters.config"));
		try {
			analyzer.countFrequency(new FileReader (new File("src/translitMagic/letterFrequency/test.txt")));
			System.out.println(analyzer.highestFrequencies());
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
