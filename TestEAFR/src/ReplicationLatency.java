import java.io.*;
import java.net.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.regex.*;
import java.util.stream.LongStream;

import org.apache.commons.io.IOUtils;
import org.apache.hadoop.fs.*;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hdfs.DistributedFileSystem;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.util.ToolRunner;
import org.apache.commons.logging.LogFactory;

import java.util.Calendar;
public class ReplicationLatency {
	
	public static long onemb=(long) Math.pow(2, 20);
	public static HashMap<String,Long> fileonemb= new HashMap<String,Long>();
	public static  HashMap<String,Long> filetenmb= new  HashMap<String,Long>();
	public static HashMap<String,Long> filetonmb= new  HashMap<String,Long>();
	public static  HashMap<String,Long>filekmb= new  HashMap<String,Long>();
	public static  HashMap<String,Long> filetenthoumb= new HashMap<String,Long>();
	public static ArrayList<Long> replatencyonemb= new ArrayList<Long>();
	public static  ArrayList<Long> replatencytenmb= new ArrayList<Long>();
	public static  ArrayList<Long> replatencytonmb= new ArrayList<Long>();;
	public static  ArrayList<Long> replatencykmb= new ArrayList<Long>();
	public static  ArrayList<Long> replatencytenthoumb= new ArrayList<Long>();
	public static ArrayList<Long> avglatency= new ArrayList<Long>();
	
	
	public static void groupFiles() throws IOException, URISyntaxException{
		Configuration conf= new Configuration();
		Path hdfsFilePath= new Path("hdfs://128.143.136.181:8020/");
		
		DistributedFileSystem dfs1=(DistributedFileSystem)FileSystem.get(new URI("hdfs://128.143.136.181:8020/"),conf);
		ArrayList<String> fileList=AccessLog.getAllFilePathInHDFS(dfs1, hdfsFilePath.toString());

		ArrayList<Long> fileSize= new ArrayList<Long>();
		
		
		for (int i=0;i<fileList.size();i++){
			String path=fileList.get(i);
			Long elem=dfs1.getFileStatus(new Path(path)).getLen();
			//System.out.println(elem);
			fileSize.add(elem);
			if (elem==onemb){
				fileonemb.put(path,elem);
			}
			if (elem==10*onemb){
				filetenmb.put(path,elem);
			}
			if (elem==100*onemb){
				filetonmb.put(path,elem);
			}
			if (elem==1000*onemb){
				filekmb.put(path,elem);
			}
		}
	    long sum=0;
		for (String key:fileonemb.keySet()){
			
			long start=System.nanoTime();
			 System.out.println(start);
			String elem=key;
		   short replication=3;
		   dfs1.setReplication(new Path(elem), replication);
		   long end=System.nanoTime();
		   long diff=end-start;
		   System.out.println(end);
           replatencyonemb.add(diff);
           sum+=diff;
           System.out.println(sum);
		   }
		

		avglatency.add(sum/replatencyonemb.size());
		long sum2=0;
		for (String key:filetenmb.keySet()){
		 
		   long start=System.nanoTime();
		   System.out.println(start);
			String elem=key;
		   short replication=3;
		   dfs1.setReplication(new Path(elem), replication);
		   long end=System.nanoTime();
		   System.out.println(end);
		   long diff=end-start;
		  
           replatencytenmb.add(diff);
           sum2+=diff;
           System.out.println(sum);
		   }
		
        System.out.println(replatencytenmb.size());
		avglatency.add(sum2/replatencytenmb.size());
		long sum3=0;
		for (String key:filetonmb.keySet()){
			long start=System.nanoTime();
			 System.out.println(start);
			String elem=key;
		   short replication=3;
		   dfs1.setReplication(new Path(elem), replication);
		   long end=System.nanoTime();
		   System.out.println(end);
		   long diff=end-start;
		  
           replatencytonmb.add(diff);
           sum3+=diff;
           System.out.println(sum);
		   }
		

		avglatency.add(sum3/replatencytonmb.size());
		
		
		
		long sum4=0;
		for (String key:filekmb.keySet()){
			long start=System.nanoTime();
			String elem=key;
		   short replication=3;
		   dfs1.setReplication(new Path(elem), replication);
		   long end=System.nanoTime();
           long diff= end-start;
           replatencykmb.add(diff);
           sum4+=diff;
           System.out.println(sum);
		   }
		

		avglatency.add(sum4/replatencykmb.size());
		System.out.println(avglatency);
		/*long sum5=0;
		for (String key:filetenthoumb.keySet()){
			long start=System.currentTimeMillis();
			String elem=key;
		   short replication=3;
		   dfs1.setReplication(new Path(elem), replication);
		   long end=System.currentTimeMillis();
           long diff=end-start;
           replatencytenthoumb.add(diff);
           sum5+=diff;
           System.out.println(replatencytonmb.add(end-start));
		   }
		

		avglatency.add(sum5/replatencytenthoumb.size());*/
		
		System.out.println("Average replication latency sequentially:"+avglatency);
		
		
		
		
	}

	public static void main (String argv[]) throws IOException, URISyntaxException{
		groupFiles();
	}

}
