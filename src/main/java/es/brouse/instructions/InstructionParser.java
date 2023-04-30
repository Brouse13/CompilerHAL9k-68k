package es.brouse.instructions;

import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;

import static es.brouse.instructions.Registers.REGISTER;
public class InstructionParser {
    private final Logger logger = Logger.getLogger(this.getClass().getName());

    /**
     * Parse the givn instruction into the correct {@link Instruction} class.
     *
     * @param instruction instruction to parse
     * @return the parsed instruction.
     */
    public Instruction parseInstruction(final String instruction) {
        final String[] parts = instruction.toUpperCase(Locale.ROOT).split(" ", 2);
        Instructions instructions = new Instructions();

        Instruction inst = null;
        try {
            final String[] split = parts[1].replaceAll("\\(", "")
                    .replaceAll("\\)", "")
                    .replaceAll(" ", "").split(",");

            switch (parts[0]) {
                case "LOA"  -> inst = instructions.LOA(new MemorySection(split[0]), REGISTER.valueOf(split[1]));
                case "STO"  -> inst = instructions.STO(REGISTER.valueOf(split[0]), new MemorySection(split[1]));
                case "LOIP" -> inst = instructions.LOIP(REGISTER.valueOf(split[0]), REGISTER.valueOf(split[1]));
                case "STIP" -> inst = instructions.STIP(REGISTER.valueOf(split[0]), REGISTER.valueOf(split[1]));
                case "GOI"  -> inst = instructions.GOI(new MemorySection(split[0]));
                case "GON"  -> inst = instructions.GON(new MemorySection(split[0]));
                case "GOZ"  -> inst = instructions.GOZ(new MemorySection(split[0]));
                case "EXIT" -> inst = instructions.EXIT();
                case "COPY" -> inst = instructions.COPY(REGISTER.valueOf(split[0]), REGISTER.valueOf(split[1]));
                case "ADD"  -> inst = instructions.ADD(REGISTER.valueOf(split[0]), REGISTER.valueOf(split[1]), REGISTER.valueOf(split[2]));
                case "SUB"  -> inst = instructions.SUB(REGISTER.valueOf(split[0]), REGISTER.valueOf(split[1]), REGISTER.valueOf(split[2]));
                case "AND"  -> inst = instructions.AND(REGISTER.valueOf(split[0]), REGISTER.valueOf(split[1]), REGISTER.valueOf(split[2]));
                case "SET"  -> inst = instructions.SET(new Constant(split[0]), REGISTER.valueOf(split[1]));
                case "ADQ"  -> inst = instructions.ADQ(new Constant(split[0]), REGISTER.valueOf(split[1]));
                case "LSH"  -> inst = instructions.LSH(new Constant(split[0]), REGISTER.valueOf(split[1]), new Constant(split[2]));
                default -> logger.log(Level.WARNING, "Instruction " + split[0] + "not found");
            }
        }catch (ArrayIndexOutOfBoundsException e) {
            logger.log(Level.WARNING, "Unable to parse instruction: " + instruction);
        }
        return inst;
    }
}
