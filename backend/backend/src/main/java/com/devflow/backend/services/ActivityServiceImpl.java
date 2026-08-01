package com.devflow.backend.services;

import com.devflow.backend.entity.Activity;
import com.devflow.backend.entity.ActivityAction;
import com.devflow.backend.entity.User;
import com.devflow.backend.repository.ActivityRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;


@Service
public class ActivityServiceImpl implements ActivityService {
    private final CurrentUserService currentUserService;
    private final ActivityRepository activityRepository;
    public ActivityServiceImpl(CurrentUserService currentUserService, ActivityRepository activityRepository) {
        this.currentUserService = currentUserService;
        this.activityRepository = activityRepository;
    }

    @Override
    public void log(ActivityAction action, String description) {
        User user=currentUserService.getCurrentUser();
        Activity activity=new Activity();
        activity.setUser(user);
        activity.setAction(action);
        activity.setDescription(description);
        activity.setCreatedAt(LocalDateTime.now());

        activityRepository.save(activity);
    }

}
