package qfile;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;



/**
 * 处理CSV后缀名文件的类,里面有一个矩阵，需要用<code>getMat()</code>获取到矩阵后才能进行相关运算
 * @author 肥肥兔
 * @version 2016-2-4
 */
public class QCSV extends File{

	private static final long serialVersionUID = 1L;
	
	
	/**
	 * 矩阵类，封装了基本属性和对数据的基本运算方法
	 * @author 肥肥的兔子
	 * @version 2016-02-10
	 */
	public class Matrix{
		
		private List<ArrayList<String> > l;
		private int xAxis;
		private int yAxis;
		
		@SuppressWarnings("unused")
		private Matrix(){};
		

		protected Matrix(List<ArrayList<String> > l){
			this.l = l;
			this.xAxis = l.size();
			int max = 0;
			for(int i=0;i<this.xAxis;i++){
				int length = l.get(i).size();
				if(length>max){
					max = length;
				}
			}
			this.yAxis = max;
		}
		 
		/**
		 * 获取行数
		 * @return 行数
		 */
		public int getXAxis(){
			return this.xAxis;
		}
		
		/**
		 * 获取最大的列数
		 * @return 列数
		 */
		public int getYAxis(){
			return this.yAxis;
		}
		
		/**
		 * 返回数据内容的字符串形式
		 * @return 内容的字符串形式
		 */
		public String getAllDataToStr(){
			return this.toString().replace("[", "").replace("]", "");
		}
		
		/*
		 * 设置数据的接口，只有一个
		 */
		/**
		 * 更改数据
		 * @param x 行
		 * @param y 列
		 * @param data 需要修改的数据
		 * @throws QException 越界触发异常
		 */
		public void setData(int x,int y,String data) throws QException{
			if (x <0||x>=this.xAxis) {throw new QException("x少于0或越界");}
			if (y <0||y>=this.l.get(x).size()){throw new QException("y少于0或越界");}
			this.l.get(x).set(y, data);
		}
		
		
		/*
		 * 获取数据的接口
		 * 可获取内容 integer long float double String boolean
		 * 也可以获取子矩阵
		 */
		
		/**
		 * 获取String数据
		 * @param x 行
		 * @param y 列
		 * @return 数据
		 * @throws QException 坐标越界
		 */
		public String getDataToStr(int x,int y)
			throws QException{
			
			if(x >=this.xAxis){
				throw new QException("x坐标越界");
			}else{
				ArrayList<String> a = this.l.get(x);
				if(y >= a.size()){throw new QException("y坐标越界");}
				else{return a.get(y);}
			}
		}
		/**
		 * 获取integer数据
		 * @param x 行
		 * @param y 列
		 * @return 数据
		 * @throws QException 坐标越界，转换失败
		 */
		public int getDataToInt(int x,int y)
				throws QException{
				
				if(x >=this.xAxis){
					throw new QException("x坐标越界");
				}else{
					ArrayList<String> a = this.l.get(x);
					if(y >= a.size()){throw new QException("y坐标越界");}
					else{
						try{
							return Integer.parseInt(a.get(y) );
						}catch(NumberFormatException err){
							throw new QException("该数据不可转换成Interger");
						}
						
					}
				}
		}
		/**
		 * 获取long数据
		 * @param x 行
		 * @param y 列
		 * @return 数据
		 * @throws QException 坐标越界，转换失败
		 */
		
		public long getDataToLong(int x,int y)
				throws QException{
				
				if(x >=this.xAxis){
					throw new QException("x坐标越界");
				}else{
					ArrayList<String> a = this.l.get(x);
					if(y >= a.size()){throw new QException("y坐标越界");}
					else{
						try{
							return Long.parseLong(a.get(y) );
						}catch(NumberFormatException err){
							throw new QException("该数据不可转换成Long");
						}
						
					}
				}
		}
		
		/**
		 * 获取float数据
		 * @param x 行
		 * @param y 列
		 * @return 数据
		 * @throws QException 坐标越界，转换失败
		 */
		
		public float getDataToFloat(int x,int y)
				throws QException{
				
				if(x >=this.xAxis){
					throw new QException("x坐标越界");
				}else{
					ArrayList<String> a = this.l.get(x);
					if(y >= a.size()){throw new QException("y坐标越界");}
					else{
						try{
							return Float.parseFloat(a.get(y) );
						}catch(NumberFormatException err){
							throw new QException("该数据不可转换成Float");
						}
						
					}
				}
		}
		
		/**
		 * 获取double数据
		 * @param x 行
		 * @param y 列
		 * @return 数据
		 * @throws QException 坐标越界，转换失败
		 */
		
		public double getDataToDouble(int x,int y)
				throws QException{
				
				if(x >=this.xAxis){
					throw new QException("x坐标越界");
				}else{
					ArrayList<String> a = this.l.get(x);
					if(y >= a.size()){throw new QException("y坐标越界");}
					else{
						try{
							return Double.parseDouble(a.get(y) );
						}catch(NumberFormatException err){
							throw new QException("该数据不可转换成Double");
						}
						
					}
				}
		}
		
		/**
		 * 获取boolean数据
		 * @param x 行
		 * @param y 列
		 * @return 数据
		 * @throws QException 坐标越界，转换失败
		 */
		
		public boolean getDataToBoolean(int x,int y)
				throws QException{
				
				if(x >=this.xAxis){
					throw new QException("x坐标越界");
				}else{
					ArrayList<String> a = this.l.get(x);
					if(y >= a.size()){throw new QException("y坐标越界");}
					else{
						try{
							return Boolean.parseBoolean(a.get(y) );
						}catch(NumberFormatException err){
							throw new QException("该数据不可转换成Boolean");
						}
					}
				}
		}
		
		/**
		 * 划分（bx，by）到（ex，ey）成小矩阵
		 * @param bx 开始的行
		 * @param by 开始的列
		 * @param ex 结束的行
		 * @param ey 结束的列
		 * @return 矩阵
		 * @throws QException 越界
		 */
		
		public Matrix getDataToMatrix(int bx,int by,int ex,int ey)
			throws QException{
			if(bx < 0||ex >= this.xAxis){throw new QException("x越界" + bx + "->" + ex);}
			else{
				List<ArrayList<String>> a = new ArrayList<ArrayList<String>>();
				for(int i = bx;i <= ex; i++){
					ArrayList<String> list = this.l.get(i);
					if(by < 0||ey >= list.size()){throw new QException("y越界" + by + "->" + ey);}
					else{
						ArrayList<String> subList = new ArrayList<String>();
						for(int j = by;j <= ey;j++){
							String value = list.get(j);
							
							subList.add(value);
						}
						a.add(subList);
					}
				}
				return new Matrix(a);
			}
		}
		
		
		/*
		 * 求和的接口
		 * 单行求和，索引b到e
		 * 单列求和，索引b到e
		 * 单行相乘，索引b到e
		 * 单列相乘，索引b到e
		 * 返回数据类型 Integer float double long 
		 */
		/**
		 * 索引x行，把x行从b到e的数据求和，用Integer保存结果并返回
		 * @param x 行数
		 * @param b 开始
		 * @param e 结束
		 * @return 结果
		 * @throws QException b大于e，越界，不能转型
		 */
		
		public int sumXToInt(int x,int b,int e) throws QException{
			int sum = 0;
			if(b > e){throw new QException("b 不能大于 e");}
			if(x < 0 || x >= xAxis){throw new QException("x少于0或越界");}
			else{
				ArrayList<String> a = this.l.get(x);
				if(b < 0 || e >= a.size()){ throw new QException(b+"少于0或"+e+"大于"+a.size());}
				for(int i=b;i <= e;i++){
					
					try{
						sum += Integer.parseInt(a.get(i));
					}catch(NumberFormatException err){
						throw new QException(a.get(i)+ "--不能转化成Integer");
					}
					
				}
			}
			return sum;
			
		}
		
		
		/**
		 * 索引x行求和，用Integer保存结果并返回
		 * @param x 行数
		 * @return 结果
		 * @throws QException b大于e，越界，不能转型
		 */
		public int sumXToInt(int x) throws QException{
			
			if(x < 0 || x >= xAxis){throw new QException("x少于0或越界");}
			else{
				int length = this.l.get(x).size();
				return this.sumXToInt(x, 0, length-1);
			}

		}
			
		
		/**
		 * 索引x行求和，把x行从b到e的数据求和，用long保存结果并返回
		 * @param x 行数
		 * @param b 开始
		 * @param e 结束
		 * @return 结果
		 * @throws QException b大于e，越界，不能转型
		 */
		
		public long sumXToLong(int x,int b,int e) throws QException{
			long sum = 0;
			if(b > e){throw new QException("b 不能大于 e");}
			if(x < 0 || x >= xAxis){throw new QException("x少于0或越界");}
			else{
				ArrayList<String> a = this.l.get(x);
				if(b < 0 || e >= a.size()){ throw new QException(b+"少于0或"+e+"大于"+a.size());}
				for(int i=b;i < e;i++){
					
					try{
						sum += Long.parseLong(a.get(i));
					}catch(NumberFormatException err){
						throw new QException(a.get(i) + "--不能转化成Long");
					}
					
				}
			}
			return sum;
			
		}
		
		
		/**
		 * 索引x行求和，把x行从b到e的数据求和，用long保存结果并返回
		 * @param x 行数
		 * @return 结果
		 * @throws QException b大于e，越界，不能转型
		 */
		public long sumXToLong(int x) throws QException{
			
			if(x < 0 || x >= xAxis){throw new QException("x少于0或越界");}
			else{
				int length = this.l.get(x).size();
				return this.sumXToLong(x, 0, length-1);
			}

		}
		
		
		
		/**
		 * 索引x行求和，把x行从b到e的数据求和，用float保存结果并返回
		 * @param x 行数
		 * @param b 开始
		 * @param e 结束
		 * @return 结果
		 * @throws QException b大于e，越界，不能转型
		 */
		
