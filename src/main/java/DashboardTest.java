import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.time.Duration;



public class DashboardTest {
	
	private WebDriver driver;
    private WebDriverWait wait;
	
	@Before
    public void setUp() {
		driver = new FirefoxDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }
	
	public static void loginSistema(WebDriver driver) {
        driver.get("http://mantis-prova.base2.com.br");
        WebElement usernameField = driver.findElement(By.id("username"));
        usernameField.click();
        usernameField.sendKeys("felipe_lucena");
        WebElement entrarButton = driver.findElement(By.cssSelector("input[type='submit']"));
        entrarButton.click();
        WebElement passwordField = driver.findElement(By.id("password"));
        passwordField.click();
        passwordField.sendKeys("123Felipe123");
        WebElement entrarSenha = driver.findElement(By.cssSelector("input[type='submit']"));
        entrarSenha.click();
    }
	
	@Test
	public void criarTarefa() {
		try {
			loginSistema(driver);
			WebElement criarTarefa = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//li[3]/a/span")));
			criarTarefa.click();
			WebElement selectElement  = wait.until(ExpectedConditions.elementToBeClickable(By.id("category_id")));
			Select select = new Select(selectElement);
			select.selectByValue("2");
			WebElement resumo = driver.findElement(By.id("summary"));
			resumo.sendKeys("Isso é um teste!");
			WebElement descricao = driver.findElement(By.id("description"));
			descricao.sendKeys("Isso é um teste de funcionalidade!");
			WebElement cadastrarTarefa = driver.findElement(By.cssSelector("input[type='submit']"));
			cadastrarTarefa.click();
		} finally {
			driver.quit();
		}
	}
	
	@Test
	public void criarTarefaComCamposObrigatoriosVazios() {
		try {
			loginSistema(driver);
			WebElement criarTarefa = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//li[3]/a/span")));
			criarTarefa.click();
			wait.until(ExpectedConditions.elementToBeClickable(By.id("category_id")));
			WebElement cadastrarTarefa = driver.findElement(By.cssSelector("input[type='submit']"));
			cadastrarTarefa.click();
	        WebElement widgetTitle = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".widget-title")));
	        String textoEsperado = "Digite os Detalhes do Relatório";
	        String textoAtual = widgetTitle.getText();
	        assertTrue("O texto do elemento com a classe .widget-title não é o esperado.", textoAtual.contains(textoEsperado));
		} finally {
			driver.quit();
		}
		
	}
	
	@Test
	public void criarTarefaComDoisCamposObrigatoriosPreenchidos() {
		try {
			loginSistema(driver);
			WebElement criarTarefa = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//li[3]/a/span")));
			criarTarefa.click();
			WebElement resumo = driver.findElement(By.id("summary"));
			resumo.sendKeys("Isso é um teste!");
			WebElement descricao = driver.findElement(By.id("description"));
			descricao.sendKeys("Isso é um teste de funcionalidade!");
			WebElement cadastrarTarefa = driver.findElement(By.cssSelector("input[type='submit']"));
			cadastrarTarefa.click();
			WebElement alertDiv = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".alert.alert-danger")));
			String textoEsperado = "APPLICATION ERROR"; 
			String textoAtual = alertDiv.getText();
			assertTrue("O texto 'APPLICATION ERROR' não foi encontrado na div com a classe alert alert-danger.", textoAtual.contains(textoEsperado));
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
