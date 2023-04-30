package es.brouse.instructions;

public class Registers {
    public enum REGISTER {
        T0("000"),
        T1("001"),
        X2("010"),
        X3("011"),
        X4("100"),
        X5("101"),
        X6("110"),
        X7("111");
        final String id;
        REGISTER(String id) {this.id = id;}
    }
    enum TYPE {
        SPECIFIC,
        GENERAL,
        COMPLETE
    }

    /**
     * Get the bin representation of the registers depending on
     * the demanded param {@param type}.
     *
     * @throws IllegalArgumentException If the register doesn't match on the asked param type
     * @param register register to get it's bin representation
     * @param type type to parse the register to
     * @return the corresponding binary string
     */
    public String getRegister(REGISTER register, TYPE type) {

        if (TYPE.SPECIFIC == type && register != REGISTER.T1 && register != REGISTER.T0)
            throw new IllegalArgumentException("Register must be an specific register");

        if (TYPE.GENERAL == type && !(register == REGISTER.T1 || register == REGISTER.T0))
            throw new IllegalArgumentException("Register must be a general register");

        if (type == TYPE.SPECIFIC) return String.valueOf(register.ordinal());

        return register.id;
    }

}
