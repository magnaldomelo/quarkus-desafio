package coopersystem.challenge.domain.service;

import coopersystem.challenge.domain.model.external.cotacaoDolarDiaAPI.CotacaoValueDTO;

public interface CotacaoDolarService {
    CotacaoValueDTO findCotacaoDolar(String dateCotacao);
}
