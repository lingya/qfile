package qfile;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;



/**
 * ����CSV��׺���ļ�����,������һ��������Ҫ��<code>getMat()</code>��ȡ���������ܽ����������
 * @author �ʷ���
 * @version 2016-2-4
 */
public class QCSV extends File{

	private static final long serialVersionUID = 1L;
	
	
	/**
	 * �����࣬��װ�˻������ԺͶ����ݵĻ������㷽��
	 * @author �ʷʵ�����
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
		 * ��ȡ����
		 * @return ����
		 */
		public int getXAxis(){
			return this.xAxis;
		}
		
		/**
		 * ��ȡ��������
		 * @return ����
		 */
		public int getYAxis(){
			return this.yAxis;
		}
		
		/**
		 * �����������ݵ��ַ�����ʽ
		 * @return ���ݵ��ַ�����ʽ
		 */
		public String getAllDataToStr(){
			return this.toString().replace("[", "").replace("]", "");
		}
		
		/*
		 * �������ݵĽӿڣ�ֻ��һ��
		 */
		/**
		 * ��������
		 * @param x ��
		 * @param y ��
		 * @param data ��Ҫ�޸ĵ�����
		 * @throws QException Խ�紥���쳣
		 */
		public void setData(int x,int y,String data) throws QException{
			if (x <0||x>=this.xAxis) {throw new QException("x����0��Խ��");}
			if (y <0||y>=this.l.get(x).size()){throw new QException("y����0��Խ��");}
			this.l.get(x).set(y, data);
		}
		
		
		/*
		 * ��ȡ���ݵĽӿ�
		 * �ɻ�ȡ���� integer long float double String boolean
		 * Ҳ���Ի�ȡ�Ӿ���
		 */
		
		/**
		 * ��ȡString����
		 * @param x ��
		 * @param y ��
		 * @return ����
		 * @throws QException ����Խ��
		 */
		public String getDataToStr(int x,int y)
			throws QException{
			
			if(x >=this.xAxis){
				throw new QException("x����Խ��");
			}else{
				ArrayList<String> a = this.l.get(x);
				if(y >= a.size()){throw new QException("y����Խ��");}
				else{return a.get(y);}
			}
		}
		/**
		 * ��ȡinteger����
		 * @param x ��
		 * @param y ��
		 * @return ����
		 * @throws QException ����Խ�磬ת��ʧ��
		 */
		public int getDataToInt(int x,int y)
				throws QException{
				
				if(x >=this.xAxis){
					throw new QException("x����Խ��");
				}else{
					ArrayList<String> a = this.l.get(x);
					if(y >= a.size()){throw new QException("y����Խ��");}
					else{
						try{
							return Integer.parseInt(a.get(y) );
						}catch(NumberFormatException err){
							throw new QException("�����ݲ���ת����Interger");
						}
						
					}
				}
		}
		/**
		 * ��ȡlong����
		 * @param x ��
		 * @param y ��
		 * @return ����
		 * @throws QException ����Խ�磬ת��ʧ��
		 */
		
		public long getDataToLong(int x,int y)
				throws QException{
				
				if(x >=this.xAxis){
					throw new QException("x����Խ��");
				}else{
					ArrayList<String> a = this.l.get(x);
					if(y >= a.size()){throw new QException("y����Խ��");}
					else{
						try{
							return Long.parseLong(a.get(y) );
						}catch(NumberFormatException err){
							throw new QException("�����ݲ���ת����Long");
						}
						
					}
				}
		}
		
		/**
		 * ��ȡfloat����
		 * @param x ��
		 * @param y ��
		 * @return ����
		 * @throws QException ����Խ�磬ת��ʧ��
		 */
		
		public float getDataToFloat(int x,int y)
				throws QException{
				
				if(x >=this.xAxis){
					throw new QException("x����Խ��");
				}else{
					ArrayList<String> a = this.l.get(x);
					if(y >= a.size()){throw new QException("y����Խ��");}
					else{
						try{
							return Float.parseFloat(a.get(y) );
						}catch(NumberFormatException err){
							throw new QException("�����ݲ���ת����Float");
						}
						
					}
				}
		}
		
		/**
		 * ��ȡdouble����
		 * @param x ��
		 * @param y ��
		 * @return ����
		 * @throws QException ����Խ�磬ת��ʧ��
		 */
		
		public double getDataToDouble(int x,int y)
				throws QException{
				
				if(x >=this.xAxis){
					throw new QException("x����Խ��");
				}else{
					ArrayList<String> a = this.l.get(x);
					if(y >= a.size()){throw new QException("y����Խ��");}
					else{
						try{
							return Double.parseDouble(a.get(y) );
						}catch(NumberFormatException err){
							throw new QException("�����ݲ���ת����Double");
						}
						
					}
				}
		}
		
		/**
		 * ��ȡboolean����
		 * @param x ��
		 * @param y ��
		 * @return ����
		 * @throws QException ����Խ�磬ת��ʧ��
		 */
		
		public boolean getDataToBoolean(int x,int y)
				throws QException{
				
				if(x >=this.xAxis){
					throw new QException("x����Խ��");
				}else{
					ArrayList<String> a = this.l.get(x);
					if(y >= a.size()){throw new QException("y����Խ��");}
					else{
						try{
							return Boolean.parseBoolean(a.get(y) );
						}catch(NumberFormatException err){
							throw new QException("�����ݲ���ת����Boolean");
						}
					}
				}
		}
		
		/**
		 * ���֣�bx��by������ex��ey����С����
		 * @param bx ��ʼ����
		 * @param by ��ʼ����
		 * @param ex ��������
		 * @param ey ��������
		 * @return ����
		 * @throws QException Խ��
		 */
		
