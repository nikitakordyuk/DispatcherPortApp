package com.port.DispatcherPortApp.services;

import com.port.DispatcherPortApp.models.General;
import com.port.DispatcherPortApp.util.FieldsNames;
import org.apache.poi.xwpf.usermodel.*;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.*;
import org.springframework.dao.IncorrectResultSizeDataAccessException;

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
            try {
                generals.add(generalService.findByCarNumber(carNumber));
            } catch (IncorrectResultSizeDataAccessException exception) {
                generals.addAll(generalService.findMoreThanOneByCarNumber(carNumber));
            }
        }

        XWPFDocument document = new XWPFDocument();
        changeOrientation(document, "landscape");
        document.removeBodyElement(0);

        XWPFTable table = document.createTable(generals.size() + 1, 10);

        CTSectPr sectPr = document.getDocument().getBody().getSectPr();
        if (sectPr == null) sectPr = document.getDocument().getBody().addNewSectPr();

        CTPageMar pageMar = sectPr.getPgMar();
        if (pageMar == null) pageMar = sectPr.addNewPgMar();

        pageMar.setLeft(BigInteger.valueOf(360));
        pageMar.setRight(BigInteger.valueOf(360));
        pageMar.setTop(BigInteger.valueOf(360));
        pageMar.setBottom(BigInteger.valueOf(360));
        pageMar.setFooter(BigInteger.valueOf(360));
        pageMar.setHeader(BigInteger.valueOf(360));
        pageMar.setGutter(BigInteger.valueOf(0));

        Object[] objects = FieldsNames.fieldsNames().keySet().toArray();

        for (int j = 0; j < 10; j++) {
            XWPFParagraph xwpfParagraph = table.getRow(0).getCell(j).getParagraphs().get(0);

            table.getRow(0).getCell(j).setVerticalAlignment(XWPFTableCell.XWPFVertAlign.CENTER);
            xwpfParagraph.setAlignment(ParagraphAlignment.CENTER);

            XWPFRun run1 = xwpfParagraph.createRun();

            run1.setFontSize(14);
            run1.setFontFamily("Times New Roman");
            run1.setBold(true);
            run1.setText(objects[j].toString());
        }
        for (int i = 1; i < table.getNumberOfRows(); i++) {

            for (int y = 0; y < 10; y++) {
                XWPFRun run1 = applyStyles(i, y, table).createRun();
                switch (y) {
                    case 0:
                        run1.setText(generals.get(i - 1).getCarNumber());
                        run1.setFontFamily("Times New Roman");
                        run1.setFontSize(13);

                        break;
                    case 1:
                        run1.setText(generals.get(i - 1).getTrailerNumber());
                        run1.setFontFamily("Times New Roman");
                        run1.setFontSize(13);

                        break;
                    case 2:
                        run1.setText(generals.get(i - 1).getDriverLicenseNumber());
                        run1.setFontFamily("Times New Roman");
                        run1.setFontSize(13);

                        break;
                    case 3:
                        run1.setText(generals.get(i - 1).getNomenclature());
                        run1.setFontFamily("Times New Roman");
                        run1.setFontSize(13);

                        break;
                    case 4:
                        run1.setText(generals.get(i - 1).getSender());
                        run1.setFontFamily("Times New Roman");
                        run1.setFontSize(13);

                        break;
                    case 5:
                        run1.setText(generals.get(i - 1).getVehicleType());
                        run1.setFontFamily("Times New Roman");
                        run1.setFontSize(13);

                        break;
                    case 6:
                        run1.setText(generals.get(i - 1).getPhoneNumber());
                        run1.setFontFamily("Times New Roman");
                        run1.setFontSize(13);

                        break;
                    case 7:
                        run1.setText(generals.get(i - 1).getFullName());
                        run1.setFontFamily("Times New Roman");
                        run1.setFontSize(13);

                        break;
                    case 8:
                        run1.setText(String.valueOf(generals.get(i - 1).getDateOfCreation()));
                        run1.setFontFamily("Times New Roman");
                        run1.setFontSize(13);

                        break;
                    case 9:
                        String s = generals.get(i - 1).isCome() ? "Да" : "Нет";
                        run1.setText(s);
                        run1.setFontFamily("Times New Roman");
                        run1.setFontSize(13);

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

    public FileOutputStream createDocxFromAll(List<General> generals) throws IOException {
        XWPFDocument document = new XWPFDocument();
        changeOrientation(document, "landscape");
        document.removeBodyElement(0);

        XWPFTable table = document.createTable(generals.size() + 1, 10);

        CTSectPr sectPr = document.getDocument().getBody().getSectPr();
        if (sectPr == null) sectPr = document.getDocument().getBody().addNewSectPr();

        CTPageMar pageMar = sectPr.getPgMar();
        if (pageMar == null) pageMar = sectPr.addNewPgMar();

        pageMar.setLeft(BigInteger.valueOf(360));
        pageMar.setRight(BigInteger.valueOf(360));
        pageMar.setTop(BigInteger.valueOf(360));
        pageMar.setBottom(BigInteger.valueOf(360));
        pageMar.setFooter(BigInteger.valueOf(360));
        pageMar.setHeader(BigInteger.valueOf(360));
        pageMar.setGutter(BigInteger.valueOf(0));

        Object[] objects = FieldsNames.fieldsNames().keySet().toArray();

        for (int j = 0; j < 10; j++) {
            XWPFParagraph xwpfParagraph = table.getRow(0).getCell(j).getParagraphs().get(0);

            table.getRow(0).getCell(j).setVerticalAlignment(XWPFTableCell.XWPFVertAlign.CENTER);
            xwpfParagraph.setAlignment(ParagraphAlignment.CENTER);

            XWPFRun run1 = xwpfParagraph.createRun();

            run1.setFontSize(14);
            run1.setFontFamily("Times New Roman");
            run1.setBold(true);
            run1.setText(objects[j].toString());
        }
        for (int i = 1; i < table.getNumberOfRows(); i++) {

            for (int y = 0; y < 10; y++) {
                XWPFRun run1 = applyStyles(i, y, table).createRun();
                switch (y) {
                    case 0:
                        run1.setText(generals.get(i - 1).getCarNumber());
                        run1.setFontFamily("Times New Roman");
                        run1.setFontSize(13);

                        break;
                    case 1:
                        run1.setText(generals.get(i - 1).getTrailerNumber());
                        run1.setFontFamily("Times New Roman");
                        run1.setFontSize(13);

                        break;
                    case 2:
                        run1.setText(generals.get(i - 1).getDriverLicenseNumber());
                        run1.setFontFamily("Times New Roman");
                        run1.setFontSize(13);

                        break;
                    case 3:
                        run1.setText(generals.get(i - 1).getNomenclature());
                        run1.setFontFamily("Times New Roman");
                        run1.setFontSize(13);

                        break;
                    case 4:
                        run1.setText(generals.get(i - 1).getSender());
                        run1.setFontFamily("Times New Roman");
                        run1.setFontSize(13);

                        break;
                    case 5:
                        run1.setText(generals.get(i - 1).getVehicleType());
                        run1.setFontFamily("Times New Roman");
                        run1.setFontSize(13);

                        break;
                    case 6:
                        run1.setText(generals.get(i - 1).getPhoneNumber());
                        run1.setFontFamily("Times New Roman");
                        run1.setFontSize(13);

                        break;
                    case 7:
                        run1.setText(generals.get(i - 1).getFullName());
                        run1.setFontFamily("Times New Roman");
                        run1.setFontSize(13);

                        break;
                    case 8:
                        run1.setText(String.valueOf(generals.get(i - 1).getDateOfCreation()));
                        run1.setFontFamily("Times New Roman");
                        run1.setFontSize(13);

                        break;
                    case 9:
                        String s = generals.get(i - 1).isCome() ? "Да" : "Нет";
                        run1.setText(s);
                        run1.setFontFamily("Times New Roman");
                        run1.setFontSize(13);

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

    private static XWPFParagraph applyStyles(int rowNumber, int cellNumber, XWPFTable table) {
        XWPFParagraph paragraph;

        paragraph = table.getRow(rowNumber).getCell(cellNumber).getParagraphs().get(0);
        paragraph.setAlignment(ParagraphAlignment.CENTER);
        table.getRow(rowNumber).getCell(cellNumber).setVerticalAlignment(XWPFTableCell.XWPFVertAlign.CENTER);

        return paragraph;
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
