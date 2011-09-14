package renren_webcanvas_tutorial

class Event {
    String city
    String name
    Date startDate
    Date endDate
    String description
    static constraints = {
        name()
        city()
        description(maxSize: 5000)
        startDate()
        endDate()
    }
}
