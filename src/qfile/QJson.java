package qfile;

import java.util.*;

import java.io.*;
/**
 * 处理json文件的类，特别注意的是，通过键值获取数据类型时，需要考虑你需要获得的类型，调用适当的API.<br>
 * 需要调用<code>getJsonObj</code>来获取文件内部的json对象,后可调用其它API获取数据<br>
 * 可以借助<code>QJson.buildJSONObject()</code>通过字符串构建QJson.JSONObject对象进行操作<br>
 * 可以借助<code>QJson.buildJSONArray()</code>通过字符串构建QJson.JSONArray对象进行操作<br>
 * @author 肥肥的兔子
 * @version 2016-02-10
 *
 */
public class QJson extends File {
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * 用于处理json中[]数组
	 * 不能用<code>new</code>创建，但可以通过<code>buildJSONArray</code>创建获取一个实例
	 * @author 肥肥的兔子
	 * @version 2016-02-10
	 */
	public class JSONArray{
		
		private ArrayList<Object> list;
		
		@SuppressWarnings("unused")
		private JSONArray(){}
		
		/**
		 * 字符串构造列表
		 * @param str 字符串
		 * @return 构造后的列表
		 * @throws QException 缺省导致出现异常
		 */
		private ArrayList<Object> strToList(String str)
				throws QException{
			
			ArrayList<Object> l = new ArrayList<Object>();
			if(!str.endsWith("]")){
				throw new QException("缺省]");
			}
			
			if(!str.startsWith("[")){
				throw new QException("缺省[");
			}
			
			int length = str.length();
			char ch;
			for(int i =1;i <length;i++){
				ch = str.charAt(i);
				if(ch != '['){
					//非数组
					if(i!=length-1 && ch == ']'){
						throw new QException("缺省[");
					}
					
					if(ch=='{'){
						//处理数组中的json对象
						int leftIndex = i;
						int rightIndex= 0; 
						int left=1;int right =0;
						for(int j =leftIndex+1;j<length;j++){
							if(left == right){
								rightIndex = j;
								break;
							}
							if(str.charAt(j) == '{'){
								left++;
							}
							if(str.charAt(j) == '}'){
								right++;
							}
						}
						
						if(left>right){
							//不匹配
							throw new QException("缺省}");
						}else if(right > left){
							throw new  QException("缺省{");
						}else{
							String value = str.substring(leftIndex,rightIndex);
							JSONObject jo = new JSONObject(value); 
							l.add(jo);
						}
						
						i= rightIndex;
						
					}else{
						int index = str.indexOf(',',i+1);
						String value = null;
						if(index != -1){
							value = str.substring(i, index);
							i = index;
						}else if(str.charAt(length-2) != ','){
							value = str.substring(i,length-1);
							i = length;
							
						}
						
						if(value != null){
							//处理基本型数据
							if(value.startsWith("\"") &&
									value.endsWith("\"")){
								//先判断是否为字符串
								String s = value.replace("\"", ""); 
								l.add(s);
							}else{
								try{
									long lo = Long.parseLong(value);
									l.add(lo);
								}catch(NumberFormatException err1){
									
									try{
										double d = Double.parseDouble(value);
										l.add(d);
									}catch(NumberFormatException err2){
										if(value.equals("true")||value.equals("false")){
											boolean b = Boolean.parseBoolean(value);
											l.add(b);
										}else{
											//不是字符串，不是整数，不是小数，不是布尔，所以报错
											throw new QException("无法识别这东西--"+value);
										}
										
									}
									
								}
							}
							
							
						}
						
					}
				}else{
					//处理数组
					int leftIndex = i;
					int rightIndex= 0; 
					int left=1;int right =0;
					for(int j =leftIndex+1;j<length;j++){
						if(left == right){
							rightIndex = j;
							break;
						}
						if(str.charAt(j) == '['){
							left++;
						}
						if(str.charAt(j) == ']'){
							right++;
						}
					}
					
					if(left>right){
						//不匹配
						throw new QException("缺省[");
					}else if(right > left){
						throw new  QException("缺省]");
					}else{
						String value = str.substring(leftIndex,rightIndex);
						JSONArray ja = new JSONArray(value);
						l.add(ja);
					}
					
					i= rightIndex;
					
				}
				
				
				
				
			}
			
			return l;
		}
		
