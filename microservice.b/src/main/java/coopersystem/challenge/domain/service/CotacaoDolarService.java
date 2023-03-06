package coopersystem.challenge.domain.service;

import coopersystem.challenge.domain.model.internal.CotacaoResponse;

public interface CotacaoDolarService {
    CotacaoResponse findCotacaoDolar(String dataCotacao);
}
