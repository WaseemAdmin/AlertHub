package com.example.processor.beans.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.processor.beans.Metric;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;

public interface MetricRepository extends JpaRepository<Metric, Long> {
}
