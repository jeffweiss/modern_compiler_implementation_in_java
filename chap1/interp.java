class interp {
	static void interp(Stm s) {

	}

	static int count(Stm s) {
		if (s instanceof PrintStm) {
			PrintStm ps = (PrintStm) s;
			return ps.exps.size();
		}
		return 0;
	}

	static int maxargs(Exp e) {
		int my_count = 0;

		if (e instanceof OpExp) {
			OpExp oe = (OpExp) e;
			int child_count = Math.max(maxargs(oe.left), maxargs(oe.right));
			return Math.max(my_count, child_count);

		} else if (e instanceof EseqExp) {
			EseqExp ee = (EseqExp) e;
			int child_count = Math.max(maxargs(ee.stm), maxargs(ee.exp));
			return Math.max(my_count, child_count);
		}

		return my_count;
	}

	static int maxargs(ExpList el) {
		int my_count = 0;

		if (el instanceof PairExpList) {
			PairExpList pel = (PairExpList) el;
			int child_count = Math.max(maxargs(pel.head), maxargs(pel.tail));
			return Math.max(my_count, child_count);

		} else if (el instanceof LastExpList) {
			LastExpList lel = (LastExpList) el;
			return Math.max(my_count, maxargs(lel.head));
		}
		return my_count;
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
		//first let's count how many I have at my current statement
		int my_count = count(s);

		if (s instanceof CompoundStm) {
			// if I'm a compound statement then I have two branchs I need to traverse
			// to find the maximum of my children
			CompoundStm cs = (CompoundStm) s;
			int child_count = Math.max(maxargs(cs.stm1), maxargs(cs.stm2));
			return Math.max(my_count, child_count);

		} else if (s instanceof AssignStm) {
			// if I'm an assignment statement, I need to traverse the expression
			// looking for applicable children because the expression might be a
			// combination statement/expression.
			AssignStm as = (AssignStm) s;
			int child_count = maxargs(as.exp);
			return Math.max(my_count, child_count);

		} else if (s instanceof PrintStm) {
			// so my_count already has the count for the current PrintStm, but we
			// still need to traverse its expression list because one of its children
			// may have a larger list than the current PrintStm does
			PrintStm ps = (PrintStm) s;
			int child_count = maxargs(ps.exps);
			return Math.max(my_count, child_count);
		}
		return my_count;
	}
	
	public static void main(String args[]) throws java.io.IOException {
		System.out.println(maxargs(prog.prog));
		interp(prog.prog);
	}
}
