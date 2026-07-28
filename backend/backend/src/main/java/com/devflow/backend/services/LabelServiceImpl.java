package com.devflow.backend.services;

import com.devflow.backend.dto.CreateLabelRequest;
import com.devflow.backend.dto.LabelResponse;
import com.devflow.backend.dto.UpdateLabelRequest;
import com.devflow.backend.entity.Label;
import com.devflow.backend.repository.LabelRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LabelServiceImpl implements LabelService{
    private final LabelRepository labelRepository;
    private final CurrentUserService currentUserService;

    public LabelServiceImpl(LabelRepository labelRepository, CurrentUserService currentUserService) {
        this.labelRepository = labelRepository;
        this.currentUserService = currentUserService;
    }

    @Override
    public LabelResponse createLabel(CreateLabelRequest request) {
        Label label=new Label();
        label.setTagName(request.getTagName());
        label.setOwner(currentUserService.getCurrentUser());
        Label savedLabel=labelRepository.save(label);
        return mapToLabelResponse(savedLabel);
    }



    @Override
    public LabelResponse updateLabel(Long labelId,UpdateLabelRequest request) {
        Label label=currentUserService.getLabelByByIdAndOwner(labelId);
        label.setTagName(request.getTagName());
        Label saveLabel=labelRepository.save(label);
        return mapToLabelResponse(saveLabel);
    }

    @Override
    public List<LabelResponse> getAllLabels() {
        return labelRepository.findByOwner(currentUserService.getCurrentUser())
                .stream()
                .map(this::mapToLabelResponse)
                .toList();
    }

    @Override
    public LabelResponse getLabelById(Long labelId) {
        Label label=currentUserService.getLabelByByIdAndOwner(labelId);
        return mapToLabelResponse(label);
    }

    @Override
    public void deleteLabel(Long labelId) {
        Label label=currentUserService.getLabelByByIdAndOwner(labelId);
        labelRepository.delete(label);
    }
    private LabelResponse mapToLabelResponse(Label label1) {
        LabelResponse labelResponse=new LabelResponse();
        labelResponse.setId(label1.getId());
        labelResponse.setTagName(label1.getTagName());

        return labelResponse;
    }
}
