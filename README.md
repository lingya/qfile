#qfile简介

qfile是专为懒人和新手而设的java程序包，它封装一些对不同格式文件进行二次处理的一些方法。


###为懒人而设
 qfile封装的方法是比较常用的，对文件进行二次处理的方法。

要知道在java中调用文件IO有关的类已经足够繁琐，获得文件内容后再进行二次处理岂不是更烦？

例如我需要批量代替掉一个文本中的某些字符串，

+ 首先，我们要实例化一个FileReader，必要时要使用BufferedReader
+ 读取字符串
+ 关闭之前创建的对象
+ 替代
+ 实例化FileWriter，必要时使用BufferedWriter
+ 写入
+ flush
+ 关闭

是不是想想就很不爽？

然而现在，你只需要动动手指，**调用一个api**就可以搞掂啦。qflie让你对文件的操作更轻松，
**把剩下来的时间去和妹子玩耍**。

###为新手而设
- qfile的本质上并没有什么黑科技，只是集中封装了一些对文件二次操作的一些方法，
所以源码简单，**对于有牢固java基础想改进qfile的源码的新手来说是友好的**。

- api设计经过多次本人与朋友的多番讨论，**应该不会反人类**，同时使用只需```import```语句而不需要配置一大堆，
这对想使用qfile的新手来说也是友好的。

- 目前qfile能够对**txt，json，csv,
html文件**进行简单处理(我知道json不是文件格式，这个设计是有原因的，下文再说)，
知识面跨度比较广，这对有一定基础，想要更进一步学习其他知识的新人也是友好的


#qfile的由来

java虽然有很多优点，但其语法臃肿繁琐一直被人吐槽，作为用惯了python的我来说，
更是不可忍。

于是便打算尝试封装一下一些平常写起来繁琐但又不可避免的代码，
再加上我正有尝试自己写一个库的意向，方便自己，也方便别人，然后qfile便应运而生。


#qfile的设计风格
可以由名字看出qfile是由q和file组成。

- file主要指的这是针对文件相关的操作，
- q则主要指轻便的，简单的。

**轻便**在于它不依赖与第三方库，例如在html的解析，和json的解析上java其实有丰富成熟的第三方库，
但为了使其轻便，我在参考了第三方包功能的实现的同时，自己做了一些改动，保持了轻便性。

**简单**在于我在保持轻便的时候，自然**免不得要对其功能进行取舍**，保留最基本最常用的操作，
例如在关于html和json的处理上，我的实现方式与其他的第三方库的实现不同，qfile只保留了简单常用的操作，
**如果你需要更高的要求，请使用其他第三方的库。**

####关于json处理的问题

我知道json不属于文件格式，属于一种约定成俗的数据格式，而鉴于sublime-text用了json来写配置文件，
而我自己也认为用json写配置文件有一定方便的地方，所以便把json也当成了一个文件类型来处理。
而同时我也留有api可以用作字符串解析成json对象，请参考文档或下文的示例。





#qfile的项目结构

- qfile
 - QTxt 用于处理txt格式文件的类，提供对txt文件进行重写，批量替代，批量统计，加密，解密等api
 - QCSV 用于处理csv格式文件的类，提供获取数据矩阵，重写该文件等api，
   - Matrix 内部类，用于数据的矩阵运算 
 - QHtml 用于处理html格式文件的类，提供统计字符，统计html标签，重写文件，根据名称，属性获取节点等api
   - NodeArray 内部类， 用于装载节点，提供获取数组长度，元素等api
   - Node 内部类，一个反映节点的类，提供获取节点名称，节点属性，属性值等api
 - QJson 用于处理json文件的类，提供重写，获取JSONObject，根据字符串构建JSONObject等api
   - JSONObject 用于处理json格式中{}所包括的数据的内部类，提供根据键值获取数据的api
   - JSONArray 用于处理json格式中[]所包括的数据的内部类，提供根据键值获取数据的api
 - QException 使用过程中可能会触发的异常
 - Encryption 加密类，提供让字符串加密的静态方法，QTxt的加密正是它完成

#qfile的代码示例
使用qfile时，需要在IDE中把qfile.jar导入工程中，若是用编辑器编写,
则需要在编译的时候用-cp参数指定路径。



- 使用QTxt读取text.txt的内容，并进行其他操作

       import java.utl.*;
       import qfile.*;
       
       public class Test {
       //123.txt的内容
       //I am happy
       //I am happy
       //I am happy
       
        public static void main(String[] args) throws Exception{
	        String path = "123.txt";
	        qfile.QTxt qt = new qfile.QTxt(path);
          
          System.out.println(文章内容:);
	      System.out.println(qt.getContent()); 
          //获取文章内容 
          
          System.out.println("happy出现的次数："+qt.countWord("happy"));
          //单个统计字符串出现次数
          
          Map<String,String> m = new HashMap<String,String>();
	      m.put("I", "you");m.put("am", "are");m.put("happy", "sad");
          System.out.println(替换后的字符串:)
	      System.out.println(qt.replaceContent(m));
          //批量替换，但只是返回被替换后的字符串，并不会修改替换的内容
          
	      qt.writeByReplacedContent(m);
          //批量替换后，会更改对象属性并修改文件内容
          System.out.println("替换后文件的内容：")
    	  System.out.println(qt.getContent());
        }
       }
       
       /*************Out Put*****************************
       文章内容:
       I am happy
       I am happy
       I am happy 
       happy出现的次数:3
       替换后的字符串:
       you are sad
       you are sad
       you are sad
       替换后文件的内容：
       you are sad
       you are sad
       you are sad
       **************************************************/
       
