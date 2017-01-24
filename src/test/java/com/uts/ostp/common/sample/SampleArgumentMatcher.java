package com.uts.ostp.common.sample;

import com.uts.ostp.sample.models.Sample;
import org.mockito.ArgumentMatcher;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.argThat;

/**
 * Argument matcher for {@link Sample}
 */
public class SampleArgumentMatcher extends ArgumentMatcher<Sample> {

    private Sample expected;

    public static Sample sampleEq(final Sample expected) {
        return argThat(new SampleArgumentMatcher(expected));

    }

    public SampleArgumentMatcher(final Sample expected) {
        this.expected = expected;
    }

    @Override
    public boolean matches(final Object argument) {
        final Sample actual = (Sample) argument;

        assertThat(actual.getId(), is(equalTo(expected.getId())));
        assertThat(actual.getName(), is(equalTo(expected.getName())));
        return true;
    }


}
