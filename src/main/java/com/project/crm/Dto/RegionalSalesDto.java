package com.project.crm.Dto;

public class RegionalSalesDto {
    private Double east;
    private Double west;
    private Double north;
    private Double south;

    public RegionalSalesDto() {
    }

    public RegionalSalesDto(Double east, Double west, Double north, Double south) {
        this.east = east;
        this.west = west;
        this.north = north;
        this.south = south;
    }

    public Double getEast() {
        return east;
    }

    public void setEast(Double east) {
        this.east = east;
    }

    public Double getWest() {
        return west;
    }

    public void setWest(Double west) {
        this.west = west;
    }

    public Double getNorth() {
        return north;
    }

    public void setNorth(Double north) {
        this.north = north;
    }

    public Double getSouth() {
        return south;
    }

    public void setSouth(Double south) {
        this.south = south;
    }
}
