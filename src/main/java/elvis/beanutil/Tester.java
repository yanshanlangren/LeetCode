package elvis.beanutil;

import com.alibaba.fastjson.JSON;
import org.apache.commons.beanutils.BeanUtils; //1335
//import org.springframework.beans.BeanUtils; //1049

import java.lang.reflect.InvocationTargetException;

public class Tester {

    public static void main(String[] args) throws InvocationTargetException, IllegalAccessException {
        long t0 = System.currentTimeMillis();
        Doctor d1 = new Doctor();
        d1.setId(123);
        d1.setName("elvis");
        DoctorDto d2 = new DoctorDto();
        for (int i = 0; i < 100000; i++) {
            BeanUtils.copyProperties(d2, d1);
        }
        System.out.println("d2:[" + JSON.toJSONString(d2) + "]");
        System.out.println("total time[" + (System.currentTimeMillis() - t0) + "]ms");
    }
}
