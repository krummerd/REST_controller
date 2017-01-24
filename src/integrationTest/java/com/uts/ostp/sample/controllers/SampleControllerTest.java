package com.uts.ostp.sample.controllers;

import com.uts.ostp.sample.models.Sample;
import com.uts.ostp.sample.repositories.SampleRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;

import static com.uts.ostp.common.sample.SampleTestRepository.*;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * Integration tests for {@link SampleController}
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
public class SampleControllerTest {

    private static final String SAMPLES_PATH = "/v1/samples";

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Autowired
    private SampleRepository sampleRepository;

    @Before
    public void cleanDb() {
        sampleRepository.deleteAllInBatch();
    }

    @Test
    public void shouldCreateNewSample() {
        createSample(sample());
    }

    @Test
    public void shouldUpdateSample() {
        final Sample created = createSample(sample());
        created.setName("baz");
        final HttpEntity<Sample> httpEntity = new HttpEntity<>(created);
        ResponseEntity<Void> response = testRestTemplate.exchange(SAMPLES_PATH,
                HttpMethod.PUT, httpEntity, Void.class);
        assertThat(response.getStatusCode(), is(equalTo(HttpStatus.NO_CONTENT)));
        final Sample foundSample = findSample(created.getId());
        assertThat(foundSample.getName(), is(equalTo(created.getName())));
    }

    @Test
    public void shouldUpdateSample_notFound() {
        final HttpEntity<Sample> httpEntity = new HttpEntity<>(withId(sample(), 99L));
        ResponseEntity<ExceptionInfo> response = testRestTemplate.exchange(SAMPLES_PATH,
                HttpMethod.PUT, httpEntity, ExceptionInfo.class);
        assertThat(response.getStatusCode(), is(HttpStatus.NOT_FOUND));
    }

    @Test
    public void shouldFindSample() {
        final Sample created = createSample(sample());
        final Sample found = findSample(created.getId());
        assertThat(found.getName(), is(equalTo(created.getName())));
    }

    @Test
    public void shouldFindSample_notFound() {
        ResponseEntity<Sample> response = testRestTemplate.getForEntity(SAMPLES_PATH + "/99",
                Sample.class);
        assertThat(response.getStatusCode(), is(HttpStatus.NOT_FOUND));
    }

    @Test
    public void shouldFindAllSamples() {
        createSample(sample());
        createSample(anotherSample());
        ResponseEntity<Sample[]> response = testRestTemplate.getForEntity(SAMPLES_PATH,
                Sample[].class);
        System.out.println(Arrays.toString(response.getBody()));
        assertThat(response.getStatusCode(), is(HttpStatus.OK));
        assertThat(response.getBody().length, is(equalTo(2)));
    }

    private Sample createSample(final Sample sample) {
        ResponseEntity<Sample> response = testRestTemplate.postForEntity(SAMPLES_PATH,
                sample, Sample.class);
        assertThat(response.getStatusCode(), is(HttpStatus.CREATED));
        final Sample created = response.getBody();
        assertThat(created.getId() > 0, is(true));
        assertThat(created.getName(), is(equalTo(sample.getName())));
        return created;
    }

    private Sample findSample(final Long id) {
        ResponseEntity<Sample> response = testRestTemplate.getForEntity(SAMPLES_PATH + "/" + id,
                Sample.class);
        assertThat(response.getStatusCode(), is(HttpStatus.OK));
        return response.getBody();
    }
}
