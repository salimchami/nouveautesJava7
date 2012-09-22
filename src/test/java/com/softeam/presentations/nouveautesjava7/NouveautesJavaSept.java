package com.softeam.presentations.nouveautesjava7;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;

import junit.framework.TestCase;

import org.junit.Test;

/**
 * Présentation SOFTEAM : Nouveautés de Java 7 <br />
 * 13/09/2012
 * 
 * @author Salim CHAMI
 * 
 */
public class NouveautesJavaSept extends TestCase {

	private String myFilePath = "D:/softeam/nio/test.txt";

	/**
	 * 1. Expression litérale binaire (Byte Code)
	 */
	@Test
	public void testNotationBinaire() {

		Byte aByte = (byte) 0b110;
		int anInt1 = 0b10100001010001011010000101000101;
		long aLong = 0b1010000101000101101000010100010110100001010001011010000101000101L;

		int myIntValue = aByte.intValue();

		assertEquals(-6825872339779608251L, aLong);
		assertEquals(-1589272251, anInt1);
		assertEquals(6, myIntValue);
	}

	/**
	 * 2. Formatage des expressions numériques
	 */
	@Test
	public void testFormatage() {
		double oneMilliard = 1__000_000_000.000_000;
		Long myLong = 1111_2222_3333_4444L;
		Long hexBytes = 0xFF_EC_DE_5EL;
		Long bytes = 0b11010010_01101001_10010100_10010010L;
		assertEquals(Double.valueOf("1000000000"), oneMilliard);
		assertEquals(Long.valueOf("1111222233334444"), myLong);
		assertEquals(Long.valueOf("4293713502"), hexBytes);
		assertEquals(Long.valueOf("3530134674"), bytes);
	}

	@Test
	public void testSwitchString() {
		String note = SwitchString.getMoodysNote("Europe");
		assertEquals("AA", note);
	}

	@Test
	public void testPath() throws URISyntaxException {
		Path path = Paths.get(myFilePath);
		assertEquals("test.txt", path.getFileName().toString());

		FileSystem myFileSystem = FileSystems
				.getFileSystem(new URI(myFilePath));
		assertEquals("NTFS", myFileSystem.toString());
	}

	@Test
	public void testFileVisitor() throws IOException {
		Path dir = Paths.get(myFilePath);
		Files.walkFileTree(dir, new SimpleFileVisitor<Path>() {
			@Override
			public FileVisitResult visitFile(Path file,
					BasicFileAttributes attrs) throws IOException {
				System.out.println(file);
				return FileVisitResult.CONTINUE;
			}
		});
		Files.walkFileTree(dir, new SimpleFileVisitor<Path>() {
		});

	}

	@Test
	public void testForkJoin() {
		Double result = CalculateTask.calculate(1500);
		assertEquals(Double.valueOf("2000"), result);
	}
}
