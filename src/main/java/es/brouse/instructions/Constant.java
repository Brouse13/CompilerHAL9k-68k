package es.brouse.instructions;

public record Constant(String number) {

    /**
     * Parse a number constant into the bin representation
     * with a max fixed length.
     *
     * @throws IllegalArgumentException If the number bits is higher to {@param length}
     * @param length bits max length
     * @return bin representation
     */
    public String parse(int length) {
        final int num = Integer.parseInt(number);
        final StringBuilder bin = new StringBuilder(Integer.toBinaryString(num));

        //Check for length
        if (bin.length() > length)
            throw new IllegalArgumentException("Constant must be a " + length + "b integer");

        //Complete with 0's
        for (int i = bin.length(); i < length; i++)
            bin.insert(0, "0");

        return bin.toString();
    }
}