		public float sumXToFloat(int x,int b,int e) throws QException{
			float sum = 0.0f;
			if(b > e){throw new QException("b 不能大于 e");}
			if(x < 0 || x >= xAxis){throw new QException("x少于0或越界");}
			else{
				ArrayList<String> a = this.l.get(x);
				if(b < 0 || e >= a.size()){ throw new QException(b+"少于0或"+e+"大于"+a.size() );}
				for(int i=0;i < a.size();i++){
					
					try{
						sum += Float.parseFloat(a.get(i));
					}catch(NumberFormatException err){
						throw new QException(a.get(i) + "--不能转化成Float");
					}
					
				}
			}
			return sum;
			
		}
		
		
		/**
		 * 索引x行求和，把x行从b到e的数据求和，用float保存结果并返回
		 * @param x 行数
		 * @return 结果
		 * @throws QException b大于e，越界，不能转型
		 */
		public float sumXToFloat(int x) throws QException{
			
			if(x < 0 || x >= xAxis){throw new QException("x少于0或越界");}
			else{
				int length = this.l.get(x).size();
				return this.sumXToFloat(x, 0, length-1);
			}

		}
		
		
		/**
		 * 索引x行求和，把x行从b到e的数据求和，用double保存结果并返回
		 * @param x 行数
		 * @param b 开始
		 * @param e 结束
		 * @return 结果
		 * @throws QException b大于e，越界，不能转型
		 */
		public double sumXToDouble(int x,int b,int e) throws QException{
			double sum = 0.0;
			if(b > e){throw new QException("b 不能大于 e");}
			if(x < 0 || x >= xAxis){throw new QException("x少于0或越界");}
			else{
				ArrayList<String> a = this.l.get(x);
				if(b < 0 || e >= a.size()){ throw new QException(b+"少于0或"+e+"大于"+a.size() );}
				for(int i=0;i < a.size();i++){
					
					try{
						sum += Double.parseDouble(a.get(i));
					}catch(NumberFormatException err){
						throw new QException(a.get(i) + "--不能转化成Dpublt");
					}
					
				}
			}
			return sum;
			
		}
		
		
		/**
		 * 索引x行求和，把x行从b到e的数据求和，用double保存结果并返回
		 * @param x 行数
		 * @return 结果
		 * @throws QException b大于e，越界，不能转型
		 */
		public double sumXToDouble(int x) throws QException{
			
			if(x < 0 || x >= xAxis){throw new QException("x少于0或越界");}
			else{
				int length = this.l.get(x).size();
				return this.sumXToDouble(x, 0, length-1);
			}

		}
		
		/**
		 * 索引y列求和，从b到e的数据求和，用int保存结果并返回
		 * @param y 列数
		 * @param b 开始
		 * @param e 结束
		 * @return 结果
		 * @throws QException b大于e，越界，不能转型
		 */
		public int sumYToInt(int y,int b,int e) throws QException{
			if(b > e){throw new QException("b 不能大于 e");}
			int length = this.xAxis;
			int sum = 0;
			if(b < 0 || e >= length){ throw new QException(b+"少于0或"+e+"大于"+length );}
			for(int i = b;i<=e;i++){
				ArrayList<String> a = this.l.get(i);
				if(a.size()>=y){
					sum += Integer.parseInt(a.get(y));
				}
			}
			return sum;
		}
		
		/**
		 * 索引y列求和，的数据求和，用integer保存结果并返回
		 * @param y 行数
		 * @return 结果
		 * @throws QException 越界，不能转型
		 */
		public int sumYToInt(int y) throws QException{
			return sumYToInt(y,0,this.xAxis-1);
		}
		
		
		
		/**
		 * 索引y列，从b到e的数据求和，用long保存结果并返回
		 * @param y 列数
		 * @param b 开始
		 * @param e 结束
		 * @return 结果
		 * @throws QException b大于e，越界，不能转型
		 */
		public long sumYToLong(int y,int b,int e) throws QException{
			if(b > e){throw new QException("b 不能大于 e");}
			int length = this.xAxis;
			long sum = 0l;
			if(b < 0 || e >= length){ throw new QException(b+"少于0或"+e+"大于"+length );}
			for(int i = b;i<=e;i++){
				ArrayList<String> a = this.l.get(i);
				if(a.size()>=y){
					sum += Long.parseLong(a.get(y));
				}
			}
			return sum;
		}
		
		/**
		 * 索引y列，数据求和，用long保存结果并返回
		 * @param y 列数
		 * @return 结果
		 * @throws QException 越界，不能转型
		 */
		public long sumYToLong(int y) throws QException{
			return sumYToLong(y,0,this.xAxis-1);
		}
		
		/**
		 * 索引y列，从b到e的数据求和，用float保存结果并返回
		 * @param y 列数
		 * @param b 开始
		 * @param e 结束
		 * @return 结果
		 * @throws QException b大于e，越界，不能转型
		 */
		public float sumYToFloat(int y,int b,int e) throws QException{
			if(b > e){throw new QException("b 不能大于 e");}
			int length = this.xAxis;
			float sum = 0.0f;
			if(b < 0 || e >= length){ throw new QException(b+"少于0或"+e+"大于"+length );}
			for(int i = b;i<=e;i++){
				ArrayList<String> a = this.l.get(i);
				if(a.size()>=y){
					sum += Float.parseFloat(a.get(y));
				}
			}
			return sum;
		}
		
		/**
		 * 索引y列，数据求和，用float保存结果并返回
		 * @param y 列数
		 * @return 结果
		 * @throws QException 越界，不能转型
		 */
		public float sumYToFlaot(int y) throws QException{
			return sumYToFloat(y,0,this.xAxis-1);
		}
		
		
		/**
		 * 索引y列，从b到e的数据求和，用double保存结果并返回
		 * @param y 列数
		 * @param b 开始
		 * @param e 结束
		 * @return 结果
		 * @throws QException b大于e，越界，不能转型
		 */
		public double sumYToDouble(int y,int b,int e) throws QException{
			if(b > e){throw new QException("b 不能大于 e");}
			int length = this.xAxis;
			double sum = 0.0;
			if(b < 0 || e >= length){ throw new QException(b+"少于0或"+e+"大于"+length );}
			for(int i = b;i<=e;i++){
				ArrayList<String> a = this.l.get(i);
				if(a.size()>=y){
					sum += Double.parseDouble(a.get(y));
				}
			}
			return sum;
		}
		
		/**
		 * 索引y列，数据求和，用double保存结果并返回
		 * @param y 列数
		 * @return 结果
		 * @throws QException 越界，不能转型
		 */
		public double sumYToDouble(int y) throws QException{
			return sumYToDouble(y,0,this.xAxis-1);
		}

////////////////////////求积
		/**
		 * 索引b到e行的第y个进行求积，用int保存结果并返回
		 * @param y 列数
		 * @param b 开始
		 * @param e 结束
		 * @return 结果
		 * @throws QException b大于e，越界，不能转型
		 */
		public int mulYToInt(int y,int b,int e) throws QException{
			if(b > e){throw new QException("b 不能大于 e");}
			int length = this.xAxis;
			int sum = 1;
			if(b < 0 || e >= length){ throw new QException(b+"少于0或"+e+"大于"+length );}
			for(int i = b;i<=e;i++){
				ArrayList<String> a = this.l.get(i);
				if(a.size()>=y){
					sum *= Double.parseDouble(a.get(y));
				}
			}
			return sum;
		}
		
		/**
		 * 索引b到e行的第y个进行求积，用int保存结果并返回
		 * @param y 列数
		 * @return 结果
		 * @throws QException 越界，不能转型
		 */
		public int mulYToInt(int y) throws QException{
			return mulYToInt(y,0,this.xAxis-1);
		}
		
		
		/**
		 * 索引b到e行的第y个进行求积，用long保存结果并返回
		 * @param y 列数
		 * @param b 开始
		 * @param e 结束
		 * @return 结果
		 * @throws QException b大于e，越界，不能转型
		 */
		public long mulYToLong(int y,int b,int e) throws QException{
			if(b > e){throw new QException("b 不能大于 e");}
			int length = this.xAxis;
			long sum = 1l;
			if(b < 0 || e >= length){ throw new QException(b+"少于0或"+e+"大于"+length );}
			for(int i = b;i<=e;i++){
				ArrayList<String> a = this.l.get(i);
				if(a.size()>=y){
					sum *= Double.parseDouble(a.get(y));
				}
			}
			return sum;
		}
		
		/**
		 * 索引b到e行的第y个进行求积，用long保存结果并返回
		 * @param y 列数
		 * @return 结果
		 * @throws QException 越界，不能转型
		 */
		public long mulYToLong(int y) throws QException{
			return mulYToLong(y,0,this.xAxis-1);
		}
		
		
		/**
		 * 索引b到e行的第y个进行求积，用float保存结果并返回
		 * @param y 列数
		 * @param b 开始
		 * @param e 结束
		 * @return 结果
		 * @throws QException b大于e，越界，不能转型
		 */
		public float mulYToFloat(int y,int b,int e) throws QException{
			if(b > e){throw new QException("b 不能大于 e");}
			int length = this.xAxis;
			float sum = 1.0f;
			if(b < 0 || e >= length){ throw new QException(b+"少于0或"+e+"大于"+length );}
			for(int i = b;i<=e;i++){
				ArrayList<String> a = this.l.get(i);
				if(a.size()>=y){
					sum *= Double.parseDouble(a.get(y));
				}
			}
			return sum;
		}
		
		/**
		 * 索引b到e行的第y个进行求积，用float保存结果并返回
		 * @param y 列数
		 * @return 结果
		 * @throws QException 越界，不能转型
		 */
		public float mulYToFloat(int y) throws QException{
			return mulYToFloat(y,0,this.xAxis-1);
		}
		
		
		
		/**
		 * 索引b到e行的第y个进行求积，用double保存结果并返回
		 * @param y 列数
		 * @param b 开始
		 * @param e 结束
		 * @return 结果
		 * @throws QException b大于e，越界，不能转型
		 */
		public double mulYToDouble(int y,int b,int e) throws QException{
			if(b > e){throw new QException("b 不能大于 e");}
			int length = this.xAxis;
			double sum = 1.0;
			if(b < 0 || e >= length){ throw new QException(b+"少于0或"+e+"大于"+length );}
			for(int i = b;i<=e;i++){
				ArrayList<String> a = this.l.get(i);
				if(a.size()>=y){
					sum *= Double.parseDouble(a.get(y));
				}
			}
			return sum;
		}
		
		/**
		 * 索引b到e行的第y个进行求积，用double保存结果并返回
		 * @param y 列数

		 * @return 结果
		 * @throws QException 越界，不能转型
		 */
		public double mulYToDouble(int y) throws QException{
			return mulYToDouble(y,0,this.xAxis-1);
		}
		
		
		
		
		/*
		 * 矩阵运算
		 * 支持行求和，列求和
		 * 支持行相乘，列相乘
		 * 返回子矩阵，子矩阵的数据储存会默认以字符串的形式储
		 * 其运算中间过程可用Integer Float Double Long作为临时值
		 * 
		 */
		
	
////////////////////////////////////////////////////////////////矩阵求和		
		/**
		 * 对实现b行到e行求和 ，用int保存，并把数据组成矩阵
		 * 
		 * @param b 开始
		 * @param e 结束
		 * @return 返回一行分别表示各列之和
		 * @throws QException b大于e，越界，类型错误
		 */
		public Matrix sumXMatWithInt(int b,int e) throws QException{
			if(b > e){throw new QException("b 不能大于 e");}
			if(b<0||e>=this.xAxis){throw new QException(b+"少于0或"+e+"大于"+this.xAxis);}
			List<ArrayList<String>> la = new ArrayList<ArrayList<String>>();
			
			ArrayList<Integer> temp = new ArrayList<Integer>();
			
			for(int i = 0;i<yAxis;i++){
				temp.add(0);
			}
			
			int maxSize = 0;
			for(int i = b;i<=e;i++){
				ArrayList<String> a = this.l.get(i);
				for(int j = 0;j<a.size();j++){
					try{
						temp.set(j,Integer.parseInt(a.get(j))+temp.get(j));
					}catch(NumberFormatException err){
						throw new QException(a.get(j) + " 不能转换Integer");
					}
				}
				
				if(a.size()>maxSize){
					maxSize = a.size();
				}
			}
			
			ArrayList<String> temp2 = new ArrayList<String>();
			
			for(int i = 0;i< maxSize;i++){
				temp2.add(String.valueOf(temp.get(i) ) );
			}
			
			la.add(temp2);
			return new Matrix(la);
			
		}
		
		
		/**
		 * 以行为单位求和，用int保存，并把数据组成矩阵
		 * @return 返回一行分别表示各列之和
		 * @throws QException 越界，类型错误
		 */
		
