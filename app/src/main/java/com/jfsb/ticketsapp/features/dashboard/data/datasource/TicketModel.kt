package com.jfsb.ticketsapp.features.dashboard.data.datasource

import com.google.firebase.firestore.DocumentId
import java.util.Date

data class TicketModel(
    @DocumentId var id: String? = null,
    var added:Date? = null,
    var author:String? = null,
    var date:Date? = null,
    var description:String? = null,
    var files: List<String>? = null,
    var status:String? = null,
    var team:Int? = null,
    var title:String? = null,
    var type:Int? = null,
    var version: Double? = null
)
