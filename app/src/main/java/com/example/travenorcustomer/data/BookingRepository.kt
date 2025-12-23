package com.example.travenorcustomer.data

import com.example.travenorcustomer.model.Booking
import kotlinx.coroutines.flow.Flow

interface BookingRepository {

    fun bookingStatus(): Flow<Booking>

    suspend fun requestBooking(booking: Booking) : Booking
}