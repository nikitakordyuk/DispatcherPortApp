package com.port.DispatcherPortApp.models;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "General")
public class General {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "car_number")
    private String carNumber;

    @Column(name = "trailer_number")
    private String trailerNumber;

    @Column(name = "driver_license_number")
    private String driverLicenseNumber;

    @Column(name = "nomenclature")
    private String nomenclature;

    @Column(name = "sender")
    private String sender;

    @Column(name = "vehicle_type")
    private String vehicleType;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "iscome")
    private boolean isCome;

    @Column(name = "date_of_creation")
    private Timestamp dateOfCreation;

    public General() {
    }

    public General(String carNumber, String trailerNumber, String driverLicenseNumber, String nomenclature,
                   String sender, String vehicleType, String phoneNumber, String fullName, boolean isCome, Timestamp dateOfCreation)
    {
        this.carNumber = carNumber;
        this.trailerNumber = trailerNumber;
        this.driverLicenseNumber = driverLicenseNumber;
        this.nomenclature = nomenclature;
        this.sender = sender;
        this.vehicleType = vehicleType;
        this.phoneNumber = phoneNumber;
        this.fullName = fullName;
        this.isCome = isCome;
        this.dateOfCreation = dateOfCreation;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCarNumber() {
        return carNumber;
    }

    public void setCarNumber(String carNumber) {
        this.carNumber = carNumber;
    }

    public String getTrailerNumber() {
        return trailerNumber;
    }

    public void setTrailerNumber(String trailerNumber) {
        this.trailerNumber = trailerNumber;
    }

    public String getDriverLicenseNumber() {
        return driverLicenseNumber;
    }

    public void setDriverLicenseNumber(String driverLicenseNumber) {
        this.driverLicenseNumber = driverLicenseNumber;
    }

    public String getNomenclature() {
        return nomenclature;
    }

    public void setNomenclature(String nomenclature) {
        this.nomenclature = nomenclature;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Timestamp getDateOfCreation() {
        return dateOfCreation;
    }

    public void setDateOfCreation(Timestamp dateOfCreation) {
        this.dateOfCreation = dateOfCreation;
    }

    public boolean isCome() {
        return isCome;
    }

    public void setCome(boolean come) {
        isCome = come;
    }

    @Override
    public String toString() {
        return "General{" +
               "id=" + id +
               ", carNumber='" + carNumber + '\'' +
               ", trailerNumber='" + trailerNumber + '\'' +
               ", driverLicenseNumber='" + driverLicenseNumber + '\'' +
               ", nomenclature='" + nomenclature + '\'' +
               ", sender='" + sender + '\'' +
               ", vehicleType='" + vehicleType + '\'' +
               ", phoneNumber='" + phoneNumber + '\'' +
               ", fullName='" + fullName + '\'' +
               ", isCome=" + isCome +
               ", dateOfCreation=" + dateOfCreation +
               '}';
    }
}
