package br.com.mrmaia.superpeope.storage.apis.dto.responses.responses;

import br.com.mrmaia.superpeope.storage.apis.dto.requests.SuperPowerDTO;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SuperPowerResponseDTO {

    public SuperPowerDTO data;
}
