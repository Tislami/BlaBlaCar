package com.zeroone.blablacar.domain.model

data class Post(
    val id: Int,
    val car: Car,
    val user: User,
    val from: String,
    val to: String,
    val date: String,
    val time: String,
    val price: Float,
) {
    fun dayOfWeek(): String {
        return "Monday"
    }
}


var defaultPost = Post(
    id = 0,
    car = Car(name = "Mercedes S Class", "",  4,4,0),
    user = User("Name","Surname", listOf(5.0f)),
    from = "Location1",
    to = "Location2",
    date = "15 SEPTEMBER",
    time = "15:50",
    price = 15.0f
)
