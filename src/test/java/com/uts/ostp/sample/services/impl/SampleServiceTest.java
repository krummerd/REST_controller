package com.uts.ostp.sample.services.impl;

import com.uts.ostp.sample.exceptions.SampleNotFoundException;
import com.uts.ostp.sample.models.Sample;
import com.uts.ostp.sample.repositories.SampleRepository;
import com.uts.ostp.sample.services.SampleService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static com.uts.ostp.common.sample.SampleArgumentMatcher.sampleEq;
import static com.uts.ostp.common.sample.SampleTestRepository.*;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Unit tests for {@link SampleService}
 */
public class SampleServiceTest {

    // TODO: Add more tests to test validation

    @Mock
    private SampleRepository sampleRepository;

    private SampleService sampleService;

    @Before
    public void initTestCase() {
        MockitoAnnotations.initMocks(this);
        sampleService = new SampleServiceImpl();
        ((SampleServiceImpl) sampleService).sampleRepository = sampleRepository;
    }

    @Test
    public void shouldCreateNewSample() {
        final Sample sampleToCreate = sample();
        when(sampleRepository.save(sampleEq(sampleToCreate)))
                .thenReturn(withId(sampleToCreate, 1L));
        final Sample created = sampleService.create(sampleToCreate);
        assertThat(created.getId(), is(equalTo(1L)));
    }

    @Test
    public void shouldUpdateSample() {
        final Sample sampleToUpdate = withId(sample(), 1L);
        when(sampleRepository.findOne(1L)).thenReturn(sampleToUpdate);
        sampleToUpdate.setName("bar");
        sampleService.update(sampleToUpdate);
        verify(sampleRepository).save(sampleEq(sampleToUpdate));
    }

    @Test(expected = SampleNotFoundException.class)
    public void shouldUpdateSample_throwException() {
        when(sampleRepository.findOne(1L)).thenReturn(null);
        sampleService.update(withId(sample(), 1L));
    }

    @Test
    public void shouldFindAllSamples() {
        when(sampleRepository.findAll()).thenReturn(
                Arrays.asList(withId(sample(), 1L), withId(anotherSample(), 2L)));
        final List<Sample> foundSamples = sampleService.findAll();
        assertThat(foundSamples.size(), is(equalTo(2)));
        assertThat(foundSamples.get(0).getName(), is(equalTo(sample().getName())));
        assertThat(foundSamples.get(1).getName(), is(equalTo(anotherSample().getName())));
    }
}