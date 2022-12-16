package com.kotlinstping.advanced.config

import com.kotlinstping.advanced.serilization.converter.YamlJackson2HttpMessageConverter
import org.springframework.context.annotation.Configuration
import org.springframework.http.MediaType
import org.springframework.http.converter.HttpMessageConverter
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Configuration// avisa o spting que é um bean de configuração
class WebConfig : WebMvcConfigurer{

    private val MEDIA_TYPE_APPLICATION_YML =  MediaType.valueOf("application/x-yaml")

    override fun extendMessageConverters(converters: MutableList<HttpMessageConverter<*>>) {
        converters.add(YamlJackson2HttpMessageConverter())
    }
// formatos de serealização de objetos.
    override fun configureContentNegotiation(configurer: ContentNegotiationConfigurer) {
        //contentNegotiation para prover em Query param
//     configurer.favorParameter(true)
//         .parameterName("mediaType")
//         .ignoreAcceptHeader(true)
//         .useRegisteredExtensionsOnly(false)
//         .defaultContentType(MediaType.APPLICATION_JSON)
//         .mediaType("json",MediaType.APPLICATION_JSON)
//         .mediaType("xml",MediaType.APPLICATION_XML)
//
//    }
        //contentNegotiation para prover em HEADER param
        configurer.favorParameter(false)
            .ignoreAcceptHeader(false)
            .useRegisteredExtensionsOnly(false)
            .defaultContentType(MediaType.APPLICATION_JSON)
            .mediaType("json", MediaType.APPLICATION_JSON)
            .mediaType("xml", MediaType.APPLICATION_XML)
            .mediaType("x-yaml", MEDIA_TYPE_APPLICATION_YML)
    }
}