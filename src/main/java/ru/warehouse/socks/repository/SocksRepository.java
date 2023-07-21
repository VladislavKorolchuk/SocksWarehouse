package ru.warehouse.socks.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.warehouse.socks.entity.Socks;

import java.util.Optional;

public interface SocksRepository extends JpaRepository<Socks,Long> {

    Optional <Socks> findByColorAndCottonPart(String color, int cottonPart);


    @Query (value = "SELECT SUM(quantity) FROM socks s WHERE s.color = :color AND s.cotton_part < :cottonPart",nativeQuery = true)
    Optional<Integer> getQuantitySocksByColorAndCottonPartLessThan(String color, int cottonPart);

    @Query (value = "SELECT SUM(quantity) FROM socks s WHERE s.color = :color AND s.cotton_part = :cottonPart",nativeQuery = true)
    Optional<Integer> getQuantitySocksByColorAndCottonEqual(String color, int cottonPart);

    @Query (value = "SELECT SUM(quantity) FROM socks s WHERE s.color = :color AND s.cotton_part > :cottonPart",nativeQuery = true)
    Optional<Integer> getQuantitySocksByColorAndCottonMoreThan(String color, int cottonPart);

}
