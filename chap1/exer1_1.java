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
	
	public String toString() {
		String l = (left != null) ? left.toString() : "";
		String r = (right != null) ? right.toString() : "";
		return "{key: "+ key+", value: " + value + ", left: " + l + ", right: " + r + "}";
	}
}

class Exer {
	static Tree insert(String key, Object binding, Tree t)	{
		if (t == null) {
			return new Tree(null, key, binding, null);
		} else if (key.compareTo(t.key) < 0) {
			return new Tree(insert(key, binding, t.left), t.key, t.value, t.right);
		} else if (key.compareTo(t.key) > 0) {
			return new Tree(t.left, t.key, t.value, insert(key, binding, t.right));
		} else {
			return new Tree(t.left, key, binding, t.right);
		}
	}
	
	public static void main(String[] args) {
		Tree t = null;
		String[] letters = { "t", "s", "p", "i", "p", "f", "b", "s", "t"};
		for(int i=0; i< letters.length; ++i) {
			t = insert(letters[i], letters[i].hashCode(), t);
		}
		//System.out.println(t);
		//for(int i=0; i< letters.length; ++i) {
		//	System.out.println("Tree contains '" + letters[i] + "': " + t.member(letters[i]));
		//}
	}
}
