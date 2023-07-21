package ru.warehouse.socks;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import ru.warehouse.socks.dto.SocksDto;
import ru.warehouse.socks.entity.Socks;
import ru.warehouse.socks.mapper.SocksMapper;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles(profiles = {"h2-database", "without-liquibase"})
public class SocksMapperTest {

    @Autowired
    private SocksMapper socksMapper;

    private SocksDto socksDto = new SocksDto();

    @BeforeEach
    public void beforeEach() throws Exception {

        socksDto.setColor("Blue");
        socksDto.setCottonPart(100);
        socksDto.setQuantity(10);

    }

    @Test
    public void toEntityTest() {

        Socks socks = socksMapper.toEntity(socksDto);

        assertThat(socks.getColor()).isEqualTo(socksDto.getColor());
        assertThat(socks.getCottonPart()).isEqualTo(socksDto.getCottonPart());
        assertThat(socks.getQuantity()).isEqualTo(socksDto.getQuantity());

    }

}
