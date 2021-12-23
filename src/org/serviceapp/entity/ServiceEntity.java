package org.serviceapp.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ServiceEntity {

    /*
    `ID` int
    `Title` string(100)
    `Cost` double
    `DurationInSeconds` int
    `Description` long string
    `Discount` double
    `MainImagePath` string
    */

    private int ID;
    private String Title;
    private double Cost;
    private int Duration;
    private String Description;
    private double Discount;
    private String MainImagePath;

}
