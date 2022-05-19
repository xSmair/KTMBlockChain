package com.example.ktmblockchain;

import com.example.ktmblockchain.bean.PdfData;
import org.apache.poi.util.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;


@org.springframework.web.bind.annotation.RestController
public class RestController {

    @Autowired
    ChainManager chainManager;

    @PostMapping(value = "add", produces = MediaType.APPLICATION_PDF_VALUE)
    public byte[] addToBlockChain(@RequestBody PdfData data) throws IOException {
        String doc = chainManager.addBlock(data);
        System.out.println(new File(doc).getAbsolutePath());
        InputStream in = new FileInputStream("result.pdf");
        return IOUtils.toByteArray(in);
    }

    @GetMapping(value = "validate")
    public Boolean validateBlock(@RequestParam String hash){
        return chainManager.validateBlock(hash);
    }
}
