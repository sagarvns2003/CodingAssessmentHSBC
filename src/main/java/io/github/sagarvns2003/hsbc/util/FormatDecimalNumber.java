package io.github.sagarvns2003.hsbc.util;

import java.math.RoundingMode;
import java.text.NumberFormat;

/**
 * @author Vidya Sagar Gupta
 */
public class FormatDecimalNumber {
  private NumberFormat nf = NumberFormat.getNumberInstance();

  public FormatDecimalNumber() {
    nf.setMaximumFractionDigits(2);
    nf.setRoundingMode(RoundingMode.UP);
    nf.setGroupingUsed(false);
  }

  public String formatNow(double value) {
    return nf.format(value);
  }
}
