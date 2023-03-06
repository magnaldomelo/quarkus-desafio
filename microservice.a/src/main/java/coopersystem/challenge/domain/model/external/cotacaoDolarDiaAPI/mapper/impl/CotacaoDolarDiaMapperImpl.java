package coopersystem.challenge.domain.model.external.cotacaoDolarDiaAPI.mapper.impl;

import coopersystem.challenge.domain.model.external.cotacaoDolarDiaAPI.CotacaoValueDTO;
import coopersystem.challenge.domain.model.external.cotacaoDolarDiaAPI.mapper.CotacaoDolarDiaMapper;
import coopersystem.challenge.domain.model.internal.response.CotacaoDolarDiaResponse;
import io.smallrye.mutiny.Uni;

import javax.annotation.processing.Generated;
import javax.enterprise.context.ApplicationScoped;
@Generated(
        value = "org.mapstruct.ap.MappingProcessor",
        date = "2023-03-05T10:39:31-0300",
        comments = "version: 1.4.2.Final, compiler: javac, environment: Java 11.0.18 (Ubuntu)"
)
@ApplicationScoped
public class CotacaoDolarDiaMapperImpl implements CotacaoDolarDiaMapper {

    @Override
    public CotacaoDolarDiaResponse toCotacaoResponse(CotacaoValueDTO cotacaoValueDTO) {
        if ( cotacaoValueDTO == null ) {
            return null;
        }

        CotacaoDolarDiaResponse cotacaoDolarDiaResponse = new CotacaoDolarDiaResponse();

        cotacaoDolarDiaResponse.setCotacaoCompra(cotacaoValueDTO.getCotacaoCompra());
        cotacaoDolarDiaResponse.setCotacaoVenda(cotacaoValueDTO.getCotacaoVenda());
        cotacaoDolarDiaResponse.setDataHoraCotacao(cotacaoValueDTO.getDataHoraCotacao());

        return cotacaoDolarDiaResponse;
    }
}
