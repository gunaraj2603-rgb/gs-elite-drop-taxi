function toggleReturnDate() {
    const tripType = document.getElementById('trip').value;
    const returnContainer = document.getElementById('returnDateContainer');
    const returnDateInput = document.getElementById('returnDate');

    if (tripType === 'Round-Trip Taxi') {
        returnContainer.style.display = 'block';
        returnDateInput.required = true;
    } else {
        returnContainer.style.display = 'none';
        returnDateInput.required = false;
        returnDateInput.value = '';
    }
}

async function handleBookingSubmission(e) {
    e.preventDefault();
    
    const tripTypeVal = document.getElementById('trip').value;
    const bookingData = {
        name: document.getElementById('name').value,
        mobile: document.getElementById('mobile').value,
        pickup: document.getElementById('pickup').value,
        dropOff: document.getElementById('drop').value,
        tripType: tripTypeVal,
        date: document.getElementById('date').value,
        returnDate: tripTypeVal === 'Round-Trip Taxi' ? document.getElementById('returnDate').value : null
    };

    try {
        const response = await fetch('http://localhost:8080/api/bookings', {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(bookingData)
        });
        
        if (!response.ok) {
            throw new Error("Failed to save booking on server.");
        }

        const savedBooking = await response.json();
        showSuccessModal(savedBooking);

    } catch (error) {
        console.error("Error:", error);
        alert("Booking server is offline or unreachable. Please try again later.");
    }
}

function showSuccessModal(booking) {
    const formContainer = document.querySelector('.booking-form');
    
    formContainer.innerHTML = `
        <div style="background: #e6f4ea; border: 1px solid #34a853; padding: 25px; border-radius: 8px; text-align: center; max-width: 500px; margin: 0 auto;">
            <h2 style="color: #137333; margin-bottom: 10px;">Booking Created Successfully! 🎉</h2>
            <p style="color: #5f6368; margin-bottom: 15px;">Thank you, <b>${booking.name}</b>. Your trip request has been registered.</p>
            
            <div style="text-align: left; background: white; padding: 15px; border-radius: 6px; margin-bottom: 15px; font-size: 14px; line-height: 1.6; color: #333;">
                <p><b>Booking ID:</b> #${booking.id}</p>
                <p><b>Mobile:</b> ${booking.mobile}</p>
                <p><b>Pickup:</b> ${booking.pickup}</p>
                <p><b>Drop-off:</b> ${booking.dropOff}</p>
                <p><b>Trip Type:</b> ${booking.tripType}</p>
                <p><b>Pickup Date:</b> ${booking.date}</p>
                ${booking.returnDate ? `<p><b>Return Date:</b> ${booking.returnDate}</p>` : ''}
            </div>
            
            <p style="font-size: 13px; color: #666;">Our dispatch driver will call you shortly to confirm availability.</p>
        </div>
    `;
}