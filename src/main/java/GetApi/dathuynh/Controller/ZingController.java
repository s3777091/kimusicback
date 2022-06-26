package GetApi.dathuynh.Controller;

import GetApi.dathuynh.Entity.ZingEntity;
import GetApi.dathuynh.Repository.ZingRepository;
import GetApi.dathuynh.Service.ZingService;
import org.json.JSONArray;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.data.repository.query.Param;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.sql.Timestamp;


import static GetApi.dathuynh.Config.Constant.*;

@RestController
@CrossOrigin(origins = "*")
public class ZingController {

    @Autowired
    ZingService zingService;

    @Autowired
    ZingRepository zingRepository;


    @GetMapping("/api/v2/get_play_list")
    public ResponseEntity<Object> getPlayList(@Param("code") String code) throws NoSuchAlgorithmException, InvalidKeyException {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        JSONObject detail = new JSONObject();
        detail.put("play_list", zingService.Get_playList(PLAYLIST, timestamp.getTime() / 1000, code));
        return new ResponseEntity<>(detail.toMap(), HttpStatus.OK);
    }
    
    @GetMapping("/api/v2/get_listen")
    public ResponseEntity<Object> getListenMp3(@Param("code") String code) {
        try {
            ZingEntity zing = zingRepository.findByPass(142632);
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            JSONObject detail = new JSONObject();
            String link = Jsoup.connect(zingService.Get_MP3(timestamp.getTime() / 1000, code))
                    .cookie("zmp3_rqid", zing.getZmp3_rqid())
                    .cookie("zmp3_sid", zing.getZmp3_sid())
                    .cookie("zpsid", zing.getZpsid())
                    .ignoreContentType(true).get().text();
            String tasLink = link.substring(link.indexOf("\"320\":\""), link.indexOf("\"},\"")).replace("\"320\":\"", "");
            detail.put("music", tasLink);
            return new ResponseEntity<>(detail.toMap(), HttpStatus.OK);
        } catch (IOException e) {
            return new ResponseEntity<>(e, HttpStatus.UNAUTHORIZED);
        }
    }


    //Get PodCast
    @GetMapping("/api/v2/get_podcast")
    public ResponseEntity<Object> getPostCast(@Param("code") String code) {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        JSONObject detail = new JSONObject();
        detail.put("music", zingService.Get_MP3(timestamp.getTime() / 1000, code));
        return new ResponseEntity<>(detail.toMap(), HttpStatus.OK);
    }

    @PostMapping("/api/v2/post_cookies")
    public ResponseEntity<Object> postCookies(@RequestBody ResCookies resCookies) {
        zingRepository.deleteAll();
        //Delete old one
        //Add new one
        ZingEntity zing = new ZingEntity();
        zing.setZmp3_rqid(resCookies.getZmp3_rqid());
        zing.setZmp3_sid(resCookies.getZmp3_sid());
        zing.setZpsid(resCookies.getZpsid());
        zing.setPass(142632);
        zingRepository.save(zing);
        return new ResponseEntity<>("Success post news Cookies", HttpStatus.OK);
    }

    @GetMapping("/api/v2/get_search")
    public ResponseEntity<Object> getSearchMP3(@Param("code") String code) {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        JSONObject detail = new JSONObject();
        detail.put("search", zingService.Get_Search(code, timestamp.getTime() / 1000));
        return new ResponseEntity<>(detail.toMap(), HttpStatus.OK);
    }

    @GetMapping("/api/v2/get_home_chart")
    public ResponseEntity<Object> getHomeLink() {
        JSONObject detail = new JSONObject();
        detail.put("home", zingService.Get_Home_Chart());
        return new ResponseEntity<>(detail.toMap(), HttpStatus.OK);
    }

    @GetMapping("/api/v2/get_home")
    public ResponseEntity<Object> getHome() {
        JSONArray tas = new JSONArray();
        JSONObject finalObject = new JSONObject();
        for (int i = 1; i <= 5; i++) {
            JSONObject detail = new JSONObject();
            detail.put("HomeData_".concat(String.valueOf(i)), zingService.Get_Home(String.valueOf(i)));
            tas.put(detail);
        }
        finalObject.put("HomeData", tas);
        return new ResponseEntity<>(finalObject.toMap(), HttpStatus.OK);
    }

    @GetMapping("/api/v2/get_radio")
    public ResponseEntity<Object> getRadio() {
        JSONObject detail = new JSONObject();
        detail.put("RadioData", zingService.Get_Radio());
        return new ResponseEntity<>(detail.toMap(), HttpStatus.OK);
    }

    @GetMapping("/api/v2/pod_cast")
    public ResponseEntity<Object> getRadioPodCast(@Param("code") String code) {
        JSONObject detail = new JSONObject();
        detail.put("pod", zingService.Get_PodCast(code));
        return new ResponseEntity<>(detail.toMap(), HttpStatus.OK);
    }

    @GetMapping("/api/v2/pod_cast_episode")
    public ResponseEntity<Object> getRadioPodCastList(@Param("code") String code) {
        JSONObject detail = new JSONObject();
        detail.put("pod", zingService.Get_PodCast_List(code));
        return new ResponseEntity<>(detail.toMap(), HttpStatus.OK);
    }

    @GetMapping("/api/v2/listen_pod_cast")
    public ResponseEntity<Object> getListenPodCast(@Param("code") String code) {
        JSONObject detail = new JSONObject();
        detail.put("listen", zingService.Get_PodCastListen(code));
        return new ResponseEntity<>(detail.toMap(), HttpStatus.OK);
    }

    @GetMapping("/api/v2/get_top_100")
    public ResponseEntity<Object> getTop100() {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        JSONObject detail = new JSONObject();
        detail.put("top100", zingService.Get_Top_100(timestamp.getTime() / 1000));
        return new ResponseEntity<>(detail.toMap(), HttpStatus.OK);
    }

    @GetMapping("/api/v2/get_artists")
    public ResponseEntity<Object> getArtist(@Param("code") String code) {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        JSONObject detail = new JSONObject();
        detail.put("artists", zingService.Get_Artist(code, timestamp.getTime() / 1000));
        return new ResponseEntity<>(detail.toMap(), HttpStatus.OK);
    }

    @GetMapping("/api/v2/get_album")
    public ResponseEntity<Object> getAlbum() {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        JSONObject detail = new JSONObject();
        detail.put("album", zingService.GET_ALBUM(timestamp.getTime() / 1000));
        return new ResponseEntity<>(detail.toMap(), HttpStatus.OK);
    }

    @GetMapping("/api/v2/get_hub_detail")
    public ResponseEntity<Object> getHubDetail(@Param("code") String code) {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        JSONObject detail = new JSONObject();
        detail.put("hub_detail", zingService.GetHubDetail(code, timestamp.getTime() / 1000));
        return new ResponseEntity<>(detail.toMap(), HttpStatus.OK);
    }

}
