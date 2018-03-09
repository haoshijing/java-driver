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
package com.datastax.oss.driver.internal.querybuilder.relation;

import com.datastax.oss.driver.api.querybuilder.relation.Term;

public class TupleTerm implements Term {

  private final Iterable<? extends Term> components;

  public TupleTerm(Iterable<? extends Term> components) {
    this.components = components;
  }

  @Override
  public String asCql(boolean pretty) {
    StringBuilder builder = new StringBuilder("(");
    boolean first = true;
    for (Term component : components) {
      if (first) {
        first = false;
      } else {
        builder.append(",");
      }
      builder.append(component.asCql(pretty));
    }
    return builder.append(")").toString();
  }

  public Iterable<? extends Term> getComponents() {
    return components;
  }
}
