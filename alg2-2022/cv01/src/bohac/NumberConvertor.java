package bohac;

/**
 * Number convertor class that converts numbers between different basis
 */
public class NumberConvertor {
    private final int baseFrom, baseTo;

    public NumberConvertor(int baseFrom, int baseTo) {
        if (!Utils.inRange(baseFrom, 2, 16) || !Utils.inRange(baseTo, 2, 16))
            throw new IllegalArgumentException("Base must be between 2 and 16");
        this.baseFrom = baseFrom;
        this.baseTo = baseTo;
    }

    /**
     * Convert numbers that can be represented as Integers
     *
     * @param value Integer value
     * @return Converted number as an Object (versatile - can be cast to whatever we want)
     */
    public Object convert(int value) {
        return convert(String.valueOf(value));
    }

    /**
     * Convert numbers that can't be represented as Integers
     *
     * @param value String value
     * @return Converted number as an Object (versatile - can be cast to whatever we want)
     */
    public Object convert(String value) throws NumberFormatException {
        return Integer.toString(Integer.parseInt(String.valueOf(value), baseFrom), baseTo);
    }

    public int getBaseFrom() {
        return baseFrom;
    }

    public int getBaseTo() {
        return baseTo;
    }
}
