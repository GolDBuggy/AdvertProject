package com.java.advertproject.Repository;

import com.java.advertproject.Model.Report;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReportRepo extends JpaRepository<Report,String> {
}