		public Matrix getDataToMatrix(int bx,int by,int ex,int ey)
			throws QException{
			if(bx < 0||ex >= this.xAxis){throw new QException("xԽ��" + bx + "->" + ex);}
			else{
				List<ArrayList<String>> a = new ArrayList<ArrayList<String>>();
				for(int i = bx;i <= ex; i++){
					ArrayList<String> list = this.l.get(i);
					if(by < 0||ey >= list.size()){throw new QException("yԽ��" + by + "->" + ey);}
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
		 * ��͵Ľӿ�
		 * ������ͣ�����b��e
		 * ������ͣ�����b��e
		 * ������ˣ�����b��e
		 * ������ˣ�����b��e
		 * ������������ Integer float double long 
		 */
		/**
		 * ����x�У���x�д�b��e��������ͣ���Integer������������
		 * @param x ����
		 * @param b ��ʼ
		 * @param e ����
		 * @return ���
		 * @throws QException b����e��Խ�磬����ת��
		 */
		
		public int sumXToInt(int x,int b,int e) throws QException{
			int sum = 0;
			if(b > e){throw new QException("b ���ܴ��� e");}
			if(x < 0 || x >= xAxis){throw new QException("x����0��Խ��");}
			else{
				ArrayList<String> a = this.l.get(x);
				if(b < 0 || e >= a.size()){ throw new QException(b+"����0��"+e+"����"+a.size());}
				for(int i=b;i <= e;i++){
					
					try{
						sum += Integer.parseInt(a.get(i));
					}catch(NumberFormatException err){
						throw new QException(a.get(i)+ "--����ת����Integer");
					}
					
				}
			}
			return sum;
			
		}
		
		
		/**
		 * ����x����ͣ���Integer������������
		 * @param x ����
		 * @return ���
		 * @throws QException b����e��Խ�磬����ת��
		 */
		public int sumXToInt(int x) throws QException{
			
			if(x < 0 || x >= xAxis){throw new QException("x����0��Խ��");}
			else{
				int length = this.l.get(x).size();
				return this.sumXToInt(x, 0, length-1);
			}

		}
			
		
		/**
		 * ����x����ͣ���x�д�b��e��������ͣ���long������������
		 * @param x ����
		 * @param b ��ʼ
		 * @param e ����
		 * @return ���
		 * @throws QException b����e��Խ�磬����ת��
		 */
		
		public long sumXToLong(int x,int b,int e) throws QException{
			long sum = 0;
			if(b > e){throw new QException("b ���ܴ��� e");}
			if(x < 0 || x >= xAxis){throw new QException("x����0��Խ��");}
			else{
				ArrayList<String> a = this.l.get(x);
				if(b < 0 || e >= a.size()){ throw new QException(b+"����0��"+e+"����"+a.size());}
				for(int i=b;i < e;i++){
					
					try{
						sum += Long.parseLong(a.get(i));
					}catch(NumberFormatException err){
						throw new QException(a.get(i) + "--����ת����Long");
					}
					
				}
			}
			return sum;
			
		}
		
		
		/**
		 * ����x����ͣ���x�д�b��e��������ͣ���long������������
		 * @param x ����
		 * @return ���
		 * @throws QException b����e��Խ�磬����ת��
		 */
		public long sumXToLong(int x) throws QException{
			
			if(x < 0 || x >= xAxis){throw new QException("x����0��Խ��");}
			else{
				int length = this.l.get(x).size();
				return this.sumXToLong(x, 0, length-1);
			}

		}
		
		
		
		/**
		 * ����x����ͣ���x�д�b��e��������ͣ���float������������
		 * @param x ����
		 * @param b ��ʼ
		 * @param e ����
		 * @return ���
		 * @throws QException b����e��Խ�磬����ת��
		 */
		
		public float sumXToFloat(int x,int b,int e) throws QException{
			float sum = 0.0f;
			if(b > e){throw new QException("b ���ܴ��� e");}
			if(x < 0 || x >= xAxis){throw new QException("x����0��Խ��");}
			else{
				ArrayList<String> a = this.l.get(x);
				if(b < 0 || e >= a.size()){ throw new QException(b+"����0��"+e+"����"+a.size() );}
				for(int i=0;i < a.size();i++){
					
					try{
						sum += Float.parseFloat(a.get(i));
					}catch(NumberFormatException err){
						throw new QException(a.get(i) + "--����ת����Float");
					}
					
				}
			}
			return sum;
			
		}
		
		
		/**
		 * ����x����ͣ���x�д�b��e��������ͣ���float������������
		 * @param x ����
		 * @return ���
		 * @throws QException b����e��Խ�磬����ת��
		 */
		public float sumXToFloat(int x) throws QException{
			
			if(x < 0 || x >= xAxis){throw new QException("x����0��Խ��");}
			else{
				int length = this.l.get(x).size();
				return this.sumXToFloat(x, 0, length-1);
			}

		}
		
		
		/**
		 * ����x����ͣ���x�д�b��e��������ͣ���double������������
		 * @param x ����
		 * @param b ��ʼ
		 * @param e ����
		 * @return ���
		 * @throws QException b����e��Խ�磬����ת��
		 */
		public double sumXToDouble(int x,int b,int e) throws QException{
			double sum = 0.0;
			if(b > e){throw new QException("b ���ܴ��� e");}
			if(x < 0 || x >= xAxis){throw new QException("x����0��Խ��");}
			else{
				ArrayList<String> a = this.l.get(x);
				if(b < 0 || e >= a.size()){ throw new QException(b+"����0��"+e+"����"+a.size() );}
				for(int i=0;i < a.size();i++){
					
					try{
						sum += Double.parseDouble(a.get(i));
					}catch(NumberFormatException err){
						throw new QException(a.get(i) + "--����ת����Dpublt");
					}
					
				}
			}
			return sum;
			
		}
		
		
		/**
		 * ����x����ͣ���x�д�b��e��������ͣ���double������������
		 * @param x ����
		 * @return ���
		 * @throws QException b����e��Խ�磬����ת��
		 */
		public double sumXToDouble(int x) throws QException{
			
			if(x < 0 || x >= xAxis){throw new QException("x����0��Խ��");}
			else{
				int length = this.l.get(x).size();
				return this.sumXToDouble(x, 0, length-1);
			}

		}
		
		/**
		 * ����y����ͣ���b��e��������ͣ���int������������
		 * @param y ����
		 * @param b ��ʼ
		 * @param e ����
		 * @return ���
		 * @throws QException b����e��Խ�磬����ת��
		 */
		public int sumYToInt(int y,int b,int e) throws QException{
			if(b > e){throw new QException("b ���ܴ��� e");}
			int length = this.xAxis;
			int sum = 0;
			if(b < 0 || e >= length){ throw new QException(b+"����0��"+e+"����"+length );}
			for(int i = b;i<=e;i++){
				ArrayList<String> a = this.l.get(i);
				if(a.size()>=y){
					sum += Integer.parseInt(a.get(y));
				}
			}
			return sum;
		}
		
		/**
		 * ����y����ͣ���������ͣ���integer������������
		 * @param y ����
		 * @return ���
		 * @throws QException Խ�磬����ת��
		 */
		public int sumYToInt(int y) throws QException{
			return sumYToInt(y,0,this.xAxis-1);
		}
		
		
		
		/**
		 * ����y�У���b��e��������ͣ���long������������
		 * @param y ����
		 * @param b ��ʼ
		 * @param e ����
		 * @return ���
		 * @throws QException b����e��Խ�磬����ת��
		 */
		public long sumYToLong(int y,int b,int e) throws QException{
			if(b > e){throw new QException("b ���ܴ��� e");}
			int length = this.xAxis;
			long sum = 0l;
			if(b < 0 || e >= length){ throw new QException(b+"����0��"+e+"����"+length );}
			for(int i = b;i<=e;i++){
				ArrayList<String> a = this.l.get(i);
				if(a.size()>=y){
					sum += Long.parseLong(a.get(y));
				}
			}
			return sum;
		}
		
		/**
		 * ����y�У�������ͣ���long������������
		 * @param y ����
		 * @return ���
		 * @throws QException Խ�磬����ת��
		 */
		public long sumYToLong(int y) throws QException{
			return sumYToLong(y,0,this.xAxis-1);
		}
		
		/**
		 * ����y�У���b��e��������ͣ���float������������
		 * @param y ����
		 * @param b ��ʼ
		 * @param e ����
		 * @return ���
		 * @throws QException b����e��Խ�磬����ת��
		 */
		public float sumYToFloat(int y,int b,int e) throws QException{
			if(b > e){throw new QException("b ���ܴ��� e");}
			int length = this.xAxis;
			float sum = 0.0f;
			if(b < 0 || e >= length){ throw new QException(b+"����0��"+e+"����"+length );}
			for(int i = b;i<=e;i++){
				ArrayList<String> a = this.l.get(i);
				if(a.size()>=y){
					sum += Float.parseFloat(a.get(y));
				}
			}
			return sum;
		}
		
		/**
		 * ����y�У�������ͣ���float������������
		 * @param y ����
		 * @return ���
		 * @throws QException Խ�磬����ת��
		 */
		public float sumYToFlaot(int y) throws QException{
			return sumYToFloat(y,0,this.xAxis-1);
		}
		
		
		/**
		 * ����y�У���b��e��������ͣ���double������������
		 * @param y ����
		 * @param b ��ʼ
		 * @param e ����
		 * @return ���
		 * @throws QException b����e��Խ�磬����ת��
		 */
		public double sumYToDouble(int y,int b,int e) throws QException{
			if(b > e){throw new QException("b ���ܴ��� e");}
			int length = this.xAxis;
			double sum = 0.0;
			if(b < 0 || e >= length){ throw new QException(b+"����0��"+e+"����"+length );}
			for(int i = b;i<=e;i++){
				ArrayList<String> a = this.l.get(i);
				if(a.size()>=y){
					sum += Double.parseDouble(a.get(y));
				}
			}
			return sum;
		}
		
		/**
		 * ����y�У�������ͣ���double������������
		 * @param y ����
		 * @return ���
		 * @throws QException Խ�磬����ת��
		 */
		public double sumYToDouble(int y) throws QException{
			return sumYToDouble(y,0,this.xAxis-1);
		}

////////////////////////���
		/**
		 * ����b��e�еĵ�y�������������int������������
		 * @param y ����
		 * @param b ��ʼ
		 * @param e ����
		 * @return ���
		 * @throws QException b����e��Խ�磬����ת��
		 */
		public int mulYToInt(int y,int b,int e) throws QException{
			if(b > e){throw new QException("b ���ܴ��� e");}
			int length = this.xAxis;
			int sum = 1;
			if(b < 0 || e >= length){ throw new QException(b+"����0��"+e+"����"+length );}
			for(int i = b;i<=e;i++){
				ArrayList<String> a = this.l.get(i);
				if(a.size()>=y){
					sum *= Double.parseDouble(a.get(y));
				}
			}
			return sum;
		}
		
		/**
		 * ����b��e�еĵ�y�������������int������������
		 * @param y ����
		 * @return ���
		 * @throws QException Խ�磬����ת��
		 */
		public int mulYToInt(int y) throws QException{
			return mulYToInt(y,0,this.xAxis-1);
		}
		
		
		/**
		 * ����b��e�еĵ�y�������������long������������
		 * @param y ����
		 * @param b ��ʼ
		 * @param e ����
		 * @return ���
		 * @throws QException b����e��Խ�磬����ת��
		 */
		public long mulYToLong(int y,int b,int e) throws QException{
			if(b > e){throw new QException("b ���ܴ��� e");}
			int length = this.xAxis;
			long sum = 1l;
			if(b < 0 || e >= length){ throw new QException(b+"����0��"+e+"����"+length );}
			for(int i = b;i<=e;i++){
				ArrayList<String> a = this.l.get(i);
				if(a.size()>=y){
					sum *= Double.parseDouble(a.get(y));
				}
			}
			return sum;
		}
		
		/**
		 * ����b��e�еĵ�y�������������long������������
		 * @param y ����
		 * @return ���
		 * @throws QException Խ�磬����ת��
		 */
		public long mulYToLong(int y) throws QException{
			return mulYToLong(y,0,this.xAxis-1);
		}
		
		
		/**
		 * ����b��e�еĵ�y�������������float������������
		 * @param y ����
		 * @param b ��ʼ
		 * @param e ����
		 * @return ���
		 * @throws QException b����e��Խ�磬����ת��
		 */
		public float mulYToFloat(int y,int b,int e) throws QException{
			if(b > e){throw new QException("b ���ܴ��� e");}
			int length = this.xAxis;
			float sum = 1.0f;
			if(b < 0 || e >= length){ throw new QException(b+"����0��"+e+"����"+length );}
			for(int i = b;i<=e;i++){
				ArrayList<String> a = this.l.get(i);
				if(a.size()>=y){
					sum *= Double.parseDouble(a.get(y));
				}
			}
			return sum;
		}
		
		/**
		 * ����b��e�еĵ�y�������������float������������
		 * @param y ����
		 * @return ���
		 * @throws QException Խ�磬����ת��
		 */
		public float mulYToFloat(int y) throws QException{
			return mulYToFloat(y,0,this.xAxis-1);
		}
		
		
		
		/**
		 * ����b��e�еĵ�y�������������double������������
		 * @param y ����
		 * @param b ��ʼ
		 * @param e ����
		 * @return ���
		 * @throws QException b����e��Խ�磬����ת��
		 */
		public double mulYToDouble(int y,int b,int e) throws QException{
			if(b > e){throw new QException("b ���ܴ��� e");}
			int length = this.xAxis;
			double sum = 1.0;
			if(b < 0 || e >= length){ throw new QException(b+"����0��"+e+"����"+length );}
			for(int i = b;i<=e;i++){
				ArrayList<String> a = this.l.get(i);
				if(a.size()>=y){
					sum *= Double.parseDouble(a.get(y));
				}
			}
			return sum;
		}
		
		/**
		 * ����b��e�еĵ�y�������������double������������
		 * @param y ����

		 * @return ���
		 * @throws QException Խ�磬����ת��
		 */
		public double mulYToDouble(int y) throws QException{
			return mulYToDouble(y,0,this.xAxis-1);
		}
		
		
		
		
		/*
		 * ��������
		 * ֧������ͣ������
		 * ֧������ˣ������
		 * �����Ӿ����Ӿ�������ݴ����Ĭ�����ַ�������ʽ��
		 * �������м���̿���Integer Float Double Long��Ϊ��ʱֵ
		 * 
		 */
		
	
////////////////////////////////////////////////////////////////�������		
		/**
		 * ��ʵ��b�е�e����� ����int���棬����������ɾ���
		 * 
		 * @param b ��ʼ
		 * @param e ����
		 * @return ����һ�зֱ��ʾ����֮��
		 * @throws QException b����e��Խ�磬���ʹ���
		 */
		public Matrix sumXMatWithInt(int b,int e) throws QException{
			if(b > e){throw new QException("b ���ܴ��� e");}
			if(b<0||e>=this.xAxis){throw new QException(b+"����0��"+e+"����"+this.xAxis);}
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
						throw new QException(a.get(j) + " ����ת��Integer");
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
		 * ����Ϊ��λ��ͣ���int���棬����������ɾ���
		 * @return ����һ�зֱ��ʾ����֮��
		 * @throws QException Խ�磬���ʹ���
		 */
		
		public Matrix sumXMatWithInt() throws QException{
			return sumXMatWithInt( 0, this.xAxis-1);	
		}
		
		
		/**
		 * ��ʵ��b�е�e����ͣ���long���棬����������ɾ���
		 * 
		 * @param b ��ʼ
		 * @param e ����
		 * @return ����һ�зֱ��ʾ����֮��
		 * @throws QException b����e��Խ�磬���ʹ���
		 */
		public Matrix sumXMatWithLong(int b,int e) throws QException{
			
			if(b > e){throw new QException("b ���ܴ��� e");}
			if(b<0||e>=this.xAxis){throw new QException(b+"����0��"+e+"����"+this.xAxis);}
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
						throw new QException(a.get(j) + "����ת����Long");
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
		 * ����Ϊ��λ��ͣ���long���棬����������ɾ���
		 * @return ����һ�зֱ��ʾ����֮��
		 * @throws QException Խ�磬���ʹ���
		 */
		
		public Matrix sumXMatWithLong() throws QException{
			return sumXMatWithLong( 0, this.xAxis-1);	
		}
		
		
		
		/**
		 * ��ʵ��b�е�e����ͣ���float���棬����������ɾ���
		 * 
		 * @param b ��ʼ
		 * @param e ����
		 * @return ����һ�зֱ��ʾ����֮��
		 * @throws QException b����e��Խ�磬���ʹ���
		 */
		public Matrix sumXMatWithFloat(int b,int e) throws QException{
			if(b > e){throw new QException("b ���ܴ��� e");}
			if(b<0||e>=this.xAxis){throw new QException(b+"����0��"+e+"����"+this.xAxis);}
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
						throw new QException(a.get(j) + "����ת��float");
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
		 * ����Ϊ��λ��ͣ���float���棬����������ɾ���
		 * @return ����һ�зֱ��ʾ����֮��
		 * @throws QException Խ�磬���ʹ���
		 */
		public Matrix sumXMatWithFloat() throws QException{
			return sumXMatWithFloat( 0, this.xAxis-1);	
		}
		
		
		/**
		 * ��ʵ��b�е�e����ͣ���double���棬����������ɾ���
		 * 
		 * @param b ��ʼ
		 * @param e ����
		 * @return ����һ�зֱ��ʾ����֮��
		 * @throws QException b����e��Խ�磬���ʹ���
		 */
		public Matrix sumXMatWithDouble(int b,int e) throws QException{
			if(b > e){throw new QException("b ���ܴ��� e");}
			if(b<0||e>=this.xAxis){throw new QException(b+"����0��"+e+"����"+this.xAxis);}
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
						throw new QException(a.get(j) + "����ת��Ϊdouble");
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
		 * ����Ϊ��λ��ͣ���double���棬����������ɾ���
		 * @return ����һ�зֱ��ʾ����֮��
		 * @throws QException Խ�磬���ʹ���
		 */
		
		public Matrix sumXMatWithDouble() throws QException{
			return sumXMatWithDouble( 0, this.xAxis-1);	
		}
		
		/**
		 * ��ʵ��b�е�e����ͣ���int���棬����������ɾ���
		 * 
		 * @param b ��ʼ
		 * @param e ����
		 * @return ����һ�зֱ��ʾ����֮��
		 * @throws QException b����e��Խ�磬���ʹ���
		 */
		
		public Matrix sumYMatWithInt(int b,int e) throws QException{
			if(b > e){throw new QException("b ���ܴ��� e");}
			if(b < 0){throw new QException("b ����С��0");}
			List<ArrayList<String>> la = new ArrayList<ArrayList<String>>();
			
			
			
			for(int i = 0;i <this.xAxis;i++){
				
				ArrayList<String> temp = new ArrayList<String>();
				ArrayList<String> a = this.l.get(i);
				if(e>=a.size()){throw new QException("eԽ��");}
				int sum = 0;
				for(int j = b; j <= e;j++){
					int value = 0;
					try{
						value = Integer.parseInt(a.get(j));
					}catch(NumberFormatException err){
						throw new QException(a.get(j) + " ����ת����Integer");
					}
					sum += value;
				}
				temp.add(String.valueOf(sum));
				la.add(temp);
			}
			
			return new Matrix(la);
		}
		
		
		/**
		 * ����Ϊ��λ��ͣ���int���棬����������ɾ���
		 * @return ����һ�зֱ��ʾ����֮��
		 * @throws QException Խ�磬���ʹ���
		 */
		public Matrix sumYMatWithInt() throws QException{
			return this.sumYMatWithInt(0, this.yAxis-1);
		}
		
		
		/**
		 * ��ʵ��b�е�e����ͣ���long���棬����������ɾ���
		 * 
		 * @param b ��ʼ
		 * @param e ����
		 * @return ����һ�зֱ��ʾ����֮��
		 * @throws QException b����e��Խ�磬���ʹ���
		 */
		
		public Matrix sumYMatWithLong(int b,int e) throws QException{
			if(b > e){throw new QException("b ���ܴ��� e");}
			if(b < 0){throw new QException("b ����С��0");}
			List<ArrayList<String>> la = new ArrayList<ArrayList<String>>();
			
			
			
			for(int i = 0;i <this.xAxis;i++){
				
				ArrayList<String> temp = new ArrayList<String>();
				ArrayList<String> a = this.l.get(i);
				if(e>=a.size()){throw new QException("eԽ��");}
				long sum = 0l;
				for(int j = b; j <= e;j++){
					long value = 0;
					try{
						value = Long.parseLong(a.get(j));
					}catch(NumberFormatException err){
						throw new QException(a.get(j) + " ����ת����Long");
					}
					sum += value;
				}
				temp.add(String.valueOf(sum));
				la.add(temp);
			}
			
			return new Matrix(la);
		}
		
		
		/**
		 * ����Ϊ��λ��ͣ���long���棬����������ɾ���
		 * @return ����һ�зֱ��ʾ����֮��
		 * @throws QException Խ�磬���ʹ���
		 */
		public Matrix sumYMatWithLong() throws QException{
			return this.sumYMatWithLong(0, this.yAxis-1);
		}
		
		
		/**
		 * ��ʵ��b�е�e����ͣ���float���棬����������ɾ���
		 * 
		 * @param b ��ʼ
		 * @param e ����
		 * @return ����һ�зֱ��ʾ����֮��
		 * @throws QException b����e��Խ�磬���ʹ���
		 */
		public Matrix sumYMatWithFloat(int b,int e) throws QException{
			if(b > e){throw new QException("b ���ܴ��� e");}
			if(b < 0){throw new QException("b ����С��0");}
			List<ArrayList<String>> la = new ArrayList<ArrayList<String>>();
			
			
			
			for(int i = 0;i <this.xAxis;i++){
				
				ArrayList<String> temp = new ArrayList<String>();
				ArrayList<String> a = this.l.get(i);
				if(e>=a.size()){throw new QException("eԽ��");}
				float sum = 0.0f;
				for(int j = b; j <= e;j++){
					float value = 0;
					try{
						value = Float.parseFloat(a.get(j));
					}catch(NumberFormatException err){
						throw new QException(a.get(j) + " ����ת����Float");
					}
					sum += value;
				}
				temp.add(String.valueOf(sum));
				la.add(temp);
			}
			
			return new Matrix(la);
		}
		
		
		/**
		 * ����Ϊ��λ��ͣ���float���棬����������ɾ���
		 * @return ����һ�зֱ��ʾ����֮��
		 * @throws QException Խ�磬���ʹ���
		 */
		public Matrix sumYMatWithFloat() throws QException{
			return this.sumYMatWithFloat(0, this.yAxis-1);
		}
		
		/**
		 * ��ʵ��b�е�e����ͣ���double���棬����������ɾ���
		 * 
		 * @param b ��ʼ
		 * @param e ����
		 * @return ����һ�зֱ��ʾ����֮��
		 * @throws QException b����e��Խ�磬���ʹ���
		 */
		public Matrix sumYMatWithDouble(int b,int e) throws QException{
			if(b > e){throw new QException("b ���ܴ��� e");}
			if(b < 0){throw new QException("b ����С��0");}
			List<ArrayList<String>> la = new ArrayList<ArrayList<String>>();
			
			
			
			for(int i = 0;i <this.xAxis;i++){
				
				ArrayList<String> temp = new ArrayList<String>();
				ArrayList<String> a = this.l.get(i);
				if(e>=a.size()){throw new QException("eԽ��");}
				double sum = 0.0;
				for(int j = b; j <= e;j++){
					double value = 0;
					try{
						value = Double.parseDouble(a.get(j));
					}catch(NumberFormatException err){
						throw new QException(a.get(j) + " ����ת����Double");
					}
					sum += value;
				}
				temp.add(String.valueOf(sum));
				la.add(temp);
			}
			
			return new Matrix(la);
		}
		
		
		/**
		 * ����Ϊ��λ��ͣ���double���棬����������ɾ���
		 * @return ����һ�зֱ��ʾ����֮��
		 * @throws QException Խ�磬���ʹ���
		 */
		public Matrix sumYMatWithDouble() throws QException{
			return this.sumYMatWithDouble( 0, this.yAxis-1);
		}
		
		
		
		
		/**
		 * �����ͬ�ľ������������
		 * @param m ��Ҫ��ӵľ���
		 * @return �Ծ����ʾ����int������
		 * @throws QException ��񲻷�������ת��ʧ��
		 */
		public Matrix sumMatWithInt(Matrix m) throws QException{
			List<ArrayList<String>> la = new ArrayList<ArrayList<String>>();
			if(!(this.xAxis == m.xAxis && this.yAxis == m.yAxis)){throw new QException("�����xy������ͬ");}
			for(int i = 0;i < xAxis;i++){
				ArrayList<String> temp = new ArrayList<String>();
				for(int j = 0;j < yAxis;j++){
					int value = 0;
					try{
						value = Integer.parseInt(this.l.get(i).get(j) )  + m.getDataToInt(i, j);
					}catch(QException err){
						throw err;
					}catch(NumberFormatException e){
						throw new QException("������"+ this.l.get(i).get(j) + "����ת����Integer");
					}
					temp.add(String.valueOf(value));
				}
				la.add(temp);
			}
			return new Matrix(la);
		}
		
		/**
		 * ��m����bx��by������ex��ey�������и�����������
		 * @param m �и�ľ���
		 * @param bx ��ʼ��x
		 * @param by ��ʼ��y
		 * @param ex ������x
		 * @param ey ������y
		 * @return �Ծ����ʾ����int������
		 * @throws QException ��񲻷�������ת��ʧ��
		 */
		public Matrix sumMatWithInt(Matrix m,int bx,int by,int ex,int ey) 
				throws QException{
			Matrix m1 = m.getDataToMatrix(bx, by, ex, ey);
			return sumMatWithInt(m1);
		}
		
		
		/**
		 * �����ͬ�ľ������������
		 * @param m ��Ҫ��ӵľ���
		 * @return �Ծ����ʾ����long������
		 * @throws QException ��񲻷�������ת��ʧ��
		 */
		public Matrix sumMatWithLong(Matrix m) throws QException{
			List<ArrayList<String>> la = new ArrayList<ArrayList<String>>();
			if(!(this.xAxis == m.xAxis && this.yAxis == m.yAxis)){throw new QException("�����xy������ͬ");}
			for(int i = 0;i < xAxis;i++){
				ArrayList<String> temp = new ArrayList<String>();
				for(int j = 0;j < yAxis;j++){
					long value = 0;
					try{
						value = Long.parseLong(this.l.get(i).get(j) )  + m.getDataToLong(i, j);
					}catch(QException err){
						throw err;
					}catch(NumberFormatException e){
						throw new QException("������"+ this.l.get(i).get(j) + "����ת����Long");
					}
					temp.add(String.valueOf(value));
				}
				la.add(temp);
			}
			return new Matrix(la);
		}
		
		/**
		 * ��m����bx��by������ex��ey�������и�����������
		 * @param m �и�ľ���
		 * @param bx ��ʼ��x
		 * @param by ��ʼ��y
		 * @param ex ������x
		 * @param ey ������y
		 * @return �Ծ����ʾ����long������
		 * @throws QException ��񲻷�������ת��ʧ��
		 */
		public Matrix sumMatWithLong(Matrix m,int bx,int by,int ex,int ey) 
				throws QException{
			Matrix m1 = m.getDataToMatrix(bx, by, ex, ey);
			return sumMatWithLong(m1);
		}
		
		
		/**
		 * �����ͬ�ľ������������
		 * @param m ��Ҫ��ӵľ���
		 * @return �Ծ����ʾ����float������
		 * @throws QException ��񲻷�������ת��ʧ��
		 */
		public Matrix sumMatWithFloat(Matrix m) throws QException{
			List<ArrayList<String>> la = new ArrayList<ArrayList<String>>();
			if(!(this.xAxis == m.xAxis && this.yAxis == m.yAxis)){throw new QException("�����xy������ͬ");}
			for(int i = 0;i < xAxis;i++){
				ArrayList<String> temp = new ArrayList<String>();
				for(int j = 0;j < yAxis;j++){
					float value = 0.0f;
					try{
						value = Float.parseFloat(this.l.get(i).get(j) )  + m.getDataToFloat(i, j);
					}catch(QException err){
						throw err;
					}catch(NumberFormatException e){
						throw new QException("������"+ this.l.get(i).get(j) + "����ת����Float");
					}
					temp.add(String.valueOf(value));
				}
				la.add(temp);
			}
			return new Matrix(la);
		}
		
		/**
		 * ��m����bx��by������ex��ey�������и�����������
		 * @param m �и�ľ���
		 * @param bx ��ʼ��x
		 * @param by ��ʼ��y
		 * @param ex ������x
		 * @param ey ������y
		 * @return �Ծ����ʾ����float������
		 * @throws QException ��񲻷�������ת��ʧ��
		 */
		public Matrix sumMatWithFloat(Matrix m,int bx,int by,int ex,int ey) 
				throws QException{
			Matrix m1 = m.getDataToMatrix(bx, by, ex, ey);
			return sumMatWithFloat(m1);
		}
		
		
		
		/**
		 * �����ͬ�ľ������������
		 * @param m ��Ҫ��ӵľ���
		 * @return �Ծ����ʾ����double������
		 * @throws QException ��񲻷�������ת��ʧ��
		 */
		public Matrix sumMatWithDouble(Matrix m) throws QException{
			List<ArrayList<String>> la = new ArrayList<ArrayList<String>>();
			if(!(this.xAxis == m.xAxis && this.yAxis == m.yAxis)){throw new QException("�����xy������ͬ");}
			for(int i = 0;i < xAxis;i++){
				ArrayList<String> temp = new ArrayList<String>();
				for(int j = 0;j < yAxis;j++){
					double value = 0.0;
					try{
						value = Double.parseDouble(this.l.get(i).get(j) )  + m.getDataToDouble(i, j);
					}catch(QException err){
						throw err;
					}catch(NumberFormatException e){
						throw new QException("������"+ this.l.get(i).get(j) + "����ת����Double");
					}
					temp.add(String.valueOf(value));
				}
				la.add(temp);
			}
			return new Matrix(la);
		}
		
		/**
		 * ��m����bx��by������ex��ey�������и�����������
		 * @param m �и�ľ���
		 * @param bx ��ʼ��x
		 * @param by ��ʼ��y
		 * @param ex ������x
		 * @param ey ������y
		 * @return �Ծ����ʾ����double������
		 * @throws QException ��񲻷�������ת��ʧ��
		 */
		public Matrix sumMatWithDouble(Matrix m,int bx,int by,int ex,int ey) 
				throws QException{
			Matrix m1 = m.getDataToMatrix(bx, by, ex, ey);
			return sumMatWithDouble(m1);
		}
		
//////////////////////////////////////////////////////////////////////////////���
		
		/**
		 * ����x���������x�д�b��e�������������int������������
		 * @param x ����
		 * @param b ��ʼ
		 * @param e ����
		 * @return ���
		 * @throws QException b����e��Խ�磬����ת��
		 */
		
		public int mulXToInt(int x,int b,int e) throws QException{
			int sum = 1;
			if(b > e){throw new QException("b ���ܴ��� e");}
			if(x < 0 || x >= xAxis){throw new QException("xԽ��");}
			else{
				ArrayList<String> a = this.l.get(x);
				if(b < 0 || e >= a.size()){ throw new QException(b + "->" + e + "Խ��");}
				for(int i=b;i <= e;i++){
					
					try{
						sum *= Integer.parseInt(a.get(i));
					}catch(NumberFormatException err){
						throw new QException(i + "--����ת����Integer");
					}
					
				}
			}
			return sum;
			
		}
		
		
		/**
		 * ����x���������x�д�b��e�������������int������������
		 * @param x ����
		 * @return ���
		 * @throws QException b����e��Խ�磬����ת��
		 */
		public int mulXToInt(int x) throws QException{
			
			if(x < 0 || x >= xAxis){throw new QException("xԽ��");}
			else{
				int length = this.l.get(x).size();
				return this.mulXToInt(x, 0, length-1);
			}

		}
		
		/**
		 * ����x���������x�д�b��e�������������float������������
		 * @param x ����
		 * @param b ��ʼ
		 * @param e ����
		 * @return ���
		 * @throws QException b����e��Խ�磬����ת��
		 */
		
		public float mulXToFloat(int x,int b,int e) throws QException{
			float sum = 1.0f;
			if(b > e){throw new QException("b ���ܴ��� e");}
			if(x < 0 || x >= xAxis){throw new QException("xԽ��");}
			else{
				ArrayList<String> a = this.l.get(x);
				if(b < 0 || e >= a.size()){ throw new QException(b + "->" + e + "Խ��");}
				for(int i=b;i <= e;i++){
					
					try{
						sum *= Float.parseFloat(a.get(i));
					}catch(NumberFormatException err){
						throw new QException(i + "--����ת����Float");
					}
					
				}
			}
			return sum;
			
		}
		
		
		/**
		 * ����x���������x�д�b��e�������������float������������
		 * @param x ����
		 * @return ���
		 * @throws QException b����e��Խ�磬����ת��
		 */
		public float mulXToFloat(int x) throws QException{
			
			if(x < 0 || x >= xAxis){throw new QException("xԽ��");}
			else{
				int length = this.l.get(x).size();
				return this.mulXToFloat(x, 0, length-1);
			}

		}
		
		/**
		 * ����x���������x�д�b��e�������������long������������
		 * @param x ����
		 * @param b ��ʼ
		 * @param e ����
		 * @return ���
		 * @throws QException b����e��Խ�磬����ת��
		 */
		
		public long mulXToLong(int x,int b,int e) throws QException{
			long sum = 1l;
			if(b > e){throw new QException("b ���ܴ��� e");}
			if(x < 0 || x >= xAxis){throw new QException("xԽ��");}
			else{
				ArrayList<String> a = this.l.get(x);
				if(b < 0 || e >= a.size()){ throw new QException(b + "->" + e + "Խ��");}
				for(int i=b;i <= e;i++){
					
					try{
						sum *= Long.parseLong(a.get(i));
					}catch(NumberFormatException err){
						throw new QException(i + "--����ת����Long");
					}
					
				}
			}
			return sum;
			
		}
		
		
		/**
		 * ����x���������x�д�b��e�������������long������������
		 * @param x ����
		 * @return ���
		 * @throws QException b����e��Խ�磬����ת��
		 */
		public long mulXToLong(int x) throws QException{
			
			if(x < 0 || x >= xAxis){throw new QException("xԽ��");}
			else{
				int length = this.l.get(x).size();
				return this.mulXToLong(x, 0, length-1);
			}

		}
		
		/**
		 * ����x���������x�д�b��e�������������double������������
		 * @param x ����
		 * @param b ��ʼ
		 * @param e ����
		 * @return ���
		 * @throws QException b����e��Խ�磬����ת��
		 */
		
		public double mulXToDouble(int x,int b,int e) throws QException{
			double sum = 1.0;
			if(b > e){throw new QException("b ���ܴ��� e");}
			if(x < 0 || x >= xAxis){throw new QException("xԽ��");}
			else{
				ArrayList<String> a = this.l.get(x);
				if(b < 0 || e >= a.size()){ throw new QException(b + "->" + e + "Խ��");}
				for(int i=b;i <= e;i++){
					
					try{
						sum *= Double.parseDouble(a.get(i));
					}catch(NumberFormatException err){
						throw new QException(i + "--����ת����Double");
					}
					
				}
			}
			return sum;
			
		}
		
		
		/**
		 * ����x���������x�д�b��e�������������double������������
		 * @param x ����
		 * @return ���
		 * @throws QException b����e��Խ�磬����ת��
		 */
		public double mulXToDouble(int x) throws QException{
			
			if(x < 0 || x >= xAxis){throw new QException("xԽ��");}
			else{
				int length = this.l.get(x).size();
				return this.mulXToDouble(x, 0, length-1);
			}

		}
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		/**
		 * ����ʵ��b�е�e���������int���棬�������ݱ���Ϊ����
		 * ����һ�зֱ��ʾ����֮����
		 * @param b ��ʼ
		 * @param e ����
		 * @return ���󣬷���һ�д���ÿһ�еĻ�
		 * @throws QException b����e��Խ�磬ת��ʧ��
		 */
		public Matrix mulXMatWithInt(int b,int e) throws QException{
			if(b > e){throw new QException("b ���ܴ��� e");}
			if(b<0||e>=this.xAxis){throw new QException("�±�Խ��");}
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
						throw new QException(a.get(j) + " ���ʹ���");
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
		 * ����ʵ��ÿ���������int���棬�������ݱ���Ϊ����
		 * ����һ�зֱ��ʾ����֮����
		 * @return ���󣬷���һ�д���ÿһ�еĻ�
		 * @throws QException b����e��Խ�磬ת��ʧ��
		 */
		public Matrix mulXMatWithInt() throws QException{
			return mulXMatWithInt( 0, this.xAxis-1);	
		}
		
		
		/**
		 * ����ʵ��b�е�e���������long���棬�������ݱ���Ϊ����
		 * ����һ�зֱ��ʾ����֮����
		 * @param b ��ʼ
		 * @param e ����
		 * @return ���󣬷���һ�д���ÿһ�еĻ�
		 *@throws QException b����e��Խ�磬ת��ʧ��
		 */
		public Matrix mulXMatWithLong(int b,int e) throws QException{
			
			if(b > e){throw new QException("b ���ܴ��� e");}
			if(b<0||e>=this.xAxis){throw new QException("�±�Խ��");}
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
						throw new QException(a.get(j) + " ���ʹ���");
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
		 * ����ʵ��ÿ���������long���棬�������ݱ���Ϊ����
		 * ����һ�зֱ��ʾ����֮����
		 * @return ���󣬷���һ�д���ÿһ�еĻ�
		 * @throws QException b����e��Խ�磬ת��ʧ��
		 */
		public Matrix mulXMatWithLong() throws QException{
			return mulXMatWithLong(0, this.xAxis-1);	
		}
		
		
		
		/**
		 * ����ʵ��b�е�e���������float���棬�������ݱ���Ϊ����
		 * ����һ�зֱ��ʾ����֮����
		 * @param b ��ʼ
		 * @param e ����
		 * @return ���󣬷���һ�д���ÿһ�еĻ�
		 * @throws QException b����e��Խ�磬ת��ʧ��
		 */
		public Matrix mulXMatWithFloat(int b,int e) throws QException{
			if(b > e){throw new QException("b ���ܴ��� e");}
			if(b<0||e>=this.xAxis){throw new QException("�±�Խ��");}
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
						throw new QException(a.get(j) + " ���ʹ���");
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
		 * ����ʵ��ÿ���������long���棬�������ݱ���Ϊ����
		 * ����һ�зֱ��ʾ����֮����
		 * @return ���󣬷���һ�д���ÿһ�еĻ�
		 * @throws QException b����e��Խ�磬ת��ʧ��
		 */
		public Matrix mulXMatWithFloat() throws QException{
			return mulXMatWithFloat(0, this.xAxis-1);	
		}
		
		
		/**
		 * ����ʵ��b�е�e���������double���棬�������ݱ���Ϊ����
		 * ����һ�зֱ��ʾ����֮����
		 * @param b ��ʼ
		 * @param e ����
		 * @return ���󣬷���һ�д���ÿһ�еĻ�
		 * @throws QException b����e��Խ�磬ת��ʧ��
		 */
		public Matrix mulXMatWithDouble(int b,int e) throws QException{
			if(b > e){throw new QException("b ���ܴ��� e");}
			if(b<0||e>=this.xAxis){throw new QException("�±�Խ��");}
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
						throw new QException(a.get(j) + " ���ʹ���");
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
		 * ����ʵ��ÿ���������long���棬�������ݱ���Ϊ����
		 * ����һ�зֱ��ʾ����֮����
		 * @return ���󣬷���һ�д���ÿһ�еĻ�
		 * @throws QException b����e��Խ�磬ת��ʧ��
		 */
		public Matrix mulXMatWithDouble() throws QException{
			return mulXMatWithDouble( 0, this.xAxis-1);	
		}
		
		/**
		 * ����ʵ��b�е�e���������int���棬�������ݱ���Ϊ����
		 * ����һ�зֱ��ʾ����֮����
		 * @param b ��ʼ
		 * @param e ����
		 * @return ���󣬷���һ�д���ÿһ�еĻ�
		 * @throws QException b����e��Խ�磬ת��ʧ��
		 */
		public Matrix mulYMatWithInt(int b,int e) throws QException{
			if(b > e){throw new QException("b ���ܴ��� e");}
			if(b < 0){throw new QException("b ��������0");}
			List<ArrayList<String>> la = new ArrayList<ArrayList<String>>();
			
			
			
			for(int i = 0;i <this.xAxis;i++){
				
				ArrayList<String> temp = new ArrayList<String>();
				ArrayList<String> a = this.l.get(i);
				if(e<a.size()){throw new QException("eȡֵ����");}
				int sum = 0;
				for(int j = b; j <= e;j++){
					int value = 0;
					try{
						value = Integer.parseInt(a.get(j));
					}catch(NumberFormatException err){
						throw new QException(a.get(j) + " ����ת����Integer��");
					}
					sum *= value;
				}
				temp.add(String.valueOf(sum));
				la.add(temp);
			}
			
			return new Matrix(la);
		}
		
		
		/**
		 * ����ʵ�ָ����������int���棬�������ݱ���Ϊ����
		 * ����һ�зֱ��ʾ����֮����

		 * @return ���󣬷���һ�д���ÿһ�еĻ�
		 * @throws QException b����e��ת��ʧ��
		 */
		public Matrix mulYMatWithInt() throws QException{
			return this.mulYMatWithInt( 0, this.yAxis-1);
		}
		
		
		/**
		 * ����ʵ��b�е�e���������long���棬�������ݱ���Ϊ����
		 * ����һ�зֱ��ʾ����֮����
		 * @param b ��ʼ
		 * @param e ����
		 * @return ���󣬷���һ�д���ÿһ�еĻ�
		 * @throws QException b����e��Խ�磬ת��ʧ��
		 */
		public Matrix mulYMatWithLong(int b,int e) throws QException{
			if(b > e){throw new QException("b ���ܴ��� e");}
			if(b < 0){throw new QException("b ��������0");}
			List<ArrayList<String>> la = new ArrayList<ArrayList<String>>();
			
			
			
			for(int i = 0;i <this.xAxis;i++){
				
				ArrayList<String> temp = new ArrayList<String>();
				ArrayList<String> a = this.l.get(i);
				if(e>=a.size()){throw new QException("eȡֵ����");}
				long sum = 0l;
				for(int j = b; j <= e;j++){
					long value = 0;
					try{
						value = Long.parseLong(a.get(j));
					}catch(NumberFormatException err){
						throw new QException(a.get(j) + " ����ת����Long");
					}
					sum *= value;
				}
				temp.add(String.valueOf(sum));
				la.add(temp);
			}
			
			return new Matrix(la);
		}
		
		
		/**
		 * ����ʵ�ָ����������long���棬�������ݱ���Ϊ����
		 * ����һ�зֱ��ʾ����֮����

		 * @return ���󣬷���һ�д���ÿһ�еĻ�
		 * @throws QException b����e��ת��ʧ��
		 */
		public Matrix mulYMatWithLong() throws QException{
			return this.mulYMatWithLong( 0, this.yAxis-1);
		}
		
		
		/**
		 * ����ʵ��b�е�e���������float���棬�������ݱ���Ϊ����
		 * ����һ�зֱ��ʾ����֮����
		 * @param b ��ʼ
		 * @param e ����
		 * @return ���󣬷���һ�д���ÿһ�еĻ�
		 * @throws QException b����e��Խ�磬ת��ʧ��
		 */
		
		public Matrix mulYMatWithFloat(int b,int e) throws QException{
			if(b > e){throw new QException("b ���ܴ��� e");}
			if(b < 0){throw new QException("b ��������0");}
			List<ArrayList<String>> la = new ArrayList<ArrayList<String>>();
			
			
			
			for(int i = 0;i <this.xAxis;i++){
				
				ArrayList<String> temp = new ArrayList<String>();
				ArrayList<String> a = this.l.get(i);
				if(e>=a.size()){throw new QException("eȡֵ����");}
				float sum = 0.0f;
				for(int j = b; j <= e;j++){
					float value = 0;
					try{
						value = Float.parseFloat(a.get(j));
					}catch(NumberFormatException err){
						throw new QException(a.get(j) + " ����ת����Float");
					}
					sum *= value;
				}
				temp.add(String.valueOf(sum));
				la.add(temp);
			}
			
			return new Matrix(la);
		}
		
		
		/**
		 * ����ʵ�ָ����������float����
		 * ����һ�зֱ��ʾ����֮����

		 * @return ���󣬷���һ�д���ÿһ�еĻ�
		 * @throws QException b����e��ת��ʧ��
		 */
		public Matrix mulYMatWithFloat() throws QException{
			return this.mulYMatWithFloat( 0, this.yAxis-1);
		}
		
		/**
		 * ����ʵ��b�е�e���������double����
		 * ����һ�зֱ��ʾ����֮����
		 * @param b ��ʼ
		 * @param e ����
		 * @return ���󣬷���һ�д���ÿһ�еĻ�
		 * @throws QException b����e��Խ�磬ת��ʧ��
		 */
		
		public Matrix mulYMatWithDouble(int b,int e) throws QException{
			if(b > e){throw new QException("b ���ܴ��� e");}
			if(b < 0){throw new QException("b ��������0");}
			List<ArrayList<String>> la = new ArrayList<ArrayList<String>>();
			
			
			
			for(int i = 0;i <this.xAxis;i++){
				
				ArrayList<String> temp = new ArrayList<String>();
				ArrayList<String> a = this.l.get(i);
				if(e>=a.size()){throw new QException("eȡֵ����");}
				double sum = 0.0;
				for(int j = b; j <= e;j++){
					double value = 0;
					try{
						value = Double.parseDouble(a.get(j));
					}catch(NumberFormatException err){
						throw new QException(a.get(j) + " ����ת����Double");
					}
					sum *= value;
				}
				temp.add(String.valueOf(sum));
				la.add(temp);
			}
			
			return new Matrix(la);
		}
		
		
		/**
		 * ����ʵ�ָ����������double����
		 * ����һ�зֱ��ʾ����֮����

		 * @return ���󣬷���һ�д���ÿһ�еĻ�
		 * @throws QException b����e��ת��ʧ��
		 */
		public Matrix mulYMatWithDouble() throws QException{
			return this.mulYMatWithDouble(0, this.yAxis-1);
		}
		
		
		
		
		
		/**
		 * �����ͬ�ľ������������
		 * @param m ��Ҫ��ӵľ���
		 * @return �Ծ����ʾ����int������
		 * @throws QException ��񲻷�������ת��ʧ��
		 */
		public Matrix mulMatWithInt(Matrix m) throws QException{
			List<ArrayList<String>> la = new ArrayList<ArrayList<String>>();
			if(!(this.xAxis == m.xAxis && this.yAxis == m.yAxis)){throw new QException("�����xy������ͬ");}
			for(int i = 0;i < xAxis;i++){
				ArrayList<String> temp = new ArrayList<String>();
				for(int j = 0;j < yAxis;j++){
					int value = 0;
					try{
						value = Integer.parseInt(this.l.get(i).get(j) )  *  m.getDataToInt(i, j);
					}catch(QException err){
						throw err;
					}catch(NumberFormatException e){
						throw new QException("������"+ this.l.get(i).get(j) + "����ת����Integer");
					}
					temp.add(String.valueOf(value));
				}
				la.add(temp);
			}
			return new Matrix(la);
		}
		
		/**
		 * ��m����bx��by������ex��ey�������и�����������
		 * @param m �и�ľ���
		 * @param bx ��ʼ��x
		 * @param by ��ʼ��y
		 * @param ex ������x
		 * @param ey ������y
		 * @return �Ծ����ʾ����int������
		 * @throws QException ��񲻷�������ת��ʧ��
		 */
		public Matrix mulMatWithInt(Matrix m,int bx,int by,int ex,int ey) 
				throws QException{
			Matrix m1 = m.getDataToMatrix(bx, by, ex, ey);
			return mulMatWithInt(m1);
		}
		
		
		/**
		 * �����ͬ�ľ������������
		 * @param m ��Ҫ��ӵľ���
		 * @return �Ծ����ʾ����long������
		 * @throws QException ��񲻷�������ת��ʧ��
		 */
		public Matrix mulMatWithLong(Matrix m) throws QException{
			List<ArrayList<String>> la = new ArrayList<ArrayList<String>>();
			if(!(this.xAxis == m.xAxis && this.yAxis == m.yAxis)){throw new QException("�����xy������ͬ");}
			for(int i = 0;i < xAxis;i++){
				ArrayList<String> temp = new ArrayList<String>();
				for(int j = 0;j < yAxis;j++){
					long value = 0;
					try{
						value = Long.parseLong(this.l.get(i).get(j) )  * m.getDataToLong(i, j);
					}catch(QException err){
						throw err;
					}catch(NumberFormatException e){
						throw new QException("������"+ this.l.get(i).get(j) + "����ת����Long");
					}
					temp.add(String.valueOf(value));
				}
				la.add(temp);
			}
			return new Matrix(la);
		}
		
		/**
		 * ��m����bx��by������ex��ey�������и�����������
		 * @param m �и�ľ���
		 * @param bx ��ʼ��x
		 * @param by ��ʼ��y
		 * @param ex ������x
		 * @param ey ������y
		 * @return �Ծ����ʾ����long������
		 * @throws QException ��񲻷�������ת��ʧ��
		 */
		public Matrix mulMatWithLong(Matrix m,int bx,int by,int ex,int ey) 
				throws QException{
			Matrix m1 = m.getDataToMatrix(bx, by, ex, ey);
			return mulMatWithLong(m1);
		}
		
		
		/**
		 * �����ͬ�ľ������������
		 * @param m ��Ҫ��ӵľ���
		 * @return �Ծ����ʾ����float������
		 * @throws QException ��񲻷�������ת��ʧ��
		 */
		public Matrix mulMatWithFloat(Matrix m) throws QException{
			List<ArrayList<String>> la = new ArrayList<ArrayList<String>>();
			if(!(this.xAxis == m.xAxis && this.yAxis == m.yAxis)){throw new QException("�����xy������ͬ");}
			for(int i = 0;i < xAxis;i++){
				ArrayList<String> temp = new ArrayList<String>();
				for(int j = 0;j < yAxis;j++){
					float value = 0.0f;
					try{
						value = Float.parseFloat(this.l.get(i).get(j) )  * m.getDataToFloat(i, j);
					}catch(QException err){
						throw err;
					}catch(NumberFormatException e){
						throw new QException("������"+ this.l.get(i).get(j) + "����ת����Float");
					}
					temp.add(String.valueOf(value));
				}
				la.add(temp);
			}
			return new Matrix(la);
		}
		
		/**
		 * ��m����bx��by������ex��ey�������и�����������
		 * @param m �и�ľ���
		 * @param bx ��ʼ��x
		 * @param by ��ʼ��y
		 * @param ex ������x
		 * @param ey ������y
		 * @return �Ծ����ʾ����float������
		 * @throws QException ��񲻷�������ת��ʧ��
		 */
		public Matrix mulMatWithFloat(Matrix m,int bx,int by,int ex,int ey) 
				throws QException{
			Matrix m1 = m.getDataToMatrix(bx, by, ex, ey);
			return mulMatWithFloat(m1);
		}
		
		
		
		/**
		 * �����ͬ�ľ������������
		 * @param m ��Ҫ��ӵľ���
		 * @return �Ծ����ʾ����double������
		 * @throws QException ��񲻷�������ת��ʧ��
		 */
		public Matrix mulMatWithDouble(Matrix m) throws QException{
			List<ArrayList<String>> la = new ArrayList<ArrayList<String>>();
			if(!(this.xAxis == m.xAxis && this.yAxis == m.yAxis)){throw new QException("�����xy������ͬ");}
			for(int i = 0;i < xAxis;i++){
				ArrayList<String> temp = new ArrayList<String>();
				for(int j = 0;j < yAxis;j++){
					double value = 0.0;
					try{
						value = Double.parseDouble(this.l.get(i).get(j) )  * m.getDataToDouble(i, j);
					}catch(QException err){
						throw err;
					}catch(NumberFormatException e){
						throw new QException("������"+ this.l.get(i).get(j) + "����ת����Double");
					}
					temp.add(String.valueOf(value));
				}
				la.add(temp);
			}
			return new Matrix(la);
		}
		
		/**
		 * ��m����bx��by������ex��ey�������и�����������
		 * @param m �и�ľ���
		 * @param bx ��ʼ��x
		 * @param by ��ʼ��y
		 * @param ex ������x
		 * @param ey ������y
		 * @return �Ծ����ʾ����double������
		 * @throws QException ��񲻷�������ת��ʧ��
		 */
		public Matrix mulMatWithDouble(Matrix m,int bx,int by,int ex,int ey) 
				throws QException{
			Matrix m1 = m.getDataToMatrix(bx, by, ex, ey);
			return mulMatWithDouble(m1);
		}
		
////////////////////////////////////////////////////////////////////////////////////////////////////////��ƽ��ֵ		
		/**
		 * ����x�У���x�д�b��e��������ƽ������Integer������������
		 * @param x ����
		 * @param b ��ʼ
		 * @param e ����
		 * @return ���
		 * @throws QException b����e��Խ�磬����ת��
		 */
		public int averXToInt(int x,int b,int e) throws QException{
			int number = e-b+1;
			int sum = this.sumXToInt(x, b, e);
			return sum / number;
		}
		/**
		 * ����x����ƽ������Integer������������
		 * @param x ����
		 * @return ���
		 * @throws QException b����e��Խ�磬����ת��
		 */
		public int averXToInt(int x) throws QException{
			int length = this.l.get(x).size();
			return averXToInt(x, 0, length-1);
		}
		
		/**
		 * ����x�У���x�д�b��e��������ƽ������long������������
		 * @param x ����
		 * @param b ��ʼ
		 * @param e ����
		 * @return ���
		 * @throws QException b����e��Խ�磬����ת��
		 */
		public long averXToLong(int x,int b,int e) throws QException{
			long number = e-b+1;
			long sum = this.sumXToLong(x, b, e);
			return sum / number;
		}
		/**
		 * ����x����ƽ������long������������
		 * @param x ����
		 * @return ���
		 * @throws QException b����e��Խ�磬����ת��
		 */
		public long averXToLong(int x) throws QException{
			int length = this.l.get(x).size();
			return averXToLong(x, 0,length-1);
		}
		
		/**
		 * ����x�У���x�д�b��e��������ƽ������float������������
		 * @param x ����
		 * @param b ��ʼ
		 * @param e ����
		 * @return ���
		 * @throws QException b����e��Խ�磬����ת��
		 */
		public float averXToFloat(int x,int b,int e) throws QException{
			int number = e-b+1;
			float sum = this.sumXToLong(x, b, e);
			return sum / number;
		}
		/**
		 * ����x����ƽ������float������������
		 * @param x ����
		 * @return ���
		 * @throws QException b����e��Խ�磬����ת��
		 */
		public float averXToFloat(int x) throws QException{
			int length = this.l.get(x).size();
			return averXToLong(x, 0, length-1);
		}
		
		/**
		 * ����x�У���x�д�b��e��������ƽ������double������������
		 * @param x ����
		 * @param b ��ʼ
		 * @param e ����
		 * @return ���
		 * @throws QException b����e��Խ�磬����ת��
		 */
		public double averXToDouble(int x,int b,int e) throws QException{
			long number = e-b+1;
			double sum = this.sumXToDouble(x, b, e);
			return sum / number;
		}
		/**
		 * ����x����ƽ������double������������
		 * @param x ����
		 * @return ���
		 * @throws QException b����e��Խ�磬����ת��
		 */
		public double averXToDouble(int x) throws QException{
			int length = this.l.get(x).size();
			return averXToDouble(x, 0, length-1);
		}
		
		
		
		/**
		 * ����y�У���b��e��������ƽ��ֵ����int������������
		 * @param y ����
		 * @param b ��ʼ
		 * @param e ����
		 * @return ���
		 * @throws QException b����e��Խ�磬����ת��
		 */
		public int averYToInt(int y,int b,int e) throws QException{
			int number = e-b+1;
			int sum = this.sumYToInt(y, b, e);
			return sum / number;
		}
		/**
		 * ����y�У�������ƽ��ֵ����int������������
		 * @param y ����
		 * @return ���
		 * @throws QException Խ�磬����ת��
		 */
		public int averYToInt(int y) throws QException{
			return averYToInt(y, 0, xAxis-1);
		}
		
		/**
		 * ����y�У���b��e��������ƽ��ֵ����long������������
		 * @param y ����
		 * @param b ��ʼ
		 * @param e ����
		 * @return ���
		 * @throws QException b����e��Խ�磬����ת��
		 */
		public long averYToLong(int y,int b,int e) throws QException{
			long number = e-b+1;
			long sum = this.sumYToLong(y, b, e);
			return sum / number;
		}
		/**
		 * ����y�У�������ƽ��ֵ����long������������
		 * @param y ����
		 * @return ���
		 * @throws QException Խ�磬����ת��
		 */
		public long averYToLong(int y) throws QException{
			return averYToLong(y, 0, xAxis-1);
		}
		
		/**
		 * ����y�У���b��e��������ƽ��ֵ����float������������
		 * @param y ����
		 * @param b ��ʼ
		 * @param e ����
		 * @return ���
		 * @throws QException b����e��Խ�磬����ת��
		 */
		public float averYToFloat(int y,int b,int e) throws QException{
			int number = e-b+1;
			float sum = this.sumYToLong(y, b, e);
			return sum / number;
		}
		/**
		 * ����y�У�������ƽ��ֵ����float������������
		 * @param y ����
		 * @return ���
		 * @throws QException Խ�磬����ת��
		 */
		public float averYToFloat(int y) throws QException{
			return averYToLong(y, 0, xAxis-1);
		}
		
		/**
		 * ����y�У���b��e��������ƽ��ֵ����double������������
		 * @param y ����
		 * @param b ��ʼ
		 * @param e ����
		 * @return ���
		 * @throws QException b����e��Խ�磬����ת��
		 */
		public double averYToDouble(int y,int b,int e) throws QException{
			long number = e-b+1;
			double sum = this.sumYToDouble(y, b, e);
			return sum / number;
		}
		/**
		 * ����y�У�������ƽ��ֵ����double������������
		 * @param y ����
		 * @return ���
		 * @throws QException Խ�磬����ת��
		 */
		public double averYToDouble(int y) throws QException{
			return averYToDouble(y, 0, xAxis-1);
		}
		
//////////////////////////////////////////////////////////////////////////////////////////////������ƽ��ֵ
		/**
		 * ��ʵ��b�е�e����ƽ��ֵ����int���棬����������ɾ���
		 * 
		 * @param b ��ʼ
		 * @param e ����
		 * @return ����һ�зֱ��ʾ����ƽ��ֵ
		 * @throws QException b����e��Խ�磬���ʹ���
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
		 * ��ʵ�ֶ�ÿ����ƽ��ֵ����int���棬����������ɾ���

		 * @return ����һ�зֱ��ʾ����ƽ��ֵ
		 * @throws QException Խ�磬���ʹ���
		 */
		public Matrix averXMatWithInt() throws QException{
			return this.averXMatWithInt( 0, xAxis-1);
		}
		
		
		
		/**
		 * ��ʵ��b�е�e����ƽ��ֵ����long���棬����������ɾ���
		 * 
		 * @param b ��ʼ
		 * @param e ����
		 * @return ����һ�зֱ��ʾ����ƽ��ֵ
		 * @throws QException b����e��Խ�磬���ʹ���
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
		 * ��ʵ�ֶ�ÿ����ƽ��ֵ����long���棬����������ɾ���

		 * @return ����һ�зֱ��ʾ����ƽ��ֵ
		 * @throws QException Խ�磬���ʹ���
		 */
		public Matrix averXMatWithLong() throws QException{
			return this.averXMatWithLong( 0, xAxis-1);
		}
		
		
		/**
		 * ��ʵ��b�е�e����ƽ��ֵ����float���棬����������ɾ���
		 * 
		 * @param b ��ʼ
		 * @param e ����
		 * @return ����һ�зֱ��ʾ����ƽ��ֵ
		 * @throws QException b����e��Խ�磬���ʹ���
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
		 * ��ʵ�ֶ�ÿ����ƽ��ֵ����float���棬����������ɾ���

		 * @return ����һ�зֱ��ʾ����ƽ��ֵ
		 * @throws QException Խ�磬���ʹ���
		 */
		public Matrix averXMatWithFloat() throws QException{
			return this.averXMatWithFloat( 0, xAxis-1);
		}
		
		
		/**
		 * ��ʵ��b�е�e����ƽ��ֵ����double���棬����������ɾ���
		 * 
		 * @param b ��ʼ
		 * @param e ����
		 * @return ����һ�зֱ��ʾ����ƽ��ֵ
		 * @throws QException b����e��Խ�磬���ʹ���
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
		 * ��ʵ�ֶ�ÿ����ƽ��ֵ����double���棬����������ɾ���

		 * @return ����һ�зֱ��ʾ����ƽ��ֵ
		 * @throws QException ��Խ�磬���ʹ���
		 */
		public Matrix averXMatWithDouble() throws QException{
			return this.averXMatWithDouble( 0, xAxis-1);
		}
		
		
		
		/**
		 * ��ʵ��b�е�e����ƽ��ֵ����int���棬����������ɾ���
		 * 
		 * @param b ��ʼ
		 * @param e ����
		 * @return ����һ�зֱ��ʾ����ƽ��ֵ
		 * @throws QException b����e��Խ�磬���ʹ���
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
		 * ��ʵ�ֶ�ÿ����ƽ��ֵ����int���棬����������ɾ���
		 * 

		 * @return ����һ�зֱ��ʾ����ƽ��ֵ
		 * @throws QException Խ�磬���ʹ���
		 */
		public Matrix averYMatWithInt() throws QException{
			return this.averYMatWithInt(0, yAxis-1);
		}
		
		
		
		/**
		 * ��ʵ��b�е�e����ƽ��ֵ����long���棬����������ɾ���
		 * 
		 * @param b ��ʼ
		 * @param e ����
		 * @return ����һ�зֱ��ʾ����ƽ��ֵ
		 * @throws QException b����e��Խ�磬���ʹ���
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
		 * ��ʵ�ֶ�ÿ����ƽ��ֵ����long���棬����������ɾ���

		 * @return ����һ�зֱ��ʾ����ƽ��ֵ
		 * @throws QException Խ�磬���ʹ���
		 */
		public Matrix averYMatWithLong() throws QException{
			return this.averYMatWithLong( 0, yAxis-1);
		}
		
		
		/**
		 * ��ʵ��b�е�e����ƽ��ֵ����float���棬����������ɾ���
		 * 
		 * @param b ��ʼ
		 * @param e ����
		 * @return ����һ�зֱ��ʾ����ƽ��ֵ
		 * @throws QException b����e��Խ�磬���ʹ���
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
		 * ��ʵ�ֶ�ÿ����ƽ��ֵ����float���棬����������ɾ���

		 * @return ����һ�зֱ��ʾ����ƽ��ֵ
		 * @throws QException Խ�磬���ʹ���
		 */
		public Matrix averYMatWithFloat() throws QException{
			return this.averYMatWithFloat(0, yAxis-1);
		}
		
		
		/**
		 * ��ʵ��b�е�e����ƽ��ֵ����double���棬����������ɾ���
		 * 
		 * @param b ��ʼ
		 * @param e ����
		 * @return ����һ�зֱ��ʾ����ƽ��ֵ
		 * @throws QException b����e��Խ�磬���ʹ���
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
		 * ��ʵ�ֶ�ÿ����ƽ��ֵ����double���棬����������ɾ���

		 * @return ����һ�зֱ��ʾ����ƽ��ֵ
		 * @throws QException Խ�磬���ʹ���
		 */
		public Matrix averYMatWithDouble() throws QException{
			return this.averYMatWithDouble(0, yAxis-1);
		}
		
		/**
		 * �Ծ����е�ÿһ��Ԫ�ؽ���ƽ�����㣬���ص�Matrix�е���������String
		 * �������������DoubleΪ���ʣ�����Ժ��ȡ���ݵ�ʱ��ֻ����float��double���ͻ�ȡ
		 * @param pow ָ��
		 * @return ������
		 * @throws QException ת������ʧ��
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
		 * ʵ�־���ÿλԪ����������int������
		 * @throws QException ת������ʧ��
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
		 * ʵ�־���ÿλԪ����������long������
		 * @throws QException ת������ʧ��
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
		 * ʵ�־���ÿλԪ���Լ�����int������
		 * @throws QException ת������ʧ��
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
		 * ʵ�־���ÿλԪ���Լ�����long������
		 * @throws QException ת������ʧ��
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
		 * ��ÿһ�е�y���У������key��ֵ����ֳ�һ�������󷵻���������ɵľ���
		 * ��С��y����ȥ����
		 * @param y �Ե�y�н��з���
		 * @param key �����ָ��ֵ
		 * @return y��ֵ����key���Ӿ���
		 * @throws QException yС��0
		 */
		public Matrix classfiy(int y,String key) throws QException{
			if(y < 0){throw new QException("yС��0");}
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
		/**��ȡ��[[1,2,3]]��ʽ���ַ���
		 * @return ���ش�[[1,2,3]]��ʽ���ַ���
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
						str +="]��";
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
	 * ����QCSV��
	 * QCSV���Խ���File����ļ��Ĵ���
	 * Ҫ�봦��������Ҫ��ȡ������д���
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
	 * ����ָ��·����ȡCSV����QCSV����
	 * @param path �ļ�·��
	 * @throws QException ��IOExceptionת������
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
				str = str.replace("��", ",");
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
	 * ����File�����ȡCSV����QCSV����
	 * @param fileName File����
	 * @throws QException ��IOExceptionת������
	 */
	public QCSV(File fileName)throws QException{
		this(fileName.getPath());
	}
	
	
	/**
	 * ��������
	 * @return ����
	 */
	public int getLine(){return this.line;}
	
	/**
	 * ��ȡ���ݵ��������ַ�������ʽ����
	 * @return ��������
	 */
	public String getContent(){return this.mat.getAllDataToStr();}
	
	/**
	 * ��ȡ����
	 * @return �ɲ��ݵľ���
	 */
	public Matrix getMat(){return this.mat;}
	
	/**
	 * ���ⲿ����Matrix;
	 * @param list ��ά�б�
	 * @return �ö�ά�б����ľ���
	 * @throws QException �����������׳����쳣
	 */
	public static Matrix buildMat(List<ArrayList<String> > list)
			throws QException{
			return new QCSV("").new Matrix(list);
	}
	
	/**
	 * ���ݶ�άList��д
	 * @param list ��ά�б�
	 * @throws QException ��IOExceptionת������
	 */
	public void writeCSV(List<ArrayList<String>> list ) throws QException{
		this.writeCSV(new Matrix(list));
	}
	
	/**
	 * ���ݾ���m��д
	 * @param m ��Ҫд��ľ���
	 * @throws QException ��IOExceptionת������
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
