package com.example.futurbe.services;

import com.google.zxing.WriterException;
//import net.glxn.qrgen.javase.QRCode;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class QRCodeService {

    public byte[] generateQRCode(String text, int width, int height) throws WriterException, IOException {


        return null;
    }
}
