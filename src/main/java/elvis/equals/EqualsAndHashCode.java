package elvis.equals;

import java.util.HashMap;

public class EqualsAndHashCode {
    public EqualsAndHashCode(String name) {
        this.name = name;
    }

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof EqualsAndHashCode) {
            EqualsAndHashCode e = (EqualsAndHashCode) o;
            return e.getName().equals(this.getName());
        }
        return false;
    }

    @Override
    public int hashCode(){
        return this.getName().hashCode();
    }


    public static void main(String[] args) {
        EqualsAndHashCode e1 = new EqualsAndHashCode("钟煜");
        EqualsAndHashCode e2 = new EqualsAndHashCode("钟煜");
        System.out.println("hashcode: e1[" + e1.hashCode() + "], e2[" + e2.hashCode() + "]");
        System.out.println("equals[" + e1.equals(e2) + "]");
        HashMap<EqualsAndHashCode, Integer> m = new HashMap<EqualsAndHashCode, Integer>();
        m.put(e1, 1);
        m.put(e2, 2);
        System.out.println(m.get(e1));
        System.out.println(m.get(e2));

        HashMap<String, Integer> m1 = new HashMap<String, Integer>();
        String s1 = new String("钟煜");
        String s2 = new String("钟煜");
        m1.put(s1, 1);
        m1.put(s2, 2);
        System.out.println(m1.get(s1));
        System.out.println(m1.get(s2));
    }
}