		/**
		 * 根据str转换成ArrayList对象
		 * @param str
		 * @throws QException 出现缺省[]的情况将会抛出异常
		 * @return 解析好的JSONArray对象
		 */
		protected JSONArray(String str) throws QException{
			this.list = strToList(str);
		}
		
		/**
		 * 获取JSONObject类型的数据
		 * @param index 索引的下标
		 * @return 该下标的JSONObject对象
		 * @throws QException 转类型失败
		 */
		
		public JSONObject getJSONObject(int index) 
				throws QException{
			Object obj = this.list.get(index);
			if(obj instanceof JSONObject){
				return (JSONObject)obj;
			}else{
				throw new QException(obj + " 不是JSONObject类型");
			}
		}
		
		
		/**
		 * 获取JSONArray类型的数据
		 * @param index 索引的下标
		 * @return 该下标的JSONArray对象
		 * @throws QException 转类型失败
		 */
		
		public JSONArray getJSONArray(int index) 
				throws QException{
			Object obj = this.list.get(index);
			if(obj instanceof JSONArray){
				return (JSONArray)obj;
			}else{
				throw new QException(obj + " 不是JSONArray类型");
			}
		}
		
		/**
		 * 获取数组长度
		 * @return 长度
		 */
		public int size(){
			return this.list.size();
		}
		
		/**
		 * 获取Long类型的数据
		 * @param index 索引的下标
		 * @return 该下标的long数据
		 * @throws QException 转类型失败
		 */
		
		public long getLong(int index) 
				throws QException{
			Object obj = this.list.get(index);
			if(obj instanceof Long){
				return (long)obj;
			}else{
				throw new QException(obj + " 不是Long类型");
			}
		}
		
		
		/**
		 * 获取Double类型的数据
		 * @param index 索引的下标
		 * @return 该下标的double数据
		 * @throws QException 转类型失败
		 */
		
		public double getDouble(int index) 
				throws QException{
			Object obj = this.list.get(index);
			if(obj instanceof Double){
				return (double)obj;
			}else{
				throw new QException(obj + " 不是Double类型");
			}
		}
		
		/**
		 * 获取Boolean类型的数据
		 * @param index 索引的下标
		 * @return 该下标的boolean数据
		 * @throws QException 转类型失败
		 */
		
		public boolean getBoolean(int index) 
				throws QException{
			Object obj = this.list.get(index);
			if(obj instanceof Boolean){
				return (boolean)obj;
			}else{
				throw new QException(obj + " 不是Boolean类型");
			}
		}
		
		/**
		 * 获取String类型的数据
		 * @param index 索引的下标
		 * @return 该下标的String数据
		 * @throws QException 转类型失败
		 */
		
		public String getString(int index) 
				throws QException{
			Object obj = this.list.get(index);
			if(obj instanceof String){
				return (String)obj;
			}else{
				throw new QException(obj + " 不是String类型");
			}
		}
		
		/**
		 * 输出格式QJson.JSONArray[value1,value2……]
		 * @return  格式QJson.JSONArray[value1,value2……]
		 */
		@Override
		public String toString(){
			String str = "QJson.JSONArray[";
			int length = this.list.size();
			for(int i = 0;i<length;i++){
				str += String.valueOf(this.list.get(i));
				if(i != length - 1){
					str += ",";
				}
			}
			str += "]";
			return str;
		}
		
		
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	/**
	 * 用于处理json中{}对象
	 * 禁止用<code>new</code>创建，但可以通过<code>buildJSONObject</code>获得一个实例
	 * @author 肥肥的兔子
	 * @version 2016-02-10
	 */
	public class JSONObject{
		
		private HashMap<String,Object> map;
		
		@SuppressWarnings("unused")
		private JSONObject(){};
		
