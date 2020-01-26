# SpliterMater
SpliterMaster gives freedom to the programmers to split any file with customizable manner.

# Dependencies
Java 1.8 or above

# How To Use
```
FileSpliterMaster fs = new FileSpliterMaster();
fs.SpliterMan(sourceFileLocation, numberOfSplitedFiles, maxAllowedSize, locationToStoreSplitedFiles, splitedFileName, format);
```
# Parameters Datatype
sourceFileLocation = String </br>
numberOfSplitedFiles = long</br>
maxAllowedSize = int</br>
locationToStoreSplitedFiles = String</br>
splitedFileName = String</br>
format = String</br>

# Real Use
```
import java.io.IOException;

import com.FileSpliter.main.FileSpliterMaster;

public class Main {

	public static void main(String[] args) {

		FileSpliterMaster fs = new FileSpliterMaster();
		try {
			fs.SpliterMan("C:\\Users\\soham\\Desktop\\TestFile.txt", 5, 8, "C:\\Users\\soham\\Desktop\\Split\\", "File", "txt");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
```
