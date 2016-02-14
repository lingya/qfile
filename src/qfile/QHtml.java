package qfile;

import java.io.*;
import java.util.*;

import javax.swing.text.html.*;

/**
 * ����HTML���ļ��ࡣ<br>
 * ���ݱ�ǩ���Ʒ��ظñ�ǩ���ɵĽڵ����<br>
 * �ɽ���<code>QHtml.buildHtml()</code>ͨ���ַ��������ļ�����ʽ�����ɲ�����QHtml.Html�Ķ���
 * @author �ʷʵ�����
 * @version 2016-02-10
 *
 */

public class QHtml extends File{

	private static final long serialVersionUID = 1L;
	
	/**
	 * �ڵ������,���������ⲿֱ�Ӵ���ʵ��
	 * ���ǿ���ͨ��QHtml.NodeArray��ʹ����ʵ��
	 * @author �ʷʵ�����
	 * @version 2016-02-10
	 */
	public class NodeArray{
		private ArrayList<Node> list;
		
		
		@SuppressWarnings("unused")
		private NodeArray(){}
		
		/**
		 * ����list����Node�ڵ�
		 * @param list �ڵ��б�
		 * @return �ڵ�����
		 */
		protected NodeArray(ArrayList<Node> list){
			this.list = list;
		}
		/**
		 * ��ȡ�����е�Node�ڵ�
		 * @param index �±�
		 * @return �ڵ�����
		 * @throws QException �±�Խ�紥���쳣
		 */
		public Node get(int index) throws QException{
			if(index<0||index>=list.size()){throw new QException("�±�����0���±�Խ��");}
			return list.get(index);
		}
		
		/**
		 * ��ȡ���鳤��
		 * @return ���鳤��
		 */
		public int size(){
			return list.size();
		}
	}
	////////////////////////////////////////
	
	/**
	 * �ڲ��࣬����HTML�ı�ǩ�ڵ㣬
	 * ������ʹ��<code>new</code>����ʵ����
	 * ������ͨ��<code>buildNode()</code>���һ��ʵ��
	 * @author �ʷʵ�����
	 * @version 2016-02-10
	 *
	 */
	public class Node{
		
		private String name;
		private Html innerHTML;
		private String innerText;
		private HashMap<String,String> map;
		
		@SuppressWarnings("unused")
		private Node(){}
		
		/**
		 * ����str����Node�ڵ�
		 * @param str
		 */
		protected Node(String str){
			this.innerText="";
			map = new HashMap<String,String>();
			char ch;
			int index = -1;
			do{
				index++;
				ch = str.charAt(index);
				
			}while(ch!=' '&&ch!='>');
			this.name = str.substring(1,index);
			//��ȡ����
			
			int flagIndex = str.indexOf(">");
			//�����ı�ǩ����λ��
			if(index != flagIndex){
				//�ñ�ǩ��Ҫ��ȡ���� 

				String sub = str.substring(1, flagIndex).replace("\"", "");
				while((sub.indexOf(" =")!=-1)||(sub.indexOf("= ")!=-1)){
					sub=sub.replace(" =", "=").replace("= ", "=");
					//����Ƿ��ո�
				}
				String[] s =sub.split(" ");
				for(String s1:s){
					if(s1.indexOf('=') != -1){
						String[] v = s1.split("=");
						String key = v[0];
						String value = null;
						if(v.length>=1){value= v[1];}
						map.put(key, value);
					}
				}
			}
			//��ȡ����
			
			int endIndex = str.indexOf("</"+this.name+">");
			//������־����
			this.innerHTML = new Html(str.substring(flagIndex+1, endIndex));
			//��ȡ��ǩ���ȫ������
			
			int begin2 = str.indexOf("<", flagIndex);
			//�ڶ���<��ʼ��λ��
			if(begin2 != endIndex){
				//֤���ñ�ǩ�м�������ַ��ͱ�ǩ
				String left = null;
				if(flagIndex +1 != begin2){
					//�м����ַ�
					left = str.substring(flagIndex+1, begin2);
				}
				//���ӱ�ǩ�󲿴���
				
				int last = 0;
				int now = 0;
				String right = null;
				while((now = str.indexOf(">",now+1))!=-1){
					if(now<endIndex){last = now;}
					//�����ڶ���>
				}
				String temp = str.substring(last+1, endIndex);
				if(temp.length()>0){right = temp;}
				if(left!=null){this.innerText += left;}
				if(right!=null){this.innerText += right;}
				//���ӱ�ǩ�Ҳ�����
			}else{
				//�м�ֻ���ַ�
				
//				System.out.println(str+"  "+ begin2 + "==" + endIndex);
				this.innerText = innerHTML.getContent();
				
			}
			this.innerText = innerText.replace("&nbsp;", " ");
		}
		/**
		 * ��ȡ��ǩӵ�����Եĸ���
		 * @return ����
		 */
		public int size(){
			return map.size();
		}
		/**
		 * ��ȡ�ڵ�����
		 * @return ����
		 */
		public String getName(){return name;}
		
