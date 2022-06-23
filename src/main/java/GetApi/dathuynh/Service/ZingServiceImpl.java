package GetApi.dathuynh.Service;


import GetApi.dathuynh.Service.Security.Signature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.sql.Timestamp;

import static GetApi.dathuynh.Config.Constant.*;

@Service
public class ZingServiceImpl implements ZingService {

    @Autowired
    Signature signature;

    @Value("${zing.apikey}")
    public String apikey;

    @Value("${zing.version}")
    public String zing_version;




    @Override
    public String Get_Search(String keySearch, long time) {
        try {
            String hash =
                    "ctime=".concat(String.valueOf(time)).concat("version=").concat(zing_version);
            String sig_final = signature.sig(time, hash, SEARCH);
            return MP3LINK
                    .concat(SEARCH)
                    .concat("?q=")
                    .concat(keySearch)
                    .concat("&ctime=")
                    .concat(String.valueOf(time))
                    .concat("&version=")
                    .concat(zing_version)
                    .concat("&sig=")
                    .concat(sig_final)
                    .concat("&apiKey=")
                    .concat(apikey);
        }catch (NoSuchAlgorithmException | InvalidKeyException e){
            e.printStackTrace();
        }
        return "Can't get api slug";
    }

    @Override
    public String Get_playList(String slug, long time, String id) throws NoSuchAlgorithmException, InvalidKeyException {
        String hash =
                "ctime=".concat(String.valueOf(time)).concat("id=").concat(id)
                        .concat("version=").concat(zing_version);
        String sig_final = signature.sig(time, hash, slug);
        return MP3LINK
                .concat(PLAYLIST)
                .concat("?id=")
                .concat(id)
                .concat("&ctime=")
                .concat(String.valueOf(time))
                .concat("&version=")
                .concat(zing_version)
                .concat("&sig=")
                .concat(sig_final)
                .concat("&apiKey=")
                .concat(apikey);
    }

    @Override
    public String Get_MP3(long time, String id) {
        try {
            String hash =
                    "ctime=".concat(String.valueOf(time)).concat("id=").concat(id)
                            .concat("version=").concat(zing_version);
            String sig_final = signature.sig(time, hash, LISTEN);
            return MP3LINK
                    .concat(LISTEN)
                    .concat("?id=")
                    .concat(id)
                    .concat("&ctime=")
                    .concat(String.valueOf(time))
                    .concat("&version=")
                    .concat(zing_version)
                    .concat("&sig=")
                    .concat(sig_final)
                    .concat("&apiKey=")
                    .concat(apikey);

        } catch (NoSuchAlgorithmException | InvalidKeyException e) {
            e.printStackTrace();
        }
        return "Can't get Link api";
    }

    @Override
    public String Get_Artist(String alias, long time) {
        try {
            String hash =
                    "ctime=".concat(String.valueOf(time)).concat("version=").concat(zing_version);
            String sig_final = signature.sig(time, hash, ARTIST);
            return MP3LINK
                    .concat(ARTIST)
                    .concat("?alias=")
                    .concat(alias)
                    .concat("&ctime=")
                    .concat(String.valueOf(time))
                    .concat("&version=")
                    .concat(zing_version)
                    .concat("&sig=")
                    .concat(sig_final)
                    .concat("&apiKey=")
                    .concat(apikey);
        } catch (NoSuchAlgorithmException | InvalidKeyException e) {
            e.printStackTrace();
        }
        return "Can't get Link api";
    }

    @Override
    public String Get_Home(String page) {
        try {
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            long time = timestamp.getTime() / 1000;
            String hash = "ctime=".concat(String.valueOf(time)).concat("page=").concat(page).concat("version=").concat(zing_version);
            String signa = signature.sig(time, hash, HOME);
            return MP3LINK
                    .concat(HOME)
                    .concat("?page=").concat(page)
                    .concat("&segmentId=-1")
                    .concat("&ctime=")
                    .concat(String.valueOf(time))
                    .concat("&version=")
                    .concat(zing_version)
                    .concat("&sig=")
                    .concat(signa)
                    .concat("&apiKey=")
                    .concat(apikey);
        } catch (NoSuchAlgorithmException | InvalidKeyException e) {
            e.printStackTrace();
        }
        return "Can't get Link api";
    }


    @Override
    public String Get_PodCast(String code){
        try {
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            long time = timestamp.getTime() / 1000;
            String hash = "ctime=".concat(String.valueOf(time)).concat("id=").concat(code).concat("version=").concat(zing_version);
            String signa = signature.sig(time, hash, PodCast);
            return MP3LINK
                    .concat(PodCast)
                    .concat("?id=").concat(code)
                    .concat("&ctime=")
                    .concat(String.valueOf(time))
                    .concat("&version=")
                    .concat(zing_version)
                    .concat("&sig=")
                    .concat(signa)
                    .concat("&apiKey=")
                    .concat(apikey);
        } catch (NoSuchAlgorithmException | InvalidKeyException e) {
            e.printStackTrace();
        }
        return "Can't get Link api";
    }

    @Override
    public String Get_PodCast_List(String code){
        try {
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            long time = timestamp.getTime() / 1000;
            String hash = "ctime=".concat(String.valueOf(time)).concat("id=").concat(code).concat("version=").concat(zing_version);
            String signa = signature.sig(time, hash, ListPodCast);
            return MP3LINK
                    .concat(ListPodCast)
                    .concat("?id=").concat(code)
                    .concat("&ctime=")
                    .concat(String.valueOf(time))
                    .concat("&version=")
                    .concat(zing_version)
                    .concat("&sig=")
                    .concat(signa)
                    .concat("&apiKey=")
                    .concat(apikey);
        } catch (NoSuchAlgorithmException | InvalidKeyException e) {
            e.printStackTrace();
        }
        return "Can't get Link api";
    }

