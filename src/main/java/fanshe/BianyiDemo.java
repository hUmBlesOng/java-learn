public class BianyiDemo {
	public static void main(String[] args) {
		try {
            Class clz = Class.forName(args[0]);
            Object o = clz.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
	}
}
