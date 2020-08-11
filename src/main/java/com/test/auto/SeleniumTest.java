package com.test.auto;

import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SeleniumTest {

	public static void main(String[] args) throws InterruptedException {
		
		WebDriver driver = new ChromeDriver();
		
		//浏览器窗口最大化
		driver.manage().window().maximize();
		Thread.sleep(3000);
		//打开网页
		driver.get("http://www.supermapol.com");
		
		//显示等待，针对某一元素（登录按钮）
		WebDriverWait wait = new WebDriverWait(driver, 10); 
		wait.until(new ExpectedCondition<WebElement>() { 
			public WebElement apply(WebDriver text) {
				return text.findElement(By.xpath("//a[@id='loginBtn']")); 
			} }).click(); 
		
		Thread.sleep(1000);
		
		//登录弹窗嵌套在iframe
		WebElement iframe = driver.findElement(By.xpath("//div[last()]//iframe"));
		//切换到登录表单的iframe
		driver.switchTo().frame(iframe);
		//切换为手机号登录
		WebElement orPhone = driver.findElement(By.xpath("//li[@id='orPhone']"));
		orPhone.click();
		
		Thread.sleep(1000);
		//定位元素
		WebElement username = driver.findElement(By.xpath("//input[@id='username']"));
		WebElement password = driver.findElement(By.xpath("//input[@id='password']"));
		WebElement loginbtn = driver.findElement(By.xpath("//input[@id='btnSubmit']"));
		
		username.clear();
		
		Thread.sleep(1500);
		//输入账号密码
		username.sendKeys("18215540952");
		password.sendKeys("clearlove..");
		
		Thread.sleep(1000);
		//点击登录
		loginbtn.click();
		
		Thread.sleep(3000);
		
		((JavascriptExecutor)driver).executeScript("window.scrollTo(0,500);");
		
		driver.findElement(By.xpath("//div[contains(@class,'features-content')]/div[2]//a")).click();
		
		Thread.sleep(3000);
		//获得当前窗口句柄
	    String search_handle = driver.getWindowHandle();
	    
		
		driver.findElement(By.linkText("即刻体验")).click();
		
		Thread.sleep(2000);
		
		//获得所有窗口句柄
		Set<String> handles = driver.getWindowHandles();
		for (String handle : handles) {
			//切换窗口
			if (handle.equals(search_handle) == false) {
				driver.switchTo().window(handle);
				Thread.sleep(2000);
				
				driver.findElement(By.xpath("//div[@class='fl']//ul[@class='second-level']//li[2]")).click();
				
				Thread.sleep(500);
				
				
				((JavascriptExecutor)driver).executeScript("window.scrollTo(0,9999999);");
				
				Thread.sleep(500);
				
				WebElement address = driver.findElement(By.xpath("//input[@id='address']"));
				WebElement city = driver.findElement(By.xpath("//input[@id='city']"));
				WebElement analyst = driver.findElement(By.xpath("//button[@class='analystButton btn btn-primary']"));
				
				address.clear();
				address.sendKeys("南湖逸家");
				city.clear();
				city.sendKeys("成都市");
				
				Thread.sleep(500);
				
				analyst.click();
				
				Thread.sleep(500);
				((JavascriptExecutor)driver).executeScript("window.scrollTo(0,9999999);");
				Thread.sleep(500);
				
				String js = "document.getElementsByClassName('showAnalystFrame')[0].scrollTop=10000";
						
				((JavascriptExecutor)driver).executeScript(js);
				
				Thread.sleep(5000);
				
				driver.close();
			}
		}
		
		Thread.sleep(5000);
		driver.quit();

	}

}
