class Tree {
	Tree left;
	String key;
	Tree right;
	
	Tree(Tree l, String k, Tree r) {
		left = l;
		key = k;
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
		return "{key: "+ key+", left: " + l + ", right: " + r + "}";
	}
}

class Exer {
	static Tree insert(String key, Tree t)	{
		if (t == null) {
			return new Tree(null, key, null);
		} else if (key.compareTo(t.key) < 0) {
			return new Tree(insert(key, t.left), t.key, t.right);
		} else if (key.compareTo(t.key) > 0) {
			return new Tree(t.left, t.key, insert(key, t.right));
		} else {
			return new Tree(t.left, key, t.right);
		}
	}
	
	public static void main(String[] args) {
		Tree t = null;
		String[] letters = { "t", "s", "p", "i", "p", "f", "b", "s", "t"};
		for(int i=0; i< letters.length; ++i) {
			t = insert(letters[i], t);
		}
		//System.out.println(t);
		//for(int i=0; i< letters.length; ++i) {
		//	System.out.println("Tree contains '" + letters[i] + "': " + t.member(letters[i]));
		//}
	}
}
