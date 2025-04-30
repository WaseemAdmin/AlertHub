package com.mst.project.repository;

import com.mst.project.beans.Label;
import com.mst.project.beans.Metric;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface MetricRepository extends JpaRepository<Metric, UUID> {
    List<Metric> findByUserId(Integer userId);
    List<Metric> findByLabel(Label label);
    List<Metric> findByTimeFrameHours(Integer timeFrameHours);
    @Override
    @NonNull
    Optional<Metric> findById(UUID id);
}
