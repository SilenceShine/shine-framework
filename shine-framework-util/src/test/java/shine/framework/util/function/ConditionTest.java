package shine.framework.util.function;

import cn.hutool.core.util.StrUtil;

class ConditionTest {

    public static void main(String[] args) {
        Condition.of(StrUtil::isNotBlank).isTrue("", System.out::println);
        Condition.of(StrUtil::isNotBlank).isTrue("not null", System.out::println);
        Condition.deal(StrUtil::isNotBlank, "not null", System.out::println);

        Condition<Integer> condition = Condition.of(i -> i % 2 == 0, i -> i % 3 == 0);
        for (int i = 0; i < 10; i++) {
            condition.isTrue(i, System.out::println);
        }

    }

}
