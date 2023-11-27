import kr.ac.kumoh.ce.s20190633.s23w12retrofit.Song
import retrofit2.http.GET

interface SongApi {
    @GET("song")
    suspend fun getSongs(): List<Song>
}