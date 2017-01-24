package com.uts.ostp.sample.controllers;

import com.uts.ostp.sample.SampleRestApplication;
import com.uts.ostp.sample.exceptions.SampleNotFoundException;
import com.uts.ostp.sample.models.Sample;
import com.uts.ostp.sample.services.SampleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Rest controller for {@link SampleRestApplication}.
 */
@RestController
@RequestMapping("/v1/samples")
public class SampleController {

    private final Logger log = LoggerFactory.getLogger(SampleController.class);

    @Autowired
    private SampleService sampleService;

    /**
     * REST method to create new {@link Sample}.
     *
     * @param sample   {@link Sample} to create.
     * @param response {@link HttpServletResponse} response.
     * @return create {@link Sample}.
     */
    @RequestMapping(method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE,
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public final Sample createSample(@RequestBody final Sample sample,
                                     final HttpServletResponse response) {
        log.debug("Creating new sample {}", sample);
        response.setStatus(HttpServletResponse.SC_CREATED);
        return sampleService.create(sample);
    }

    /**
     * REST method to update sample.
     *
     * @param sample   a {@link Sample} to update.
     * @param response {@link HttpServletResponse} response.
     */
    @RequestMapping(method = RequestMethod.PUT,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE,
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public final void updateSample(@RequestBody final Sample sample,
                                   final HttpServletResponse response) {
        log.debug("Updating sample {}", sample);
        sampleService.update(sample);
        response.setStatus(HttpServletResponse.SC_NO_CONTENT);
    }

    /**
     * REST method to find one {@link Sample}.
     *
     * @param id sample id.
     * @return found samples.
     */
    @RequestMapping(
            path = "/{id:[\\d+]}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public final Sample findOne(@PathVariable("id") final Long id) {
        log.debug("Finding one sample {}", id);
        return sampleService.findOne(id);
    }

    /**
     * REST method to find all samples.
     *
     * @return available {@link Sample}s.
     */
    @RequestMapping(
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public final List<Sample> findAll() {
        log.debug("Finding all samples");
        return sampleService.findAll();
    }


    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(SampleNotFoundException.class)
    @ResponseBody
    final ExceptionInfo handleNotFound(HttpServletRequest req, Exception ex) {
        return new ExceptionInfo(req.getRequestURL().toString(), ex);
    }
}