<br>

- 使用QJson解析json

       import qfile.*;
       
       public class Test{
       //123.json的内容
       //   {
       //        "long":123,
       //        "double":456.4,
       //        "String":"this is str",
       //        "boolean":true,
       //        "array":[1,2,3],
       //    "obj":{"str":"this is str in obj"},
       //   }
         public static void main(String[] avgs){
         
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
    		 System.out.println("long类型 --" + jo.getLong("long"));
    		 System.out.println("double类型 --" + jo.getDouble("double"));
    		 System.out.println("array类型 --" + jo.getJSONArray("array"));
    		 System.out.println("obj类型 --" + jo.getJSONObject("obj")); 
             
            String str="{\"abc\":123}";
             QJson.JSONObject jo2 = QJson.buildJSONObject(str);
             System.out.println(jo2);
             //允许以字符串形式的方式获取一个内部类的实例
          }
       }
    
       /*****************Out Put********************
       内容：
       {"long":123,"double":456.4,"String":"this is str","boolean":true,"array":[1,2,3],"obj":{"str":"this is str in obj"},}
       直接输出该对象信息:
       QJson.JSONObject{"boolean":true,"array":QJson.JSONArray[1,2,3],"double":456.4,"obj":QJson.JSONObject{"str":"this is str in obj"},"String":"this is str","long":123}
       该对象有如下键值:
       boolean
       array
       double
       obj
       String
       long
       根据键值获取数据
       boolean类型 --true
       String类型 --this is str
       long类型或int类型 --123
       double类型或float类型  --456.4
       array类型 --QJson.JSONArray[1,2,3]
       obj类型 --QJson.JSONObject{"str":"this is str in obj"}
       QJson.JSONObject{"abc":123}
       *****************************************************/


<br>

- 使用QHtml解析html

        import qfile.*
        
        public class Test{
        /**123.html的内容
        <html>
        <body>
        <title>测试</title>
        <div class="></div>
        <div class="123" style="background">123</div>
        <div class="123">123</div>
        <div class="123">123</div>
        <a href="123.html">123.html</a>
        <a href="123.html">123.html</a>
        <a href="123.html">123.html</a>
        <a href="123.html">123.html</a>
        </body>
        </html>
        **/
            public static void main(String[] avgs){
            
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
			        System.out.println("href属性的值为:" +(n.getValue("href"));
		        }
            
                String str = qh.getContent();
                QHtml.Html h = QHtml.buildHtml(str);
                System.out.println("字符串构建后");
                System.out.println(h);
                //支持字符串构建
            }
        }
        /***********************OutPut
        html的题目:测试
        html内容:<html>
        <body>
        <title>测试</title>
        <div></div>
        <div class="123" style="background-color:red">123</div>
        <div class="123">123</div>
        <div class="123">123</div>
        <a href="123.html">123.html</a>
        <a href="123.html">123.html</a>
        <a href="123.html">123.html</a>
        <a href="123.html">123.html</a>
        </body>
        </html>
        
        div标签出现的数目：4
        它们分别是
        <div style="background-color:red" class="123">
        class属性的值为:123
        <div class="123">
        class属性的值为:123
        <div class="123">
        class属性的值为:123
        <div>
        class属性的值为:null
        a标签出现的数目：4
        它们分别是
        <a href="123.html">
        href属性的值为:123.html
        <a href="123.html">
        href属性的值为:123.html
        <a href="123.html">
        href属性的值为:123.html
        <a href="123.html">
        href属性的值为:123.html
        System.out.println("字符串构建后");
        <html>
        <body>
        <title>测试</title>
        <div></div>
        <div class="123" style="background-color:red">123</div>
        <div class="123">123</div>
        <div class="123">123</div>
        <a href="123.html">123.html</a>
        <a href="123.html">123.html</a>
        <a href="123.html">123.html</a>
        <a href="123.html">123.html</a>
        </body>
        </html>
        *************************************/
 
<br>

- 利用QCSV处理csv文档

        impot qfile.*;
        public class Test{
        /**123.csv内容
        姓名，班级，分数
        小明，一班，75
        小红，二班，45
        小黄，三班，69
        小李，一班，80
        ***************/
            public static void main(String[] avgs){
            String path = "123.csv";
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
		        
                System.out.println("按\"一班\"分类的子矩阵:");
		        System.out.println(qm.classfiy(1,"一班" ));
                
            }
        }
        /*************************Out Put
        内容：
        姓名,班级,分数，
        小明,一班,75，
        小红,二班,45，
        小黄,三班,69，
        小李,一班,80，
        数据行数： 5
        矩阵形式：
        [[姓名,班级,分数]，
        [小明,一班,75]，
        [小红,二班,45]，
        [小黄,三班,69]，
        [小李,一班,80]，]
        只提取数字部分的子矩阵
        [[75]，
        [45]，
        [69]，
        [80]，]
        竖轴求和是：269
        竖轴求平均是：67.25
        按"一班"分类的子矩阵:
        [[小明,一班,75]，
        [小李,一班,80]，]
        *********************************/


**以上是qfile使用的一部分例子，更多的api信息请查看[帮助文档](doc/index.html)**


#qfile遵循协议
Apache Licence

由于个人实力原因，qfile还有很多不足的地方，希望各位指点。