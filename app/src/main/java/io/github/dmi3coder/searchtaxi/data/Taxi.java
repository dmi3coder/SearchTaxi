package io.github.dmi3coder.searchtaxi.data;

import java.util.Arrays;

/**
 * Created by dim3coder on 8/26/17.
 */
public class Taxi {
  private String address;
  private Double[] coordinates;
  private String engineType;
  private ComparisonMark exterior;
  private Integer fuel;
  private ComparisonMark interior;
  private String name;
  private String vin;

  public Taxi() {
  }

  public Taxi(String address, Double[] coordinates, String engineType,
      ComparisonMark exterior, Integer fuel,
      ComparisonMark interior, String name, String vin) {
    this.address = address;
    this.coordinates = coordinates;
    this.engineType = engineType;
    this.exterior = exterior;
    this.fuel = fuel;
    this.interior = interior;
    this.name = name;
    this.vin = vin;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public Double[] getCoordinates() {
    return coordinates;
  }

  public void setCoordinates(Double[] coordinates) {
    this.coordinates = coordinates;
  }

  public String getEngineType() {
    return engineType;
  }

  public void setEngineType(String engineType) {
    this.engineType = engineType;
  }

  public ComparisonMark getExterior() {
    return exterior;
  }

  public void setExterior(ComparisonMark exterior) {
    this.exterior = exterior;
  }

  public Integer getFuel() {
    return fuel;
  }

  public void setFuel(Integer fuel) {
    this.fuel = fuel;
  }

  public ComparisonMark getInterior() {
    return interior;
  }

  public void setInterior(ComparisonMark interior) {
    this.interior = interior;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getVin() {
    return vin;
  }

  public void setVin(String vin) {
    this.vin = vin;
  }

  @Override
  public String toString() {
    final StringBuffer sb = new StringBuffer("Taxi{");
    sb.append("address='").append(address).append('\'');
    sb.append(", coordinates=")
        .append(coordinates == null ? "null" : Arrays.asList(coordinates).toString());
    sb.append(", engineType='").append(engineType).append('\'');
    sb.append(", exterior=").append(exterior);
    sb.append(", fuel=").append(fuel);
    sb.append(", interior=").append(interior);
    sb.append(", name='").append(name).append('\'');
    sb.append(", vin='").append(vin).append('\'');
    sb.append('}');
    return sb.toString();
  }
}
