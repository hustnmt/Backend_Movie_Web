package com.test.nmt.service.scheduleRequest;

import java.util.Arrays;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.nmt.model.room.RoomDTO;
import com.test.nmt.model.scheduleRequest.MovieScheduleRequestDTO;
import com.test.nmt.model.showTime.ShowTimeDTO;
import com.test.nmt.model.showTime.ShowTimeDetailsDTO;
import com.test.nmt.model.showTime.TimeZoneDTO;
import com.test.nmt.service.movie.MovieService;
import com.test.nmt.service.room.RoomService;
import com.test.nmt.service.showTime.ShowTimeService;
import com.test.nmt.service.showTime.TimeService;

@Service
public class ScheduleRequestServiceImp implements ScheduleRequestService {
    @Autowired
    private MovieService movieService;
    @Autowired
    private RoomService roomService;
    @Autowired
    private ShowTimeService showTimeService;
    @Autowired
    private TimeService timeZoneService;

    public ShowTimeDetailsDTO getShowTimeDetails(Long id) {
        ShowTimeDetailsDTO dto = new ShowTimeDetailsDTO();
        ShowTimeDTO showTimeDTO = showTimeService.getByID(id);
        dto.setNumTicketSold(showTimeDTO.getNumTicketSold());
        dto.setShowDate(showTimeDTO.getShowDate());
        dto.setShowTimeID(id);
        RoomDTO roomDTO = roomService.getById(showTimeDTO.getRoomID());
        dto.setRoomName(roomDTO.getRoomName());
        dto.setLocation(roomDTO.getLocation());
        dto.setNumberSit(roomDTO.getNumberSit());
        TimeZoneDTO timeZoneDTO = timeZoneService.getByID(showTimeDTO.getTimeZoneID());
        dto.setStartTime(timeZoneDTO.getStartTime());
        dto.setEndTime(timeZoneDTO.getEndTime());
        dto.setTimeZoneID(timeZoneDTO.getTimeZoneID());
        return dto;
    }

    @Override
    public MovieScheduleRequestDTO getDataByShowTimeID(Long id) {
        MovieScheduleRequestDTO dto = new MovieScheduleRequestDTO();
        dto.setShowTimeDetailsDTOs(Arrays.asList(getShowTimeDetails(id)));
        dto.setMovieDTO(movieService.findById(showTimeService.getByID(id).getMovieID()));
        return dto;
    }

    @Override
    public MovieScheduleRequestDTO getDataByMovieID(Long id) {
        MovieScheduleRequestDTO dto = new MovieScheduleRequestDTO();
        dto.setMovieDTO(movieService.findById(id));
        dto.setShowTimeDetailsDTOs(showTimeService.getAll().stream().filter(value -> value.getMovieID().equals(id))
                .map(value -> getShowTimeDetails(value.getShowTimeID())).collect(Collectors.toList()));
        return dto;
    }
}