		/**
		 * ��ȡ��ǩ������
		 * @return �������ݹ�����Html����
		 */
		public Html getInnerHTML(){return innerHTML;}
		
		/**
		 * ��ȡ�Ǳ�ǩ������
		 * @return ����
		 */
		public String getInnerText(){return innerText;}
		
		/**
		 * ��ȡ�ñ�ǩ��������������
		 * @return �������Ե����Ƽ���
		 */
		public Set<String> getPropertys(){return map.keySet();}
		
		/**
		 * ��ȡ����ֵ
		 * @param property ������
		 * @return ֵ
		 */
		public String getValue(String property){return map.get(property);}
		
		/**
		 * ���ýڵ��Ƿ���ڸ�����
		 * @param property ����
		 * @return ����ֵ
		 */
		public boolean existProperty(String property){return map.containsKey(property);}
		/**
		 * ���ýڵ��Ƿ���ڸ����ԣ�������ֵ����ָ��ֵ
		 * @param property ����
		 * @param value ���ƶ�Ӧֵ
		 * @return ����ֵ
		 */
		public boolean existPropertyAndValue(String property,String value){
			return this.existProperty(property) && map.get(property).equals(value);
		}
		/**
		 * ��ȡ&lt;name property��key&gt;����ʽ���ַ���
		 * @return ����&lt;name property��key&gt;����ʽ���ַ���
		 */
		public String toString(){
			String str = "<" + name;
			int length = size();
			if(length > 0){str+=" ";}
			int i =0;
			for(Map.Entry<String, String> e : map.entrySet()){
				i++;
				if(e.getValue()!= null){str += e.getKey() + "=\"" + e.getValue() + "\"";}
				else{str += e.getKey() + "=\"\"";}
				if(i<length){str+=" ";}
			}
			return str + ">";
		}
	}
	
	
	/**
	 * ����HTML���ַ�����ʽ��ͬʱҲ��Ϊ���û����ⲿʹ���ַ�����������
	 * ������ʹ��<code>new</code>����ʵ����
	 * ������ͨ��<code>buildHtml(String str)</code>���һ��ʵ��
	 * @author �ʷʵ�����
	 * @version 2016-02-10
	 */
	protected class Html{
		
		private String content;
		private String title;
		
		@SuppressWarnings("unused")
		private Html(){}
		
		/**
		 * �����ⲿ�ַ���������
		 * @param content ��������
		 */
		protected Html(String content){
			this.content = content;
			try{
				NodeArray na = this.getTag("title");
				if(na.size() > 0){
					this.title = na.get(0).innerText;
				}
			}catch(QException err){}
		}
		/**
		 * ��ȡ����
		 * @return ����
		 */
		public String getContent(){return content;}
		
		
		/**
		 * ��property,value������ӵĲ���,����ʵ�֣�Ϊ����Ӧ���1�����2
		 * @param name ����
		 * @param property ����
		 * @param value ����ֵ
		 */
		private  void addList(String sta,String name,String property,String value,ArrayList<Node> l){
			String sub = null;
			int index = 0;int location = 0;
			while((index = content.indexOf(sta,location)) != -1){
				int endIndex = content.indexOf("</"+name+">", index);
				if(endIndex != -1){
					sub = content.substring(index, endIndex+("</"+name+">").length());
				}else{
					//û�з����ȫ���������ӽڵ�
					sub = content.substring(index);
				}
//				System.out.println(sub);
				location = index + sub.length();
				Node n 	= new Node(sub);
				if(property != null){
					//��������
					if(value != null){
						//ֵ����
						if(n.existPropertyAndValue(property, value)){
							l.add(n);
						}
					}else{
						//��ֵ����
						if(n.existProperty(property)){
							l.add(n);
						}
					}
					
				}else{
					//������
					l.add(n);
				}
				
			}
			
		}
		
