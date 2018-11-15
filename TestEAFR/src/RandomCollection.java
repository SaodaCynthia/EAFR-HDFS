import java.io.*;
import java.net.URI;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.regex.*;
import java.util.Map.Entry;
import java.util.*;
import java.lang.*;

import org.apache.commons.io.IOUtils;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hdfs.DistributedFileSystem;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FSDataOutputStream;

/*public class Replica {
	public static void main (String[] argv ) throws Exception{
		if (argv.length !=2){
			System.out.println("not enough input!!");
			System.exit(-1);
		}
		String command=argv[0];
		String filePath=argv[1];
		Configuration conf=new Configuration
	}
}
*/
//public class MyThreadPoolExecutor  {
	//public static void main(String[] args) throws IOException{

//SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
/*String Cal= Calendar.getInstance().getTime().toString();
Calendar cal= Calendar.getInstance();
 //Date start=sdf.parse(Cal);
long d1=cal.getTime();
System.out.println(d1);
cal.add(Calendar.HOUR,-5); 
String startTime = "10:00";
String endTime = "12:00";

Date d3= sdf.parse(startTime);
Date d2 = sdf.parse(endTime);
//System.out.println(d1);
long elapsed = d3.getTime() ;
System.out.println(elapsed);  */

//Date date= new Date();
//System.out.println(sdf.format(date));

//long currenttime= date.getTime();
//System.out.println(currenttime);
//nt timeSpan=4*60*3600;
//long sec= 1235105986000L;
//Date date2 = new Date(sec);
//System.out.println(date2);
//long microsec= 274441;
//long lasttime=currenttime-timeSpan;
//System.out.println(lasttime); 


//String abc="2018-01-08 16:29:56,533 INFO FSNamesystem.audit: allowed=true	ugi=ns8nf (auth:SIMPLE)	ip=/128.143.136.180	cmd=setReplication	src=/all/gutenberg/20417-8.txt	dst=null	perm=null	proto=rpc 2018-01-08 16:29:56,534 INFO FSNamesystem.audit: allowed=true	ugi=ns8nf (auth:SIMPLE)	ip=/128.143.136.180	cmd=setReplication	src=/all/gutenberg/4300-0.txt	dst=null	perm=null	proto=rpc 2018-01-08 16:05:56,529 INFO FSNamesystem.audit: allowed=true	ugi=ns8nf (auth:SIMPLE)	ip=/128.143.136.180	cmd=setReplication	src=/Write/EAFR.pdf	dst=null	perm=null	proto=rpc  2018-01-08 16:40:00,034 INFO FSNamesystem.audit: allowed=true	ugi=ns8nf (auth:SIMPLE)	ip=/128.143.136.180	cmd=setReplication	src=/all/Write/EAFR.pdf	dst=null	perm=null	proto=rpc";

//String bc="src=/gutenberg";
//String ac=bc.substring(4);
//System.out.println(ac);


 
//String [] pat= new String[] { "src=/Write/EAFR.pdf","src=/all/Write/EAFR.pdf" ,"xcddcd","src=/gutenberg/20417-8.txt"
		                                   // ,  "cs" ,"kkkiikiki", "src=/gutenberg/4300-0.txt", };
///Pattern pattern= Pattern.compile(String.join("|", pat));
//Matcher matcher=pattern.matcher(abc);
//System.out.println(pattern);

/*ArrayList<String> Patterns= new ArrayList<String> ();
for (String log:pat){
	Patterns.add(log);
}*/
//System.out.println(Patterns);

//String strr="\\d{4}\\-(0?[1-9]|1[012])\\-(0?[1-9]|[12][0-9]|3[0-1])*\\s(([0-1]?[0-9]|2[0-3]):([0-5][0-9])(:[0-5][0-9]))";
//String time="([0-1]?[0-9]|2[0-3]):([0-5][0-9])(:[0-5][0-9])"
//String file="/gutenberg/4300-0.txt";
//String equal="=";
//String space=" ";
//String oop="src="+file+" ";
//System.out.println(oop);
//Pattern patterna= Pattern.compile(strr);
//Pattern patternb=Pattern.compile(oop);
/* ArrayList<Pattern> compiledpatterns= new ArrayList<Pattern>();
int count=0;
for (String i:pat){
	compiledpatterns.add(Pattern.compile(i));
}*/
//System.out.println(compiledpatterns);
//System.out.println(compiledpatterns.size());

