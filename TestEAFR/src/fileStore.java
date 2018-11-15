import java.io.*;
import java.lang.reflect.Array;
import java.net.*;
import java.nio.charset.Charset;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.regex.*;

import org.apache.commons.io.IOUtils;
import org.apache.hadoop.fs.*;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hdfs.DistributedFileSystem;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.util.ToolRunner;
public class fileStore{
	
public static int totalFiles=200;
public static long onemb=(long) Math.pow(2, 20);
public static ArrayList<Long> sizes= new ArrayList<Long> ();
	
  public static ArrayList<Long> sizes(){
	  
	 System.out.println("Enter the file size in mb:");
	 Scanner reader=new Scanner(System.in);
	 int filesize=reader.nextInt();
	 reader.close();
	 
	 for (int i=0;i<10;i++){
	 sizes.add(filesize*onemb);}
	
	 /*for (int i=0;i<10;i++){
	sizes.add(10000*onemb);}*/
	 
	 
	 return sizes;
	 
} 
  public static void makeFile() throws IOException, URISyntaxException{
		
		ArrayList<Long> FileSize=sizes();
		
		for (int i=0;i<FileSize.size();i++){
			
			writeFile(FileSize.get(i),i);
		}
	}
		
		public static void writeFile ( long filesize,int fileSeqNum) throws IOException, URISyntaxException{
			String hdfsfilepath=AccessLog.hdfsFilePath;
			Configuration conf= new Configuration();
			DistributedFileSystem dfs1=(DistributedFileSystem)FileSystem.get(new URI(hdfsfilepath),conf);
			byte buffer[]= new byte[8192];
			int chunk= (int) (filesize/buffer.length);
					
	        String path=hdfsfilepath+"/testfiles/"+"file"+fileSeqNum+".txt";
		    Path outFile=new Path (path);
		    FSDataOutputStream fos=dfs1.create(outFile);
			if (filesize<buffer.length){
				  
				try{
						fos.write((int) filesize);	
					}
					catch (IOException e){
						e.printStackTrace();
						
					}
					}
			else{
				
		    for (int j=0;j<chunk;j++){
					try{
					fos.write(buffer,0,buffer.length);	
				}
				catch (IOException e){
					e.printStackTrace();
					
				}
				}
			}
				
		}
		public static  void main (String [] argv) throws Exception{
			makeFile();
		}
	}
