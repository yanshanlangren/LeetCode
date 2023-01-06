package elvis.effectivejava.builder;

public final class Person {
    //required
    private String name;

    //optional
    private String sex;
    private int age;

    private Person(Builder builder) {
        this.name = builder.name;
        this.sex = builder.sex;
        this.age = builder.age;
    }

    @Override
    public String toString() {
        return "My name is " + this.name + ", I am " + this.sex + ", I am " + age + " years old.";
    }

    public static class Builder {
        private String name;
        private String sex;
        private int age;

        public Builder(String name) {
            this.name = name;
        }

        public Builder sex(String sex) {
            this.sex = sex;
            return this;
        }

        public Builder age(int age) {
            this.age = age;
            return this;
        }

        public Person build() {
            return new Person(this);
        }
    }

    public static void main(String[] args) {
        Person p = new Person.Builder("elvis").build();
        System.out.println("123");
    }
}
