package br.com.mrmaia.superpeope.storage.apis.dto.responses.responses;

import br.com.mrmaia.superpeope.storage.apis.dto.requests.SuperPeopleDTO;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SuperPeopleResponseDTO {

    public SuperPeopleDTO data;
}
