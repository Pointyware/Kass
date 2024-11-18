package org.pointyware.kass.assertions.collections

import org.pointyware.kass.assertions.Condition
import kotlin.test.Asserter

/**
 * Extends [Condition] to provide additional statements for collections.
 */
class CollectionCondition<T>(
    override val subject: Collection<T>,
    asserter: Asserter
): Condition<Collection<T>>(subject, asserter) {
    fun contains(item: T): CollectionCondition<T> {
        asserter.assertTrue("$subject does not contain $item", item in subject)
        return this
    }

    fun doesNotContain(item: T): CollectionCondition<T> {
        asserter.assertTrue("$subject contains $item", item !in subject)
        return this
    }

    override fun and(): CollectionCondition<T> {
        return this
    }
}
