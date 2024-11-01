package com.theryanDev.HotelReservationAPI.domain.repository;

import com.theryanDev.HotelReservationAPI.domain.model.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface HotelRepository extends JpaRepository<Hotel, Long> {

    @Query("SELECT h FROM hotels h WHERE h.location.logradouro = :logradouro AND h.location.numero = :numero AND h.location.bairro = :bairro AND h.location.cidade = :cidade AND h.location.uf = :uf AND h.location.cep = :cep")
    List<Hotel> findHotelsWithSameLocation(@Param("logradouro") String logradouro, @Param("numero") Integer numero, @Param("bairro") String bairro, @Param("cidade") String cidade, @Param("uf") String uf, @Param("cep") String cep);

    @Query("SELECT h FROM hotels h WHERE h.location.uf = :uf AND h.location.cidade = :cidade")
    List<Hotel> findHotelsForLocation(@Param("uf") String uf, @Param("cidade") String cidade);
}
