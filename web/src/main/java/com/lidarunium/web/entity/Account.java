package com.lidarunium.web.entity;

//import jakarta.persistence.*;
//
//import java.io.Serializable;
//
//@Entity
//@Table(name = "tb_accounts")
//public class Account implements Serializable {
//
//    @Id
//    private long id;
//
//    @MapsId
//    @OneToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "ownwer_id")
//    private User user;
//    private float nonCash;
//    private float cash;
//
//    @Override
//    public String toString() {
//        return "OwnerID: " + id +
//                "\nNonCASH: " + nonCash +
//                "\nCASH: " + cash;
//    }
//}
//