abstract class Stm {
	abstract int maxargs();
}

class CompoundStm extends Stm {
	Stm stm1, stm2;
	CompoundStm(Stm s1, Stm s2) {stm1=s1; stm2=s2;}
	int maxargs() {
		return Math.max(stm1.maxargs(), stm2.maxargs());
	}
}

class AssignStm extends Stm {
	String id; Exp exp;
	AssignStm(String i, Exp e) {id=i; exp=e;}
	int maxargs() {
		return exp.maxargs();		
	}
}

class PrintStm extends Stm {
	ExpList exps;
	PrintStm(ExpList e) {exps=e;}
	int maxargs() {
		return Math.max(exps.size(), exps.maxargs());
	}
}

abstract class Exp {
	abstract int maxargs();
}

class IdExp extends Exp {
	String id;
	IdExp(String i) {id=i;}
	int maxargs() {
		return 0;
	}
}

class NumExp extends Exp {
	int num;
	NumExp(int n) {num=n;}
	int maxargs() {
		return 0;
	}
}

class OpExp extends Exp {
	Exp left, right; int oper;
	final static int Plus=1,Minus=2,Times=3,Div=4;
	OpExp(Exp l, int o, Exp r) {left=l; oper=o; right=r;}
	int maxargs() {
		return Math.max(left.maxargs(), right.maxargs());
	}
}

class EseqExp extends Exp {
	Stm stm; Exp exp;
	EseqExp(Stm s, Exp e) {stm=s; exp=e;}
	int maxargs() {
		return Math.max(stm.maxargs(), exp.maxargs());
	}
}

abstract class ExpList {
	abstract int size();
	abstract int maxargs();
}

class PairExpList extends ExpList {
	Exp head; ExpList tail;
	public PairExpList(Exp h, ExpList t) {head=h; tail=t;}
	
	int size() {
		return 1 + tail.size();
	}
	int maxargs() {
		return Math.max(head.maxargs(), tail.maxargs());
	}
}

class LastExpList extends ExpList {
	Exp head; 
	public LastExpList(Exp h) {head=h;}
	
	int size() {
		return 1;
	}
	
	int maxargs() {
		return head.maxargs();
	}
}