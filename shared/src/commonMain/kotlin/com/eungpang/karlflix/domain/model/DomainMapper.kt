package com.eungpang.karlflix.domain.model

interface DomainMapper<DataModel, DomainModel> {
    fun DataModel.toDomain(): DomainModel
}

interface DataModelMapper<DataModel, DomainModel> {
    fun DomainModel.toData(): DataModel
}