import java.io.*;
import java.net.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.regex.*;

import org.apache.commons.io.IOUtils;
import org.apache.hadoop.fs.*;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hdfs.DistributedFileSystem;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.util.ToolRunner;

public class ReadLatency {
	
	//public static int concurrentReadRequest;
	
	public static void getSomeRandomFiles(int concurrentReadRequest) throws IOException, URISyntaxException, InterruptedException, ExecutionException{
	
	System.out.println("a");
	Configuration conf= new Configuration();
	Path hdfsFilePath= new Path("hdfs://128.143.136.181:8020/testfiles/");
	String hdfsfilepath="hdfs://128.143.136.181:8020/testfiles/";
	DistributedFileSystem dfs=(DistributedFileSystem)FileSystem.get(new URI("hdfs://128.143.136.181:8020/testfiles/"),conf);
	ArrayList<String> fileList= AccessLog.getAllFilePathInHDFS(dfs,hdfsfilepath);
	
	ArrayList <String> randomFiles= new ArrayList<String> ();
	Random randomGen= new Random();
	
	for (int i=0; i<20;i++){
		
		int index=randomGen.nextInt(fileList.size());
		String file= fileList.get(index);
		randomFiles.add(file);
		
  }
	System.out.println(randomFiles);
	
	ExecutorService executor= Executors.newFixedThreadPool(concurrentReadRequest);
	CompletionService<readTimes> compservice= new ExecutorCompletionService<>(executor);
	
	for (int j=0;j<randomFiles.size();j++){
		File file= new File(randomFiles.get(j));
		String filepath=randomFiles.get(j);
		//readFiles(dfs,randomFiles.get(j),file.length());
    for (int i=0; i<concurrentReadRequest+1;i++){
        Task task= new Task(dfs,filepath,file.length());
		compservice.submit(task);
		
	}
	     Future<readTimes> future= compservice.take();
	     System.out.println("Time Out:"+future.get().timeoutcount);
	     System.out.println("Total read Time:"+future.get().timing);
	}
	executor.shutdown();
	
}

public  static class Task extends ReadLatency implements Callable<readTimes> {
	   public  Task (DistributedFileSystem dfs,String randomFiles,long filelength){
		  filePath=randomFiles;
		  filesize=filelength;
		  dfs1=dfs;
	  }
	  @Override public readTimes call () throws Exception{
		 
     return readFiles(dfs1,filePath,filesize);
	  }
	  
	  private String filePath;
	  private long filesize;
	  DistributedFileSystem dfs1;
}
private static readTimes readFiles(DistributedFileSystem dfs,String filePath, long filesize) throws IOException{
	readTimes result= new readTimes();
	Path inFile= new Path(filePath);
	FSDataInputStream fin=dfs.open(inFile);
	byte[] buffer = new byte[8192];
	byte[] buf=new byte[1];
	long start= System.currentTimeMillis();

	if (filesize<buffer.length){
		  
		try{
				fin.read(buf,0,buf.length);	
			//long end=System.currentTimeMillis();	
			//result.timing=end-start;
			}
			catch (IOException e){
				e.printStackTrace();
				
			}
			}
	else{
		
     try{
			fin.read(buffer, 0, (int) buffer.length);	
			//long end=System.currentTimeMillis();
			//result.timing=end-start;
		}
		catch (IOException e){
			e.printStackTrace();
			
		}
		}
	long end=System.currentTimeMillis();
	result.timing=end-start;

	
	if (result.timing>10){
		result.timeoutcount=result.timeoutcount+1;
	}
	return result;
	}
	
 private static  class readTimes{
	 long timing;
	 int  timeoutcount=0;
	
	 }
	 
 
public static void main (String[] argv) throws Exception{
	
	System.out.println("Enter concurrent read rquest number:");
	   Scanner reader= new Scanner(System.in);
	   int concurrentReadRequest=reader.nextInt();
	   ReadLatency.getSomeRandomFiles(concurrentReadRequest);
	   reader.close();
	   }
}
