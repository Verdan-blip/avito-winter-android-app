package ru.verdan.feature.home.presentation

interface HomeRouter {

    fun navigateToPlayer(trackIds: List<Long>, selectedTrackId: Long)
}