		public Matrix sumXMatWithInt() throws QException{
			return sumXMatWithInt( 0, this.xAxis-1);	
		}
		
		
		/**
		 * 对实现b行到e行求和，用long保存，并把数据组成矩阵
		 * 
		 * @param b 开始
		 * @param e 结束
		 * @return 返回一行分别表示各列之和
		 * @throws QException b大于e，越界，类型错误
		 */
		public Matrix sumXMatWithLong(int b,int e) throws QException{
			
			if(b > e){throw new QException("b 不能大于 e");}
			if(b<0||e>=this.xAxis){throw new QException(b+"少于0或"+e+"大于"+this.xAxis);}
			List<ArrayList<String>> la = new ArrayList<ArrayList<String>>();
			
			ArrayList<Long> temp = new ArrayList<Long>();
			
			for(int i = 0;i<yAxis;i++){
				temp.add(0l);
			}
			
			int maxSize = 0;
			for(int i = b;i<=e;i++){
				ArrayList<String> a = this.l.get(i);
				for(int j = 0;j<a.size();j++){
					try{
						temp.set(j,Long.parseLong(a.get(j))+temp.get(j));
					}catch(NumberFormatException err){
						throw new QException(a.get(j) + "不能转换成Long");
					}
				}
				
				if(a.size()>maxSize){
					maxSize = a.size();
				}
			}
			
			ArrayList<String> temp2 = new ArrayList<String>();
			
			for(int i = 0;i< maxSize;i++){
				temp2.add(String.valueOf(temp.get(i) ) );
			}
			
			la.add(temp2);
			return new Matrix(la);
			
		}
		
		
		/**
		 * 以行为单位求和，用long保存，并把数据组成矩阵
		 * @return 返回一行分别表示各列之和
		 * @throws QException 越界，类型错误
		 */
		
		public Matrix sumXMatWithLong() throws QException{
			return sumXMatWithLong( 0, this.xAxis-1);	
		}
		
		
		
		/**
		 * 对实现b行到e行求和，用float保存，并把数据组成矩阵
		 * 
		 * @param b 开始
		 * @param e 结束
		 * @return 返回一行分别表示各列之和
		 * @throws QException b大于e，越界，类型错误
		 */
		public Matrix sumXMatWithFloat(int b,int e) throws QException{
			if(b > e){throw new QException("b 不能大于 e");}
			if(b<0||e>=this.xAxis){throw new QException(b+"少于0或"+e+"大于"+this.xAxis);}
			List<ArrayList<String>> la = new ArrayList<ArrayList<String>>();
			
			ArrayList<Float> temp = new ArrayList<Float>();
			
			for(int i = 0;i<yAxis;i++){
				temp.add(0.0f);
			}
			
			int maxSize = 0;
			for(int i = b;i<=e;i++){
				ArrayList<String> a = this.l.get(i);
				for(int j = 0;j<a.size();j++){
					try{
						temp.set(j,Float.parseFloat(a.get(j))+temp.get(j));
					}catch(NumberFormatException err){
						throw new QException(a.get(j) + "不能转换float");
					}
				}
				
				if(a.size()>maxSize){
					maxSize = a.size();
				}
			}
			
			ArrayList<String> temp2 = new ArrayList<String>();
			
			for(int i = 0;i< maxSize;i++){
				temp2.add(String.valueOf(temp.get(i) ) );
			}
			
			la.add(temp2);
			return new Matrix(la);
			
		}
		
		
		/**
		 * 以行为单位求和，用float保存，并把数据组成矩阵
		 * @return 返回一行分别表示各列之和
		 * @throws QException 越界，类型错误
		 */
		public Matrix sumXMatWithFloat() throws QException{
			return sumXMatWithFloat( 0, this.xAxis-1);	
		}
		
		
		/**
		 * 对实现b行到e行求和，用double保存，并把数据组成矩阵
		 * 
		 * @param b 开始
		 * @param e 结束
		 * @return 返回一行分别表示各列之和
		 * @throws QException b大于e，越界，类型错误
		 */
		public Matrix sumXMatWithDouble(int b,int e) throws QException{
			if(b > e){throw new QException("b 不能大于 e");}
			if(b<0||e>=this.xAxis){throw new QException(b+"少于0或"+e+"大于"+this.xAxis);}
			List<ArrayList<String>> la = new ArrayList<ArrayList<String>>();
			
			ArrayList<Double> temp = new ArrayList<Double>();
			
			for(int i = 0;i<yAxis;i++){
				temp.add(0.0);
			}
			
			int maxSize = 0;
			for(int i = b;i<=e;i++){
				ArrayList<String> a = this.l.get(i);
				for(int j = 0;j<a.size();j++){
					try{
						temp.set(j,Double.parseDouble(a.get(j))+temp.get(j));
					}catch(NumberFormatException err){
						throw new QException(a.get(j) + "不能转换为double");
					}
				}
				
				if(a.size()>maxSize){
					maxSize = a.size();
				}
			}
			
			ArrayList<String> temp2 = new ArrayList<String>();
			
			for(int i = 0;i< maxSize;i++){
				temp2.add(String.valueOf(temp.get(i) ) );
			}
			
			la.add(temp2);
			return new Matrix(la);
			
		}
		
		
		/**
		 * 以行为单位求和，用double保存，并把数据组成矩阵
		 * @return 返回一行分别表示各列之和
		 * @throws QException 越界，类型错误
		 */
		
		public Matrix sumXMatWithDouble() throws QException{
			return sumXMatWithDouble( 0, this.xAxis-1);	
		}
		
		/**
		 * 对实现b列到e列求和，用int保存，并把数据组成矩阵
		 * 
		 * @param b 开始
		 * @param e 结束
		 * @return 返回一列分别表示各行之和
		 * @throws QException b大于e，越界，类型错误
		 */
		
		public Matrix sumYMatWithInt(int b,int e) throws QException{
			if(b > e){throw new QException("b 不能大于 e");}
			if(b < 0){throw new QException("b 不能小于0");}
			List<ArrayList<String>> la = new ArrayList<ArrayList<String>>();
			
			
			
			for(int i = 0;i <this.xAxis;i++){
				
				ArrayList<String> temp = new ArrayList<String>();
				ArrayList<String> a = this.l.get(i);
				if(e>=a.size()){throw new QException("e越界");}
				int sum = 0;
				for(int j = b; j <= e;j++){
					int value = 0;
					try{
						value = Integer.parseInt(a.get(j));
					}catch(NumberFormatException err){
						throw new QException(a.get(j) + " 不能转换成Integer");
					}
					sum += value;
				}
				temp.add(String.valueOf(sum));
				la.add(temp);
			}
			
			return new Matrix(la);
		}
		
		
		/**
		 * 以列为单位求和，用int保存，并把数据组成矩阵
		 * @return 返回一列分别表示各行之和
		 * @throws QException 越界，类型错误
		 */
		public Matrix sumYMatWithInt() throws QException{
			return this.sumYMatWithInt(0, this.yAxis-1);
		}
		
		
		/**
		 * 对实现b列到e列求和，用long保存，并把数据组成矩阵
		 * 
		 * @param b 开始
		 * @param e 结束
		 * @return 返回一列分别表示各行之和
		 * @throws QException b大于e，越界，类型错误
		 */
		
		public Matrix sumYMatWithLong(int b,int e) throws QException{
			if(b > e){throw new QException("b 不能大于 e");}
			if(b < 0){throw new QException("b 不能小于0");}
			List<ArrayList<String>> la = new ArrayList<ArrayList<String>>();
			
			
			
			for(int i = 0;i <this.xAxis;i++){
				
				ArrayList<String> temp = new ArrayList<String>();
				ArrayList<String> a = this.l.get(i);
				if(e>=a.size()){throw new QException("e越界");}
				long sum = 0l;
				for(int j = b; j <= e;j++){
					long value = 0;
					try{
						value = Long.parseLong(a.get(j));
					}catch(NumberFormatException err){
						throw new QException(a.get(j) + " 不能转换成Long");
					}
					sum += value;
				}
				temp.add(String.valueOf(sum));
				la.add(temp);
			}
			
			return new Matrix(la);
		}
		
		
		/**
		 * 以列为单位求和，用long保存，并把数据组成矩阵
		 * @return 返回一列分别表示各行之和
		 * @throws QException 越界，类型错误
		 */
		public Matrix sumYMatWithLong() throws QException{
			return this.sumYMatWithLong(0, this.yAxis-1);
		}
		
		
		/**
		 * 对实现b列到e列求和，用float保存，并把数据组成矩阵
		 * 
		 * @param b 开始
		 * @param e 结束
		 * @return 返回一列分别表示各行之和
		 * @throws QException b大于e，越界，类型错误
		 */
		public Matrix sumYMatWithFloat(int b,int e) throws QException{
			if(b > e){throw new QException("b 不能大于 e");}
			if(b < 0){throw new QException("b 不能小于0");}
			List<ArrayList<String>> la = new ArrayList<ArrayList<String>>();
			
			
			
			for(int i = 0;i <this.xAxis;i++){
				
				ArrayList<String> temp = new ArrayList<String>();
				ArrayList<String> a = this.l.get(i);
				if(e>=a.size()){throw new QException("e越界");}
				float sum = 0.0f;
				for(int j = b; j <= e;j++){
					float value = 0;
					try{
						value = Float.parseFloat(a.get(j));
					}catch(NumberFormatException err){
						throw new QException(a.get(j) + " 不能转换成Float");
					}
					sum += value;
				}
				temp.add(String.valueOf(sum));
				la.add(temp);
			}
			
			return new Matrix(la);
		}
		
		
		/**
		 * 以列为单位求和，用float保存，并把数据组成矩阵
		 * @return 返回一列分别表示各行之和
		 * @throws QException 越界，类型错误
		 */
		public Matrix sumYMatWithFloat() throws QException{
			return this.sumYMatWithFloat(0, this.yAxis-1);
		}
		
