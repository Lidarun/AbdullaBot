package com.lidarunium.web.entity;

//import com.lidarunium.afpf.enums.PaymentType;
//import jakarta.persistence.*;
//import jakarta.validation.constraints.NotNull;
//import lombok.Getter;
//import lombok.Setter;
//
//import java.io.Serializable;
//import java.util.Date;

//@Entity
//@Getter @Setter
//@Table(name = "tb_expenditures")
//public class Expenditure implements Serializable {
//
//    @Id
//    private long id;
//
//    @MapsId
//    @ManyToOne(fetch = FetchType.EAGER)
//    @JoinColumn(name = "owner_id")
//    private User user;
//
//    private String description;
//
//    @Enumerated(EnumType.STRING)
//    private PaymentType paymentType;
//
//    @NotNull
//    private float sum;
//
//    @Temporal(TemporalType.DATE)
//    private Date date;
//
//    @Override
//    public String toString() {
//        return "OwnerID: " + id +
//                "\nDescription: " + description +
//                "\nPaymentType: " + paymentType.name() +
//                "\nSum: " + sum +
//                "\nDate: " + date.toString();
//    }
//}
