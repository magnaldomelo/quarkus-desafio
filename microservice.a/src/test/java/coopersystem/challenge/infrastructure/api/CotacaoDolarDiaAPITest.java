package coopersystem.challenge.infrastructure.api;

import coopersystem.challenge.domain.model.external.cotacaoDolarDiaAPI.CotacaoDiaRequest;
import coopersystem.challenge.domain.model.external.cotacaoDolarDiaAPI.CotacaoDiaResponse;
import io.quarkus.test.junit.QuarkusTest;
import lombok.val;
import org.apache.commons.lang3.time.DateUtils;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.jboss.resteasy.util.DateUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;
import java.time.LocalDate;
import java.util.Date;
import java.util.Set;

import static coopersystem.challenge.infrastructure.api.CotacaoDolarDiaAPIMock.COTACAO_VALUE;

@QuarkusTest
public class CotacaoDolarDiaAPITest {

    @Inject
    @RestClient
    CotacaoDolarDiaAPI cotacaoDolarDiaAPI;

    @Test
    void testGetCotacaoDolarDia() {
        val cotacaoDiaRequest = new CotacaoDiaRequest("03-03-2023");
        val cotacaoDiaResponse = new CotacaoDiaResponse(Set.of(COTACAO_VALUE));
        Assertions.assertEquals(cotacaoDiaResponse, cotacaoDolarDiaAPI.getCotacaoDolarDia(cotacaoDiaRequest));
    }
}
