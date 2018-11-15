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
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.regex.*;

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





public class ReadFilesFromTrace {
	
	private static class Time{

		    ArrayList<Double> elapsedReadTime= new ArrayList<>();
			ArrayList<Double> elapsedWriteTime= new ArrayList<>();
	}
	
	public static Time  readTrace() throws IOException{
		
		Time call= new Time();
		
	   
	    String tracefilePath="/zf18/ns8nf/Work/cth.traces.sanitized.txt";
		BufferedReader br= new BufferedReader(new FileReader(tracefilePath));
		int countread=0;
	    double prevwritetime=0;
	    double prevreadtime=0;
		String line="";
		String a="[(]ireadv[)]";
		String b="[(]iwritev[)]";
		String c="ENTER";
	    String d="EXIT";
		Pattern read=Pattern.compile(a);
		Pattern write=Pattern.compile(b);
		Pattern enter=Pattern.compile(c);
		//Pattern exit=Pattern.compile(d);
		
		
		while ((line=br.readLine())!=null && countread<1000){
			
			//System.out.println("x");
			Matcher readfile=read.matcher(line);
			Matcher writefile=write.matcher(line);
			Matcher enterfile=enter.matcher(line);
			//Matcher exitfile=exit.matcher(line);
			while (readfile.find() && enterfile.find()){
				//System.out.println("a");
				String sub=line.substring(22, 32);
				int su=Integer.parseInt(sub);
				String z=line.substring(44,45);
				String y=line.substring(43,44);
				if (!(Character.isDigit(z.charAt(0)))){
					
				    String sub2=line.substring(39, 43);
					int su2=Integer.parseInt(sub2);
					double time=(double) (su+su2*(Math.pow(10, -5)));
					double elapsed=time-prevreadtime;
					 if (time==elapsed){
						call.elapsedReadTime.add((double) 0);
					 }else{
					call.elapsedReadTime.add(elapsed);
					}
					prevreadtime=time;
					countread=countread+1;
					}
				else{	
			String sub2=line.substring(39, 44);
				int su2=Integer.parseInt(sub2);
				double time=(double)(su+su2*(Math.pow(10, -5)));
				double elapsed=time-prevreadtime;
				 if (time==elapsed){
					 call.elapsedReadTime.add((double) 0);
				 }else{
				call.elapsedReadTime.add(elapsed);
				}
				prevreadtime=time;
			countread=countread+1;
					
				}
				if (!(Character.isDigit(z.charAt(0))) && !(Character.isDigit(y.charAt(0)))){
					 String sub2=line.substring(39, 43);
						int su2=Integer.parseInt(sub2);
						double time=(double) (su+su2*(Math.pow(10, -5)));
						double elapsed=time-prevwritetime;
						 if (time==elapsed){
							 call.elapsedWriteTime.add((double) 0);
						 }else{
						call.elapsedWriteTime.add(elapsed);
						}
						prevwritetime=time;
						countread=countread+1;
				}
				
			}
			while(writefile.find() && enterfile.find()){
				//System.out.println("b");
				String sub=line.substring(22, 32);
				int su=Integer.parseInt(sub);
				String z=line.substring(44,45);
				String y=line.substring(43,45);
                if (!(Character.isDigit(y.charAt(0)))){
					
				    String sub2=line.substring(39, 43);
					int su2=Integer.parseInt(sub2);
					double time=(double) (su+su2*(Math.pow(10, -5)));
					double elapsed=time-prevwritetime;
					 if (time==elapsed){
						 call.elapsedWriteTime.add((double) 0);
					 }else{
					call.elapsedWriteTime.add(elapsed);
					}
					prevwritetime=time;
					countread=countread+1;
					}
				else if (!(Character.isDigit(z.charAt(0))) && !(Character.isDigit(y.charAt(0)))){
					 String sub2=line.substring(39, 43);
						int su2=Integer.parseInt(sub2);
						double time=(double) (su+su2*(Math.pow(10, -5)));
						double elapsed=time-prevwritetime;
						 if (time==elapsed){
							 call.elapsedWriteTime.add((double) 0);
						 }else{
						call.elapsedWriteTime.add(elapsed);
						}
						prevwritetime=time;
						countread=countread+1;
				}
				else{
						String sub2=line.substring(39, 44);
						int su2=Integer.parseInt(sub2);
						double time=(double) (su+su2*(Math.pow(10, -5)));
						double elapsed=time-prevwritetime;
						 if (time==elapsed){
							 call.elapsedWriteTime.add((double) 0);
						 }else{
						call.elapsedWriteTime.add(elapsed);
						}
						prevwritetime=time;
					countread=countread+1;
				}
			}
		}
		System.out.println(call.elapsedReadTime.size());
		System.out.println(call.elapsedWriteTime.size());
	return call;	
	}
	
