package com.kotlinstping.restwhitspringbootandkotlin.controller

import com.kotlinstping.restwhitspringbootandkotlin.converters.NumberConverter
import com.kotlinstping.restwhitspringbootandkotlin.converters.NumberConverter.convertToDouble
import com.kotlinstping.restwhitspringbootandkotlin.exception.UnsupportedMathOperationException
import org.apache.tomcat.util.http.parser.HttpParser.isNumeric
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import java.util.concurrent.atomic.AtomicLong

//@RestController
class MathController1 {

    val counter: AtomicLong = AtomicLong()
    @RequestMapping(value = ["/sum/{numberOne}/{numberTwo}"])
    fun Sum(@PathVariable(value="numberOne")numberOne: String?,
            @PathVariable(value="numberTwo")numberTwo: String?
    ):Double{
        if(!NumberConverter.isNumeric(numberOne)||!NumberConverter.isNumeric(numberTwo)) throw UnsupportedMathOperationException("Please set a numeric value")
        return convertToDouble(numberOne) + convertToDouble(numberTwo)
    }

    @RequestMapping(value = ["/subtraction/{numberOne}/{numberTwo}"])
    fun subtraction(@PathVariable(value="numberOne")numberOne: String?,
                    @PathVariable(value="numberTwo")numberTwo: String?
    ):Double{
        if(!NumberConverter.isNumeric(numberOne)||!NumberConverter.isNumeric(numberTwo)) throw UnsupportedMathOperationException("Please set a numeric value")
        return convertToDouble(numberOne) - convertToDouble(numberTwo)
    }

  @RequestMapping(value = ["/multiplication/{numberOne}/{numberTwo}"])
    fun multiplication(@PathVariable(value="numberOne")numberOne: String?,
                        @PathVariable(value="numberTwo")numberTwo: String?
    ):Double{
        if(!NumberConverter.isNumeric(numberOne)||!NumberConverter.isNumeric(numberTwo)) throw UnsupportedMathOperationException("Please set a numeric value")
        return convertToDouble(numberOne) * convertToDouble(numberTwo)
    }

 @RequestMapping(value = ["/division/{numberOne}/{numberTwo}"])
    fun division(@PathVariable(value="numberOne")numberOne: String?,
                 @PathVariable(value="numberTwo")numberTwo: String?
    ):Double{
        if(!NumberConverter.isNumeric(numberOne)||!NumberConverter.isNumeric(numberTwo)) throw UnsupportedMathOperationException("Please set a numeric value")
        return convertToDouble(numberOne) / convertToDouble(numberTwo)
    }

    @RequestMapping(value = ["/mean/{numberOne}/{numberTwo}"])
    fun mean(@PathVariable(value="numberOne")numberOne: String?,
             @PathVariable(value="numberTwo")numberTwo: String?
    ):Double{
        if(!NumberConverter.isNumeric(numberOne)||!NumberConverter.isNumeric(numberTwo)) throw UnsupportedMathOperationException("Please set a numeric value")
        return (convertToDouble(numberOne) + convertToDouble(numberTwo)) /2
    }

      @RequestMapping(value = ["/squareRoot/{number}"])
    fun squareRoot(@PathVariable(value="number")number: String?
    ):Double{
        if(!NumberConverter.isNumeric(number))
            throw UnsupportedMathOperationException("Please set a numeric value")
        return Math.sqrt(convertToDouble(number)) /2
    }
}