		/**
		 * ��property,value������ӵĲ���
		 * @param name ����
		 * @param property ����
		 * @param value ����ֵ
		 */
		
		private NodeArray addByContrl(String name,String property,String value){
			ArrayList<Node> l = new ArrayList<Node>();
			String sta1 = "<" + name+" ";
			//��һ�����
			this.addList(sta1, name, property, value, l);
			String sta2 = "<"+name+">";
			//�ڶ������
			this.addList(sta2, name, property, value, l);
			return new NodeArray(l);
		}
		/**
		 * ����name��ȡ��ǩ
		 * @param name ����
		 * @return �������ֵĽڵ��γɵ�����
		 */
		public NodeArray getTag(String name){
			return this.addByContrl(name, null, null);
		}
		
		/**
		 * ���ݱ�ǩ���ƺ������޶�����Node����
		 * @param name ����
		 * @param propery ����
		 * @return �������ֵĽڵ��γɵ�����
		 */
		
		public NodeArray getTag(String name,String propery){
			return this.addByContrl(name, propery, null);
		}
		
		/**
		 * ���ݱ�ǩ���ƺ����Ե�ֵ�޶�����Node����
		 * @param name ����
		 * @param propery ����
		 * @param value ��Ӧֵ
		 * @return �������ֵĽڵ��γɵ�����
		 */
		
		public NodeArray getTag(String name,String propery,String value){
			return this.addByContrl(name, propery,value);
		}
		
		/**
		 * ��ȡtitle
		 * @return title
		 */
		public String getTitle(){return title;}
		
		/**
		 * ��ȡ������
		 * @return ����
		 */
		public String toString(){
			return this.content;
		}
		
	}
	
	
	
//////////////////////////////////////	
	
	
	
	
	
	
	
	/*
	 * 
	 * QHtml��
	 * 
	 */
	
	private Html html;
	
	private QHtml(){super("");}
	
	/**
	 * ����path����QHtml����
	 * @param path ����·��
	 * @throws QException ��IOExceptionת���������쳣
	 */
	public QHtml(String path) throws QException{
		this(path,"UTF-8");
	}
	
	
	/**
	 * ����path����QHtml������encodeָ������
	 * @param path ����·��
	 * @param encode ���ļ���ָ������
	 * @throws QException ��IOExceptionת���������쳣
	 */
	public QHtml(String path,String encode) throws QException{
		super(path);
		if(path != ""){
				try{
				FileInputStream f = new FileInputStream(path);
				InputStreamReader fr = new InputStreamReader(f,encode);
				BufferedReader br = new BufferedReader(fr);
				
				String content = "";
				String line ;
				while((line = br.readLine()) != null){
					content += line  + System.getProperty("line.separator","\r\n");//�������з�
				}
				
				this.html = new Html(content);
				System.out.println(html);
				br.close();
			}catch(IOException err){
				
				throw new QException(err.getMessage());
			}
		}
		
		
		
	}
	/**
	 * ����fileName�����ļ�����
	 * @param fileName File����
	 * @throws QException ��IOExceptionת���������쳣
	 */
	public QHtml(File fileName) throws QException{
		this(fileName.getPath(),"UTF-8");
	}
	/**
	 * ����fileName�����ļ�����,��encodeָ������
	 * @param fileName File����
	 * @param encode ���ļ���ָ������
	 * @throws QException ��IOExceptionת���������쳣
	 */
	public QHtml(File fileName,String encode) throws QException{
		this(fileName.getPath(),encode);
	}
	
