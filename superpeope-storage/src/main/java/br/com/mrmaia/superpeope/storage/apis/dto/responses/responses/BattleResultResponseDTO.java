package br.com.mrmaia.superpeope.storage.apis.dto.responses.responses;

import br.com.mrmaia.superpeope.storage.apis.dto.requests.BattleResultDTO;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BattleResultResponseDTO {

    public BattleResultDTO data;
}
