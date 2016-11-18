package translitMagic.translitTranslate;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Pattern;

public class ConsoleTranslator {
	private FileWriter writer;
	private String lastChars = "      ";
	String hello;

	private void updateLast(char newChar) {
			lastChars = lastChars.substring(1) + newChar;
	}

	private boolean reachedEnd() {
		return lastChars.matches("[\\s!,\\.]край[\\s!,\\.]");
	}

	public ConsoleTranslator(FileWriter writer) {
		this.writer = writer;
	}

	public void read() {
		File symbolTable = new File("src/translitMagic/translitTranslate/latin-ciryllic.config");
		try {
			Translator translator = new Translator(symbolTable);
			TranslitReader channel = new TranslitReader(new InputStreamReader(System.in), translator);
			int currentChar = 0;
			while (!reachedEnd()) {
				currentChar = channel.read();
				Character symbol = new Character((char) currentChar);
				writer.write(symbol);
				writer.flush();
				updateLast(symbol);
			}
			channel.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void main(String args[]) throws IOException {
		FileWriter writer = new FileWriter(new File("src/translitMagic/translitTranslate/output.txt"));
		ConsoleTranslator translator = new ConsoleTranslator(writer);
		translator.read();
	}

}

