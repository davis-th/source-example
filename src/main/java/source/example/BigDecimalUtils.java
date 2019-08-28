package source.example;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;

public class BigDecimalUtils {
  private static final DecimalFormat INTEGER_FORMAT = new DecimalFormat("0");
  private static final DecimalFormat TWO_DECIMAL_FORMAT = new DecimalFormat("0.00");

  /**
   * 100 BigDecimal，计算百分比时使用
   */
  private static final BigDecimal HUNDRED = new BigDecimal(100);

  /**
   * 做除法时保留四位小数, 转换为百分数时就是2为小数
   */
  private static final int SCALE_FOUR = 4;

  private BigDecimalUtils() {
    //do nothing
  }

  /**
   * 判断两个BigDecimal的值是否相等？
   *
   * @param num1
   * @param num2
   * @return
   */
  public static boolean valueEquals(BigDecimal num1, BigDecimal num2) {
    return num1 != null
        && num2 != null
        && num1.compareTo(num2) == 0;
  }

  /**
   * 获取BigDecimal的值，如果为null, 则返回BigDecimal.Zero
   */
  public static BigDecimal valueOrZero(BigDecimal value) {
    return value == null ?
        BigDecimal.ZERO :
        value;
  }

  /**
   * 传入一个数字，返回值为 0 和该值之间的较大者
   *
   * @param number 传入的数字
   * @return zero if number < zero, or else number
   */
  public static BigDecimal getNumberNoLessThanZero(BigDecimal number) {
    return number.compareTo(BigDecimal.ZERO) < 0 ? BigDecimal.ZERO : number;
  }

  /**
   * 传入一个BigDecimal和Integer，计算两者的乘积
   *
   * @param multiplier   乘数
   * @param multiplicand 被乘数
   * @return
   */
  public static BigDecimal multiplyInteger(BigDecimal multiplier, Integer multiplicand) {
    return multiplier.multiply(new BigDecimal(multiplicand));
  }

  /**
   * 将一个 BigDecimal 数字格式化为整数字符串返回
   *
   * @param num 源数字
   * @return 对应的整数字符串
   */
  public static String formatAsInteger(BigDecimal num) {
    synchronized (INTEGER_FORMAT) {
      return INTEGER_FORMAT.format(num);
    }
  }

  /**
   * 将一个 BigDecimal 数字格式化为保留两位小数返回
   *
   * @param num 源数字
   * @return 保留两位小数
   */
  public static String formatAsTwoDecimal(BigDecimal num) {
    synchronized (TWO_DECIMAL_FORMAT) {
      return TWO_DECIMAL_FORMAT.format(num);
    }
  }

  /**
   * 计算百分比，保留2位
   *
   * @param percent 要计算的百分比
   * @return percent % 的小数形式
   */
  public static BigDecimal divideByHundred(String percent) {
    return new BigDecimal(percent).divide(HUNDRED, 2, RoundingMode.UNNECESSARY);
  }

  /**
   * 两数相减
   *
   * @param value1 减数
   * @param value2 被减数
   * @return
   */
  public static BigDecimal subtract(BigDecimal value1, BigDecimal value2) {
    return value1.subtract(value2);
  }

  /**
   * 两数相加
   *
   * @param value1
   * @param value2
   * @return
   */
  public static BigDecimal add(BigDecimal value1, BigDecimal value2) {
    return value1.add(value2);
  }

  /**
   * 判断两个数是否相等
   *
   * @param value1 第一个数
   * @param value2 第二个数
   * @return 是否相等
   */
  public static boolean equals(BigDecimal value1, BigDecimal value2) {
    return value1.compareTo(value2) == 0;
  }

  /**
   * 判断两个数是否不相等
   *
   * @param value1 第一个数
   * @param value2 第二个数
   * @return 是否不相等
   */
  public static boolean notEquals(BigDecimal value1, BigDecimal value2) {
    return !equals(value1, value2);
  }

  /**
   * 多个数相加
   *
   * @param nums
   * @return
   */
  public static BigDecimal sum(BigDecimal... nums) {
    return Arrays.stream(nums)
        .filter(Objects::nonNull)
        .reduce(BigDecimal.ZERO, BigDecimal::add);
  }

  /**
   * 判断值是否大于0
   *
   * @param num 要比较的值
   * @return 比较结果
   */
  public static boolean greaterThanZero(String num) {
    return new BigDecimal(num).compareTo(BigDecimal.ZERO) > 0;
  }

  /**
   * 判断值是否小于0
   *
   * @param num 要比较的值
   * @return 比较结果
   */
  public static boolean lessThanZero(String num) {
    return new BigDecimal(num).compareTo(BigDecimal.ZERO) < 0;
  }

  /**
   * 将指定的对象中的属性值相加
   */
  public static <T> BigDecimal sumBy(List<T> items, Function<T, BigDecimal> evaluator) {
    return items.stream()
        .map(evaluator)
        .filter(Objects::nonNull)
        .reduce(BigDecimal.ZERO, BigDecimal::add);
  }
}
