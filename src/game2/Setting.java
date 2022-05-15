package game2;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;

public class Setting {
	private final String fileLocation = "src/game2/data/setting.txt";
	private final Map<String, String> setting = new HashMap<>();
	public void loadSetting() {
		try {
			BufferedReader bReader = new BufferedReader(new FileReader(new File(fileLocation)));
			String line;
			while ((line = bReader.readLine()) != null) {
				String[] comp = line.split("=", 2);
				setting.put(comp[0], comp[1]);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
