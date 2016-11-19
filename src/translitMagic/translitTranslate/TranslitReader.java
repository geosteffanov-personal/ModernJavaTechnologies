package translitMagic.translitTranslate;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;

public class TranslitReader extends Reader {
	private Reader in;
	private Translator translator;

	public TranslitReader(Reader in, Translator translator) {
		this.in = in;
		this.translator = translator;
	}

	@Override
	public void close() throws IOException {
		in.close();
	}

	@Override
	public int read(char[] cbuf, int off, int len) throws IOException {
		return in.read(cbuf, off, len);
	}

	@Override
	public int read() throws IOException {
		int result = in.read();

		if (result != -1) {
			return translator.translate((char) result);
		}

		return -1;
	}

	public static void main(String args[]) {
		File symbolTable = new File("src/translitMagic/translitTranslate/latin-ciryllic.config");
		Translator translator = new Translator(symbolTable);
		try (TranslitReader channel = new TranslitReader(new InputStreamReader(System.in, "UTF-8"), translator)) {
			int currentChar = 0;
			while (currentChar != -1) {
				currentChar = channel.read();
				System.out.print((char) currentChar);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
