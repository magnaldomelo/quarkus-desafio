package coopersystem.challenge.infrastructure.api;

import coopersystem.challenge.domain.model.external.cotacaoDolarDiaAPI.CotacaoDiaRequest;
import coopersystem.challenge.domain.model.external.cotacaoDolarDiaAPI.CotacaoDiaResponse;
import coopersystem.challenge.domain.model.external.cotacaoDolarDiaAPI.CotacaoValueDTO;
import io.quarkus.test.Mock;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import javax.enterprise.context.ApplicationScoped;
import java.math.BigDecimal;
import java.util.Set;

@Mock
@ApplicationScoped
@RestClient
public class CotacaoDolarDiaAPIMock implements CotacaoDolarDiaAPI {

    public static final CotacaoValueDTO COTACAO_VALUE = new CotacaoValueDTO(
            new BigDecimal("5.04730"), new BigDecimal("5.04790"), "2023-03-03 12:00:00.000");

    @Override
    public CotacaoDiaResponse getCotacaoDolarDia(CotacaoDiaRequest cotacaoDiaRequest) {
        return new CotacaoDiaResponse(Set.of(COTACAO_VALUE));
    }
}