	/**
	 * ��ȡHTML����
	 * @return ����
	 */
	public String getContent(){	return html.getContent();}
	
	/**
	 * �������³���
	 * @return ���³���
	 */
	public int getLength(){return getContent().length();}
	
	/**
	 * ͳ���ַ������ִ�����
	 * ���ִ�Сд
	 * @param word ��ͳ�Ƶ��ַ���
	 * @return ���ִ���
	 */
	public int countWord(String word) {
		int sum = 0;
		int index = 0;
		int location = 0;
		String content = html.getContent();
		while ((index = content.indexOf(word, location)) != -1) {
			location = index + word.length();
			sum++;
		}
		return sum;
	}
	
	/**
	 * ͳ���ַ������ִ�����
	 * ��ѡ������Ƿ����ִ�Сд
	 * @param word ��ͳ�Ƶ��ַ���
	 * @param flag false�������֣�true��������
	 * @return ���ִ���
	 */
	public int countWord(String word,boolean flag) {
		int sum = 0;
		if(!flag){
			//������
			word = word.toLowerCase();
			int index = 0;
			int location = 0;
			String content = html.getContent();
			String temp = content.toLowerCase();
			while ((index = temp.indexOf(word, location)) != -1) {
				location = index + word.length();
				sum++;
			}
		}else{
			//����
			sum = this.countWord(word);
		}
		return sum;
	}
	/**
	 * �����ǩ������������ǰ����Ϸ���
	 * @param word ��ͳ�Ʊ�ǩ����
	 * @return ���ִ���
	 * 
	 */
	public int countTag(String word){
		HTML.Tag t = HTML.getTag(word);
		if(t == null){
			return 0;
		}else{
			String word1 = "<" + t.toString()+" ";
			//�󲿼ӿո���Ϊ�˷�ֹ����<a �� <aricle�����Ļ���
			//����>�ǿ��ǵ��ñ�ǩ����������
			String word2 ="<"+t.toString()+">";
			return countWord(word1,false)+countWord(word2, false);
		}
		
	}
	
	/**
	 * ���ݱ�ǩ�����޶�����Node����
	 * @param name ����
	 * @return �������ֵĽڵ��γɵ�����
	 */
	
	public NodeArray getTag(String name) {
		return html.getTag(name);
	}
	
	/**
	 * ���ݱ�ǩ���ƺ������޶�����Node����
	 * @param name ����
	 * @param property ����
	 * @return �������ֵĽڵ��γɵ�����
	 */
	public NodeArray getTag(String name,String property) {
		return html.getTag(name,property);
	}
	
	/**
	 * ���ݱ�ǩ���ƺ����Ե�ֵ�޶�����Node����
	 * @param name ����
	 * @param property ����
	 * @param value ��Ӧֵ
	 * @return �������ֵĽڵ��γɵ�����
	 */
	
	public NodeArray getTag(String name,String property,String value) {
		return html.getTag(name,property,value);
	}
	
	/**
	 * Ϊ���û����ⲿ����HTML�����ʵ��
	 * @param html �ַ���
	 * @return Html����
	 * @throws QException ���������д������쳣
	 */
	
	public static Html buildHtml(String html) throws QException{
		return new QHtml("").new Html(html);
	}
	
	/**
	 * ����д���ļ�
	 * @param str ��д����
	 * @throws QException �� IOException ת���������쳣
	 */
	public void writeHTML(String str) throws QException{
		html = new Html(str);
		try{
			FileWriter fw = new FileWriter(this.getAbsolutePath());
			fw.write(str);
			fw.flush();
			fw.close();
		}catch(IOException err){
			throw new QException(err.getMessage());
		}
	}
	/**
	 * ����д���ļ�
	 * @param h ��д��html�ĵ�����
	 * @throws QException �� IOException ת���������쳣
	 */
	public void writeHTML(Html h) throws QException{
		this.writeHTML(h.getContent());
	}
	
	/**
	 * ��ȡtitle
	 * @return title
	 */
	public String getTitle(){return this.html.getTitle();}
	
	
}
