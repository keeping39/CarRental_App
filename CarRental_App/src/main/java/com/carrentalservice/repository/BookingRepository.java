package com.carrentalservice.repository;

import com.carrentalservice.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {

    @Query("From Booking booking where lower(booking.dateOfBooking) like '%:bookingName%'")
    Booking findBookingByName(@Param("bookingName") String bookingName);
}
