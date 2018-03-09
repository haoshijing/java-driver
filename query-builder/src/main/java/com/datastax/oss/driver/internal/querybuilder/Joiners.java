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
package com.datastax.oss.driver.internal.querybuilder;

import com.datastax.oss.driver.api.core.CqlIdentifier;
import com.google.common.base.Joiner;
import com.google.common.collect.Iterables;

public class Joiners {

  private static final Joiner WITH_COMMA = Joiner.on(',');

  public static String joinWithComma(Iterable<CqlIdentifier> identifiers, boolean pretty) {
    return Joiner.on(",").join(Iterables.transform(identifiers, i -> i.asCql(pretty)));
  }
}