		/**
		 * 递归构造HashMap返回给上层
		 * @return str 需要解析的字符串
		 * @return HashMap
		 */
		private HashMap<String,Object> strToMap(String str)
				throws QException{
			HashMap<String,Object> m = new HashMap<String,Object>();
			
			char ch;
			int length = str.length();
			for (int i = 0; i < length; i++) {
				ch = str.charAt(i);
				if(i==0 && ch != '{'){
					throw new QException("Json需以\"{\"开始");
				}else if(i==length-1 && ch != '}'){
					throw new QException("Json需以\"}\"结束");
				}else if(ch == '"'){
					int keyIndex = str.indexOf("\"",i+1);//找到下一个
					String key =str.substring(i+1, keyIndex);
					if(str.charAt(keyIndex+1) != ':'){
						throw new QException("缺省：");
					}
					char test = str.charAt(keyIndex+2);

					if(test == '['){
						//处理数组对象
						int leftIndex = keyIndex+2;//左括号下标
						int rightIndex = 0;//右括号下标
						int left = 1;//左括号的个数
						int right=0;//右括号的个数
						
						for(int j =leftIndex+1;j<length;j++){
							if(left == right){
								rightIndex = j;
								break;
							}
							if(str.charAt(j) == '['){
								left++;
							}
							if(str.charAt(j) == ']'){
								right++;
							}
						}
						
						if(left>right){
							//不匹配
							throw new QException("缺省]");
						}else if(right > left){
							throw new QException("缺省[");
						}else{
							String value = str.substring(leftIndex, rightIndex);
							JSONArray v = new JSONArray(value);
							m.put(key, v);
						}
						i= rightIndex-1;
						continue;
					}
					
					
					
					if(test == '{'){
						//处理json对象
						int leftIndex = keyIndex+2;//左括号下标
						int rightIndex = 0;//右括号下标
						int left = 1;//左括号的个数
						int right=0;//右括号的个数
						
						for(int j =leftIndex+1;j<length;j++){
							if(left == right){
								rightIndex = j;
								break;
							}
							if(str.charAt(j) == '{'){
								left++;
							}
							if(str.charAt(j) == '}'){
								right++;
							}
						}
						
						if(left>right){
							//不匹配
							throw new QException("缺省{");
						}else if(right > left){
							//不匹配
							throw new QException("缺省}");
						}else{
							//匹配
							String value = str.substring(leftIndex, rightIndex);
							JSONObject v =new JSONObject(value);
							m.put(key, v);
						}
						i= rightIndex-1;
						continue;
					}
					
					
					
					if((test<'0'||test>'9')&&
						(test<'a'||test>'z')&&
						(test<'A'||test>'Z')&&
						test!='"'){
						throw new QException("不能识别符号" + test);
					}
					
					
					
					
					//处理基本类型
					int valueKey = str.indexOf(",",keyIndex);
					String value;
					if(valueKey == -1){
						//后面没有数据了
						value = str.substring(keyIndex+2,length-1);
						i = length;//必须执行完后面的东西后才结束，不能用break
					}else{
						value = str.substring(keyIndex+2, valueKey);
						i = valueKey;
					}
					
					
					
					//对基本类型进行转型，默认是字符串
					try{
						long v = Long.parseLong(value);

						m.put(key, v);
					}catch(NumberFormatException err1){
						try{
							double v = Double.parseDouble(value);
							m.put(key, v);
						}catch(NumberFormatException err2){
							if(value.equals("true")||value.equals("false")){
								boolean v = Boolean.parseBoolean(value);	
								m.put(key, v);
							}else{
								String v = value.replace("\"","");
								m.put(key, v);
							}
						}
						
					}
					
				}else if(ch == ']'){
					//因为[的进入在上面，所以这里正常情况下不能碰到]
					throw new QException("缺省[");
				}else if(ch == '}' && i != length-1){
					throw new QException("缺省{");
				}
			}
			return m;
		}
		
		/**
		 * 根据str转换成HashMap对象
		 * @param str 需要解析的字符串
		 * @throws QException 缺省[]或{}触发的异常
		 */
		protected JSONObject(String str) throws QException{
			map = strToMap(str);			
		}
		
