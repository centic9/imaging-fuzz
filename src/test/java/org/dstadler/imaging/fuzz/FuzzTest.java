package org.dstadler.imaging.fuzz;

import org.apache.commons.imaging.ImageReadException;
import org.apache.commons.imaging.Imaging;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

class FuzzTest {
	@Test
	public void test() {
		Fuzz.fuzzerTestOneInput(new byte[] {});
		Fuzz.fuzzerTestOneInput(new byte[] {1});
		Fuzz.fuzzerTestOneInput(new byte[] {'P', 'K'});
		Fuzz.fuzzerTestOneInput(new byte[] {'P', 'N', 'G'});
	}

	@Test
	public void testImageFile() throws IOException {
		byte[] bytes = FileUtils.readFileToByteArray(new File("src/test/resources/test.jpg"));
		Fuzz.fuzzerTestOneInput(bytes);
	}

	@Test
	public void testReproduceOOM() {
		byte[] input = java.util.Base64.getDecoder().decode("iVBORw0KGgoAAAAbaUNDUMlDQyCrbAAtGHZwQWdQyUNDIKtsAAAYiVBORw0KGgp1AAAASURBVA0KGgoAAAANSUhEUgAAACAAIAQACAJ/2QAAsnMAAAAAAElFTkRCYAAY");

		// fuzz-target works even though an OOM happens because it currently ignores OOM!
		Fuzz.fuzzerTestOneInput(input);
	}

	@Disabled("See https://issues.apache.org/jira/browse/IMAGING-332")
	@Test
	public void testReproduceOOM2() throws ImageReadException {
		byte[] input = java.util.Base64.getDecoder().decode("iVBORw0KGgoAAAAbaUNDUMlDQyCrbAAtGHZwQWdQyUNDIKtsAAAYiVBORw0KGgp1AAAASURBVA0KGgoAAAANSUhEUgAAACAAIAQACAJ/2QAAsnMAAAAAAElFTkRCYAAY");
		try {
			Imaging.getAllBufferedImages(input);
		} catch (IOException e) {
			// expected here
		}
	}

	@Disabled("Local test for verifying a slow run")
	@Test
	public void testSlowUnit() throws IOException {
		Fuzz.fuzzerTestOneInput(FileUtils.readFileToByteArray(new File("corpus/3035833feec28f5b4bc0ef33c5a7b9b348efe260")));
	}
}