package translitMagic.wordCount;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class WordCounter {
	private StringBuilder content;

	public WordCounter(File file) throws IOException {
		this.content = new StringBuilder();
		readFile(file);
	}

	private void readFile(File file) throws IOException {
		FileReader reader = new FileReader(file);
		int currentChar = 0;
		while (currentChar != -1) {
				currentChar = reader.read();

				if (currentChar == -1) {
					continue;
				}

				int index = "!?.,-+* \r\n".indexOf(currentChar);

				if (index == -1) {
					content.append((char) currentChar);
				} else if (index > 6) {
					content.append(' ');
				}
		}
		
		reader.close();

	}

	public int wordCount() {
		return content.toString().split("\\s+").length;
	}

	public String getString() {
		return content.toString();
	}

	public static void main(String args[]) {
		WordCounter counter;
		try {
			counter = new WordCounter(new File("src/translitMagic/translitTranslate/output.txt"));
			System.out.println(counter.getString());
			System.out.println(counter.wordCount());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
