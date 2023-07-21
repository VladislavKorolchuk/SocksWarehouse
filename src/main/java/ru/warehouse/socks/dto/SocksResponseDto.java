package ru.warehouse.socks.dto;

public class SocksResponseDto {
    /**
     *  Response dto sock (used in tests - class SockControllerTest)
     *  Fields:
     *   Socks color  - type String
     *   cotton_part  - type int, percentage of cotton
     *   quantity     - type int, number of socks
     *
     *   Methods (behavior):
     *    String getColor()             - getting a field "color"
     *    int setCottonPart()           - getting a value "cottonPart"
     *    int getQuantity()             - getting a field "quantity"
     */

    private String color;

    private  int cottonPart;

    private  int quantity;

    public String getColor() {
        return color;
    }

    public int getCottonPart() {
        return cottonPart;
    }

    public int getQuantity() {
        return quantity;
    }

}
