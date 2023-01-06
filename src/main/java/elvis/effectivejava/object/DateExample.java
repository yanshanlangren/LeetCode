package elvis.effectivejava.object;

import java.util.Date;

public class DateExample {
    Date birthday;

    static class Builder {
        private Date birthday;

        public Builder() {

        }

        public Builder birthday(Date birthday) {
            this.birthday = birthday;
            return this;
        }

        public DateExample build() {
            return new DateExample(this);
        }
    }

    public DateExample(Builder builder) {
        this.birthday = builder.birthday;
    }

    public static void main(String[] args) {
        DateExample de = new DateExample.Builder().birthday(new Date()).build();
    }
}
