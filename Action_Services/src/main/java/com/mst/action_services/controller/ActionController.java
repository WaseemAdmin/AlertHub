package com.mst.action_services.controller;

import com.mst.action_services.beans.Action;
import com.mst.action_services.beans.Days;
import com.mst.action_services.service.ActionService;
import com.mst.action_services.service.exceptions.ActionNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/action")
public class ActionController {

    @Autowired
    public ActionService actionService;

    @GetMapping
    public List<Action> getAllStudents() {
        return actionService.getAllActions();
    }

    @GetMapping("/{id}")
    public Action getMetricById(@PathVariable UUID id) {
        return actionService.getActionById(id);
    }

    @GetMapping("/all/active")
    public List<Action> getAllMetricByUserId() { return actionService.getAllActionsActive(); }

    @PostMapping("/create/action")
    public Action createAction(@RequestParam Action action) {
        return actionService.createAction(action);
    }

    @PutMapping("/update/action")
    public Action updateMetric(@RequestParam Action action) {
        return actionService.updateAction(action);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteActionById(@PathVariable UUID id) {
        try {
            actionService.deleteActionById(id);
            return ResponseEntity.ok("Action with ID " + id + " has been successfully deleted.");
        } catch (ActionNotFoundException e) {
            return ResponseEntity.badRequest().body("Failed to delete action: " + e.getMessage());
        }
    }
    @DeleteMapping("/delete/owner/{ownerId}")
    public ResponseEntity<String> deleteActionsByOwnerId(@PathVariable String ownerId) {
        try {
            actionService.deleteActionsByOwnerId(ownerId);
            return ResponseEntity.ok("Actions for owner " + ownerId + " deleted.");
        } catch (ActionNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/disable/{id}")
    public ResponseEntity<String> disableAction(@PathVariable UUID id) {
        try {
            actionService.disableAction(id);
            return ResponseEntity.ok("Action disabled successfully.");
        } catch (ActionNotFoundException e) {
            return ResponseEntity.badRequest().body("Failed to disable action  " + e.getMessage());
        }
    }

    @PutMapping("/enable/{id}")
    public ResponseEntity<String> enableAction(@PathVariable UUID id) {
        try {
            actionService.enableAction(id);
            return ResponseEntity.ok("Action  enabled successfully.");
        } catch (ActionNotFoundException e) {
            return ResponseEntity.badRequest().body("Failed to enable action  " + e.getMessage());
        }
    }

    @PutMapping("/undelete/{id}")
    public ResponseEntity<String> undeleteAction(@PathVariable UUID id) {
        try {
            actionService.undeleteAction(id);
            return ResponseEntity.ok("Action with ID " + id + " has been undeleted.");
        } catch (ActionNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/scheduled")
    public ResponseEntity<List<Action>> getScheduledActions(@RequestParam Days runOnDay, @RequestParam String runOnTime) {
        List<Action> actions = actionService.getScheduledActions(runOnDay, runOnTime);
        return actions.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(actions);
    }

}
