package com.devflow.backend.services;

import com.devflow.backend.dto.CheckListResponse;
import com.devflow.backend.dto.CreateCheckListRequest;
import com.devflow.backend.dto.UpdateCheckListRequest;

import java.util.List;

public interface CheckListService {
    CheckListResponse createCheckListItem(Long taskId, CreateCheckListRequest request);
    List<CheckListResponse> getCheckListItems(Long taskId);
    CheckListResponse updateCheckListItem(Long itemId, UpdateCheckListRequest request);
    void deleteCheckListItem(Long itemId);
}
