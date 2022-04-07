package Classes;

import java.util.Arrays;

public class Salon { // Toros || Erkan
    int salonId;
    int cinemaId;
    int salonRow;
    int salonCol;
    int[][]defultSalonSeats;

    public Salon(int salonId, int cinemaId, int row, int col) {
        this.salonId = salonId;
        this.cinemaId = cinemaId;
        this.salonRow = row;
        this.salonCol = col;
        defultSalonSeats = new int[this.salonRow][this.salonCol];
    }

    public Salon(){}

    public int getSalonId() {
        return salonId;
    }

    public void setSalonId(int salonId) {
        this.salonId = salonId;
    }

    public int getCinemaId() {
        return cinemaId;
    }

    public void setCinemaId(int cinemaId) {
        this.cinemaId = cinemaId;
    }

    public int[][] getDefultSalonSeats() {
        return defultSalonSeats;
    }

    public void setDefultSalonSeats(int[][] defultSalonSeats) {
        this.defultSalonSeats = defultSalonSeats;
    }

    public int getSalonRow() {
        return salonRow;
    }

    public void setSalonRow(int salonRow) {
        this.salonRow = salonRow;
    }

    public int getSalonCol() {
        return salonCol;
    }

    public void setSalonCol(int salonCol) {
        this.salonCol = salonCol;
    }

    @Override
    public String toString() {
        return "Salon{" +
                "salonId=" + salonId +
                ", cinemaId=" + cinemaId +
                ", salonRow=" + salonRow +
                ", salonCol=" + salonCol +
                ", defultSalonSeats=" + Arrays.toString(defultSalonSeats) +
                '}';
    }
}
