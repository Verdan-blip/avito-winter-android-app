package ru.verdan.common.resource

interface ResourceProvider {

    fun getString(id: Int): String

    fun getString(id: Int, vararg formatArgs: Any): String
}
