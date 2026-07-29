package com.devflow.backend.services;

import com.devflow.backend.dto.CreateLabelRequest;
import com.devflow.backend.dto.LabelResponse;
import com.devflow.backend.dto.UpdateLabelRequest;
import com.devflow.backend.entity.Label;
import com.devflow.backend.entity.Task;
import com.devflow.backend.repository.LabelRepository;
import com.devflow.backend.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LabelServiceImpl implements LabelService{
    private final LabelRepository labelRepository;
    private final CurrentUserService currentUserService;
    private final TaskRepository taskRepository;

    public LabelServiceImpl(LabelRepository labelRepository, CurrentUserService currentUserService, TaskRepository taskRepository) {
        this.labelRepository = labelRepository;
        this.currentUserService = currentUserService;
        this.taskRepository = taskRepository;
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

    @Override
    public LabelResponse attachLabelToTask(Long taskId,Long labelId) {
        Task task=currentUserService.getTaskByIdAndOwner(taskId);
        Label label=currentUserService.getLabelByByIdAndOwner(labelId);
        if (!task.getLabels().contains(label)) {
            task.getLabels().add(label);
        }
        taskRepository.save(task);
        return mapToLabelResponse(label);
    }

    @Override
    public void removeLabelFromTask( Long taskId,Long labelId) {
        Task task=currentUserService.getTaskByIdAndOwner(taskId);
        Label label=currentUserService.getLabelByByIdAndOwner(labelId);

        task.getLabels().remove(label);
        taskRepository.save(task);
    }

    @Override
    public List<LabelResponse> getLabelsByTask(Long taskId) {
        Task task=currentUserService.getTaskByIdAndOwner(taskId);
        return task.getLabels()
                .stream()
                .map(this::mapToLabelResponse)
                .toList();
    }

    private LabelResponse mapToLabelResponse(Label label1) {
        LabelResponse labelResponse=new LabelResponse();
        labelResponse.setId(label1.getId());
        labelResponse.setTagName(label1.getTagName());

        return labelResponse;
    }
}
