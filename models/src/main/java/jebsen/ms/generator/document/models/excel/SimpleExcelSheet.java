package jebsen.ms.generator.document.models.excel;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.Map;
import java.util.SortedMap;


@Data
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class SimpleExcelSheet {
    private String sheetName;
    private Integer index;
    private Map<String, SimpleExcelColumn> columns;
    private List<SortedMap<String, SimpleExcelCell>> rows;
}
