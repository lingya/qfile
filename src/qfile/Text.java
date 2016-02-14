package qfile;
import java.util.*;





public class Text {

	static void textTxt() throws Exception{
		String path = "C:\\Users\\zq001\\Desktop\\��Ŀ��ʵ��\\���鳡\\123.txt";
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
		String path = "C:\\Users\\zq001\\Desktop\\��Ŀ��ʵ��\\Java��Ŀ\\Init.json";
		QJson qj = new QJson(path);
   	 System.out.println("���ݣ�");
		 System.out.println(qj.getJsonStr());
		
		 QJson.JSONObject jo = qj.getJsonObj();
		 System.out.println("ֱ������ö�����Ϣ:" + jo);
		
		 Set<String> s = jo.getKeys();
		 Iterator<String> iter = s.iterator(); 
		 System.out.println("�ö��������¼�ֵ:");
		 while(iter.hasNext()){
		  	 String key = iter.next();
			 System.out.println(key);
		 }
		
		 System.out.println("���ݼ�ֵ��ȡ����");
		 System.out.println("boolean���� --" + jo.getBoolean("boolean"));
		 System.out.println("String���� --" + jo.getString("String"));
		 System.out.println("long���ͻ�int���� --" + jo.getLong("long"));
		 System.out.println("double���ͻ�float���� --" + jo.getDouble("double"));
		 System.out.println("array���� --" + jo.getJSONArray("array"));
		 System.out.println("obj���� --" + jo.getJSONObject("obj"));   
		 
		 String str="{\"abc\":123}";
         QJson.JSONObject jo2 = QJson.buildJSONObject(str);
         System.out.println(jo2);
		
//		QJson.JSONArray ja = jo.getJSONArray("array");
//		int length = ja.size();
//		System.out.println("���鳤��" + length);
//		for(int i = 0;i<length;i++){
//			System.out.println(i+"--"+jo.getLong(i));
//		}
		
//		System.out.println(jo.getString("str"));
	}
	
	static void textQCSV() throws Exception{
		String path ="C:\\Users\\zq001\\Desktop\\��Ŀ��ʵ��\\���鳡\\123.csv";
		QCSV qc = new QCSV(path);
		System.out.println("���ݣ�");
		System.out.println(qc.getContent());
		System.out.println("���������� "+ qc.getLine());
		System.out.println("������ʽ��");
		QCSV.Matrix  qm = qc.getMat();
		System.out.println(qm);
		System.out.println("ֻ��ȡ���ֲ��ֵ��Ӿ���");
		QCSV.Matrix qm2 = qm.getDataToMatrix(1, 2, qc.getLine()-1, 2);
		System.out.println(qm2);
		System.out.println("��������ǣ�" + qm2.sumYToInt(0));
		System.out.println("������ƽ���ǣ�" + qm2.averYToDouble(0));
		System.out.println("��\"һ��\"����:");
		System.out.println(qm.classfiy(1,"һ��" ));
//		System.out.println("x:"+qm.getXAxis());
//		System.out.println("y:"+qm.getYAxis());
//		QCSV.Matrix qm2 = qm.getDataToMatrix(0, 3, 7, 5);
//		QCSV.Matrix qm3 = qm.getDataToMatrix(8, 3, 15, 5);
//		System.out.println("qm2:\n"+qm2);
//		System.out.println("qm3:\n"+qm3);
//		
//		System.out.println("QM2ƽ����\n " + qm2.powMatrixWithDouble(2));
//		qc.writeCSV(qm2);
//		System.out.println("��д��");
//		System.out.println(qc.getMat());
//		System.out.println(qm2.getAllDataToStr());
//		System.out.println(qm2.mulYToDouble(1));
//		System.out.println(qm2.sumYMatWithDouble());

	}
	

	
	static void testQHtml() throws QException{
		String path = "C:\\Users\\zq001\\Desktop\\��Ŀ��ʵ��\\Java��Ŀ\\123.html";
		QHtml qh = new QHtml(path);
		System.out.println("html����Ŀ:"+qh.getTitle());
        
        System.out.println("html����:"+qh.getContent());
        
        System.out.println("div��ǩ���ֵ���Ŀ��"+qh.countTag("div"));
        QHtml.NodeArray na = qh.getTag("div");
        System.out.println("���Ƿֱ���");
        for(int i = 0;i<na.size();i++){
	        QHtml.Node n = na.get(i);
	        System.out.println(n);
	        System.out.println("class���Ե�ֵΪ:" + n.getValue("class"));
        }

        System.out.println("a��ǩ���ֵ���Ŀ��"+qh.countTag("a"));
        QHtml.NodeArray na1 = qh.getTag("a");
        System.out.println("���Ƿֱ���");
        for(int i = 0;i<na.size();i++){
	        QHtml.Node n = na1.get(i);
	        System.out.println(n);
	        System.out.println("href���Ե�ֵΪ:" +n.getValue("href"));
        }
        
        String str = qh.getContent();
        QHtml.Html h = QHtml.buildHtml(str);
        System.out.println("�ַ���������");
        System.out.println(h);
	}
	
	public static void main(String[] args) throws Exception{
//
		System.out.println("����QJson�࡭��");
		textQJson();
		
		System.out.println("����QCSV��");
		textQCSV();
		System.out.println("����QHtml");
		testQHtml();
//		
		
		System.out.println("����QTxt");
		textTxt();
	}

}
