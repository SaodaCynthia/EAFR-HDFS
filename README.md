Prerequisite:
Hadoop 2.8.1
Java 1.8.0_161

Steps:

1. Install hadoop and replace the hadoop-common-2.8.1.jar and hadoop-hdfs-2.8.1.jar with the new modified jar in the hadoop-2.8.1-src(EAFR-HDFS in shared drive).

3. enagle hdfs audit loging from the configuration file and alos specify the location of warm and cold servers(hot by deafult).

2. To create a number of files in HDFS according to the size specified in trace data, export the TestEAFR to a jar file and run the class named fileStore.java.

3. To create a number of files with a size of your own choice, run the jar with class fileCreate.java. It will ask the file size in MB from the console. 10 files of the specified size will be added to HDFS. Run the class several times to create various sixe of files ( for example: 1 mb, 10 mb, 1024mb etc)

4. To check the file replication can work adaptively, 
    a)run the ReadFilesFromTrace.java from the jar which will read/write from the trace. (check it if it works from the HDFS auit-log)
    
    b) then run the AccessLog.java to test the adaptive replication. It will give a list of current hot and cold files with a popularity map to the console output.
    
5. To test the read latency performance, run the ReadLatency.java. You will be able to set the number of concurrent read request to a file from the console as an input parameter.
6. To test the replication latency performance, run the ReplicationLatency.java.

7.To get the energy consumption measurement, run the file CPUUtil.java, to get the cpu utilization per hour.

8.To get the memory consumption, the values can be collected from the HDFS metrics  using command line tools or from the url of your HDFS cluster.

9. To get the maintenance overhead, I did it by hand. You can add a line in ReadLatency to get the maintenance overhead by multiplying the read latency value with the size of the corresponding file.



The classes modified in Hadoop source code:

1. For the adaptive replication, In hadoop-common-project/hadoop-common/src/main/java/org/apache/hadoop/fs, I added the following AccesssLog.java class.

2.For the block placement mechanism, In hadoop-hdfs-project/hadoop-hdfs/src/main/java/org/apache/hadoop/hdfs/server/blockmanagement/BlockPlacementPolicyDefault.java I modified the following methods:

    a) ChooseTarget, ChooseTargetInOrder.
  And I added the following methods:
    b)  ChooseTargetwithHighestStorage,EWMA,ChooseTargetForEAFR
    
    The position of the lines are within line number 507-664.
    


