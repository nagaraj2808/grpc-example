package com.nagaraj.projects.todoservice.converters;

import com.google.protobuf.Timestamp;

import java.time.Instant;
import java.util.Date;

public class DateConverter {
    public static Date toDate(Timestamp timestamp){
        return Date.from(toInstant(timestamp));
    }

    public static Instant toInstant(Timestamp timestamp){
        return Instant.ofEpochSecond(timestamp.getSeconds(), timestamp.getNanos());
    }

    public static Timestamp toTimestamp(Date date){
        if (date == null) return Timestamp.getDefaultInstance();
        Instant instant = Instant.from(date.toInstant());
        return Timestamp.newBuilder()
                .setSeconds(instant.getEpochSecond())
                .setNanos(instant.getNano())
                .build();
    }
}
