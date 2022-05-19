package game2;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;

public class Setting {
	private static final String fileLocation = "src/game2/data/setting.txt";
	private static final Map<String, String> setting = new HashMap<>();
	public static void loadSetting() {
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
	public static int getSettingInt(String name) {
		return Integer.parseInt(getSettingStr(name));
	}
	public static String getSettingStr(String name) {
		return setting.get(name);
	}
	public static float getSettingFlt(String name) {
		return Float.parseFloat(getSettingStr(name));
	}
}
