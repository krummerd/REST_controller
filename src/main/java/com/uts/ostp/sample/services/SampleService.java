package com.uts.ostp.sample.services;

import com.uts.ostp.sample.exceptions.SampleNotFoundException;
import com.uts.ostp.sample.models.Sample;

import java.util.List;

/**
 * Sample Service
 */
public interface SampleService {

    /**
     * Creates new sample.
     *
     * @param sample model to be created.
     * @return created model.
     */
    Sample create(final Sample sample);

    /**
     * Updates existing model.
     *
     * @param sample existing model to be updated.
     * @throws SampleNotFoundException
     * @throws SampleNotFoundException if sample not found.
     */
    // TODO: Throw validation error using {@link javax.validation.Validator}.
    void update(final Sample sample) throws SampleNotFoundException;

    /**
     * Finds model by id.
     *
     * @param id model id.
     * @return found model.
     * @throws SampleNotFoundException if sample not found.
     */
    Sample findOne(final Long id) throws SampleNotFoundException;

    /**
     * Gets list of {@link Sample}s.
     *
     * @return list of {@link Sample}s.
     */
    List<Sample> findAll();


}
