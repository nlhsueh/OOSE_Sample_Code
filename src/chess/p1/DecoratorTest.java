package p1;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class DecoratorTest {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		FileWriter file = new FileWriter("sample.txt");
		BufferedWriter writer= new BufferedWriter(file);
		writer.write("test");
		writer.newLine();
		writer.close();

	}

}


