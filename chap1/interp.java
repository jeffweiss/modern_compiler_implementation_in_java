class interp {
	static void interp(Stm s) {

	}

	/**
	 * The purpose of maxargs is to find the maximum number of arguments passed
	 * to a PrintStm anywhere in the program.
	 *
	 * This consists of two primary operations:
	 *   1. How many do we have?
	 *   2. How many do our children have?
	 * 
	 * The maximum of those two then needs to be propagated up through the tree.
	 */
	static int maxargs(Stm s) {
		return s.maxargs();
	}
	
	public static void main(String args[]) throws java.io.IOException {
		System.out.println(maxargs(prog.prog));
		interp(prog.prog);
	}
}
