package qfile;

import java.io.*;
import java.util.*;
/**
 * QTxt 封装了对于.txt文件的操作。
 * 
 * @author 肥肥的兔子
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
	 * 根据path构建文件对象
	 * 过程中遇到IOException会将其转发为QException进行抛出
	 * @param path 文件路径
	 * @throws QException 由IOException转化而来
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
				//不等于1024的时候，求模防止读取buff重复的部分
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
	 * 根据File对象构建QFile对象
	 * 过程中遇到IOException会将其转发为QException进行抛出
	 * @param filename File文件对象
	 * @throws QException 由IOException转化而来
	 * 
	 */
	public QTxt(File filename) throws QException {
		this(filename.getPath());
	}

	/**
	 * 获取文件<b>内容</b>长度
	 * 并非文件的大小
	 * @return 内容长度
	 * 
	 */
	public int getLength() {
		return length;
	}

	/**
	 * 获取文件内容
	 * @return 文件内容
	 * 
	 */
	public String getContent() {
		return content;
	}

	/**
	 * 获取文件行数
	 * @return 文件行数
	 *
	 */
	public int getLine() {
		return line + 1;
	}

	/**
	 * 对文件内容按行分割
	 * @return 分割后形成的字符串数组
	 * 
	 */
	public String[] getContentByLine() {
		return content.split(lineSeparator);
	}

	
	/**
	 * 对文章中的oldStr替换成newStr
	 * 该操作不会直接修改对象的content属性
	 * @param oldStr 需要替换的字符串
	 * @param newStr 替换成的字符串
	 * @return 替换后的新字符串
	 */
	public String replace(String oldStr, String newStr) {
		String str = new String(content);
		// 创造副本以免直接对content进行修改
		return str.replace(oldStr, newStr);
	}

	/**
	 * 对文章中的oldStr替换成newStr
	 * 该操作会直接修改对象的content属性并写入文件
	 * 
	 * @param oldStr 需要替换的字符串
	 * @param newStr 替换成的字符串
	 * @throws QException 由IOException转化而来
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
	 * 批量转换后写入，同时更新该对象属性
	 * @param m 键值是需要替换的字符串，映射值是替换成的字符串
	 * @throws QException 由IOException转化而来
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
	 * 重写文件内容，并会自动更新该对象的属性值
	 * @param str 内容
	 * @throws QException IO错误
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
	 * 替换后按行分组
	 * @param oldStr 需要替换的字符串
	 * @param newStr 替换成的字符串
	 * @return 分组后的字符串数组
	 */
	public String[] replaceAndSplit(String oldStr, String newStr) {
		return replace(oldStr, newStr).split(lineSeparator);
	}

	/**
	 * 替换后按指定标志分组
	 * @param oldStr 需要替换的字符串
	 * @param newStr 替换成的字符串
	 * @param flag 分割标志
	 * @return 分组后的字符串数组
	 */
	public String[] replaceAndSplit(String oldStr, String newStr,
			String flag) {
		return replace(oldStr, newStr).split(flag);
	}


	 /**
	  * 批量替换，不会修改该对象的属性
	  * @param m 键值是需要替换的字符串，映射值是替换成的字符串
	  * @return 批量替换后的新字符串
	  */
	 public String replace(Map<String,String> m){
		 String str = new String(content);
		 for(Map.Entry<String,String> e : m.entrySet()){
			 str = str.replace(e.getKey(), e.getValue());
		 }
		 return str;
	 }

	 /**
	  * 把m的key代替成m的value后分行
	  * @param m 键值是需要替换的字符串，映射值是替换成的字符串
	  * @return 批量替换后的新字符串数组
	  */
	 public String[] replaceAndSplit(Map<String,String> m){
		 String str[] = this.replace(m).split(this.lineSeparator);
		 return str;
	 }
	 
	 /**
	  * 把m的key代替成m的value后按flag分割
 	  * @param m 键值是需要替换的字符串，映射值是替换成的字符串
	  * @return 批量替换后的新字符串数组
	  * @param flag 切割标志
	  */
	 public String[] replaceAndSplit(Map<String,String> m,String flag){
		 String str[] = this.replace(m).split(flag);
		 return str;
	 }
	
	/**
	 * 按flag分割，且不带换行符
	 * @param flag 分割标志
	 * @return 分割后的字符串数组
	 */
	public String[] split(String flag) {
		String toSplit = content.replace(this.lineSeparator, "");
		return toSplit.split(flag);
	}



	/**
	 * 统计字符串出现次数
	 * @param word 需要统计的单词
	 * @return 统计次数
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
	 * 批量统计字符串出现次数
	 * @param word 需要统计的单词
	 * @return key 为字符串，value为出现次数的哈希表
	*/
	 public HashMap<String,Integer> countWord(String[] word) {
		 HashMap<String,Integer> m = new HashMap<String,Integer>();
		 for(int i=0;i<word.length;i++){
			 int number = this.countWord(word[i]);
			 m.put(word[i],number);
		 }
		 return m;
	 }

	private final String hexSplit = "    ";// 十六进制数间默认用4空格分割
	
	/**
	 * 利用整形key加密 
	 * @param key 加密钥匙
	 * @throws QException 由IOException转换
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
	 * 利用字符形key加密 
	 * @param key 加密钥匙
	 * @throws QException 由IOException转换
	 */
	public void hexLock(char key) throws QException {
		int k = (int) key;
		this.hexLock(k);
	}

	/**
	 * 利用整形key解密 
	 * @param key 解密钥匙
	 * @throws QException 由IOException转换
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
			// 去除加密后新存在的换行符，原本存在的换行符已经变成了16进制
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
	 * 利用字符形key解密 
	 * @param key 解密钥匙
	 * @throws QException 由IOException转换
	 */

	public void hexOpen(char key) throws QException {
		int k = (int) key;
		this.hexOpen(k);
	}

}
