package utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 
 * @author Hikaru
 *
 */
public class FunctionalUtils {
	/**
	 * Check if the input string contains characters in regex string
	 * @param str input string to be check
	 * @param regex regex string for checking
	 * @return true if the input string has characters in regex string <br> false otherwise
	 */
	public static boolean contains(String str, String regex) {
		Pattern p = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
		Matcher m = p.matcher(str);
		if(m.find()) {
			return true;
		}
		return false;
	}
}
