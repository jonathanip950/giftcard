package jebsen.ms.generator.document.models.excel;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Data;

import java.util.List;


@Data
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class SimpleExcel {
    private String fileName;
    private List<SimpleExcelSheet> sheets;
}
