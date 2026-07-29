package com.devflow.backend.controller;

import com.devflow.backend.dto.*;
import com.devflow.backend.dto.LabelResponse;
import com.devflow.backend.services.LabelService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class LabelController{
    private final LabelService labelService;

    public LabelController(LabelService labelService) {
        this.labelService = labelService;
    }

    @PostMapping("/labels")
    public ResponseEntity<LabelResponse> createLabel(@Valid @RequestBody CreateLabelRequest createLabelRequest){
        LabelResponse response=labelService.createLabel(createLabelRequest);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
    @GetMapping("/labels")
    public ResponseEntity<List<LabelResponse>> getAllLabels(){
        List<LabelResponse> response=labelService.getAllLabels();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/labels/{id}")
    public ResponseEntity<LabelResponse> getLabelById(@PathVariable Long id){
        LabelResponse response=labelService.getLabelById(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/labels/{id}")
    public ResponseEntity<LabelResponse> updateLabel(@PathVariable Long id ,@Valid @RequestBody UpdateLabelRequest updateLabelRequest){
        LabelResponse response=labelService.updateLabel(id,updateLabelRequest);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/labels/{id}")
    public ResponseEntity<Void> deleteLabel(@PathVariable Long id){
        labelService.deleteLabel(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/tasks/{taskId}/labels/{labelId}")
    public ResponseEntity<LabelResponse> attackLabelsToTask(@PathVariable Long taskId,@PathVariable Long labelId){
        LabelResponse response=labelService.attachLabelToTask(taskId,labelId);
        return new ResponseEntity<>(response,HttpStatus.CREATED);
    }

    @DeleteMapping("/tasks/{taskId}/labels/{labelId}")
    public ResponseEntity<Void> removeLabelFromTask(@PathVariable Long taskId,@PathVariable Long labelId){
        labelService.removeLabelFromTask(taskId,labelId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/tasks/{taskId}/labels")
    public ResponseEntity<List<LabelResponse>> getLabelsByTask(@PathVariable Long taskId){
        List<LabelResponse> response =labelService.getLabelsByTask(taskId);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

}
