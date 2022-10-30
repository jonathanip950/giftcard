package jebsen.ms.generator.document.api.excel;

import jebsen.ms.generator.document.models.excel.SimpleExcel;
import lombok.Cleanup;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.Optional;

@Slf4j
@Service
public class SimpleExcelService {

    private String excelType = "excel (simple)";

    public <Excel extends SimpleExcel> ByteArrayOutputStream getExcel(Excel excel) {
        var outputStream = new ByteArrayOutputStream();

        var sheets = excel.getSheets();
        var fileName = excel.getFileName();
        log.info("Rendering {}. File name: {} ; Sheet(s) Count: {}", excelType, fileName, excel.getSheets().size());

        try {
            try (XSSFWorkbook workbook = new XSSFWorkbook()) {
                var headerCellStyle = SimpleExcelUtils.getHeaderCellStyle(workbook);
                var dataCellStyle = SimpleExcelUtils.getDataCellStyle(workbook);
                var dataCellStyleSecondary = SimpleExcelUtils.getDataCellStyleSecondary(workbook);

                sheets.forEach(simpleExcelSheet -> {
                    var columns = simpleExcelSheet.getColumns();
                    var rows = simpleExcelSheet.getRows();
                    log.info("Rendering sheet for {}. File name: {} ; Index: {} ; Columns Count: {} ; Rows Count: {}", excelType, fileName, simpleExcelSheet.getIndex(), columns.size(), rows.size());

                    var sheet = workbook.createSheet(simpleExcelSheet.getSheetName());

                    if (rows.size() > 0) {
                        var columnsSet = new ArrayList<>(columns.entrySet());
                        for (var b = 0; b < rows.size(); b++) {
                            var row = rows.get(b);
                            var dataRow = sheet.createRow(b + 1);
                            for (var col : columnsSet) {
                                var dataCell = dataRow.createCell(col.getValue().getIndex());
                                dataCell.setCellStyle(Optional.ofNullable(row.get(col.getKey()).getIsError()).orElse(false) ? dataCellStyleSecondary : dataCellStyle);
                                dataCell.setCellValue(row.get(col.getKey()).getValue());
                            }
                        }
                        var header = sheet.createRow(0);
                        for (var column : columnsSet) {
                            var columnIndex = column.getValue().getIndex();
                            var headerCell = header.createCell(columnIndex);
                            headerCell.setCellValue(column.getValue().getDisplayName());
                            headerCell.setCellStyle(headerCellStyle);
                            if (Optional.ofNullable(column.getValue().getAutoResize()).orElse(true)) {
                                sheet.autoSizeColumn(columnIndex);
                            }
                        }
                    }

                    workbook.setSheetOrder(sheet.getSheetName(), simpleExcelSheet.getIndex());
                });

                workbook.write(outputStream);
                log.info("Done {} generation, size: {}", excelType, outputStream.size());
            }
        } catch (Exception e) {
            log.error("Failed to create {}", excelType, e);
        }

        return outputStream;
    }
}
