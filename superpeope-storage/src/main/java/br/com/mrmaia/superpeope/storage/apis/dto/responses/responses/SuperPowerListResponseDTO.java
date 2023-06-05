package br.com.mrmaia.superpeope.storage.apis.dto.responses.responses;

import br.com.mrmaia.superpeope.storage.apis.dto.requests.SuperPowerDTO;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class SuperPowerListResponseDTO {

    public List<SuperPowerDTO> data;
}
