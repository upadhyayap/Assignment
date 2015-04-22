/**
 * 
 */
package com.pramati.imaginea.utilities;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * @author anandu
 *
 */
public class Helper {

	/**
	 * 
	 */
	public static boolean isDirectMatchAvalaible(Set<String> Content,
			String searchword) {
		boolean matchAvalaible = false;
		if (Content != null && Content.contains(searchword)) {
			matchAvalaible = true;
		}
		return matchAvalaible;
	}

	public static boolean isZigZagMatchAvailaible(Set<String> Content,
			String searchWord, String delimiter) {
		boolean matchAvalaible = false;
		String[] sourceWord;
		String[] targetWord;
		if (searchWord != null) {
			sourceWord = searchWord.split(delimiter);
		} else {
			return false;
		}
		Iterator<String> contentIterator = Content.iterator();
		while (contentIterator.hasNext()) {
			targetWord = contentIterator.next().split(delimiter);
			if (findCommonData(sourceWord, targetWord).size() >= 2 ) {
				matchAvalaible = true;		
			}
		}
		return matchAvalaible;
	}

	public static Set<String> findCommonData(String[] sourceArray,
			String[] targetArray) {
		Set<String> common = new HashSet<String>(Arrays.asList(sourceArray));
		common.retainAll(new HashSet<>(Arrays.asList(targetArray)));
		return common;
	}
}
