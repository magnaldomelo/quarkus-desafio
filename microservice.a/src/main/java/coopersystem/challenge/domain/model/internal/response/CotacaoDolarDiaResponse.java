package coopersystem.challenge.domain.model.internal.response;

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

    private static final long serialVersionUID = -7132216509537020551L;

    private String dataHoraCotacao;
    private BigDecimal cotacaoCompra;
    private BigDecimal cotacaoVenda;
}
