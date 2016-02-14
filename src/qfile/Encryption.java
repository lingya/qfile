package qfile;

/**
 * ���ַ�������ʹ�õ��࣬Ŀǰ����װ���ĸ���̬������
 * ��Ŀǰ��˵������ļ��ܹ��ڼ򵥣����Ƽ�ʹ�á�
 * �պ����ӵġ�
 * @author �ʷʵ�����
 * @version 2016-2-10
 *
 */
public class Encryption {
	
	/**
	 * �������������ε�key���ܣ����ܺ�ת����ʮ�����Ƶ���ʽ����ȡ���ܺ���ַ������������ʾ
	 * @param content ����ܵ�����
	 * @param key ��Կ
	 * @return ���ܺ���ַ������飬ÿһ����ʮ�����Ʊ�ʾ
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
	 * �������������ε�key���ܣ����ܺ�ת����ʮ�����Ƶ���ʽ����ȡ���ܺ���ַ������������ʾ
	 * @param content ����ܵ�����
	 * @param key ��Կ
	 * @return ���ܺ���ַ������飬ÿһ����ʮ�����Ʊ�ʾ
	 */
	public static String[] hexLock(String content, char key) {
		int k = (int) key;
		return hexLock(content, k);
	}
	
	/**
	 * �������������ε�key���ܣ���ȡ���ܺ���ַ���
	 * @param toTen �����ܺ��ʮ�������ַ�������
	 * @param key ��Կ
	 * @return ���ܺ��ʮ�����Ƶ��ַ���
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
	 * �����������ַ��ε�key���ܣ���ȡ���ܺ���ַ���
	 * @param toTen �����ܺ��ʮ�������ַ�������
	 * @param key ��Կ
	 * @return ���ܺ��ʮ�����Ƶ��ַ���
	 */
	public static String hexOpen(String[] toTen, char key) {
		int k = (int) key;
		return hexOpen(toTen, k);
	}

}
