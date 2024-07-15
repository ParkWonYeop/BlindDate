package org.dblab.blinddate.common.routingDataSource

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource
import org.springframework.transaction.support.TransactionSynchronizationManager

class RoutingDataSource : AbstractRoutingDataSource() {
    override fun determineCurrentLookupKey(): String =
        if (TransactionSynchronizationManager.isCurrentTransactionReadOnly()) {
            "reader"
        } else {
            "writer"
        }
}
