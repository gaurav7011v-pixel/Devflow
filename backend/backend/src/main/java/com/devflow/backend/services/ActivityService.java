package com.devflow.backend.services;

import com.devflow.backend.dto.RecentActivityResponse;
import com.devflow.backend.entity.ActivityAction;

public interface ActivityService {
    void log(ActivityAction action,String desc);
}
