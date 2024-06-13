import java.util.ArrayList;
import java.util.Hashtable;

public class Output {

	private Register a; // Register object that will be used for String representation

	private int opcode; // the 32-bit binary code

	private Hashtable<Integer, String> op = new Hashtable<Integer, String>(); // Hashtable to store op codes

	public int label = 0; // variable for label address

	/**
	 * 
	 * @param a,    the Register object
	 * @param bits, the 32-bit String
	 */
	public Output(Register a, int bits) {

		fillop(op);

		this.a = a;

		opcode = bits;

		label = 0;

	}

	/**
	 * The method takes in the opcode and returns the string representation of the
	 * code
	 * 
	 * @param binary, the binary representation of the opcode
	 * @return string representation of the opcode
	 */
	public int checkopcode(int binary) {

		int b = binary;

		int opcode = b >> 26 & 0x3f; // op code 6 bits

		if (op.containsKey(opcode)) {

			return opcode;
		}

		opcode = b >> 22 & 0x3ff; // opcode 10 bits

		if (op.containsKey(opcode)) {

			return opcode;
		}

		opcode = b >> 21 & 0x7ff;

		if (op.containsKey(opcode)) {// op code 11 bits

			return opcode;
		}

		opcode = b >> 24 & 0xff; // op code 8 bits

		if (op.containsKey(opcode)) {

			return opcode;
		}

		return 0;

	}

	/**
	 * The disable method disassemblies the program and returns the String
	 * representation of the 32-bit instruction.
	 * 
	 * @param binary,       the 32-bit binary instruction
	 * @param instructions, the amount of instructions so far
	 * @return the String representation for that 32-bit instruction
	 */
	public String decode(int binary, int instructions) {

		String convert = " ";

		String instruction = op.get(checkopcode(opcode));// bit manipulates the opcode and gets the
		// instruction

		if (instruction.equals("ADDI") || instruction.equals("ANDI") || instruction.equals("ADDI")
				|| instruction.equals("ADDIS") || instruction.equals("ANDIS") || instruction.equals("ANDS")
				|| instruction.equals("EORI") || instruction.equals("ORRI") || instruction.equals("SUBIS")
				|| instruction.equals("SUBI")) {

			convert += (instruction + " ")
					+ (a.Register1() + a.Register2() + "#" + a.checknegative(a.immediate(), 10, instruction));

		} else if (instruction.equals("ADD") || instruction.equals("ADDS") || instruction.equals("AND")
				|| instruction.equals("EOR") || instruction.equals("MUL") || instruction.equals("ORR")
				|| instruction.equals("SUB") || instruction.equals("SUBS")) {

			convert += (instruction + " ") + (a.Register1() + a.Register2() + a.Register3());

		}

		else if (instruction.equals("HALT") || instruction.equals("DUMP")) {

			convert += instruction;
		}

		else if (instruction.equals("PRNT")) {

			convert += (instruction + " ") + a.oneRegister();

		} else if (instruction.equals("PRNL")) {
			convert += "PRNL";

		} else if (instruction.equals("B.")) {

			int check = binary & 0x1f;

			label = instructions + a.checknegative(a.address(), 0, "BL");

			convert += conditions(check) + " " + "label" + (instructions + a.checknegative(a.address(), 0, "B."));

		} else if (instruction.equals("B") || instruction.equals("BL")) {

			label = (instructions + a.checknegative(a.bradd(), 0, "BL"));

			convert += instruction + " " + "label" + (instructions + a.checknegative(a.bradd(), 0, "BL"));

		}

		else if (instruction.equals("BR")) {

			convert += "BR " + a.twoRegister();

		} else if (instruction.equals("CBZ") || instruction.equals("CBNZ")) {

			convert += instruction + " " + a.Register1() + " label"
					+ (instructions + a.checknegative(a.address(), 0, "B."));

			label = (instructions + a.checknegative(a.address(), 0, "B."));

		} else if (instruction.equals("LDUR") || instruction.equals("STUR")) {

			convert += (instruction + " ")
					+ (a.Register1() + "[" + a.Register2() + "#" + a.checknegative(a.array(), 0, "LDUR") + "]");

		}

		else if (instruction.equals("LSL") || instruction.equals("LSR")) {

			convert += (instruction + " ")
					+ (a.Register1() + a.Register2() + "#" + a.checknegative(a.shamt(), 0, "LSL"));

		} else {

			convert += "Missing";
		}

		return convert;

	}

	/**
	 * The method checks which conditon it for the branch and returns the string
	 * representation
	 * 
	 * @param check, the conditonal binary doe
	 * @return returns the correct condition for B.
	 */
	public String conditions(int check) {

		String a = "";

		if (check == 0) {

			a += "B.EQ";

		} else if (check == 1) {

			a += "B.NE";
		} else if (check == 2) {

			a += "B.HS";
		}

		else if (check == 3) {

			a += "B.LO";
		} else if (check == 4) {
			a += "B.MI";
		} else if (check == 5) {

			a += "B.PL";
		} else if (check == 6) {

			a += "B.VS";
		} else if (check == 7) {
			a += "B.VC";
		} else if (check == 8) {
			a += "B.HI";
		} else if (check == 9) {
			a += "B.LS";
		} else if (check == 10) {
			a += "B.GE";
		} else if (check == 11) {
			a += "B.LT";
		} else if (check == 12) {

			a += "B.GT";
		} else if (check == 13) {

			a += "B.LE";
		}

		return a;

	}

	/**
	 * The method adds all the opcodes to the Hashtable
	 * 
	 * @param op, an empty Hashmap
	 * @return a filled out Hashmap of the opcodes
	 */
	public Hashtable<Integer, String> fillop(Hashtable<Integer, String> op) {

		op.put(0b10001011000, "ADD");
		op.put(0b1001000100, "ADDI");
		op.put(0b1011000100, "ADDIS");
		op.put(0b10001010000, "AND");
		op.put(0b1001001000, "ANDI");
		op.put(0b1111001000, "ANDIS");
		op.put(0b1110101000, "ANDS");
		op.put(0b000101, "B");
		op.put(0b100101, "BL");
		op.put(0b11010110000, "BR");
		op.put(0b10110101, "CBNZ");
		op.put(0b10110100, "CBZ");
		op.put(0b11111111110, "DUMP");
		op.put(0b11001010000, "EOR");
		op.put(0b1101001000, "EORI");
		op.put(0b11111111111, "HALT");
		op.put(0b11111000010, "LDUR");
		op.put(0b10111100010, "LDURS");
		op.put(0b10111000100, "LDURSW");
		op.put(0b11010011011, "LSL");
		op.put(0b11010011010, "LSR");
		op.put(0b10011011000, "MUL");
		op.put(0b10101010000, "ORR");
		op.put(0b1011001000, "ORRI");
		op.put(0b11111111100, "PRNL");
		op.put(0b11111111101, "PRNT");
		op.put(0b11111000000, "STUR");
		op.put(0b00111000000, "STURB");
		op.put(0b11111100000, "STURD");
		op.put(0b01111000000, "STURH");
		op.put(0b10111100000, "STURS");
		op.put(0b10111000000, "STURSW");
		op.put(0b11001011000, "SUB");
		op.put(0b1101000100, "SUBI");
		op.put(0b1111000100, "SUBIS");
		op.put(0b11101011000, "SUBS");
		op.put(0b01010100, "B.");
		return op;

	}

}
