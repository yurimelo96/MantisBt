package core;

import org.openqa.selenium.WebDriver;

public class BaseMap {
	protected WebDriver driver;

	public BaseMap(WebDriver driver) {
		this.driver = driver;
	}

	public Elemento criarElementoPorTexto(String texto) {
		return new Elemento(driver, "text", texto);
	}
}
