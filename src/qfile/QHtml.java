package qfile;

import java.io.*;
import java.util.*;

import javax.swing.text.html.*;

/**
 * 处理HTML的文件类。<br>
 * 根据标签名称返回该标签构成的节点对象。<br>
 * 可借助<code>QHtml.buildHtml()</code>通过字符串而非文件的形式构建可操作的QHtml.Html的对象
 * @author 肥肥的兔子
 * @version 2016-02-10
 *
 */

public class QHtml extends File{

	private static final long serialVersionUID = 1L;
	
	/**
	 * 节点的数组,不允许在外部直接创建实例
	 * 但是可以通过QHtml.NodeArray来使用其实例
	 * @author 肥肥的兔子
	 * @version 2016-02-10
	 */
	public class NodeArray{
		private ArrayList<Node> list;
		
		
		@SuppressWarnings("unused")
		private NodeArray(){}
		
		/**
		 * 根据list构建Node节点
		 * @param list 节点列表
		 * @return 节点数组
		 */
		protected NodeArray(ArrayList<Node> list){
			this.list = list;
		}
		/**
		 * 获取数组中的Node节点
		 * @param index 下标
		 * @return 节点类型
		 * @throws QException 下标越界触发异常
		 */
		public Node get(int index) throws QException{
			if(index<0||index>=list.size()){throw new QException("下标少于0或下标越界");}
			return list.get(index);
		}
		
		/**
		 * 获取数组长度
		 * @return 数组长度
		 */
		public int size(){
			return list.size();
		}
	}
	////////////////////////////////////////
	
	/**
	 * 内部类，处理HTML的标签节点，
	 * 不允许使用<code>new</code>构建实例，
	 * 但可以通过<code>buildNode()</code>获得一个实例
	 * @author 肥肥的兔子
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
		 * 根据str构建Node节点
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
			//提取名字
			
			int flagIndex = str.indexOf(">");
			//结束的标签符号位置
			if(index != flagIndex){
				//该标签需要提取属性 

				String sub = str.substring(1, flagIndex).replace("\"", "");
				while((sub.indexOf(" =")!=-1)||(sub.indexOf("= ")!=-1)){
					sub=sub.replace(" =", "=").replace("= ", "=");
					//处理非法空格
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
			//提取属性
			
			int endIndex = str.indexOf("</"+this.name+">");
			//结束标志内容
			this.innerHTML = new Html(str.substring(flagIndex+1, endIndex));
			//提取标签间的全部内容
			
			int begin2 = str.indexOf("<", flagIndex);
			//第二个<开始的位置
			if(begin2 != endIndex){
				//证明该标签中间混杂了字符和标签
				String left = null;
				if(flagIndex +1 != begin2){
					//中间有字符
					left = str.substring(flagIndex+1, begin2);
				}
				//混杂标签左部处理
				
				int last = 0;
				int now = 0;
				String right = null;
				while((now = str.indexOf(">",now+1))!=-1){
					if(now<endIndex){last = now;}
					//倒数第二个>
				}
				String temp = str.substring(last+1, endIndex);
				if(temp.length()>0){right = temp;}
				if(left!=null){this.innerText += left;}
				if(right!=null){this.innerText += right;}
				//混杂标签右部处理
			}else{
				//中间只有字符
				
//				System.out.println(str+"  "+ begin2 + "==" + endIndex);
				this.innerText = innerHTML.getContent();
				
			}
			this.innerText = innerText.replace("&nbsp;", " ");
		}
		/**
		 * 获取标签拥有属性的个数
		 * @return 个数
		 */
		public int size(){
			return map.size();
		}
		/**
		 * 获取节点名称
		 * @return 名称
		 */
		public String getName(){return name;}
		
		/**
		 * 获取标签间内容
		 * @return 由其内容构建的Html对象
		 */
		public Html getInnerHTML(){return innerHTML;}
		
		/**
		 * 获取非标签的内容
		 * @return 内容
		 */
		public String getInnerText(){return innerText;}
		
		/**
		 * 获取该标签的所有属性名称
		 * @return 所有属性的名称集合
		 */
		public Set<String> getPropertys(){return map.keySet();}
		
