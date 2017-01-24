package com.uts.ostp.sample.repositories;

import com.uts.ostp.sample.models.Sample;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Sample repository
 */
public interface SampleRepository extends JpaRepository<Sample, Long> {
}