	public static void readWrite() throws IOException, URISyntaxException{
		 Configuration conf= new Configuration();
		  Path hdfsFilePath= new Path("hdfs://128.143.136.181:8020/testfiles");
		  
		  DistributedFileSystem dfs1=(DistributedFileSystem)FileSystem.get(new URI("hdfs://128.143.136.181:8020/testfiles"),conf);
		  ArrayList<String> fileList= AccessLog.getAllFilePathInHDFS(dfs1,hdfsFilePath.toString());
		  
		  Time call= new Time();
		  call=readTrace();
		  long currentTime=AccessLog.getCurrentTime();
		  long sec=currentTime/1000;
		  Random randomGen= new Random();
		 
		  ExecutorService executor=Executors.newFixedThreadPool(2);
		  executor.submit(new readFiles(dfs1,fileList));
		  executor.submit(new writeFiles(dfs1,fileList));
		  
		  executor.shutdown();
		
		  
		  
	}
	
	public  static class readFiles extends ReadFilesFromTrace implements Callable<Object> {
		   public  readFiles (DistributedFileSystem dfs,ArrayList<String> fileList){
			  another=fileList;
			  dfs1=dfs;
		  }
		  @Override public Object call () throws Exception{
			 
	      read(dfs1,another);
	      return null;
		  }
		  
		  ArrayList<String> another= new ArrayList<String>();
		  DistributedFileSystem dfs1;
	}
		
	private static void read(DistributedFileSystem dfs,ArrayList<String> fileList) throws IOException, InterruptedException{
		  Time call= new Time();
		  call=readTrace();
		  Random randomGen= new Random();
		  for (int i=0; i<call.elapsedReadTime.size();i++){
			  double diff=call.elapsedReadTime.get(i);
			  if (diff<0){diff=-diff;}
			  int index=randomGen.nextInt(fileList.size());
			  String file= fileList.get(index);
			
		
			  
		Path inFile= new Path(file);
		FSDataInputStream fin=dfs.open(inFile);
		byte[] buffer = new byte[8192];

		try{
					fin.read(buffer);	
				
				}
				catch (IOException e){
					e.printStackTrace();}
		  Thread.sleep((long) diff);
		}
}
	

public  static class writeFiles extends ReadFilesFromTrace implements Callable<Object> {
	   
	public  writeFiles (DistributedFileSystem dfs,ArrayList<String> fileList){
		  another=fileList;
		  dfs1=dfs;
	  }
	  @Override public Object call () throws Exception{
		 
   write(dfs1,another);
   return null;
	  }
	  
	  ArrayList<String> another= new ArrayList<String>();
	  DistributedFileSystem dfs1;
}
		
		private static  void write(DistributedFileSystem dfs,ArrayList<String> fileList) throws IOException, InterruptedException{
			 Time call= new Time();
			  call=readTrace();
			  Random randomGen= new Random();
			  for (int i=0; i<call.elapsedWriteTime.size();i++){
				  double diff=call.elapsedWriteTime.get(i);
				  if (diff<0){diff=-diff;}
				  int index=randomGen.nextInt(fileList.size());
				  String file= fileList.get(index);
				
			String z="any text in between to append to a file in hdfs";
			Path inFile= new Path(file);
			FSDataOutputStream fout=dfs.append(inFile);
			PrintWriter writer=new PrintWriter(fout);
			writer.append(z);
			fout.close();
			 Thread.sleep((long) diff);
		}
		}


public static void main(String[] argv) throws IOException, URISyntaxException{
		ReadFilesFromTrace.readWrite();
	}
		
}


