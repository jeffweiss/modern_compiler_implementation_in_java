class Tree {
	Tree left;
	String key;
	Object value;
	Tree right;
	
	Tree(Tree l, String k, Object v, Tree r) {
		left = l;
		key = k;
		value = v;
		right = r;
	}
	
	boolean member(String k) {
		int comp = k.compareTo(key);
		if (comp < 0) return (left != null) ? left.member(k) : false;
		else if (comp > 0) return (right != null) ? right.member(k) : false;
		else return true;
	}
	
	Tree insert(String key, Object binding) {
		if (key.compareTo(this.key) < 0) {
			return new Tree(left.insert(key, binding), this.key, value, right);
		} else if (key.compareTo(this.key) > 0) {
			return new Tree(left, this.key, value, right.insert(key, binding));
		} else {
			return new Tree(left, key, binding, right);
		}
	}
	
	public String toString() {
		String l = (left != null) ? left.toString() : "";
		String r = (right != null) ? right.toString() : "";
		return "{key: "+ key+", value: " + value + ", left: " + l + ", right: " + r + "}";
	}
}

class EmptyTree extends Tree {
	EmptyTree() {
		super(null, null, null, null);
	}
	
	Tree insert(String key, Object binding) {
		return new Tree(new EmptyTree(), key, binding, new EmptyTree());
	}
	public String toString() { 
		return ""; 
	}
}

class Exer {
	
	public static void main(String[] args) {
		Tree t = new EmptyTree();
		String[] letters = { "t", "s", "p", "i", "p", "f", "b", "s", "t"};
		//String[] letters = { "a", "b", "c", "d", "e", "f", "g", "h", "i"};
		for(int i=0; i< letters.length; ++i) {
			t = t.insert(letters[i], letters[i].hashCode());
		}
		System.out.println(t);
		//for(int i=0; i< letters.length; ++i) {
		//	System.out.println("Tree contains '" + letters[i] + "': " + t.member(letters[i]));
		//}
	}
}
