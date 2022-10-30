package jebsen.ms.generator.document.models.excel;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Data;


@Data
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class SimpleExcelColumn {
    private String displayName;
    private Boolean autoResize; // nullable
    private Integer index;


}

