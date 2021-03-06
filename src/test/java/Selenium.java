import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Random;

public class Selenium {
    //cria instancia do drive chrome
    private static WebDriver driver;
    private static WebDriverWait wait;

    @BeforeAll
    public static void setup(){
        //setar as propriedade do chrome drive
        System.setProperty("webdriver.chrome.driver" , "./src/main/resources/chromedriver.exe");
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 100);

        // abrir o browser pe acessar a url
        driver.get("https://automacaocombatista.herokuapp.com");

        //Maximizando o browser window
        driver.manage().window().maximize();

        driver.findElement(By.xpath("//a[text()='Começar Automação Web']")).click();

        //Maximizando o browser mac/linux
        //driver.manage().window().fullscreen();
    }

    @Test //anotação do jUnit jupiter que vai me ajudar a rodar o teste (Dar o play)
    public  void criarUsuario(){

      Random random = new Random();
    //Cria varios emails diferentes
      String email = "naiara"+random.nextInt(1000)+"@sysdata.com.br";


    //Identificar um elemento preenchendo o campo com um texto
        driver.findElement(By.xpath("//a[text()='Formulário']")).click();

        driver.findElement(By.xpath("//a[text()='Criar Usuários']")).click();
        driver.findElement(By.id("user_name")).sendKeys("Naiara"); //1° encontra um elemento na tela 2° inserir texto no campo
        driver.findElement(By.id("user_lastname")).sendKeys("Silva");
        driver.findElement(By.id("user_email")).sendKeys(email);
        driver.findElement(By.id("user_address")).sendKeys("Rua das Pedras");
        driver.findElement(By.id("user_university")).sendKeys("EStacio");
        driver.findElement(By.id("user_profile")).sendKeys("Analista");
        driver.findElement(By.id("user_gender")).sendKeys("F");
        driver.findElement(By.id("user_age")).sendKeys("28");
        //driver.findElement(By.xpath("//input[@value=\"Criar\"]")).click();
        driver.findElement(By.name("commit")).click();

        //Atribuindo o texto da mensagem na tela para  variavel
        String mensagem = driver.findElement(By.xpath("//p[@id='notice']")).getText();

        //validando se a mensagem que eu necessito esta aparecendo na tela
        Assertions.assertEquals("Usuário Criado com sucesso", mensagem);
    }


    @Test
    public void radioCheckBox(){
       driver.findElement(By.xpath("//a[text()='Busca de elementos']")).click();

        WebElement link = driver.findElement(By.xpath("//a[text()='Radio e Checkbox']"));
        wait.until(ExpectedConditions.visibilityOf(link));
        link.click();

        /*driver.findElement(By.xpath("//input[@id='red']")).click();
        driver.findElement(By.xpath("//input[@id='blue']")).click();
        driver.findElement(By.xpath("//input[@id='yellow']")).click();
        driver.findElement(By.xpath("//input[@id='green']")).click();
        driver.findElement(By.xpath("//input[@id='purple']")).click();
        driver.findElement(By.xpath("//input[@id='grey']")).click();
        driver.findElement(By.xpath("//input[@id='black']")).click();
        driver.findElement(By.xpath("//input[@id='white']")).click();*/



    }

    @Test
    public void select() {
        driver.findElement(By.xpath("//a[text()='Busca de elementos']")).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='Dropdown e Select']")));

        driver.findElement(By.xpath("//a[text()='Dropdown e Select']")).click();

        driver.findElement(By.xpath("//label[text()='Desenho Favorito']/preceding-sibling::div[@class='select-wrapper']")).click();
        driver.findElement(By.xpath("//span[text()='Dragon Ball']")).click();

        driver.findElement(By.xpath("//span[text()='Ronaldinho Gaucho']/ancestor::ul/preceding-sibling::input")).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[text()='Ronaldinho Gaucho']")));
        driver.findElement(By.xpath("//span[text()='Ronaldinho Gaucho']")).click();


        WebElement listaSelecao = driver.findElement(By.xpath("//select[@id ='dropdown']"));

        //Existe uma classe só para o select do Selenium
       Select select = new Select(listaSelecao);

       select.selectByIndex(1);

       //valor que fica dentro do elemento value
     //  select.selectByValue("3");

     //  select.selectByVisibleText("Internet Explorer");


    }

    @AfterAll
    public static void fecharBrowser(){

        //Fechar meu Browser
        driver.quit();
    }
}