		/**
		 * 对实现b列到e列求和，用double保存，并把数据组成矩阵
		 * 
		 * @param b 开始
		 * @param e 结束
		 * @return 返回一列分别表示各行之和
		 * @throws QException b大于e，越界，类型错误
		 */
		public Matrix sumYMatWithDouble(int b,int e) throws QException{
			if(b > e){throw new QException("b 不能大于 e");}
			if(b < 0){throw new QException("b 不能小于0");}
			List<ArrayList<String>> la = new ArrayList<ArrayList<String>>();
			
			
			
			for(int i = 0;i <this.xAxis;i++){
				
				ArrayList<String> temp = new ArrayList<String>();
				ArrayList<String> a = this.l.get(i);
				if(e>=a.size()){throw new QException("e越界");}
				double sum = 0.0;
				for(int j = b; j <= e;j++){
					double value = 0;
					try{
						value = Double.parseDouble(a.get(j));
					}catch(NumberFormatException err){
						throw new QException(a.get(j) + " 不能转换成Double");
					}
					sum += value;
				}
				temp.add(String.valueOf(sum));
				la.add(temp);
			}
			
			return new Matrix(la);
		}
		
		
		/**
		 * 以列为单位求和，用double保存，并把数据组成矩阵
		 * @return 返回一列分别表示各行之和
		 * @throws QException 越界，类型错误
		 */
		public Matrix sumYMatWithDouble() throws QException{
			return this.sumYMatWithDouble( 0, this.yAxis-1);
		}
		
		
		
		
		/**
		 * 规格相同的矩阵与自身相加
		 * @param m 需要相加的矩阵
		 * @return 以矩阵表示，用int做限制
		 * @throws QException 规格不符，类型转换失败
		 */
		public Matrix sumMatWithInt(Matrix m) throws QException{
			List<ArrayList<String>> la = new ArrayList<ArrayList<String>>();
			if(!(this.xAxis == m.xAxis && this.yAxis == m.yAxis)){throw new QException("矩阵的xy必需相同");}
			for(int i = 0;i < xAxis;i++){
				ArrayList<String> temp = new ArrayList<String>();
				for(int j = 0;j < yAxis;j++){
					int value = 0;
					try{
						value = Integer.parseInt(this.l.get(i).get(j) )  + m.getDataToInt(i, j);
					}catch(QException err){
						throw err;
					}catch(NumberFormatException e){
						throw new QException("本矩阵"+ this.l.get(i).get(j) + "不能转换成Integer");
					}
					temp.add(String.valueOf(value));
				}
				la.add(temp);
			}
			return new Matrix(la);
		}
		
		/**
		 * 对m按（bx，by）到（ex，ey）进行切割后与自身相加
		 * @param m 切割的矩阵
		 * @param bx 开始的x
		 * @param by 开始的y
		 * @param ex 结束的x
		 * @param ey 结束的y
		 * @return 以矩阵表示，用int做限制
		 * @throws QException 规格不符，类型转换失败
		 */
		public Matrix sumMatWithInt(Matrix m,int bx,int by,int ex,int ey) 
				throws QException{
			Matrix m1 = m.getDataToMatrix(bx, by, ex, ey);
			return sumMatWithInt(m1);
		}
		
		
		/**
		 * 规格相同的矩阵与自身相加
		 * @param m 需要相加的矩阵
		 * @return 以矩阵表示，用long做限制
		 * @throws QException 规格不符，类型转换失败
		 */
		public Matrix sumMatWithLong(Matrix m) throws QException{
			List<ArrayList<String>> la = new ArrayList<ArrayList<String>>();
			if(!(this.xAxis == m.xAxis && this.yAxis == m.yAxis)){throw new QException("矩阵的xy必需相同");}
			for(int i = 0;i < xAxis;i++){
				ArrayList<String> temp = new ArrayList<String>();
				for(int j = 0;j < yAxis;j++){
					long value = 0;
					try{
						value = Long.parseLong(this.l.get(i).get(j) )  + m.getDataToLong(i, j);
					}catch(QException err){
						throw err;
					}catch(NumberFormatException e){
						throw new QException("本矩阵"+ this.l.get(i).get(j) + "不能转换成Long");
					}
					temp.add(String.valueOf(value));
				}
				la.add(temp);
			}
			return new Matrix(la);
		}
		
		/**
		 * 对m按（bx，by）到（ex，ey）进行切割后与自身相加
		 * @param m 切割的矩阵
		 * @param bx 开始的x
		 * @param by 开始的y
		 * @param ex 结束的x
		 * @param ey 结束的y
		 * @return 以矩阵表示，用long做限制
		 * @throws QException 规格不符，类型转换失败
		 */
		public Matrix sumMatWithLong(Matrix m,int bx,int by,int ex,int ey) 
				throws QException{
			Matrix m1 = m.getDataToMatrix(bx, by, ex, ey);
			return sumMatWithLong(m1);
		}
		
		
		/**
		 * 规格相同的矩阵与自身相加
		 * @param m 需要相加的矩阵
		 * @return 以矩阵表示，用float做限制
		 * @throws QException 规格不符，类型转换失败
		 */
		public Matrix sumMatWithFloat(Matrix m) throws QException{
			List<ArrayList<String>> la = new ArrayList<ArrayList<String>>();
			if(!(this.xAxis == m.xAxis && this.yAxis == m.yAxis)){throw new QException("矩阵的xy必需相同");}
			for(int i = 0;i < xAxis;i++){
				ArrayList<String> temp = new ArrayList<String>();
				for(int j = 0;j < yAxis;j++){
					float value = 0.0f;
					try{
						value = Float.parseFloat(this.l.get(i).get(j) )  + m.getDataToFloat(i, j);
					}catch(QException err){
						throw err;
					}catch(NumberFormatException e){
						throw new QException("本矩阵"+ this.l.get(i).get(j) + "不能转换成Float");
					}
					temp.add(String.valueOf(value));
				}
				la.add(temp);
			}
			return new Matrix(la);
		}
		
		/**
		 * 对m按（bx，by）到（ex，ey）进行切割后与自身相加
		 * @param m 切割的矩阵
		 * @param bx 开始的x
		 * @param by 开始的y
		 * @param ex 结束的x
		 * @param ey 结束的y
		 * @return 以矩阵表示，用float做限制
		 * @throws QException 规格不符，类型转换失败
		 */
		public Matrix sumMatWithFloat(Matrix m,int bx,int by,int ex,int ey) 
				throws QException{
			Matrix m1 = m.getDataToMatrix(bx, by, ex, ey);
			return sumMatWithFloat(m1);
		}
		
		
		
		/**
		 * 规格相同的矩阵与自身相加
		 * @param m 需要相加的矩阵
		 * @return 以矩阵表示，用double做限制
		 * @throws QException 规格不符，类型转换失败
		 */
		public Matrix sumMatWithDouble(Matrix m) throws QException{
			List<ArrayList<String>> la = new ArrayList<ArrayList<String>>();
			if(!(this.xAxis == m.xAxis && this.yAxis == m.yAxis)){throw new QException("矩阵的xy必需相同");}
			for(int i = 0;i < xAxis;i++){
				ArrayList<String> temp = new ArrayList<String>();
				for(int j = 0;j < yAxis;j++){
					double value = 0.0;
					try{
						value = Double.parseDouble(this.l.get(i).get(j) )  + m.getDataToDouble(i, j);
					}catch(QException err){
						throw err;
					}catch(NumberFormatException e){
						throw new QException("本矩阵"+ this.l.get(i).get(j) + "不能转换成Double");
					}
					temp.add(String.valueOf(value));
				}
				la.add(temp);
			}
			return new Matrix(la);
		}
		
		/**
		 * 对m按（bx，by）到（ex，ey）进行切割后与自身相加
		 * @param m 切割的矩阵
		 * @param bx 开始的x
		 * @param by 开始的y
		 * @param ex 结束的x
		 * @param ey 结束的y
		 * @return 以矩阵表示，用double做限制
		 * @throws QException 规格不符，类型转换失败
		 */
		public Matrix sumMatWithDouble(Matrix m,int bx,int by,int ex,int ey) 
				throws QException{
			Matrix m1 = m.getDataToMatrix(bx, by, ex, ey);
			return sumMatWithDouble(m1);
		}
		
//////////////////////////////////////////////////////////////////////////////求积
		
		/**
		 * 索引x行求积，把x行从b到e的数据求积，用int保存结果并返回
		 * @param x 行数
		 * @param b 开始
		 * @param e 结束
		 * @return 结果
		 * @throws QException b大于e，越界，不能转型
		 */
		
		public int mulXToInt(int x,int b,int e) throws QException{
			int sum = 1;
			if(b > e){throw new QException("b 不能大于 e");}
			if(x < 0 || x >= xAxis){throw new QException("x越界");}
			else{
				ArrayList<String> a = this.l.get(x);
				if(b < 0 || e >= a.size()){ throw new QException(b + "->" + e + "越界");}
				for(int i=b;i <= e;i++){
					
					try{
						sum *= Integer.parseInt(a.get(i));
					}catch(NumberFormatException err){
						throw new QException(i + "--不能转化成Integer");
					}
					
				}
			}
			return sum;
			
		}
		
		
		/**
		 * 索引x行求积，把x行从b到e的数据求积，用int保存结果并返回
		 * @param x 行数
		 * @return 结果
		 * @throws QException b大于e，越界，不能转型
		 */
		public int mulXToInt(int x) throws QException{
			
			if(x < 0 || x >= xAxis){throw new QException("x越界");}
			else{
				int length = this.l.get(x).size();
				return this.mulXToInt(x, 0, length-1);
			}

		}
		
		/**
		 * 索引x行求积，把x行从b到e的数据求积，用float保存结果并返回
		 * @param x 行数
		 * @param b 开始
		 * @param e 结束
		 * @return 结果
		 * @throws QException b大于e，越界，不能转型
		 */
		
		public float mulXToFloat(int x,int b,int e) throws QException{
			float sum = 1.0f;
			if(b > e){throw new QException("b 不能大于 e");}
			if(x < 0 || x >= xAxis){throw new QException("x越界");}
			else{
				ArrayList<String> a = this.l.get(x);
				if(b < 0 || e >= a.size()){ throw new QException(b + "->" + e + "越界");}
				for(int i=b;i <= e;i++){
					
					try{
						sum *= Float.parseFloat(a.get(i));
					}catch(NumberFormatException err){
						throw new QException(i + "--不能转化成Float");
					}
					
				}
			}
			return sum;
			
		}
		
		
		/**
		 * 索引x行求积，把x行从b到e的数据求积，用float保存结果并返回
		 * @param x 行数
		 * @return 结果
		 * @throws QException b大于e，越界，不能转型
		 */
		public float mulXToFloat(int x) throws QException{
			
			if(x < 0 || x >= xAxis){throw new QException("x越界");}
			else{
				int length = this.l.get(x).size();
				return this.mulXToFloat(x, 0, length-1);
			}

		}
		
