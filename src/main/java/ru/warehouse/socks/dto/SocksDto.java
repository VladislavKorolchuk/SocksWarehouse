package ru.warehouse.socks.dto;

import ru.warehouse.socks.controller.SocksController;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

public class SocksDto {
    /**
     * dto sock (used in controller - class SocksController {{@link SocksController}}
     * in service    - class SockService {{@link ru.warehouse.socks.service.SockService }} )
     * in tests      - class SockMapperTest and class SockControllerTest)
     * <p>
     * Fields:
     * Socks color  - type String, size 32 characters, cannot be empty
     * cotton_part  - type int, percentage of cotton, number from 0-100, must be positive
     * quantity     - type int, number of socks, non-negative integer, must be positive
     * <p>
     * Methods (behavior):
     * setColor(String color)        - setting a value "color"
     * setCottonPart(int cottonPart) - setting a value "cottonPart"
     * setQuantity(int quantity)     - setting a value "quantity"
     * String getColor()             - getting a field "color"
     * int setCottonPart()           - getting a value "cottonPart"
     * int getQuantity()             - getting a field "quantity"
     */

    @NotBlank
    @Size(max = 32)
    private String color;

    @Positive
    @Max(100)
    private int cottonPart;

    @Positive
    private int quantity;

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getCottonPart() {
        return cottonPart;
    }

    public void setCottonPart(int cottonPart) {
        this.cottonPart = cottonPart;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

}
