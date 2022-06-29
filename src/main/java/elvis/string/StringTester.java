package elvis.string;

import java.util.ArrayList;
import java.util.List;

public class StringTester {
    int a;
    public StringTester(int a){
        this.a = a;
    }
    public static void main(String[] args){
        System.out.println("abcdefg".contains("bcd"));
        
        List<StringTester> a = new ArrayList<>();
        a.add(new StringTester(10));
        a.add(new StringTester(11));
        a.stream().forEach(x-> x.a = 1);
        a.stream().forEach(x->System.out.println(x.a));
    }
}
