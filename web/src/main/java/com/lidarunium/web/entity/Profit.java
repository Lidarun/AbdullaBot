package com.lidarunium.web.entity;

//import jakarta.persistence.*;
//import lombok.Getter;
//import lombok.Setter;
//
//import java.io.Serializable;
//import java.util.Date;
//
//@Entity
//@Getter @Setter
//@Table(name = "tb_profits")
//public class Profit implements Serializable {
//
//    @Id
//    private long id;
//
//    @MapsId
//    @ManyToOne
//    @JoinColumn(name = "owner_id")
//    private User user;
//    private float sum;
//    private String description;
//
//    @Temporal(TemporalType.DATE)
//    private Date date;
//
//    @Override
//    public String toString() {
//        return "Owner_id: " + id +
//                "\nSum: " + sum +
//                "\nDescription: " + description +
//                "\nDate: " + date.toString();
//    }
//}
