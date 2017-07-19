package com.backroads.webdriver.common.util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class TableHandler {	
	
	//get hidden column in list
	public static <E> List<WebElement> getHiddenColRemovedList(WebElement rowElement, String tagName){	
		List<WebElement> cols = rowElement.findElements(By.tagName(tagName));
		for (int i = 0; i < cols.size(); i++){
			if(cols.get(i).getAttribute("class").equals("HiddenCol")){
				cols.remove(i);
				i--;
			}
		}
		return cols;
	}
	
	//get the strings in the specified column in a table
	public static String [] getColumnsAsStrArr(WebElement tableElement, String tagName, int colLoc){
		List<WebElement> rows = tableElement.findElements(By.tagName("tr"));	
		
		System.out.println("row size: " + rows.size());
		String [] colStr = new String[rows.size()-1];
		for (int i = 1; i < rows.size(); i++){
			colStr[i-1]=rows.get(i).findElements(By.tagName(tagName)).get(colLoc).findElement(By.tagName("input")).getAttribute("value");
			//System.out.println(colStr[i-1]);
		}		
		return colStr;
	}
	
	public static <E> List<WebElement> getColumnsAsList(WebElement tableElement, String tagName, int colLoc){
		List<WebElement> rows = tableElement.findElements(By.tagName("tr"));
		List<WebElement> colStr = new ArrayList<WebElement>();
		for (int i = 1; i < rows.size(); i++){			
			colStr.add(rows.get(i).findElements(By.tagName(tagName)).get(colLoc));		
		}		
		return colStr;
	}
	    
    public static void fillAllInputFieldInColumn(List <WebElement> columns, String [] inputs){   	
    	WebElement inputField;
    	for(int i = 0; i < inputs.length; i++) {
			inputField = columns.get(i).findElement(By.tagName("input"));
			inputField.clear();
			inputField.sendKeys(inputs[i]);
		}
    }
    
    //Return web elements of rows that contains "s" in tagName, searching only in colLoc (specified column location).
    public static <E> List<WebElement> getARowElements(WebElement tableElement, String s, String tagName, int colLoc){
		List<WebElement> rows = tableElement.findElements(By.tagName("tr"));
		List<WebElement> foundRows = new ArrayList<WebElement>();
		for (int i = 1; i < rows.size(); i++){	
			String colstr = rows.get(i).findElements(By.tagName(tagName)).get(colLoc).getText();
			if(colstr.equals(s)){
				foundRows.add(rows.get(i));
			}					
		}		
		return foundRows;    	
    }
	
	
}
