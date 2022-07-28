package elvis.MyEnum;

public enum MyEnum {
    ROOM(1), PERSON(2);

    private int value;

    MyEnum(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }

    public static void main(String[] args) {
        for (MyEnum e : MyEnum.values()) {
            System.out.println(e.name());
            System.out.println(e.getValue());
            System.out.println(e);
        }
    }
}
