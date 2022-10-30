package jebsen.ms.generator.document.api.excel;

import jebsen.ms.generator.document.models.excel.SimpleExcel;
import jebsen.ms.generator.document.sdk.Endpoints;
import jebsen.ms.generator.document.sdk.interfaces.SdkExcelImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(Endpoints.excel)
public class SdkExcelController implements SdkExcelImpl {

    @Autowired
    private SimpleExcelService simpleExcelService;

    @CrossOrigin
    @PostMapping(Endpoints.excelSimple)
    @Override
    public ResponseEntity<ByteArrayResource> simpleExcel(@RequestBody SimpleExcel simpleExcel) {
        var fileName = simpleExcel.getFileName();
        var output = simpleExcelService.getExcel(simpleExcel);
        return ResponseEntity.ok()
                .header("Content-Disposition", String.format("attachment; filename=%s.xlsx", fileName))
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .contentLength(output.size())
                .body(new ByteArrayResource(output.toByteArray()));
    }
}
