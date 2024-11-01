package com.theryanDev.HotelReservationAPI.controller;

import com.theryanDev.HotelReservationAPI.domain.model.User;
import com.theryanDev.HotelReservationAPI.service.DTOuser.DadosRegisterDTO;
import com.theryanDev.HotelReservationAPI.service.DTOuser.DetalhamentoUserDTO;
import com.theryanDev.HotelReservationAPI.service.DTOuser.ListagemUserDTO;
import com.theryanDev.HotelReservationAPI.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
     @Autowired
     private UserService userService;

     @PostMapping
     @Transactional
     public ResponseEntity<DetalhamentoUserDTO> registerUser(@RequestBody @Valid DadosRegisterDTO userRegister, UriComponentsBuilder uriBuilder) {
         User user = userService.register(userRegister);
         var uri = uriBuilder.path("/users/{id}").buildAndExpand(user.getId()).toUri();
         return ResponseEntity.created(uri).body(new DetalhamentoUserDTO(user));
     }

    @GetMapping("/{id}")
    public ResponseEntity<DetalhamentoUserDTO> findByIdUser(@PathVariable Long id){
        var user = userService.findById(id);
        return ResponseEntity.ok(new DetalhamentoUserDTO(user));
    }

    @GetMapping
    public ResponseEntity<Page<ListagemUserDTO>> findAllUser(@PageableDefault(sort = {"name"}) Pageable pageable){
        Page<ListagemUserDTO> page = userService.findAll(pageable);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/search")
    //http://localhost:8080/users/search?name=John
    public ResponseEntity<List<ListagemUserDTO>> findByNameUser(@RequestParam String name) {
        List<ListagemUserDTO> users = userService.findByName(name);
        return ResponseEntity.ok(users);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        userService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
