package qfile;

import java.io.*;
import java.util.*;
/**
 * QTxt ��װ�˶���.txt�ļ��Ĳ�����
 * 
 * @author �ʷʵ�����
 * @version 2016-2-9
 */
public class QTxt extends File{

	private static final long serialVersionUID = 1L;
	
	private int length;
	private int line;
	private String path;
	private String content;
	
	private String lineSeparator ;


	private QTxt() {
		super("");
	}

	/**
	 * ����path�����ļ�����
	 * ����������IOException�Ὣ��ת��ΪQException�����׳�
	 * @param path �ļ�·��
	 * @throws QException ��IOExceptionת������
	 * 
	 */
	public QTxt(String path) throws QException {
		super(path);
		try{
			line = 0;
			this.path = path;
			content = new String();

			FileReader reader = new FileReader(path);
			char[] buffer = new char[1024];
			String newContent = null;

			int isEnd = 0;
			while ((isEnd = reader.read(buffer)) != -1) {
				if(isEnd != 1024){isEnd = isEnd % 1024;}
				//������1024��ʱ����ģ��ֹ��ȡbuff�ظ��Ĳ���
				newContent = new String(buffer, 0, isEnd);
				content = content + newContent;
			}
			length = content.length();
			for (int i = 0; i < buffer.length; i++) {
				char c = buffer[i];
				if (c == '\n') {
					line++;
				}
			}
			reader.close();
		}catch(IOException err){
			throw new QException(err.getMessage(),err.getCause());
		}
		
		lineSeparator = System.getProperty("line.separator","\r\n");
	}

	/**
	 * ����File���󹹽�QFile����
	 * ����������IOException�Ὣ��ת��ΪQException�����׳�
	 * @param filename File�ļ�����
	 * @throws QException ��IOExceptionת������
	 * 
	 */
	public QTxt(File filename) throws QException {
		this(filename.getPath());
	}

	/**
	 * ��ȡ�ļ�<b>����</b>����
	 * �����ļ��Ĵ�С
	 * @return ���ݳ���
	 * 
	 */
	public int getLength() {
		return length;
	}

	/**
	 * ��ȡ�ļ�����
	 * @return �ļ�����
	 * 
	 */
	public String getContent() {
		return content;
	}

	/**
	 * ��ȡ�ļ�����
	 * @return �ļ�����
	 *
	 */
	public int getLine() {
		return line + 1;
	}

	/**
	 * ���ļ����ݰ��зָ�
	 * @return �ָ���γɵ��ַ�������
	 * 
	 */
	public String[] getContentByLine() {
		return content.split(lineSeparator);
	}

	
	/**
	 * �������е�oldStr�滻��newStr
	 * �ò�������ֱ���޸Ķ����content����
	 * @param oldStr ��Ҫ�滻���ַ���
	 * @param newStr �滻�ɵ��ַ���
	 * @return �滻������ַ���
	 */
	public String replace(String oldStr, String newStr) {
		String str = new String(content);
		// ���츱������ֱ�Ӷ�content�����޸�
		return str.replace(oldStr, newStr);
	}

	/**
	 * �������е�oldStr�滻��newStr
	 * �ò�����ֱ���޸Ķ����content���Բ�д���ļ�
	 * 
	 * @param oldStr ��Ҫ�滻���ַ���
	 * @param newStr �滻�ɵ��ַ���
	 * @throws QException ��IOExceptionת������
	 */
	public void writeByReplaced(String oldStr, String newStr)
			throws QException {
		content = replace(oldStr, newStr);
		try{
			FileWriter fw = new FileWriter(path);
			fw.write(content);
			fw.flush();
			fw.close();
		}catch(IOException err){
			throw new QException(err.getMessage(),err.getCause());
		}
	}
	
	/**
	 * ����ת����д�룬ͬʱ���¸ö�������
	 * @param m ��ֵ����Ҫ�滻���ַ�����ӳ��ֵ���滻�ɵ��ַ���
	 * @throws QException ��IOExceptionת������
	 */
	
	public void writeByReplaced(Map<String,String> m)
			throws QException {
		content = this.replace(m);
		try{
			FileWriter fw = new FileWriter(path);
			fw.write(content);
			fw.flush();
			fw.close();
		}catch(IOException err){
			throw new QException(err.getMessage(),err.getCause());
		}
	}
	
	
	/**
	 * ��д�ļ����ݣ������Զ����¸ö��������ֵ
	 * @param str ����
	 * @throws QException IO����
	 */
	
	public void writeTxt(String str) throws QException{
		content = str;
		try{
			FileWriter fw = new FileWriter(path);
			fw.write(content);
			fw.flush();
			fw.close();
		}catch(IOException err){
			throw new QException(err.getMessage(),err.getCause());
		}
		
	}

	/**
	 * �滻���з���
	 * @param oldStr ��Ҫ�滻���ַ���
	 * @param newStr �滻�ɵ��ַ���
	 * @return �������ַ�������
	 */
	public String[] replaceAndSplit(String oldStr, String newStr) {
		return replace(oldStr, newStr).split(lineSeparator);
	}

