package ru.warehouse.socks.entity;

import javax.persistence.*;

/**
 * Essence of socks
 * Fields:
 *  id          - type Long, Identifier
 *  Socks color - type String, size 32 characters
 *  cotton_part - type int, percentage of cotton, number from 0-100
 *  quantity    - type int, number of socks, non-negative integer
 *
 *  Methods (behavior):
 *    setId(Long id)                - setting a value id
 *    setColor(String color)        - setting a value "color"
 *    setCottonPart(int cottonPart) - setting a value "cottonPart"
 *    setQuantity(int quantity)     - setting a value "quantity"
 *    Long getId()                  - getting a field id
 *    int getQuantity()             - getting a field "quantity"
 *    String getColor()             - getting a field "color"
 *    int getCottonPart()           - getting a field "cottonPart"
 */
@Entity
@Table(name = "Socks")
public class Socks {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 32)
    private String color;

    @Column(name = "cotton_part", nullable = false)
    private int cottonPart;

    @Column(nullable = false)
    private int quantity;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setCottonPart(int cottonPart) {
        this.cottonPart = cottonPart;
    }

    public int getCottonPart() {
        return cottonPart;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getQuantity() {
        return quantity;
    }

}
