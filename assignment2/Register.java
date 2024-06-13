
public class Register {

	private int num; // the 32-bit binary number

	public Register(int binary) {

		num = binary;
	}

	/**
	 * Grabs the Rd/Rt
	 * 
	 */
	public String Register1() {

		int x = num >> 0 & 0x1f;

		return Registers(x) + ", ";
	}

	/**
	 * Grabs the rd/rt without the comma
	 * 
	 */
	public String oneRegister() {

		int x = num >> 0 & 0x1f;

		return Registers(x);

	}

	/**
	 * Grabs the Rn
	 * 
	 */
	public String Register2() {

		int x = num >> 5 & 0x1f;

		return Registers(x) + ", ";
	}

	/**
	 * Grabs the rn without the comma
	 * 
	 */
	public String twoRegister() {

		int x = num >> 5 & 0x1f;

		return Registers(x);
	}

	/**
	 * Grabs the Rm
	 * 
	 */
	public String Register3() {

		int x = num >> 16 & 0x1f;

		return Registers(x);

	}

	/**
	 * Grabs the address for 8 bit op code
	 * 
	 */
	public int address() {

		int x = num >> 5 & 0x7ffff;

		return x;
	}

	/**
	 * Grabs the address for 6 bit op code
	 * 
	 */
	public int bradd() {

		int x = num & 0x3ffffff;

		return x;
	}

	/**
	 * The method takes in the Register op-code and returns the correct string
	 * representation
	 * 
	 * @param Register, checks if the Register falls under 28, 29, 30, 31
	 * @return the correct String representation
	 */
	public String Registers(int Register) {

		if (Register == 28) {
			return "SP";
		}
		if (Register == 31) {
			return "XZR";
		}
		if (Register == 29) {

			return "FP";
		}
		if (Register == 30) {

			return "LR";
		}

		return "X" + Register;

	}

	/**
	 * Grabs the immediate for I-types
	 * 
	 */
	public int immediate() {

		int x = num >> 10 & 0xfff;

		return x;
	}

	/**
	 * Grabs the address for the LDUR and STUR
	 * 
	 */
	public int array() {

		int x = num >> 12 & 0x1ff;

		return x;
	}

	/**
	 * Grabs the Shamt for LSL and LSR
	 * 
	 */
	public int shamt() {

		int x = num >> 10 & 0x3f;

		return x;
	}

	/**
	 * The method checknegative, if the immediate/address is a negative it turns it
	 * into the correct negative number.
	 * 
	 * @param number,   the immediate for the instruction
	 * @param length,   the length of the op-code
	 * @param instruct, the instruction the number is for
	 * @return the postive/negative representation of the number
	 */
	public int checknegative(int number, int length, String instruct) {

		if (length == 10 && number > 2047) {

			return number - 4096;
		}

		else if (instruct.equals("LDUR") && number > 255) {

			return number - 512;

		} else if (instruct.equals("BL") && number > 33554431) {

			return number - 67108864;

		} else if (instruct.equals("B.") && number > 262143) {

			return number - 524288;
		}

		else if (instruct.equals("LSL") && number > 31) {

			return number - 64;
		}

		return number;
	}

}