    @Override
    public String Get_PodCastListen(String code){
        try {
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            long time = timestamp.getTime() / 1000;
            String hash = "ctime=".concat(String.valueOf(time)).concat("id=").concat(code).concat("version=").concat(zing_version);
            String signa = signature.sig(time, hash, ListenPodCast);
            return MP3LINK
                    .concat(ListenPodCast)
                    .concat("?id=").concat(code)
                    .concat("&ctime=")
                    .concat(String.valueOf(time))
                    .concat("&version=")
                    .concat(zing_version)
                    .concat("&sig=")
                    .concat(signa)
                    .concat("&apiKey=")
                    .concat(apikey);
        } catch (NoSuchAlgorithmException | InvalidKeyException e) {
            e.printStackTrace();
        }
        return "Can't get Link api";
    }

    @Override
    public String Get_Radio() {
        try {
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            long time = timestamp.getTime() / 1000;
            String hash = "ctime=".concat(String.valueOf(time)).concat("page=").concat("1").concat("version=").concat(zing_version);
            String signa = signature.sig(time, hash, RADIO);
            return MP3LINK
                    .concat(RADIO)
                    .concat("?page=").concat("1")
                    .concat("&ctime=")
                    .concat(String.valueOf(time))
                    .concat("&version=")
                    .concat(zing_version)
                    .concat("&sig=")
                    .concat(signa)
                    .concat("&apiKey=")
                    .concat(apikey);
        } catch (NoSuchAlgorithmException | InvalidKeyException e) {
            e.printStackTrace();
        }
        return "Can't get Link api";
    }


    @Override
    public String Get_Home_Chart() {
        try {
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            long time = timestamp.getTime() / 1000;
            String hash =
                    "ctime=".concat(String.valueOf(time))
                            .concat("version=").concat(zing_version);
            String signa = signature.sig(time, hash, Chart_Home);

            return MP3LINK
                    .concat(Chart_Home)
                    .concat("?ctime=")
                    .concat(String.valueOf(time))
                    .concat("&version=")
                    .concat(zing_version)
                    .concat("&sig=")
                    .concat(signa)
                    .concat("&apiKey=")
                    .concat(apikey);
        } catch (NoSuchAlgorithmException | InvalidKeyException e) {
            e.printStackTrace();
        }
        return "Can't get Link api";
    }

    @Override
    public String Get_Week_Chart(long time, String id) {
        try {
            String hash =
                    "ctime=".concat(String.valueOf(time))
                            .concat("id=").concat(id)
                            .concat("version=").concat(zing_version);
            String signatureTrash = signature.sig(time, hash, WEEK_CHART);
            return MP3LINK
                    .concat(WEEK_CHART)
                    .concat("?id=")
                    .concat(id)
                    .concat("&week=0&year=0")
                    .concat("&ctime=")
                    .concat(String.valueOf(time))
                    .concat("&version=")
                    .concat(zing_version)
                    .concat("&sig=")
                    .concat(signatureTrash)
                    .concat("&apiKey=")
                    .concat(apikey);
        } catch (NoSuchAlgorithmException | InvalidKeyException ex) {
            ex.printStackTrace();
        }
        return "Can't hash the API";
    }

    @Override
    public String Get_Top_100(long time) {
        try {
            String hash =
                    "ctime=".concat(String.valueOf(time))
                            .concat("version=").concat(zing_version);
            String signatureTrash = signature.sig(time, hash, TOP100);
            return MP3LINK
                    .concat(TOP100)
                    .concat("?ctime=")
                    .concat(String.valueOf(time))
                    .concat("&version=")
                    .concat(zing_version)
                    .concat("&sig=")
                    .concat(signatureTrash)
                    .concat("&apiKey=")
                    .concat(apikey);
        } catch (NoSuchAlgorithmException | InvalidKeyException e) {
            e.printStackTrace();
        }
        return "Can't hash the API";
    }

    @Override
    public String GET_ALBUM(long time) {
        try {
            String hash_album =
                    "ctime=".concat(String.valueOf(time))
                            .concat("version=").concat(zing_version);
            String signatureTrash = signature.sig(time, hash_album, ALBUM);
            return MP3LINK
                    .concat(ALBUM)
                    .concat("?ctime=")
                    .concat(String.valueOf(time))
                    .concat("&version=")
                    .concat(zing_version)
                    .concat("&sig=")
                    .concat(signatureTrash)
                    .concat("&apiKey=")
                    .concat(apikey);
        } catch (NoSuchAlgorithmException | InvalidKeyException e) {
            e.printStackTrace();
        }
        return "Can't hash the API";
    }

    @Override
    public String GetHubDetail(String id, long time) {
        try {
            String hash_album =
                    "ctime=".concat(String.valueOf(time))
                            .concat("id=").concat(id)
                            .concat("version=").concat(zing_version);
            String signatureTrash = signature.sig(time, hash_album, HUB_DETAIL);
            return MP3LINK
                    .concat(HUB_DETAIL)
                    .concat("?id=")
                    .concat(id)
                    .concat("&ctime=")
                    .concat(String.valueOf(time))
                    .concat("&version=")
                    .concat(zing_version)
                    .concat("&sig=")
                    .concat(signatureTrash)
                    .concat("&apiKey=")
                    .concat(apikey);
        } catch (NoSuchAlgorithmException | InvalidKeyException e) {
            e.printStackTrace();
        }
        return "Can't hash the API";
    }

}
