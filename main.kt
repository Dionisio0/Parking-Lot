package parking

import java.util.*

fun main(){
    val scanner = Scanner(System.`in`)
    var postiAuto = arrayOfNulls<Car?>(0)
    var createPark = false;
    var go = true;
    while(go){
        var choice = scanner.next();
        when(choice){
            "create" -> {
                val spotNumbers = scanner.nextInt()
                postiAuto = arrayOfNulls(spotNumbers)
                createPark = true
                println("Created a parking lot with $spotNumbers spots.")
            }
            "park" ->{
                if(!createPark){
                    println("Sorry, parking lot is not created.")
                } else {
                    val registrationNumber = scanner.next()
                    val color = scanner.next().toLowerCase()
                    var parked = false;
                    for (index in postiAuto.indices) {
                        if (postiAuto[index] == null) {
                            println("$color car parked on the spot ${index+1}.")
                            postiAuto[index] = Car(registrationNumber, color)
                            parked = true;
                            break;
                        }
                    }
                    if(!parked) println("Sorry, parking lot is full.")
                }
            }
            "leave" -> {
                if(!createPark){
                    println("Sorry, parking lot is not created.")
                } else {
                    var spot = scanner.nextInt()
                    spot -= 1
                    if(postiAuto[spot] != null){
                        postiAuto[spot] = null
                        println("Spot ${spot+1} is free.")
                    } else println("There is no car in the spot ${spot+1}.")
                }
            }
            "status" -> {
                if(!createPark){
                    println("Sorry, parking lot is not created.")
                } else {
                    var isEmpty = true;
                    for (car in postiAuto) if (car != null) isEmpty = false
                    if (isEmpty)
                        println("Parking lot is empty.")
                    else {
                        for (index in postiAuto.indices) {
                            if (postiAuto[index] != null)
                                println("${index + 1} ${postiAuto[index]?.registrationNumber} ${postiAuto[index]?.color}")
                        }
                    }
                }
            }
            "reg_by_color" -> {
                if(!createPark){
                    println("Sorry, parking lot is not created.")
                } else {
                    val color = scanner.next().toLowerCase()
                    var count = 0
                    var s = ""
                    for (car in postiAuto) if (car?.color == color) count++
                    if (count != 0) {
                        for (car in postiAuto) {
                            if (car?.color == color) {
                                s += car.registrationNumber
                                count -= 1
                                if (count != 0) s += ", "
                            }
                        }
                        println(s)
                    } else {
                        println("No cars with color ${color.toUpperCase()} were found.")
                    }
                }
            }
            "spot_by_color" -> {
                if (!createPark) {
                    println("Sorry, parking lot is not created.")
                } else {
                    val color = scanner.next().toLowerCase()
                    var count = 0
                    var s = ""
                    for (car in postiAuto) if (car?.color == color) count++
                    if (count != 0) {
                        for (index in postiAuto.indices) {
                            if (postiAuto[index]?.color == color) {
                                s += (index + 1)
                                count -= 1
                                if (count != 0) s += ", "
                            }
                        }
                        println(s)
                    } else {
                        println("No cars with color ${color.toUpperCase()} were found.")
                    }
                }
            }
            "spot_by_reg" -> {
                if (!createPark) {
                    println("Sorry, parking lot is not created.")
                } else {
                    var found = false
                    val reg = scanner.next()
                    for(index in postiAuto.indices){
                        if(postiAuto[index]?.registrationNumber == reg) {
                            found = true
                            println(index + 1)
                            break
                        }
                    }
                    if(!found) println("No cars with registration number $reg were found.")
                }
            }
            "exit" -> go = false
        }
    }
}


class Car(val registrationNumber: String, val color: String)
