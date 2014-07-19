package com.jstanier.tweetscheduler.util;

import com.google.common.base.Supplier;
import org.joda.time.DateTime;
import org.springframework.stereotype.Component;

@Component
public class DateTimeSupplier implements Supplier<DateTime> {

    public DateTime get() {
        return new DateTime();
    } 
}