//Matcher m=patterna.matcher(abc);
//System.out.println(m);
//Matcher n=patternb.matcher(str);
//for (int i=0; i<compiledpatterns.size(); i++){
//if (i==2){	
//System.out.println(compiledpatterns.get(i));
//}

//Pattern element=compiledpatterns.get(i);
//System.out.println(element);


//Matcher fileLocation=element.matcher(abc);
/*for (int i=0; i<compiledpatterns.size();i++) {
Pattern pattern=compiledpatterns.get(i);
Matcher matcher=pattern.matcher(abc);
while (matcher.find() && m.find()) {
	count=count+1;
	String date1=m.group(0);

	//long time=(sdf.parse(date1)).getTime(); 
	//System.out.println(m.group(0)); 
	//System.out.println(matcher.group(0));
	//System.out.println(matcher.groupCount());
	//System.out.println(n.groupCount());
	//System.out.println(time);
	}
}*/



	
//System.out.println(count);
//double meg= Math.pow(2, 10);
//System.out.println(meg);

//byte[] buf = new byte[8192];
//ong n = Long.parseLong("102456");
//FileOutputStream fos = new FileOutputStream("rough.bin");
//ong m1 = n / buf.length;
/*for (int i=0; i<2;i++){
String dir="/zf18/ns8nf/Work/";
String filename="name"+i+".txt";	
File file= new File(dir+filename);
FileOutputStream fos = new FileOutputStream(file);
 for (long i1 = 0; i1 < m1; i1++) {
    try {
		fos.write(buf, 0, buf.length);
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}
 fos.write(buf, 0, (int)(n % buf.length));
 fos.close();
 }*/
//String a="some arbitrary string";
//int b=a.getBytes().length;
//int meg1=1024*1024;
//float cou=meg1/b;
//System.out.println(cou);
//System.out.println(b);
//String tracefilePath="/zf18/ns8nf/Work/cth.traces.sanitized.txt";
//BufferedReader br= new BufferedReader(new FileReader(tracefilePath));
/*String ab="tracetype(ENTER)time:(1235106190)time:(4148)str(iwritev)fd(3)ziovec(base(0x34E5D9B0)len(10444))";
String sub=ab.substring(22, 32);
String z=ab.substring(44,45);
if(Character.isDigit(z.charAt(0))){
	//System.out.println("match");
}
//System.out.println(z);
int su=Integer.parseInt(sub);
String sub2=ab.substring(39, 43);
int su2=Integer.parseInt(sub2);


//System.out.println(su2);
double time=(double) (su+Math.pow(su2, -6));
double time1=(Math.pow(10, -6));
//double val=time2-time;
//double lol=val*1000;
//System.out.println(time1);
//System.out.println(sub2);
//System.out.println(su);
//System.out.println(ab.length());
String lengthFormat="[(]iwritev[)]";
String lenghtend="ENTER";
Pattern lengthPattern=Pattern.compile(lengthFormat);
Pattern lengthend2=Pattern.compile(lenghtend);
Matcher lenMatcher=lengthPattern.matcher(ab);
Matcher enter=lengthend2.matcher(ab);
while (lenMatcher.find() && enter.find()){
	//2System.out.println(enter.group(0));
}
//System.out.println("Enter concurrent read rquest number:");
Scanner reader= new Scanner(System.in);
int concurrentReadRequest=reader.nextInt();
//System.out.println(concurrentReadRequest);
String line="";
ArrayList <Integer> fileSize= new ArrayList<Integer>();
int count1=0;*/

/*ArrayList<Integer> a1 = new ArrayList<Integer>();

for (int i=0;i<10;i++){
	a1.add(i); 
}
System.out.println(a1);

ArrayList<Integer> b1=new ArrayList<Integer>();

for (int i=0;i<5;i++){
	b1.add(i); 
}

a1.addAll(b1);
System.out.println(a1);
System.out.println(b1);


/*while ((line=br.readLine())!=null){
	
	Matcher lenMatcher=lengthPattern.matcher(line);
	Matcher end=lengthend2.matcher(line);
	while (lenMatcher.find() && end.find() ){
	//System.out.println(lenMatcher.start());
	String n1=line.substring(lenMatcher.start()+4,line.length()-2);
	int x=Integer.parseInt(n1);
	//System.out.println(x);
     fileSize.add(x);
	}
	  count1=count1+1;

	
	   }
System.out.println(count1);*/

