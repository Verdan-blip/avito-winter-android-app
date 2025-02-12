package ru.verdan.common.resource

interface ResourceProvider {

    fun getString(id: Int): String
}
