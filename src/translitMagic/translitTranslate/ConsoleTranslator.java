package translitMagic.translitTranslate;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

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
		Translator translator = new Translator(symbolTable);
		try (TranslitReader channel = new TranslitReader(new InputStreamReader(System.in, "UTF-8"), translator)) {
			int currentChar = 0;
			while (!reachedEnd()) {
				currentChar = channel.read();
				Character symbol = new Character((char) currentChar);
				writer.write(symbol);
				writer.flush();
				updateLast(symbol);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void main(String args[]) throws IOException {
		FileWriter writer = new FileWriter(new File("src/translitMagic/translitTranslate/shliokavica.txt"));
		ConsoleTranslator translator = new ConsoleTranslator(writer);
		translator.read();
	}

}
