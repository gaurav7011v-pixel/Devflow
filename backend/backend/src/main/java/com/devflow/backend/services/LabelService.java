package com.devflow.backend.services;

import com.devflow.backend.dto.CreateLabelRequest;
import com.devflow.backend.dto.LabelResponse;
import com.devflow.backend.dto.UpdateLabelRequest;

import java.util.List;

public interface LabelService {
    LabelResponse createLabel(CreateLabelRequest request);
    LabelResponse updateLabel(Long labelId,UpdateLabelRequest request);
    List<LabelResponse> getAllLabels();
    LabelResponse getLabelById(Long labelId);
    void deleteLabel(Long labelId);
}
