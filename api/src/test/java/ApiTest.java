import jebsen.ms.generator.document.models.excel.SimpleExcel;
import jebsen.ms.generator.document.models.excel.SimpleExcelColumn;
import jebsen.ms.generator.document.models.excel.SimpleExcelSheet;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

@Slf4j
public class ApiTest {

    public static void main(String[] args) {
        new RestTemplate().exchange("https://jfw-0002.myshopify.com/admin/api/2021-04/orders.json", HttpMethod.GET, null, String.class);
    }
}
