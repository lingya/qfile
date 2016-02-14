package qfile;

/**
 * 给字符串加密使用的类，目前共封装了四个静态方法。
 * 就目前来说，该类的加密过于简单，不推荐使用。
 * 日后会填坑的。
 * @author 肥肥的兔子
 * @version 2016-2-10
 *
 */
public class Encryption {
	
	/**
	 * 文章内容由整形的key加密，加密后转化成十六进制的形式，获取加密后的字符串，用数组表示
	 * @param content 需加密的文章
	 * @param key 密钥
	 * @return 加密后的字符串数组，每一个用十六进制表示
	 */
	public static String[] hexLock(String content, int key) {
		
		char[] ch = content.toCharArray();
		String[] result = new String[ch.length];
		
		for (int i = 0; i < ch.length; i++) {
			int m = (int) ch[i] + key;
			String hex = Integer.toHexString(m).toUpperCase();
			result[i] = "0x" + hex;
		}
		
		return result;
	}

	
	/**
	 * 文章内容由整形的key加密，加密后转化成十六进制的形式，获取加密后的字符串，用数组表示
	 * @param content 需加密的文章
	 * @param key 密钥
	 * @return 加密后的字符串数组，每一个用十六进制表示
	 */
	public static String[] hexLock(String content, char key) {
		int k = (int) key;
		return hexLock(content, k);
	}
	
	/**
	 * 文章内容由整形的key解密，获取解密后的字符串
	 * @param toTen 被加密后的十六进制字符串数组
	 * @param key 密钥
	 * @return 解密后的十六进制的字符串
	 */

	public static String hexOpen(String[] toTen, int key) {
		char[] result = new char[toTen.length];
		for (int i = 0; i < toTen.length; i++) {
			String str = toTen[i];
			String hex = str.replace("0x", "");
			int ten = Integer.parseInt(hex, 16);
			ten = ten - key;
			result[i] = (char) ten;
		}
		return new String(result);
	}

	
	/**
	 * 文章内容由字符形的key解密，获取解密后的字符串
	 * @param toTen 被加密后的十六进制字符串数组
	 * @param key 密钥
	 * @return 解密后的十六进制的字符串
	 */
	public static String hexOpen(String[] toTen, char key) {
		int k = (int) key;
		return hexOpen(toTen, k);
	}

}
