package com.helevator.carservicetracker.data.repair;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;
import java.util.Objects;

/**
 * The Item class represents a single item which was used/bought
 * in the repair for a car, every item is stored in the Repairment class
 */

public class Item implements Serializable {
    private String description;
    private int quantity;
    private double price;
    /**
     * A metric type for an item
     * Examples - Motor oil will have a metric LITER because it is a liquid
     *          - Battery will have a metric UNIT, because it can't be represented by anything else
     */
    private Metric metric;

    public Item(){}

    public Item(String description, int quantity, Metric metric, double price){
        this.description = description;
        this.quantity = quantity;
        this.metric = metric;
        this.price = price;
    }


    public int getQuantity() {
        return quantity;
    }

    public Metric getMetric() {
        return metric;
    }

    public String getDescription() {
        return description;
    }

    public double getPrice() {
        return price;
    }

    @JsonIgnore
    public double getTotalPrice() {
        return quantity*price;
    }

    @Override
    public String toString() {
        return "Item{" + "description='" + description + '\'' +
                ", quantity=" + quantity +
                ", price=" + price +
                ", metric=" + metric +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return Double.compare(item.price, price) == 0 && Objects.equals(description, item.description) && metric == item.metric;
    }

    @Override
    public int hashCode() {
        return Objects.hash(description, price, metric);
    }

}