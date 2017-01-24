package com.uts.ostp.sample.services.impl;

import com.uts.ostp.sample.exceptions.SampleNotFoundException;
import com.uts.ostp.sample.models.Sample;
import com.uts.ostp.sample.repositories.SampleRepository;
import com.uts.ostp.sample.services.SampleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Implementation for {@link SampleService}.
 */
@Service
public class SampleServiceImpl implements SampleService {

    @Autowired
    SampleRepository sampleRepository;

    @Override
    public final Sample create(final Sample sample) {
        return sampleRepository.save(sample);
    }

    @Override
    public final void update(final Sample sample) throws SampleNotFoundException {
        findOne(sample.getId());
        sampleRepository.save(sample);
    }

    @Override
    public final Sample findOne(final Long id) throws SampleNotFoundException {
        final Sample foundSample = sampleRepository.findOne(id);
        if (foundSample == null) {
            throw new SampleNotFoundException("Sample with id " + id + " not found.");
        }
        return foundSample;
    }

    @Override
    public final List<Sample> findAll() {
        return sampleRepository.findAll();
    }
}
