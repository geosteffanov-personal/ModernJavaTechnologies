package filesio.bg.unisofia.fmi.javasecourse.fileio;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class DirectoryTraversal {

	public static void main(String[] args) {
		Path rootDir = Paths.get("d:\\temp\\");
		try (DirectoryStream<Path> stream = 
				Files.newDirectoryStream(rootDir)) {
			for (Path path : stream) {
				if (Files.isDirectory(path)) {
					System.out.println("Directory " + path.getFileName());
				} else {
					System.out.println("File " + path.getFileName());
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
