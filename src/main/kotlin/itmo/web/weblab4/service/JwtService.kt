package itmo.web.weblab4.service

import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import io.jsonwebtoken.io.Decoders
import io.jsonwebtoken.security.Keys
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Service
import java.security.Key
import java.util.*
import java.util.function.Function


@Service
class JwtService {
    private val SECRET_KEY ="357638792F423F4528482B4D6251655468566D597133743677397A2443264629"

    fun extractUsername(token:String):String{
        try {
            return extractClaim(token, Claims::getSubject)
        }catch (e:Exception){
            return ""
        }
    }
    fun extractAllClaims(token: String): Claims {
        return Jwts.parserBuilder().setSigningKey(getSignInKey())
            .build()
            .parseClaimsJws(token)
            .body
    }
    fun validateToken(token: String?): Boolean {
        return try {
            Jwts.parserBuilder().setSigningKey(getSignInKey()).build()
                .parseClaimsJws(token)
            true
        } catch (e: java.lang.Exception) {
            false
        }
    }

    fun generateAccessToken(userDetails: UserDetails, expirationTime:Long):String{
        return generateAccessToken(mutableMapOf(),userDetails, expirationTime)
    }

    fun isTokenValid(token: String,userDetails: UserDetails):Boolean{
        val username: String = extractUsername(token)
        return (username.equals(userDetails.username)) && !isTokenExpired(token)
    }

    private fun isTokenExpired(token: String): Boolean {
        return extractExpiration(token).before(Date())
    }

    private fun extractExpiration(token: String):Date{
        return extractClaim(token,Claims::getExpiration)
    }

     fun makeAccessJWTExpirationTime():Long{
        return System.currentTimeMillis()+1000*24*30
    }

    fun makeRefreshJWTExpirationTime():Long{
        return System.currentTimeMillis()+1000*30*10
    }

    fun generateAccessToken(extraClaims: Map<String, Any>, userDetails: UserDetails,expirationTime:Long):String{
        return Jwts
            .builder()
            .setClaims(extraClaims)
            .setSubject(userDetails.username)
            .setIssuedAt(Date(System.currentTimeMillis()))
            .setExpiration(Date(expirationTime))
            .signWith(getSignInKey(),SignatureAlgorithm.HS256)
            .compact()
    }

    private fun getSignInKey(): Key {
        val keyBytes= Decoders.BASE64.decode(SECRET_KEY)
        return Keys.hmacShaKeyFor(keyBytes)
    }

    fun <T> extractClaim(token: String, claimsResolver:Function<Claims,T>): T{
        println("extract Claim")
        val claims = extractAllClaims(token)
        println("all claims are extacted")
        return claimsResolver.apply(claims)
    }

    fun generateRefreshToken(userDetails: UserDetails, expirationTime:Long):String{
        return generateRefreshToken(mutableMapOf(),userDetails, expirationTime)
    }

    fun generateRefreshToken(extraClaims: Map<String, Any>, userDetails: UserDetails,expirationTime:Long):String{
        return Jwts
            .builder()
            .setClaims(extraClaims)
            .setSubject(userDetails.username)
            .setIssuedAt(Date(System.currentTimeMillis()))
            .setExpiration(Date(expirationTime))//24 hours
            .signWith(getSignInKey(),SignatureAlgorithm.HS256)
            .compact()
    }
}
