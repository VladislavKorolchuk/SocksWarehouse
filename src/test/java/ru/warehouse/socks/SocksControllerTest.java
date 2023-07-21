package ru.warehouse.socks;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.warehouse.socks.dto.SocksDto;
import ru.warehouse.socks.repository.SocksRepository;
import ru.warehouse.socks.service.SocksService;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
@ActiveProfiles(profiles = {"h2-database", "without-liquibase"})
public class SocksControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private SocksRepository socksRepository;

    @Autowired
    private SocksService socksService;

    @Autowired
    private ObjectMapper objectMapper;

    private SocksDto socksDto = new SocksDto();

    private String baseUri() {
        return "/api/socks";
    }

    @BeforeEach
    public void beforeEach() throws Exception {

        socksDto.setColor("Blue");
        socksDto.setCottonPart(50);
        socksDto.setQuantity(10);

    }

    @AfterEach
    public void afterEach() {

        socksRepository.deleteAll();

    }


    @Test
    public void incomeSockTest() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.post(baseUri() + "/income")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(socksDto)))
                .andExpect(status().is(200))
                .andExpect(result -> {
                    SocksDto socksResponseDto = objectMapper.readValue(
                            result.getResponse().getContentAsString(),
                            SocksDto.class
                    );
                    assertThat(socksResponseDto.getColor()).isEqualTo(socksDto.getColor());
                    assertThat(socksResponseDto.getCottonPart()).isEqualTo(socksDto.getCottonPart());
                    assertThat(socksResponseDto.getQuantity()).isEqualTo(socksDto.getQuantity());
                });

        socksDto.setQuantity(-1);
        mockMvc.perform(MockMvcRequestBuilders.post(baseUri() + "/income")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(socksDto)))
                .andExpect(status().is(400));
        socksDto.setQuantity(10);

        socksDto.setCottonPart(101);
        mockMvc.perform(MockMvcRequestBuilders.post(baseUri() + "/income")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(socksDto)))
                .andExpect(status().is(400));
        socksDto.setQuantity(50);

        socksDto.setColor(null);
        mockMvc.perform(MockMvcRequestBuilders.post(baseUri() + "/income")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(socksDto)))
                .andExpect(status().is(400));
        socksDto.setColor("Blue");

    }

    @Test
    public void outcomeSockTest() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.post(baseUri() + "/income")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(socksDto)))
                .andExpect(status().is(200))
                .andExpect(result -> {
                    SocksDto socksResponseDto = objectMapper.readValue(
                            result.getResponse().getContentAsString(),
                            SocksDto.class);
                    assertThat(socksService.outcomeSocks(socksDto).equals(socksResponseDto));
                });

        socksDto.setQuantity(-1);
        mockMvc.perform(MockMvcRequestBuilders.post(baseUri() + "/outcome")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(socksDto)))
                .andExpect(status().is(400));
        socksDto.setQuantity(10);

        socksDto.setCottonPart(101);
        mockMvc.perform(MockMvcRequestBuilders.post(baseUri() + "/outcome")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(socksDto)))
                .andExpect(status().is(400));
        socksDto.setQuantity(50);

        socksDto.setColor(null);
        mockMvc.perform(MockMvcRequestBuilders.post(baseUri() + "/outcome")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(socksDto)))
                .andExpect(status().is(400));
        socksDto.setColor("Blue");

    }

    @Test
    public void getSockTest() throws Exception {

//        mockMvc.perform(MockMvcRequestBuilders.post(baseUri() + "/income")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(objectMapper.writeValueAsString(socksDto)))
//                .andExpect(status().is(200))
//                .andExpect(result -> {
//                    SocksDto socksResponseDto = objectMapper.readValue(
//                            result.getResponse().getContentAsString(),
//                            SocksDto.class);
//                    assertThat(socksService.getSocks("Blue", equal, 50) == 10).isTrue();
//                    assertThat(socksService.getSocks("Blue", moreThan, 49) == 10).isTrue();
//                    assertThat(socksService.getSocks("Blue", lessThan, 61) == 10).isTrue();
//
//                    assertThat(socksService.getSocks("Blue", equal, 51) == 0).isTrue();
//                    assertThat(socksService.getSocks("Blue", moreThan, 51) == 0).isTrue();
//                    assertThat(socksService.getSocks("Blue", lessThan, 49) == 0).isTrue();
//                });


        mockMvc.perform(MockMvcRequestBuilders
                        .get(baseUri())
                        .queryParam("color", "Blue")
                        .queryParam("comparisonEnumeration", "equal")
                        .queryParam("cottonPart", "")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is(400));

    }

}
