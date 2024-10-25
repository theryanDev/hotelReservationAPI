package com.theryanDev.HotelReservationAPI.service;

import com.theryanDev.HotelReservationAPI.infra.Exceptions.ValidationException;
import com.theryanDev.HotelReservationAPI.domain.model.User;
import com.theryanDev.HotelReservationAPI.domain.repository.UserRepository;
import com.theryanDev.HotelReservationAPI.service.DTOuser.DadosRegisterDTO;
import com.theryanDev.HotelReservationAPI.service.DTOuser.ListagemUserDTO;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User register(DadosRegisterDTO userRegister) {
        if(userRepository.existsByEmail(userRegister.email())) {
            throw new ValidationException("Email already exists");
        }
        User user = new User(userRegister.name(), userRegister.email(), userRegister.telephone(),userRegister.password());
        return userRepository.save(user);
    }

    public User findById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("User not found"));
    }

    public List<ListagemUserDTO> findByName(String name) {
        if(!userRepository.existsByName(name)) {
            throw new EntityNotFoundException("User not found");
        }
        return userRepository.findByName(name);
    }

    public void delete(Long id) {
        User user = userRepository.getReferenceById(id);
        user.setAtivo(false);
    }

    public Page<ListagemUserDTO> findAll(Pageable pageable) {
        return userRepository.findAllByAtivoTrue(pageable);
    }
}
