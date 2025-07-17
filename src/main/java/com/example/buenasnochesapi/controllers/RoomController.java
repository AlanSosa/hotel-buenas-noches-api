package com.example.buenasnochesapi.controllers;

import com.example.buenasnochesapi.models.dto.requests.RoomRequest;
import com.example.buenasnochesapi.models.dto.requests.SearchRoomRequest;
import com.example.buenasnochesapi.models.dto.responses.HotelResponse;
import com.example.buenasnochesapi.models.dto.responses.RoomResponse;
import com.example.buenasnochesapi.models.dto.responses.status.IResponseMessage;
import com.example.buenasnochesapi.services.IRoomService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/room")
@AllArgsConstructor
public class RoomController {

    private IRoomService roomService;

    @PostMapping("/hotel/{hotelId}")
    public ResponseEntity<HotelResponse> createRoom(@PathVariable String hotelId, @Valid @RequestBody RoomRequest roomRequest){
        return new ResponseEntity<HotelResponse>( roomService.createRoomByHotelId(hotelId, roomRequest), HttpStatus.OK );
    }

    @PutMapping("/{roomId}")
    public ResponseEntity<RoomResponse> updateRoomById(@PathVariable String roomId, @Valid @RequestBody RoomRequest roomRequest){
        return new ResponseEntity<RoomResponse>( roomService.updateRoomByRoomId(roomId, roomRequest), HttpStatus.OK );
    }

    @GetMapping("/hotel/{hotelId}")
    public ResponseEntity<List<RoomResponse>> findAvailableRoomsIn(@PathVariable String hotelId, @Valid @RequestBody SearchRoomRequest searchRoomRequest){
        return new ResponseEntity<List<RoomResponse>>( roomService.findRoomAvailableByHotelId(hotelId,
                searchRoomRequest.getStartDate(), searchRoomRequest.getEndDate()), HttpStatus.OK );
    }

    @GetMapping("/hotel/{hotelId}/{roomId}")
    public ResponseEntity<RoomResponse> findAvailableRoomsIn(@PathVariable String hotelId,
                                                             @PathVariable String roomId,
                                                             @Valid @RequestBody SearchRoomRequest searchRoomRequest){
        return new ResponseEntity<RoomResponse>(
                roomService.findRoomAvailableByHotelIdAndRoomId(hotelId, roomId
        , searchRoomRequest.getStartDate()
        , searchRoomRequest.getEndDate()), HttpStatus.OK );
    }

    @DeleteMapping("/{roomId}")
    public ResponseEntity<IResponseMessage> deleteRoomById(@PathVariable String roomId){
        return new ResponseEntity<IResponseMessage>(roomService.removeRoomByRoomId(roomId), HttpStatus.OK );
    }
}
