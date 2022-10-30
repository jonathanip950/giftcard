package jebsen.ms.generator.document.api.excel;

import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class SimpleExcelUtils {

    public static XSSFFont getHeaderCellFont(XSSFWorkbook workbook) {
        var font = workbook.createFont();
        font.setFontName("Arial");
        font.setFontHeightInPoints((short) 12);
        font.setBold(true);
        return font;
    }

    public static XSSFFont getDataCellFont(XSSFWorkbook workbook) {
        var font = workbook.createFont();
        font.setFontName("Arial");
        font.setFontHeightInPoints((short) 12);
        return font;
    }

    public static XSSFCellStyle getHeaderCellStyle(XSSFWorkbook workbook) {
        var cellStyle = workbook.createCellStyle();
        cellStyle.setFillForegroundColor(IndexedColors.LIGHT_GREEN.getIndex());
        cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        cellStyle.setBorderTop(BorderStyle.MEDIUM);
        cellStyle.setBorderBottom(BorderStyle.MEDIUM);
        cellStyle.setBorderLeft(BorderStyle.MEDIUM);
        cellStyle.setBorderRight(BorderStyle.MEDIUM);
        cellStyle.setFont(getHeaderCellFont(workbook));
        return cellStyle;
    }

    public static XSSFCellStyle getDataCellStyle(XSSFWorkbook workbook) {
        var cellStyle = workbook.createCellStyle();
        cellStyle.setWrapText(false);
        cellStyle.setBorderTop(BorderStyle.MEDIUM);
        cellStyle.setBorderBottom(BorderStyle.MEDIUM);
        cellStyle.setBorderLeft(BorderStyle.MEDIUM);
        cellStyle.setBorderRight(BorderStyle.MEDIUM);
        cellStyle.setFont(getDataCellFont(workbook));
        return cellStyle;
    }

    public static XSSFCellStyle getDataCellStyleSecondary(XSSFWorkbook workbook) {
        var cellStyle = workbook.createCellStyle();
        cellStyle.cloneStyleFrom(getDataCellStyle(workbook));
        cellStyle.setFillForegroundColor(IndexedColors.CORAL.getIndex());
        cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        return cellStyle;
    }
}