/*Path inFile= new Path("zf18/ns8nf/Work/patents.txt");
BufferedReader br1= new BufferedReader(new FileReader(tracefilePath));

byte[] buffer = new byte[8192];


	
 try{
		br1.readLine();	
	}
	catch (IOException e){
		e.printStackTrace();
		
	}
	}
}*/
	   
/*int poolSize = 5;

int maxPoolSize = 5;

long keepAliveTime = 5;

ThreadPoolExecutor threadPool = null;

final ArrayBlockingQueue<Runnable> queue = new ArrayBlockingQueue<Runnable>(
        5);

public MyThreadPoolExecutor()
{
    threadPool = new ThreadPoolExecutor(poolSize, maxPoolSize,
            keepAliveTime, TimeUnit.SECONDS, queue);

}

public void runTask(Runnable task)
{
    // System.out.println("Task count.."+threadPool.getTaskCount() );
    // System.out.println("Queue Size before assigning the
    // task.."+queue.size() );
    threadPool.execute(task);
    // System.out.println("Queue Size after assigning the
    // task.."+queue.size() );
    // System.out.println("Pool Size after assigning the
    // task.."+threadPool.getActiveCount() );
    // System.out.println("Task count.."+threadPool.getTaskCount() );
    System.out.println("Task count.." + queue.size());

}

public void shutDown()
{
    threadPool.shutdown();
}

public static void main(String args[])
{
    MyThreadPoolExecutor mtpe = new MyThreadPoolExecutor();
    // start first one
    mtpe.runTask(new Runnable()   

	
	
	
	
{
        public void run()
        {
    for (int i = 0; i < 10; i++)
    {
        try
        {
            System.out.println("First Task");
            Thread.sleep(1000);
        } catch (InterruptedException ie)
        {
        }
    }
}
});
// start second one
/*
* try{ Thread.sleep(500); }catch(InterruptedException
* ie){}
*/
/*mtpe.runTask(new Runnable()
{
public void run()
{
    for (int i = 0; i < 10; i++)
    {
        try
        {
            System.out.println("Second Task");
            Thread.sleep(1000);
        } catch (InterruptedException ie)
        {
        }
    }
}
});
// start third one
/*
* try{ Thread.sleep(500); }catch(InterruptedException
* ie){}
*/
/*mtpe.runTask(new Runnable()
{
public void run()
{
    for (int i = 0; i < 10; i++)
    {
        try
        {
            System.out.println("Third Task");
            Thread.sleep(1000);
        } catch (InterruptedException ie)
        {
        }
    }
}
});
// start fourth one
/*
* try{ Thread.sleep(500); }catch(InterruptedException
* ie){}
*/
/*mtpe.runTask(new Runnable()
{
public void run()
{
    for (int i = 0; i < 10; i++)
    {
        try
        {
            System.out.println("Fourth Task");
            Thread.sleep(1000);
        } catch (InterruptedException ie)
        {
        }
    }
}
});
// start fifth one
/*
* try{ Thread.sleep(500); }catch(InterruptedException
* ie){}
*/
/*mtpe.runTask(new Runnable()
{
public void run()
{
    for (int i = 0; i < 10; i++)
    {
        try
        {
            System.out.println("Fifth Task");
            Thread.sleep(1000);
        } catch (InterruptedException ie)
        {
        }
    }
}
});
// start Sixth one
/*
* try{ Thread.sleep(500); }catch(InterruptedException
* ie){}
*/
/*mtpe.runTask(new Runnable()
{
public void run()
{
    for (int i = 0; i < 10; i++)
    {
        try
        {
            System.out.println("Sixth Task");
            Thread.sleep(1000);
        } catch (InterruptedException ie)
        {
        }
    }
}
});*/
//mtpe.shutDown();

/*public static void main(String[] args) throws IOException{
	
	
	
	FileReader f1 = new FileReader("/zf18/ns8nf/Work/part-r-00000");
	BufferedReader file = new BufferedReader(f1);

	String text;
	int i=0;
	while(file.readLine()!= null)
	{
	text=file.readLine();

	i++;

	System.out.println(text);	
	}
	System.out.println("\n The value of I is: "+i);
	file.close();*/
	
