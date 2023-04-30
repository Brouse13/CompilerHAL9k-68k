package es.brouse.instructions;

public class MemorySection {
    private final String section;

    /**
     * Main class constructor used to create new {@link MemorySection}
     * instances. This sections will be a 8b fixed bin.
     *
     * @throws IllegalArgumentException If the address length is higher to 8b
     * @param addr address to parse
     */
    public MemorySection(String addr) {
        final int hex = Integer.parseInt(addr, 16);
        StringBuilder bits = new StringBuilder(Integer.toBinaryString(hex));

        if (bits.length() > 8) throw new IllegalArgumentException("Memory address must be a 8b address");

        //We force to get a 8b address
        for (int i = bits.length(); i < 8; i++) bits.insert(0, "0");

        section =  bits.toString();
    }

    @Override
    public String toString() {
        return section;
    }
}