		/**
		 * 获取属性值
		 * @param property 属性名
		 * @return 值
		 */
		public String getValue(String property){return map.get(property);}
		
		/**
		 * 检查该节点是否存在该属性
		 * @param property 属性
		 * @return 布尔值
		 */
		public boolean existProperty(String property){return map.containsKey(property);}
		/**
		 * 检查该节点是否存在该属性，且属性值等于指定值
		 * @param property 属性
		 * @param value 限制对应值
		 * @return 布尔值
		 */
		public boolean existPropertyAndValue(String property,String value){
			return this.existProperty(property) && map.get(property).equals(value);
		}
		/**
		 * 获取&lt;name property：key&gt;的形式的字符串
		 * @return 返回&lt;name property：key&gt;的形式的字符串
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
	 * 处理HTML的字符串形式，同时也是为了用户在外部使用字符串构建该类
	 * 不允许使用<code>new</code>构建实例，
	 * 但可以通过<code>buildHtml(String str)</code>获得一个实例
	 * @author 肥肥的兔子
	 * @version 2016-02-10
	 */
	protected class Html{
		
		private String content;
		private String title;
		
		@SuppressWarnings("unused")
		private Html(){}
		
		/**
		 * 接受外部字符串来操作
		 * @param content 文章内容
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
		 * 获取内容
		 * @return 内容
		 */
		public String getContent(){return content;}
		
		
		/**
		 * 由property,value控制添加的部分,具体实现，为了适应情况1和情况2
		 * @param name 名称
		 * @param property 属性
		 * @param value 属性值
		 */
		private  void addList(String sta,String name,String property,String value,ArrayList<Node> l){
			String sub = null;
			int index = 0;int location = 0;
			while((index = content.indexOf(sta,location)) != -1){
				int endIndex = content.indexOf("</"+name+">", index);
				if(endIndex != -1){
					sub = content.substring(index, endIndex+("</"+name+">").length());
				}else{
					//没有封闭则全部都当成子节点
					sub = content.substring(index);
				}
//				System.out.println(sub);
				location = index + sub.length();
				Node n 	= new Node(sub);
				if(property != null){
					//属性限制
					if(value != null){
						//值限制
						if(n.existPropertyAndValue(property, value)){
							l.add(n);
						}
					}else{
						//无值限制
						if(n.existProperty(property)){
							l.add(n);
						}
					}
					
				}else{
					//无限制
					l.add(n);
				}
				
			}
			
		}
		
		/**
		 * 由property,value控制添加的部分
		 * @param name 名称
		 * @param property 属性
		 * @param value 属性值
		 */
		
		private NodeArray addByContrl(String name,String property,String value){
			ArrayList<Node> l = new ArrayList<Node>();
			String sta1 = "<" + name+" ";
			//第一种情况
			this.addList(sta1, name, property, value, l);
			String sta2 = "<"+name+">";
			//第二种情况
			this.addList(sta2, name, property, value, l);
			return new NodeArray(l);
		}
		/**
		 * 根据name获取标签
		 * @param name 名字
		 * @return 符合名字的节点形成的数组
		 */
		public NodeArray getTag(String name){
			return this.addByContrl(name, null, null);
		}
		
		/**
		 * 根据标签名称和属性限定返回Node数组
		 * @param name 名字
		 * @param propery 属性
		 * @return 符合名字的节点形成的数组
		 */
		
		public NodeArray getTag(String name,String propery){
			return this.addByContrl(name, propery, null);
		}
		
		/**
		 * 根据标签名称和属性的值限定返回Node数组
		 * @param name 名字
		 * @param propery 属性
		 * @param value 对应值
		 * @return 符合名字的节点形成的数组
		 */
		
		public NodeArray getTag(String name,String propery,String value){
			return this.addByContrl(name, propery,value);
		}
		
		/**
		 * 获取title
		 * @return title
		 */
		public String getTitle(){return title;}
		
		/**
		 * 获取其内容
		 * @return 内容
		 */
		public String toString(){
			return this.content;
		}
		
	}
	
	
	
//////////////////////////////////////	
	
	
	
	
	
	
	
