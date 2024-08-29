
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.time.Duration;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.By;

public class MantisLoginTest {
	
	private WebDriver driver;
    private WebDriverWait wait;
    
    @Before
    public void setUp() {
		driver = new FirefoxDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }
	
	public static void entrarSite(WebDriver driver) {
        driver.get("http://mantis-prova.base2.com.br");
    }
	public static void preencherUsuario(WebDriver driver) {
		WebElement usernameField = driver.findElement(By.id("username"));
        usernameField.click();
        usernameField.sendKeys("felipe_lucena");
    }
	public static void submitUsuario(WebDriver driver) {
		WebElement entrarButton = driver.findElement(By.cssSelector("input[type='submit']"));
        entrarButton.click();
    }
	public static void preencherSenha(WebDriver driver) {
		WebElement passwordField = driver.findElement(By.id("password"));
        passwordField.click();
        passwordField.sendKeys("123Felipe123");
    }
	
	public static void submitSenha(WebDriver driver) {
		WebElement entrarSenha = driver.findElement(By.cssSelector("input[type='submit']"));
        entrarSenha.click();
	}
	
	@Test
	public void loginTeste() {
		WebDriver driver = new FirefoxDriver();
		try {
			 entrarSite(driver);
			 preencherUsuario(driver);
	         submitUsuario(driver);
	         preencherSenha(driver);
	         submitSenha(driver);
		} finally {
			 driver.quit();
		}
		
	}
	
	@Test
	public void entrarCampoUsuarioVazio() {
		WebDriver driver = new FirefoxDriver();
		try {
			 entrarSite(driver);
			 submitUsuario(driver);
			 WebElement errorMessage = driver.findElement(By.xpath("//div[contains(text(), 'Sua conta pode estar desativada ou bloqueada ou o nome de usuário e a senha que você digitou não estão corretos.')]"));
			 if (errorMessage.isDisplayed()) {
	                System.out.println("Mensagem de erro exibida: " + errorMessage.getText());
	            } else {
	                System.out.println("Mensagem de erro não foi exibida.");
	            }
		} catch (Exception e) {
            System.out.println("Erro ao verificar a mensagem: " + e.getMessage());
        } finally {
			 driver.quit();
		}
		
	}
	
	@Test
	public void entrarCampoSenhaVazio() {
		WebDriver driver = new FirefoxDriver();
		try {
			entrarSite(driver);
			preencherUsuario(driver);
			submitUsuario(driver);
			submitSenha(driver);
	        WebElement errorMessage = driver.findElement(By.xpath("//div[contains(text(), 'Sua conta pode estar desativada ou bloqueada ou o nome de usuário e a senha que você digitou não estão corretos.')]"));
	        if (errorMessage.isDisplayed()) {
                System.out.println("Mensagem de erro exibida: " + errorMessage.getText());
            } else {
                System.out.println("Mensagem de erro não foi exibida.");
            }
		} catch (Exception e) {
            System.out.println("Erro ao verificar a mensagem: " + e.getMessage());
        } finally {
			 driver.quit();
		}
	}
	
	@After
	 public void tearDown() {
		 if (driver != null) {
	         	driver.quit();
		 }
	}
	
}
