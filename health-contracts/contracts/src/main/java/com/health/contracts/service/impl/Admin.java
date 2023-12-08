package com.health.contracts.service.impl;

import com.health.contracts.model.GetLogsResp;
import com.health.contracts.model.LogVisitationReq;
import com.health.contracts.model.VisitationLog;

public interface Admin {
	void logVisitation(LogVisitationReq req);
    GetLogsResp getPendingVisits(String provider);
      
}
