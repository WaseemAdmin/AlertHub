package com.mst.action_services.beans;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class Action {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID id;

    @Column(name = "owner_id", nullable = false)
    private String ownerId;

    @Column(nullable = false)
    private String name;

    private LocalDate createDate;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ActionType actionType;

    @Column(name = "run_on_time", nullable = false)
    private Time runOnTime;

    @Column(name = "run_on_day", nullable = false)
    @Enumerated(EnumType.STRING)
    private Days runOnDay;

    @Column(nullable = false)
    private String message;

    @Column(nullable = false)
    private String to;

    private boolean isEnabled;

    private boolean isDeleted;

    @Column(name = "last_update")
    private Timestamp lastUpdate;

    @Column(name = "last_run")
    private Timestamp lastRun;

    private List<List<Integer>> conditions;

}
