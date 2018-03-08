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

import com.datastax.oss.driver.api.core.CqlIdentifier;
import com.datastax.oss.driver.api.querybuilder.select.Selector;
import com.google.common.base.Preconditions;
import java.util.Objects;

public class RawSelector implements Selector {

  private final String rawExpression;
  private final CqlIdentifier alias;

  public RawSelector(String rawExpression) {
    this(rawExpression, null);
  }

  private RawSelector(String rawExpression, CqlIdentifier alias) {
    Preconditions.checkNotNull(rawExpression);
    this.rawExpression = rawExpression;
    this.alias = alias;
  }

  @Override
  public Selector as(CqlIdentifier alias) {
    return new RawSelector(rawExpression, alias);
  }

  @Override
  public String asCql(boolean pretty) {
    return (alias == null) ? rawExpression : rawExpression + " AS " + alias.asCql(pretty);
  }

  public String getRawExpression() {
    return rawExpression;
  }

  public CqlIdentifier getAlias() {
    return alias;
  }

  @Override
  public boolean equals(Object other) {
    if (other == this) {
      return true;
    } else if (other instanceof RawSelector) {
      RawSelector that = (RawSelector) other;
      return this.rawExpression.equals(that.rawExpression)
          && Objects.equals(this.alias, that.alias);
    } else {
      return false;
    }
  }

  @Override
  public int hashCode() {
    return Objects.hash(rawExpression, alias);
  }
}
