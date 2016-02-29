package test;

import java.io.File;
import java.io.FileInputStream;
//import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.util.regex.Pattern;

public class CM {
	
	private XSSFWorkbook myWorkBook;
	
	public Map<String,String> dict = new HashMap<String,String>();

	public void ReadFile() throws IOException {
		
		//System.out.println("working directory = " + System.getProperty("user.dir"));
		File myFile = new File("t.xlsx");
        FileInputStream fis = new FileInputStream(myFile);

        myWorkBook = new XSSFWorkbook (fis);
       
        XSSFSheet mySheet = myWorkBook.getSheetAt(0);
       
        Iterator<Row> rowIterator = mySheet.iterator();
        
        while(rowIterator.hasNext()) {
        	Row row = rowIterator.next();
        	
        	Iterator<Cell> cellIterator = row.cellIterator();
        	int i = 0;
        	String Chinese = "";
        	String Pinyin = "";
        	while (cellIterator.hasNext()) {
        		Cell cell = cellIterator.next();
        		if(i == 0)
        			Chinese = cell.getStringCellValue();
        		else
        			Pinyin = cell.getStringCellValue();
        		i++;
        	}
        	//System.out.println(Chinese+" "+Pinyin);
        	
        	if(dict.containsKey(Chinese)) {
        		dict.put(Chinese,  dict.get(Chinese)+"/"+Pinyin);
        	}
        	else
				{dict.put(Chinese, Pinyin);}
        }
        //System.out.println(dict);
	}
	
	//public static void main(String[] args) throws IOException {
	
	public String check(String s) throws IOException {
		
		//ReadFile();
		String result = "";
		Pattern pattern = Pattern.compile("[\\u4E00-\\u9FBF]+");
	       //System.out.println(s.length());
		for(int i = 0; i<s.length(); i++) {
			String temp = s.substring(i,i+1); 
			if(!pattern.matcher(temp.trim()).find()) {
				//System.out.print(temp+" ");
				result = result + temp;
				continue;
			}
	    		   
			if(dict.containsKey(temp)) {
				//System.out.print(dict.get(temp)+" ");
				result = result + dict.get(temp) + "  ";
			}
			else {
				//System.out.print("* ");
				result = result + "*  ";
			}
		}
		return result;
	}
	
	
	
	/*public static void main(String[] args) throws IOException
	{
		ReadFile();
	}*/
}
