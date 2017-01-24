package com.uts.ostp.common.sample;

import com.uts.ostp.sample.models.Sample;

/**
 * Test repository for {@link Sample}
 */
public class SampleTestRepository {

    public static Sample sample() {
        return new Sample("foo");
    }

    public static Sample anotherSample() {
        return new Sample("bar");
    }

    public static Sample withId(final Sample sample, final Long id) {
        sample.setId(id);
        return sample;
    }
}
