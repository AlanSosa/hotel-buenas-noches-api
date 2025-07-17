package com.example.buenasnochesapi.controllers;

import com.example.buenasnochesapi.models.dto.requests.CreateHotelRequest;
import com.example.buenasnochesapi.models.dto.requests.UpdateHotelRequest;
import com.example.buenasnochesapi.models.dto.responses.HotelResponse;
import com.example.buenasnochesapi.models.dto.responses.status.IResponseMessage;
import com.example.buenasnochesapi.models.entities.HotelEntity;
import com.example.buenasnochesapi.services.IHotelService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/hotel")
@AllArgsConstructor
public class HotelController {

    private IHotelService hotelService;

    @PostMapping
    public ResponseEntity<HotelResponse> createHotel(@RequestBody @Valid CreateHotelRequest createHotelRequest){
        return new ResponseEntity<HotelResponse>(hotelService.createHotel(createHotelRequest), HttpStatus.CREATED);
    }

    @PutMapping("/{hotelId}")
    public ResponseEntity<IResponseMessage> updateHotelById(@PathVariable String hotelId, @Valid @RequestBody UpdateHotelRequest updateHotelRequest){
        return new ResponseEntity<IResponseMessage>(hotelService.updateHotelById(hotelId, updateHotelRequest ), HttpStatus.OK);
    }

    @DeleteMapping("/{hotelId}")
    public ResponseEntity<IResponseMessage> deleteHotel(@PathVariable String hotelId){
        return new ResponseEntity<IResponseMessage>(hotelService.deleteHotelById(hotelId), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<HotelResponse>> getAllHotels(){
        return new ResponseEntity<List<HotelResponse>>(hotelService.listAllHotels(), HttpStatus.CREATED);
    }

    @GetMapping("/{hotelId}")
    public ResponseEntity<HotelResponse> getHotelById(@PathVariable String hotelId){
        return new ResponseEntity<HotelResponse>( hotelService.getHotelById(hotelId), HttpStatus.OK );
    }

    @GetMapping("/hotelPageable")
    public ResponseEntity<Page<HotelResponse>> hotelsPageable(Pageable pageable){
        return new ResponseEntity<Page<HotelResponse>>(hotelService.hotelPageable(pageable), HttpStatus.OK);
    }
    //TODO: Validate updateHotelById when the Request DTO has rooms List. It should return a bad request if that happens.
}