/*
 HashMap<String, Integer> abc= new HashMap<String,Integer> ();
	int i=0;
	int value=0;
	abc.put("/tmp/cynthia", value);
	while (i<5){
		//if (i%3==0){
		//abc.put("/tmp/cynthia", abc.get("/tmp/cynthia")+1);
		//abc.put("/tmp/cynthia",  abc.get("/tmp/cynthia")+1);
		//System.out.println(abc);
		 i++;
	}
	//System.out.println(abc);
//HashSet<String>  a= new HashSet<String>();
	
	//a.add("/a/d/c/");
	//a.add("n/m/m");
	
	//System.out.println(a); 
	 */


 //BufferedReader br= new BufferedReader(new InputStreamReader(dfs2.open(new Path (logFilePath.toString()))));
	

	/* 		/*ArrayList <byte[]> records1= new ArrayList<byte[]>();
		ArrayList <byte[]> records2= new ArrayList<byte[]>();
		ArrayList <byte[]> records3= new ArrayList<byte[]>();
		ArrayList <byte[]> records4= new ArrayList<byte[]>();
		int recsize=record.getBytes().length;
		byte[] b= record.getBytes(Charset.forName("UTF-8"));
		long recCount[] ;
		recCount= new long[4];
		recCount[i]= recsize/filesize[i];
			System.out.println(recCount[i]);
			for (int j=0; j<recCount[i]; j++){
				if (i==1){
					records1.add(b);
				}
				if (i==2){
					records2.add(b);
				}
				if (i==3){
					records3.add(b);
				}
				if (i==4){
					records4.add(b);
				}
			}
			
		}
			/*for (int i=0;i<200;i++){
			if (i<50){
				filesize.add(filesize1);
			}
			if (i>=50 && i<100)
				filesize.add(filesize2);
			if (i>=100 && i<150){
				filesize.add(filesize3);
			
			}
			if (i>=150 && i<200){
				filesize.add(filesize4);
			}
				
			
			}
		System.out.println(filesize);	
		return filesize;
		
		}
		*/
	
	
	/* 				if (countread==1){
					System.out.println("c");
					String sub1=line.substring(22, 31);
					int su1=Integer.parseInt(sub1);
					if (line.substring(44,45)==")"){
						String sub21=line.substring(39, 44);
						int su21=Integer.parseInt(sub21);
						double timeStart=(double) (su1+Math.pow(su21, -6));
						System.out.println(timeStart);}
						
							String sub21=line.substring(39, 44);
							int su21=Integer.parseInt(sub21);	
							double timeStart=(double) (su1+Math.pow(su21, -6));
							System.out.println(timeStart);
						
				
					}
		 
				if (countread==499){
					System.out.println("d");
					String sub1=line.substring(22, 32);
					int su1=Integer.parseInt(sub1);
					if (line.substring(44,45)==")"){
						String sub21=line.substring(39, 44);
						int su21=Integer.parseInt(sub21);
						double timeStart=(double) (su1+Math.pow(su21, -6));
						System.out.println(timeStart);}
						
							String sub21=line.substring(39, 45);
							int su21=Integer.parseInt(sub21);	
							double timeStart=(double) (su1+Math.pow(su21, -6));
							System.out.println(timeStart);
						
				
					}*/
	
	
public class RandomCollection<E> {
    private final NavigableMap<Double, E> map = new TreeMap<Double, E>();
    private final Random random;
    private double total = 0;

    public RandomCollection() {
        this(new Random());
    }

    public RandomCollection(Random random) {
        this.random = random;
    }

    public RandomCollection<E> add(double weight, E result) {
        if (weight <= 0) return this;
        total += weight;
        map.put(total, result);
        return this;
    }

    public E next() {
        double value2 = random.nextDouble() * total;
        return map.higherEntry(value2).getValue();
    }
    
    public void main(){
    	RandomCollection<Object> rc = new RandomCollection<>()
                .add(40, "dog").add(35, "cat").add(25, "horse");

for (int i = 0; i < 10; i++) {
System.out.println(rc.next());
} 
    	
    }
}
	
	

