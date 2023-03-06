package coopersystem.challenge.domain.model.internal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CotacaoResponse implements Serializable {
    private static final long serialVersionUID = -7132216509537020551L;

    private Cotacao cotacaoSolicitada;
    private Cotacao cotacaoUltimoDiaUtilAnterior;
}
