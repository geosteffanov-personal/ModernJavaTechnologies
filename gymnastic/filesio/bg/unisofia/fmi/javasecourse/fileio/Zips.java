package filesio.bg.unisofia.fmi.javasecourse.fileio;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;

public class Zips {

	public static void main(String[] args) throws IOException {
		try (ZipFile zip = new ZipFile("c:\\temp\\javacourse\\streams.zip")) {
			Enumeration<? extends ZipEntry> entries = zip.entries();
			while (entries.hasMoreElements()) {
				ZipEntry zipEntry = entries.nextElement();
				System.out.println("Entry name: " + zipEntry.getName());
				if (!zipEntry.isDirectory()) {
					printEntry(zip.getInputStream(zipEntry));
				} else {
					System.out.println("This is a directory");
				}
				System.out.println("==========================");
			}
		}
		
		try (FileOutputStream fos = new FileOutputStream(new File("c:\\temp\\javacourse\\lorem-ipsum.zip"));
				ZipOutputStream zos = new ZipOutputStream(fos)) {
			ZipEntry zipEntry  = new ZipEntry("content/loremipsum.txt");
			zos.putNextEntry(zipEntry);
			zos.write(loremIpsum().getBytes());
			zos.closeEntry();
			
			ZipEntry newEntry = new ZipEntry("blahblah.txt");
			zos.putNextEntry(newEntry);
			zos.write("Blah Blah".getBytes());
			zos.closeEntry();
		}
	}

	private static void printEntry(InputStream inputStream) throws IOException {
		try (InputStreamReader isr = new InputStreamReader(inputStream);
				BufferedReader br = new BufferedReader(isr)) {
			String line;
			while ((line = br.readLine()) != null) {
				System.out.println(line);
			}
		}
	}
	
	private static String loremIpsum() {
		return "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Aliquam sodales ultrices lectus, vitae consequat magna. Suspendisse nec massa risus. Praesent in libero finibus, aliquet lorem non, efficitur lacus. Sed posuere et felis id malesuada. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Sed vestibulum, ligula vitae malesuada gravida, ligula metus maximus nunc, fermentum elementum enim augue vitae justo. Donec porta scelerisque elit nec viverra. Sed vestibulum est finibus velit lobortis bibendum. Morbi mattis lacus odio, ut suscipit nunc pellentesque elementum.\n\nNulla lacinia ex eget diam rhoncus lacinia. Praesent quis iaculis est. Nulla vestibulum nisi interdum lacus finibus vestibulum. Aenean at velit eu justo tincidunt aliquet. Etiam interdum egestas ornare. Etiam vel est molestie est cursus dictum. Morbi commodo maximus ex id volutpat. Mauris sed orci sodales, venenatis tortor sed, dapibus lorem. Aliquam erat volutpat. Phasellus non massa ac augue ornare pellentesque lacinia nec massa. Cras suscipit hendrerit nisi. Morbi ultrices sapien porttitor sem consequat venenatis. Aenean a porta neque. Integer pulvinar suscipit neque, nec commodo tortor dictum et. Maecenas sed dictum nunc.Etiam id justo non sem aliquet fringilla. Maecenas sed commodo est. Integer tempor, tortor non ultrices consequat, ipsum massa iaculis augue, non pulvinar ex urna sed ligula. Donec orci erat, tempus ut diam at, suscipit ultrices nisi. Pellentesque ultricies sed nisi id efficitur. Praesent fermentum efficitur tincidunt. Interdum et malesuada fames ac ante ipsum primis in faucibus.\n\nVivamus vehicula malesuada ligula. Vivamus urna turpis, bibendum a vehicula eget, egestas vitae diam. Proin nec sem justo. Nullam pharetra mauris eu libero rutrum, in blandit nisi lacinia. Nam purus ante, suscipit ut fringilla et, mollis ultricies orci. Donec ut orci aliquet, vehicula elit varius, facilisis quam. Donec gravida risus ac nisl pharetra, efficitur sollicitudin enim laoreet. Sed vel metus sit amet eros maximus ultricies. Phasellus a ligula tellus. Nullam vestibulum quam neque, id euismod velit porttitor vel. Nam et arcu scelerisque, tincidunt enim sit amet, lobortis nisl. Fusce volutpat, enim ac tempor condimentum, sapien urna mattis tellus, in fringilla mi odio a orci. In eget tellus ante. Nullam eros arcu, laoreet quis enim a, facilisis imperdiet elit.\n\nNullam vehicula finibus purus a maximus. Sed quis arcu venenatis, tempor turpis ut, luctus nisl. Donec nisl tortor, fringilla convallis nulla eget, auctor facilisis arcu. Praesent eu erat ipsum. Nunc tempus nulla ac nibh tincidunt, id rhoncus tellus placerat. Sed quis nisl cursus tellus pellentesque sagittis sit amet vitae urna. Praesent malesuada ipsum felis, eget auctor justo tincidunt eu. Quisque vehicula ultricies leo. Nam lectus eros, sagittis nec ligula quis, lobortis malesuada ex. Quisque sollicitudin turpis nec dictum lobortis. Nulla facilisi. Pellentesque sed eleifend felis. Donec mattis nisi eget turpis egestas mattis. Pellentesque odio dolor, vestibulum id lorem eu, semper ullamcorper dolor. Morbi placerat tempor sapien, eget consectetur turpis tempor ut.";
	}
}
