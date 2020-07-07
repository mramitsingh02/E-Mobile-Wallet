package com.tester.spring.rest.webservices.repository.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import java.sql.Timestamp;
import java.time.LocalDateTime;

import static java.util.Objects.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TimePeriod {
    @Column
    private LocalDateTime time;

    public TimePeriod(Timestamp timestamp) {
        if (nonNull(timestamp)) this.time = timestamp.toLocalDateTime();
    }

    public static TimePeriod of(Timestamp createdOn) {
        return new TimePeriod(createdOn);
    }

    public static TimePeriod of(LocalDateTime dateTime) {
        return new TimePeriod(dateTime);
    }

    public LocalDateTime getTime() {
        return this.time;
    }

    public Timestamp getTimestamp() {
        return Timestamp.valueOf(this.time);
    }
}
