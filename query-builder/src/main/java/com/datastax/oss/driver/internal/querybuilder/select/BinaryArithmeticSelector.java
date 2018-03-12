/*
 * Copyright DataStax, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.datastax.oss.driver.internal.querybuilder.select;

import static com.datastax.oss.driver.internal.querybuilder.select.ArithmeticOperator.DIFFERENCE;
import static com.datastax.oss.driver.internal.querybuilder.select.ArithmeticOperator.OPPOSITE;

import com.datastax.oss.driver.api.core.CqlIdentifier;
import com.datastax.oss.driver.api.querybuilder.select.Selector;
import com.google.common.base.Preconditions;
import java.util.Objects;

public class BinaryArithmeticSelector extends ArithmeticSelector {

  private final Selector left;
  private final Selector right;
  private final CqlIdentifier alias;

  public BinaryArithmeticSelector(ArithmeticOperator operator, Selector left, Selector right) {
    this(operator, left, right, null);
  }

  public BinaryArithmeticSelector(
      ArithmeticOperator operator, Selector left, Selector right, CqlIdentifier alias) {
    super(operator);
    Preconditions.checkNotNull(left);
    Preconditions.checkNotNull(right);
    this.left = left;
    this.right = right;
    this.alias = alias;
  }

  @Override
  public Selector as(CqlIdentifier alias) {
    return new BinaryArithmeticSelector(operator, left, right, alias);
  }

  @Override
  public String asCql(boolean pretty) {
    StringBuilder builder =
        new StringBuilder(maybeParenthesize(operator, left, pretty))
            .append(' ')
            .append(operator.getSymbol())
            .append(' ')
            .append(
                maybeParenthesize((operator == DIFFERENCE ? OPPOSITE : operator), right, pretty));
    if (alias != null) {
      builder.append(" AS ").append(alias.asCql(pretty));
    }
    return builder.toString();
  }

  public Selector getLeft() {
    return left;
  }

  public Selector getRight() {
    return right;
  }

  public CqlIdentifier getAlias() {
    return alias;
  }

  @Override
  public boolean equals(Object other) {
    if (other == this) {
      return true;
    } else if (other instanceof BinaryArithmeticSelector) {
      BinaryArithmeticSelector that = (BinaryArithmeticSelector) other;
      return this.operator.equals(that.operator)
          && this.left.equals(that.left)
          && this.right.equals(that.right)
          && Objects.equals(this.alias, that.alias);
    } else {
      return false;
    }
  }

  @Override
  public int hashCode() {
    return Objects.hash(operator, left, right, alias);
  }
}
