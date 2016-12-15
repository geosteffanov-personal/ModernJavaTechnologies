package filesio.bg.unisofia.fmi.javasecourse.fileio;

import java.net.URI;
import java.nio.file.DirectoryStream;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.HashMap;
import java.util.Map;

public class Archiver {

	public static void main(String[] args) throws Exception {
		Map<String, String> env = new HashMap<>();
		env.put("create", "true");
		URI zipfsUri = URI.create("jar:file:///temp/javacourse/lectures.zip");
		try (FileSystem zipFs = FileSystems.newFileSystem(zipfsUri, env)) {		
			Path javacourse = Paths.get("c:\\temp\\javacourse");
			DirectoryStream<Path> stream = Files.newDirectoryStream(javacourse);
			for (Path path : stream) {
				if (path.getFileName().toString().endsWith(".pdf")) {
					System.out.println("Adding " + path.getFileName().toString());
					Path pathInZip = zipFs.getPath("/" + path.getFileName().toString());
					Files.copy(path, pathInZip, StandardCopyOption.REPLACE_EXISTING);
				}
			}
		}
	}
}
