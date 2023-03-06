package coopersystem.challenge.domain.model.external.cotacaoDolarDiaAPI;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class CotacaoDiaResponse implements Serializable {
    private static final long serialVersionUID = 4318562145236503443L;

    private Set<CotacaoValueDTO> value = Set.of();
}
