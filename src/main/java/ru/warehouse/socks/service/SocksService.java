package ru.warehouse.socks.service;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.warehouse.socks.dto.SocksDto;
import ru.warehouse.socks.entity.ComparisonEnumeration;
import ru.warehouse.socks.entity.Socks;
import ru.warehouse.socks.exeption.NoRequiredQuantityException;
import ru.warehouse.socks.mapper.SocksMapper;
import ru.warehouse.socks.repository.SocksRepository;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class SocksService {

    private static final Logger logger = LoggerFactory.getLogger(SocksService.class);

    private final SocksRepository socksRepository;

    private final SocksMapper socksMapper;

    /**
     * <br> The arrival of socks </br>
     *
     * @param socksDto {{@link SocksDto}} DTO containing information about socks
     * @return Returns the Sock object added to the database
     * @author Korolchuk Vladislav
     */
    @Transactional
    public Socks incomeSocks(SocksDto socksDto) {

        logger.info("Current method is - incomeSocks");
        // Checking for availability in the database of a duplicate product by color and percentage of cotton
        Optional<Socks> socks = socksRepository.findByColorAndCottonPart(socksDto.getColor(),
                socksDto.getCottonPart());
        if (socks.isEmpty()) {
            // The database does not contain a duplicate of the product by color and the percentage of cotton
            return socksRepository.save(socksMapper.toEntity(socksDto));
        } else {
            // The database has a duplicate of the product by color and the percentage of cotton
            socks.get().setQuantity(socks.get().getQuantity() + socksDto.getQuantity());
            return socksRepository.save(socks.get());
        }

    }

    /**
     * <br> Sock release method </br>
     *
     * @param socksDto {{@link SocksDto}} DTO containing information about socks
     * @return Returns the Sock object edit to the database
     * @author Korolchuk Vladislav
     */
    @Transactional
    public Socks outcomeSocks(SocksDto socksDto) {

        logger.info("Current method is - outcomeSocks");
        Socks socks = socksRepository.
                findByColorAndCottonPart(socksDto.getColor(), socksDto.getCottonPart())
                .orElseThrow();
        // Checking for the availability of the required quantities of dispensed socks
        if (socks.getQuantity() < socksDto.getQuantity()) {
            throw new NoRequiredQuantityException(socks.getId());
        }
        // Performing a sock release
        socks.setQuantity(socks.getQuantity() - socksDto.getQuantity());
        return socksRepository.save(socks);

    }

    /**
     * <br> Method for obtaining the number of socks </br>
     *
     * @param color                 - socks color value > 0
     * @param comparisonEnumeration - enumeration value =  lessThan or equal or moreThan
     * @param cottonPart            - percentage of cotton value = > 0 and < 100
     * @return number of socks in stock
     * @author Korolchuk Vladislav
     */
    @Transactional(readOnly = true)
    public Integer getSocks(String color, ComparisonEnumeration comparisonEnumeration, int cottonPart) {

        logger.info("Current method is - getSocks");
        switch (comparisonEnumeration) {
            case lessThan:
                return socksRepository.getQuantitySocksByColorAndCottonPartLessThan(color, cottonPart).orElse(0);
            case equal:
                return socksRepository.getQuantitySocksByColorAndCottonEqual(color, cottonPart).orElse(0);
            case moreThan:
                return socksRepository.getQuantitySocksByColorAndCottonMoreThan(color, cottonPart).orElse(0);
        }
        return 0;
    }

}
