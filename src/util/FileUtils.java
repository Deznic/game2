package util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class FileUtils {
	public static String readShaderFile(String filepath) {
		StringBuilder str = new StringBuilder();
		try {
			FileReader fReader = new FileReader(new File(filepath));
			BufferedReader reader = new BufferedReader(fReader);
			String line;
			while ((line = reader.readLine()) != null) {
				str.append(line).append("\n");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return str.toString();
	}
	
}
