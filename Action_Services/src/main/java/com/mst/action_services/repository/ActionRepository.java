package com.mst.action_services.repository;

import com.mst.action_services.beans.Action;
import com.mst.action_services.beans.ActionType;
import com.mst.action_services.beans.Days;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.time.LocalTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ActionRepository extends JpaRepository<Action, UUID> {

    Optional<Action> findActionById(UUID id);
    List<Action> findActionsByOwnerId(String ownerId);
    List<Action> findAllByIsDeletedFalse();
    List<Action> findScheduledActions(@Param("day") Days day, @Param("formattedTime") LocalTime formattedTime);

 /*   List<Action> findAllActionsByOwnerId(String ownerId);
    List<Action> findByOwnerIdAndIsDeletedFalse(String ownerId);
    List<Action> findByOwnerIdAndIsDeletedTrue(String ownerId);
    List<Action> findAllByisDeletedTrue();
    List<Action> findActionsByActionType (ActionType actionType);
    List<Action> findByOwnerIdAndNameAndConditions(String ownerId,String name,List<List<Integer>> conditions);
    void updateRunOnDayToZero(@Param("newDay") Days newDay,@Param("id") Long id); */
}
