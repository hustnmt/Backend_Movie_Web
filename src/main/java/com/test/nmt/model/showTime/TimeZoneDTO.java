package com.test.nmt.model.showTime;

import java.sql.Time;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TimeZoneDTO {
    private Long timeZoneID;
    private Time startTime;
    private Time endTime;

    public TimeZoneDTO loadFromEntity(TimeZoneEntity entity) {
        this.timeZoneID = entity.getTimeZoneID();
        this.startTime = entity.getStartTime();
        this.endTime = entity.getEndTime();
        return this;
    }
}
