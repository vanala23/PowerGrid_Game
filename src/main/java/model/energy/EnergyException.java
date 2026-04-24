package model.energy;

public class EnergyException extends Exception {
    public EnergyException(String message) {
        super(message);
    }

    @Override
    public void printStackTrace() {
        super.printStackTrace();
    }
}
