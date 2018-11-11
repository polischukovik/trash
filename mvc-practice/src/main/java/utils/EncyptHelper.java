package utils;

import java.nio.charset.StandardCharsets;

import com.google.common.hash.Hashing;

public class EncyptHelper {
	
	public static String toSha256(String originalString) {
		String sha256hex = Hashing.sha256()
				  .hashString(originalString, StandardCharsets.UTF_8)
				  .toString();
		return sha256hex;
	}
	
}
