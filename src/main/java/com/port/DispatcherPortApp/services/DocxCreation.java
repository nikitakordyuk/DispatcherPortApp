package com.port.DispatcherPortApp.services;

import com.port.DispatcherPortApp.models.General;
import com.port.DispatcherPortApp.util.FieldsNames;
import com.port.DispatcherPortApp.util.FileDownloader;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.*;

import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class DocxCreation {
    private final GeneralService generalService;

    public DocxCreation(GeneralService generalService) {
        this.generalService = generalService;
    }

    public FileOutputStream createDocx(List<String> print) throws IOException {
        List<General> generals = new ArrayList<>();
        for (String carNumber : print) {
            generals.add(generalService.findByCarNumber(carNumber));
        }

        XWPFDocument document = new XWPFDocument();
        changeOrientation(document, "landscape");
        document.removeBodyElement(0);

        XWPFTable table = document.createTable(generals.size() + 1, 10);

        Object[] objects = FieldsNames.fieldsNames().keySet().toArray();

        for (int j = 0; j < 10; j++) {
            table.getRow(0).getCell(j).setText(objects[j].toString());
        }
        for (int i = 1; i < table.getNumberOfRows(); i++) {

            for (int y = 0; y < 10; y++) {
                switch (y) {
                    case 0:
                        table.getRow(i).getCell(y).setText(generals.get(i - 1).getCarNumber());
                        break;
                    case 1:
                        table.getRow(i).getCell(y).setText(generals.get(i - 1).getTrailerNumber());
                        break;
                    case 2:
                        table.getRow(i).getCell(y).setText(generals.get(i - 1).getDriverLicenseNumber());
                        break;
                    case 3:
                        table.getRow(i).getCell(y).setText(generals.get(i - 1).getNomenclature());
                        break;
                    case 4:
                        table.getRow(i).getCell(y).setText(generals.get(i - 1).getSender());
                        break;
                    case 5:
                        table.getRow(i).getCell(y).setText(generals.get(i - 1).getVehicleType());
                        break;
                    case 6:
                        table.getRow(i).getCell(y).setText(generals.get(i - 1).getPhoneNumber());
                        break;
                    case 7:
                        table.getRow(i).getCell(y).setText(generals.get(i - 1).getFullName());
                        break;
                    case 8:
                        table.getRow(i).getCell(y).setText(String.valueOf(generals.get(i - 1).getDateOfCreation()));
                        break;
                    case 9:
                        String s = generals.get(i - 1).isCome() ? "Да" : "Нет";
                        table.getRow(i).getCell(y).setText(s);
                        break;
                }
            }
        }

        FileOutputStream out = new FileOutputStream("output/" + "createdWord2" + "_" + ".docx");

        document.write(out);
        out.close();
        document.close();

        return out;

        //FileDownloader.download("/output/createdWord2_.docx");
    }

    private static void changeOrientation(XWPFDocument document, String orientation) {
        CTDocument1 doc = document.getDocument();
        CTBody body = doc.getBody();
        CTSectPr section = body.addNewSectPr();
        XWPFParagraph para = document.createParagraph();
        CTP ctp = para.getCTP();
        CTPPr br = ctp.addNewPPr();
        br.setSectPr(section);
        CTPageSz pageSize = section.addNewPgSz();

        if (orientation.equals("landscape")) {
            pageSize.setOrient(STPageOrientation.LANDSCAPE);
            pageSize.setW(BigInteger.valueOf(842 * 20));
            pageSize.setH(BigInteger.valueOf(595 * 20));
        } else {
            pageSize.setOrient(STPageOrientation.PORTRAIT);
            pageSize.setH(BigInteger.valueOf(842 * 20));
            pageSize.setW(BigInteger.valueOf(595 * 20));
        }
    }
}
