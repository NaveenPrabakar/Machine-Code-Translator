
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

public class PA2 {

	public static void main(String[] args) throws IOException {

		FileInputStream b = new FileInputStream(args[0]);

		String finish = "";

		int num = 1;

		ArrayList<String> finished = new ArrayList<>(); // stores all the output

		ArrayList<Integer> m = new ArrayList<>(); // stores all the label locations

		while (b.available() >= 4) {

			// Reads 4 bytes (4*8 = 32 bits)
			byte[] code = new byte[4];

			b.read(code);

			// gets the 32-bit instruction
			int fin = (code[3] & 0xff) + ((code[2] & 0xff) << 8) + ((code[1] & 0xff) << 16) + ((code[0] & 0xff) << 24);

			Register d = new Register(fin); // the class that does all the bit manipulation

			Output v = new Output(d, fin); // The class that outputs one line of the code

			finished.add(v.decode(fin, num) + "\n");

			m.add(v.label);

			num++;

		}

		for (int i = 0; i < finished.size(); i++) {

			if (m.contains(i + 1)) {
				finish += "label" + (i + 1) + ":" + "\n";
			}

			finish += finished.get(i);

		}

		System.out.println(finish);

	}
}
