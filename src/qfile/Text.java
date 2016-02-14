package qfile;
import java.util.*;





public class Text {

	static void textTxt() throws Exception{
		String path = "C:\\Users\\zq001\\Desktop\\项目与实验\\试验场\\123.txt";
		QTxt qt = new QTxt(path);
		System.out.println(qt.getContent()); 
		System.out.println(qt.countWord("happy"));
		Map<String,String> m = new HashMap<String,String>();
		m.put("I", "you");m.put("am", "are");m.put("happy", "sad");
		System.out.println(qt.replace(m));
		qt.writeByReplaced(m);
		System.out.println(qt.getContent());
	}
	
	
	static void textQJson() throws Exception{
		String path = "C:\\Users\\zq001\\Desktop\\项目与实验\\Java项目\\Init.json";
		QJson qj = new QJson(path);
   	 System.out.println("内容：");
		 System.out.println(qj.getJsonStr());
		
		 QJson.JSONObject jo = qj.getJsonObj();
		 System.out.println("直接输出该对象信息:" + jo);
		
		 Set<String> s = jo.getKeys();
		 Iterator<String> iter = s.iterator(); 
		 System.out.println("该对象有如下键值:");
		 while(iter.hasNext()){
		  	 String key = iter.next();
			 System.out.println(key);
		 }
		
		 System.out.println("根据键值获取数据");
		 System.out.println("boolean类型 --" + jo.getBoolean("boolean"));
		 System.out.println("String类型 --" + jo.getString("String"));
		 System.out.println("long类型或int类型 --" + jo.getLong("long"));
		 System.out.println("double类型或float类型 --" + jo.getDouble("double"));
		 System.out.println("array类型 --" + jo.getJSONArray("array"));
		 System.out.println("obj类型 --" + jo.getJSONObject("obj"));   
		 
		 String str="{\"abc\":123}";
         QJson.JSONObject jo2 = QJson.buildJSONObject(str);
         System.out.println(jo2);
		
//		QJson.JSONArray ja = jo.getJSONArray("array");
//		int length = ja.size();
//		System.out.println("数组长：" + length);
//		for(int i = 0;i<length;i++){
//			System.out.println(i+"--"+jo.getLong(i));
//		}
		
//		System.out.println(jo.getString("str"));
	}
	
	static void textQCSV() throws Exception{
		String path ="C:\\Users\\zq001\\Desktop\\项目与实验\\试验场\\123.csv";
		QCSV qc = new QCSV(path);
		System.out.println("内容：");
		System.out.println(qc.getContent());
		System.out.println("数据行数： "+ qc.getLine());
		System.out.println("矩阵形式：");
		QCSV.Matrix  qm = qc.getMat();
		System.out.println(qm);
		System.out.println("只提取数字部分的子矩阵");
		QCSV.Matrix qm2 = qm.getDataToMatrix(1, 2, qc.getLine()-1, 2);
		System.out.println(qm2);
		System.out.println("竖轴求和是：" + qm2.sumYToInt(0));
		System.out.println("竖轴求平均是：" + qm2.averYToDouble(0));
		System.out.println("按\"一班\"分类:");
		System.out.println(qm.classfiy(1,"一班" ));
//		System.out.println("x:"+qm.getXAxis());
//		System.out.println("y:"+qm.getYAxis());
//		QCSV.Matrix qm2 = qm.getDataToMatrix(0, 3, 7, 5);
//		QCSV.Matrix qm3 = qm.getDataToMatrix(8, 3, 15, 5);
//		System.out.println("qm2:\n"+qm2);
//		System.out.println("qm3:\n"+qm3);
//		
//		System.out.println("QM2平方：\n " + qm2.powMatrixWithDouble(2));
//		qc.writeCSV(qm2);
//		System.out.println("重写后：");
//		System.out.println(qc.getMat());
//		System.out.println(qm2.getAllDataToStr());
//		System.out.println(qm2.mulYToDouble(1));
//		System.out.println(qm2.sumYMatWithDouble());

	}
	

	
	static void testQHtml() throws QException{
		String path = "C:\\Users\\zq001\\Desktop\\项目与实验\\Java项目\\123.html";
		QHtml qh = new QHtml(path);
		System.out.println("html的题目:"+qh.getTitle());
        
        System.out.println("html内容:"+qh.getContent());
        
        System.out.println("div标签出现的数目："+qh.countTag("div"));
        QHtml.NodeArray na = qh.getTag("div");
        System.out.println("它们分别是");
        for(int i = 0;i<na.size();i++){
	        QHtml.Node n = na.get(i);
	        System.out.println(n);
	        System.out.println("class属性的值为:" + n.getValue("class"));
        }

        System.out.println("a标签出现的数目："+qh.countTag("a"));
        QHtml.NodeArray na1 = qh.getTag("a");
        System.out.println("它们分别是");
        for(int i = 0;i<na.size();i++){
	        QHtml.Node n = na1.get(i);
	        System.out.println(n);
	        System.out.println("href属性的值为:" +n.getValue("href"));
        }
        
        String str = qh.getContent();
        QHtml.Html h = QHtml.buildHtml(str);
        System.out.println("字符串构建后");
        System.out.println(h);
	}
	
	public static void main(String[] args) throws Exception{
//
		System.out.println("测试QJson类……");
		textQJson();
		
		System.out.println("测试QCSV类");
		textQCSV();
		System.out.println("测试QHtml");
		testQHtml();
//		
		
		System.out.println("测试QTxt");
		textTxt();
	}

}
