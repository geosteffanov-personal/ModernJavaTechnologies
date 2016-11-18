package translitMagic.translitTranslate;

import java.io.File;
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
			return translator.translate(new Character((char) result));
		}

		return -1;
	}

	public static void main(String args[]) {
		File symbolTable = new File("src/translitMagic/translitTranslate/latin-ciryllic.config");
		try {
			Translator translator = new Translator(symbolTable);
			TranslitReader channel = new TranslitReader(new InputStreamReader(System.in), translator);
			int currentChar = 0;
			while (currentChar != -1) {
				currentChar = channel.read();
					System.out.print(new Character((char) currentChar));
			}
			channel.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
