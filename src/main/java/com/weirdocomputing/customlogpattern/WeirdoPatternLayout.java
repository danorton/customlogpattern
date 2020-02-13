package com.weirdocomputing.customlogpattern;

import org.apache.log4j.EnhancedPatternLayout;
import org.apache.log4j.spi.LoggingEvent;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

/**
 * Extend log4j pattern layout to provide microsecond timestamps
 * © 2020 Daniel Norton
 */
public class WeirdoPatternLayout extends EnhancedPatternLayout {
    private static final String WEIRDO_MICROS = "%d{WEIRDO_MICROS}";
    private boolean isWithWeirdoTimestamp = false;
    /**
     * Formats a logging event to a writer.
     *
     * @param event logging event to be formatted.
     */
    public String format(final LoggingEvent event) {
        String result = super.format(event);
        if (isWithWeirdoTimestamp) {
            result = Instant.now().truncatedTo(ChronoUnit.MICROS).toString().substring(11) + result;
        }
        return result;
    }

    protected org.apache.log4j.helpers.PatternParser createPatternParser(String pattern) {
        if (pattern.startsWith(WEIRDO_MICROS)) {
            isWithWeirdoTimestamp = true;
            pattern = pattern.substring(WEIRDO_MICROS.length());
        }
        return new org.apache.log4j.pattern.BridgePatternParser(pattern);
    }
}

