package com.FileSpliter.main;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.time.Duration;
import java.time.Instant;

public class FileSpliterMaster {

//	public static void main(String[] args) throws IOException {
	public void SpliterMan(String targetFileLocation, long numberOfSplitedFiles, int maxAllowedSize, String locationToStoreSplitedFiles, String splitedFileName, String format) throws IOException {

		Instant start = Instant.now();
		RandomAccessFile raf = new RandomAccessFile(targetFileLocation, "r");
		long numSplits = numberOfSplitedFiles; 
		long sourceSize = raf.length();
		long bytesPerSplit = sourceSize/numSplits ;
		long remainingBytes = sourceSize % numSplits;

		int maxReadBufferSize = maxAllowedSize * 1024; //8KB
		for(int destIx=1; destIx <= numSplits; destIx++) {
			BufferedOutputStream bw = new BufferedOutputStream(new FileOutputStream("locationToStoreSplitedFiles"+"split."+destIx+"."+format));
			if(bytesPerSplit > maxReadBufferSize) {
				long numReads = bytesPerSplit/maxReadBufferSize;
				long numRemainingRead = bytesPerSplit % maxReadBufferSize;
				for(int i=0; i<numReads; i++) {
					ReadMaster(raf, bw, maxReadBufferSize);
				}
				if(numRemainingRead > 0) {
					ReadMaster(raf, bw, numRemainingRead);
				}
			}else {
				ReadMaster(raf, bw, bytesPerSplit);
			}
			bw.close();
		}
		if(remainingBytes > 0) {
			BufferedOutputStream bw = new BufferedOutputStream(new FileOutputStream(splitedFileName+"-"+(numSplits+1)));
			ReadMaster(raf, bw, remainingBytes);
			bw.close();
		}
		raf.close();
		Instant end = Instant.now();
		System.out.println("Total Taken Time : " + Duration.between(start, end).toMinutes());

	}

	static void ReadMaster(RandomAccessFile raf, BufferedOutputStream bw, long numBytes) throws IOException {
		byte[] buf = new byte[(int) numBytes];
		int val = raf.read(buf);
		if(val != -1) {
			bw.write(buf);
		}

	}
}
