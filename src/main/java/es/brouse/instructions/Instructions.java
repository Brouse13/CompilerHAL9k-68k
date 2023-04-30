package es.brouse.instructions;

import static es.brouse.instructions.Registers.REGISTER;
import static es.brouse.instructions.Registers.TYPE;
public class Instructions {
    final Registers registers = new Registers();
    public Instruction LOA(MemorySection src, REGISTER dst) {
        final String src_memory = registers.getRegister(dst, TYPE.SPECIFIC);

        return () -> "0000" + "000" + src + src_memory;
    }

    public Instruction STO(REGISTER src, MemorySection dst) {
        final String dst_memory = registers.getRegister(src, TYPE.SPECIFIC);

        return () -> "0001" + "000" + dst + dst_memory;
    }

    public Instruction LOIP(REGISTER src, REGISTER dst) {
        final String src_register = registers.getRegister(src, TYPE.GENERAL);
        final String dst_register = registers.getRegister(dst, TYPE.SPECIFIC);

        return () -> "0010" + "00000" + src_register + "000" + dst_register;
    }

    public Instruction STIP(REGISTER src, REGISTER dst) {
        final String src_register = registers.getRegister(src, TYPE.SPECIFIC);
        final String dst_register = registers.getRegister(dst, TYPE.GENERAL);

        return () -> "0011" + "00000" + src_register + "000" + dst_register;
    }

    public Instruction GOI(MemorySection memory) {
        return () -> "0100" + "000" + memory + "0";
    }

    public Instruction GOZ(MemorySection memory) {
        return () -> "0101" + "000" + memory + "0";
    }

    public Instruction GON(MemorySection memory) {
        return () -> "0110" + "000" + memory + "0";
    }

    public Instruction EXIT() {
        return () -> "10" + "00000000000000";
    }

    public Instruction COPY(REGISTER src, REGISTER dst) {
        final String src_register = registers.getRegister(src, TYPE.COMPLETE);
        final String dst_register = registers.getRegister(dst, TYPE.COMPLETE);

        return () -> "11000" + "0000" + src_register + "0" + dst_register;
    }

    public Instruction ADD(REGISTER srcA, REGISTER srcB, REGISTER dst) {
        final String srcA_register = registers.getRegister(srcA, TYPE.COMPLETE);
        final String srcB_register = registers.getRegister(srcB, TYPE.COMPLETE);
        final String dst_register = registers.getRegister(dst, TYPE.COMPLETE);

        return () -> "11001" + "0" + srcA_register + "0" + srcB_register + "0" + dst_register;
    }

    public Instruction SUB(REGISTER srcA, REGISTER srcB, REGISTER dst) {
        final String srcA_register = registers.getRegister(srcA, TYPE.COMPLETE);
        final String srcB_register = registers.getRegister(srcB, TYPE.COMPLETE);
        final String dst_register = registers.getRegister(dst, TYPE.COMPLETE);

        return () -> "11010" + "0" + srcA_register + "0" + srcB_register + "0" + dst_register;
    }

    public Instruction AND(REGISTER srcA, REGISTER srcB, REGISTER dst) {
        final String srcA_register = registers.getRegister(srcA, TYPE.COMPLETE);
        final String srcB_register = registers.getRegister(srcB, TYPE.COMPLETE);
        final String dst_register = registers.getRegister(dst, TYPE.COMPLETE);

        return () -> "11011" + "0" + srcA_register + "0" + srcB_register + "0" + dst_register;
    }

    public Instruction SET(Constant number, REGISTER dst) {
        final String dst_register = registers.getRegister(dst, TYPE.COMPLETE);

        return () -> "11100" + number.parse(8) + dst_register;
    }

    public Instruction ADQ(Constant number, REGISTER dst) {
        final String dst_register = registers.getRegister(dst, TYPE.COMPLETE);

        return () -> "11101" + number.parse(8) + dst_register;
    }

    public Instruction LSH(Constant times, REGISTER reg, Constant direction) {
        final String register = registers.getRegister(reg, TYPE.COMPLETE);

        return () -> "11110" + times.parse(3) + "0" + register + "000" + direction.parse(1);
    }
}
