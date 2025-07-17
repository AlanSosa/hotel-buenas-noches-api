package com.example.buenasnochesapi.controllers;

import com.example.buenasnochesapi.models.dto.requests.CreateGuestRequest;
import com.example.buenasnochesapi.models.dto.requests.CreateHotelRequest;
import com.example.buenasnochesapi.models.dto.responses.GuestResponse;
import com.example.buenasnochesapi.models.dto.responses.HotelResponse;
import com.example.buenasnochesapi.models.dto.responses.status.IResponseMessage;
import com.example.buenasnochesapi.services.IGuestService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/guest")
@AllArgsConstructor
public class GuestController {

    private IGuestService guestService;

    @PostMapping
    public ResponseEntity<GuestResponse> createGuest(@RequestBody @Valid CreateGuestRequest createGuestRequest){
        return new ResponseEntity<GuestResponse>(guestService.createGuest(createGuestRequest), HttpStatus.CREATED);
    }

    @DeleteMapping("/{guestEmail}")
    public ResponseEntity<IResponseMessage> deleteGuestByEmail(@PathVariable String guestEmail){
        return new ResponseEntity<IResponseMessage>(guestService.deleteGuestByEmail(guestEmail), HttpStatus.OK );
    }
}