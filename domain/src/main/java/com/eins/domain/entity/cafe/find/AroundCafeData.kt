package com.eins.domain.entity.cafe.find

import com.eins.domain.entity.cafe.find.value.Address
import com.eins.domain.entity.cafe.find.value.CafeName
import com.eins.domain.entity.cafe.find.value.ImageUrl
import com.eins.domain.entity.cafe.find.value.Star

data class AroundCafeData(
    val imageUrl: ImageUrl,
    val cafeName: CafeName,
    val star: Star,
    val address: Address
)
