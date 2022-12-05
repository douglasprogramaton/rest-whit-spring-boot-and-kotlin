package com.kotlinstping.restwhitspringbootandkotlin.controller

import com.kotlinstping.restwhitspringbootandkotlin.converters.NumberConverter
import com.kotlinstping.restwhitspringbootandkotlin.exception.UnsupportedMathOperationException
import com.kotlinstping.restwhitspringbootandkotlin.math.SimpleMath
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.concurrent.atomic.AtomicLong

@RestController
class MathController {

    val counter: AtomicLong = AtomicLong()

    private val math:SimpleMath=SimpleMath()// instance of SimpleMath

    @RequestMapping(value = ["/sum/{numberOne}/{numberTwo}"])
    fun Sum(@PathVariable(value="numberOne")numberOne: String?,
            @PathVariable(value="numberTwo")numberTwo: String?
    ):Double{
        if(!isNumeric(numberOne)||!isNumeric(numberTwo)) throw UnsupportedMathOperationException("Please set a numeric value")
        return math.sum(NumberConverter.convertToDouble(numberOne), convertToDouble(numberTwo))
    }

    @RequestMapping(value = ["/subtraction/{numberOne}/{numberTwo}"])
    fun subtraction(@PathVariable(value="numberOne")numberOne: String?,
                    @PathVariable(value="numberTwo")numberTwo: String?
    ):Double{
        if(!isNumeric(numberOne)||!isNumeric(numberTwo)) throw UnsupportedMathOperationException("Please set a numeric value")
        return math.subtraction(NumberConverter.convertToDouble(numberOne), convertToDouble(numberTwo))
    }

  @RequestMapping(value = ["/multiplication/{numberOne}/{numberTwo}"])
    fun multiplication(@PathVariable(value="numberOne")numberOne: String?,
                        @PathVariable(value="numberTwo")numberTwo: String?
    ):Double{
        if(!isNumeric(numberOne)||!isNumeric(numberTwo)) throw UnsupportedMathOperationException("Please set a numeric value")
        return math.multiplication(NumberConverter.convertToDouble(numberOne), convertToDouble(numberTwo))
    }

 @RequestMapping(value = ["/division/{numberOne}/{numberTwo}"])
    fun division(@PathVariable(value="numberOne")numberOne: String?,
                 @PathVariable(value="numberTwo")numberTwo: String?
    ):Double{
        if(!isNumeric(numberOne)||!isNumeric(numberTwo)) throw UnsupportedMathOperationException("Please set a numeric value")
        return math.division(NumberConverter.convertToDouble(numberOne), convertToDouble(numberTwo))
    }

    @RequestMapping(value = ["/mean/{numberOne}/{numberTwo}"])
    fun mean(@PathVariable(value="numberOne")numberOne: String?,
             @PathVariable(value="numberTwo")numberTwo: String?
    ):Double{
        if(!isNumeric(numberOne)||!isNumeric(numberTwo)) throw UnsupportedMathOperationException("Please set a numeric value")
        return math.mean(NumberConverter.convertToDouble(numberOne), convertToDouble(numberTwo))
    }

      @RequestMapping(value = ["/squareRoot/{number}"])
    fun squareRoot(@PathVariable(value="number")number: String?
    ):Double{
        if(!isNumeric(number))
            throw UnsupportedMathOperationException("Please set a numeric value")
        return math.squareRoott(NumberConverter.convertToDouble(number))
    }


    private fun convertToDouble(strNumber: String?): Double {
        if (strNumber.isNullOrBlank())
            return 0.0
        val number= strNumber.replace(",".toRegex(),".")//conversion to american standard.
        return if (isNumeric(number))number.toDouble() else 0.0// checks if the number matches the regex.
    }

    private fun isNumeric(strNumber: String?): Boolean {
   if(strNumber.isNullOrBlank()) return false
        val number= strNumber.replace(",".toRegex(),".")
        return number.matches("""[-+]?[0,9]*\.?[0-9]+""".toRegex())

    }
}