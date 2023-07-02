package vip.wexiang.job.txt.test;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

public class TestObjectFieldOffset {
    public static void main(String[] args) throws Exception{
        Field f = Unsafe.class.getDeclaredField("theUnsafe"); //Internal reference
        f.setAccessible(true);
        Unsafe unsafe = (Unsafe) f.get(null);

        // Now you can use the unsafe object
        Class<Student> s = Student.class;
        long name = unsafe.objectFieldOffset(s.getDeclaredField("name"));
        System.out.println(name);
        long age = unsafe.objectFieldOffset(s.getDeclaredField("age"));
        System.out.println(age);
    }
}
class Student{
    private String name;
    private Integer age;
}

