package com.example.tijn.bartenderapp;

import java.util.List;

public class PumpData {
    private List<Pump> pumps;

    public PumpData(List<Pump> pumps) {
        this.pumps = pumps;
    }

    public List<Pump> getPumps() {
        return pumps;
    }

    public void setPumps(List<Pump> pumps) {
        this.pumps = pumps;
    }
}
