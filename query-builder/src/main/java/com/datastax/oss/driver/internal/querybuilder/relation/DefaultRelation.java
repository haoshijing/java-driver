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

import com.datastax.oss.driver.api.querybuilder.relation.Relation;
import com.datastax.oss.driver.api.querybuilder.relation.Term;
import com.google.common.base.Preconditions;

public class DefaultRelation implements Relation {

  private final LeftHandSide leftHandSide;
  private final String operator;
  private final Term rightHandSide;

  public DefaultRelation(LeftHandSide leftHandSide, String operator, Term rightHandSide) {
    Preconditions.checkNotNull(leftHandSide);
    Preconditions.checkNotNull(operator);
    this.leftHandSide = leftHandSide;
    this.operator = operator;
    this.rightHandSide = rightHandSide;
  }

  @Override
  public String asCql(boolean pretty) {
    StringBuilder builder =
        new StringBuilder(leftHandSide.asCql(pretty)).append(' ').append(operator);
    if (rightHandSide != null) {
      builder.append(' ').append(rightHandSide.asCql(pretty));
    }
    return builder.toString();
  }

  public LeftHandSide getLeftHandSide() {
    return leftHandSide;
  }

  public String getOperator() {
    return operator;
  }

  public Term getRightHandSide() {
    return rightHandSide;
  }
}
