package GetApi.dathuynh.Service;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public interface ZingService {
    String Get_playList(String slug, long time, String id) throws NoSuchAlgorithmException, InvalidKeyException;
    String Get_Home_Chart();
    String Get_Week_Chart(long time, String id);
    String Get_Home(String page);

    String Get_Top_100(long time);
    String GET_ALBUM(long time);

    String Get_Artist(String alias, long time);
    String Get_MP3(long time, String id);
    String Get_Search(String keySearch, long time);
    String Get_Radio();
    String Get_PodCast(String code);
    String Get_PodCast_List(String code);
    String Get_PodCastListen(String code);
    String GetHubDetail(String id,long time);
}
