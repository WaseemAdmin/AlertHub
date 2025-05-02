package com.mst.action_services.service;

import com.mst.action_services.beans.Action;
import com.mst.action_services.beans.Days;
import com.mst.action_services.service.exceptions.ActionNotFoundException;

import java.util.List;
import java.util.UUID;

public interface ActionService {

    List<Action> getAllActions();
    Action getActionById(UUID id) throws ActionNotFoundException;
    List<Action> getAllActionsActive();
    Action createAction(Action action);
    Action updateAction(Action action) throws ActionNotFoundException;
    void deleteActionById(UUID id) throws ActionNotFoundException;
    void deleteActionsByOwnerId(String ownerId) throws ActionNotFoundException;
    void disableAction(UUID id) throws ActionNotFoundException;
    void enableAction(UUID id) throws ActionNotFoundException;
    void undeleteAction(UUID id) throws ActionNotFoundException;
    List<Action> getScheduledActions(Days runOnDay, String runOnTime);
}
