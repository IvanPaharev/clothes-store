package com.netcracker.store.web.webservice;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Created by A-one on 31.05.2017.
 */
@ToString
@Getter @Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class Rates {
    private Double AUD;
    private Double EUR;
    private Double RUB;
    private Double GBP;
    private Double CAD;
    private Double PLN;

    public Double getAUD() {
        return AUD;
    }

    @JsonProperty("AUD")
    public void setAUD(Double AUD) {
        this.AUD = AUD;
    }

    public Double getEUR() {
        return EUR;
    }

    @JsonProperty("EUR")
    public void setEUR(Double EUR) {
        this.EUR = EUR;
    }

    public Double getRUB() {
        return RUB;
    }

    @JsonProperty("RUB")
    public void setRUB(Double RUB) {
        this.RUB = RUB;
    }

    public Double getGBP() {
        return GBP;
    }

    @JsonProperty("GBP")
    public void setGBP(Double GPB) {
        this.GBP = GPB;
    }

    public Double getCAD() {
        return CAD;
    }

    @JsonProperty("CAD")
    public void setCAD(Double CAD) {
        this.CAD = CAD;
    }

    public Double getPLN() {
        return PLN;
    }

    @JsonProperty("PLN")
    public void setPLN(Double PLN) {
        this.PLN = PLN;
    }
}
