package jebsen.ms.generator.document.sdk.interfaces;

import jebsen.ms.generator.document.models.excel.SimpleExcel;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.ResponseEntity;

public interface SdkExcelImpl {
    ResponseEntity<ByteArrayResource> simpleExcel(SimpleExcel simpleExcel);
}
