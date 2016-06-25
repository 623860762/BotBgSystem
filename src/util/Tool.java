package util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Tool {

	public static String inputStream2String(InputStream in) throws IOException {
		StringBuffer sb = new StringBuffer();
		BufferedReader br;
		String line;
		try {
			br = new BufferedReader(new InputStreamReader(in, "UTF-8"));
			while ((line = br.readLine()) != null) {
				sb.append(line);
			}
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		return sb.toString();
	}

	public static String readerBuffer2String(BufferedReader br) {
		StringBuffer sb = new StringBuffer();
		String line;
		try {
			while ((line = br.readLine()) != null) {
				sb.append(line);
			}
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		return sb.toString();
	}

	/**
	 * map转list
	 * 
	 * @param map
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static List map2List(Map map) {
		List list = new ArrayList();
		Set keySet = map.keySet();
		Iterator ir = keySet.iterator();
		while (ir.hasNext()) {
			list.add(map.get(ir.next()));
		}

		return list;
	}

	/**
	 * string变list
	 * 
	 * @param str
	 * @param div
	 *            分隔符
	 * @return
	 */
	public static List<String> string2list(String str, String div) {
		List<String> list = new ArrayList<String>();
		if (str == null || "".equals(str)) {
			return list;
		}
		String[] items = str.split(div);
		for (int i = 0, len = items.length; i < len; i++) {
			list.add(items[i]);
		}
		return list;
	}
}