		/**
		 * 索引x行求积，把x行从b到e的数据求积，用long保存结果并返回
		 * @param x 行数
		 * @param b 开始
		 * @param e 结束
		 * @return 结果
		 * @throws QException b大于e，越界，不能转型
		 */
		
		public long mulXToLong(int x,int b,int e) throws QException{
			long sum = 1l;
			if(b > e){throw new QException("b 不能大于 e");}
			if(x < 0 || x >= xAxis){throw new QException("x越界");}
			else{
				ArrayList<String> a = this.l.get(x);
				if(b < 0 || e >= a.size()){ throw new QException(b + "->" + e + "越界");}
				for(int i=b;i <= e;i++){
					
					try{
						sum *= Long.parseLong(a.get(i));
					}catch(NumberFormatException err){
						throw new QException(i + "--不能转化成Long");
					}
					
				}
			}
			return sum;
			
		}
		
		
		/**
		 * 索引x行求积，把x行从b到e的数据求积，用long保存结果并返回
		 * @param x 行数
		 * @return 结果
		 * @throws QException b大于e，越界，不能转型
		 */
		public long mulXToLong(int x) throws QException{
			
			if(x < 0 || x >= xAxis){throw new QException("x越界");}
			else{
				int length = this.l.get(x).size();
				return this.mulXToLong(x, 0, length-1);
			}

		}
		
		/**
		 * 索引x行求积，把x行从b到e的数据求积，用double保存结果并返回
		 * @param x 行数
		 * @param b 开始
		 * @param e 结束
		 * @return 结果
		 * @throws QException b大于e，越界，不能转型
		 */
		
		public double mulXToDouble(int x,int b,int e) throws QException{
			double sum = 1.0;
			if(b > e){throw new QException("b 不能大于 e");}
			if(x < 0 || x >= xAxis){throw new QException("x越界");}
			else{
				ArrayList<String> a = this.l.get(x);
				if(b < 0 || e >= a.size()){ throw new QException(b + "->" + e + "越界");}
				for(int i=b;i <= e;i++){
					
					try{
						sum *= Double.parseDouble(a.get(i));
					}catch(NumberFormatException err){
						throw new QException(i + "--不能转化成Double");
					}
					
				}
			}
			return sum;
			
		}
		
		
		/**
		 * 索引x行求积，把x行从b到e的数据求积，用double保存结果并返回
		 * @param x 行数
		 * @return 结果
		 * @throws QException b大于e，越界，不能转型
		 */
		public double mulXToDouble(int x) throws QException{
			
			if(x < 0 || x >= xAxis){throw new QException("x越界");}
			else{
				int length = this.l.get(x).size();
				return this.mulXToDouble(x, 0, length-1);
			}

		}
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		/**
		 * 自身实现b行到e行求积，用int保存，并把数据保存为矩阵
		 * 返回一行分别表示各列之积累
		 * @param b 开始
		 * @param e 结束
		 * @return 矩阵，返回一行代表每一列的积
		 * @throws QException b大于e，越界，转换失败
		 */
		public Matrix mulXMatWithInt(int b,int e) throws QException{
			if(b > e){throw new QException("b 不能大于 e");}
			if(b<0||e>=this.xAxis){throw new QException("下标越界");}
			List<ArrayList<String>> la = new ArrayList<ArrayList<String>>();
			
			ArrayList<Integer> temp = new ArrayList<Integer>();
			
			for(int i = 0;i<yAxis;i++){
				temp.add(0);
			}
			
			int maxSize = 0;
			for(int i = b;i<=e;i++){
				ArrayList<String> a = this.l.get(i);
				for(int j = 0;j<a.size();j++){
					try{
						temp.set(j,Integer.parseInt(a.get(j))*temp.get(j));
					}catch(NumberFormatException err){
						throw new QException(a.get(j) + " 类型错误");
					}
				}
				
				if(a.size()>maxSize){
					maxSize = a.size();
				}
			}
			
			ArrayList<String> temp2 = new ArrayList<String>();
			
			for(int i = 0;i< maxSize;i++){
				temp2.add(String.valueOf(temp.get(i) ) );
			}
			
			la.add(temp2);
			return new Matrix(la);
			
		}
		
		
		/**
		 * 自身实现每行求积，用int保存，并把数据保存为矩阵
		 * 返回一行分别表示各列之积累
		 * @return 矩阵，返回一行代表每一列的积
		 * @throws QException b大于e，越界，转换失败
		 */
		public Matrix mulXMatWithInt() throws QException{
			return mulXMatWithInt( 0, this.xAxis-1);	
		}
		
		
		/**
		 * 自身实现b行到e行求积，用long保存，并把数据保存为矩阵
		 * 返回一行分别表示各列之积累
		 * @param b 开始
		 * @param e 结束
		 * @return 矩阵，返回一行代表每一列的积
		 *@throws QException b大于e，越界，转换失败
		 */
		public Matrix mulXMatWithLong(int b,int e) throws QException{
			
			if(b > e){throw new QException("b 不能大于 e");}
			if(b<0||e>=this.xAxis){throw new QException("下标越界");}
			List<ArrayList<String>> la = new ArrayList<ArrayList<String>>();
			
			ArrayList<Long> temp = new ArrayList<Long>();
			
			for(int i = 0;i<yAxis;i++){
				temp.add(0l);
			}
			
			int maxSize = 0;
			for(int i = b;i<=e;i++){
				ArrayList<String> a = this.l.get(i);
				for(int j = 0;j<a.size();j++){
					try{
						temp.set(j,Long.parseLong(a.get(j))*temp.get(j));
					}catch(NumberFormatException err){
						throw new QException(a.get(j) + " 类型错误");
					}
				}
				
				if(a.size()>maxSize){
					maxSize = a.size();
				}
			}
			
			ArrayList<String> temp2 = new ArrayList<String>();
			
			for(int i = 0;i< maxSize;i++){
				temp2.add(String.valueOf(temp.get(i) ) );
			}
			
			la.add(temp2);
			return new Matrix(la);
			
		}
		
		
		/**
		 * 自身实现每行求积，用long保存，并把数据保存为矩阵
		 * 返回一行分别表示各列之积累
		 * @return 矩阵，返回一行代表每一列的积
		 * @throws QException b大于e，越界，转换失败
		 */
		public Matrix mulXMatWithLong() throws QException{
			return mulXMatWithLong(0, this.xAxis-1);	
		}
		
		
		
		/**
		 * 自身实现b行到e行求积，用float保存，并把数据保存为矩阵
		 * 返回一行分别表示各列之积累
		 * @param b 开始
		 * @param e 结束
		 * @return 矩阵，返回一行代表每一列的积
		 * @throws QException b大于e，越界，转换失败
		 */
		public Matrix mulXMatWithFloat(int b,int e) throws QException{
			if(b > e){throw new QException("b 不能大于 e");}
			if(b<0||e>=this.xAxis){throw new QException("下标越界");}
			List<ArrayList<String>> la = new ArrayList<ArrayList<String>>();
			
			ArrayList<Float> temp = new ArrayList<Float>();
			
			for(int i = 0;i<yAxis;i++){
				temp.add(0.0f);
			}
			
			int maxSize = 0;
			for(int i = b;i<=e;i++){
				ArrayList<String> a = this.l.get(i);
				for(int j = 0;j<a.size();j++){
					try{
						temp.set(j,Float.parseFloat(a.get(j))*temp.get(j));
					}catch(NumberFormatException err){
						throw new QException(a.get(j) + " 类型错误");
					}
				}
				
				if(a.size()>maxSize){
					maxSize = a.size();
				}
			}
			
			ArrayList<String> temp2 = new ArrayList<String>();
			
			for(int i = 0;i< maxSize;i++){
				temp2.add(String.valueOf(temp.get(i) ) );
			}
			
			la.add(temp2);
			return new Matrix(la);
			
		}
		
		
		/**
		 * 自身实现每行求积，用long保存，并把数据保存为矩阵
		 * 返回一行分别表示各列之积累
		 * @return 矩阵，返回一行代表每一列的积
		 * @throws QException b大于e，越界，转换失败
		 */
		public Matrix mulXMatWithFloat() throws QException{
			return mulXMatWithFloat(0, this.xAxis-1);	
		}
		
		
		/**
		 * 自身实现b行到e行求积，用double保存，并把数据保存为矩阵
		 * 返回一行分别表示各列之积累
		 * @param b 开始
		 * @param e 结束
		 * @return 矩阵，返回一行代表每一列的积
		 * @throws QException b大于e，越界，转换失败
		 */
		public Matrix mulXMatWithDouble(int b,int e) throws QException{
			if(b > e){throw new QException("b 不能大于 e");}
			if(b<0||e>=this.xAxis){throw new QException("下标越界");}
			List<ArrayList<String>> la = new ArrayList<ArrayList<String>>();
			
			ArrayList<Double> temp = new ArrayList<Double>();
			
			for(int i = 0;i<yAxis;i++){
				temp.add(0.0);
			}
			
			int maxSize = 0;
			for(int i = b;i<=e;i++){
				ArrayList<String> a = this.l.get(i);
				for(int j = 0;j<a.size();j++){
					try{
						temp.set(j,Double.parseDouble(a.get(j))*temp.get(j));
					}catch(NumberFormatException err){
						throw new QException(a.get(j) + " 类型错误");
					}
				}
				
				if(a.size()>maxSize){
					maxSize = a.size();
				}
			}
			
			ArrayList<String> temp2 = new ArrayList<String>();
			
			for(int i = 0;i< maxSize;i++){
				temp2.add(String.valueOf(temp.get(i) ) );
			}
			
			la.add(temp2);
			return new Matrix(la);
			
		}
		
		
		/**
		 * 自身实现每行求积，用long保存，并把数据保存为矩阵
		 * 返回一行分别表示各列之积累
		 * @return 矩阵，返回一行代表每一列的积
		 * @throws QException b大于e，越界，转换失败
		 */
		public Matrix mulXMatWithDouble() throws QException{
			return mulXMatWithDouble( 0, this.xAxis-1);	
		}
		
