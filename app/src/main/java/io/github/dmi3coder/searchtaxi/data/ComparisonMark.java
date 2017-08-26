package io.github.dmi3coder.searchtaxi.data;

import android.support.annotation.StringRes;
import io.github.dmi3coder.searchtaxi.R;

/**
 * Created by dim3coder on 8/26/17.
 */
public enum ComparisonMark {
  GOOD(R.string.good_mark), UNACCEPTABLE(R.string.unacceptable_mark);

  @StringRes int stringRes;

  ComparisonMark(int stringRes) {
    this.stringRes = stringRes;
  }

  public @StringRes
  int getStringRes() {
    return stringRes;
  }
}
