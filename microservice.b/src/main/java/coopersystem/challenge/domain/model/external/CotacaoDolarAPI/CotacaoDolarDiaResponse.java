package coopersystem.challenge.domain.model.external.CotacaoDolarAPI;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CotacaoDolarDiaResponse implements Serializable {

    private String dataHoraCotacao;
    private BigDecimal cotacaoCompra;
    private BigDecimal cotacaoVenda;
}
