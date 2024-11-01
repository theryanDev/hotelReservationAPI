package com.theryanDev.HotelReservationAPI.service;

import com.theryanDev.HotelReservationAPI.domain.model.Hotel;
import com.theryanDev.HotelReservationAPI.domain.repository.HotelRepository;
import com.theryanDev.HotelReservationAPI.infra.Exceptions.ValidationException;
import com.theryanDev.HotelReservationAPI.service.DTOhotel.DadosRegisterHotel;
import com.theryanDev.HotelReservationAPI.service.DTOhotel.DetalhamentoHotelDTO;
import com.theryanDev.HotelReservationAPI.service.DTOhotel.ListagemHotelsDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.text.Normalizer;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Service
public class HotelService {
    @Autowired
    private HotelRepository hotelRepository;

    public Page<DetalhamentoHotelDTO> findAll(Pageable pageable) {
        Page<Hotel> hotelsPage = hotelRepository.findAll(pageable);
        List<DetalhamentoHotelDTO> hotelsDTOList = hotelsPage.stream()
                .map(DetalhamentoHotelDTO::new)
                .collect(Collectors.toList());
        return new PageImpl<>(hotelsDTOList, pageable, hotelsPage.getTotalElements());
    }

    public Hotel register(DadosRegisterHotel hotelRegister) {
        List<Hotel> hotels = hotelRepository.findHotelsWithSameLocation(
                normalize(hotelRegister.location().logradouro()),
                hotelRegister.location().numero(),
                normalize(hotelRegister.location().bairro()),
                normalize(hotelRegister.location().cidade()),
                hotelRegister.location().uf().toUpperCase(),
                hotelRegister.location().cep()
        );
        if(!hotels.isEmpty()){
            throw new ValidationException("Hotel with same location already exists");
        }
        Hotel hotel = new Hotel(hotelRegister);
        hotel.getLocation().setCidade(normalize(hotel.getLocation().getCidade()));
        hotel.getLocation().setBairro(normalize(hotel.getLocation().getBairro()));
        hotel.getLocation().setLogradouro(normalize(hotel.getLocation().getLogradouro()));
        hotel.getLocation().setUf(hotel.getLocation().getUf().toUpperCase());
        return hotelRepository.save(hotel);
    }

    public List<DetalhamentoHotelDTO> findHotelsForLocation(String uf, String cidade) {
        String normalizedUf = uf.toUpperCase();
        String normalizedCity = normalize(cidade);
        List<Hotel> hotels = hotelRepository.findHotelsForLocation(normalizedUf, normalizedCity);
        return hotels.stream()
                .map(DetalhamentoHotelDTO::new)
                .collect(Collectors.toList());
    }

    public static String normalize(String text) {
        if (text == null) return null;
        String normalized = Normalizer.normalize(text, Normalizer.Form.NFD);
        normalized = normalized.replaceAll("[^\\p{ASCII}]", ""); // Remove acentos
        normalized = normalized.toLowerCase(); // Converte para minúsculas
        return normalized.trim().replaceAll(" ", "-"); // Remove espaços extras
    }
}

