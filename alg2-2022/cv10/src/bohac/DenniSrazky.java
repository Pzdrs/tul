package bohac;

import java.io.*;

public class DenniSrazky {
    private final byte day, month;
    private final short year;
    private final float srazky;

    public DenniSrazky(byte day, byte month, short year, float srazky) {
        this.day = day;
        this.month = month;
        this.year = year;
        this.srazky = srazky;
    }

    public void save(DataOutputStream outputStream) throws IOException {
        // kazdej zaznam zabere 8 bytes
        outputStream.writeByte(day); // 1b
        outputStream.writeByte(month); // 1b
        outputStream.writeShort(year); // 1b
        outputStream.writeFloat(srazky); // 4b
    }

    public static DenniSrazky load(DataInputStream inputStream) throws IOException {
        return new DenniSrazky(inputStream.readByte(), inputStream.readByte(), inputStream.readShort(), inputStream.readFloat());
    }

    public byte getDay() {
        return day;
    }

    public byte getMonth() {
        return month;
    }

    public short getYear() {
        return year;
    }

    @Override
    public String toString() {
        return "DenniSrazky{" +
                "day=" + day +
                ", month=" + month +
                ", year=" + year +
                ", srazky=" + srazky +
                '}';
    }
}
