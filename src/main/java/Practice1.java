import com.google.common.annotations.VisibleForTesting;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;
public class Practice1 {
    protected static WebDriver driver;
    public String getaemail(String startvalue) {
        String email = startvalue.concat(new Date().toString());
        // your code
        return email;
    }
    public static String randomDate(){
        DateFormat format = new SimpleDateFormat("ddMMyyHHmmss");
        return format.format(new Date());
    }
    @BeforeMethod
    public void openbrowser() {
        System.setProperty("webdriver.chrome.driver", "src\\main\\Resources\\BrowserDriver\\chromedriver.exe");
        //open the browser
        driver = new ChromeDriver();
        //maximise the browser window screen
        driver.manage().window().fullscreen();
        //set implicity wait for driver object
        driver.manage().timeouts().implicitlyWait(59, TimeUnit.SECONDS);
        //open the website
        driver.get("http://demo.nopcommerce.com/");
        //click on register button
        driver.findElement(By.xpath("//a[@class = 'ico-register']")).click();
        //enter firstname
        driver.findElement(By.id("FirstName")).sendKeys("Venus");
        //enter lastname
        driver.findElement(By.xpath("//input[@name='LastName']")).sendKeys("patel");
        //enter email
        driver.findElement(By.name("Email")).sendKeys("testtest1"+randomDate()+"@test.com");
        //enter password
        driver.findElement(By.xpath("//input[@id='Password']")).sendKeys("abcd1234");
        //enter confirm password
        driver.findElement(By.xpath("//input[@name='ConfirmPassword']")).sendKeys("abcd1234");
    }
    @AfterMethod
    public void closebrowaer() {
        driver.quit();
    }
    @Test
    public void userShouldBeAbleToRegisterSuccessfully() {
        //click on register button
        //driver.findElement(By.xpath("//input[@id='register-button']")).click();
        //click on countinue
       // driver.findElement(By.xpath("//input[@value=\"Continue\"]")).click();
        //click on register button
        driver.findElement(By.xpath("//input[@id='register-button']")).click();
        String ActualMassage = "Your registration completed";
        String ExpectedMassage=driver.findElement(By.xpath("//div[@class='result']")).getText();
        Assert.assertEquals(ActualMassage, ExpectedMassage);
    }
    @Test
    public void userShouldBeAbleToReferAProductToFriend(){
        //click on register button
        driver.findElement(By.xpath("//input[@id='register-button']")).click();
        //click on countinue
        driver.findElement(By.xpath("//input[@value=\"Continue\"]")).click();
        //click on macbook page
        driver.findElement(By.xpath("//h2//a[@href=\"/apple-macbook-pro-13-inch\"]")).click();
        //click on enter friend
        driver.findElement(By.xpath("//input[@value='Email a friend']")).click();
        //enter friend email
        driver.findElement(By.xpath("//input[@id='FriendEmail']")).sendKeys("kunlaraval73@gmail.com");
        //enter your email
        driver.findElement(By.xpath("//input[@id='YourEmailAddress']")).click();
        //enter persnol massage
        driver.findElement(By.xpath("//textarea[@placeholder='Enter personal message (optional).']")).sendKeys("please check this out");
        //click on send email
        driver.findElement(By.xpath("//input[@value=\"Send email\"]")).click();
        String ActualMassage = "Your message has been sent.";
        String ExpectedMassage=driver.findElement(By.xpath("//div[@class=\"result\"]")).getText();
        Assert.assertEquals(ActualMassage, ExpectedMassage);
    }
    @Test
    public void userShouldBeAbleToNavigateCameraAndPhotoPage(){
        //click on register button
        driver.findElement(By.xpath("//input[@id='register-button']")).click();
        //click on countinue
        driver.findElement(By.xpath("//input[@value=\"Continue\"]")).click();
        //click on electronics
        driver.findElement(By.xpath("//h2/a[@title=\"Show products in category Electronics\"] ")).click();
        //click on camera and photo button
        driver.findElement(By.xpath("//h2/a[@title=\"Show products in category Camera & photo\"]")).click();
        String ActualMassage = "Camera & photo";
        String ExpectedMassage=driver.findElement(By.xpath("//h1")).getText();
        Assert.assertEquals(ExpectedMassage, ActualMassage);
    }
    @Test
    public void userShouldBeAbleAddProductFromBookCategory(){
        //click on register button
        driver.findElement(By.xpath("//input[@id='register-button']")).click();
        //click on countinue
        driver.findElement(By.xpath("//input[@value=\"Continue\"]")).click();
        //click on books catagory
        driver.findElement(By.linkText("Books")).click();
        //add to cart
        driver.findElement(By.xpath("//input[contains(@onclick,'37/1/1')]")).click();
        //click on books catagory
        driver.findElement(By.linkText("Books")).click();
        //click on another book
        driver.findElement(By.xpath("//input[contains(@onclick,'38/1/1')]")).click();
        //click on shopping cart
        driver.findElement(By.xpath("//span[@class=\"cart-label\"]")).click();
        String ActualMassage = "Shopping cart";
        String ExpectedMassage=driver.findElement(By.xpath("//span[@class=\"cart-label\"]")).getText();
        Assert.assertEquals(ExpectedMassage, ActualMassage);
    }
    @Test
    public void userShouldBeAbleToSelectTheJewelleryProduct(){
        //click on register button
        driver.findElement(By.xpath("//input[@id='register-button']")).click();
        //click on continue
        driver.findElement(By.xpath("//input[@name='register-continue']")).click();
        //Navigate to jewelry
        driver.findElement(By.linkText("Jewelry")).click();
        //select price limit
        driver.findElement(By.xpath("//a[contains(@href,\"700-3000\")]")).click();
        //check the Expected result
        String Expectedresult="$700.00 - $3,000.00";
        //check the Actual result
        String Actualresult= driver.findElement(By.xpath("//span[@class='item']")).getText();
        //compare the result
        Assert.assertEquals(Actualresult,Expectedresult);
        String Productprice=driver.findElement(By.xpath("//span[@class='price actual-price']")).getText();
        String price1=String.valueOf(Productprice.replace("$",""));
        String price2=String.valueOf(price1.replace(",",""));
        double price=Double.valueOf(price2);
        //check the result
        Assert.assertTrue(price>=700 && price<=3000);
    }
}