package org.dstadler.imaging.fuzz;

import org.apache.commons.imaging.ImageReadException;
import org.apache.commons.imaging.Imaging;

import java.io.IOException;

/**
 * This class provides a simple target for fuzzing Apache Commons Imaging with Jazzer.
 *
 * It uses the fuzzed input data to try to detect and read image-data.
 *
 * It catches all exceptions that are currently expected.
 */
public class Fuzz {
	public static void fuzzerTestOneInput(byte[] inputData) {
		try {
			Imaging.getAllBufferedImages(inputData);
		} catch (ImageReadException | IOException | IllegalArgumentException e) {
			// expected here
		}

		try {
			Imaging.getFormatCompliance(inputData);
		} catch (ImageReadException | IOException | IllegalArgumentException e) {
			// expected here
		}

		try {
			Imaging.getICCProfile(inputData);
		} catch (ImageReadException | IOException | IllegalArgumentException e) {
			// expected here
		}

		try {
			Imaging.getImageInfo(inputData);
		} catch (ImageReadException | IOException | IllegalArgumentException e) {
			// expected here
		}

		try {
			Imaging.getMetadata(inputData);
		} catch (ImageReadException | IOException | IllegalArgumentException e) {
			// expected here
		}

		try {
			Imaging.getXmpXml(inputData);
		} catch (ImageReadException | IOException | IllegalArgumentException e) {
			// expected here
		}

		try {
			Imaging.guessFormat(inputData);
		} catch (IOException | IllegalArgumentException e) {
			// expected here
		}
	}
}
