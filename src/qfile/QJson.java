package qfile;

import java.util.*;

import java.io.*;
/**
 * ����json�ļ����࣬�ر�ע����ǣ�ͨ����ֵ��ȡ��������ʱ����Ҫ��������Ҫ��õ����ͣ������ʵ���API.<br>
 * ��Ҫ����<code>getJsonObj</code>����ȡ�ļ��ڲ���json����,��ɵ�������API��ȡ����<br>
 * ���Խ���<code>QJson.buildJSONObject()</code>ͨ���ַ�������QJson.JSONObject������в���<br>
 * ���Խ���<code>QJson.buildJSONArray()</code>ͨ���ַ�������QJson.JSONArray������в���<br>
 * @author �ʷʵ�����
 * @version 2016-02-10
 *
 */
public class QJson extends File {
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * ���ڴ���json��[]����
	 * ������<code>new</code>������������ͨ��<code>buildJSONArray</code>������ȡһ��ʵ��
	 * @author �ʷʵ�����
	 * @version 2016-02-10
	 */
	public class JSONArray{
		
		private ArrayList<Object> list;
		
		@SuppressWarnings("unused")
		private JSONArray(){}
		
		/**
		 * �ַ��������б�
		 * @param str �ַ���
		 * @return �������б�
		 * @throws QException ȱʡ���³����쳣
		 */
		private ArrayList<Object> strToList(String str)
				throws QException{
			
			ArrayList<Object> l = new ArrayList<Object>();
			if(!str.endsWith("]")){
				throw new QException("ȱʡ]");
			}
			
			if(!str.startsWith("[")){
				throw new QException("ȱʡ[");
			}
			
			int length = str.length();
			char ch;
			for(int i =1;i <length;i++){
				ch = str.charAt(i);
				if(ch != '['){
					//������
					if(i!=length-1 && ch == ']'){
						throw new QException("ȱʡ[");
					}
					
					if(ch=='{'){
						//���������е�json����
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
							//��ƥ��
							throw new QException("ȱʡ}");
						}else if(right > left){
							throw new  QException("ȱʡ{");
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
							//�������������
							if(value.startsWith("\"") &&
									value.endsWith("\"")){
								//���ж��Ƿ�Ϊ�ַ���
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
											//�����ַ�������������������С�������ǲ��������Ա���
											throw new QException("�޷�ʶ���ⶫ��--"+value);
										}
										
									}
									
								}
							}
							
							
						}
						
					}
				}else{
					//��������
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
						//��ƥ��
						throw new QException("ȱʡ[");
					}else if(right > left){
						throw new  QException("ȱʡ]");
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
		 * ����strת����ArrayList����
		 * @param str
		 * @throws QException ����ȱʡ[]����������׳��쳣
		 * @return �����õ�JSONArray����
		 */
		protected JSONArray(String str) throws QException{
			this.list = strToList(str);
		}
		
		/**
		 * ��ȡJSONObject���͵�����
		 * @param index �������±�
		 * @return ���±��JSONObject����
		 * @throws QException ת����ʧ��
		 */
		
		public JSONObject getJSONObject(int index) 
				throws QException{
			Object obj = this.list.get(index);
			if(obj instanceof JSONObject){
				return (JSONObject)obj;
			}else{
				throw new QException(obj + " ����JSONObject����");
			}
		}
		
		
		/**
		 * ��ȡJSONArray���͵�����
		 * @param index �������±�
		 * @return ���±��JSONArray����
		 * @throws QException ת����ʧ��
		 */
		
		public JSONArray getJSONArray(int index) 
				throws QException{
			Object obj = this.list.get(index);
			if(obj instanceof JSONArray){
				return (JSONArray)obj;
			}else{
				throw new QException(obj + " ����JSONArray����");
			}
		}
		
		/**
		 * ��ȡ���鳤��
		 * @return ����
		 */
		public int size(){
			return this.list.size();
		}
		
		/**
		 * ��ȡLong���͵�����
		 * @param index �������±�
		 * @return ���±��long����
		 * @throws QException ת����ʧ��
		 */
		
		public long getLong(int index) 
				throws QException{
			Object obj = this.list.get(index);
			if(obj instanceof Long){
				return (long)obj;
			}else{
				throw new QException(obj + " ����Long����");
			}
		}
		
		
		/**
		 * ��ȡDouble���͵�����
		 * @param index �������±�
		 * @return ���±��double����
		 * @throws QException ת����ʧ��
		 */
		
		public double getDouble(int index) 
				throws QException{
			Object obj = this.list.get(index);
			if(obj instanceof Double){
				return (double)obj;
			}else{
				throw new QException(obj + " ����Double����");
			}
		}
		
		/**
		 * ��ȡBoolean���͵�����
		 * @param index �������±�
		 * @return ���±��boolean����
		 * @throws QException ת����ʧ��
		 */
		
		public boolean getBoolean(int index) 
				throws QException{
			Object obj = this.list.get(index);
			if(obj instanceof Boolean){
				return (boolean)obj;
			}else{
				throw new QException(obj + " ����Boolean����");
			}
		}
		
		/**
		 * ��ȡString���͵�����
		 * @param index �������±�
		 * @return ���±��String����
		 * @throws QException ת����ʧ��
		 */
		
		public String getString(int index) 
				throws QException{
			Object obj = this.list.get(index);
			if(obj instanceof String){
				return (String)obj;
			}else{
				throw new QException(obj + " ����String����");
			}
		}
		
		/**
		 * �����ʽQJson.JSONArray[value1,value2����]
		 * @return  ��ʽQJson.JSONArray[value1,value2����]
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
	 * ���ڴ���json��{}����
	 * ��ֹ��<code>new</code>������������ͨ��<code>buildJSONObject</code>���һ��ʵ��
	 * @author �ʷʵ�����
	 * @version 2016-02-10
	 */
	public class JSONObject{
		
		private HashMap<String,Object> map;
		
		@SuppressWarnings("unused")
		private JSONObject(){};
		
		/**
		 * �ݹ鹹��HashMap���ظ��ϲ�
		 * @return str ��Ҫ�������ַ���
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
					throw new QException("Json����\"{\"��ʼ");
				}else if(i==length-1 && ch != '}'){
					throw new QException("Json����\"}\"����");
				}else if(ch == '"'){
					int keyIndex = str.indexOf("\"",i+1);//�ҵ���һ��
					String key =str.substring(i+1, keyIndex);
					if(str.charAt(keyIndex+1) != ':'){
						throw new QException("ȱʡ��");
					}
					char test = str.charAt(keyIndex+2);

					if(test == '['){
						//�����������
						int leftIndex = keyIndex+2;//�������±�
						int rightIndex = 0;//�������±�
						int left = 1;//�����ŵĸ���
						int right=0;//�����ŵĸ���
						
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
							//��ƥ��
							throw new QException("ȱʡ]");
						}else if(right > left){
							throw new QException("ȱʡ[");
						}else{
							String value = str.substring(leftIndex, rightIndex);
							JSONArray v = new JSONArray(value);
							m.put(key, v);
						}
						i= rightIndex-1;
						continue;
					}
					
					
					
					if(test == '{'){
						//����json����
						int leftIndex = keyIndex+2;//�������±�
						int rightIndex = 0;//�������±�
						int left = 1;//�����ŵĸ���
						int right=0;//�����ŵĸ���
						
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
							//��ƥ��
							throw new QException("ȱʡ{");
						}else if(right > left){
							//��ƥ��
							throw new QException("ȱʡ}");
						}else{
							//ƥ��
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
						throw new QException("����ʶ�����" + test);
					}
					
					
					
					
					//�����������
					int valueKey = str.indexOf(",",keyIndex);
					String value;
					if(valueKey == -1){
						//����û��������
						value = str.substring(keyIndex+2,length-1);
						i = length;//����ִ�������Ķ�����Ž�����������break
					}else{
						value = str.substring(keyIndex+2, valueKey);
						i = valueKey;
					}
					
					
					
					//�Ի������ͽ���ת�ͣ�Ĭ�����ַ���
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
					//��Ϊ[�Ľ��������棬����������������²�������]
					throw new QException("ȱʡ[");
				}else if(ch == '}' && i != length-1){
					throw new QException("ȱʡ{");
				}
			}
			return m;
		}
		
		/**
		 * ����strת����HashMap����
		 * @param str ��Ҫ�������ַ���
		 * @throws QException ȱʡ[]��{}�������쳣
		 */
		protected JSONObject(String str) throws QException{
			map = strToMap(str);			
		}
		
		/**
		 * �Լ��ϵ���ʽ����key��ȫ������
		 * @return ȫ�����Ƶļ���
		 */
		public Set<String> getKeys(){		
			return map.keySet();
		}
		
		
		/**
		 * ��ȡJSONObject��ӳ�����
		 * @return ����
		 */
		public int size(){
			return this.map.size();
		}
		
		
		
		
		/**
		 * ��ȡJSONObject���͵�����
		 * @param key ��ֵ
		 * @return ��Ӧֵ
		 * @throws QException ת������ʧ��
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
				throw new QException(obj + " ����JSONObject����");
			}
		}
		
		
		/**
		 * ��ȡJSONArray���͵�����
		 * @param key ��ֵ
		 * @return ��Ӧֵ
		 * @throws QException ת������ʧ��
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
				throw new QException(obj + " ����JSONArray����");
			}
		}
		
		/**
		 * ��ȡLongt���͵�����
		 * @param key ��ֵ
		 * @return ��Ӧֵ
		 * @throws QException ת������ʧ��
		 */
		
		public long getLong(String key) 
				throws QException{
			
			Object obj = this.map.get(key);
			if(obj instanceof Long){
				return (long)obj;
			}else{
				throw new QException(obj + " ����Long����");
			}
		}
		
		
		/**
		 * ��ȡDouble���͵�����
		 * @param key ��ֵ
		 * @return ��Ӧֵ
		 * @throws QException ת������ʧ��
		 */
		
		public double getDouble(String key) 
				throws QException{
			Object obj = this.map.get(key);
			if(obj instanceof Double){
				return (double)obj;
			}else{
				throw new QException(obj + " ����Double����");
			}
		}
		
		/**
		 * ��ȡBoolean���͵�����
		 * @param key ��ֵ
		 * @return ��Ӧֵ
		 * @throws QException ת������ʧ��
		 */
		public boolean getBoolean(String key) 
				throws QException{
			Object obj = this.map.get(key);
			if(obj instanceof Boolean){
				return (boolean)obj;
			}else{
				throw new QException(obj + " ����Boolean����");
			}
		}
		
		/**
		 * ��ȡString���͵�����
		 * @param key ��ֵ
		 * @return ��Ӧֵ
		 * @throws QException ת������ʧ��
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
				throw new QException(obj + " ����String����");
			}
		}
		
		/**
		 * ��ȡQJson.JSONObject{����}��ʽ��JSONObjetct�ַ���
		 * @return ����QJson.JSONObject{����}
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
	 * ���￪ʼ��QJson�������
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 */
	private JSONObject jsonObj;
	private String path; // Json�ļ�·��
	private String jsonStr;// json���ļ�·��
	private String lineSeparator ;
	
	
	
	
	/**
	 * ��ָ��·������QJson�����Զ���ȡ
	 * @param path �ļ�·��
	 * @throws QException ��IOExceptionת������
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
					//������1024��ʱ����ģ��ֹ��ȡbuff�ظ��Ĳ���
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
	 * fileName����QJson�����Զ���ȡ�ļ�����
	 * @param fileName File����
	  * @throws QException ��IOExceptionת������
	 */
	public QJson(File fileName) throws QException{
		this(fileName.getPath());
	}
	
	
	/**
	 * ����Json�ļ�������
	 * @return �ļ�����
	 */
	public String getJsonStr(){
		return this.jsonStr;
	}
	
	/**
	 * �����ڲ���jsonObje��ʵ��
	 * @return �ļ��۲���JSONObjectʵ��
	 */
	public JSONObject getJsonObj(){
		return this.jsonObj;
	}

	
	/**
	 * ʹ�û������ⲿͨ���������һ��JSONObject����
	 * @param str ��Ҫ�������ַ���
	 * @return  JSONObejct����
	 * @throws QException ��������в������쳣������ȱʡ
	 */
	public static JSONObject buildJSONObject(String str)
		throws QException{
			return new QJson("").new JSONObject(str);
	} 
	
	/**
	 * ʹ�û������ⲿͨ���������һ��JSONArray����
	 * @param str ��Ҫ�������ַ���
	 * @return  JSONArray����
	 * @throws QException ��������в������쳣������ȱʡ
	 */
	public static JSONArray buildJSONArray(String str)
		throws QException{
			return new QJson("").new JSONArray(str);
	} 
	
	
	/**
	 * ��HashMap��ʽ����д��json�ļ�
	 * @param json jsonӳ��
	 * @throws QException ��IOExceptionת������
	 */
	public void writeJson(Map<String,Object> json) 
			throws QException{
		//��json�����ַ�����ʽ����ñ�������������ʽ
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
	 * ��JSONObject��ʽд���ļ�
	 * @param jo ��Ҫд���JSONObject����
	 * @throws QException ��IOExceptionת������
	 */
	public void writeJson(JSONObject jo)throws QException{
		String str = jo.toString().replace("QJson.JSONObject", "");
		writeJson(str);
	}
	
	/**
	 * ���ַ�����ʽ����д��json�ļ�
	 * ͬʱ����jsonStr��ֵ
	 * @param jsonStr ��Ҫд����ַ���
	 * @throws QException ��IOExceptionת������
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
	 * ��ȡ�ڲ�JSONObeject����Ϣ
	 * @return jsonObj����Ϣ
	 */
	@Override
	public String toString(){
		return this.jsonObj.toString();
	}
}