		/**
		 * 自身实现b列到e列求积，用int保存，并把数据保存为矩阵
		 * 返回一行分别表示各列之积累
		 * @param b 开始
		 * @param e 结束
		 * @return 矩阵，返回一列代表每一行的积
		 * @throws QException b大于e，越界，转换失败
		 */
		public Matrix mulYMatWithInt(int b,int e) throws QException{
			if(b > e){throw new QException("b 不能大于 e");}
			if(b < 0){throw new QException("b 不能少于0");}
			List<ArrayList<String>> la = new ArrayList<ArrayList<String>>();
			
			
			
			for(int i = 0;i <this.xAxis;i++){
				
				ArrayList<String> temp = new ArrayList<String>();
				ArrayList<String> a = this.l.get(i);
				if(e<a.size()){throw new QException("e取值错误");}
				int sum = 0;
				for(int j = b; j <= e;j++){
					int value = 0;
					try{
						value = Integer.parseInt(a.get(j));
					}catch(NumberFormatException err){
						throw new QException(a.get(j) + " 不能转换成Integer形");
					}
					sum *= value;
				}
				temp.add(String.valueOf(sum));
				la.add(temp);
			}
			
			return new Matrix(la);
		}
		
		
		/**
		 * 自身实现各列求积，用int保存，并把数据保存为矩阵
		 * 返回一行分别表示各列之积累

		 * @return 矩阵，返回一列代表每一行的积
		 * @throws QException b大于e，转换失败
		 */
		public Matrix mulYMatWithInt() throws QException{
			return this.mulYMatWithInt( 0, this.yAxis-1);
		}
		
		
		/**
		 * 自身实现b列到e列求积，用long保存，并把数据保存为矩阵
		 * 返回一行分别表示各列之积累
		 * @param b 开始
		 * @param e 结束
		 * @return 矩阵，返回一列代表每一行的积
		 * @throws QException b大于e，越界，转换失败
		 */
		public Matrix mulYMatWithLong(int b,int e) throws QException{
			if(b > e){throw new QException("b 不能大于 e");}
			if(b < 0){throw new QException("b 不能少于0");}
			List<ArrayList<String>> la = new ArrayList<ArrayList<String>>();
			
			
			
			for(int i = 0;i <this.xAxis;i++){
				
				ArrayList<String> temp = new ArrayList<String>();
				ArrayList<String> a = this.l.get(i);
				if(e>=a.size()){throw new QException("e取值错误");}
				long sum = 0l;
				for(int j = b; j <= e;j++){
					long value = 0;
					try{
						value = Long.parseLong(a.get(j));
					}catch(NumberFormatException err){
						throw new QException(a.get(j) + " 不能转换成Long");
					}
					sum *= value;
				}
				temp.add(String.valueOf(sum));
				la.add(temp);
			}
			
			return new Matrix(la);
		}
		
		
		/**
		 * 自身实现各列求积，用long保存，并把数据保存为矩阵
		 * 返回一行分别表示各列之积累

		 * @return 矩阵，返回一列代表每一行的积
		 * @throws QException b大于e，转换失败
		 */
		public Matrix mulYMatWithLong() throws QException{
			return this.mulYMatWithLong( 0, this.yAxis-1);
		}
		
		
		/**
		 * 自身实现b列到e列求积，用float保存，并把数据保存为矩阵
		 * 返回一行分别表示各列之积累
		 * @param b 开始
		 * @param e 结束
		 * @return 矩阵，返回一列代表每一行的积
		 * @throws QException b大于e，越界，转换失败
		 */
		
		public Matrix mulYMatWithFloat(int b,int e) throws QException{
			if(b > e){throw new QException("b 不能大于 e");}
			if(b < 0){throw new QException("b 不能少于0");}
			List<ArrayList<String>> la = new ArrayList<ArrayList<String>>();
			
			
			
			for(int i = 0;i <this.xAxis;i++){
				
				ArrayList<String> temp = new ArrayList<String>();
				ArrayList<String> a = this.l.get(i);
				if(e>=a.size()){throw new QException("e取值错误");}
				float sum = 0.0f;
				for(int j = b; j <= e;j++){
					float value = 0;
					try{
						value = Float.parseFloat(a.get(j));
					}catch(NumberFormatException err){
						throw new QException(a.get(j) + " 不能转换成Float");
					}
					sum *= value;
				}
				temp.add(String.valueOf(sum));
				la.add(temp);
			}
			
			return new Matrix(la);
		}
		
		
		/**
		 * 自身实现各列求积，用float保存
		 * 返回一行分别表示各列之积累

		 * @return 矩阵，返回一列代表每一行的积
		 * @throws QException b大于e，转换失败
		 */
		public Matrix mulYMatWithFloat() throws QException{
			return this.mulYMatWithFloat( 0, this.yAxis-1);
		}
		
		/**
		 * 自身实现b列到e列求积，用double保存
		 * 返回一行分别表示各列之积累
		 * @param b 开始
		 * @param e 结束
		 * @return 矩阵，返回一列代表每一行的积
		 * @throws QException b大于e，越界，转换失败
		 */
		
		public Matrix mulYMatWithDouble(int b,int e) throws QException{
			if(b > e){throw new QException("b 不能大于 e");}
			if(b < 0){throw new QException("b 不能少于0");}
			List<ArrayList<String>> la = new ArrayList<ArrayList<String>>();
			
			
			
			for(int i = 0;i <this.xAxis;i++){
				
				ArrayList<String> temp = new ArrayList<String>();
				ArrayList<String> a = this.l.get(i);
				if(e>=a.size()){throw new QException("e取值错误");}
				double sum = 0.0;
				for(int j = b; j <= e;j++){
					double value = 0;
					try{
						value = Double.parseDouble(a.get(j));
					}catch(NumberFormatException err){
						throw new QException(a.get(j) + " 不能转换成Double");
					}
					sum *= value;
				}
				temp.add(String.valueOf(sum));
				la.add(temp);
			}
			
			return new Matrix(la);
		}
		
		
		/**
		 * 自身实现各列求积，用double保存
		 * 返回一行分别表示各列之积累

		 * @return 矩阵，返回一列代表每一行的积
		 * @throws QException b大于e，转换失败
		 */
		public Matrix mulYMatWithDouble() throws QException{
			return this.mulYMatWithDouble(0, this.yAxis-1);
		}
		
		
		
		
		
		/**
		 * 规格相同的矩阵与自身相加
		 * @param m 需要相加的矩阵
		 * @return 以矩阵表示，用int做限制
		 * @throws QException 规格不符，类型转换失败
		 */
		public Matrix mulMatWithInt(Matrix m) throws QException{
			List<ArrayList<String>> la = new ArrayList<ArrayList<String>>();
			if(!(this.xAxis == m.xAxis && this.yAxis == m.yAxis)){throw new QException("矩阵的xy必需相同");}
			for(int i = 0;i < xAxis;i++){
				ArrayList<String> temp = new ArrayList<String>();
				for(int j = 0;j < yAxis;j++){
					int value = 0;
					try{
						value = Integer.parseInt(this.l.get(i).get(j) )  *  m.getDataToInt(i, j);
					}catch(QException err){
						throw err;
					}catch(NumberFormatException e){
						throw new QException("本矩阵"+ this.l.get(i).get(j) + "不能转换成Integer");
					}
					temp.add(String.valueOf(value));
				}
				la.add(temp);
			}
			return new Matrix(la);
		}
		
		/**
		 * 对m按（bx，by）到（ex，ey）进行切割后与自身相乘
		 * @param m 切割的矩阵
		 * @param bx 开始的x
		 * @param by 开始的y
		 * @param ex 结束的x
		 * @param ey 结束的y
		 * @return 以矩阵表示，用int做限制
		 * @throws QException 规格不符，类型转换失败
		 */
		public Matrix mulMatWithInt(Matrix m,int bx,int by,int ex,int ey) 
				throws QException{
			Matrix m1 = m.getDataToMatrix(bx, by, ex, ey);
			return mulMatWithInt(m1);
		}
		
		
		/**
		 * 规格相同的矩阵与自身相加
		 * @param m 需要相加的矩阵
		 * @return 以矩阵表示，用long做限制
		 * @throws QException 规格不符，类型转换失败
		 */
		public Matrix mulMatWithLong(Matrix m) throws QException{
			List<ArrayList<String>> la = new ArrayList<ArrayList<String>>();
			if(!(this.xAxis == m.xAxis && this.yAxis == m.yAxis)){throw new QException("矩阵的xy必需相同");}
			for(int i = 0;i < xAxis;i++){
				ArrayList<String> temp = new ArrayList<String>();
				for(int j = 0;j < yAxis;j++){
					long value = 0;
					try{
						value = Long.parseLong(this.l.get(i).get(j) )  * m.getDataToLong(i, j);
					}catch(QException err){
						throw err;
					}catch(NumberFormatException e){
						throw new QException("本矩阵"+ this.l.get(i).get(j) + "不能转换成Long");
					}
					temp.add(String.valueOf(value));
				}
				la.add(temp);
			}
			return new Matrix(la);
		}
		
		/**
		 * 对m按（bx，by）到（ex，ey）进行切割后与自身相乘
		 * @param m 切割的矩阵
		 * @param bx 开始的x
		 * @param by 开始的y
		 * @param ex 结束的x
		 * @param ey 结束的y
		 * @return 以矩阵表示，用long做限制
		 * @throws QException 规格不符，类型转换失败
		 */
		public Matrix mulMatWithLong(Matrix m,int bx,int by,int ex,int ey) 
				throws QException{
			Matrix m1 = m.getDataToMatrix(bx, by, ex, ey);
			return mulMatWithLong(m1);
		}
		
		
		/**
		 * 规格相同的矩阵与自身相加
		 * @param m 需要相加的矩阵
		 * @return 以矩阵表示，用float做限制
		 * @throws QException 规格不符，类型转换失败
		 */
		public Matrix mulMatWithFloat(Matrix m) throws QException{
			List<ArrayList<String>> la = new ArrayList<ArrayList<String>>();
			if(!(this.xAxis == m.xAxis && this.yAxis == m.yAxis)){throw new QException("矩阵的xy必需相同");}
			for(int i = 0;i < xAxis;i++){
				ArrayList<String> temp = new ArrayList<String>();
				for(int j = 0;j < yAxis;j++){
					float value = 0.0f;
					try{
						value = Float.parseFloat(this.l.get(i).get(j) )  * m.getDataToFloat(i, j);
					}catch(QException err){
						throw err;
					}catch(NumberFormatException e){
						throw new QException("本矩阵"+ this.l.get(i).get(j) + "不能转换成Float");
					}
					temp.add(String.valueOf(value));
				}
				la.add(temp);
			}
			return new Matrix(la);
		}
		
		/**
		 * 对m按（bx，by）到（ex，ey）进行切割后与自身相乘
		 * @param m 切割的矩阵
		 * @param bx 开始的x
		 * @param by 开始的y
		 * @param ex 结束的x
		 * @param ey 结束的y
		 * @return 以矩阵表示，用float做限制
		 * @throws QException 规格不符，类型转换失败
		 */
		public Matrix mulMatWithFloat(Matrix m,int bx,int by,int ex,int ey) 
				throws QException{
			Matrix m1 = m.getDataToMatrix(bx, by, ex, ey);
			return mulMatWithFloat(m1);
		}
		
		
		
