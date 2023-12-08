package com.health.contracts.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.health.contracts.entity.VisitationEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class GetLogsResp implements Serializable {
    @JsonProperty("visitationLog")
    List<VisitationEntity> visitationLog;
    @JsonProperty("completedReqs")
    List<VisitationEntity> completedReqs;

}
