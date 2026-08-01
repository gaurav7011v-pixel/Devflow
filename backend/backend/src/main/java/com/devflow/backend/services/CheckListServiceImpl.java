package com.devflow.backend.services;

import com.devflow.backend.dto.CheckListResponse;
import com.devflow.backend.dto.CreateCheckListRequest;
import com.devflow.backend.dto.UpdateCheckListRequest;
import com.devflow.backend.entity.ActivityAction;
import com.devflow.backend.entity.CheckList;
import com.devflow.backend.entity.Task;
import com.devflow.backend.repository.CheckListRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CheckListServiceImpl implements CheckListService{
    private final CurrentUserService currentUserService;
    private final CheckListRepository checkListRepository;
    private final ActivityService activityService;

    public CheckListServiceImpl(CurrentUserService currentUserService, CheckListRepository checkListRepository, ActivityService activityService) {
        this.currentUserService = currentUserService;
        this.checkListRepository = checkListRepository;
        this.activityService = activityService;
    }

    @Override
    public CheckListResponse createCheckListItem(Long taskId, CreateCheckListRequest request) {
        CheckList checkList=new CheckList();
        Task task=currentUserService.getTaskByIdAndOwner(taskId);
        checkList.setTask(task);
        checkList.setTitle(request.getTitle());
        checkList.setCompleted(request.getCompleted());

        CheckList savedList=checkListRepository.save(checkList);

        activityService.log(ActivityAction.CHECKLIST_CREATED,savedList.getTitle()+" created");

        return mapToCheckListResponse(savedList);
    }

    @Override
    public List<CheckListResponse> getCheckListItems(Long taskId) {
        Task task=currentUserService.getTaskByIdAndOwner(taskId);
        return checkListRepository.findByTask(task)
                .stream()
                .map(this::mapToCheckListResponse)
                .toList();
    }

    @Override
    public CheckListResponse updateCheckListItem(Long itemId, UpdateCheckListRequest request) {
        CheckList checkList=currentUserService.getCheckListByIdAndOwner(itemId);
        checkList.setTitle(request.getTitle());
        checkList.setCompleted(request.getCompleted());
        CheckList savedList=checkListRepository.save(checkList);

        if(Boolean.TRUE.equals(request.getCompleted())
                && !Boolean.TRUE.equals(checkList.getCompleted())) {

            activityService.log(ActivityAction.CHECKLIST_COMPLETED,checkList.getTitle()+" completed");

        }

        return mapToCheckListResponse(savedList);
    }

    @Override
    public void deleteCheckListItem(Long itemId) {
        CheckList checkList=currentUserService.getCheckListByIdAndOwner(itemId);
        checkListRepository.delete(checkList);
    }

    private CheckListResponse mapToCheckListResponse(CheckList checkList){
        CheckListResponse checkListResponse=new CheckListResponse();
        checkListResponse.setId(checkList.getId());
        checkListResponse.setTitle(checkList.getTitle());
        checkListResponse.setCompleted(checkList.getCompleted());
        checkListResponse.setTaskId(checkList.getTask().getId());
        checkListResponse.setCreatedAt(checkList.getTask().getCreatedAt());
        checkListResponse.setUpdatedAt(checkList.getTask().getUpdatedAt());
        return checkListResponse;
    }
}