	/**
	 * �滻��ָ����־����
	 * @param oldStr ��Ҫ�滻���ַ���
	 * @param newStr �滻�ɵ��ַ���
	 * @param flag �ָ��־
	 * @return �������ַ�������
	 */
	public String[] replaceAndSplit(String oldStr, String newStr,
			String flag) {
		return replace(oldStr, newStr).split(flag);
	}


	 /**
	  * �����滻�������޸ĸö��������
	  * @param m ��ֵ����Ҫ�滻���ַ�����ӳ��ֵ���滻�ɵ��ַ���
	  * @return �����滻������ַ���
	  */
	 public String replace(Map<String,String> m){
		 String str = new String(content);
		 for(Map.Entry<String,String> e : m.entrySet()){
			 str = str.replace(e.getKey(), e.getValue());
		 }
		 return str;
	 }

	 /**
	  * ��m��key�����m��value�����
	  * @param m ��ֵ����Ҫ�滻���ַ�����ӳ��ֵ���滻�ɵ��ַ���
	  * @return �����滻������ַ�������
	  */
	 public String[] replaceAndSplit(Map<String,String> m){
		 String str[] = this.replace(m).split(this.lineSeparator);
		 return str;
	 }
	 
	 /**
	  * ��m��key�����m��value��flag�ָ�
 	  * @param m ��ֵ����Ҫ�滻���ַ�����ӳ��ֵ���滻�ɵ��ַ���
	  * @return �����滻������ַ�������
	  * @param flag �и��־
	  */
	 public String[] replaceAndSplit(Map<String,String> m,String flag){
		 String str[] = this.replace(m).split(flag);
		 return str;
	 }
	
	/**
	 * ��flag�ָ�Ҳ������з�
	 * @param flag �ָ��־
	 * @return �ָ����ַ�������
	 */
	public String[] split(String flag) {
		String toSplit = content.replace(this.lineSeparator, "");
		return toSplit.split(flag);
	}



	/**
	 * ͳ���ַ������ִ���
	 * @param word ��Ҫͳ�Ƶĵ���
	 * @return ͳ�ƴ���
	 */
	public int countWord(String word) {
		int sum = 0;
		int index = 0;
		int location = 0;
		while ((index = content.indexOf(word, location)) != -1) {
			location = index + word.length();
			sum++;
		}
		return sum;
	}

	/**
	 * ����ͳ���ַ������ִ���
	 * @param word ��Ҫͳ�Ƶĵ���
	 * @return key Ϊ�ַ�����valueΪ���ִ����Ĺ�ϣ��
	*/
	 public HashMap<String,Integer> countWord(String[] word) {
		 HashMap<String,Integer> m = new HashMap<String,Integer>();
		 for(int i=0;i<word.length;i++){
			 int number = this.countWord(word[i]);
			 m.put(word[i],number);
		 }
		 return m;
	 }

	private final String hexSplit = "    ";// ʮ����������Ĭ����4�ո�ָ�
	
	/**
	 * ��������key���� 
	 * @param key ����Կ��
	 * @throws QException ��IOExceptionת��
	 */
	public void hexLock(int key) throws QException {
		try{
			FileWriter fw = new FileWriter(path);
			BufferedWriter bw = new BufferedWriter(fw);
			String[] result = Encryption.hexLock(content, key);
			for (int i = 0; i < result.length; i++) {
				bw.write(result[i] + hexSplit);
				if ((i + 1) % 8 == 0) {
					bw.newLine();
				}
				bw.flush();
			}
			bw.close();
		}catch(IOException err){
			throw new QException(err.getMessage(), err.getCause());
		}
		
	}

	/**
	 * �����ַ���key���� 
	 * @param key ����Կ��
	 * @throws QException ��IOExceptionת��
	 */
	public void hexLock(char key) throws QException {
		int k = (int) key;
		this.hexLock(k);
	}

	/**
	 * ��������key���� 
	 * @param key ����Կ��
	 * @throws QException ��IOExceptionת��
	 */
	public void hexOpen(int key) throws	QException {
		try{
			FileReader fr = new FileReader(path);
			BufferedReader br = new BufferedReader(fr);
			char[] cbuf = new char[1024];
			int len = 0;
			String hex = new String();
			while ((len = br.read(cbuf)) != -1) {
				String str = new String(cbuf, 0, len);
				hex += str;
			}
			br.close();
	
			String[] toTen = hex.replace("\r\n", "").split(hexSplit);
			// ȥ�����ܺ��´��ڵĻ��з���ԭ�����ڵĻ��з��Ѿ������16����
			String result = Encryption.hexOpen(toTen, key);
	
			FileWriter fw = new FileWriter(path);
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(result);
			bw.flush();
			bw.close();
		}catch(IOException err){
			throw new QException(err.getMessage(), err.getCause());
		}
		
	}
	/**
	 * �����ַ���key���� 
	 * @param key ����Կ��
	 * @throws QException ��IOExceptionת��
	 */

	public void hexOpen(char key) throws QException {
		int k = (int) key;
		this.hexOpen(k);
	}

}
