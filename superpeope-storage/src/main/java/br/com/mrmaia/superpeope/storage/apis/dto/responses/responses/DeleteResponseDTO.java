package br.com.mrmaia.superpeope.storage.apis.dto.responses.responses;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DeleteResponseDTO {

    private String deleteSuccessMessage;
}
