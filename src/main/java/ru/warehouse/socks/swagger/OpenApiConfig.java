package ru.warehouse.socks.swagger;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;

@OpenAPIDefinition(
        info = @Info(
                title = "Warehouse of socks",
                description = "Finishing group", version = "2",
                contact = @Contact(
                        name = "Korolchuk Vladislav",
                        email = "9329496@mail.ru",
                        url = "https://github.com/VladislavKorolchuk"
                )
        )
)
public class OpenApiConfig {

}
