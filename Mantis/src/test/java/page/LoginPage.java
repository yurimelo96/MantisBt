package page;

import static util.EvidenceManager.GerarEvidencia;

import org.openqa.selenium.By;

import core.BasePage;
import core.Elemento;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.E;
import io.cucumber.java.pt.Entao;
import io.cucumber.java.pt.Quando;

public class LoginPage extends BasePage {
	private Elemento inputLogin = new Elemento(getDriver(), "xpath", "//input[@type='text']");
	private Elemento inputPass = new Elemento(getDriver(), "xpath", "//input[@type='password']");
	private Elemento buttonLogin = new Elemento(getDriver(), "xpath", "//input[@type='submit']");
	private Elemento buttonReport = new Elemento(getDriver(), "xpath", "//a[contains(text(),'Report Issue')]");
	private Elemento optionProject = new Elemento(getDriver(), "xpath",
			"//td[@width='60%']//select[@name='project_id']");
	private Elemento selectProjectJm = new Elemento(getDriver(), "xpath",
			"//td[@width='60%']//select[@name='project_id']//option[@value='24'][contains(text(),'Desafio jMeter Project 1')]");
	private Elemento buttonSelect = new Elemento(getDriver(), "xpath", "//td[@class='center']//input[@type='submit']");
	private Elemento optionCategory = new Elemento(getDriver(), "xpath", "//select[@name='category_id']");
	private Elemento optionGeneral = new Elemento(getDriver(), "xpath",
			"//option[contains(text(),'[All Projects] General')]");
	private Elemento optionReproducibility = new Elemento(getDriver(), "xpath", "//select[@tabindex='2']");
	private Elemento optionSometimes = new Elemento(getDriver(), "xpath", "//option[contains(text(),'sometimes')]");
	private Elemento optionSeverity = new Elemento(getDriver(), "xpath", "//select[@tabindex='2']");
	private Elemento optionText = new Elemento(getDriver(), "xpath", "//option[contains(text(),'text')]");
	private Elemento optionPriority = new Elemento(getDriver(), "xpath", "//select[@tabindex='4']");
	private Elemento optionNormal = new Elemento(getDriver(), "xpath", "//option[contains(text(),'normal')]");
	private Elemento optionProfile = new Elemento(getDriver(), "xpath", "//select[@tabindex='5']");
	private Elemento optionWindows10 = new Elemento(getDriver(), "xpath", "//option[@value='74']");
	private Elemento inputPlataform = new Elemento(getDriver(), "xpath", "//input[@id='platform']");
	private Elemento inputOs = new Elemento(getDriver(), "xpath", "//input[@id='os']");
	private Elemento inputVersion = new Elemento(getDriver(), "xpath", "//input[@id='os_build']");
	private Elemento inputSumary = new Elemento(getDriver(), "xpath",
			"//body/div[@align='center']/form[@name='report_bug_form']/table[@class='width90']/tbody/tr[8]/td[2]/input[1]");
	private Elemento inputDescription = new Elemento(getDriver(), "xpath", "//textarea[@name='description']");
	private Elemento inputStep = new Elemento(getDriver(), "xpath", "//textarea[@name='steps_to_reproduce']");
	private Elemento inputAdditional = new Elemento(getDriver(), "xpath", "//textarea[@tabindex='12']");
	private Elemento fileUpload = new Elemento(getDriver(), "xpath", "//input[@id='ufile[]']");
	private Elemento radioStatus = new Elemento(getDriver(), "xpath",
			"//label[contains(text(),'public')]//input[@type='radio']");
	private Elemento buttonSubmit = new Elemento(getDriver(), "xpath", "//td[@class='center']//input[@type='submit']");

	@Dado("que preencho o login e senha no sistema MantisBT com as credenciais {string} e {string}")
	public void acessarMantis(String user, String pass) throws Exception {
		navegar("https://mantis-prova.base2.com.br");
		esperarElemento(buttonLogin, SMALL);
		GerarEvidencia("Tela inicial do mantis");
		escrever(inputLogin, user);
		escrever(inputPass, pass);

	}

	@Quando("clico no botao Login")
	public void clicarLogin() throws Exception {
		clicar(buttonLogin);
		GerarEvidencia("botão clicado");
	}

	@Entao("valido a mensagem de erro")
	public void validarMensagem() throws Exception {
		Elemento labelError = new Elemento(getDriver(), "xpath", "/html[1]/body[1]/div[2]/font[1]");
		validarElemento(labelError);
		GerarEvidencia("Mensagem de falha");
	}

	@Entao("valido a mensagem de sucesso")
	public void validarMensagemSucesso() throws Exception {
		Elemento labelSucesso = new Elemento(getDriver(), "xpath", "/html[1]/body[1]/table[1]/tbody[1]/tr[1]/td[1]");

		validarElemento(labelSucesso);
		GerarEvidencia("Mensagem de sucesso");
	}

	@Quando("clico na aba Report Issue")
	public void clicarReportIssue() throws Exception {
		esperarElemento(buttonReport, BIG);
		clicar(buttonReport);
		GerarEvidencia("botão clicado");
	}

	@E("seleciono o projeto")
	public void selecionoProjeto() throws Exception {
		clicar(optionProject);
		clicar(selectProjectJm);
		clicar(buttonSelect);
		GerarEvidencia("botão clicado");
	}

	@Entao("preencho o formulario com o upload")
	public void preenchoFormulario() throws Exception {
		clicar(optionCategory);
		clicar(optionGeneral);
		clicar(optionReproducibility);
		clicar(optionSometimes);
		clicar(optionSeverity);
		clicar(optionText);
		clicar(optionPriority);
		clicar(optionNormal);
		clicar(optionProfile);
		clicar(optionWindows10);
		escrever(inputPlataform, "Windows10");
		escrever(inputOs, "Windows");
		escrever(inputVersion, "Ultimate");
		escrever(inputSumary, "testY");
		escrever(inputDescription, "ola");
		esperarElemento(inputStep, SMALL);
		escrever(inputStep, "etccte");
		escrever(inputAdditional, "Nothing");
		driver.findElement(By.xpath("//input[@id='ufile[]']")).sendKeys("C:/Users/ymelorei/Documents/yuri/Mantis/Data/teste.txt");
		escrever(fileUpload, "C:/Users/ymelorei/Documents/yuri/Mantis/Data/teste.txt");
		clicar(radioStatus);
		clicar(buttonSubmit);
		GerarEvidencia("Formulario preenchido");
	}
}