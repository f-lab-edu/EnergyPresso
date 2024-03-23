package com.eins.data.repository

import com.eins.domain.entity.CafeCurrentInfo
import com.eins.domain.entity.UseTime
import com.eins.domain.entity.VisitedCafe
import com.eins.domain.entity.cafe.find.AroundCafeData
import com.eins.domain.entity.cafe.find.FrequentlyCafeData
import com.eins.domain.entity.cafe.find.value.Address
import com.eins.domain.entity.cafe.find.value.CafeName
import com.eins.domain.entity.cafe.find.value.ImageUrl
import com.eins.domain.entity.cafe.find.value.Star
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

    override suspend fun getAroundCafeList(): List<AroundCafeData> =
        arrayListOf<AroundCafeData>().apply {
            for (i in 0 .. 10){
                this.add(AroundCafeData(
                    imageUrl = ImageUrl("https://search.pstatic.net/common/?src=https%3A%2F%2Fldb-phinf.pstatic.net%2F20200506_223%2F1588730227110kCxsl_JPEG%2FuI6citkjAfP1NRbklRuQvpBP.jpeg.jpg"),
                    cafeName = CafeName("FOLKI"),
                    star = Star(4.5f),
                    address = Address("서울 종로구 사직로9길 6")
                ))
            }
        }

    override suspend fun getFrequentlyCafeList(): List<FrequentlyCafeData> =
        arrayListOf<FrequentlyCafeData>().apply {
            for(i in 0 .. 10){
                this.add(FrequentlyCafeData(
                    imageUrl = ImageUrl("https://search.pstatic.net/common/?src=https%3A%2F%2Fldb-phinf.pstatic.net%2F20200506_223%2F1588730227110kCxsl_JPEG%2FuI6citkjAfP1NRbklRuQvpBP.jpeg.jpg"),
                    cafeName = CafeName("FOLKI")
                ))
            }
        }
}