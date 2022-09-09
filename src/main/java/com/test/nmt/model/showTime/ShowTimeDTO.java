package com.test.nmt.model.showTime;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ShowTimeDTO {
    private Long showTimeID;
    private Long movieID;
    private Long roomID;
    private Long numTicketSold;
    private Date showDate;
    private Long timeZoneID;

    public ShowTimeDTO loadFromEntity(ShowTimeEntity entity) {
        this.showTimeID = entity.getShowTimeID();
        this.movieID = entity.getMovieID();
        this.roomID = entity.getRoomID();
        this.numTicketSold = entity.getNumTicketSold();
        this.showDate = entity.getShowDate();
        this.timeZoneID = entity.getTimeZoneID();
        return this;
    }
}
