package GetApi.dathuynh.Controller;

import GetApi.dathuynh.Service.ZingService;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.sql.Timestamp;
import java.util.List;

import static GetApi.dathuynh.Config.Constant.*;

@RestController
@CrossOrigin(origins = "*")
public class ZingController {

    @Autowired
    ZingService zingService;

    @GetMapping("/api/v2/get_play_list")
    public ResponseEntity<Object> getPlayList(@Param("code") String code) throws NoSuchAlgorithmException, InvalidKeyException {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        JSONObject detail = new JSONObject();
        detail.put("play_list", zingService.Get_playList(PLAYLIST, timestamp.getTime() / 1000, code));
        return new ResponseEntity<>(detail.toMap(), HttpStatus.OK);
    }


    @GetMapping("/api/v2/get_listen")
    public ResponseEntity<Object> getListenMp3(@Param("code") String code, HttpServletResponse response) {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        JSONObject detail = new JSONObject();

        String token = "MHwxLjU0LjM2LjYxfG51WeBGx8MTY1NTgxMzQ1NjA2NQ";
        Cookie cookie = new Cookie("zmp3_rqid", token);
        cookie.setDomain("zingmp3.vn");
        cookie.setPath("/");

        Cookie cookie1 = new Cookie("zmp3_rqid_lagecy", token);
        cookie.setDomain("zingmp3.vn");
        cookie.setPath("/");
        response.addCookie(cookie);
        response.addCookie(cookie1);

        detail.put("music", zingService.Get_MP3(timestamp.getTime() / 1000, code));
        return new ResponseEntity<>(detail.toMap(), HttpStatus.OK);
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
    public ResponseEntity<Object> getHome(@Param("page") String page) {
        JSONObject detail = new JSONObject();
        detail.put("HomeData", zingService.Get_Home(page));
        return new ResponseEntity<>(detail.toMap(), HttpStatus.OK);
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
