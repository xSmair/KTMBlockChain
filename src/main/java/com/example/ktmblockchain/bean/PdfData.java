package com.example.ktmblockchain.bean;

public class PdfData {
    //{"Motor":98.92999999999996,
    // "Lenckung":100,
    // "Radiator":98.60000000000008,
    // "Bremse":100,
    // "Getriebe":99.70000000000002,
    // "Federgabel":99.88999999999994,
    // "Kette_ritzerl":100,"Tank":99.9,
    // "Gashebel":98,
    // "Reifen":99.00000000000006
    //     //TODO TANK
    // }

    private double motor;
    private double lenkung;
    private double radiator;
    private double bremse;
    private double getriebe;
    private double federgabel;
    private double kette_ritzerl;
    private double gashebel;
    private double reifen;

    private double tank;

    public PdfData() {
    }

    public double getTank() {
        return tank;
    }

    public void setTank(double tank) {
        this.tank = tank;
    }

    public PdfData(double motor, double lenkung, double radiator, double bremse, double getriebe, double federgabel, double kette_ritzerl, double gashebel, double reifen, double tank) {
        this.motor = motor;
        this.lenkung = lenkung;
        this.radiator = radiator;
        this.bremse = bremse;
        this.getriebe = getriebe;
        this.federgabel = federgabel;
        this.kette_ritzerl = kette_ritzerl;
        this.gashebel = gashebel;
        this.reifen = reifen;
        this.tank = tank;
    }

    public PdfData(double motor, double lenkung, double radiator, double bremse, double getriebe, double federgabel, double kette_ritzerl, double gashebel, double reifen) {
        this.motor = motor;
        this.lenkung = lenkung;
        this.radiator = radiator;
        this.bremse = bremse;
        this.getriebe = getriebe;
        this.federgabel = federgabel;
        this.kette_ritzerl = kette_ritzerl;
        this.gashebel = gashebel;
        this.reifen = reifen;
    }

    public double getMotor() {
        return motor;
    }

    public void setMotor(double motor) {
        this.motor = motor;
    }

    public double getLenkung() {
        return lenkung;
    }

    public void setLenkung(double lenkung) {
        this.lenkung = lenkung;
    }

    public double getRadiator() {
        return radiator;
    }

    public void setRadiator(double radiator) {
        this.radiator = radiator;
    }

    public double getBremse() {
        return bremse;
    }

    public void setBremse(double bremse) {
        this.bremse = bremse;
    }

    public double getGetriebe() {
        return getriebe;
    }

    public void setGetriebe(double getriebe) {
        this.getriebe = getriebe;
    }

    public double getFedergabel() {
        return federgabel;
    }

    public void setFedergabel(double federgabel) {
        this.federgabel = federgabel;
    }

    public double getKette_ritzerl() {
        return kette_ritzerl;
    }

    public void setKette_ritzerl(double kette_ritzerl) {
        this.kette_ritzerl = kette_ritzerl;
    }

    public double getGashebel() {
        return gashebel;
    }

    public void setGashebel(double gashebel) {
        this.gashebel = gashebel;
    }

    public double getReifen() {
        return reifen;
    }

    public void setReifen(double reifen) {
        this.reifen = reifen;
    }
}
