package org.dstadler.imaging.fuzz;

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

	@Disabled("Local test for verifying a slow run")
	@Test
	public void testSlowUnit() throws IOException {
		Fuzz.fuzzerTestOneInput(FileUtils.readFileToByteArray(new File("corpus/3035833feec28f5b4bc0ef33c5a7b9b348efe260")));
	}
}