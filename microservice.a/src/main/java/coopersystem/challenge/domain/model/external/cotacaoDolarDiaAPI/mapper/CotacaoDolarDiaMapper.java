package coopersystem.challenge.domain.model.external.cotacaoDolarDiaAPI.mapper;

import coopersystem.challenge.domain.model.internal.response.CotacaoDolarDiaResponse;
import org.mapstruct.Mapper;

import coopersystem.challenge.domain.model.external.cotacaoDolarDiaAPI.CotacaoValueDTO;

@Mapper
public interface CotacaoDolarDiaMapper {

    CotacaoDolarDiaResponse toCotacaoResponse(CotacaoValueDTO cotacaoValueDTO);
}