	/*
	 * 
	 * QHtml类
	 * 
	 */
	
	private Html html;
	
	private QHtml(){super("");}
	
	/**
	 * 根据path构建QHtml对象
	 * @param path 文章路径
	 * @throws QException 有IOException转换过来的异常
	 */
	public QHtml(String path) throws QException{
		this(path,"UTF-8");
	}
	
	
	/**
	 * 根据path构建QHtml对象，用encode指定编码
	 * @param path 文章路径
	 * @param encode 打开文件的指定编码
	 * @throws QException 由IOException转换过来的异常
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
					content += line  + System.getProperty("line.separator","\r\n");//保留换行符
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
	 * 根据fileName构建文件对象
	 * @param fileName File对象
	 * @throws QException 由IOException转换过来的异常
	 */
	public QHtml(File fileName) throws QException{
		this(fileName.getPath(),"UTF-8");
	}
	/**
	 * 根据fileName构建文件对象,用encode指定编码
	 * @param fileName File对象
	 * @param encode 打开文件的指定编码
	 * @throws QException 由IOException转换过来的异常
	 */
	public QHtml(File fileName,String encode) throws QException{
		this(fileName.getPath(),encode);
	}
	
	/**
	 * 获取HTML内容
	 * @return 内容
	 */
	public String getContent(){	return html.getContent();}
	
	/**
	 * 返回文章长度
	 * @return 文章长度
	 */
	public int getLength(){return getContent().length();}
	
	/**
	 * 统计字符串出现次数，
	 * 区分大小写
	 * @param word 需统计的字符串
	 * @return 出现次数
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
	 * 统计字符串出现次数，
	 * 可选择控制是否区分大小写
	 * @param word 需统计的字符串
	 * @param flag false代表不区分，true代表区分
	 * @return 出现次数
	 */
	public int countWord(String word,boolean flag) {
		int sum = 0;
		if(!flag){
			//不区分
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
			//区分
			sum = this.countWord(word);
		}
		return sum;
	}
	/**
	 * 计算标签的数量，计算前会检查合法性
	 * @param word 需统计标签名字
	 * @return 出现次数
	 * 
	 */
	public int countTag(String word){
		HTML.Tag t = HTML.getTag(word);
		if(t == null){
			return 0;
		}else{
			String word1 = "<" + t.toString()+" ";
			//后部加空格是为了防止出现<a 和 <aricle这样的混肴
			//不加>是考虑到该标签可能有属性
			String word2 ="<"+t.toString()+">";
			return countWord(word1,false)+countWord(word2, false);
		}
		
	}
	
	/**
	 * 根据标签名称限定返回Node数组
	 * @param name 名字
	 * @return 符合名字的节点形成的数组
	 */
	
	public NodeArray getTag(String name) {
		return html.getTag(name);
	}
	
	/**
	 * 根据标签名称和属性限定返回Node数组
	 * @param name 名字
	 * @param property 属性
	 * @return 符合名字的节点形成的数组
	 */
	public NodeArray getTag(String name,String property) {
		return html.getTag(name,property);
	}
	
	/**
	 * 根据标签名称和属性的值限定返回Node数组
	 * @param name 名字
	 * @param property 属性
	 * @param value 对应值
	 * @return 符合名字的节点形成的数组
	 */
	
	public NodeArray getTag(String name,String property,String value) {
		return html.getTag(name,property,value);
	}
	
	/**
	 * 为了用户在外部穿件HTML对象的实例
	 * @param html 字符串
	 * @return Html对象
	 * @throws QException 构建过程中触发的异常
	 */
	
	public static Html buildHtml(String html) throws QException{
		return new QHtml("").new Html(html);
	}
	
	/**
	 * 重新写入文件
	 * @param str 重写内容
	 * @throws QException 由 IOException 转换过来的异常
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
	 * 重新写入文件
	 * @param h 重写的html文档内容
	 * @throws QException 由 IOException 转换过来的异常
	 */
	public void writeHTML(Html h) throws QException{
		this.writeHTML(h.getContent());
	}
	
	/**
	 * 获取title
	 * @return title
	 */
	public String getTitle(){return this.html.getTitle();}
	
	
}