		/**
		 * 以集合的形式返回key的全部名称
		 * @return 全部名称的集合
		 */
		public Set<String> getKeys(){		
			return map.keySet();
		}
		
		
		/**
		 * 获取JSONObject的映射个数
		 * @return 个数
		 */
		public int size(){
			return this.map.size();
		}
		
		
		
		
		/**
		 * 获取JSONObject类型的数据
		 * @param key 键值
		 * @return 对应值
		 * @throws QException 转换类型失败
		 */
		
		public JSONObject getJSONObject(String key) 
				throws QException{
			if(!this.map.containsKey(key)){
				return null;
			}
			
			Object obj = this.map.get(key);
			if(obj instanceof JSONObject){
				return (JSONObject)obj;
			}else{
				throw new QException(obj + " 不是JSONObject类型");
			}
		}
		
		
		/**
		 * 获取JSONArray类型的数据
		 * @param key 键值
		 * @return 对应值
		 * @throws QException 转换类型失败
		 */
		
		public JSONArray getJSONArray(String key) 
				throws QException{
			if(!this.map.containsKey(key)){
				return null;
			}
			
			Object obj = this.map.get(key);
			if(obj instanceof JSONArray){
				return (JSONArray)obj;
			}else{
				throw new QException(obj + " 不是JSONArray类型");
			}
		}
		
		/**
		 * 获取Longt类型的数据
		 * @param key 键值
		 * @return 对应值
		 * @throws QException 转换类型失败
		 */
		
		public long getLong(String key) 
				throws QException{
			
			Object obj = this.map.get(key);
			if(obj instanceof Long){
				return (long)obj;
			}else{
				throw new QException(obj + " 不是Long类型");
			}
		}
		
		
		/**
		 * 获取Double类型的数据
		 * @param key 键值
		 * @return 对应值
		 * @throws QException 转换类型失败
		 */
		
		public double getDouble(String key) 
				throws QException{
			Object obj = this.map.get(key);
			if(obj instanceof Double){
				return (double)obj;
			}else{
				throw new QException(obj + " 不是Double类型");
			}
		}
		
		/**
		 * 获取Boolean类型的数据
		 * @param key 键值
		 * @return 对应值
		 * @throws QException 转换类型失败
		 */
		public boolean getBoolean(String key) 
				throws QException{
			Object obj = this.map.get(key);
			if(obj instanceof Boolean){
				return (boolean)obj;
			}else{
				throw new QException(obj + " 不是Boolean类型");
			}
		}
		
		/**
		 * 获取String类型的数据
		 * @param key 键值
		 * @return 对应值
		 * @throws QException 转换类型失败
		 */
		
		public String getString(String key) 
				throws QException{
			if(!this.map.containsKey(key)){
				return null;
			}
			
			Object obj = this.map.get(key);
			if(obj instanceof String){
				return (String)obj;
			}else{
				throw new QException(obj + " 不是String类型");
			}
		}
		
		/**
		 * 获取QJson.JSONObject{……}格式的JSONObjetct字符串
		 * @return 返回QJson.JSONObject{……}
		 */
		@Override
		public String toString(){
			String str = "QJson.JSONObject{";
			int length = this.map.size();int i =0;
			for(Map.Entry<String, Object> m: this.map.entrySet()){
				String key = m.getKey();
				String value = null;
				if(m.getValue() instanceof String){
					value = "\"" + m.getValue() + "\"";
				}else{
					value = String.valueOf(m.getValue());
				}
				 
				str += "\"" + key +"\":" + value;
				if(i != length - 1){
					str += ",";
					i++;
				}
			}
			str += "}";
			return str;
		}
		
		
		
		
		
	}
	
