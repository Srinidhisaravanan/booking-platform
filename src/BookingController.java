
@RestController
@RequestMapping("/bookings")
public class BookingController {

    @PostMapping
    public String book() {
        return "Booking successful";
    }
}
