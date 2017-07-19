package com.backroads.webdriver.common.util;

import org.openqa.selenium.WebElement;

public class WebElementUtils {
	
	//assumes that button1 is selected by default and button2 is not.
	public static boolean isRadioMutuallyExclusive(WebElement button1, WebElement button2){

		if(button1.isSelected() && !button2.isSelected()){
			button2.click();
			if(button1.isSelected() == button2.isSelected() || (button1.isSelected() && !button2.isSelected())){
				System.out.println("The radio buttons are not mutually exclusive.");
				return false;
			}
		} else {
			System.out.println("The default may not be incorrect.");
			return false;
		}
		
		//setting return to default
		button1.click();
		if(button1.isSelected() && !button2.isSelected()){
			return true;
		}
		return false;
	}
	
	
} 
