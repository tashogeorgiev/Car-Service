package com.helevator.carservicetracker.data.repair;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.*;

/**
 * Represents a repair which was made for a specific car
 */

public class Repairment implements Serializable {
    /**
     * The unique car number which the repairment was done to
     */
    private String vinForRepair;

    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate dateOfRepair;

    private int mileage;
    /**
     * All the items used for the repair
     */
    private HashSet<Item> items;

    @JsonIgnore//the total cost won't be available in the JSON files, so it is ignored
    private double totalCostForRepair = 0;

    public Repairment(String vinForRepair, LocalDate dateOfRepair, int mileage, HashSet<Item> items){
        this.vinForRepair = vinForRepair;
        this.dateOfRepair = dateOfRepair;
        this.mileage = mileage;
        this.items = items;
        calculateTotalRepairCost();
    }

    public Repairment(){}

    public void addItem(Item item){
        items.add(item);

        //every time an item is added, the total price for the repair will be increased
        totalCostForRepair += item.getTotalPrice();
    }

    public void calculateTotalRepairCost(){
        double totalCost = 0;
        for(Item item : items){
            totalCost += item.getTotalPrice();
        }
        totalCostForRepair = totalCost;
    }

    public LocalDate getDateOfRepair() {
        return dateOfRepair;
    }

    public int getMileage() {
        return mileage;
    }

    public double getTotalCostForRepair() {
        return totalCostForRepair;
    }

    public String getVinForRepair() {
        return vinForRepair;
    }

    public HashSet<Item> getItems() {
        return items;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Repairment that = (Repairment) o;
        return vinForRepair.equals(that.vinForRepair) && dateOfRepair.equals(that.dateOfRepair);
    }

    @Override
    public int hashCode() {
        return Objects.hash(vinForRepair, dateOfRepair);
    }

    @Override
    public String toString() {
        return "Repairment{" + "vinForRepair='" + vinForRepair + '\'' +
                ", dateOfRepair=" + dateOfRepair +
                ", mileage=" + mileage +
                ", items=" + items +
                ", totalCostForRepair=" + totalCostForRepair +
                '}';
    }
}