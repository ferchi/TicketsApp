package com.jfsb.ticketsapp.features.session.data.datasource

import com.google.firebase.firestore.DocumentId
import java.util.Date

data class UserModel(
    @DocumentId var id: String? = null,
    var name: String? = null,
    var team: Int? = null,
)
