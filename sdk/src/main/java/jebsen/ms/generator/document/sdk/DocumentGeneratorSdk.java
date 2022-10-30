package jebsen.ms.generator.document.sdk;

import jebsen.ms.generator.document.models.excel.SimpleExcel;
import jebsen.ms.generator.document.sdk.interfaces.SdkExcelImpl;
import lombok.Builder;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.client.RestTemplate;

public class DocumentGeneratorSdk implements SdkExcelImpl {
    private String domain;
    private RestTemplate restTemplate;

    @Builder
    public DocumentGeneratorSdk(String domain, RestTemplate restTemplate) {
        this.domain = domain;
        this.restTemplate = null == restTemplate ? new RestTemplate() : restTemplate;
    }

    private <T> ResponseEntity<T> getRestTemplate(String endpoints, HttpMethod httpMethod, Object body, Class<T> responseType) {
        var httpEntity = new HttpEntity<>(body);
        return restTemplate.exchange(String.format("%s/%s", domain, endpoints), httpMethod, httpEntity, responseType);
    }

    @Override
    public ResponseEntity<ByteArrayResource> simpleExcel(SimpleExcel simpleExcel) {
        return getRestTemplate(String.format("%s/%s", Endpoints.excel, Endpoints.excelSimple), HttpMethod.POST, simpleExcel, ByteArrayResource.class);
    }
}
