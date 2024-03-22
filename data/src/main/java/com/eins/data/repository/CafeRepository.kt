package com.eins.data.repository

import com.eins.domain.entity.CafeCurrentInfo
import com.eins.domain.entity.UseTime
import com.eins.domain.entity.VisitedCafe
import com.eins.domain.repository.ICafeRepository
import javax.inject.Inject

class CafeRepository @Inject constructor(

): ICafeRepository {
    override suspend fun getVisitCafeList(): List<VisitedCafe> =
        arrayListOf<VisitedCafe>().apply {
            for(i in 1 .. 10){
                this.add(VisitedCafe(
                    cafeName = "카페 A-$i",
                    address = "A시 B구 C동 ${i}로",
                    useTime = UseTime(i, 0),
                    useWatt = i * 10
                ))
            }
        }

    override suspend fun getCafeCurrentInfo(id: Int): CafeCurrentInfo =
        CafeCurrentInfo(
            cafeName = "카페 $id",
            leftSitCount = id,
            imageUrl = id.toString()
        )
}