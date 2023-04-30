package es.brouse.instructions;

@FunctionalInterface
public interface Instruction {
    /**
     * Return the compiled instruction in machine code.
     *
     * @return the machine coe of the instruction
     */
    String compile();
}
