package coopersystem.challenge.domain.model.internal;

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
public class Cotacao implements Serializable {

    private static final long serialVersionUID = 6767009543899690761L;

    private String dataHoraCotacao;
    private BigDecimal cotacaoCompra;
    private BigDecimal cotacaoVenda;
}
