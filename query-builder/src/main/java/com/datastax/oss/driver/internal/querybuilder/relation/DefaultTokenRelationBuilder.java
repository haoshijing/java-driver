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

import com.datastax.oss.driver.api.core.CqlIdentifier;
import com.datastax.oss.driver.api.querybuilder.relation.Relation;
import com.datastax.oss.driver.api.querybuilder.relation.Term;
import com.datastax.oss.driver.api.querybuilder.relation.TokenRelationBuilder;

public class DefaultTokenRelationBuilder implements TokenRelationBuilder {

  private final Iterable<CqlIdentifier> identifiers;

  public DefaultTokenRelationBuilder(Iterable<CqlIdentifier> identifiers) {
    this.identifiers = identifiers;
  }

  @Override
  public Relation build(String operator, Term rightHandSide) {
    return new DefaultRelation(new TokenLeftHandSide(identifiers), operator, rightHandSide);
  }
}
