package com.helmes.imageservice;

import com.google.gson.Gson;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@RestController
public class ImageServiceRestController {
    private Map<String, String> base64Image = new HashMap<>();

    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/{image}", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity write(@PathVariable String image, HttpEntity<String> httpEntity) {
        Gson gson = new Gson();
        Map<String, String> dto = gson.fromJson(httpEntity.getBody(), Map.class);
        if (dto.size() == 1 && dto.containsKey("image")) {
            this.base64Image.put(image, dto.get("image"));
            return new ResponseEntity(HttpStatus.OK);
        }

        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }

    @ResponseBody
    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/{image}", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public ResponseEntity<byte[]> read(@PathVariable String image) {
        if (!this.base64Image.containsKey(image)) throw new NotFoundException();

        CacheControl cacheControl = CacheControl.noCache();
        String data = this.base64Image.get(image).split(",")[1];
        return ResponseEntity.ok()
                .cacheControl(cacheControl)
                .body(Base64.getDecoder().decode(data.getBytes(StandardCharsets.UTF_8)));
    }
}
