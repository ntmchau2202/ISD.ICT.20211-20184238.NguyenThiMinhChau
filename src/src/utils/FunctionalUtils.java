package utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 
 * @author Hikaru
 *
 */
public class FunctionalUtils {
	public static boolean contains(String str, String regex) {
		Pattern p = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
		Matcher m = p.matcher(str);
		if(m.find()) {
			return true;
		}
		return false;
	}
}
