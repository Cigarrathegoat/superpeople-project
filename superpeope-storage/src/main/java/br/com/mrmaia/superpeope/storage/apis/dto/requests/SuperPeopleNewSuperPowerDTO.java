package br.com.mrmaia.superpeope.storage.apis.dto.requests;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SuperPeopleNewSuperPowerDTO {

    private String superPeopleName;

    private String superPowerName;
}
