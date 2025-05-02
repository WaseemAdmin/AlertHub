package com.mst.action_services.service;

import com.mst.action_services.beans.Action;
import com.mst.action_services.beans.Days;
import com.mst.action_services.repository.ActionRepository;
import com.mst.action_services.service.exceptions.ActionNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.UUID;

@Service
public abstract class ActionServiceImpl implements ActionService {

    @Autowired
    private ActionRepository actionRepository;

    public List<Action> getAllActions()
    {
        return actionRepository.findAll();
    }

    public Action getActionById(UUID id) throws ActionNotFoundException {
        return actionRepository.findActionById(id)
                .orElseThrow(() -> new ActionNotFoundException("Action with ID " + id + " not found"));
    }

    public List<Action> getAllActionsActive() {
        return actionRepository.findAllByIsDeletedFalse();
    }

    public Action createAction(Action action) {
        return actionRepository.save(action);
    }

    public Action updateAction(Action action) throws ActionNotFoundException {
        Action existingAction = actionRepository.findById(action.getId())
                .orElseThrow(() -> new ActionNotFoundException("Action with ID " + action.getId() + " not found"));

        existingAction.setName(action.getName());
        existingAction.setOwnerId(action.getOwnerId());
        existingAction.setRunOnTime(action.getRunOnTime());
        existingAction.setRunOnDay(action.getRunOnDay());
        existingAction.setMessage(action.getMessage());
        existingAction.setTo(action.getTo());
        existingAction.setActionType(action.getActionType());
        existingAction.setEnabled(action.isEnabled());
        existingAction.setDeleted(action.isDeleted());
        existingAction.setLastUpdate(action.getLastUpdate());
        existingAction.setLastRun(action.getLastRun());
        existingAction.setConditions(action.getConditions());

        return actionRepository.save(existingAction);
    }
    public void deleteActionById(UUID id) throws ActionNotFoundException {
        Action action = actionRepository.findActionById(id)
                .orElseThrow(() -> new ActionNotFoundException("Action with ID " + id + " not found"));

        actionRepository.delete(action);
    }
    public void deleteActionsByOwnerId(String ownerId) throws ActionNotFoundException{
        List<Action> actions = actionRepository.findActionsByOwnerId(ownerId);
        if (actions.isEmpty()) {
            throw new ActionNotFoundException("No actions found for owner ID " + ownerId);}
        actionRepository.deleteAll(actions);
    }
    public void disableAction(UUID id) throws ActionNotFoundException{

        Action action =actionRepository.findById(id)
                .orElseThrow(()-> new ActionNotFoundException("Action with ID " + id + " not found"));
        action.setEnabled(false);
        action.setLastUpdate(Timestamp.valueOf(LocalDateTime.now()));
        actionRepository.save(action);
    }

    public void enableAction(UUID id) throws ActionNotFoundException {
        Action action = actionRepository.findById(id)
                .orElseThrow(() -> new ActionNotFoundException("Action with ID " + id + " not found"));

        action.setEnabled(true);
        action.setLastUpdate(new Timestamp(System.currentTimeMillis()));
        actionRepository.save(action);
    }

    public void undeleteAction(UUID id) throws ActionNotFoundException{
        Action action = actionRepository.findById(id)
                .orElseThrow(() -> new ActionNotFoundException("Action with ID " + id + " not found"));

        if (!action.isDeleted())
            return;

        action.setDeleted(false);
        action.setLastUpdate(new Timestamp(System.currentTimeMillis()));
        actionRepository.save(action);

    }

    public List<Action> getScheduledActions(Days runOnDay, String runOnTime) {
        try {
            LocalTime formattedTime = LocalTime.parse(runOnTime); // use LocalTime directly
            return actionRepository.findScheduledActions(runOnDay, formattedTime);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Invalid time format. Expected format: HH:mm", e);
        }
    }

}
