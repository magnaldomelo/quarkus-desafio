package coopersystem.challenge.domain.model.external.cotacaoDolarDiaAPI;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class CotacaoValueDTO implements Serializable {
    private static final long serialVersionUID = -4000141931389601902L;

    private BigDecimal cotacaoCompra;
    private BigDecimal cotacaoVenda;
    private String dataHoraCotacao;
}
