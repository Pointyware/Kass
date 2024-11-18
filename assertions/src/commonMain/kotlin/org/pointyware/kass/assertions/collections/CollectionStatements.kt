package org.pointyware.kass.assertions.collections

import org.pointyware.kass.assertions.Statement

/**
 *
 */
object CollectionStatements {
    fun <T> contains(item: T) = Statement<Collection<T>> { subject, asserter ->
        asserter.assertTrue("$subject does not contain $item", item in subject)
    }

    fun <T> doesNotContain(item: T) = Statement<Collection<T>> { subject, asserter ->
        asserter.assertTrue("$subject contains $item", item !in subject)
    }

    fun <T> isIn(items: Collection<T>) = Statement<T> { subject, asserter ->
        asserter.assertTrue("$subject is not in $items", subject in items)
    }

    fun <T> isNotIn(items: Collection<T>) = Statement<T> { subject, asserter ->
        asserter.assertTrue("$subject is in $items", subject !in items)
    }
}