		/**
		 * 规格相同的矩阵与自身相加
		 * @param m 需要相加的矩阵
		 * @return 以矩阵表示，用double做限制
		 * @throws QException 规格不符，类型转换失败
		 */
		public Matrix mulMatWithDouble(Matrix m) throws QException{
			List<ArrayList<String>> la = new ArrayList<ArrayList<String>>();
			if(!(this.xAxis == m.xAxis && this.yAxis == m.yAxis)){throw new QException("矩阵的xy必需相同");}
			for(int i = 0;i < xAxis;i++){
				ArrayList<String> temp = new ArrayList<String>();
				for(int j = 0;j < yAxis;j++){
					double value = 0.0;
					try{
						value = Double.parseDouble(this.l.get(i).get(j) )  * m.getDataToDouble(i, j);
					}catch(QException err){
						throw err;
					}catch(NumberFormatException e){
						throw new QException("本矩阵"+ this.l.get(i).get(j) + "不能转换成Double");
					}
					temp.add(String.valueOf(value));
				}
				la.add(temp);
			}
			return new Matrix(la);
		}
		
		/**
		 * 对m按（bx，by）到（ex，ey）进行切割后与自身相乘
		 * @param m 切割的矩阵
		 * @param bx 开始的x
		 * @param by 开始的y
		 * @param ex 结束的x
		 * @param ey 结束的y
		 * @return 以矩阵表示，用double做限制
		 * @throws QException 规格不符，类型转换失败
		 */
		public Matrix mulMatWithDouble(Matrix m,int bx,int by,int ex,int ey) 
				throws QException{
			Matrix m1 = m.getDataToMatrix(bx, by, ex, ey);
			return mulMatWithDouble(m1);
		}
		
////////////////////////////////////////////////////////////////////////////////////////////////////////求平均值		
		/**
		 * 索引x行，把x行从b到e的数据求平均，用Integer保存结果并返回
		 * @param x 行数
		 * @param b 开始
		 * @param e 结束
		 * @return 结果
		 * @throws QException b大于e，越界，不能转型
		 */
		public int averXToInt(int x,int b,int e) throws QException{
			int number = e-b+1;
			int sum = this.sumXToInt(x, b, e);
			return sum / number;
		}
		/**
		 * 索引x行求平均，用Integer保存结果并返回
		 * @param x 行数
		 * @return 结果
		 * @throws QException b大于e，越界，不能转型
		 */
		public int averXToInt(int x) throws QException{
			int length = this.l.get(x).size();
			return averXToInt(x, 0, length-1);
		}
		
		/**
		 * 索引x行，把x行从b到e的数据求平均，用long保存结果并返回
		 * @param x 行数
		 * @param b 开始
		 * @param e 结束
		 * @return 结果
		 * @throws QException b大于e，越界，不能转型
		 */
		public long averXToLong(int x,int b,int e) throws QException{
			long number = e-b+1;
			long sum = this.sumXToLong(x, b, e);
			return sum / number;
		}
		/**
		 * 索引x行求平均，用long保存结果并返回
		 * @param x 行数
		 * @return 结果
		 * @throws QException b大于e，越界，不能转型
		 */
		public long averXToLong(int x) throws QException{
			int length = this.l.get(x).size();
			return averXToLong(x, 0,length-1);
		}
		
		/**
		 * 索引x行，把x行从b到e的数据求平均，用float保存结果并返回
		 * @param x 行数
		 * @param b 开始
		 * @param e 结束
		 * @return 结果
		 * @throws QException b大于e，越界，不能转型
		 */
		public float averXToFloat(int x,int b,int e) throws QException{
			int number = e-b+1;
			float sum = this.sumXToLong(x, b, e);
			return sum / number;
		}
		/**
		 * 索引x行求平均，用float保存结果并返回
		 * @param x 行数
		 * @return 结果
		 * @throws QException b大于e，越界，不能转型
		 */
		public float averXToFloat(int x) throws QException{
			int length = this.l.get(x).size();
			return averXToLong(x, 0, length-1);
		}
		
		/**
		 * 索引x行，把x行从b到e的数据求平均，用double保存结果并返回
		 * @param x 行数
		 * @param b 开始
		 * @param e 结束
		 * @return 结果
		 * @throws QException b大于e，越界，不能转型
		 */
		public double averXToDouble(int x,int b,int e) throws QException{
			long number = e-b+1;
			double sum = this.sumXToDouble(x, b, e);
			return sum / number;
		}
		/**
		 * 索引x行求平均，用double保存结果并返回
		 * @param x 行数
		 * @return 结果
		 * @throws QException b大于e，越界，不能转型
		 */
		public double averXToDouble(int x) throws QException{
			int length = this.l.get(x).size();
			return averXToDouble(x, 0, length-1);
		}
		
		
		
		/**
		 * 索引y列，从b到e的数据求平均值，用int保存结果并返回
		 * @param y 列数
		 * @param b 开始
		 * @param e 结束
		 * @return 结果
		 * @throws QException b大于e，越界，不能转型
		 */
		public int averYToInt(int y,int b,int e) throws QException{
			int number = e-b+1;
			int sum = this.sumYToInt(y, b, e);
			return sum / number;
		}
		/**
		 * 索引y列，数据求平均值，用int保存结果并返回
		 * @param y 列数
		 * @return 结果
		 * @throws QException 越界，不能转型
		 */
		public int averYToInt(int y) throws QException{
			return averYToInt(y, 0, xAxis-1);
		}
		
		/**
		 * 索引y列，从b到e的数据求平均值，用long保存结果并返回
		 * @param y 列数
		 * @param b 开始
		 * @param e 结束
		 * @return 结果
		 * @throws QException b大于e，越界，不能转型
		 */
		public long averYToLong(int y,int b,int e) throws QException{
			long number = e-b+1;
			long sum = this.sumYToLong(y, b, e);
			return sum / number;
		}
		/**
		 * 索引y列，数据求平均值，用long保存结果并返回
		 * @param y 列数
		 * @return 结果
		 * @throws QException 越界，不能转型
		 */
		public long averYToLong(int y) throws QException{
			return averYToLong(y, 0, xAxis-1);
		}
		
		/**
		 * 索引y列，从b到e的数据求平均值，用float保存结果并返回
		 * @param y 列数
		 * @param b 开始
		 * @param e 结束
		 * @return 结果
		 * @throws QException b大于e，越界，不能转型
		 */
		public float averYToFloat(int y,int b,int e) throws QException{
			int number = e-b+1;
			float sum = this.sumYToLong(y, b, e);
			return sum / number;
		}
		/**
		 * 索引y列，数据求平均值，用float保存结果并返回
		 * @param y 列数
		 * @return 结果
		 * @throws QException 越界，不能转型
		 */
		public float averYToFloat(int y) throws QException{
			return averYToLong(y, 0, xAxis-1);
		}
		
		/**
		 * 索引y列，从b到e的数据求平均值，用double保存结果并返回
		 * @param y 列数
		 * @param b 开始
		 * @param e 结束
		 * @return 结果
		 * @throws QException b大于e，越界，不能转型
		 */
		public double averYToDouble(int y,int b,int e) throws QException{
			long number = e-b+1;
			double sum = this.sumYToDouble(y, b, e);
			return sum / number;
		}
		/**
		 * 索引y列，数据求平均值，用double保存结果并返回
		 * @param y 列数
		 * @return 结果
		 * @throws QException 越界，不能转型
		 */
		public double averYToDouble(int y) throws QException{
			return averYToDouble(y, 0, xAxis-1);
		}
		
//////////////////////////////////////////////////////////////////////////////////////////////矩阵求平均值
		/**
		 * 对实现b行到e行求平均值，用int保存，并将数据组成矩阵
		 * 
		 * @param b 开始
		 * @param e 结束
		 * @return 返回一行分别表示各列平均值
		 * @throws QException b大于e，越界，类型错误
		 */
		public Matrix averXMatWithInt(int b,int e) throws QException{
			int number = e-b+1;
			Matrix mat = this.sumXMatWithInt( b, e);
			
			for(int i = 0;i<mat.getYAxis();i++){
				int value = mat.getDataToInt(0, i);
				value = value / number;
				String data = String.valueOf(value);
				mat.setData(0, i, data);
			}
			
			return mat;		
		}
		
		/**
		 * 对实现对每行求平均值，用int保存，并将数据组成矩阵

		 * @return 返回一行分别表示各列平均值
		 * @throws QException 越界，类型错误
		 */
		public Matrix averXMatWithInt() throws QException{
			return this.averXMatWithInt( 0, xAxis-1);
		}
		
		
		
		/**
		 * 对实现b行到e行求平均值，用long保存，并将数据组成矩阵
		 * 
		 * @param b 开始
		 * @param e 结束
		 * @return 返回一行分别表示各列平均值
		 * @throws QException b大于e，越界，类型错误
		 */
		public Matrix averXMatWithLong(int b,int e) throws QException{
			int number = e-b+1;
			Matrix mat = this.sumXMatWithLong(b, e);
			
			for(int i = 0;i<mat.getYAxis();i++){
				long value = mat.getDataToLong(0, i);
				value = value / number;
				String data = String.valueOf(value);
				mat.setData(0, i, data);
			}
			
			return mat;		
		}
		
		/**
		 * 对实现对每行求平均值，用long保存，并将数据组成矩阵

		 * @return 返回一行分别表示各列平均值
		 * @throws QException 越界，类型错误
		 */
		public Matrix averXMatWithLong() throws QException{
			return this.averXMatWithLong( 0, xAxis-1);
		}
		
		
		/**
		 * 对实行b行到e行求平均值，用float保存，并将数据组成矩阵
		 * 
		 * @param b 开始
		 * @param e 结束
		 * @return 返回一行分别表示各列平均值
		 * @throws QException b大于e，越界，类型错误
		 */
		public Matrix averXMatWithFloat(int b,int e) throws QException{
			int number = e-b+1;
			Matrix mat = this.sumXMatWithFloat( b, e);
			
			for(int i = 0;i<mat.getYAxis();i++){
				float value = mat.getDataToFloat(0, i);
				value = value / number;
				String data = String.valueOf(value);
				mat.setData(0, i, data);
			}
			
			return mat;		
		}
		
		/**
		 * 对实现对每行求平均值，用float保存，并将数据组成矩阵

		 * @return 返回一行分别表示各列平均值
		 * @throws QException 越界，类型错误
		 */
		public Matrix averXMatWithFloat() throws QException{
			return this.averXMatWithFloat( 0, xAxis-1);
		}
		
		
		/**
		 * 对实现b行到e行求平均值，用double保存，并将数据组成矩阵
		 * 
		 * @param b 开始
		 * @param e 结束
		 * @return 返回一行分别表示各列平均值
		 * @throws QException b大于e，越界，类型错误
		 */
		public Matrix averXMatWithDouble(int b,int e) throws QException{
			int number = e-b+1;
			Matrix mat = this.sumXMatWithDouble( b, e);
			
			for(int i = 0;i<mat.getYAxis();i++){
				double value = mat.getDataToDouble(0, i);
				value = value / number;
				String data = String.valueOf(value);
				mat.setData(0, i, data);
			}
			
			return mat;		
		}
		
		/**
		 * 对实现对每行求平均值，用double保存，并将数据组成矩阵

		 * @return 返回一行分别表示各列平均值
		 * @throws QException ，越界，类型错误
		 */
		public Matrix averXMatWithDouble() throws QException{
			return this.averXMatWithDouble( 0, xAxis-1);
		}
		
		
		
