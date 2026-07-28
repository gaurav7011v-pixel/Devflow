package com.devflow.backend.controller;

import com.devflow.backend.dto.*;
import com.devflow.backend.services.CheckListService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class CheckListController {
    private final CheckListService checkListService;

    public CheckListController(CheckListService checkListService) {
        this.checkListService = checkListService;
    }

    @PostMapping("/tasks/{taskId}/checkList")
    public ResponseEntity<CheckListResponse> createCheckListItem(@PathVariable Long taskId, @Valid @RequestBody CreateCheckListRequest createCheckListRequest){
        CheckListResponse response=checkListService.createCheckListItem(taskId,createCheckListRequest);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
    @GetMapping("/tasks/{taskId}/checkList")
    public ResponseEntity<List<CheckListResponse>> getCheckListItems(@PathVariable Long taskId){
        List<CheckListResponse> response=checkListService.getCheckListItems(taskId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/checkList/{checkListId}")
    public ResponseEntity<CheckListResponse> updateCheckListItem(@PathVariable Long checkListId ,@Valid @RequestBody UpdateCheckListRequest updateCheckListRequest){
        CheckListResponse response=checkListService.updateCheckListItem(checkListId,updateCheckListRequest);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/checkList/{checkListId}")
    public ResponseEntity<Void> deleteComment(@PathVariable Long checkListId) {
        checkListService.deleteCheckListItem(checkListId);
        return ResponseEntity.noContent().build();
    }
}
