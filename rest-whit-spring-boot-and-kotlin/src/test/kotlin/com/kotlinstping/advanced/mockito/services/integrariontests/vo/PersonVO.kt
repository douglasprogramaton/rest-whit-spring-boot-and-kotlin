package com.kotlinstping.advanced.mockito.services.integrariontests.vo

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonPropertyOrder


@JsonPropertyOrder("id", "first_name", "last_name", "address", "gender")
data class PersonVO (

     var id: Long = 0,

     @field:JsonProperty("first_name")
     var firstName: String = "",

     @field:JsonProperty("last_name")
     var lastName: String = "",

     var address: String = "",

     //@field:JsonIgnore
     var gernde: String = ""

)