	/*
	 * 这里开始是QJson类的正文
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 */
	private JSONObject jsonObj;
	private String path; // Json文件路径
	private String jsonStr;// json的文件路径
	private String lineSeparator ;
	
	
	
	
	/**
	 * 按指定路径构建QJson对象，自动读取
	 * @param path 文件路径
	 * @throws QException 由IOException转换得来
	 */
	public QJson(String path) throws QException{
		
		super(path);
		this.path = path;
		if(path != ""){
			try{
				FileReader fr = new FileReader(path);
				char[] buff = new char[1024];
				int isEnd = -1; 
				this.jsonStr = new String();
				
				while((isEnd = fr.read(buff) )!=-1 ){
					if(isEnd != 1024){isEnd = isEnd % 1024;}
					//不等于1024的时候，求模防止读取buff重复的部分
					this.jsonStr += new String(buff,0,isEnd);
				}
				fr.close();
			}catch(IOException err){
				throw new QException(err.getMessage(),err.getCause());
			}
		}		
		lineSeparator  = System.getProperty("line.separator","\r\n");
		
		try{
			this.jsonStr = this.jsonStr.replace(lineSeparator, "").replace("\t", "");
			jsonObj = new JSONObject(this.jsonStr);
		}catch(NullPointerException err){}
		
	}
	
	/**
	 * fileName构建QJson对象，自动读取文件内容
	 * @param fileName File对象
	  * @throws QException 由IOException转换得来
	 */
	public QJson(File fileName) throws QException{
		this(fileName.getPath());
	}
	
	
	/**
	 * 返回Json文件的内容
	 * @return 文件内容
	 */
	public String getJsonStr(){
		return this.jsonStr;
	}
	
	/**
	 * 返回内部类jsonObje的实例
	 * @return 文件累不的JSONObject实例
	 */
	public JSONObject getJsonObj(){
		return this.jsonObj;
	}

	
	/**
	 * 使用户可在外部通过方法获得一个JSONObject对象
	 * @param str 需要解析的字符串
	 * @return  JSONObejct对象
	 * @throws QException 构造过程中产生的异常，例如缺省
	 */
	public static JSONObject buildJSONObject(String str)
		throws QException{
			return new QJson("").new JSONObject(str);
	} 
	
	/**
	 * 使用户可在外部通过方法获得一个JSONArray对象
	 * @param str 需要解析的字符串
	 * @return  JSONArray对象
	 * @throws QException 构造过程中产生的异常，例如缺省
	 */
	public static JSONArray buildJSONArray(String str)
		throws QException{
			return new QJson("").new JSONArray(str);
	} 
	
	
	/**
	 * 以HashMap形式重新写入json文件
	 * @param json json映射
	 * @throws QException 由IOException转化得来
	 */
	public void writeJson(Map<String,Object> json) 
			throws QException{
		//将json换成字符串形式后调用本函数的重载形式
		String j = "{";
		int length = json.size();int i =0;
		for (Map.Entry<String, Object> e : json.entrySet()) {
			j += "\"" + e.getKey() + "\":";
			Object obj = e.getValue();
			if(obj instanceof JSONObject ){
				j += obj.toString().replace("QJson.JSONObject", "").replace("QJson.JSONArray",""); 
			}else if(obj instanceof JSONArray){
				j += obj.toString().replace("QJson.JSONArray",""); 
			}else{
				j += String.valueOf(obj);
			}
			
			if(i < length -1){
				j += ",";
				i++;
			}
		}
		j += "}";
		this.writeJson(j);
	}
	
	/**
	 * 以JSONObject格式写入文件
	 * @param jo 需要写入的JSONObject对象
	 * @throws QException 由IOException转化得来
	 */
	public void writeJson(JSONObject jo)throws QException{
		String str = jo.toString().replace("QJson.JSONObject", "");
		writeJson(str);
	}
	
	/**
	 * 以字符串形式重新写入json文件
	 * 同时更新jsonStr的值
	 * @param jsonStr 需要写入的字符串
	 * @throws QException 由IOException转化得来
	 */
	public void writeJson(String jsonStr) 
			throws QException{
		this.jsonObj = new JSONObject(jsonStr);
		this.jsonStr = jsonStr;
		try{
			FileWriter fw = new FileWriter(path);
			fw.write(jsonStr);
			fw.flush();
			fw.close();
		}catch(IOException err){
			throw new QException(err.getMessage(),err.getCause());
		}
		
	}
	
	
	/**
	 * 获取内部JSONObeject的信息
	 * @return jsonObj的信息
	 */
	@Override
	public String toString(){
		return this.jsonObj.toString();
	}
}
