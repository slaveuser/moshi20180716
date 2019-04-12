/*
 * Copyright (C) 2018 Square, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.squareup.moshi.kotlin.codegen

import com.google.common.truth.Truth.assertThat
import com.squareup.kotlinpoet.ParameterizedTypeName
import com.squareup.kotlinpoet.WildcardTypeName
import com.squareup.kotlinpoet.asClassName
import com.squareup.moshi.kotlin.codegen.TypeResolver
import org.junit.Test

class TypeResolverTest {
  private val resolver = TypeResolver()

  @Test
  fun ensureClassNameNullabilityIsPreserved() {
    assertThat(resolver.resolve(Int::class.asClassName().asNullable()).nullable).isTrue()
  }

  @Test
  fun ensureParameterizedNullabilityIsPreserved() {
    val nullableTypeName = ParameterizedTypeName.get(
        List::class.asClassName(),
        String::class.asClassName())
        .asNullable()

    assertThat(resolver.resolve(nullableTypeName).nullable).isTrue()
  }

  @Test
  fun ensureWildcardNullabilityIsPreserved() {
    val nullableTypeName = WildcardTypeName.subtypeOf(List::class.asClassName())
        .asNullable()

    assertThat(resolver.resolve(nullableTypeName).nullable).isTrue()
  }
}
