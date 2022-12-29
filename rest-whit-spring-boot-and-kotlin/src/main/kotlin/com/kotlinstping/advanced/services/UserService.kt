package com.kotlinstping.advanced.services



import com.kotlinstping.advanced.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service
import java.util.logging.Logger

@Service
class UserService(@field:Autowired var repository: UserRepository) : UserDetailsService{

    private val logger = Logger.getLogger(UserService::class.java.name)

    override fun loadUserByUsername(username: String?): UserDetails {
        logger.info("Finding one User by Username $username!")
        val user = repository.findByUsername(username)
        return user ?: throw UsernameNotFoundException("Username $username not found!")
    }                //operador Elves que verifica se o valor do retorno é null e se não for null ele retorna o valor,
                    // se for null ele cria uma exception
}