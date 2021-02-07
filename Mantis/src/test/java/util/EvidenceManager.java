package util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import org.apache.commons.io.FileUtils;
import org.apache.poi.util.Units;
import org.apache.poi.xwpf.usermodel.BreakType;
import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import core.BaseTest;
import io.cucumber.java.pt.E;

public class EvidenceManager {
    static String path = "";

    public EvidenceManager() {
        path = System.getProperty("user.dir") + "/Output/" + BaseTest.currentExecutionId;
    }

    public static void GerarEvidencia(String nome) throws IOException {
        File scrFile = ((TakesScreenshot) BaseTest.driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile, new File(path + "/" + BaseTest.evidId++ + "_" + nome.replace(" ", "_") + ".png"));
    }

    public static void generateDoc() {

        XWPFDocument doc = null;
        FileOutputStream fileOutputStream = null;
        try {

            doc = new XWPFDocument();
            File fileToBeCreated = new File(path);
            String[] images = getEvidences();
            XWPFParagraph title = doc.createParagraph();
            XWPFRun run = title.createRun();
            run.setText(BaseTest.scenarioName);
            run.setBold(true);
            title.setAlignment(ParagraphAlignment.CENTER);
            run.addBreak(BreakType.PAGE);
            run.setText("Evidencias de teste");
            run.addBreak();
            run.addBreak();
            int i = 0;
            for (String im : images) {
                FileInputStream is = new FileInputStream(path + "/" + im);
                run.setText(im.replace("_", " ").replace(".png", ""));
                run.addBreak();
                run.addPicture(is, XWPFDocument.PICTURE_TYPE_JPEG, im, Units.toEMU(400), Units.toEMU(400)); // 200x200
                is.close();
                if (i++ < images.length)
                    run.addBreak(BreakType.PAGE);

            }
            fileOutputStream = new FileOutputStream(fileToBeCreated + "/Evidence.docx");

            doc.write(fileOutputStream);

            System.out.println("Documento de evidencias gerado com sucesso !!!");
        } catch (Exception e) {
            System.out.println("Houve um erro ao gerar documento de evidencias");
        } finally {
            try {
                if (fileOutputStream != null) {
                    fileOutputStream.close();
                }
            } catch (Exception ex) {
            }
        }
    }

    @E("gerarDoc")
    private static String[] getEvidences() {
        File f = new File(path);
        String[] images = f.list();
        Arrays.sort(images);
        return images;

    }
}
