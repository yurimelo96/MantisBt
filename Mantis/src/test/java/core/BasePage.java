package core;

import java.util.ArrayList;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import util.EvidenceManager;
import util.Globalinformation;

public class BasePage {
	protected String SMALL = "10", MIDDLE = "30", BIG = "60";
	private String ERR = "ERROR";
	protected WebDriver driver;
	protected Globalinformation info;

	public BasePage() {
		this.driver = BaseTest.driver;
		info = new Globalinformation();
	}

	public WebDriver getDriver() {
		if (driver == null)
			driver = BaseTest.driver;
		return driver;
	}

	public Boolean esperarElemento(Elemento e, String tempo) {
		try {
			new WebDriverWait(driver, Integer.parseInt(tempo))
					.until(ExpectedConditions.presenceOfElementLocated(e.getBy()));

		} catch (Exception ex) {
			System.err.println(ex.getMessage());
			return false;
		}
		return true;
	}

	public Boolean validarElemento(Elemento e) throws Exception {
		try {
			new WebDriverWait(driver, 0).until(ExpectedConditions.elementToBeClickable(e.getBy()));

		} catch (Exception ex) {
			EvidenceManager.GerarEvidencia(ERR + " Item nao encontrado");
			System.err.println(ex.getMessage());
			return false;
		}
		return true;
	}

	public Boolean navegar(String url) throws Exception {
		try {
			driver.navigate().to(url);
		} catch (Exception ex) {
			EvidenceManager.GerarEvidencia(ERR + " Impossivel acessar url");
			System.err.println(ex.getMessage());
			return false;
		}
		return true;
	}

	public Boolean clicar(Elemento e) throws Exception {
		try {
			rolarAoElemento(e);
			e.click();
		} catch (Exception ex) {
			EvidenceManager.GerarEvidencia(ERR + " nao foi possivel clicar");
			System.err.println(ex.getMessage());
			return false;
		}
		return true;
	}

	public Boolean escrever(Elemento e, String texto) throws Exception {
		try {
			rolarAoElemento(e);
			e.escrever(texto);
		} catch (Exception ex) {
			EvidenceManager.GerarEvidencia(ERR + " nao foi possivel preencher o campo");
			System.err.println(ex.getMessage());
			return false;
		}
		return true;
	}

	public Boolean trocarAba() {
		try {
			ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
			if (tabs.size() > 1) {
				String handleName = "";
				for (String tab : tabs)
					handleName = tab;
				driver.switchTo().window(handleName);
				driver.manage().window().maximize();
			}
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	public void rolarAoElemento(Elemento e) {
		e.scrollToElement();
	}

	public String pegarInformacaoAtributo(Elemento e, String atributo) {
		return e.pegarAtributo(atributo);
	}

	public void fecharAbas() {
		ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
		if (tabs.size() > 1) {
			driver.close();
			trocarAba();
			fecharAbas();
		} else {
			String handleName = "";
			for (String tab : tabs)
				handleName = tab;
			driver.switchTo().window(handleName);
		}
	}
}
