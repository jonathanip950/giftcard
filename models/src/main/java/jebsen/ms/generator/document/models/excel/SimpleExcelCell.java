package jebsen.ms.generator.document.models.excel;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Data;


@Data
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class SimpleExcelCell {
    private String value;
    private Boolean isError; // nullable
}

