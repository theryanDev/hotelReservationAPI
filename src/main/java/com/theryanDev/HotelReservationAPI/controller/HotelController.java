package com.theryanDev.HotelReservationAPI.controller;

import com.theryanDev.HotelReservationAPI.domain.model.Hotel;
import com.theryanDev.HotelReservationAPI.service.DTOhotel.DadosRegisterHotel;
import com.theryanDev.HotelReservationAPI.service.DTOhotel.DetalhamentoHotelDTO;
import com.theryanDev.HotelReservationAPI.service.DTOhotel.ListagemHotelsDTO;
import com.theryanDev.HotelReservationAPI.service.HotelService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/hotels")
public class HotelController {
    @Autowired
    private HotelService hotelService;

    @GetMapping
    public ResponseEntity<Page<DetalhamentoHotelDTO>> findAllHotels(@PageableDefault(sort = {"name"}) Pageable pageable){
        Page<DetalhamentoHotelDTO> page = hotelService.findAll(pageable);
        return ResponseEntity.ok(page);
    }

    @PostMapping //somente admins podem registrar hoteis
    public ResponseEntity<DetalhamentoHotelDTO> registerHotel(@RequestBody @Valid DadosRegisterHotel hotelRegister, UriComponentsBuilder uriBuilder) {
        Hotel hotel = hotelService.register(hotelRegister);
        var uri = uriBuilder.path("/hotels/{id}").buildAndExpand(hotel.getId()).toUri();
        return ResponseEntity.created(uri).body(new DetalhamentoHotelDTO(hotel));
    }

    @GetMapping("/location")
    // /hotels/location?uf=MA&city=saoluis
    public ResponseEntity<List<DetalhamentoHotelDTO>> findHotelsForLocation(@RequestParam String uf, @RequestParam String cidade) {
        List<DetalhamentoHotelDTO> hotels = hotelService.findHotelsForLocation(uf, cidade);
        return ResponseEntity.ok(hotels);
    }
}
