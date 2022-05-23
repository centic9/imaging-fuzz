package org.dstadler.imaging.fuzz;

import org.apache.commons.imaging.ImageReadException;
import org.apache.commons.imaging.Imaging;
import org.apache.commons.lang3.exception.ExceptionUtils;

import java.awt.image.RasterFormatException;
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
		} catch (ImageReadException | RasterFormatException | IOException | IllegalArgumentException e) {
			// expected here
		} catch (ArrayIndexOutOfBoundsException e) {
			// TODO: should be fixed in the library
			// https://issues.apache.org/jira/browse/IMAGING-334
			// https://issues.apache.org/jira/browse/IMAGING-276
			// https://issues.apache.org/jira/browse/IMAGING-277
		} catch (OutOfMemoryError e) {
			// TODO: should be fixed in the library
			// https://issues.apache.org/jira/browse/IMAGING-332
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
		} catch (ArrayIndexOutOfBoundsException e) {
			// TODO: should be fixed in the library
			// https://issues.apache.org/jira/browse/IMAGING-334
			// https://issues.apache.org/jira/browse/IMAGING-276
			// https://issues.apache.org/jira/browse/IMAGING-277
		}

		try {
			Imaging.getImageInfo(inputData);
		} catch (ImageReadException | IOException | IllegalArgumentException e) {
			// expected here
		} catch (IndexOutOfBoundsException e) {
			// TODO: should be fixed in the library at some point
			// https://issues.apache.org/jira/browse/IMAGING-333
		}

		try {
			Imaging.getMetadata(inputData);
		} catch (ImageReadException | IOException | IllegalArgumentException e) {
			// expected here
		} catch (NullPointerException e) {
			// TODO: ignore one NPE that should be fixed in the library
			// https://issues.apache.org/jira/browse/IMAGING-282
			if (!ExceptionUtils.getStackTrace(e).contains("GifImageParser.getMetadata")) {
				throw e;
			}
		} catch (ArrayIndexOutOfBoundsException e) {
			// TODO: should be fixed in the library
			// https://issues.apache.org/jira/browse/IMAGING-276
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