		/**
		 * 对实现b列到e列求平均值，用int保存，并将数据组成矩阵
		 * 
		 * @param b 开始
		 * @param e 结束
		 * @return 返回一列分别表示各行平均值
		 * @throws QException b大于e，越界，类型错误
		 */
		public Matrix averYMatWithInt(int b,int e) throws QException{
			int number = e-b+1;
			Matrix mat = this.sumYMatWithInt(b, e);
			
			for(int i = 0;i<mat.getXAxis();i++){
				int value = mat.getDataToInt(i, 0);
				value = value / number;
				String data = String.valueOf(value);
				mat.setData(i, 0, data);
			}
			
			return mat;		
		}
		
		/**
		 * 对实现对每列求平均值，用int保存，并将数据组成矩阵
		 * 

		 * @return 返回一列分别表示各行平均值
		 * @throws QException 越界，类型错误
		 */
		public Matrix averYMatWithInt() throws QException{
			return this.averYMatWithInt(0, yAxis-1);
		}
		
		
		
		/**
		 * 对实现b列到e列求平均值，用long保存，并将数据组成矩阵
		 * 
		 * @param b 开始
		 * @param e 结束
		 * @return 返回一列分别表示各行平均值
		 * @throws QException b大于e，越界，类型错误
		 */
		public Matrix averYMatWithLong(int b,int e) throws QException{
			int number = e-b+1;
			Matrix mat = this.sumYMatWithLong( b, e);
			
			for(int i = 0;i<mat.getXAxis();i++){
				long value = mat.getDataToLong(i, 0);
				value = value / number;
				String data = String.valueOf(value);
				mat.setData( i,0, data);
			}
			
			return mat;		
		}
		
		/**
		 * 对实现对每列求平均值，用long保存，并将数据组成矩阵

		 * @return 返回一列分别表示各行平均值
		 * @throws QException 越界，类型错误
		 */
		public Matrix averYMatWithLong() throws QException{
			return this.averYMatWithLong( 0, yAxis-1);
		}
		
		
		/**
		 * 对实现b列到e列求平均值，用float保存，并将数据组成矩阵
		 * 
		 * @param b 开始
		 * @param e 结束
		 * @return 返回一列分别表示各行平均值
		 * @throws QException b大于e，越界，类型错误
		 */
		public Matrix averYMatWithFloat(int b,int e) throws QException{
			int number = e-b+1;
			Matrix mat = this.sumYMatWithFloat( b, e);
			
			for(int i = 0;i<mat.getXAxis();i++){
				float value = mat.getDataToFloat(i, 0);
				value = value / number;
				String data = String.valueOf(value);
				mat.setData(i, 0, data);
			}
			
			return mat;		
		}
		
		/**
		 * 对实现对每列求平均值，用float保存，并将数据组成矩阵

		 * @return 返回一列分别表示各行平均值
		 * @throws QException 越界，类型错误
		 */
		public Matrix averYMatWithFloat() throws QException{
			return this.averYMatWithFloat(0, yAxis-1);
		}
		
		
		/**
		 * 对实现b列到e列求平均值，用double保存，并将数据组成矩阵
		 * 
		 * @param b 开始
		 * @param e 结束
		 * @return 返回一列分别表示各行平均值
		 * @throws QException b大于e，越界，类型错误
		 */
		public Matrix averYMatWithDouble(int b,int e) throws QException{
			int number = e-b+1;
			Matrix mat = this.sumYMatWithDouble( b, e);
			
			for(int i = 0;i<mat.getXAxis();i++){
				double value = mat.getDataToDouble(i, 0);
				value = value / number;
				String data = String.valueOf(value);
				mat.setData(i, 0, data);
			}
			
			return mat;		
		}
		
		/**
		 * 对实现对每列求平均值，用double保存，并将数据组成矩阵

		 * @return 返回一列分别表示各行平均值
		 * @throws QException 越界，类型错误
		 */
		public Matrix averYMatWithDouble() throws QException{
			return this.averYMatWithDouble(0, yAxis-1);
		}
		
		/**
		 * 对矩阵中的每一个元素进行平方运算，返回的Matrix中的数据仍是String
		 * 但计算过程中以Double为介质，因此以后获取数据的时候只能用float和double类型获取
		 * @param pow 指数
		 * @return 运算结果
		 * @throws QException 转换类型失败
		 */
		public Matrix powMatrixWithDouble(double pow) throws QException{
			List<ArrayList<String>> la = new ArrayList<ArrayList<String>>();
			for(int i = 0;i < xAxis;i++){
				ArrayList<String> temp = new ArrayList<String>();
				for(int j = 0;j < yAxis;j++){
					double value = 0.0;
					value = Math.pow(this.getDataToDouble(i, j), pow);
					temp.add(String.valueOf(value));
				}
				la.add(temp);
			}
			return new Matrix(la);
		}
		
		/**
		 * 实现矩阵每位元素自增，以int做限制
		 * @throws QException 转换类型失败
		 */
		public void INCMatrixToInt()throws QException{
			for(int i = 0;i < xAxis;i++){
				for(int j = 0;j < this.l.get(i).size();j++){
					int value = this.getDataToInt(i, j) + 1;
					String data = String.valueOf(value);
					this.setData(i, j, data);
				}
			}
		}

		/**
		 * 实现矩阵每位元素自增，以long做限制
		 * @throws QException 转换类型失败
		 */ 
		public void INCMatrixToLong()throws QException{
			for(int i = 0;i < xAxis;i++){
				for(int j = 0;j < this.l.get(i).size();j++){
					long value = this.getDataToLong(i, j) + 1;
					String data = String.valueOf(value);
					this.setData(i, j, data);
				}
			}
		}
		
		/**
		 * 实现矩阵每位元素自减，以int做限制
		 * @throws QException 转换类型失败
		 */
		public void DECMatrixToInt()throws QException{
			for(int i = 0;i < xAxis;i++){
				for(int j = 0;j < this.l.get(i).size();j++){
					int value = this.getDataToInt(i, j) - 1;
					String data = String.valueOf(value);
					this.setData(i, j, data);
				}
			}
		}
		
		
		
		/**
		 * 实现矩阵每位元素自减，以long做限制
		 * @throws QException 转换类型失败
		 */
		public void DECMatrixToLong()throws QException{
			for(int i = 0;i < xAxis;i++){
				for(int j = 0;j < this.l.get(i).size();j++){
					long value = this.getDataToLong(i, j) - 1;
					String data = String.valueOf(value);
					this.setData(i, j, data);
				}
			}
		}
		
		/**
		 * 在每一行的y列中，如符合key的值，则分成一个类别，最后返回这个类别组成的矩阵，
		 * 若小于y，则不去考虑
		 * @param y 对第y列进行分类
		 * @param key 分类的指定值
		 * @return y的值符合key的子矩阵
		 * @throws QException y小于0
		 */
		public Matrix classfiy(int y,String key) throws QException{
			if(y < 0){throw new QException("y小于0");}
			List<ArrayList<String>> la = new ArrayList<ArrayList<String>>();
			
			for(int i = 0;i<xAxis;i++){
				ArrayList<String> a = this.l.get(i);
				if(y <a.size()){
					if(a.get(y).equals(key)){la.add(a);}
				}	
			}
			
			return new Matrix(la);
		}
		
		
		
		@Override
		/**获取带[[1,2,3]]形式的字符串
		 * @return 返回带[[1,2,3]]形式的字符串
		 */
		public String toString(){
			String str = "[";
			for(int i = 0; i < this.xAxis;i++){
				str+="[";
				ArrayList <String> d = l.get(i);
				for(int j =0;j<d.size();j++){
					str += d.get(j);
					if (j != d.size()-1){
						str +=",";
					}else{
						str +="]，";
					}
				}
				if (i != this.xAxis-1){
					str += System.getProperty("line.separator","\r\n");
				}
			}
			str += "]";
			return str;
		}
		
		
		
	}
	
	
	
	/*
	 * 以下QCSV类
	 * QCSV可以进行File类的文件的处理
	 * 要想处理数据需要获取矩阵进行处理
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 */
	private String path;
	private int line;
	private Matrix mat;

	
	private QCSV(){super("");};
	/**
	 * 根据指定路径读取CSV构造QCSV对象
	 * @param path 文件路径
	 * @throws QException 由IOException转换而来
	 */
	public QCSV(String path) throws QException{
		
		super(path);
		this.path = path;
		try{
			FileReader fr = new FileReader(path);
			BufferedReader br = new BufferedReader(fr);
			String str = ""; this.line = 0;
			List<ArrayList<String> > li = new ArrayList<ArrayList<String> >();
			while((str=br.readLine() ) != null){
				
				this.line++;
				str = str.replace("，", ",");
				String[] content = str.split(",");
				ArrayList<String> list = new ArrayList<String>();;
				for(int i =0;i<content.length;i++){
					list.add(content[i]);
				}
				li.add(list);
			} 
			this.mat = new Matrix(li);
			br.close();
		}catch(IOException err){
			throw new QException(err.getMessage(),err.getCause());
		}
		

	
		
		
	}
	
	/**
	 * 根据File对象读取CSV构造QCSV对象
	 * @param fileName File对象
	 * @throws QException 由IOException转换而来
	 */
	public QCSV(File fileName)throws QException{
		this(fileName.getPath());
	}
	
	
	/**
	 * 返回行数
	 * @return 行数
	 */
	public int getLine(){return this.line;}
	
	/**
	 * 获取数据的内容以字符串的形式返回
	 * @return 文章内容
	 */
	public String getContent(){return this.mat.getAllDataToStr();}
	
	/**
	 * 获取矩阵
	 * @return 可操纵的矩阵
	 */
	public Matrix getMat(){return this.mat;}
	
	/**
	 * 从外部构建Matrix;
	 * @param list 二维列表
	 * @return 用二维列表构建的矩阵
	 * @throws QException 构建过程中抛出的异常
	 */
	public static Matrix buildMat(List<ArrayList<String> > list)
			throws QException{
			return new QCSV("").new Matrix(list);
	}
	
	/**
	 * 根据二维List重写
	 * @param list 二维列表
	 * @throws QException 有IOException转换而来
	 */
	public void writeCSV(List<ArrayList<String>> list ) throws QException{
		this.writeCSV(new Matrix(list));
	}
	
	/**
	 * 根据矩阵m重写
	 * @param m 需要写入的矩阵
	 * @throws QException 有IOException转换而来
	 */
	public void writeCSV(Matrix m) throws QException{
		this.mat = m;
		this.line = m.getXAxis();
		String content = m.getAllDataToStr();
		try{
			FileWriter fw = new FileWriter(this.path);
			fw.write(content);
			fw.flush();
			fw.close();
		}catch(IOException err){
			throw new QException(err.getMessage());
		}
	}
}
