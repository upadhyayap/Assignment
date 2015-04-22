/**
 * 
 */
package com.pramati.imaginea.bobj;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;
import com.pramati.imaginea.utilities.Helper;

/**
 * @author anandu
 *
 */
public class TextFile {

	private File sourceFile;
	private Set<String> content = new HashSet<String>();
	private boolean contentLoaded = false;

	public TextFile(File pFile) {
		this.sourceFile = pFile;
	}

	public Set<String> getContent() throws FileNotFoundException, IOException {
		if (!this.contentLoaded) {
			read();
		}
		return content;
	}

	public Set<String> intersection(TextFile targetFile)
			throws IllegalArgumentException, IOException, FileNotFoundException {
		Set<String> matchingContent = new TreeSet<String>();
		this.getContent();
		if (!this.content.isEmpty()) {
			Iterator<String> contentIterator = this.content.iterator();
			String lineData = "";
			while (contentIterator.hasNext()) {
				lineData = contentIterator.next();
				if (Helper.isDirectMatchAvalaible(targetFile.getContent(),
						lineData)) {
					matchingContent.add(lineData);
				} else if (Helper.isZigZagMatchAvailaible(
						targetFile.getContent(), lineData, " ")) {
					matchingContent.add(lineData);
				}
			}
		}
		return matchingContent;
	}

	private void read() throws IOException, FileNotFoundException {

		FileReader reader = new FileReader(sourceFile);
		BufferedReader buffer = new BufferedReader(reader);
		try {
			String PersonName = "";
			while (((PersonName = buffer.readLine()) != null)) {
				this.content.add(PersonName);
			}
			contentLoaded = true;
		} catch (IOException e) {
			throw e;
		} finally {
			try {
				buffer.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
