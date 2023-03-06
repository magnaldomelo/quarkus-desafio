package coopersystem.challenge.domain.model.external.cotacaoDolarDiaAPI;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.ws.rs.QueryParam;
import java.io.Serializable;

@Data
@NoArgsConstructor
public class CotacaoDiaRequest implements Serializable {

    private static final long serialVersionUID = -4828820520487387900L;
    @QueryParam("@dataCotacao")
    private String dataCotacao;

    public CotacaoDiaRequest(String dataCotacao) {
        this.dataCotacao = "\'" + dataCotacao + "\'";
    }
}
